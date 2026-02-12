package com;

public class WorldTest {

    public static void main(String[] args) {
        // Create a test world
        GameWorld world = new GameWorld();

        // Create a 10x10 world with random walls and coins
        world.createWorld(10, 10);

        // Print the generated world
        System.out.println("Generated World (10x10):");
        System.out.println("█ = Wall, · = Empty space");
        System.out.println();
        world.printWorld();

        System.out.println();
        System.out.println("World dimensions: " + world.getWorldHeight() + "x" + world.getWorldWidth());
    }
}
