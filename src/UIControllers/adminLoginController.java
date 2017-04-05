package UIControllers;

import CredentialManager.credentialManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Leon Zhang on 2017/4/1.
 */


public class AdminLoginController extends CentralUIController implements Initializable {

  // define all ui elements
  @FXML
  private Pane AdminLogin;
  @FXML
  private javafx.scene.control.TextField AdminNameField;
  @FXML
  private PasswordField AdminPassField;
  @FXML
  private Label LoginError;

  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
  }

  public void login () {
    Stage primaryStage = (Stage) AdminLogin.getScene().getWindow();
    String enteredName = AdminNameField.getText();
    String enteredPass = AdminPassField.getText();
    credentialManager cm = new credentialManager();
    if (cm.userIsAdmin(enteredName, enteredPass)) {
      LoginError.setVisible(false);
      try {
        loadScene(primaryStage, "/DirectEdit.fxml");
      } catch (Exception e) {
        System.out.println("Cannot load directory editor");
        e.printStackTrace();
      }
    } else {
      LoginError.setVisible(true);
    }
  }

  public void back () {
    Stage primaryStage = (Stage) AdminLogin.getScene().getWindow();
    try {
      restartUI(primaryStage);
    } catch (Exception e) {
      System.out.println("Cannot load main menu");
      e.printStackTrace();
    }
  }

}