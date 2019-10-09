public class HumanPlayer extends Player {

    public HumanPlayer(char symbol, String name) {
        super(symbol, name);
    }

    @Override
    public int[] makeTurn(Field field, View view) {
        try{
            View.printMessage(this.getName() + "("+ this.getSymbol() + ") введите координаты: \n");
            return new int[]{Integer.parseInt(view.getString()),Integer.parseInt(view.getString())};
        }
        catch(Exception e)
        {
            View.printMessage("Введите 2 целых числа");
        }
        return null;
    }
}
