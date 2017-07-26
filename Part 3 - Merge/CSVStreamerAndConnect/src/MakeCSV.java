import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.*;

public class MakeCSV 
{
	public static void main(String[] args) throws IOException
	{
		String csvfile = "/tmp/Kafka-Ignite/MessageMetadata.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csvfile), ',', '\0', CSVWriter.NO_ESCAPE_CHARACTER);
		
		String[] head = {"\"MID\"", "\"MsgType\"", "\"Status\"", "\"Speed\"", "\"TimeStamp\"", "\"SType\"", "\"CallSign\""};
		
		writer.writeNext(head);
		writer.close();
		
		csvfile = "/tmp/Kafka-Ignite/MessageData.csv";
		writer = new CSVWriter(new FileWriter(csvfile), ',', '\0', CSVWriter.NO_ESCAPE_CHARACTER);
		
		String pad = "MSG000000";
		String[] types = {"Type00", "Type01", "Type02", "Type03", "Type04", "Type05", "Type06", "Type07", "Type08", "Type09"};
		char[] alphanum = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
		
		
		char[] stype = new char[8];
		char[] csign = new char[5];
		String dd, yy, hh, mm,mon, z;
		
		for (int i = 0; i < 1000000; i++)
		{
			// Generate Message ID.
			String ii = Integer.toString(i);
			String id = pad.substring(0, 9-ii.length()) + ii;
			
			// Random Message type.
			int type_ind = (int)(Math.random()*10);
			
			// Random alphanumeric string for SType and CallSign.
			for (int j = 0; j < 8; j++)
			{
				if(j < 5)
					csign[j] = alphanum[(int)(Math.random()*36)];
				stype[j] = alphanum[(int)(Math.random()*36)];
			}
			
			// Generate Date in DTG format (DDHHMMZMONYY).
			dd = String.format("%02d", (int)(Math.random()*31 + 1));
			yy = String.format("%02d", (int)(Math.random()*18));
			hh = String.format("%02d", (int)(Math.random()*25));
			mm = String.format("%02d", (int)(Math.random()*61));
			mon = months[(int)(Math.random()*12)];
			z = Character.toString(alphanum[(int)(Math.random()*26 + 10)]);
			
			// Create String array with all properties.
			String[] msg = {id, types[type_ind], Integer.toString((int)(Math.random()*16)), Integer.toString((int)(Math.random()*151)), dd+hh+mm+z+mon+yy, new String(stype), new String(csign)};
			// Store values in CSV.
			writer.writeNext(msg);
		}
		
		writer.close();
	}
}
