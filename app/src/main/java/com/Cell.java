package com;

import com.utils.Coin;
import com.utils.Goal;
import com.utils.Path;
import com.utils.Wall;

public class Cell {

    public enum Type {
        PATH(new Path().toString()),
        WALL(new Wall().toString()),
        COIN(new Coin().toString()),
        GOAL(new Goal().toString()),
        ENTITY(new Entity().toString());

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
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

    @Override
    public String toString() {
        return getOccupationType().toString();
    }
}
