package com;

public class GameWorld {

    // Properties
    private int worldHeight = 0;
    private int worldWidth = 0;
    private Cell[][] world;

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
    }

    private void initializeWorld() {
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < worldHeight; i++) {
            for (int j = 0; j < worldWidth; j++) {
                // Create a new Cell for each position
                world[i][j] = new Cell();

                // Randomly decide what to place in this cell
                // 30% chance for wall, 20% chance for coin, 50% chance for empty
                double randomValue = random.nextDouble();

                if (randomValue < 0.3) {
                    // Wall (occupied)
                    world[i][j].setOccupied(true);
                } else if (randomValue < 0.5) {
                    // Coin (not occupied, but has a coin)
                    world[i][j].setOccupied(false);
                    // You could add a coin property to Cell if needed
                } else {
                    // Empty space (not occupied)
                    world[i][j].setOccupied(false);
                }
            }
        }
    }

    // Methods
    public void printWorld() {
        for (int i = 0; i < this.worldHeight; i++) {
            for (int j = 0; j < this.worldWidth; j++) {
                if (world[i][j].getOccupied()) {
                    // Wall
                    System.out.print("█ "); // Block character for wall
                } else {
                    // Check if there's a coin (you could add a coin property to Cell)
                    // For now, just show empty space
                    System.out.print("· "); // Dot for empty space
                }
            }
            System.out.println();
        }
    }
}
