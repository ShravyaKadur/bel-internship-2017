import java.net.*;
import java.io.*;

import com.opencsv.*;

public class WriteToUDP 
{
	public static void main(String[] args) throws IOException
	{
		DatagramSocket sock = null;
		// Port number for UDP
		int port = 35352;
		String s;
		
		String csvfile = "/home/shravya/BEL/MessageData.csv";
		//CSVReader csvReader = new CSVReader(new FileReader(csvfile));
		BufferedReader br = new BufferedReader(new FileReader(csvfile));
		//String[] row = null;
		
		try
		{
			sock = new DatagramSocket();
			// Initialize host
			InetAddress host = InetAddress.getByName("localhost");
			
			while((s = br.readLine()) != null)
			{
				byte[] b = s.getBytes();	// Convert message to byte stream
				
				// Create packet and send
				DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
				sock.send(dp);
			}
			br.close();
		}
		
		catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
	}
}

