public class Pieces
{
    int pieceState; // 0 is empty space, 1 is player piece, 2 is computer piece,
                    // 3 is king player piece, 4 is king computer piece

    public Pieces(int state)
    {
        pieceState = state;
    }

    public void KingMe()
    {
        if (pieceState == 1)
        {
            pieceState = 3;
        }
        else if (pieceState == 2)
        {
            pieceState = 4;
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
        return (pieceState == 3 || pieceState == 4);
    }

    public boolean isCompPiece()
    {
        return (pieceState == 4 || pieceState == 2);
    }

    public boolean isEmpty()
    {
        return pieceState == 0;
    }

}
