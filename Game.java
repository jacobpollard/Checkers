import java.util.LinkedList;
public class Game
{
    static Game uniqueInstance;
    CheckerBoard board;
    boolean playerTurn;
    LinkedList<Pieces> playerJumps;
    LinkedList<Pieces> compJumps;
    private Game()
    {
        playerJumps = new LinkedList<Pieces>();
        compJumps = new LinkedList<Pieces>();
        board = new CheckerBoard();
        playerTurn = false;
    }
    static Game getInstance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }
    
    public boolean checkMove(int startX, int startY, int endX, int endY)
    {
        return( playerTurn && 
              ( isJump(startX, startY, endX, endY) ||
                isMove(startX, startY, endX, endY)));

            
        }
    private boolean isMove(int startX, int startY, int endX, int endY)
    {
        if(playerTurn && board.hasPlayerPiece(startX, startY))
        {
            
        }
    }
    private boolean isJump(int startX, int startY, int endX, int endY)
    {
        // TODO Auto-generated method stub
        return false;
    }
            
    }
}
