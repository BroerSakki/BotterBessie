package com;

public class GameWorld {

    // Properties
    private int worldHeight;
    private int worldWidth;
    private Cell[][] world;

    // Contructor
    public GameWorld() {
        this.worldHeight = 0;
        this.worldWidth = 0;
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

    // Methods
    public void printWorld() {
        for (int i = 0; i < this.worldHeight; i++) {
            for (int j = 0; j < this.worldWidth; j++) {
                Cell.Type cellType = world[i][j].getOccupationType();

                switch (cellType) {
                    case WALL:
                        System.out.print("# "); // Block character for wall
                        break;
                    case COIN:
                        System.out.print("0 "); // Circle for coin
                        break;
                    case EXIT:
                        System.out.print("E "); // E for exit
                        break;
                    case PATH:
                        System.out.print("_ "); // Dot for empty space
                        break;
                    case ENTITY:
                        System.out.print("@ "); // @ for entity
                        break;
                    default:
                        System.out.print("? "); // Unknown type
                        break;
                }
            }
            System.out.println();
        }
    }
}
