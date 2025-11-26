# 🐓 Cock Clashers: Object-Oriented Coliseum

## 📘 Project Overview
**Cock Clashers: Object-Oriented Coliseum** is a Java-based battle simulator game inspired by Pokémon-style combat mechanics, but instead of Pokémon, it features **roosters** as the main battling characters.

The project aims to apply the fundamental principles of **Object-Oriented Programming (OOP)** — including **Encapsulation**, **Inheritance**, **Polymorphism**, and **Abstraction** — in designing an interactive and engaging turn-based combat system.

## 🎯 Description
In **Cock Clashers**, players can select their **roosters**, each with distinct attributes such as **HP**, **Attack**, **Defense**, and unique **skills**. Battles occur in a **turn-based system**, where strategy and rooster selection determine the outcome.

The game currently includes:
- A base `Rooster` class with key attributes and methods.
- A structure for subclasses representing different **rooster types**.
- A **battle logic system** that calculates attacks and damage.
- Planned integration of a **JavaFX-based GUI** for a more engaging experience.

The main goal of the game is to defeat opponent roosters through strategic combat while managing health and utilizing special abilities effectively.

### Future Improvements and Planned Features:
- Rooster **types** with varying strengths and weaknesses.
- Implementation of **special skill classes** and animations.
- Enhanced **AI opponent** and difficulty levels.
- Player **progression system** and data saving.
- Full GUI implementation with JavaFX.

## 🧠 Concepts Applied
- **Encapsulation** – protecting class data through private fields and getters/setters.
- **Inheritance** – allowing various rooster types to inherit properties from the base class.
- **Polymorphism** – enabling different attack behaviors depending on the rooster type.
- **Abstraction** – simplifying complex battle mechanics into reusable class structures.

## 🛠️ Technologies Used
- **Programming Language:** Java
- **Framework:** JavaFX (for GUI implementation)
- **IDE:** IntelliJ IDEA
- **Version Control:** Git and GitHub

## 📁 Project Structure
```
Cock-Clashers/
├── src/
│   ├── models/          # Rooster classes and game entities
│   ├── controllers/     # Game logic and battle controllers
│   ├── views/          # JavaFX GUI components
│   └── utils/          # Helper classes and utilities
├── Documentation/       # Project documentation
├── LICENSE             # MIT License
└── README.md          # This file
```

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- JavaFX SDK (if not included in your JDK)
- IntelliJ IDEA or any Java IDE

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/ChadBojelador/Cock-Clashers.git
   ```
2. Open the project in IntelliJ IDEA
3. Configure JavaFX SDK in your project settings (if needed)
4. Run the main application file

## 👥 Collaborators
| Name | Role | Contribution |
|------|------|---------------|
| **Chad Laurence Bojelador** | Lead Developer | Project structure, main battle logic, and OOP implementation |
| **James Bunyi** | UI Designer | JavaFX layout and GUI design *(planned)* |
| **Frinz Jairus Dagon** | Systems Analyst | Documentation and feature planning *(in progress)* |
| **Aira Comia** | Gameplay Designer | Concept refinement and skill balancing *(in development)* |

## 🚧 Project Status
> **Status:** Work in Progress  
> Currently focusing on skill system implementation, GUI setup, and testing rooster subclasses.

## 📝 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📝 Notes
This project is developed for academic purposes to demonstrate the application of Object-Oriented Programming principles through a creative and interactive game concept.

---

*"Sa coliseum, hindi lang lakas ang puhunan — diskarte rin."* 🐔
*"In the coliseum, it's not just about strength — it's about strategy too."*