import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Database Editor Abstracts the SQL Away from the rest of the program by providing methods to add /
 * remove / edit elements of the database directly in java methods Created by Evan on 4/3/2017.
 */
public class DatabaseEditor {

  DatabaseController dbc;

  DatabaseEditor(DatabaseController _dbc) {
    this.dbc = _dbc;
  }

  ///////////////////////////
  ///////// Service /////////
  ///////////////////////////

  ///////////////////////////
  /////// Physician /////////
  ///////////////////////////

  boolean removePhysician(String first_name, String last_name, String title) {
    dbc.send_Command(
        "delete from physician (first_name, last_name, title) values (\"" + first_name + "\",\""
            + last_name + "\",\"" + title + "\")");
    return true;
  }

  boolean addPhysician(String first_name, String last_name, String title) {
    dbc.send_Command(
        "insert into physician (first_name, last_name, title) values (\"" + first_name + "\",\""
            + last_name + "\",\"" + title + "\")");
    return true;
  }

  ArrayList<Physician> getAllPhysicians() throws SQLException {
    ArrayList<Physician> physicians = new ArrayList<Physician>();
    ResultSet res = dbc.send_Command("select * from physician").get(0);
    while (res.next()) {
      String first_name = res.getString("FIRST_NAME");
      String last_name = res.getString("LAST_NAME");
      String title = res.getString("TITLE");
      long pid = res.getLong("PID");

      Physician p = new Physician(first_name, last_name, title, pid, new ArrayList<Point>());
      physicians.add(p);
    }
    return physicians;
  }

  ///////////////////////////
  //////// Location /////////
  ///////////////////////////

  ///////////////////////////
  //// Location - Service ///
  ///////////////////////////

  ///////////////////////////
  /// Location - Physician //
  ///////////////////////////

  boolean addPhysicianLocation(String first_name, String last_name, String title,
      String location_name) {
    dbc.send_Command(
        "insert into P_Location (location_id, physician_id) values (select pid from physician where first_name = \""
            + first_name + "\" and last_name = \"" + last_name + "\" and title = \"" + title
            + "\", select lid from location where name = \"" + location_name + "\");\n");
    return true;
  }

  boolean removePhysicianLocation(String first_name, String last_name, String title,
      String location_name) {
    dbc.send_Command(
        "delete from P_Location (location_id, physician_id) values (select pid from physician where first_name = \""
            + first_name + "\" and last_name = \"" + last_name + "\" and title = \"" + title
            + "\", select lid from location where name = \"" + location_name + "\");\n");
    return true;
  }

  ///////////////////////////
  ///////// Points //////////
  ///////////////////////////

  boolean addPoint(Point point) {
    dbc.send_Command("insert into point (pid,x,y,name) values (" + point.id + "," + point.xCoord + "," + point.yCoord + ",\"" + point.name + "\");");
    return true;
  }

  boolean removePoint(Point point) {
    dbc.send_Command("delete from point where pid = " + point.id + " and x=" + point.xCoord + "and y=" + point.yCoord + " and name=\"" + point.name + "\");");
    return true;
  }

  boolean addPoints(ArrayList<Point> points){
    for (Point point : points){
      if (!addPoint(point))
        return false;
    }
    return true;
  }


}