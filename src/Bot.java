import java.util.Random;

public class Bot extends Player {
    public Bot(char symbol, String name) {
        super(symbol, name);
    }

    @Override
    public int[] makeTurn(Field field, View view) {
        Random random = new Random();
        return new int[]{random.nextInt(field.getData()[0].length),random.nextInt(field.getData().length)};
    }
}
