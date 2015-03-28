

public class Move {
int startX;
int startY;

static int convertTo1D(int x, int y)
{
	int count = 0;
	int start = 1;
	for(int row = 0; row < 8; row++)
	{
		for(int col = start; col < 8; col+=2)
		{
			if(row == x && col == y)
			{
				return count;
			}
			count++;
		}
		start++;
		start = start % 2;
	}
	return -1;
}
public Move(int startX, int startY)
{
	this.startX = startX;
	this.startY = startY;

		
}
}
