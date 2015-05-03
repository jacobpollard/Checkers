import java.util.ArrayList;


public class Computer {
	Node tree;
	public Computer()
	{
		Game game = Game.getInstance();
		//tree = new Node(game.getBoard(), 4, true);
	}
	public int minimax(Node n, int depth, boolean player)
	{
		
		if(depth == 0)
		{
			return n.getValue();
		}
		int best;
		if(player)
		{
			best = -999;
			for(Node c : n.getNextNodes())
			{
				int val = minimax(c, depth - 1, !player);
				best = max(best, val);
			}
			return best;
		}
		else
		{
			best = 999;
			for(Node c : n.getNextNodes())
			{
				int val = minimax(c, depth - 1, !player);
				best = min(best, val);
			}
			return best;
		}
	}

	private int min(int best, int val) {
		if(best < val)
		{
			return best;
		}
		else
		{
			return val;
		}
	}
	private int max(int best, int val) {
		if(best > val)
		{
			return best;
		}
		else
		{
			return val;
		}
	}
	public int getMove() 
	{
		int best = -1;
		int bestVal = -999;
		Game game = Game.getInstance();
		//ArrayList<MoveList> allMoves = game.getAllMoves();
		tree = new Node(game.getBoard(), 6, false);
		ArrayList<Node> moveVals = tree.getNextNodes();
		for(int i = 0; i < moveVals.size(); i++)
		{
			int temp = minimax(moveVals.get(i), 4, false);
			System.out.println("Move i: " + i + " had a value of " + temp);
			if(temp > bestVal)
			{
				best = i;
				bestVal = temp;
			}
		}
		System.out.println("chose move "+best);
		return best;
	}
	public MoveList getMoveAsMoveList() {
		int x = getMove();
		Game game = Game.getInstance();
		ArrayList<MoveList> moves = game.getAllMoves();
		return moves.get(x);
		
	}
}
