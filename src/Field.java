import java.util.EmptyStackException;

public class Field {
    public char[][] getData() {
        return data;
    }

    private char [][] data;
    private int countToWin;
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char EMPTY = '-';
    //public static final int N = 10;
    //public static final int COUNT_TO_WIN = 3;

    public Field(int countToWin, int X, int Y) {
        this.data = new char[X][Y];
        this.countToWin = countToWin;
        this.init();
    }

    public void initTest()
    {
        this.init();
        this.data[0][0] = X;    this.data[0][1] = EMPTY;this.data[0][2] = X;    this.data[0][3] = O;    this.data[0][4] = O;
        this.data[1][0] = EMPTY;this.data[1][1] = X;    this.data[1][2] = X;    this.data[1][3] = X;this.data[1][4] = O;
        this.data[2][0] = X;    this.data[2][1] = X;    this.data[2][2] = EMPTY;this.data[2][3] = X;    this.data[2][4] = X;
        this.data[3][0] = O;    this.data[3][1] = EMPTY;    this.data[3][2] = EMPTY;this.data[3][3] = X;    this.data[3][4] = X;
        this.data[4][0] = O;    this.data[4][1] = O;    this.data[4][2] = X;    this.data[4][3] = EMPTY;this.data[4][4] = O;
    }

    private void init()
    {
        for(int i = 0 ; i < this.data.length ; i ++)
        {
            for (int j = 0 ; j < this.data[i].length; j++)
            {
                this.data[i][j] = EMPTY;
            }
        }
    }
    
    public boolean setValue(int x, int y, char symbol)
    {
        if(x < 0 || x >= this.data[0].length || y < 0 || y >= this.data.length)
        {
            View.printMessage("Выход за границы игрового поля\n");
            return false;
        }
        if(symbol != X && symbol != O)
        {
            View.printMessage("Недопустимый символ: '"+ symbol + "'\n");
            return false;
        }
        if(this.data[x][y] == EMPTY || this.data[x][y] == 0) this.data[x][y] = symbol;
        else {View.printMessage("Поле занято\n");return false;}
        return true;
    }
    
    private int getLBoundX(int x)
    {
        return 0;
    }
    private int getLBoundY(int y)
    {
        return  0;
    }
    private int getRBoundX(int x, int y)
    {
        return this.data[y].length;
    }
    private int getRBoundY(int y)
    {
        return this.data.length;
    }

    private int min(int a, int b)
    {
        return a>b?b:a;
    }
    private int[] getLBoundXYright(int x, int y)
    {
        int sumOfIndex = x+y;
        if(sumOfIndex < min(this.data.length , this.data[y].length) - 1)
            return new int[]{0, sumOfIndex};
        else if(sumOfIndex > min(this.data.length , this.data[y].length) - 1)
            return new int[]{sumOfIndex - this.data.length + 1, this.data.length - 1};
        else return new int[]{0, this.data.length - 1};
    }
    private int[] getRBoundXYright(int x, int y)
    {
        int sumOfIndex = x + y;
        if(x + y < min(this.data.length , this.data[y].length) - 1)
            return new int[]{sumOfIndex, 0};
        else if(x + y > min(this.data.length , this.data[y].length) - 1)
            return new int[]{this.data[y].length - 1, sumOfIndex - this.data[y].length + 1};
        else return new int[]{this.data[y].length - 1, 0};
    }

    private int[] getLBoundXYleft(int x, int y)
    {
        if(x > y)
            return new int[]{x - y, 0};
        else if(x < y)
            return new int[]{0, y - x};
        else
            return new int[]{0, 0};
    }
    private int[] getRBoundXYleft(int x, int y)
    {

        if(x<y) return new int[]{x + this.data[y].length - y , this.data.length};
        else if(x>y) return new int[]{this.data[y].length,this.data.length + y - x};
        else  return new int[]{this.data[y].length , this.data.length};
    }
    //public
    private boolean checkHorizontal(int x, int y, char symbol)
    {
        int countToWin = 0;
        for(int i = this.getLBoundX(x) ; i < this.getRBoundX(x,y) ; i++)
        {
            if(this.data[y][i] == symbol) countToWin++;
            else countToWin = 0;
            if(countToWin >= this.countToWin) return true;
        }
        return false;
    }
    
    //public
    private boolean checkVertical(int x, int y, char symbol)
    {
        int countToWin = 0;
        for(int j = this.getLBoundY(y) ; j < this.getRBoundY(y) ; j++)
        {
            if(this.data[j][x] == symbol) countToWin++;
            else {countToWin = 0;}
            if(countToWin >= this.countToWin) return true;
        }
        return false;
    }
    
    //private
    public boolean checkRightDiagonal(int x, int y, char symbol)
    {
        int countToWin = 0;
        int lboundXY[] = this.getLBoundXYright(x,y);
        int rboundXY[] = this.getRBoundXYright(x,y);
        for(int i = lboundXY[1],  j = lboundXY[0];
            i >= rboundXY[1] && j <= rboundXY[0]; i--,j++)
        {
            if(this.data[i][j] == symbol) countToWin++;
            else {countToWin = 0;}
            if(countToWin >= this.countToWin) return true;
        }
        return  false;
    }
    
    //private
    public boolean checkLeftDiagonal(int x, int y, char symbol)
    {
        int countToWin = 0;

        for(int i = this.getLBoundXYleft(x, y)[1], j = this.getLBoundXYleft(x,y)[0] ;
            i <this.getRBoundXYleft(x, y)[1] && j < this.getRBoundXYleft(x,y)[0] ;
            i++, j++)
        {
            if(this.data[i][j] == symbol) countToWin++;
            else {countToWin = 0;}
            if(countToWin >= this.countToWin) return true;
        }
        return false;
    }
    
    public char checkWinner(int x, int y, char symbol)
    {
        if (    this.checkHorizontal(x, y, symbol)    ||
                this.checkVertical(x, y, symbol)      ||
                this.checkRightDiagonal(x, y, symbol) ||
                this.checkLeftDiagonal(x, y, symbol)   ) 
        {return symbol;}
        return EMPTY;
    }
    
    public boolean isFull()
    {
        for(int i = 0 ; i < this.data.length ; i ++)
        {
            for (int j = 0 ; j < this.data[i].length; j++)
            {
                if(this.data[i][j] == EMPTY || this.data[i][j] == 0) return false;
            }
        }
        return true;
    }
    
    public void printField()
    {
        for(int i = 0 ; i < this.data.length ; i ++)
        {
            for (int j = 0 ; j < this.data[i].length; j++)
            {
                View.printMessage(((this.data[i][j] == 0 || this.data[i][j] == EMPTY)?EMPTY:this.data[i][j]) + " ");
            }
            View.printMessage("\n");
        }
        View.printMessage("\n");
    }
}
