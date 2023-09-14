package super_tic_tac_toe.models;

public class Board{

    private SmallBoard[][] boards;
    private char winner;
    

    public Board(){
        this.boards = new SmallBoard[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.boards[i][j] = new SmallBoard();
            }
        }

        this.winner = ' ';
    }

    public SmallBoard getSmallBoard(int row, int col){
        return boards[row][col];
    }

    public char getWinner(){
        return winner;
    }

    public boolean checkForWinner(int row, int col) {
        char currentPlayer = boards[row][col].getWinner();

        // Check the current row
        if (boards[row][0].getWinner() == currentPlayer && boards[row][1].getWinner() == currentPlayer && boards[row][2].getWinner() == currentPlayer) {
            return true;
        }

        // Check the current column
        if (boards[0][col].getWinner() == currentPlayer && boards[1][col].getWinner() == currentPlayer && boards[2][col].getWinner() == currentPlayer) {
            return true;
        }

        // Check diagonals
        if (row == col && boards[0][0].getWinner() == currentPlayer && boards[1][1].getWinner() == currentPlayer && boards[2][2].getWinner() == currentPlayer) {
            return true;
        }

        if (row + col == 2 && boards[0][2].getWinner() == currentPlayer && boards[1][1].getWinner() == currentPlayer && boards[2][0].getWinner() == currentPlayer) {
            return true;
        }

        return false;
    }

    

    public boolean markSquare(int boardRow, int boardCol, int squareRow, int squareCol, Player player) {
        boolean isWin = boards[boardRow][boardCol].markSquare(squareRow, squareCol, player);

        if(isWin){
            if (checkForWinner(boardRow, boardCol)){
                this.winner = player.getSymbol();
                return true;
            }
        }
        return false;
    }

    // Utility function to check if a SmallBoard is full
    public boolean isSmallBoardFull(int row, int col) {
        return boards[row][col].isFull();
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!boards[i][j].isFull()) {
                    return false;
                }
            }
        }
        return true;
    }
    
}