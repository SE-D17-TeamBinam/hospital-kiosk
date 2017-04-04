import java.sql.*;

/**
 * Registers, Connects to, and sends commands to a database
 * Created by Evan on 4/3/2017.
 */
public class DatabaseController{
  private Connection conn = null;
  private Statement stmt = null;
  private String url = "";
  private String driver = "";

  DatabaseController(String _driver, String _url){
    this.url = _url;
    this.driver =  _driver;
    try {
      this.registerDriver();
      this.connect();
    }
    catch (SQLException e){
      e.printStackTrace();
      System.out.println("SQL Exception Occurred, check that the url is correct");
    }
    catch (ClassNotFoundException e){
      e.printStackTrace();
      System.out.println("Class Not Found Exception Occurred, check that the driver is correct and installed");
    }
  }

  ResultSet send_Command(String command){
    ResultSet rs;
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(command);
      return rs;

    }
    catch (SQLException e){
      System.out.println("Error Querying, Trying Execute...");
      try {
        stmt.execute(command);
        System.out.println("Executed Successfully");

      }
      catch (SQLException e2){
        e.printStackTrace();
        System.out.println("Query Error!");
      }
    }
    return null;
  }

  private boolean registerDriver() throws ClassNotFoundException{
    Class.forName(driver);
    return true;
  }

  private boolean connect() throws SQLException{
    conn = DriverManager.getConnection(url);
    return true;
  }

  @Override
  public String toString(){
    if (url.length() > 0)
      return "DatabaseController connected to " + url;
    return "Unconnected DatabaseController";
  }

}
