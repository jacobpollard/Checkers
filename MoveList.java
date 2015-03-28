import java.util.ArrayList;


public class MoveList {
ArrayList<Move> moves;
	public MoveList(Move move)
	{
		moves = new ArrayList<Move>();
		moves.add(move);
	}
	public MoveList()
	{
		moves = new ArrayList<Move>();
	}
	public MoveList(MoveList moves2)
	{
		// TODO Auto-generated constructor stub
		moves = new ArrayList<Move>(moves2.moves);
	}
	public void addMove(int x, int y)
	{
		moves.add(new Move(x, y));
	}

}
