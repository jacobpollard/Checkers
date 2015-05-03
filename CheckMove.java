import java.util.ArrayList;


public class CheckMove {
	Game game;
	Game manipulateGame;
	BetterBoard currentState;
	boolean currentTurn;
	ArrayList<BetterBoard> nextStates;
	public CheckMove()
	{
		game = Game.getInstance();
		manipulateGame = Game.getManipulateInstance();
		currentState = game.getBoard();
		currentTurn = game.getTurn();
		produceNextStates();
	}
	private void produceNextStates() {
		nextStates = new ArrayList<BetterBoard>();
		ArrayList<MoveList> moves = game.getAllMoves();
		for(int x = 0; x < moves.size(); x++)
		{
			resetState();
			manipulateGame.makeMove(x);
			BetterBoard nextBoard = manipulateGame.getBoard();
			nextStates.add(nextBoard);
		}
	}
	private void resetState() {
		// TODO Auto-generated method stub
		manipulateGame.setBoard(currentState);
		manipulateGame.setTurn(currentTurn);
	}
	public boolean checkIfValid(BetterBoard board)
	{
		for(BetterBoard possibleBoard : nextStates)
		{
			if(possibleBoard.equals(board))
			{	// if inside the if statement, then the move was legal and we proceed!
				currentTurn = !currentTurn;
				currentState = board;
				game.setTurn(currentTurn);
				game.setBoard(board);
				produceNextStates();
				 
				return true;
			}
		}
		return false;
	}
}
