/**
 * Created by Alberto on 4/3/2017.
 */
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataControllerTest {
  //List of node-Empty
  private ArrayList<Point> neigh = new ArrayList<Point>();
  //Point 1
  private Point point1 = new Point( 42, 50, "A56","A56", neigh, 4);
  // Point 2
  private Point point2 = new Point( 0, -5, "Basement", "Base",
      null, -1);
  // Point 2
  private Point point3 = new Point( 12, 30, "5th FLoor","5a",
      null, 5);


  @Test
  void aStarEmptyListNodesTest() {
    //assertThrows();
  }
}
