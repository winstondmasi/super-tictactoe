package super_tic_tac_toe.models;

public class Player{
    private char symbol; // 'X' or 'O'

    public Player(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol(){
        return symbol;
    }
}