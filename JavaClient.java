import java.io.*;
import java.net.*;
 
public class JavaClient {
	Socket socket;
    PrintWriter out;
    BufferedReader in;
	public JavaClient()
    {
        
 
        try {
        	socket = new Socket("127.0.0.1", 1234);
        	socket.setTcpNoDelay(true);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	} catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }
		//System.out.println(in.readLine()); //Uncomment to debug
 
	public void writeMessage(String message)
	{
		out.println(message);
	}
	public boolean readPlayerTurn()
	{
		String val = "";
        try 
        {	
    		 val = in.readLine();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
		if(val == "1")
		{
			writeMessage("GOOD");
			return true;
		}
		else if(val == "0")
		{
			writeMessage("GOOD");
			return false;
		}
		else
		{
			System.out.println("The turn was improperly sent");
			System.exit(0);
			return false;
		}
	}
	public BetterBoard readGameState()
	{
		String val ="";
		try {
			val = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int position = 31;
		int arr[] = new int[32];
		while(val.contains(","))
		{
			arr[position] = Integer.parseInt(val);
			int index = val.indexOf(',');
			val = val.substring(index);
			position--;
		}
		return new BetterBoard(arr);
	}
	public void endComm()
	{
		out.close();
		try {
			socket.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
