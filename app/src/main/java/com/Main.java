package com;

public class Main {
    public static void main(String[] args) {
        // Create a test world
        GameWorld world = new GameWorld();

        // Create a 10x10 world with random walls, coins, and one exit
        world.createWorld(10, 10);

        // Print the generated world
        System.out.println("Generated World (10x10):");
        System.out.println("# = Wall, 0 = Coin, E = Exit, _ = Path");
        System.out.println();
        world.printWorld();

        System.out.println();
        System.out.println("World dimensions: " + world.getWorldHeight() + "x" + world.getWorldWidth());

        // Test multiple worlds to show randomness
        System.out.println("\n--- Testing multiple world generations ---");
        for (int i = 0; i < 3; i++) {
            System.out.println("\nWorld " + (i + 1) + ":");
            GameWorld testWorld = new GameWorld();
            testWorld.createWorld(8, 8);
            testWorld.printWorld();
        }
    }
}
