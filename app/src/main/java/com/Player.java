package com;

public class Player extends Entity {

    // Properties
    private int score;
    private boolean hasWon;

    // Constructors
    public Player() {
        this.row = 0;
        this.col = 0;
        this.score = 0;
        this.hasWon = false;
    }

    // Accessors}
    public void setWon(boolean won) {
        this.hasWon = won;
    }
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
    public boolean hasWon() {
        return hasWon;
    } 

    // Methods
    public boolean move(char direction) {
        switch (direction) {
            case 'w' -> {
                return move(1);
            }
            case 'a' -> {
                return move(4);
            }
            case 's' -> {
                return move(3);
            }
            case 'd' -> {
                return move(2);
            }
            default -> {
                throw new IllegalArgumentException("Invalid direction: '" + direction + "'. Valid directions are 'w', 'a', 's', or 'd'.");
            }
        }
    }
}
