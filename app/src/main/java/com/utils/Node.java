package com.utils;

public class Node {
    
    // Properties
    private final  char icon;
    private final String style;

    // Constructors
    public Node(char icon, String style) {
        this.icon = icon;
        this.style = style;
    }

    // Accessors
    public char getIcon() {
        return icon;
    }
    public String getStyle() {
        return style;
    }

    // Methods
    @Override
    public String toString() {
        return Ansi.format(style, icon) + " ";
    }
}
