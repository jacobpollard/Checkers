import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Stack;


public class Game implements ConstVals
{
	ArrayList<MoveList> allMoves;
	Stack<Pieces> stack;
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
    		//makeMove(startX, startY, endX, endY);
    	}
    }
    public ArrayList<Move> getMoves()
    {
    	
    	if(!getJumps().isEmpty())
    	{
    		return getJumps();
    	}
    	else
    	{
    		return getBasicMoves();
    	}
    }
    private ArrayList<Move> getBasicMoves() {
		// TODO Auto-generated method stub
		return null;
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
  
    /*
     * Checks the legality of a move
     * First it must be determined if the move was a jump, if it wasn't a jump
     * we must check if a jump was available (because jumps must be taken)
     * We must also check if it was the correct players piece at the start position
     * Then we have to check if destination was space was empty
     */
  

	private ArrayList<Move> getJumps() 
    {
		ArrayList<Move> x = new ArrayList<Move>();
		int start = 1;
    	for(int row = 0; row < 8; row++)
    	{
    		for(int col = start; col < 8; col+=2)
    		{
    			if(playerTurn)
    			{
    				ArrayList<Move> y = playerCanJump(row, col);
    				x.addAll(y);
    			}
    			else
    			{
    				ArrayList<Move> y = computerCanJump(row, col);
    	    		x.addAll(y);
    			}
    		}
    		start++;
    		start = start % 2;
    	}
    	return x;
	}
	/*
	 * This checks to make sure the jump is legal, that the intermediate piece is the right type
	 * returns true if the jump is legal
	 * 
	 * 
	 */
	private boolean isLegalJump(int startX, int startY, int endX, int endY) {
    	if(board.getPieceType(endX, endY) != PieceTypes.EMPTY_SPACE)
    	{
    		return false;
    	}
    	if(playerTurn && board.getPieceType(startX, startY) == PieceTypes.PLAYER_PIECE )
    	{
    		if(endY - startY > 0) // if move is downward, is the piece kinged? 
    		{
    			if(board.isKing(startX, startY) != 1)
    			{
    				return false;
    			}
    		}
    		if(board.getPieceType((startX + endX)/2, (startY + endY)/2) == PieceTypes.COMP_PIECE)
    		{
    			return true;
    		}
    	}   	
    	else if(!playerTurn && board.getPieceType(startX, startY) == PieceTypes.COMP_PIECE )
    	{
    		if(endY - startY < 0) // if move is upward, is the piece kinged? 
    		{
    			if(board.isKing(startX, startY) != 1)
    			{
    				return false;
    			}
    		}
    		if(board.getPieceType((startX + endX)/2, (startY + endY)/2) == PieceTypes.PLAYER_PIECE)
    		{
    			return true;
    		}
    	}
    	return false;
	}


    // forward is moving non king piece for a computer
    private boolean canJumpUpRight(int startX, int startY)
    {
    	int endX = startX + 2; 
    	int endY = startY - 2;
    	if(isLegalJump(startX, startY, endX, endY))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
    }
    private boolean canJumpUpLeft(int startX, int startY)
    {
    	int endX = startX - 2; 
    	int endY = startY - 2;
    	if(isLegalJump(startX, startY, endX, endY))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    private boolean canJumpDownRight(int startX, int startY)
    {
    	stack = new Stack<Pieces>();
    	int endX = startX + 2; 
    	int endY = startY + 2;
    	if(isLegalJump(startX, startY, endX, endY))
    	{
    		Pieces x = board.getPiece((endX + startX)/2, (startY+endY)/2);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    private boolean canJumpDownLeft(int startX, int startY)
    {
    	int endX = startX - 2; 
    	int endY = startY + 2;
    	if(isLegalJump(startX, startY, endX, endY))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    private ArrayList<Move> canJumpBackward(int x, int y)
    {
    	ArrayList<Move> retVal = new ArrayList<Move>(); 
    	Move val1 = canJumpUpRight(x, y);
        Move val2 = canJumpUpLeft(x, y);
        if(val1 != null)
        {
        	retVal.add(val1);
        }
        if(val2 != null)
        {
        	retVal.add(val2);
        }
        return retVal;
    }
    private ArrayList<Move> canJumpForward(int x, int y)
    {
    	
    	
    	ArrayList<Move> retVal = new ArrayList<Move>(); 
    	Move val1 = canJumpDownRight(x, y);
        Move val2 = canJumpDownLeft(x,y);
        if(val1 != null)
        {
        	retVal.add(val1);
        }
        if(val2 != null)
        {
        	retVal.add(val2);
        }
        return retVal;
    }
    private ArrayList<MoveList> allJumpsAtIndex(int startX, int startY)
    {
    	allMoves = new ArrayList<MoveList>(); 
    	if(playerTurn && board.getPieceType(startX, startY) == PieceTypes.PLAYER_PIECE)
    	{
    		retVal.addAll(jumpUpLeft(startX, startY, null));
    		retVal.addAll(jumpUpRight(startX, startY, null));
    		if(board.isKing(startX,  startY) == 1)
    		{
    			retVal.addAll(jumpBackLeft(startX, startY, null));
    			retVal.addAll(jumpBackRight(startX, startY, null));
    		}
    		return retVal;
    	}
    	else if(!playerTurn && board.getPieceType(startX, startY) == PieceTypes.COMP_PIECE)
    	{
    		
    	}
		return retVal;
    }
	private void jumpUpLeft(int startX, int startY, MoveList moves) {
		if(canJumpUpLeft(startX, startY))
		{
			if(moves == null)
			{
				moves = new MoveList();
			}
			else
			{
				moves = new MoveList(moves); // make a copy of it if it already exists
			}
			// move piece and remove middle
			// add move to moves
			// push move onto stack
			// then see if call jumpUpLeft and jumpUpRight works
			if(board.isKing(startX,  startY) == 0)
			{
				if(!canJumpUpLeft(startX, startY) && !canJumpUpRight(startX, startY))
				{ // if we can't keep jumping then we are done
					allMoves.add(moves);
				}
				jumpUpLeft(startX, startY, moves);
				jumpUpRight(startX, startY, moves);
			}
			else if(board.isKing(startX,  startY) == 1)
			{
				if(!canJumpUpLeft(startX, startY) && !canJumpUpRight(startX, startY) 
						&& canJumpDownLeft(startX, startY) && canJumpDownRight(startX, startY))
				{ // if we can't keep jumping then we are done
					allMoves.add(moves);
				}
				jumpUpLeft(startX, startY, moves);
				jumpUpRight(startX, startY, moves);
				jumpDownLeft(startX, startY, moves);
				jumpDownnRight(startX, startY, moves);
			}
		}
	}

}
