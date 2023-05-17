import javax.swing.*;
public class Main extends JFrame{
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Map map = new Map();
                map.setVisible(false);
                Menu menu = new Menu(map);
                menu.setVisible(true);
            });
    }}