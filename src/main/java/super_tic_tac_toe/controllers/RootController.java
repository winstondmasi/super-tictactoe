package super_tic_tac_toe.controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import super_tic_tac_toe.models.*;

public class RootController extends JPanel {
    
    private Board gameBoard;
    private Player currentPlayer;
    private int targetBoardRow = -1;  // Initialized to -1 to allow the first player to play anywhere
    private int targetBoardCol = -1;


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
        // Check if the move is allowed
        if (targetBoardRow != -1 && targetBoardCol != -1 && (boardRow != targetBoardRow || boardCol != targetBoardCol)) {
            return;  // Ignore this move and wait for a valid move
        }
        
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

        // Update the target board for the next player
        targetBoardRow = squareRow;
        targetBoardCol = squareCol;
        enableTargetSmallBoard();
    }
    
    private void enableTargetSmallBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel smallBoardPanel = (JPanel) this.getComponent(i * 3 + j);
                if (i == targetBoardRow && j == targetBoardCol) {
                    smallBoardPanel.setEnabled(true);
                } else {
                    smallBoardPanel.setEnabled(false);
                }
            }
        }

        showLockedBoards();
    }

    private void indicateSmallBoardWin(int boardRow, int boardCol, Player player) {
        JPanel smallBoardPanel = (JPanel) this.getComponent(boardRow * 3 + boardCol);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(smallBoardPanel.getSize());
        
        // Transparent panel
        JPanel overlay = new JPanel();
        overlay.setBackground(new Color(255, 255, 255, 120)); // Last parameter is alpha for transparency
        overlay.setBounds(0, 0, smallBoardPanel.getWidth(), smallBoardPanel.getHeight());
        
        // Winner label
        JLabel winnerLabel = new JLabel(player.getSymbol() + " Wins");
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winnerLabel.setBounds(0, 0, smallBoardPanel.getWidth(), smallBoardPanel.getHeight());
        
        layeredPane.add(overlay, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(winnerLabel, JLayeredPane.PALETTE_LAYER);
        
        smallBoardPanel.add(layeredPane);
        smallBoardPanel.revalidate();
        smallBoardPanel.repaint();
    }
    
    

    private void displayGameOver(char winner) {
        String message = (winner == ' ') ? "It's a draw!" : "Player " + winner + " wins!";
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showLockedBoards() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != targetBoardRow || j != targetBoardCol) {
                    indicateLockedBoard(i, j);
                }
            }
        }
    }
    
    private void indicateLockedBoard(int boardRow, int boardCol) {
        JPanel smallBoardPanel = (JPanel) this.getComponent(boardRow * 3 + boardCol);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(smallBoardPanel.getSize());
        
        // Transparent panel
        JPanel overlay = new JPanel();
        overlay.setBackground(new Color(255, 255, 255, 150)); // More opaque for locked boards
        overlay.setBounds(0, 0, smallBoardPanel.getWidth(), smallBoardPanel.getHeight());
        
        // Locked label
        JLabel lockedLabel = new JLabel("Locked");
        lockedLabel.setHorizontalAlignment(JLabel.CENTER);
        lockedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        lockedLabel.setBounds(0, 0, smallBoardPanel.getWidth(), smallBoardPanel.getHeight());
        
        layeredPane.add(overlay, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(lockedLabel, JLayeredPane.PALETTE_LAYER);
        
        smallBoardPanel.add(layeredPane);
        smallBoardPanel.revalidate();
        smallBoardPanel.repaint();
    }
    
    
    
    
}
