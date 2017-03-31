import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Tom on 3/31/2017.
 */
public class SampletextController {

  @FXML
  private Label textLabel;

  public void toggleText(){
    textLabel.setText("It Works!");
    System.out.print("it works");
  }

}
