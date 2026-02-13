package com.utils;

public class Coin extends Node {

    // Constructors
    public Coin() {
        this('0', Ansi.YELLOW);
    }
    public Coin(char icon, String style) {
        super(icon, style);
    }
}
