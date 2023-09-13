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
        SmallBoard currentSmallBoard = gameBoard.getSmallBoard(boardRow, boardCol);
        if (currentSmallBoard.getSquare(squareRow, squareCol).IsSquareEmpty()) {
            boolean smallBoardWon = gameBoard.markSquare(boardRow, boardCol, squareRow, squareCol, currentPlayer);
            btn.setText(String.valueOf(currentPlayer.getSymbol()));
            btn.setEnabled(false); // Disable the button after it's clicked
            
            // Check if this SmallBoard has been won by the current move
            if (smallBoardWon) {
                indicateSmallBoardWin(boardRow, boardCol, currentPlayer);
            }
    
            // Check if the main game has been won or if it's a draw
            char gameWinner = gameBoard.getWinner();
            if (gameWinner != ' ') {
                // Display game-over message indicating the winner
                displayGameOver(gameWinner);
            } else if (gameBoard.isFull()) {
                // Display game-over message indicating a draw
                displayGameOver(' ');
            }
    
            // Switch to the next player
            currentPlayer = (currentPlayer.getSymbol() == 'X') ? new Player('O') : new Player('X');
        }
    }
    
    private void indicateSmallBoardWin(int boardRow, int boardCol, Player player) {
        JPanel smallBoardPanel = (JPanel) this.getComponent(boardRow * 3 + boardCol);
        smallBoardPanel.setBackground(player.getSymbol() == 'X' ? Color.RED : Color.BLUE);
        for (Component component : smallBoardPanel.getComponents()) {
            component.setEnabled(false); // Disable all buttons in the won SmallBoard
        }
    }

    private void displayGameOver(char winner) {
        String message = (winner == ' ') ? "It's a draw!" : "Player " + winner + " wins!";
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
}
