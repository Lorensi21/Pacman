import java.awt.Color;
public class Ghost {
    public float x,y;
   public Color color;
   public int direction;

    public Ghost(float x, float y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void direction(Game world){
        float dx = world.pacx-x;
        float dy = world.pacy-y;

        if (Math.abs(dx) > Math.abs(dy))
            if(dx>0)
                direction=world.RIGHT;
            else
                direction=world.LEFT;
        else{
            if(dy>0)
                direction=world.DOWN;
            else
                direction=world.UP;
        }
    }
}