import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;


public class PutMessageIgnite 
{
	public static void main(String args[])
	{
		Ignite ignite = Ignition.start("/tmp/Ignite-Kafka/beans.xml");
		 
		// Get an instance of named cache.
		
		//IgniteCache<String, Message> cache = ignite.getOrCreateCache("msgCache");
		CacheConfiguration<String, Message> cfg = new CacheConfiguration<String, Message>("msgCache");
		cfg.setIndexedTypes(String.class, Message.class);
		IgniteCache<String, Message> cache = ignite.getOrCreateCache(cfg);
		
		Message m;
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
			
			// Create Message object with all properties generated.
			m = new Message(types[type_ind], (int)(Math.random()*16), (int)(Math.random()*151), dd+hh+mm+z+mon+yy, new String(stype), new String(csign));
			// Store keys in cache.
			cache.put(new String(id), m);
		}
		
		/*		
		// Retrieve values from cache.
		for (int i = 0; i < 100; i++)
		{
			String ii = Integer.toString(i);
			String id = pad.substring(0, 9-ii.length()) + ii;
		    System.out.println("Got [key=" + id + ", val=" + cache.get(id).printAll() + ']');
		}
		 */
		
		System.out.println("DONE.");
		
	}	
}
