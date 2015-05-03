import java.util.ArrayList;




public class MoveList {
final public int REMOVE_PIECE = 32;
final public int KING_PIECE = 33;

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
	public void add(Move move) {
		// TODO Auto-generated method stub
		moves.add(move);
	}
	public String toString()
	{
		String str = "";
		for(Move m: moves)
		{
			str += m.toString()+"-";
		}
		return str;
	}
	public Move get(int x) {
		// TODO Auto-generated method stub
		return moves.get(x);
	}
	public boolean isJump()
	{
		return (Math.abs(moves.get(0).getX() - moves.get(1).getX()) != 1);
	}
	public int size() {
		return moves.size();
	}
	public String convertToSendableMoveList()
	{
		String str ="";
		if(isJump())
		{
			for(int i = 0; i < moves.size(); i++)
			{
				str += Move.convertTo1DOther(moves.get(i)) + ",";
				str += Move.convertTo1DOther(moves.get(i+1))+",";
				int removeX = (moves.get(i).getX() + moves.get(i+1).getX())/2;
				int removeY = (moves.get(i).getY() + moves.get(i+1).getY())/2;
				int coord = Move.convertTo1DOther(removeX, removeY);
				
				str += coord
					+  REMOVE_PIECE;
				if(i+1 < moves.size())
				{
					str += ",";
				}
			}
			if(moves.get(moves.size()-1).isKing()) // if the last move made a piece a king then king the piece
			{
				str += Move.convertTo1DOther(moves.get(moves.size()-1)) + ",";
				str += KING_PIECE;
			}
		}
		else // if the move is just a regular move
		{
			str += Move.convertTo1DOther(moves.get(0)) +","
				+  Move.convertTo1DOther(moves.get(1));// Move piece to the next location
			if(moves.get(1).isKing()) // king the piece if appropriate
			{
				str += Move.convertTo1DOther(moves.get(1)) +"," + KING_PIECE;
			}
			
			
		}
		
		return str;
	}

}
