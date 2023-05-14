import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class Map extends JFrame{
    Game world= new Game();
    Drawing pac = new Drawing(world);
    public List<HighScore> highScores = new ArrayList<>();
    public Map(){
        super("Pacman");
        setBackground(Color.WHITE);
        setSize(world.getWidth()*40, world.getHeight()*40);
        add(pac);
        addKeyListener(new Movement(world));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void goToMainMenu() {
        Menu menu = new Menu();
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
                if (score < 0 || score > 270) {
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
        showHighScores();
    }

    private void saveHighScores() {
        try {
            // Serialize the high scores list and save it to a file
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
            // Read the serialized high scores file
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
        JTextArea highScoresArea = new JTextArea();
        highScoresArea.setEditable(false);

        // Add each high score to the text area
        for (HighScore entry : highScores) {
            highScoresArea.append(entry.getPlayerName() + ": " + entry.getScore() + "\n");
        }

        // Create a scroll pane to contain the text area
        JScrollPane scrollPane = new JScrollPane(highScoresArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        // Display the high scores in a separate window
        JFrame highScoresFrame = new JFrame("High Scores");
        highScoresFrame.getContentPane().add(scrollPane);
        highScoresFrame.pack();
        highScoresFrame.setLocationRelativeTo(null); // Center the window on the screen
        highScoresFrame.setVisible(true);
//        // Load the high scores from the serialized file
//        loadHighScores();
//
//        // Create a string representation of the high scores
//        StringBuilder sb = new StringBuilder();
//        for (HighScore entry : highScores) {
//            sb.append(entry.getPlayerName()).append(": ").append(entry.getScore()).append("\n");
//        }
//
//        // Show the high scores in a dialog box
//        JOptionPane.showMessageDialog(null, sb.toString(), "Your score", JOptionPane.INFORMATION_MESSAGE);
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
            goToMainMenu();
        });

        gameThread.start();
    }

}

