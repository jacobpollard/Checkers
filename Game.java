
public class Game
{
    static Game uniqueInstance;
    private CheckerBoard board;
    private boolean playerTurn;

    private Game()
    {
        board = new CheckerBoard();
        playerTurn = false;
    }

    static Game getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

    public boolean checkMove(int startX, int startY, int endX, int endY)
    {
        return (playerTurn && (canJump(startX, startY) || isMove(startX,
            startY, endX, endY)));
    }

    private boolean isMove(int startX, int startY, int endX, int endY)
    {
        if (playerTurn && board.hasPlayerPiece(startX, startY))
        {

        }
    }

    private boolean getJumps()
    {

    }

    // forward is moving normal for a computer
    private boolean canJumpBackward(int startX, int startY)
    {
        Pieces[] around = board.getSurrounds(startX, startY);
        boolean startType = board.getPiece(startX, startY).isCompPiece();

        if (around[0] != null && around[4] != null)
        {
            if (around[0].isCompPiece() != startType && around[4].isEmpty())
            {
                return true;
            }
        }
        else if (around[1] != null && around[5] != null)
        {

            if (around[1].isCompPiece() != startType && around[5].isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    private boolean canJumpForward(int startX, int startY)
    {
        Pieces[] around = board.getSurrounds(startX, startY);
        boolean startType = board.getPiece(startX, startY).isCompPiece();

        if (around[3] != null && around[7] != null)
        {
            if (around[3].isCompPiece() != startType && around[7].isEmpty())
            {
                return true;
            }
        }
        else if (around[2] != null && around[6] != null)
        {

            if (around[2].isCompPiece() != startType && around[6].isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    private boolean canJumpPPiece(int startX, int startY)
    {
        if (canJumpBackward(startX, startY))
        {
            return true;
        }
        if (board.getPiece(startX, startY).isKing())
        {
            return (canJumpForward(startX, startY));
        }
    }

}
