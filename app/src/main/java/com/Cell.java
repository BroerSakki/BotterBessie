package com;

public class Cell {

    public enum Type {
        PATH,
        WALL,
        COIN,
        EXIT,
        ENTITY
    }

    // Properties
    private boolean occupied;

    private Type occupationType;

    // Constructor
    public Cell() {
        this.occupied = false;
        this.occupationType = Type.PATH;
    }

    // Accessors
    public boolean getOccupied() {
        return this.occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Type getOccupationType() {
        return this.occupationType;
    }

    public void setOccupationType(Type type) {
        this.occupationType = type;
    }

    // Methods
}
