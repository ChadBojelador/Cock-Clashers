import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.io.IOException;
import java.net.URL;

public class Player {

    public int x, y;
    public int speed;
    public int direction; 
    public boolean isMoving;

    private BufferedImage spriteSheet;
    private int spriteCounter = 0;
    private int spriteNum = 0; 
    
    private final int[] walkSequence = {0, 1, 2, 3}; 
    private int sequenceIndex = 0;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.speed = 2; 
        this.direction = 0;
        loadImages();
    }

    private void loadImages() {
        try {
            URL url = getClass().getResource("/res/Char.png");
            if (url != null) {
                spriteSheet = ImageIO.read(url);
            } else {
                 System.out.println("⚠️ Error: '/res/Char.png' not found!");
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error loading player sprite: " + e.getMessage());
        }
    }

    public void update(Set<Integer> activeKeys, int[][] worldMap, int tileSize) {
        isMoving = false;
        int nextX = x;
        int nextY = y;

        if (activeKeys.contains(KeyEvent.VK_W)) {
            nextY -= speed;
            direction = 1; // Up
            isMoving = true;
        } else if (activeKeys.contains(KeyEvent.VK_S)) {
            nextY += speed;
            direction = 0; // Down
            isMoving = true;
        } else if (activeKeys.contains(KeyEvent.VK_A)) {
            nextX -= speed;
            direction = 2; // Left
            isMoving = true;
        } else if (activeKeys.contains(KeyEvent.VK_D)) {
            nextX += speed;
            direction = 3; // Right
            isMoving = true;
        }

        if (isMoving) {
            if (!checkCollision(nextX, nextY, worldMap, tileSize)) {
                x = nextX;
                y = nextY;
            }
            updateAnimation();
        } else {
            spriteNum = 0; 
        }
    }

    private void updateAnimation() {
        spriteCounter++;
        if (spriteCounter > 10) { 
            sequenceIndex++;
            if (sequenceIndex >= walkSequence.length) {
                sequenceIndex = 0;
            }
            spriteNum = walkSequence[sequenceIndex];
            spriteCounter = 0;
        }
    }

    private boolean checkCollision(int targetX, int targetY, int[][] map, int tileSize) {
        int hitboxWidth = tileSize;   
        int hitboxHeight = tileSize;
        int margin = 4;
        
    
        int[][] corners = {
            {targetX + margin, targetY + margin},                                     
            {targetX + hitboxWidth - 1 - margin, targetY + margin},                  
            {targetX + margin, targetY + hitboxHeight - 1 - margin},               
            {targetX + hitboxWidth - 1 - margin, targetY + hitboxHeight - 1 - margin} 
        };
        
        for (int[] corner : corners) {
            int col = corner[0] / tileSize;
            int row = corner[1] / tileSize;
            
           
            if (col < 0 || row < 0 || col >= map[0].length || row >= map.length) {
                return true;
            }
            
            int tileType = map[row][col];
            
            if (tileType != 2 && tileType != 3) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D g2, int tileSize) {
        if (spriteSheet != null) {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

            int columns = 4; 
            int rows = 4; 

            int frameWidth = spriteSheet.getWidth() / columns;
            int frameHeight = spriteSheet.getHeight() / rows;

            int renderRow = switch (direction) {
                case 0 -> 0; // Down
                case 1 -> 3; // Up
                case 2 -> 1; // Left
                case 3 -> 2; // Right
                default -> 0;
            };

            int srcX = spriteNum * frameWidth;
            int srcY = renderRow * frameHeight;

            int renderSize = (int)(tileSize * 1.5);  
            int offsetX = (tileSize - renderSize) / 2;
            int offsetY = (tileSize - renderSize) / 2;
            
            g2.drawImage(spriteSheet,
                    x + offsetX, y + offsetY, x + offsetX + renderSize, y + offsetY + renderSize, 
                    srcX, srcY, srcX + frameWidth, srcY + frameHeight, 
                    null
            );
        } else {
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, tileSize, tileSize);
        }
    }
}
