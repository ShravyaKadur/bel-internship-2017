import java.sql.*;

public class GenMessage
{
	public static void main(String[] args)
	  {
	    try
	    {
	      // create a mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/bel";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      // the mysql insert statement
	      String query = " insert into message (mid, msg_type, status, speed, time_stamp, s_type, call_sign)"
	        + " values (?, ?, ?, ?, ?, ?, ?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, "TEST12345");
	      preparedStmt.setString (2, "Type00");
	      preparedStmt.setInt (3, 0);
	      preparedStmt.setInt (4, 20);
	      preparedStmt.setString (5, "DDHHMMZMONYY");
	      preparedStmt.setString (6, "Stypestr");
	      preparedStmt.setString (7, "Csign");

	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }
}
