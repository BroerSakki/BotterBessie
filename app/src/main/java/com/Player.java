package com;

import com.utils.Ansi;

public class Player extends Entity {

    // Properties
    private int score;
    private boolean hasWon;

    // Constructors
    public Player() {
        this('@', Ansi.BLUE);
    }
    public Player(char icon, String style) {
        super(icon, style);
        this.score = 0;
        this.hasWon = false;
    }

    // Accessors}
    public void setWon(boolean won) {
        this.hasWon = won;
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

    // Optimized move method with boundary checking
    public boolean move(char direction, int maxRow, int maxCol) {
        switch (direction) {
            case 'w' -> {
                return move(1, maxRow, maxCol);
            }
            case 'a' -> {
                return move(4, maxRow, maxCol);
            }
            case 's' -> {
                return move(3, maxRow, maxCol);
            }
            case 'd' -> {
                return move(2, maxRow, maxCol);
            }
            default -> {
                throw new IllegalArgumentException("Invalid direction: '" + direction + "'. Valid directions are 'w', 'a', 's', or 'd'.");
            }
        }
    }
}
