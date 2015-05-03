

public class Move {
int startX;
int startY;
boolean isKing;
static int convertTo1D(int x, int y)
{
	return 4*x + y - (y/2 + y % 2);
}
static int convertTo1DOther(int x, int y)
{
	return 31 - (4*x + y - (y/2 + y % 2));
}
public Move(int startX, int startY)
{
	this.startX = startX;
	this.startY = startY;
	isKing = false;
		
}
public String toString()
{
	return "(" + startX + ", " + startY + ")";
}
public int getX() {
	// TODO Auto-generated method stub
	return startX;
}
public int getY()
{
	return startY;
}
public static int convertTo1D(Move move) {
	int x = move.getX();
	int y = move.getY();
	return 4*x + y - (y/2 + y % 2);
}
public static int convertTo1DOther(Move move) {
	// TODO Auto-generated method stub
	int x = move.getX();
	int y = move.getY();
	return 31 - (4*x + y - (y/2 + y % 2));
}
public void makesKing() {
	// TODO Auto-generated method stub
	isKing = true;
}
public boolean isKing()
{
	return isKing;
}
}
