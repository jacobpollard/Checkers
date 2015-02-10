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
                Pieces comp = new Pieces(2);
                Pieces player = new Pieces(1);
                Pieces empty = new Pieces(0);
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
                    board[i][j] = empty;
                }
            }
        }
    }

    public void setBoard()
    {

    }

    public void setPiece(int x, int y, Pieces piece)
    {
        board[x][y] = piece;
    }

    public Pieces getState(int x, int y)
    {
        return board[x][y];
    }

    public Pieces getPiece(int x, int y)
    {
        return board[x][y];
    }

    public void makeMove(int startX, int startY, int endX, int endY)
    {
        board[endX][endY] = board[startX][startY];
    }

    public boolean hasPlayerPiece(int x, int y)
    {
        if (board[x][y].isEmpty() || board[x][y].isCompPiece())
        {
            return false;
        }
        if (!board[x][y].isCompPiece())
        {
            return true;
        }
        return true;
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean hasCompPiece(int x, int y)
    {
        if (board[x][y].isEmpty() || !board[x][y].isCompPiece())
        {
            return false;
        }
        if (board[x][y].isCompPiece())
        {
            return true;
        }
        return true;
    }

    /**
     * Returns an array of pieces containing the pieces in the following
     * locations/indices relative to the specified piece.
     * 
     * |5|_|_|_|4| 
     * |_|1|_|0|_| 
     * |_|_|X|_|_| 
     * |_|2|_|3|_| 
     * |6|_|_|_|7|
     * 
     * @param x
     *            x index in the board
     * @param y
     *            y index in the board
     * @return array containing pieces in specific indices
     */
    public Pieces[] getSurrounds(int x, int y)
    {
        Pieces[] mine = new Pieces[8];

        if (x > 7 || y > 7)
        {
            return null;
        }
        if (x != 0 && y != 7)
        {
            mine[0] = board[x - 1][y + 1];
            if (x > 1 && y < 6)
            {
                mine[4] = board[x - 2][y + 2];
            }
        }
        if (x != 0 && y != 0)
        {
            mine[1] = board[x - 1][y - 1];
            if (x > 1 && y > 1)
            {
                mine[5] = board[x - 2][y - 2];
            }
        }
        if (x != 7 && y != 0)
        {
            mine[2] = board[x + 1][y - 1];
            if (x < 6 && y > 1)
            {
                mine[6] = board[x + 2][y - 2];
            }
        }
        if (x != 7 && y != 7)
        {
            mine[3] = board[x + 1][y + 1];
            if (x < 6 && y < 6)
            {
                mine[7] = board[x + 2][y + 2];
            }
        }
        return mine;
    }

    /**
     * Prints the state of the board.
     */
    public void printBoard()
    {
        int i, j;
        for (i = 0; i < 8; i++)
        {
            System.out.print("|");
            for (j = 0; j < 8; j++)
            {
                if (board[i][j].isEmpty())
                {
                    System.out.print("_");
                }
                else if (board[i][j].isCompPiece() == true)
                {
                    System.out.print("C");
                }
                else
                {
                    System.out.print("P");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

}
