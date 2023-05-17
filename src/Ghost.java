import java.awt.Color;
import java.util.ArrayList;

public class Ghost {
    public float x,y;
   public Color color;
   public int direction;

    public Ghost(float x, float y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
//    public void direction(Game world) {
//        int currentX = (int) x;
//        int currentY = (int) y;
//
//        ArrayList<Integer> validDirections = new ArrayList<>();
//
//        if (world.getElement(currentY, currentX + 1) != '#')
//            validDirections.add(world.RIGHT);
//        if (world.getElement(currentY, currentX - 1) != '#')
//            validDirections.add(world.LEFT);
//        if (world.getElement(currentY + 1, currentX) != '#')
//            validDirections.add(world.DOWN);
//        if (world.getElement(currentY - 1, currentX) != '#')
//            validDirections.add(world.UP);
//
//        if (validDirections.size() > 0) {
//            int randomIndex = (int) (Math.random() * validDirections.size());
//            direction = validDirections.get(randomIndex);
//        }
//    }


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