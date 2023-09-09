package super_tic_tac_toe.controllers;

import javax.swing.*;
import java.awt.*;
import super_tic_tac_toe.models.*;

public class RootController extends JPanel {
    
    private Board gameBoard;

    public RootController() {
        gameBoard = new Board();
        setLayout(new GridLayout(3, 3, 10, 10));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel smallBoard = createSmallBoard();
                add(smallBoard);
            }
        }
    }

    private JPanel createSmallBoard() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(50, 50));
                panel.add(btn);
            }
        }
        return panel;
    }
}
