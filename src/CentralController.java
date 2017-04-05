import UIControllers.centralUIController;
import javafx.stage.Stage;

/**
 * Created by Tom on 4/2/2017.
 */
public class CentralController {

  private FileController fController;
  private Controller dController;
  //private CredentialManager credManager;
  static Session currSession = new Session();
  private centralUIController uiController;
  public CentralController(){
  }
  public void startUI (Stage primaryStage) throws Exception {
    this.uiController = new centralUIController();
    uiController.restartUI(primaryStage);
  }
}
