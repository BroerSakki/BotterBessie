package com;

public class Player {

    private int row;
    private int col;
    private int score;
    private boolean hasWon;

    public Player() {
        this.row = 0;
        this.col = 0;
        this.score = 0;
        this.hasWon = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
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

    public void setWon(boolean won) {
        this.hasWon = won;
    }

    public boolean moveUp() {
        if (row > 0) {
            row--;
            return true;
        }
        return false;
    }

    public boolean moveDown() {
        row++;
        return true;
    }

    public boolean moveLeft() {
        if (col > 0) {
            col--;
            return true;
        }
        return false;
    }

    public boolean moveRight() {
        col++;
        return true;
    }
}
