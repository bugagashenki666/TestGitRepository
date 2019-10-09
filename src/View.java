import java.util.Scanner;

public class View {
    Scanner scanner;
    public View(java.io.InputStream stream)
    {
        this.scanner = new Scanner(stream);
    }
    public static void printMessage(String message)
    {
        System.out.print(message);
    }
    public String getString()
    {
        return this.scanner.next();
    }
    public int getFieldSize() throws Exception
    {
        return Integer.parseInt(this.scanner.next());
    }
    public int getCountToWin() throws Exception
    {
        return Integer.parseInt(this.scanner.next());
    }
    public String getNameOfPlayer1()
    {
        return this.scanner.next();
    }
    public String getNameOfPlayer2()
    {
        return this.scanner.next();
    }
    public void printField(Field field)
    {
        for(int i = 0 ; i < field.getData().length ; i ++)
        {
            for (int j = 0 ; j < field.getData()[i].length; j++)
            {
                View.printMessage(((field.getData()[i][j] == 0 || field.getData()[i][j] == Field.EMPTY)?Field.EMPTY:field.getData()[i][j]) + " ");
            }
            View.printMessage("\n");
        }
        View.printMessage("\n");
    }
}
