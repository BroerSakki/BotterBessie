package com;

public class Player extends Entity{

    //private String move;
    private int health ;
    private int stamina;
    private String dash;

    //Konstructers
    public Player()
    {
        this(0,0,"");
    }

    public Player(int health,int stamina,String dash)
    {
        setHealth(health);
        setStamina(stamina);
        setDash(dash);

    }
    
    //Accessors
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

    //Dash
    public void dash(int speed, int direction)
    {
        if(stamina >= 2)
        {
            stamina = 0;

            for (int i = 0; i < speed; i++)
            {
                int[] dash2 = move(direction);
                setX(dash2[getX()]);
                setY(dash2[getY()]);

                if (occupied = false)
                {
                    int[] dash2 = move(direction);
                    setX(dash2[getX()]);
                    setY(dash2[getY()]);
                }
            }


        }
        
        
    }

    
}
