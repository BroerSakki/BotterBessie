package com.utils;

public class Path extends Node {
    
    // Constructors
    public Path() {
        this('_', Ansi.GREEN);
    }
    public Path(char icon, String style) {
        super(icon, style);
    }
}
