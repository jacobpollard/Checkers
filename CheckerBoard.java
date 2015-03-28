

/**
 * Program: CheckerBoard.java
 * 
 * .....
 * 
 * @author Jacob Pollard Neil Butcher
 * 
 * @version tbd...
 */

public class CheckerBoard implements ConstVals
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

/*
 * This returns 1 if the space is empty 
 * 				0 if the space has a piece in it
 * 				and -1 if index was invalid
 * 
 */
    private boolean isLegal(int x, int y)
    {
    	return (x >= 0 && y >= 0 && x < 8 && y < 8);
    }
    public int isEmptySpace(int x, int y)
    {
    	if(isLegal(x, y))
    	{
    		if(board[x][y].isEmpty())
    		{
    			return 1;
    		}
    		else
    		{
    			return 0;
    		}
    			
    	}
    	else
    	{
    		return -1;
    		
    	}
    }
    public int isKing(int x, int y)
    {
    	if(!isLegal(x, y))
    	{
    		return -1;
    	}
    	else if(board[x][y].isKing())
    	{
    		return 1;
    	}
    	else
    	{
    		return 0;
    	}
    }
    public void makeMove(int startX, int startY, int endX, int endY)
    {
    	Pieces temp = board[endX][endY];
        board[endX][endY] = board[startX][startY];
        board[startX][startY] = temp;
    }

    public PieceTypes getPieceType(int x, int y)
    {
    	if(!isLegal(x, y))
    	{
    		return PieceTypes.INVALID;
    	}
    	else if(board[x][y].isEmpty())
    	{
    		return PieceTypes.EMPTY_SPACE;
    	}
    	else if(board[x][y].isCompPiece())
    	{
    		return PieceTypes.COMP_PIECE;
    	}
    	else
    	{
    		return PieceTypes.PLAYER_PIECE;
    	}
    	
    }
    public void removePiece(int x, int y)
    {
    	board[x][y] = new Pieces(0);
    }
    public void addPiece(int x, int y, boolean isKinged, int status)
    {
    	Pieces piece = new Pieces(status);
    	if(isKinged == true)
    	{
    		piece.KingMe();
    	}
    }

    /**
     * Returns an array of pieces containing the pieces in the following
     * locations/indices relative to the specified piece.
     * 
     * |5|_|_|_|4| 
     * |_|1|_|0|_| 
     * |_|_|X|_|_| 
   
   
   
    /**
     * Prints the state of the board.
     */
    public void printBoard()
    {
    	System.out.print(" ");
    	for(int x = 0; x < 8; x++)
    	{
    		System.out.print("|"+x);
    	}
    	System.out.println();
        int i, j;
        for (i = 0; i < 8; i++)
        {
            System.out.print(i +"|");
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

	public Pieces getPiece(int i, int j) {
		return board[i][j];
	}

}
