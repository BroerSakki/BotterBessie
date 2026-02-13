package com.utils;

public class Path {
    
    // Properties
    private char icon;
    private String style;

    // Constructors
    public Path() {
        this('_', Ansi.BG_GREEN);
    }
    public Path(char icon, String style) {
        setIcon(icon);
        setStyle(style);
    }

    // Accessors
    public final void setIcon(char icon) {
        this.icon = icon;
    }
    public final void setStyle(String style) {
        this.style = style;
    }

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
