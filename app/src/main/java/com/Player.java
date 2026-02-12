package com;

public class Player extends Entity {

    private int health ;
    private int stamina;
    private String dash;

    public Player()
    {
        this(0,0,"");
    }

    public Player(int health,int stamina,String dash)
    {
        setHealth(health);
        setDash(dash);
        setStamina(stamina);
    }

    
    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getHealth() 
    {
        return health;
    }

    public void setStamina(int stamina)
    {
        this.stamina = stamina;
    }

    public int getStamina() 
    {
        return stamina;
    }

    public void setDash(String dash)
    {
        this.dash = dash;
    }

    public String getDash() 
    {
        return dash;
    }
    
    public void dash(int direction)
    {
        if (stamina >= 2)
        {
            stamina -= 2; // Consume stamina for dash
            
            // Move twice in the specified direction for dash effect
            int[] newCoords = move(direction);
            setX(newCoords[0]);
            setY(newCoords[1]);
            
            // Move again for the dash distance
            newCoords = move(direction);
            setX(newCoords[0]);
            setY(newCoords[1]);
        }
    }
 }