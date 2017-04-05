import static javafx.application.Application.launch;

import UIControllers.controllers.centralUIController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static DatabaseController dbc;
    static DatabaseEditor dbe;

    @Override
    public void start(Stage primaryStage) throws Exception{
      CentralController cc = new CentralController();
      cc.startUI(primaryStage);
    }

    public static void main(String[] args) {
      dbc = new DatabaseController("org.apache.derby.jdbc.EmbeddedDriver",
          "jdbc:derby:testDB;create=true");
      dbe = new DatabaseEditor(dbc);
      launch(args);
    }
}
