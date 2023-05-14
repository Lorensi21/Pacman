import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
public class Drawing extends JPanel{
    private Game pacworld;

    public Drawing(Game pacworld){
        this.pacworld=pacworld;
    }

    public void paint (Graphics g){
        // Map
        for (int x = 0; x < pacworld.getHeight(); x++) {
            for (int y = 0; y < pacworld.getWidth(); y++) {
                char c= pacworld.getElement(x, y);
                switch(c){
                    case '#':{
                        g.setColor(Color.BLACK);
                        g.fillRect(y*40, x*40, 40, 40);break;}
                    case '.':
                        g.setColor(Color.RED);
                        g.fillOval(y*40+40/3, x*40+40/3, 40/4, 40/4); break;
                    default: System.out.println(c);
                }
            }
        }


        //Pacman
        if(pacworld.pacdir==pacworld.RIGHT || pacworld.pacdir==pacworld.NOMOVEMENT){
            g.setColor(Color.orange);
            pacworld.tick();
            g.fillArc((int)pacworld.pacx*40, (int)pacworld.pacy*40, 40, 40, 30, 300);}
        else if(pacworld.pacdir==pacworld.LEFT) {
            g.setColor(Color.orange);
            pacworld.tick();
            g.fillArc((int)pacworld.pacx*40, (int)pacworld.pacy*40, 40, 40, 200, 300);}
        else if(pacworld.pacdir==pacworld.UP){
            g.setColor(Color.orange);
            pacworld.tick();
            g.fillArc((int)pacworld.pacx*40, (int)pacworld.pacy*40, 40, 40, 110, 300);}
        else if(pacworld.pacdir==pacworld.DOWN){
            g.setColor(Color.orange);
            pacworld.tick();
            System.out.println(pacworld.pacy);
            g.fillArc((int)pacworld.pacx*40, (int)pacworld.pacy*40, 40, 40, 290, 300);}
        //Draw score message
        if(Game.gameState==true)
            g.drawString("Your score: "+ String.valueOf((int)pacworld.score-1), pacworld.getWidth()/2*(40-5), 20);
        else{
            g.drawString("You have been eaten!!! Your total score is: "+ String.valueOf((int)pacworld.score-1), pacworld.getWidth()/2*(40-15), 15);
        }
        //Ghosts
        for (Ghost ghost: pacworld.ghosts){
            g.setColor(ghost.color);
            g.fillOval((int)(ghost.x*40), (int)(ghost.y*40), 40, 40);
            g.fillRect((int)(ghost.x*40), (int)(ghost.y*40) +40/2, 40, 40/2);
            g.setColor(Color.WHITE);
            g.fillOval((int)(ghost.x*40)+40/4, (int)(ghost.y*40)+40/4, 40/6, 40/6);
            g.fillOval((int)(ghost.x*40)+40/2, (int)(ghost.y*40)+40/4, 40/6, 40/6);
        }

        // Hearts

        int heartSize = 20;
        int heartSpacing = 5;
        int margin = 10;

        g.setColor(Color.RED);
        for (int i = 0; i < pacworld.getLives(); i++) {
            int heartX = margin + i * (heartSize + heartSpacing);
            int heartY = margin;

            // Draw heart shape
            g.fillArc(heartX, heartY, heartSize / 2, heartSize / 2, 0, 180);
            g.fillArc(heartX + heartSize / 2, heartY, heartSize / 2, heartSize / 2, 0, 180);
            g.fillRect(heartX, heartY + heartSize / 4, heartSize, heartSize / 2);
        }
    }

}
