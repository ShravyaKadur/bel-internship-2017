import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class MessageWithKey implements Serializable
{
	@QuerySqlField
	private String mid;
	private Message m;
	
	MessageWithKey(String mid, Message m)
	{
		this.mid = mid;
		this.m = m;
	}
	
	String getMID()
	{
		return this.mid;
	}
	
	byte[] getBytes() throws IOException
	{
		try(ByteArrayOutputStream b = new ByteArrayOutputStream())
		{
			try(ObjectOutputStream o = new ObjectOutputStream(b))
			{
				o.writeObject(this);
			}
			return b.toByteArray();
		}
	}
}
