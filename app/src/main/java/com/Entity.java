public class Entity {
    
    // Properties
    public int x;
    public int y;

    // Constructors
    public Entity() {
        
    }

    // Accessors
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    // Methods
    public int[] move(int direction) {
        //Local Variables
        int[] cordsCurrent = new int[]{getX(), getY()};
        int[] cordsNew = cordsCurrent;

        switch (direction) {
            case 1:
                cordsNew[1]++;
                break;
            case 2:
                cordsNew[0]++;
                break;
            case 3:
                cordsNew[1]--;
            case 4:
                cordsNew[0]--;
            default:
                throw new Error("Invalid Direction");
        }

        return cordsNew;
    }
}
