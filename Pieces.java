public class Pieces implements ConstVals
{
    int pieceState; // 0 is empty space, 1 is player piece, 2 is computer piece,
                    // 3 is king player piece, 4 is king computer piece

    public Pieces(int state)
    {
        pieceState = state;
    }

    public void KingMe()
    {
        if (pieceState == PLAYER)
        {
            pieceState = PLAYER_KING;
        }
        else if (pieceState == CPU)
        {
            pieceState = CPU_KING;
        }
        else if (pieceState == 0)
        {
            System.out.println("No piece.");
        }
        else
        {
            System.out.println("Already kinged.");
        }

    }
    public boolean isKing()
    {
        return (pieceState == CPU_KING || pieceState == PLAYER_KING);
    }

    public boolean isCompPiece()
    {
        return (pieceState == CPU_KING || pieceState == CPU);
    }

    public boolean isEmpty()
    {
        return pieceState == EMPTY;
    }

	public int getState() {
		// TODO Auto-generated method stub
		return pieceState;
	}

}
