import java.util.Scanner;

public class TicTackToe {
    public static void main(String[] args) {
        while (true)
        {
            Controller controller = new Controller();
            controller.play();
            View.printMessage("Хотите сыграть еще раз [любую фигню]yes or [n]o: \n");
            if(controller.getView().getString().trim().toLowerCase().equals("n")) return;
        }
    }
}
