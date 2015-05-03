
public class Main {
CheckMove moveState;
JavaClient tcpClient;
Computer aiAgent;
Game game;
boolean playerTurn;
	public Main()
	{
		moveState = new CheckMove();
		tcpClient = new JavaClient();
		aiAgent = new Computer();
		game = Game.getInstance();
		playerTurn = !tcpClient.readPlayerTurn();
		mainLoop();
	}
	private void mainLoop() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			if(playerTurn)
				tcpClient.writeMessage("1");
			else
				tcpClient.writeMessage("0");
			
			if(playerTurn)
			{
				BetterBoard boardState = tcpClient.readGameState();
				if(moveState.checkIfValid(boardState))
				{
					tcpClient.writeMessage("GOOD");
					playerTurn = !playerTurn;
				}
				else
				{
					tcpClient.writeMessage("BAD");
				}
			}
			else
			{
				MoveList move = aiAgent.getMoveAsMoveList();
				String message = move.convertToSendableMoveList();
				tcpClient.writeMessage(message);
				playerTurn = !playerTurn;
			}
		}
	}
	public static void main(String args[])
	{
		Main x = new Main();
	}

}
