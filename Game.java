import java.util.Scanner;
public class Game
{
    static Game uniqueInstance;
    private CheckerBoard board;
    private boolean playerTurn;

    private Game()
    {
        board = new CheckerBoard();
        playerTurn = true;
    }
    public static void main(String args[])
    {
    	Game x = Game.getInstance();
    	x.play();
    }
    public void play()
    {
    	Scanner input = new Scanner(System.in);
    	int startX;
    	int startY;
    	int endX;
    	int endY;
    	while(gameNotOver())
    	{
    		board.printBoard();
    		System.out.println("please enter next move");
    		startX = input.nextInt();
    		startY = input.nextInt();
    		endX = input.nextInt();
    		endY = input.nextInt();
    		makeMove(startX, startY, endX, endY);
    	}
    }
    public boolean gameNotOver()
    {
    	return true;
    }
    static Game getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }
    private boolean isJump(int startX, int startY, int endX, int endY)
    {
    	return ((startX + 2 == endX && startY + 2 == endY) ||
    			(startX + 2 == endX && startY - 2 == endY) ||
    			(startX - 2 == endX && startY + 2 == endY) ||
    			(startX - 2 == endX && startY - 2 == endY));
    	
    }
    public void makeMove(int startX, int startY, int endX, int endY)
    {
    	if(checkMove(startX, startY, endX, endY))
    	{
    		board.makeMove(startX, startY, endX, endY);
    		playerTurn = !playerTurn;
    		if(isJump(startX, startY, endX, endY))
    		{
    			board.removePiece((startX + endX) / 2, (startY + endY)/2);
    			if(playerTurn && playerCanJump(endX, endY))
    			{
    				System.out.println("anouther jump available, it must be taken");
    			}
    			else if(!playerTurn && computerCanJump(endX, endY))
    			{
    				System.out.println("anouther jump available");
    			}
    		
    		}
    	}
    	else
    	{
    		System.out.println("is not a legal move, please enter anouther");
    	}
    	 
    }
    /*
     * Checks the legality of a move
     * First it must be determined if the move was a jump, if it wasn't a jump
     * we must check if a jump was available (because jumps must be taken)
     * We must also check if it was the correct players piece at the start position
     * Then we have to check if destination was space was empty
     */
  
    public boolean checkMove(int startX, int startY, int endX, int endY)
    {
     if(board.getPieceType(startX, startY) == 2)
     {
    	 System.out.println("start was player piece");
     }
      System.out.println();
      if(isJump(startX, startY, endX, endY))
      {
    	if(isLegalJump(startX, startY, endX, endY))
    	{
    		return true;
    	}
      }
      if(hasJump())
      {
    	  return false;
      }
      if(isLegalMove(startX, startY, endX, endY))
      {
    	  return true;
      }
      else
      {
    	System.out.println("in else");  
    	  return false;
      }
    }

    private boolean isLegalMove(int startX, int startY, int endX, int endY) {
		if(board.isEmptySpace(endX, endY) != 1)
		{
			System.out.println("wasn't to empty space");
			return false;
		}
		if(Math.abs(startX - endX) != 1 || Math.abs(startY - endY) != 1)
		{
			return false;
		}
    	if(playerTurn && board.getPieceType(startX, startY) == 2)
    	{	
    		System.out.println("was wrong piece type");
    		return true;
    	}
    	else if(!playerTurn && board.getPieceType(startX, startY) == 1)
    	{	
    		return true;
    	}
    	System.out.println("piece typee " + board.getPieceType(startX,  startY));
		return false;
	}

	private boolean hasJump() 
    {
		int start = 1;
    	for(int row = 0; row < 8; row++)
    	{
    		for(int col = start; col < 8; col+=2)
    		{
    			if(playerTurn && playerCanJump(row, col))
    			{
    				return true;
    			}
    			else if(!playerTurn && computerCanJump(row, col))
    			{
    				return true;
    			}
    		}
    		start++;
    		start = start % 2;
    	}
    	return false;
	}

	private boolean isLegalJump(int startX, int startY, int endX, int endY) {
    	if(board.getPieceType(endX, endY) != 0)
    	{
    		return false;
    	}
    	if(playerTurn && board.getPieceType(startX, startY) == 2 )
    	{
    		if(endY - startY > 0) // if move is downward, is the piece kinged? 
    		{
    			if(board.isKing(startX, startY) != 1)
    			{
    				return false;
    			}
    		}
    		if(board.getPieceType((startX + endX)/2, (startY + endY)/2) == 1)
    		{
    			return true;
    		}
    	}   	
    	else if(!playerTurn && board.getPieceType(startX, startY) == 1 )
    	{
    		if(endY - startY < 0) // if move is upward, is the piece kinged? 
    		{
    			if(board.isKing(startX, startY) != 1)
    			{
    				return false;
    			}
    		}
    		if(board.getPieceType((startX + endX)/2, (startY + endY)/2) == 2)
    		{
    			return true;
    		}
    	}
    	return false;
	}


    // forward is moving non king piece for a computer
    private boolean canJumpUpRight(int startX, int startY)
    {
    	int endSpaceEmpty = board.isEmptySpace(startX + 2, startY - 2);
    	if(endSpaceEmpty != 1)// if the end space isn't  empty
    	{
    		return false;
    	}
    	int startPiece = board.getPieceType(startX, startY);
    	int jumpPiece = board.getPieceType(startX + 1, startY - 1);
    	if(startPiece == 1)
    	{
    		if(jumpPiece == 2)
    		{
    			return true;
    		}
    	}
    	else if(startPiece == 2)
    	{
    		if(jumpPiece == 1)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    private boolean canJumpUpLeft(int startX, int startY)
    {
    	int endSpaceEmpty = board.isEmptySpace(startX - 2, startY - 2);
    	if(endSpaceEmpty != 1)// if the end space isn't  empty
    	{
    		return false;
    	}
    	int startPiece = board.getPieceType(startX, startY);
    	int jumpPiece = board.getPieceType(startX - 1, startY - 1);
    	if(startPiece == 1)
    	{
    		if(jumpPiece == 2)
    		{
    			return true;
    		}
    	}
    	else if(startPiece == 2)
    	{
    		if(jumpPiece == 1)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    private boolean canJumpDownRight(int startX, int startY)
    {
    	int endSpaceEmpty = board.isEmptySpace(startX + 2, startY + 2);
    	if(endSpaceEmpty != 1)// if the end space isn't  empty
    	{
    		return false;
    	}
    	int startPiece = board.getPieceType(startX, startY);
    	int jumpPiece = board.getPieceType(startX + 1, startY + 1);
    	if(startPiece == 1)
    	{
    		if(jumpPiece == 2)
    		{
    			return true;
    		}
    	}
    	else if(startPiece == 2)
    	{
    		if(jumpPiece == 1)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    private boolean canJumpDownLeft(int startX, int startY)
    {
    	int endSpaceEmpty = board.isEmptySpace(startX - 2, startY + 2);
    	if(endSpaceEmpty != 1)// if the end space isn't  empty
    	{
    		return false;
    	}
    	int startPiece = board.getPieceType(startX, startY);
    	int jumpPiece = board.getPieceType(startX - 1, startY + 1);
    	if(startPiece == 1)
    	{
    		if(jumpPiece == 2)
    		{
    			return true;
    		}
    	}
    	else if(startPiece == 2)
    	{
    		if(jumpPiece == 1)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    private boolean canJumpBackward(int x, int y)
    {
    	return (canJumpUpRight(x, y) || canJumpUpLeft(x, y));
    }
    private boolean canJumpForward(int x, int y)
    {
    	return (canJumpDownRight(x, y) || canJumpDownLeft(x,y));
    }
    private boolean playerCanJump(int startX, int startY)
    {
    	if(board.getPieceType(startX,  startY) != 2)
    	{
    		return false;
    	}
        if (canJumpBackward(startX, startY))
        {
            return true;
        }
        if (board.isKing(startX, startY) == 1)
        {
            return (canJumpForward(startX, startY));
        }
        else
        {
        	return false;
        }
    }
    private boolean computerCanJump(int startX, int startY)
    {
    	if(board.getPieceType(startX,  startY) != 1)
    	{
    		return false;
    	}
    	if(canJumpForward(startX, startY))
    	{
    		return true;
    	}
    	if (board.isKing(startX, startY) == 1)
    	{
    		
    		return (canJumpForward(startX, startY));		
    	}
    	else
    	{
    		return false;
    	}
    }

}
