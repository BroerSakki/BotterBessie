package com.utils;

public class Wall extends Node {

    // Constructors
    public Wall() {
        this('#', Ansi.WHITE);
    }
    public Wall(char icon, String style) {
        super(icon, style);
    }
}
