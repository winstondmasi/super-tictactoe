package super_tic_tac_toe.controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import super_tic_tac_toe.models.*;

public class RootController extends JPanel {
    
    private Board gameBoard;
    private Player currentPlayer;

    public RootController() {
        gameBoard = new Board();
        currentPlayer = new Player('X'); // Start with player X
        setLayout(new GridLayout(3, 3, 10, 10));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel smallBoard = createSmallBoard(i, j);
                add(smallBoard);
            }
        }
    }

    private JPanel createSmallBoard(int boardRow, int boardCol) {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(50, 50));

                // Create effectively final copies of the loop variables
                int finalI = i;
                int finalJ = j;

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(boardRow, boardCol, finalI, finalJ, btn);
                    }
                });
                panel.add(btn);
            }
        }
        return panel;
    }

    private void handleButtonClick(int boardRow, int boardCol, int squareRow, int squareCol, JButton btn) {
        if (gameBoard.markSquare(boardRow, boardCol, squareRow, squareCol, currentPlayer)) {
            btn.setText(String.valueOf(currentPlayer.getSymbol()));
            // TODO: Check for game winner and handle game over
        }
        // Switch to the next player
        currentPlayer = (currentPlayer.getSymbol() == 'X') ? new Player('O') : new Player('X');
    }
}
