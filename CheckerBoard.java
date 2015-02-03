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

    public void printBoard()
    {
        int i, j;
        for (i = 0; i < 8; i++)
        {
            for (j = 0; j < 8; j++)
            {
                if (board[i][j] == null)
                {
                    System.out.print("0  ");
                }
                else if (board[i][j].isCompPiece() == true)
                {
                    System.out.print("C  ");
                }
                else
                {
                    System.out.print("P  ");
                }
            }
            System.out.print("\n");
        }
    }

}
