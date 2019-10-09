import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private View view;
    Player players[];
    Field field;



    public Controller()
    {
        this.view = new View(System.in);
        Random r = new Random();
        View.printMessage("Введите имя первого пользователя:\n");
        Player p1 = this.createPlayer(null,r.nextBoolean()?'X':'O', this.view.getNameOfPlayer1());
        View.printMessage("Введите имя второго пользователя:\n");
        this.players = new Player[]{p1, createPlayer(p1, p1.getSymbol()=='X'?'O':'X', this.view.getNameOfPlayer2())};
        View.printMessage(this.players[0].getName() + " играет " + this.players[0].getSymbol() + "\n");
        View.printMessage(this.players[1].getName() + " играет " + this.players[1].getSymbol() + "\n");
        int dim[];
        int countToWin = this.getCountOfWin();
        dim = this.getDimensions(countToWin);
        this.field = new Field(countToWin, dim[0], dim[1]);
    }

    private Player createPlayer(Player player1, char symbol, String name)
    {
        if(name.trim().toLowerCase().equals("bot")) return new Bot(symbol, player1 == null?name + "1":name + "2");
        else if(name.trim().toLowerCase().equals("random")) return new RandomPlayer(symbol, player1 == null?name + "1":name + "2");
        else return new HumanPlayer(symbol, name);
    }

    private int[] getDimensions(int countToWin)
    {
        int m, n;
        while (true) {
            try {
                View.printMessage("Введите размер поля(2 целых числа:)\n");
                m = this.view.getFieldSize();
                n = this.view.getFieldSize();
                if(m < 0 || n < 0 ) throw new Exception("Одно или оба из введенных значений отрицательны");
                if(m < countToWin && n < countToWin) throw new Exception("Размер поля не позволяет кому-нибудь выйграть");
                break;
            }
            catch (Exception e)
            {
                View.printMessage("Введены некорректные значения/е. " + e.getMessage() +"\n");
            }
        }
        return new int[]{m,n};
    }

    private int getCountOfWin()
    {
        int countToWin ;
        while (true) {
            try {
                View.printMessage("Введите количество символов 'X' или 'O' подряд необходимых для выигрыша: \n");
                countToWin = this.view.getCountToWin();
                if(countToWin < 0) throw new Exception();
                break;
            } catch (Exception e) {
                View.printMessage("Введено некорректное значение.\n");
            }
        }
        return countToWin;
    }
    public void play()
    {
        Player currentPlayer = this.getPlayer();
        do {
            int point[];
            do {
                point = this.getPoint(currentPlayer);
            } while (!this.field.setValue(point[0], point[1], currentPlayer.getSymbol()));
            char winner = field.checkWinner(point[1], point[0], currentPlayer.getSymbol());
            if (winner == Field.O || winner == Field.X) {
                View.printMessage("Победил игрок играющий " + currentPlayer.getSymbol() + " " + currentPlayer.getName() + "\n");
                this.view.printField(this.field);
                return;
            }
            this.view.printField(this.field);
            currentPlayer = this.nextPlayer(currentPlayer);
        } while (!this.field.isFull());
        View.printMessage("Ничья\n");
    }

    private Player nextPlayer(Player currentPlayer)
    {
        if(currentPlayer.equals(this.players[0])) return this.players[1];
        return this.players[0];
    }

    private Player chooseFirstPlayer()// Если 1 то ходит первый игрок,
    {
        try {
            View.printMessage("Введите кто ходит первым : [1]" + this.players[0].getName() +
                                  " или [любую фигню]" + this.players[1].getName()+"\n");
            return Integer.parseInt(this.view.getString()) == 1 ? this.players[0] : this.players[1];
        } catch (Exception e) {
            return this.players[1];
        }
    }
    private Player getPlayer()
    {
        Player currentPlayer = null;
        while(currentPlayer == null){
            currentPlayer = this.chooseFirstPlayer();
        }
        return currentPlayer;
    }
    private int[] getPoint(Player currentPlayer)
    {
        int point[];
        do {
            point = currentPlayer.makeTurn(this.field, this.view);
        } while (point == null);
        return point;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
