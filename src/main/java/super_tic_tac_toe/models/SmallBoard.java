package super_tic_tac_toe.models;

public class SmallBoard{
    private Square[][] squares;
    private char winner; // 'X' , 'O' or ' '

    public SmallBoard() {
        this.squares = new Square[3][3];

        for (int i = 0; i < 3; i++){
            for(int j = 0, j < 3; j++){
                this.squares[i][j] = new Square();
            }
        }

        this.winner = ' ';
    }

    public Square getSquare(int row, int col){
        return squares[row][col];
    }

    public char getWinner(){
        return winner;
    }

    private boolean CheckForWinner(int row, int col){
        char currentPlayer = squares[row][col].getValue();

         // Check the current row
        if (squares[row][0].getValue() == currentPlayer && squares[row][1].getValue() == currentPlayer && squares[row][2].getValue() == currentPlayer) {
            return true;
        }

        // Check the current column
        if (squares[0][col].getValue() == currentPlayer && squares[1][col].getValue() == currentPlayer && squares[2][col].getValue() == currentPlayer) {
            return true;
        }

        // Check diagonals
        if (row == col && squares[0][0].getValue() == currentPlayer && squares[1][1].getValue() == currentPlayer && squares[2][2].getValue() == currentPlayer) {
            return true;
        }

        if (row + col == 2 && squares[0][2].getValue() == currentPlayer && squares[1][1].getValue() == currentPlayer && squares[2][0].getValue() == currentPlayer) {
            return true;
        }

        return false;


    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (squares[i][j].IsSquareEmpty()){
                    return false;
                }
            }
        }
    }

    public boolean markSquare(int row, int col, Player player){
        if (squares[row][col].IsSquareEmpty()){
            squares[row][col].setValue(player.getSymbol());

            if (CheckForWinner(row, col)){
                this.winner = player.getSymbol();
                return true;
            }
        }

        return false;
    }
}