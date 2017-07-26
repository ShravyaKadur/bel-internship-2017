import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.Ignition;
import java.util.List;

public class QueryMessages2
{
	public static void main(String args[])
	{
		Ignite ignite = Ignition.start("/tmp/Ignite-Kafka/beans.xml");
		
		// Get an instance of named cache.
		//IgniteCache<String, Message> cache = ignite.cache("msgCache");
		CacheConfiguration<String, Message> cfg = new CacheConfiguration<String, Message>("msgCache");
		cfg.setIndexedTypes(String.class, Message.class);
		IgniteCache<String, Message> cache = ignite.getOrCreateCache(cfg);
		
		// Count number of each type of message in the year '15
		//SqlFieldsQuery sql = new SqlFieldsQuery("select count(timeStamp), msgType from Message where timeStamp like '%15' group by msgType");
		
		//
		SqlFieldsQuery sql = new SqlFieldsQuery("select m1.sType, m2.sType from Message m1 where m1.timeStamp like (select m2.timeStamp from Message m2)");
		
		
		try (QueryCursor<List<?>> cursor = cache.query(sql))
		{
			//System.out.println("MsgType, Count");
			System.out.println("m1.sType, m2.sType");
			
			for (List<?> row : cursor)
			    System.out.println(row.get(0) +", "+ row.get(1));
		}
		
		System.out.println("End.");
	}
}
