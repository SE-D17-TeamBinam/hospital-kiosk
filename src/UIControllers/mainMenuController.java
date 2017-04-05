package UIControllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * Created by Leon Zhang on 2017/4/1.
 */

public class mainMenuController extends centralUIController implements Initializable {
  /* initialize all ui elements */
  @FXML
  private Pane MainMenu;

  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
  }

  /**
   * go to the map view screen
   */
  public void gotoMap () {
    Stage primaryStage = (Stage) MainMenu.getScene().getWindow();
    try {
      mapViewFlag = 3;
      loadScene(primaryStage, "/MapScene.fxml");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * go to the search menu screen (contains user directory)
   */
  public void gotoSearch () {
    Stage primaryStage = (Stage) MainMenu.getScene().getWindow();
    try {
      loadScene(primaryStage, "/SearchMenu.fxml");
    } catch (Exception e) {
    }
  }

  /**
   * go to admin login screen
   */
  public void gotoAdmin () {
    Stage primaryStage = (Stage) MainMenu.getScene().getWindow();
    try {
      loadScene(primaryStage, "/AdminLogin.fxml");
    } catch (Exception e) {
    }
  }

}
