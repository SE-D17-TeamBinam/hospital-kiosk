package org;

import Definitions.Physician;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface to make the interaction with the Database Editor cleaner
 * Created by Evan on 4/9/2017.
 */
public interface DatabaseInterface {


  /**
   * Method to get all physicians from the database
   * @return  Arraylist of Physician objects
   * @throws SQLException If there is a problem querying the database
   */
  public ArrayList<Physician> getAllPhysicians() throws SQLException;


  /**
   * Method to update the table of physicians by emptying the previous version of the table and repopulating it with the new physicians
   * @param ap Array List of Physicians to populate the database with
   * @return true if successful and false otherwise
   * @throws SQLException If there is a problem querying the database
   */
  public boolean updatePhysicians(ArrayList<Physician> ap) throws SQLException;


  /**
   * Method to update the table of points by emptying the previous version of the table and repopulating it with the new points
   * @param al Array List of Point objects
   * @return True if successful
   */
  public boolean update_points(ArrayList<Point> al);


  /**
   * Method to get all points from the database
   * @return Array List of Point objects
   * @throws SQLException If there is a problem querying the database
   */
  public ArrayList<Point> getAllPoints() throws SQLException;

}
