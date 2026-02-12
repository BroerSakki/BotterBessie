package com;

import java.util.Scanner;

public class GameWorld {

    // Properties
    private int worldHeight;
    private int worldWidth;
    private Cell[][] world;
    private Player player;
    private final Scanner input;
    private boolean gameRunning;

    // Contructor
    public GameWorld() {
        this.worldHeight = 0;
        this.worldWidth = 0;
        this.player = new Player();
        this.input = new Scanner(System.in);
        this.gameRunning = false;
    }

    // Accessors
    public void setWorldHeight(int worldHeight) {
        this.worldHeight = worldHeight;
    }

    public void setWorldWidth(int worldWidth) {
        this.worldWidth = worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public void createWorld(int worldHeight, int worldWidth) {
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
        this.world = new Cell[worldHeight][worldWidth];

        // Initialize the world with random walls and coins
        initializeWorld();

        // Place player at a random starting position (not on a wall)
        placePlayer();
    }

    private void initializeWorld() {
        java.util.Random random = new java.util.Random();
        boolean exitPlaced = false;

        for (int i = 0; i < worldHeight; i++) {
            for (int j = 0; j < worldWidth; j++) {
                // Create a new Cell for each position
                world[i][j] = new Cell();

                // Randomly decide what to place in this cell
                // 30% chance for wall, 20% chance for coin, 40% chance for empty, 10% chance for exit
                double randomValue = random.nextDouble();

                if (randomValue < 0.3) {
                    // Wall (occupied)
                    world[i][j].setOccupied(true);
                    world[i][j].setOccupationType(Cell.Type.WALL);
                } else if (randomValue < 0.5) {
                    // Coin (not occupied)
                    world[i][j].setOccupied(false);
                    world[i][j].setOccupationType(Cell.Type.COIN);
                } else if (randomValue < 0.9) {
                    // Empty space (not occupied)
                    world[i][j].setOccupied(false);
                    world[i][j].setOccupationType(Cell.Type.PATH);
                } else {
                    // Exit (not occupied) - but only place one exit total
                    if (!exitPlaced) {
                        world[i][j].setOccupied(false);
                        world[i][j].setOccupationType(Cell.Type.EXIT);
                        exitPlaced = true;
                    } else {
                        // If exit already placed, make this a path instead
                        world[i][j].setOccupied(false);
                        world[i][j].setOccupationType(Cell.Type.PATH);
                    }
                }
            }
        }

        // Ensure there's at least one exit if none was placed
        if (!exitPlaced) {
            // Place exit at a random location
            int exitRow = random.nextInt(worldHeight);
            int exitCol = random.nextInt(worldWidth);
            world[exitRow][exitCol].setOccupied(false);
            world[exitRow][exitCol].setOccupationType(Cell.Type.EXIT);
        }
    }

    private void placePlayer() {
        java.util.Random random = new java.util.Random();
        boolean playerPlaced = false;

        // Try to place player on a non-wall cell
        while (!playerPlaced) {
            int row = random.nextInt(worldHeight);
            int col = random.nextInt(worldWidth);

            if (world[row][col].getOccupationType() != Cell.Type.WALL) {
                player.setPosition(row, col);
                playerPlaced = true;
            }
        }
    }

    // Methods
    public void printWorld() {
        // Clear the console to make it appear as if the world is printed over the previous one
        clearConsole();
        
        for (int i = 0; i < this.worldHeight; i++) {
            for (int j = 0; j < this.worldWidth; j++) {
                // Check if player is at this position
                if (i == player.getRow() && j == player.getCol()) {
                    System.out.print("@ "); // Player symbol
                } else {
                    Cell.Type cellType = world[i][j].getOccupationType();

                    switch (cellType) {
                        case WALL -> System.out.print("# "); // Block character for wall
                        case COIN -> System.out.print("0 "); // Circle for coin
                        case EXIT -> System.out.print("E "); // E for exit
                        case PATH -> System.out.print("_ "); // Dot for empty space
                        case ENTITY -> System.out.print("@ "); // @ for entity
                        default -> System.out.print("? "); // Unknown type
                    }
                }
            }
            System.out.println();
        }
    }
    
    // Method to clear the console using ANSI escape codes (works on most modern terminals)
    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (java.io.IOException | InterruptedException e) {
            // fallback or silent
        }
    }

    public void printGameState() {
        System.out.println("Score: " + player.getScore());
        System.out.println("Use W/A/S/D to move (W=Up, A=Left, S=Down, D=Right)");
        System.out.println("Press Q to quit");
        System.out.println();
    }

    public void startGame() {
        try (input) {
            gameRunning = true;
            System.out.println("Welcome to the Maze Game!");
            System.out.println("Find the exit (E) while collecting coins (0)!");
            System.out.println();
            
            while (gameRunning) {
                printWorld();
                printGameState();
                
                String inputString = input.nextLine().toLowerCase();
                
                if (inputString.equals("q")) {
                    System.out.println("Game ended by player.");
                    break;
                }
                
                processInput(inputString);
                
                // Check win condition
                if (checkWinCondition()) {
                    endGame(true);
                    break;
                }
            }
        }
    }

    private void processInput(String inputString) {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        boolean moved = false;

        try {
            moved = player.move(inputString.charAt(0));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input! Use W/A/S/D to move or Q to quit.");
        }

        if (moved) {
            // Check boundaries
            if (player.getRow() >= worldHeight) {
                player.setPosition(worldHeight - 1, player.getCol());
            }
            if (player.getCol() >= worldWidth) {
                player.setPosition(player.getRow(), worldWidth - 1);
            }

            // Check what's at the new position
            Cell.Type cellType = world[player.getRow()][player.getCol()].getOccupationType();

            if (cellType == Cell.Type.WALL) {
                // Can't move into wall, revert position
                player.setPosition(oldRow, oldCol);
                System.out.println("You hit a wall!");
            } else if (cellType == Cell.Type.COIN) {
                // Collect coin
                player.addScore(10);
                world[player.getRow()][player.getCol()].setOccupationType(Cell.Type.PATH);
                System.out.println("You collected a coin! +10 points");
            }
        }
    }

    private boolean checkWinCondition() {
        Cell.Type cellType = world[player.getRow()][player.getCol()].getOccupationType();
        return cellType == Cell.Type.EXIT;
    }

    private void endGame(boolean won) {
        gameRunning = false;
        System.out.println();
        System.out.println("=== GAME OVER ===");
        if (won) {
            System.out.println("Congratulations! You found the exit!");
            player.setWon(true);
        } else {
            System.out.println("Better luck next time!");
        }
        System.out.println("Final Score: " + player.getScore());
    }
}
