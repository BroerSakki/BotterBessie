package com;

public class Main {

    public static void main(String[] args) {
        // Create a new game world
        GameWorld game = new GameWorld();

        // Create a 10x10 world
        game.createWorld(10, 10);

        // Start the game
        game.startGame();
    }
}
