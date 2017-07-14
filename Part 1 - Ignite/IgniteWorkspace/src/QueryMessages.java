import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.Ignition;
import javax.cache.Cache.Entry;

public class QueryMessages
{
	public static void main(String args[])
	{
		Ignite ignite = Ignition.start("/home/shravya/workspace/IgniteWorkspace/beans.xml");
		
		// Get an instance of named cache.
		IgniteCache<String, Message> cache = ignite.cache("msgCache");
		
		// Get all messages.
		// SqlQuery sql = new SqlQuery(Message.class, "select * from Message");
		
		// Get 10 messages of Type00
		//SqlQuery sql = new SqlQuery(Message.class, "select * from Message where msgType='Type00' limit 10");
		
		// 5 fastest messages with status 5.
		SqlQuery sql = new SqlQuery(Message.class, "select * from Message where status=5 order by speed desc limit 5");
		
		//SqlQuery sql = new SqlQuery(Message.class, "select * from Message where speed=150 union select * from Message where speed=0 order by speed asc limit 10");
		
		try (QueryCursor<Entry<String, Message>> cursor = cache.query(sql))
		{
			System.out.println("MID, MsgType, Status, Speed, Time Stamp, SType, CallSign");
			for (Entry<String, Message> e : cursor)
			    System.out.println(e.getKey() +", "+ e.getValue().printAll());
		}
		
		System.out.println("End.");
	}
}
