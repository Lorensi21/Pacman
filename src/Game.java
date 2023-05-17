import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
public class Game {
public static String map =
        "####################/n" +
                "#..................#/n" +
                "#.#.#.###.#.###.##..#/n" +
                "#.#...#...#...#...#.#/n" +
                "#.###.#.#..##.#.##..#/n" +
                "#..................#/n" +
                "#.###.###.###.##..###/n" +
                "#.#...#...#...#.....#/n" +
                "#.#.#.##.##.###.###.#/n" +
                "#.#.#.......#.....#.#/n" +
                "#...#.......#.....#.#/n" +
                "###.###.#####.#####.#/n" +
                "#...................#/n" +
                "########.#.######.###/n" +
                "#...................#/n" +
                "#.#####.#####.######/n" +
                "#...................#/n" +
                "#.########.#####.###/n" +
                "#...................#/n" +
                "####################";

    public static int score = 0;
    public static boolean gameState = true;
    private ArrayList<ArrayList<Character>> board = new ArrayList<>();
    public ArrayList<Ghost> ghosts = new ArrayList<>();
    public static int lives = 3;
    public float pacx = 9, pacy = 12;
    private final float startingPacX = 9;
    private final float startingPacY = 12;


    public static int NOMOVEMENT = 0;
    public static int UP = 1, DOWN = 2;
    public static int LEFT = 3, RIGHT = 4;

    public int pacdir = NOMOVEMENT;

    public Game() {
        for (String s : map.split("/n")) {
            ArrayList<Character> row = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                row.add(s.charAt(i));
            }
            board.add(row);
        }
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.cyan));
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.RED));
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.GREEN));
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.YELLOW));
    }

    public int getHeight() {
        return board.size();
    }

    public int getWidth() {
        return board.get(0).size();
    }

    public char getElement(int x, int y) {
        return board.get(x).get(y);
    }

    public void setElement(int x, int y, char c) {
        board.get(y).set(x, c);
    }
    public int getLives(){
        return lives;
    }
    public void resetGame() {
        score = 0;
        lives = 3;
        pacx = startingPacX;
        pacy = startingPacY;
        pacdir = NOMOVEMENT;

        // Reset the game board
        board.clear();
        for (String s : map.split("/n")) {
            ArrayList<Character> row = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                row.add(s.charAt(i));
            }
            board.add(row);
        }

        // Reset the ghosts
        ghosts.clear();
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.cyan));
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.RED));
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.GREEN));
        ghosts.add(new Ghost((int) (Math.random() * 18) + 1, (int) (Math.random() * 8) + 1, Color.YELLOW));
    }

    public void tick() {
        int x = (int) pacx;
        int y = (int) pacy;

        //wall collision
        if (getElement(y, x + 1) == '#' && pacdir == RIGHT || getElement(y, x - 1) == '#' && pacdir == LEFT
                || getElement(y + 1, x) == '#' && pacdir == DOWN || getElement(y - 1, x) == '#' && pacdir == UP)
            pacdir = NOMOVEMENT;

        //moving pac
        if (pacdir == RIGHT)
            pacx += .1;
        else if (pacdir == LEFT)
            pacx -= .1;
        else if (pacdir == UP)
            pacy -= .1;
        else if (pacdir == DOWN)
            pacy += .1;

        //getting scores
        if (getElement(y, x) == '.') {
            setElement(x, y, ' ');
            score = score + 1;
        }
       // moving ghosts
        for (Ghost ghost: ghosts){
            ghost.direction(this);

//            float ghostX = ghost.x;
//            float ghostY = ghost.y;
//
//            if (ghost.direction == LEFT && getElement((int) ghostY, (int) ghostX - 1) != '#')
//                ghost.x -= .07;
//            else if (ghost.direction == RIGHT && getElement((int) ghostY, (int) ghostX + 1) != '#')
//                ghost.x += .07;
//            else if (ghost.direction == UP && getElement((int) ghostY - 1, (int) ghostX) != '#')
//                ghost.y -= .07;
//            else if (ghost.direction == DOWN && getElement((int) ghostY + 1, (int) ghostX) != '#')
//                ghost.y += .07;
//            else {
//                continue;
//            }

            if(ghost.direction==LEFT)
                ghost.x-=.07;
            else if (ghost.direction==RIGHT)
                ghost.x+=.07;
            else if  (ghost.direction==UP)
                ghost.y-=.07;
            else if(ghost.direction==DOWN)
                ghost.y+=.07;

            checkCollisionWithPacman(ghost, x, y);
        }

//        for (Ghost ghost : ghosts) {
//            ghost.direction(this);
//            moveGhostRandomly(ghost);
//            checkCollisionWithPacman(ghost, x, y);
//        }
        System.out.println("Total score: " + score);
        }

    private void checkCollisionWithPacman(Ghost ghost, int pacX, int pacY) {
        int ghostX = (int) ghost.x;
        int ghostY = (int) ghost.y;

        if (ghostX == pacX && ghostY == pacY) {
            lives--;
            if (lives <= 0) {
                gameState = false;
            } else {

                pacx = startingPacX;
                pacy = startingPacY;
                pacdir = NOMOVEMENT;
            }
        }
    }


//        private void moveGhostRandomly(Ghost ghost) {
//        int direction = getRandomDirection();
//
//        double currentCellX = ghost.x;
//        double currentCellY = ghost.y;
//
//        double newCellX = currentCellX;
//        double newCellY = currentCellY;
//
//        if (direction == LEFT)
//            newCellX -= .07;
//        else if (direction == RIGHT)
//            newCellX += .07;
//        else if (direction == UP)
//            newCellY -= .07;
//        else if (direction == DOWN)
//            newCellY += .07;
//
//        // Check if the new position is valid (not colliding with a wall)
//        if (!isCollidingWithWall(newCellX, newCellY)) {
//            ghost.x = (float) newCellX;
//            ghost.y = (float) newCellY;
//        }
//}
//    private int getRandomDirection() {
//        int[] directions = { LEFT, RIGHT, UP, DOWN };
//        int randomIndex = (int) (Math.random() * directions.length);
//        return directions[randomIndex];
//    }
//    private boolean isCollidingWithWall(double x, double y){
//        int row = (int) y;
//        int col = (int) x;
//
//        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
//            // Coordinates are outside the bounds of the map, considered as collision with wall
//            return true;
//        }
//
//        char element = getElement(row, col);
//        return element == '#';
//
//    }


}