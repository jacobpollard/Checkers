public class CheckerBoard
{
    public Pieces[][] board;

    public CheckerBoard()
    {
        int i, j;
        board = new Pieces[8][8];

        for (i = 0; i < 3; i++)
        {
            for (j = 1; i < 8; i += 2)
            {
                Pieces fuckyou = new Pieces(true);
                Pieces fuckme = new Pieces(false);
                board[i][j] = fuckyou;
                board[i + 5][j] = fuckme;
            }
        }
    }

}
