package super_tic_tac_toe.models;

public class Square{

    private char value; // 'X', 'O', ' '

    public Square(){
        this.value = ' ';
    }

    // getter
    public char getValue() {
        return value;
    }

    // setter
    public void setValue(char value){
        this.value = value;
    }

    public boolean IsSquareEmpty(){
        return this.value == ' ';
    }

}