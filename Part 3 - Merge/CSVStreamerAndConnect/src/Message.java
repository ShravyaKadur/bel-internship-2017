import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class Message implements Serializable
{
	@QuerySqlField
	private String msgType;
	@QuerySqlField
	private int status;
	@QuerySqlField
	private int speed;
	@QuerySqlField
	private String timeStamp;
	@QuerySqlField
	private String sType;
	@QuerySqlField
	private String callSign;
	
	Message(String msgType, int status, int speed, String timeStamp, String sType, String callSign)
	{
		this.msgType = msgType;
		this.status = status;
		this.speed = speed;
		this.timeStamp = timeStamp;
		this.sType = sType;
		this.callSign = callSign;
	}
	
	String getMsgType()
	{
		return this.msgType;
	}
	
	int getStatus()
	{
		return this.status;
	}
	
	int getSpeed()
	{
		return this.speed;
	}
	
	String getTimeStamp()
	{
		return this.timeStamp;
	}
	
	String getSType()
	{
		return this.sType;
	}
	
	String getCallSign()
	{
		return this.callSign;
	}
	
	String printAll()
	{
		return this.msgType + ", " +this.status + ", " +this.speed + ", " +this.timeStamp + ", " +this.sType + ", " +this.callSign;
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
