package super_tic_tac_toe.main;

import javax.swing.*;

import super_tic_tac_toe.controllers.RootController;

public class MainApp {
    
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Super Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        RootController gamePanel = new RootController();
        frame.add(gamePanel);
        
        frame.pack();
        frame.setVisible(true);
    });
}

}
