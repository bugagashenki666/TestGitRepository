import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;

    public Player()
    {

    }

    public Player(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public int[]makeTurn(Field field, View view)
    {
        return null;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
