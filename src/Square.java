public class Square{

    private char value; // 'X', 'O', ' '

    public Square(){
        this.value = ' '
    }

    // getter
    public char getVlue() {
        return value;
    }

    // setter
    public void setValue(char value){
        this.value = value
    }

    public boolean IsSquareEmpty(){
        return this.value == ' ';
    }

}