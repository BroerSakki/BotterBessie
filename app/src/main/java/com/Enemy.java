package com;

public class Enemy {

    // Properties
    public int x;
    public int y;

    // Accessors
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Methods
    public int move(GameWorld world) {
        System.out.print("Walking");
        return 0;
    }
}
