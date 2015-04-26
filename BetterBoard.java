

public class BetterBoard implements ConstVals
{
    public int board[];
    // first 4 bits are cpu normal pieces, next 4 are cpu kings and next 4 are player normal pieces, last 4 are cpu pieces
    byte cpuSize;
    byte playerSize;
    public BetterBoard()
    {
    	board = new int[4];
    	setPlayerSize(12);
    	setComputerSize(12);
    	setPlayerKings(0);
    	setComputerKings(0);
    	for(int x  = 0; x < 12; x++)
    	{
    		setComputerPiece(x);
    	}
    	for(int x = 12; x < 20; x++)
    	{
    		setEmptyPiece(x);
    	}
    	for(int x = 20; x < 32; x++)
    	{
    		setPlayerPiece(x);
    	}
    }
public BetterBoard(BetterBoard newBoard)
{
	board = new int[4];
	board[0] = newBoard.board[0];
	board[1] = newBoard.board[1];
	board[2] = newBoard.board[2];
	board[3] = newBoard.board[3];
	cpuSize = newBoard.cpuSize;
	playerSize = newBoard.playerSize;
}

private void setComputerKings(int i) {
		if(i <= 12)
		{
			cpuSize = (byte) Tools.setFourBits(cpuSize, i, 3);
		}
	}

private void setPlayerKings(int i)
{
	if(i <= 12)
	{
		playerSize = (byte) Tools.setFourBits(playerSize, i, 3);
	}
}

private void setPlayerPiece(int x) {
		// TODO Auto-generated method stub
		int index = x / 10;
		board[index] = Tools.setThreeBits(board[index], PLAYER, (x % 10) * 3);
		//System.out.printf("Set player piece: %x\n at index: %d, %d\n", Tools.getBits((x % 10) * 3, (x % 10) * 3 + 3, board[index]), board[index], x);
	}

private void setComputerPiece(int x) {
		// TODO Auto-generated method stub
		int index = x / 10;
		board[index] = Tools.setThreeBits(board[index], CPU, (x % 10) * 3);
		//System.out.printf("Set cpu piece: %x\n at index: %d, %d\n", Tools.getBits((x % 10) * 3, (x % 10) * 3 + 3, board[index]), board[index], x);
		
	}

private void setEmptyPiece(int x) {
		int index = x / 10;
		board[index] = Tools.setThreeBits(board[index], EMPTY, (x % 10) * 3);
		//System.out.printf("Set empty piece: %x\n at index: %d, %d\n", Tools.getBits((x % 10) * 3 - 3, (x % 10) * 3, board[index]), board[index], x);
		
	}

private void setComputerSize(int i) {
	if(i <= 12)
		cpuSize = (byte) Tools.setFourBits(cpuSize, i, 0);
	}

private void setPlayerSize(int i) {
	if(i <= 12)
		playerSize = (byte) Tools.setFourBits(playerSize, i, 0);
	}
public int getComputerSize() {
	
		return Tools.getBits(0, 3, cpuSize);
	}

public int getPlayerSize() {
		return Tools.getBits(0, 3, playerSize);
	}
public int getComputerKingSize() {
	
	return Tools.getBits(4, 7, cpuSize);
}

public int getPlayerKingSize() {
	return Tools.getBits(4, 7, playerSize);
}
	/*
 * This returns 1 if the space is empty 
 * 				0 if the space has a piece in it
 * 				and -1 if index was invalid
 * 
 */
    private boolean isLegal(int x, int y)
    {
    	return (x >= 0 && y >= 0 && x < 8 && y < 8);
    }
    public int getPositionVal(int x)
    {
    	int index = x /10;
    	return Tools.getBits((x % 10) * 3, (x % 10) * 3 + 2, board[index]);
    }
    public int isEmptySpace(int y, int x)
    {
    	int position = Move.convertTo1D(x, y);
    	if(isLegal(x, y))
    	{
    		
    		if (getPositionVal(position) == EMPTY)
    		{
    			return 1;
    	
    		}
    		else
    		{
    			return 0;
    		}
    	}
    	else
    	{
    		return -1;
    	}
    		
    }
    public int isKing(int x, int y)
    {
    	int position = Move.convertTo1D(x,  y);
    	if(!isLegal(x, y))
    	{
    		return -1;
    	}
    	else if(getPositionVal(position) == PLAYER_KING || getPositionVal(position) == CPU_KING)
    	{
    		return 1;
    	}
    	else
    	{
    		return 0;
    	}
    }
    public void makeMove(int startX, int startY, int endX, int endY)
    {
    	int pos1 = Move.convertTo1D(startX,  startY);
    	int pos2 = Move.convertTo1D(endX, endY);
    	int temp = getPositionVal(pos1);
    	setPositionVal(pos1, getPositionVal(pos2));
    	setPositionVal(pos2, temp);
    }

    private void setPositionVal(int pos, int temp) {
    	int index = pos / 10;
		board[index] = Tools.setThreeBits(board[index], temp, (pos % 10) * 3);
    }

	public PieceTypes getPieceType(int x, int y)
    {
		int position = Move.convertTo1D(x, y);
    	if(!isLegal(x, y))
    	{
    		return PieceTypes.INVALID;
    	}
    	else if(isEmptySpace(y, x) == 1)
    	{
    		return PieceTypes.EMPTY_SPACE;
    	}
    	else if(isCompPiece(x, y) == 1)
    	{
    		return PieceTypes.COMP_PIECE;
    	}
    	else
    	{
    		return PieceTypes.PLAYER_PIECE;
    	}
    	
    }
    private int isCompPiece(int x, int y) {
		// TODO Auto-generated method stub
    	int position = Move.convertTo1D(x,  y);
    	if(!isLegal(x, y))
    	{
    		return -1;
    	}
    	else if(getPositionVal(position) == CPU || getPositionVal(position) == CPU_KING)
    	{
    		return 1;
    	}
    	else
    	{
    		return 0;
    	}
	}

	public Pieces removePiece(int x, int y)
    {
		if(!isLegal(x, y))
		{
			return null;
		}
		int position = Move.convertTo1D(x,  y);
		int val = getPositionVal(position);
    	Pieces piece = new Pieces(val);
		setPositionVal(position, EMPTY);
    	if(piece.isCompPiece())
    	{
    		if(piece.isKing())
    		{
    			decrementCpuKingCount();
    		}
    		else
    		{
    			decrementCpuCount();
    		}
    	}
    	else
    	{
    		if(piece.isKing())
    		{
    			decrementPlayerKingCount();
    		}
    		else
    		{
    			decrementPlayerCount();
    		}
    	}
    	return piece;
    }
    private void decrementCpuKingCount() {
		// TODO Auto-generated method stub
		cpuSize -= 0x10;
	}

	private void decrementCpuCount() {
		// TODO Auto-generated method stub
		cpuSize--;
	}

	private void decrementPlayerKingCount() {
		// TODO Auto-generated method stub
		playerSize -= 0x10;
	}

	private void decrementPlayerCount() {
		// TODO Auto-generated method stub
		playerSize--;
	}
	private void incrementCpuKingCount() {
			// TODO Auto-generated method stub
		cpuSize += 0x10;
	}

	private void incrementCpuCount() {
		// TODO Auto-generated method stub
		cpuSize++;
	}

	private void incrementPlayerKingCount() {
			// TODO Auto-generated method stub
		playerSize += 0x10;
	}

	private void incrementPlayerCount() {
		// TODO Auto-generated method stub
		playerSize++;
	}



	public void addPiece(int x, int y, boolean isKinged, int status)
    {
    	Pieces piece = new Pieces(status);
    	if(piece.isCompPiece())
    	{
    		if(piece.isKing())
    		{
    			incrementCpuCount();
    		}
    		else
    		{
    			incrementCpuKingCount();
    		}
    	}
    	else
    	{
    		if(piece.isKing())
    		{
    			incrementPlayerCount();
    		}
    		else
    		{
    			incrementPlayerKingCount();
    		}
    	}
    	if(isKinged == true)
    	{
    		piece.KingMe();
    	}
    }

    /**
     * Returns an array of pieces containing the pieces in the following
     * locations/indices relative to the specified piece.
     * 
     * |5|_|_|_|4| 
     * |_|1|_|0|_| 
     * |_|_|X|_|_| 
   
   
   
    /**
     * Prints the state of the board.
     */
    public void printBoard()
    {
    	System.out.print(" ");
    	for(int x = 0; x < 8; x++)
    	{
    		System.out.print("|"+x);
    	}
    	System.out.println();
        int i, j;
        for (i = 0; i < 8; i++)
        {
            System.out.print(i +"|");
            for (j = 0; j < 8; j++)
            {
            	if(i % 2 != j % 2)
            	{
            		System.out.print("_");
            	}
            	else if (isEmptySpace(j, i) == 1)
                {
                    System.out.print("_");
                }
                else if (isCompPiece(i, j) == 1)
                {
                	if(isKing(i, j) == 1)
                	{
                		System.out.print("C");
                	}
                	else
                	{
                		System.out.print("c");
                	}
                }
                else
                {
                	if(isKing(i, j) == 1)
                	{
                		System.out.print("P");
                	}
                	else
                	{
                		System.out.print("p");
                	}
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

	public Pieces getPiece(int i, int j) {
		return new Pieces(getPositionVal(Move.convertTo1D(i, j)));
	}

	public void addPiece(Pieces oldPiece, int i, int j) {
		// TODO Auto-generated method stub
		setPositionVal(Move.convertTo1D(i,  j), oldPiece.getState());
	}

	public void kingPiece(int x, int y) {
		int val = getPositionVal(Move.convertTo1D(x,  y));
		if(val == PLAYER)
		{
			val = PLAYER_KING;
		}
		else if(val == CPU)
		{
			val = CPU_KING;
		}
		else
		{
			System.out.println("Can't king space " + x +", " + y);
			return;
		}
		setPositionVal(Move.convertTo1D(x, y), val);
	}
	public static void main(String args[])
	{
		BetterBoard x = new BetterBoard();
		x.printBoard();
	}

	
	public BetterBoard clone()
	{
		return new BetterBoard(this);
	}

}

