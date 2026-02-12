package com;

public class GameWorld {

    // Properties
    private int worldHeight;
    private int worldWidth;

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

    // Methods
    public void printWorld() {
        for (int i = 0; i < this.worldHeight; i++) {
            for (int j = 0; i < this.worldWidth; j++) {
                // Print current coords, maybe use a function to determine if there is a wall or whatever, maybe Cell
                //System.out.print();
            }
            System.out.println();
        }
    }
}
