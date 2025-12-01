import javax.sound.sampled.*;
import java.net.URL;

public class MusicManager {
    private Clip musicClip;
    private FloatControl musicVolumeControl;
    private boolean isMuted = false;
    private float currentVolume = 0.6f;
    private String currentTrack = "";
    
    public void playMusic(String filepath, boolean loop) {
        if (filepath.equals(currentTrack) && musicClip != null && musicClip.isRunning()) {
            return;
        }
        
        stopMusic();
        currentTrack = filepath;
        
        try {
            URL url = getClass().getResource(filepath);
            if (url == null) {
                System.out.println("⚠️ Could not find music file: " + filepath);
                return;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            
            if (musicClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                musicVolumeControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(currentVolume);
            }
            
            if (loop) {
                musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                musicClip.start();
            }
            
        } catch (Exception e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }
    
    public void playSound(String filepath) {
        playSound(filepath, 0.7f);
    }
    
    public void playSound(String filepath, float volume) {
        if (isMuted) return;
        
        new Thread(() -> {
            try {
                URL url = getClass().getResource(filepath);
                if (url == null) return;
                
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                
                if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    float min = volumeControl.getMinimum();
                    float max = volumeControl.getMaximum();
                    float dB = min + (max - min) * volume;
                    volumeControl.setValue(dB);
                }
                
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
                
                clip.start();
                
            } catch (Exception e) {
            }
        }).start();
    }
    
    public void stopMusic() {
        if (musicClip != null) {
            musicClip.stop();
            musicClip.flush();
            musicClip.close();
            musicClip = null;
        }
        currentTrack = "";
    }
    
    public void setVolume(float volume) {
        currentVolume = volume;
        if (musicVolumeControl != null && !isMuted) {
            float min = musicVolumeControl.getMinimum();
            float max = musicVolumeControl.getMaximum();
            float dB = min + (max - min) * volume;
            musicVolumeControl.setValue(dB);
        }
    }
    
    public void toggleMute() {
        isMuted = !isMuted;
        if (musicVolumeControl != null) {
            if (isMuted) {
                musicVolumeControl.setValue(musicVolumeControl.getMinimum());
            } else {
                setVolume(currentVolume);
            }
        }
    }
    
    public boolean isPlaying() {
        return musicClip != null && musicClip.isRunning();
    }
}
