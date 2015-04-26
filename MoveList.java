import java.util.ArrayList;




public class MoveList {
final public int REMOVE_PIECE = 64;
final public int FLIP_PIECE = 65;
final public int CLOSE_CLAW = 66;
final public int OPEN_CLAW = 67;
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
		// TODO Auto-generated method stub
		return moves.size();
	}
	public String convertToSendableMoveList()
	{
		String str ="";
		if(isJump())
		{
			for(int i = 0; i < moves.size(); i++)
			{
				str += Move.convertTo1D(moves.get(i)) * 2 + " " 
					+  Move.convertTo1D(moves.get(i)) * 2 + 1 +" "
					+  CLOSE_CLAW +" "
					+  Move.convertTo1D(moves.get(i)) * 2;// Picks the piece up
				
				
				str += Move.convertTo1D(moves.get(i+1)) * 2 + " "
					+  Move.convertTo1D(moves.get(i+1)) * 2 + 1 +" "
					+  OPEN_CLAW +" "
					+  Move.convertTo1D(moves.get(i+1)); // puts the piece down
				
				
				int removeX = (moves.get(i).getX() + moves.get(i+1).getX())/2;
				int removeY = (moves.get(i).getY() + moves.get(i+1).getY())/2;
				int coord = Move.convertTo1D(removeX, removeY);
				
				str += coord * 2 + " " 
					+  coord * 2 + 1 + " "
					+  REMOVE_PIECE + " "
					+  OPEN_CLAW;
				
			}
		}
		else
		{
			str += Move.convertTo1D(moves.get(0)) * 2 + " " 
					+  Move.convertTo1D(moves.get(0)) * 2 + 1 +" "
					+  CLOSE_CLAW +" "
					+  Move.convertTo1D(moves.get(0)) * 2;// Picks the piece up
			
			
			str += Move.convertTo1D(moves.get(1)) * 2 + " "
					+  Move.convertTo1D(moves.get(1)) * 2 + 1 +" "
					+  OPEN_CLAW +" "
					+  Move.convertTo1D(moves.get(1)); // puts the piece down
		}
		
		return str;
	}

}
