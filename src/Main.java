import javax.swing.*;
public class Main extends JFrame{
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Menu menu = new Menu();
                menu.setVisible(true);
            });
    }}