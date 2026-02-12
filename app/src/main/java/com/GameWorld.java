package com;

import java.util.Scanner;

public class GameWorld {

    // Properties
    private int worldHeight;
    private int worldWidth;
    private Cell[][] world;
    private Player player;
    private Scanner scanner;
    private boolean gameRunning;

    // Contructor
    public GameWorld() {
        this.worldHeight = 0;
        this.worldWidth = 0;
        this.player = new Player();
        this.scanner = new Scanner(System.in);
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
        for (int i = 0; i < this.worldHeight; i++) {
            for (int j = 0; j < this.worldWidth; j++) {
                // Check if player is at this position
                if (i == player.getRow() && j == player.getCol()) {
                    System.out.print("@ "); // Player symbol
                } else {
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
            }
            System.out.println();
        }
    }

    public void printGameState() {
        System.out.println("Score: " + player.getScore());
        System.out.println("Use W/A/S/D to move (W=Up, A=Left, S=Down, D=Right)");
        System.out.println("Press Q to quit");
        System.out.println();
    }

    public void startGame() {
        gameRunning = true;
        System.out.println("Welcome to the Maze Game!");
        System.out.println("Find the exit (E) while collecting coins (0)!");
        System.out.println();

        while (gameRunning) {
            printWorld();
            printGameState();

            String input = scanner.nextLine().toLowerCase();

            if (input.equals("q")) {
                System.out.println("Game ended by player.");
                break;
            }

            processInput(input);

            // Check win condition
            if (checkWinCondition()) {
                endGame(true);
                break;
            }
        }

        scanner.close();
    }

    private void processInput(String input) {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        boolean moved = false;

        switch (input) {
            case "w":
                if (player.moveUp()) {
                    moved = true;
                }
                break;
            case "s":
                if (player.moveDown()) {
                    moved = true;
                }
                break;
            case "a":
                if (player.moveLeft()) {
                    moved = true;
                }
                break;
            case "d":
                if (player.moveRight()) {
                    moved = true;
                }
                break;
            default:
                System.out.println("Invalid input! Use W/A/S/D to move or Q to quit.");
                return;
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
