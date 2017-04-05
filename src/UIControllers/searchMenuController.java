package UIControllers;

import Definitions.Physician;
import Definitions.Point;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Created by Leon Zhang on 2017/4/1.
 */
public class searchMenuController extends centralUIController implements Initializable {
  /* initialize all ui elements */
  @FXML
  private ListView SearchDirectory;
  @FXML
  private TextField SearchField;
  @FXML
  private ListView DropMenu;
  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    /**
     * definition of Physician array
     * TODO: DB people can pass their array of physicians to docs
     */
    ArrayList<Physician> docs = new ArrayList<Physician>();

    /* Tests */
    ArrayList<Point> rooms = new ArrayList<Point>();
    Point a1 = new Point(0, 0, "a");
    Point a2 = new Point(0, 0, "b");
    Point a3 = new Point(0, 0, "c");
    Point a4 = new Point(0, 0, "d");
    Point a5 = new Point(0, 0, "e");
    Point a6 = new Point(0, 0, "f");
    rooms.add(a1);
    rooms.add(a2);
    rooms.add(a3);
    rooms.add(a4);
    rooms.add(a5);
    rooms.add(a6);
    Physician b1 = new Physician("A", "B", "Nurse", 0, rooms);
    Physician b2 = new Physician("C", "D", "Nurse", 1, rooms);
    Physician b3 = new Physician("E", "F", "Nurse", 2, rooms);
    Physician b4 = new Physician("G", "H", "Nurse", 3, rooms);
    docs.add(b1);
    docs.add(b2);
    docs.add(b3);
    docs.add(b4);
    updateDirectory(docs);
  }

  /**
   * display an ArrayList of Physicians in directory screen
   */
  public void updateDirectory (ArrayList<Physician> HCs){
    ArrayList<String> list = new ArrayList<String>();
    for (Physician doctor : HCs) {
      String newDoc = doctor.getLastName() + ", " + doctor.getFirstName() + ", " + doctor.getTitle()
          + "\nLocations: ";
      for (Point p : doctor.getLocations()) {
        newDoc = newDoc + ", " + p.getName();
      }
      list.add(newDoc);
    }
    ObservableList<String> listHC = FXCollections.observableList(list);
    SearchDirectory.setItems(listHC);
  }

  public void updateDropMenu(List<Point> points) {
    ArrayList<String> list = new ArrayList<String>();
    for (Point room : points) {
      if (!(room.getName().equals(""))) {
        list.add(room.getName());
      }
    }
    ObservableList<String> nameList = FXCollections.observableList(list);
    DropMenu.setItems(nameList);
  }
  /**
   * go back to the main menu
   */
  public void back () {
    Stage primaryStage = (Stage) SearchDirectory.getScene().getWindow();
    try {
      restartUI(primaryStage);
    } catch (Exception e) {
      System.out.println("Cannot load main menu");
      e.printStackTrace();
    }
  }

  /**
   * clear the search field
   */
  public void clear () {
    SearchField.clear();
  }
}