import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.Ignition;
import java.util.List;

public class QueryMessages2
{
	public static void main(String args[])
	{
		Ignite ignite = Ignition.start("/home/shravya/workspace/IgniteWorkspace/beans.xml");
		
		// Get an instance of named cache.
		IgniteCache<String, Message> cache = ignite.cache("msgCache");
		
		// Count number of each type of message in the year '15
		SqlFieldsQuery sql = new SqlFieldsQuery("select count(timeStamp), msgType from Message where timeStamp like '%15' group by msgType");
		
		
		try (QueryCursor<List<?>> cursor = cache.query(sql))
		{
			System.out.println("MsgType, Count");
			for (List<?> row : cursor)
			    System.out.println(row.get(1) +", "+ row.get(0));
		}
		
		System.out.println("End.");
	}
}
