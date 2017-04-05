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


  boolean addService(String service, String md_related) {
    dbc.send_Command(
        "insert into service (name,md_related) values ('" + service + "','" + md_related
            + "')");
    return true;
  }

  boolean removeService(String service) {
    dbc.send_Command("delete from service where service = '" + service + "');");
    return true;
  }

  ///////////////////////////
  /////// Physician /////////
  ///////////////////////////

  boolean removePhysician(String first_name, String last_name, String title) {
    dbc.send_Command(
        "delete from physician (first_name, last_name, title) values ('" + first_name + "','"
            + last_name + "','" + title + "')");
    return true;
  }

  boolean addPhysician(String first_name, String last_name, String title) {
    dbc.send_Command(
        "insert into physician (first_name, last_name, title) values ('" + first_name + "','"
            + last_name + "','" + title + "')");
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

  boolean addLocation(String name, String isFloor, String floor) {
    dbc.send_Command(
        "insert into Location (name,isFloor,Floor) values (" + name + "," + isFloor + "," + floor
            + ")");
    return true;
  }

  boolean removeLocation(String name, String isFloor, String floor) {
    dbc.send_Command("delete from Location where name = '" + name + "')");
    return true;
  }

  ///////////////////////////
  //// Location - Service ///
  ///////////////////////////

  boolean addServiceLocation(String service_name, String md_related, String location_name) {
    dbc.send_Command(
        "insert into ServiceLocation (lid,sid) select lid,sid from service_location, service where location.name = '"
            + location_name + "', select sid from service where"
            + " service.name = '" + service_name + "')\n ");
    return true;
  }

  boolean removeServiceLocation(String service_name, String location_name) {
    dbc.send_Command(
        "delete from ServiceLocation  where sid = (select sid from service where name = '"
            + service_name + "') and  lid = (select lid from location where"
            + " name = '" + location_name + "')\n ");
    return true;
  }

  ///////////////////////////
  /// Location - Physician //
  ///////////////////////////

  boolean addPhysicianLocation(String first_name, String last_name, String title,
      String location_name) {
    dbc.send_Command(
        "insert into P_Location (pid,lid) select pid,lid from physician,location where first_name = '"
            + first_name + "' and last_name = '" + last_name + "' and title = '" + title
            + "'and name = '" + location_name + "');\n");
    return true;
  }

  boolean removePhysicianLocation(String first_name, String last_name, String title,
      String location_name) {
    dbc.send_Command(
        "delete from P_Location where pid =  (select pid from physician where first_name = '"
            + first_name + "' and last_name = '" + last_name + "' and title = '" + title
            + "')and lid  = (select lid from location where name = '" + location_name + "');\n");
    return true;
  }

  ///////////////////////
  /////Location -Point///
  ///////////////////////

  boolean addPointLocation(String location_name,int pid){
    dbc.send_Command(
        "insert into Point_Location (lid,pid) select lid,pid from location,point where location =  '"
            + location_name + "' and pid = '" + pid +  "');\n");
    return true;
  }

  boolean removeLocationPoint(String location_name,int pid){

    dbc.send_Command(
        "delete from Point_Location where pid = '" + pid +  "' and lid = (select lid from location where name = '" + location_name +  "' ) ); \n");
    return true;

  }

  ////////////////////////
  /////////Point/////////
  //////////////////////

  boolean addPoint(Point point) {
    int cost = point.cost;
    int x = point.xCoord;
    int y = point.yCoord;
    String id = point.id;
    int floor = point.floor;
    String name = point.name;


    dbc.send_Command(
        "insert into Point (x,y,cost,pid) values ('" + x + "','"
            + y + "','" + cost + "','" + id + "' ); \n");

    //add method to put node in location

    /*
    int i ;
    ArrayList<Point> pl = point.Neighbors;
    for(i = 0;i < pl.size();i++){
      this.addNeighbor(pl.get(i).id);
      this.addNeighboring(pl.get(i).id,point.id);
    }
    */

    return true;
  }


  boolean removePoint(Point point) {
    int cost = point.cost;
    int x = point.xCoord;
    int y = point.yCoord;
    String id = point.id;
    int floor = point.floor;
    String name = point.name;

    dbc.send_Command(
        "delete from Point where pid = '" + id + "' ); \n");
    return true;
  }


  /*boolean update_nodes(ArrayList<Point> al){
      dbc.send_Command("truncate table Point;");
      int i;
      for(i = 0;i < al.size();i++){
        this.addPoint(point);
      }

      return true;
  }*/

  //////////////////////
  ///////Neighbor///////
  //////////////////////

  boolean addNeighbor(int pid) {

    dbc.send_Command(
        "insert into Neighbor (pid) values ('" + pid + "' ); \n");
    return true;
  }

  boolean removeNeighbor(int pid) {

    dbc.send_Command(
        "delete from Neighbor where pid = '" + pid + "' ); \n");
    return true;
  }

  /*boolean update_nodes(ArrayList<Point> al){
      dbc.send_Command("truncate table Point");


      return true;
  }*/


  ///////////////////////
  ///////Neighboring///////
  //////////////////////

  boolean addNeighboring(int pid_n,int pid_p) {

    dbc.send_Command(
        "insert into Neighboring (pid_n,pid_p) values ('" + pid_n + "','" + pid_p +  "' ); \n");
    return true;
  }

  boolean deleteNeighboring(int pid_n,int pid_p) {

    dbc.send_Command(
        "delete from Neighboring where pid_n = '" + pid_n + "' and pid_p = '" + pid_p +  "' ); \n");
    return true;
  }
}