package org;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Definitions.*;
import org.Point;

/**
 * Created by evan on 3/25/17.
 * This Object will add, remove and edit our hospital database
 */
public class DatabaseEditor {

  DatabaseController dbc = null;

  public DatabaseEditor(DatabaseController _dbc) {
    this.dbc = _dbc;
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

  boolean addPhysician(int PID, String first_name, String last_name, String title,
      ArrayList<Integer> array_points) {
    dbc.send_Command(
        "insert into physician (pid,first_name, last_name, title) values ('" + PID + "','"
            + first_name + "','"
            + last_name + "','" + title + "')");

    int i;
    for (i = 0; i < array_points.size(); i++) {
      this.addPhysicianLocation(PID, array_points.get(i));
    }
    return true;
  }

  Physician get_physician(int pid) {
    ResultSet res = dbc.send_Command("select * from physician where pid = " + pid).get(0);
    int c = 0;
    Physician my_p = null;
    c++;
    try {
      while (res.next()) {
        if (c > 1) {
          System.out.println("was not supposed to happen");
          break;
        }
        String first_name = res.getString("FIRST_NAME");
        String last_name = res.getString("LAST_NAME");
        String title = res.getString("TITLE");
        int new_pid = res.getInt("PID");

        my_p = new Physician(first_name, last_name, title, pid, new ArrayList<Integer>());
        //physicians.add(p);

      }

    res.close();

    if (my_p == null) {
      System.out.println("no physician found");
      return null;
    }

    ResultSet res2 = dbc.send_Command("select * from physician_location where pid_ph = " + pid)
        .get(0);

    ArrayList<Integer> my_locs = new ArrayList<Integer>();
    while (res2.next()) {
      int new_pid2 = res2.getInt("PID_po");
      my_locs.add(new_pid2);

    }
    res2.close();
    my_p.setLocations(my_locs);


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return my_p;


  }


  ArrayList<Physician> getAllPhysicians() throws SQLException {
    ArrayList<Physician> physicians = new ArrayList<Physician>();
    ResultSet res = dbc.send_Command("select pid from physician").get(0);
    while (res.next()) {
      int pid = res.getInt("PID");

      Physician p = get_physician(pid);
      physicians.add(p);
    }

    return physicians;

  }


  boolean updatePhysicians(ArrayList<Physician> ap) throws SQLException {
    dbc.send_Command("truncate table Physician;");
    int i;
    for (i = 0; i < ap.size(); i++) {
      this.addPhysician(ap.get(i).getID(), ap.get(i).getFirstName(), ap.get(i).getLastName(),
          ap.get(i).getTitle(), ap.get(i).getLocations());
    }

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

  boolean addPhysicianLocation(int pid_ph, int pid_po) {
    dbc.send_Command(
        "insert into Physician_Location (pid_po,pid_ph) values('" + pid_po + "','" + pid_ph
            + "');\n");
    return true;
  }

  boolean removePhysicianLocation(int pid_ph, int pid_po) {
    dbc.send_Command(
        "delete from Physician_Location where pid_ph = '" + pid_ph + "' and pid_po = '" + pid_po
            + "');\n");
    return true;
  }

  ////////////////////////
  /////////Point/////////
  //////////////////////

  boolean addPoint(Point point) {
    int cost = point.getCost();
    int x = point.getXCoord();
    int y = point.getYCoord();
    int id = point.getId();
    int floor = point.getFloor();
    String name = point.getName();
    ArrayList<Integer> neighbors = point.getNeighbors();

    dbc.send_Command(
        "insert into Point (id,x,y,cost,pid) values (" + id + "," + x + ","
            + y + "," + cost + "," + id + "); \n");

    //add instructions to put node in location

    //name = point.name.replaceAll("\\s+","");
    //this.addLocation(name,"N",floor);
    //this.addPointLocation(name,Integer.parseInt(id));

    int i;
    ArrayList<Integer> pl = point.getNeighbors();
    for (i = 0; i < neighbors.size(); i++) {
      this.addNeighbor(id, pl.get(i));
      //this.addNeighboring(pl.get(i).id,point.id);
    }

    return true;
  }


  boolean removePoint(Point point) {
    int cost = point.getCost();
    int x = point.getXCoord();
    int y = point.getYCoord();
    int id = point.getId();
    int floor = point.getFloor();
    String name = point.getName();

    dbc.send_Command(
        "delete from Point where pid = " + id + ";");
    return true;
  }


  boolean update_points(ArrayList<Point> al) {
    dbc.send_Command("truncate table Point;");
    int i;
    for (i = 0; i < al.size(); i++) {
      this.addPoint(al.get(i));
    }

    return true;
  }

  Point get_point(int my_pid) {
    Point my_point = null;
    ResultSet res1 = dbc.send_Command("select * from point where pid = " + my_pid).get(0);
    int c = 0;
    try{
    while (res1.next()) {
      c++;
      if (c > 1) {
        System.out.println("that was not supposed to happen. ");
        break;
      }

      int floor = res1.getInt("floor");
      String name = res1.getString("NAME");
      int pid = res1.getInt("PID");
      int x = res1.getInt("x");
      int y = res1.getInt("y");
      int cost = res1.getInt("cost");

      my_point = new Point(x, y, name, pid, new ArrayList<Integer>(), floor);

      ArrayList<Integer> neighbor_ids = new ArrayList<Integer>();
      ResultSet res4 = dbc.send_Command(
          "select pid1,pid2 from Neighbors where pid1 = " + pid + "OR pid2 = " + pid).get(0);
      while (res4.next()) {
        int pid1 = res4.getInt("Pid1");
        int pid2 = res4.getInt("Pid1");
        if (pid1 != my_pid) {
          neighbor_ids.add(pid1);
        } else {
          neighbor_ids.add(pid2);
        }

      }
      res4.close();

      my_point.setNeighbors(neighbor_ids);


    }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return my_point;

  }


  ArrayList<Point> getAllPoints() throws SQLException {
    ArrayList<Point> points = new ArrayList<Point>();
    ResultSet res = dbc.send_Command("select pid from point").get(0);
    Point new_point;
    while (res.next()) {
      int pid = res.getInt("PID");
      new_point = get_point(pid);
      points.add(new_point);
    }
    res.close();
    return points;
  }

  //////////////////////
///////Neighbor///////
//////////////////////

  boolean addNeighbor(int pid1,int pid2) {

    dbc.send_Command(
        "insert into Neighbor (pid1,pid2) values (" + pid1 + "," + pid2 + "); \n");
    return true;
  }

  boolean removeNeighbor(int pid1,int pid2) {

    dbc.send_Command(
        "delete from Neighbor where pid1 = " + pid1 + " + or pid2 = " + pid2 + "); \n");
    return true;
  }

}












//OLD COMMENTED OUT CODE STARTS



///////////////////////
  ///////Neighboring///////
  //////////////////////
/*
  boolean addNeighboring(int pid_n,int pid_p) {

    dbc.send_Command(
        "insert into Neighboring (pid_n,pid_p) values (" + pid_n + "," + pid_p +  " ); \n");
    return true;
  }

  boolean deleteNeighboring(int pid_n,int pid_p) {

    dbc.send_Command(
        "delete from Neighboring where pid_n = " + pid_n + " and pid_p = " + pid_p +  " ); \n");
    return true;
  }

}


*/


///////////////////////////
//////// Location /////////
///////////////////////////
/*
  boolean addLocation(String name, String isFloor, int floor) {
    dbc.send_Command(
        "insert into Location (name,isFloor,Floor) values (" + name + "," + isFloor + "," + floor
            + ")");
    return true;
  }

  boolean removeLocation(String name, String isFloor, String floor) {
    dbc.send_Command("delete from Location where name = '" + name + "')");
    return true;
  }
*/



  /*boolean update_nodes(ArrayList<Point> al){
      dbc.send_Command("truncate table Point");


      return true;
  }*/


///////////////////////
/////Location -Point///
///////////////////////
/*
  boolean addPointLocation(String location_name,int pid){
    dbc.send_Command(
        "insert into Point_Location (lid,pid) select lid,pid from location,point where location =  '"
            + location_name + "' and pid = " + pid +  ");\n");
    return true;
  }

  boolean removeLocationPoint(String location_name,int pid){

    dbc.send_Command(
        "delete from Point_Location where pid = " + pid +  " and lid = (select lid from location where name = '" + location_name +  "' ) ); \n");
    return true;

  }
*/