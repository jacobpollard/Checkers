/**
 * Program: CheckerBoard.java
 * 
 * .....
 * 
 * @author Jacob Pollard Neil Butcher
 * 
 * @version tbd...
 */

public class CheckerBoard
{
    public Pieces[][] board;

    public static void main(String args[])
    {
        CheckerBoard king = new CheckerBoard();
        king.printBoard();
    }

    public CheckerBoard()
    {
        int i, j;
        board = new Pieces[8][8];

        for (i = 0; i < 8; i++)
        {
            for (j = 0; j < 8; j++)
            {
                Pieces comp = new Pieces(true);
                Pieces player = new Pieces(false);

                if ((j % 2 == 1) && (i == 0 || i == 2))
                {
                    board[i][j] = comp;
                }
                else if ((j % 2 == 0) && (i == 1))
                {
                    board[i][j] = comp;
                }
                else if ((j % 2 == 0) && (i == 5 || i == 7))
                {
                    board[i][j] = player;
                }
                else if ((j % 2 == 1) && (i == 6))
                {
                    board[i][j] = player;
                }
                else
                {
                    board[i][j] = null;
                }
            }
        }
    }

    public Pieces[][] getBoard()
    {
        return board;
    }

    public Pieces getState(int x, int y)
    {
        return board[x][y];
    }

    public void makeMove(int startX, int startY, int endX, int endY)
    {
       board[endX][endY] = board[startX][startY];
    }
    
    public boolean hasPlayerPiece(int x, int y)
    {
        if(board[x][y] == null || board[x][y].isCompPiece())
        {
            return false;
        }
        if(!board[x][y].isCompPiece())
        {
            return true;
        }
        return true;
    }
    
    public boolean hasCompPiece(int x, int y)
    {
        if(board[x][y] == null || !board[x][y].isCompPiece())
        {
            return false;
        }
        if(board[x][y].isCompPiece())
        {
            return true;
        }
        return true;
    }

    public void printBoard()
    {
        int i, j;
        for (i = 0; i < 8; i++)
        {
            System.out.print("| ");
            for (j = 0; j < 8; j++)
            {
                if (board[i][j] == null)
                {
                    System.out.print("0");
                }
                else if (board[i][j].isCompPiece() == true)
                {
                    System.out.print("C");
                }
                else
                {
                    System.out.print("P");
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

}
