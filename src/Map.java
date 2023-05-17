import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class Map extends JFrame implements Serializable {
    Game game = new Game();
    Drawing pac = new Drawing(game);
    public List<HighScore> highScores = new ArrayList<>();
    public Map(){
        super("Pacman");
        pac.setBackground(Color.BLACK);
        setSize(game.getWidth()*40, game.getHeight()*40);
        add(pac);
        addKeyListener(new Movement(game));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private void goToMainMenu() {
        Menu menu = new Menu(this);
        menu.setVisible(true);
        dispose();
    }
    private void setupKeyboardShortcut() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_RELEASED && e.isMetaDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                goToMainMenu();
            }
            return false;
        });
    }

    private void endGame() {
        // Prompt the player for their name and score
        String playerName = JOptionPane.showInputDialog(null, "Enter your name:", "Game Over", JOptionPane.PLAIN_MESSAGE);
        int score;
        while (true) {
            try {
                score = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your score:", "Game Over", JOptionPane.PLAIN_MESSAGE));
                if (score < 0 || score > 214) {
                    throw new IllegalArgumentException("Invalid score. Please enter a score between 0 and 270.");
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid score.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

                HighScore newHighScore = new HighScore(playerName, score);
        highScores.add(newHighScore);
        saveHighScores();
        goToMainMenu();
    }

    private void saveHighScores() {
        try {
            FileOutputStream fileOut = new FileOutputStream("highscores.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(highScores);
            objectOut.close();
            fileOut.close();
            System.out.println("High scores saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving high scores: " + e.getMessage());
        }
    }

    private void loadHighScores() {
        try {
            FileInputStream fileIn = new FileInputStream("highscores.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            highScores = (List<HighScore>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("High scores loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading high scores: " + e.getMessage());
        }
    }

    public void showHighScores() {
        loadHighScores();

        DefaultListModel<String> model = new DefaultListModel<>();

        for (HighScore entry : highScores) {
            model.addElement(entry.getPlayerName() + ": " + entry.getScore());
        }

        JList<String> highScoresList = new JList<>(model);
        highScoresList.setBackground(Color.BLACK);
        highScoresList.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(highScoresList);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        JFrame highScoresFrame = new JFrame("High Scores");
        highScoresFrame.getContentPane().add(scrollPane);
        highScoresFrame.pack();
        highScoresFrame.setLocationRelativeTo(null);
        highScoresFrame.setVisible(true);
    }

    public void start() {

        Thread gameThread = new Thread(() -> {
            setupKeyboardShortcut();
            Game.gameState = true;
            while (Game.gameState) {
                pac.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            endGame();
        });
        game.resetGame();
        gameThread.start();
    }

}

