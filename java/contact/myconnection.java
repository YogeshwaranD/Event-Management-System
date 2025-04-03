package contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.DriverManager;

public class myconnection {
 public static java.sql.Connection getConnection()
    {
        java.sql.Connection con=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/eventmangement","root","root");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return con;
    }

}
