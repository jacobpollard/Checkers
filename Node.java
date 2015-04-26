import java.util.ArrayList;




public class Node {
	private ArrayList<Node> nextNodes;
	private int moveNo;
	private BetterBoard board;
	private int value;
	boolean turn;
	public Node(BetterBoard board, int depth, boolean turn)
	{
		this.turn = turn;
		this.board = board;
		nextNodes = new ArrayList<Node>();
		evaluate();
		if(depth > 0)
		{
			depth--;
			Game game = Game.getManipulateInstance();
			game.setBoard(this.board);
			game.setTurn(turn);
			game.getMoves();
			ArrayList<MoveList> allMoves = game.getAllMoves();
			for(int x = 0; x < allMoves.size(); x++)
			{
				game.makeMove(x);
				BetterBoard newBoard = game.getBoard();
				Node newNode = new Node(newBoard, depth, !turn, x);
				nextNodes.add(newNode);
				game.setBoard(this.board);
				game.setTurn(turn);
			}
		}
	}
	public Node(BetterBoard board, int depth, boolean turn, int move)
	{
		this.moveNo = move;
		this.turn = turn;
		this.board = board;
		nextNodes = new ArrayList<Node>();
		evaluate();
		if(depth > 0)
		{
			depth--;
			Game game = Game.getManipulateInstance();
			game.setBoard(this.board);
			game.setTurn(turn);
			game.getMoves();
			boolean f = true;
			ArrayList<MoveList> allMoves = game.getAllMoves();
			for(int x = 0; x < allMoves.size(); x++)
			{
				if(game.makeMove(x))
				{
					if(f)
					{
						//System.out.println("*****************\n"+ allMoves.get(x).toString());
						f = false;
					}
					BetterBoard newBoard = game.getBoard();
					Node newNode = new Node(newBoard, depth, !turn, x);
					nextNodes.add(newNode);
					
				}
				game.setBoard(this.board);
				game.setTurn(turn);
			}
		}
	}
	public ArrayList<Node> getNextNodes() {
		return nextNodes;
	}
	public void setNextNodes(ArrayList<Node> nextNodes) {
		this.nextNodes = nextNodes;
	}
	public BetterBoard getBoard() {
		return board;
	}
	public void setBoard(BetterBoard board) {
		this.board = board;
	}
	public int getValue() {
		return value;
	}
	public void evaluate()
	{
		value = 0;
		value = board.getComputerSize() - board.getPlayerSize(); // we want to maximize this
		//value += board.getComputerKingSize() - board.getPlayerKingSize();
	}
	
}
