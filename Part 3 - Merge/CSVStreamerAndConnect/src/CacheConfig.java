import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;

public class CacheConfig 
{
	public static void main(String args[])
	{
		Ignition.start("/tmp/Ignite-Kafka/beans.xml");
		 
		// Get an instance of named cache.
		
		//IgniteCache<String, Message> cache = ignite.getOrCreateCache("msgCache");
		CacheConfiguration<String, Message> cfg = new CacheConfiguration<String, Message>("connCache");
		cfg.setIndexedTypes(String.class, Message.class);
	}
}
