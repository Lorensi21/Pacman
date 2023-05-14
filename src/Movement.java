import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Movement implements KeyListener{

    private Game move;

    public Movement(Game move){
        this.move=move;
    }
    @Override
    public void keyPressed(KeyEvent k) {
        int code=k.getKeyCode();

        if (code==KeyEvent.VK_LEFT){
            move.pacdir=move.LEFT;}
        else if(code==KeyEvent.VK_RIGHT)
            move.pacdir=move.RIGHT;
        else if(code==KeyEvent.VK_UP)
            move.pacdir=move.UP;
        else if(code==KeyEvent.VK_DOWN)
            move.pacdir=move.DOWN;


    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}

}