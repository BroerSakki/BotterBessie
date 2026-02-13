package com.utils;

public class Goal extends Node {

    // Constructors
    public Goal() {
        this('E', Ansi.BG_BLUE);
    }
    public Goal(char icon, String style) {
        super(icon, style);
    }
}