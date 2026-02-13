package com;

import com.utils.Ansi;
import com.utils.Node;

public class Entity extends Node {
    
    // Properties
    private  int row;
    private int col;

    // Constructors
    public Entity() {
        this('@', Ansi.BLUE);
    }
    public Entity(char icon, String style) {
        super(icon, style);
        setRow(0);
        setCol(0);
    }

    // Accessors
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }

    public final void setRow(int row) {
        this.row = row;
    }
    public final void setCol(int col) {
        this.col = col;
    }
    public void setPosition(int row, int col) {
        setRow(row);
        setCol(col);
    }

    // Methods
    public boolean move(int direction) {
        //Local variables
        boolean validMove = false;

        switch (direction) {
            case 1 -> {
                if (row > 0) {
                    this.row--;
                    validMove = true;
                }
            }
            case 2 -> {
                this.col++;
                validMove = true;
            }
            case 3 -> {
                this.row++;
                validMove = true;
            }
            case 4 -> {
                if (col > 0) {
                    this.col--;
                    validMove = true;
                }
            }
            default -> throw new IllegalArgumentException("Invalid direction: " + direction + ". Valid directions are 1-4.");
        }

        return validMove;
    }
}
