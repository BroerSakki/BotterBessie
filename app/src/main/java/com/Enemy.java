package com;

import com.utils.Ansi;

public class Enemy extends Entity {

    // Constructors
    public Enemy() {
        this('!', Ansi.RED);
    }
    public Enemy(char icon, String style) {
        super(icon, style);
    }
}
