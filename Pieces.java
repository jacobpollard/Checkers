public class Pieces
{
	private boolean isKing;
	private boolean CompPiece;
	
	public Pieces(boolean c)
	{
		CompPiece = c;
		isKing = false;	
	}
	
	public void KingMe()
	{
		isKing = true;
	}
	
	public boolean isKing()
	{
		return isKing;
	}
	
	public boolean isCompPiece()
	{
		return CompPiece;
	}
}
