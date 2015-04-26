import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Game implements ConstVals
{
	ArrayList<MoveList> allMoves;
	Stack<Pieces> stack;
    static Game uniqueInstance;
    static Game manipulateInstance;
    private BetterBoard board;
    private boolean playerTurn;

    private Game()
    {
        board = new BetterBoard();
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
    	Computer cpu = new Computer();
    	while(gameNotOver())
    	{
    		board.printBoard();
    		getMoves();
    		displayMoves();
    		board.printBoard();
    		int index;
    		if(playerTurn)
    		{
    			System.out.println("please choose a move");
    			index = input.nextInt();
    		}
    		else
    		{
    			index = cpu.getMove(allMoves);
    		}
			while (!makeMove(index))
    		{
    			System.out.println("Move was illegal, please enter anouther");
    			board.printBoard();
    			displayMoves();
    			index = input.nextInt();
    		}
    		playerTurn = !playerTurn;
    		//makeMove(startX, startY, endX, endY);
    	}
    	input.close();
    }
    public boolean makeMove(int index) {
		
    	if(index < 0 || index >= allMoves.size())
		{
			return false;
		}
		else
		{
			MoveList move = allMoves.get(index);
			Move lastMove = move.get(0);
			
			for(int x = 1; x < move.size(); x++)
			{
				Move currentMove = move.get(x);
				int avgX = (currentMove.getX() + lastMove.getX())/2;
				if(avgX == lastMove.getX() || avgX ==currentMove.getX())// if move was simple
				{
					board.makeMove(lastMove.getX(), lastMove.getY(), 
							currentMove.getX(), currentMove.getY());
				}
				else // if move is a jump
				{
					board.makeMove(lastMove.getX(), lastMove.getY(), 
							currentMove.getX(), currentMove.getY());
					int avgY = (currentMove.getY() + lastMove.getY())/2;
					board.removePiece(avgX, avgY);					
				}
				lastMove = currentMove;
			}
			checkToKingPiece(lastMove);
			return true;
		}
	}
	private void checkToKingPiece(Move currentMove) {
		int x = currentMove.getX();
		int y = currentMove.getY();
		if(playerTurn)
		{
			if(x == 0 && board.getPieceType(x, y) == PieceTypes.PLAYER_PIECE)
			{
				board.kingPiece(x, y);
			}
		}
		else
		{
			if(x == 7 && board.getPieceType(x, y) == PieceTypes.COMP_PIECE)
			{
				board.kingPiece(x, y);
			}
		}
		
	}
	private void displayMoves() {
		// TODO Auto-generated method stub
		for(int x = 0; x < allMoves.size(); x++)
		{
			System.out.print("Move "+x+": ");
			MoveList current = allMoves.get(x);
			System.out.println(current.toString());

		}
	}
	public void getMoves()
    {
		allMoves = new ArrayList<MoveList>();
    	getJumps();
    	if(allMoves.isEmpty())
    	{
     		getBasicMoves();
    	}
    }
    private void getBasicMoves() {
		// TODO Auto-generated method stub
    	int start = 0;
    	for(int row = 0; row < 8; row++)
    	{
    		for(int col = start; col < 8; col+=2)
    		{
    			addBasicMove(col, row );
    		}
    		start++;
    		start = start % 2;
    	}
    	
		
	}
private void addBasicMove(int row, int col) 
{
	if(board.getPieceType(row, col) == PieceTypes.EMPTY_SPACE 
	|| (board.getPieceType(row, col) == PieceTypes.PLAYER_PIECE && !playerTurn)
	|| (board.getPieceType(row, col) == PieceTypes.COMP_PIECE && playerTurn))
	{
		return;
	}
	if(canMoveUp(row, col))
	{
		if(board.getPieceType(row-1, col+1) == PieceTypes.EMPTY_SPACE)
		{
			MoveList newMove = new MoveList(new Move(row, col));
			newMove.add(new Move(row-1, col+1));
			allMoves.add(newMove);
		}	
		if(board.getPieceType(row-1, col-1) == PieceTypes.EMPTY_SPACE)
		{
			MoveList newMove = new MoveList(new Move(row, col));
			newMove.add(new Move(row-1, col-01));
			allMoves.add(newMove);
		}
	}
	if(canMoveDown(row, col))
	{
		if(board.getPieceType(row+1, col-1) == PieceTypes.EMPTY_SPACE)
		{
			MoveList newMove = new MoveList(new Move(row, col));
			newMove.add(new Move(row+1, col-1));
			allMoves.add(newMove);
		}
		if(board.getPieceType(row+1, col+1) == PieceTypes.EMPTY_SPACE)
		{
			MoveList newMove = new MoveList(new Move(row, col));
			newMove.add(new Move(row+1, col+1));
			allMoves.add(newMove);
		}
	}
}
	private boolean canMoveDown(int row, int col) {
		// TODO Auto-generated method stub
		if(!playerTurn && board.getPieceType(row, col) == PieceTypes.COMP_PIECE)
		{
			return true;
		}
		else if(playerTurn && board.getPieceType(row,  col) == PieceTypes.PLAYER_PIECE
				&& board.isKing(row,  col) == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private boolean canMoveUp(int row, int col) {
		// TODO Auto-generated method stub
		if(playerTurn && board.getPieceType(row, col) == PieceTypes.PLAYER_PIECE)
		{
			return true;
		}
		else if(!playerTurn && board.getPieceType(row,  col) == PieceTypes.COMP_PIECE
				&& board.isKing(row,  col) == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean gameNotOver()
    {
    	return true;
		//return allMoves == null || (board.getPlayerKingSize() == 0 && board.getPlayerSize() == 0) || 
    	//		(board.getComputerKingSize() == 0 && board.getComputerSize() == 0) ;
    }
    static Game getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }
    static Game getManipulateInstance()
    {
        if (manipulateInstance == null)
        {
            manipulateInstance = new Game();
        }
        return manipulateInstance;
    }
  
	private void getJumps() 
    {
		//
		int start = 0;
    	for(int row = 0; row < 8; row++)
    	{
    		for(int col = start; col < 8; col+=2)
    		{
    			allJumpsAtIndex(row, col);
    		}
    		start++;
    		start = start % 2;
    	}
	}
	/*
	 * This checks to make sure the jump is legal, that the intermediate piece is the right type
	 * returns true if the jump is legal
	 * 
	 * 
	 */
	private boolean isLegal(int x, int y)
    {
    	return (x >= 0 && y >= 0 && x < 8 && y < 8);
    }
	private boolean isLegalJump(int startX, int startY, int endX, int endY) {
		if(!isLegal(startX, startY) && !isLegal(endX, endY)) // if these indices are invalid return 0
		{
			return false;
		}
		if(startX == 2 && startY == 3 && endX == 4 && endY == 1)
			System.out.println("start x " +startX +" "+startY+" "+endX+" "+endY);
		if(board.getPieceType(endX, endY) != PieceTypes.EMPTY_SPACE)
    	{
    		return false;
    	}
    	if(playerTurn && board.getPieceType(startX, startY) == PieceTypes.PLAYER_PIECE )
    	{
    		if(endX > startX) // if move is downward, is the piece kinged? 
    		{
    			if(board.isKing(startX, startY) == 0)
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
    		if(endX < startX) // if move is upward, is the piece kinged? 
    		{
    			if(board.isKing(startX, startY) == 0)
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
    	//stack = new Stack<Pieces>();
    	int endX = startX + 2; 
    	int endY = startY + 2;
    	if(isLegalJump(startX, startY, endX, endY))
    	{
    		//Pieces x = board.getPiece((endX + startX)/2, (startY+endY)/2);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    private boolean canJumpDownLeft(int startX, int startY)
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
   
    private void allJumpsAtIndex(int startX, int startY)
    {
    	if(playerTurn && board.getPieceType(startX, startY) == PieceTypes.PLAYER_PIECE)
    	{
    		jumpUpLeft (startX, startY, null);
    		jumpUpRight(startX, startY, null);
    		if(board.isKing(startX,  startY) == 1)
    		{
    			jumpDownLeft (startX, startY, null);
    			jumpDownRight(startX, startY, null);
    		}
    	}
    	else if(!playerTurn && board.getPieceType(startX, startY) == PieceTypes.COMP_PIECE)
    	{
    		jumpDownLeft (startX, startY, null);
    		jumpDownRight(startX, startY, null);
    		if(board.isKing(startX,  startY) == 1)
    		{
    			jumpUpLeft (startX, startY, null);
    			jumpUpRight(startX, startY, null);
    		}
    	}
    }
	private void jumpUpLeft(int startX, int startY, MoveList moves) {
		Pieces oldPiece;
		int xOff = -2;
		int yOff = -2;
		if(canJumpUpLeft(startX, startY))
		{
			System.out.println("inside if" + startX +", "+ startY);
			if(moves == null)
			{
				moves = new MoveList();
				moves.add(new Move(startX, startY));
			}
			else
			{
				moves = new MoveList(moves); // make a copy of it if it already exists
			}
			moves.add(new Move(startX+xOff, startY+yOff));
			// move piece and remove middle
			board.makeMove(startX, startY, startX + xOff, startY + yOff);
			oldPiece = board.removePiece(startX + (xOff/2), startY + (yOff/2));
			if(board.isKing(startX, startY) == 0)
			{
				allMoves.add(moves);
				jumpUpLeft (startX + xOff, startY + yOff, moves);
				jumpUpRight(startX + xOff, startY + yOff, moves);
			}
			else if(board.isKing(startX,  startY) == 1)
			{ // if we can't keep jumping then we are done
				allMoves.add(moves);
				jumpUpLeft   (startX+xOff, startY+yOff, moves);
				jumpUpRight  (startX+xOff, startY+yOff, moves);
				jumpDownLeft (startX+xOff, startY+yOff, moves);
				jumpDownRight(startX-xOff, startY+yOff, moves);
			}// undo the moves
			board.makeMove(startX+xOff, startY+yOff, startX, startY);
			board.addPiece(oldPiece, startX+(xOff/2), startY+(yOff/2));
		}
	}
	private void jumpUpRight(int startX, int startY, MoveList moves) {
		Pieces oldPiece;
		int xOff = -2;
		int yOff =  2;
		if(canJumpUpRight(startX, startY))
		{
			if(moves == null)
			{
				moves = new MoveList();
				moves.add(new Move(startX, startY));
			}
			else
			{
				moves = new MoveList(moves); // make a copy of it if it already exists
			}
			moves.add(new Move(startX+xOff, startY+yOff));
			// move piece and remove middle
			board.makeMove(startX, startY, startX+xOff, startY+yOff);
			oldPiece = board.removePiece(startX+(xOff/2), startY+(yOff/2));
			if(board.isKing(startX,  startY) == 0)
			{
				//if(!canJumpUpLeft(startX+xOff, startY+yOff) && !canJumpUpRight(startX+xOff, startY+yOff))
				//{ // if we can't keep jumping then we are done
					allMoves.add(moves);
				//}
				//else
				//{	
					jumpUpLeft (startX + xOff, startY+yOff, moves);
					jumpUpRight(startX + xOff, startY+yOff, moves);
				//}
			}
			else if(board.isKing(startX,  startY) == 1)
			{
				//if(!canJumpUpLeft(startX + xOff, startY + yOff)   && 
				 //  !canJumpUpRight(startX + xOff, startY + yOff)  && 
				  // !canJumpDownLeft(startX + xOff, startY + yOff) && 
				//   !canJumpDownRight(startX + xOff, startY + yOff))
				//{ // if we can't keep jumping then we are done
				allMoves.add(moves);
				//}
				jumpUpLeft   (startX+xOff, startY+yOff, moves);
				jumpUpRight  (startX+xOff, startY+yOff, moves);
				jumpDownLeft (startX+xOff, startY+yOff, moves);
				jumpDownRight(startX-xOff, startY+yOff, moves);
			}
			board.makeMove(startX+xOff, startY+yOff, startX, startY);
			board.addPiece(oldPiece, startX+(xOff/2), startY+(yOff/2));
		}
	}
	private void jumpDownLeft(int startX, int startY, MoveList moves) {
		Pieces oldPiece;
		int xOff =  2;
		int yOff = -2;
		if(canJumpDownLeft(startX, startY))
		{
			if(moves == null)
			{
				moves = new MoveList();
				moves.add(new Move(startX, startY));
			}
			else
			{
				moves = new MoveList(moves); // make a copy of it if it already exists
			}
			moves.add(new Move(startX+xOff, startY+yOff));
			// move piece and remove middle
			board.makeMove(startX, startY, startX+xOff, startY+yOff);
			oldPiece = board.removePiece(startX+(xOff/2), startY+(yOff/2));
			if(board.isKing(startX,  startY) == 0)
			{
				//if(!canJumpDownLeft(startX+xOff, startY+yOff) && 
				//   !canJumpDownRight(startX+xOff, startY+yOff))
				//{ // if we can't keep jumping then we are done
				allMoves.add(moves);
				//}
				//else
				//{ // if we can keep jumping, then we keep jumping
				jumpDownLeft (startX + xOff, startY+yOff, moves);	
				jumpDownRight(startX + xOff, startY+yOff, moves);
				//}
			}
			else if(board.isKing(startX,  startY) == 1)
			{
				/*if(!canJumpUpLeft(startX + xOff, startY + yOff)   && 
				   !canJumpUpRight(startX + xOff, startY + yOff)  && 
				   !canJumpDownLeft(startX + xOff, startY + yOff) && 
				   !canJumpDownRight(startX + xOff, startY + yOff))
				{ // if we can't keep jumping then we are done */
					allMoves.add(moves);
				//}
				//else
				//{
					jumpUpLeft   (startX+xOff, startY+yOff, moves);
					jumpUpRight  (startX+xOff, startY+yOff, moves);
					jumpDownLeft (startX+xOff, startY+yOff, moves);
					jumpDownRight(startX-xOff, startY+yOff, moves);
				//}
			}
			board.makeMove(startX+xOff, startY+yOff, startX, startY);
			board.addPiece(oldPiece, startX+(xOff/2), startY+(yOff/2));
		}
		else if(startY == 2 && startX == 3)
			System.out.println("cant jump down left");
	}
	private void jumpDownRight(int startX, int startY, MoveList moves) {
		Pieces oldPiece;
		int xOff =  2;
		int yOff =  2;
		if(canJumpDownRight(startX, startY))
		{
			if(moves == null)
			{
				moves = new MoveList();
				moves.add(new Move(startX, startY));
			}
			else
			{
				moves = new MoveList(moves); // make a copy of it if it already exists
			}
			moves.add(new Move(startX+xOff, startY+yOff));
			// move piece and remove middle
			board.makeMove(startX, startY, startX+xOff, startY+yOff);
			oldPiece = board.removePiece(startX+(xOff/2), startY+(yOff/2));
			if(board.isKing(startX,  startY) == 0)
			{
				//if(!canJumpDownLeft(startX+xOff, startY+yOff) && !canJumpDownRight(startX+xOff, startY+yOff))
				//{ // if we can't keep jumping then we are done
					allMoves.add(moves);
			//	}
				//else
				//{
					jumpDownLeft (startX + xOff, startY+yOff, moves);
					jumpDownRight(startX + xOff, startY+yOff, moves);
				//}
			}
			else if(board.isKing(startX,  startY) == 1)
			{
				if(!canJumpUpLeft(startX + xOff, startY + yOff) && !canJumpUpRight(startX + xOff, startY + yOff) 
						&& canJumpDownLeft(startX + xOff, startY + yOff) && canJumpDownRight(startX + xOff, startY + yOff))
				{ // if we can't keep jumping then we are done
					allMoves.add(moves);
				}
				jumpUpLeft   (startX+xOff, startY+yOff, moves);
				jumpUpRight  (startX+xOff, startY+yOff, moves);
				jumpDownLeft (startX+xOff, startY+yOff, moves);
				jumpDownRight(startX-xOff, startY+yOff, moves);
			}
			board.makeMove(startX+xOff, startY+yOff, startX, startY);
			board.addPiece(oldPiece, startX+(xOff/2), startY+(yOff/2));
		}
	}
	public ArrayList<MoveList> getAllMoves() {
		// TODO Auto-generated method stub
		return allMoves;
	}
	public void setBoard(BetterBoard board2) {
		board = board2.clone();
	}
	public BetterBoard getBoard() {
		// TODO Auto-generated method stub
		return board.clone();
	}
	public void setTurn(boolean turn) {
		// TODO Auto-generated method stub
		this.playerTurn = turn;
	}

}
