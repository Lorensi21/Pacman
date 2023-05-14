import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private Map gameWindow;
    Map map;

    public Menu(){

        setTitle("Main menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Pacman");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton game = new JButton("New Game");
        JButton score = new JButton("High Scores");
        JButton exit = new JButton("Exit");

        panel.add(titleLabel);
        panel.add(game);
        panel.add(score);
        panel.add(exit);

        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int size = promptBoardSize();
                //if (size != -1) {
                    //createGameBoard(size);
                //}
                gameWindow = new Map();
                gameWindow.start();

            }
        });


        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = new Map();
                map.showHighScores();
//                StringBuilder scoresBuilder = new StringBuilder();
//                Map map = new Map();
//                for (HighScore entry : map.highScores) {
//                    scoresBuilder.append(entry.getPlayerName()).append(": ").append(entry.getScore()).append("\n");
//                }
//                String highScoresString = scoresBuilder.toString();
//
//                // Set the text of the JTextArea to the high scores string
//                highScoresTextArea.setText(highScoresString);
//
//                // Show the high scores in a dialog window with scrollbars
//                JOptionPane.showMessageDialog(null, scrollPane, "High Scores", JOptionPane.PLAIN_MESSAGE);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        getContentPane().add(panel);
    }



//    private int promptBoardSize(){
//        String input = JOptionPane.showInputDialog(Menu.this, "Enter the size of the board (10-100):");
//        try {
//            int size = Integer.parseInt(input);
//            if (size < 10 || size > 100) {
//                throw new NumberFormatException();
//            }
//            return size;
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(Menu.this, "Invalid board size. Please enter a number between 10 and 100.");
//            return -1;
//        }
//    }

//    private void createGameBoard(int size){
//        JFrame gameBoardFrame = new JFrame("Game Board");
//        gameBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        gameBoardFrame.setSize(600, 600);
//        gameBoardFrame.setLocationRelativeTo(null);
//
//        // Create the game board table
//        JTable gameBoardTable = new JTable(new Main(size));
//        gameBoardTable.setRowHeight(20);
//        gameBoardTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        gameBoardTable.getColumnModel().getColumn(0).setPreferredWidth(20);
//
//        // Add the game board table to the frame
//        JScrollPane scrollPane = new JScrollPane(gameBoardTable);
//        scrollPane.setPreferredSize(gameBoardTable.getPreferredScrollableViewportSize());
//        gameBoardFrame.getContentPane().add(scrollPane);
//
//        gameBoardFrame.pack();
//        gameBoardFrame.setVisible(true);
//        int maxCellSize = 30; // Maximum cell size for readability
//        int minCellSize = 10; // Minimum cell size to fit within the window
//
//        int cellSize = Math.min(maxCellSize, (int) (500.0 / size)); // Adjust the division factor based on your preference
//
//        int width = size * cellSize + 50; // Adjust the additional width based on your preference
//        int height = size * cellSize + 100; // Adjust the additional height based on your preference
//
//        JFrame gameBoardFrame = new JFrame("Game Board");
//        gameBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        gameBoardFrame.setSize(width, height);
//        gameBoardFrame.setLocationRelativeTo(null);
//
//        // Create the game board table
//        JTable gameBoardTable = new JTable(new Main(size));
//        gameBoardTable.setRowHeight(cellSize);
//        gameBoardTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        gameBoardTable.setGridColor(Color.BLACK);
//
//        // Set column widths
//        for (int i = 0; i < size; i++) {
//            gameBoardTable.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
//        }
//
//        // Add the game board table to the frame
//        JScrollPane scrollPane = new JScrollPane(gameBoardTable);
//        gameBoardFrame.getContentPane().add(scrollPane);
//
//        gameBoardFrame.setVisible(true);
//    }

}