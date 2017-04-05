package UIControllers;

import Definitions.Physician;
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
  // define all ui elements
  @FXML
  private ListView SearchDirectory;
  @FXML
  private TextField SearchField;

  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    // actions of each ui element
  }

  public void updateDirectory (ArrayList<Physician> HCs){
    ArrayList<String> list = new ArrayList<String>();
    for (Physician doctor : HCs) {
      String newDoc = doctor.getLastName() + ", " + doctor.getFirstName();
      list.add(newDoc);
    }
    ObservableList<String> listHC = FXCollections.observableList(list);
    SearchDirectory.setItems(listHC);
  }

  public void back () {
    Stage primaryStage = (Stage) SearchDirectory.getScene().getWindow();
    try {
      restartUI(primaryStage);
    } catch (Exception e) {
      System.out.println("Cannot load main menu");
      e.printStackTrace();
    }
  }

  public void clear () {
    SearchField.clear();
  }
}