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
        "insert into HC_Location (location_id, physician_id) values (select pid from physician where first_name = \""
            + first_name + "\" and last_name = \"" + last_name + "\" and title = \"" + title
            + "\", select lid from location where name = \"" + location_name + "\");\n");
    return true;
  }

  boolean removePhysicianLocation(String first_name, String last_name, String title,
      String location_name) {
    dbc.send_Command(
        "delete from HC_Location (location_id, physician_id) values (select pid from physician where first_name = \""
            + first_name + "\" and last_name = \"" + last_name + "\" and title = \"" + title
            + "\", select lid from location where name = \"" + location_name + "\");\n");
    return true;
  }
}
