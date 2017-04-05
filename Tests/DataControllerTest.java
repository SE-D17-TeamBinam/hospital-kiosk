/**
 * Created by Alberto on 4/3/2017.
 */

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataControllerTest {

  DataController data = new DataController(null);
  Point p1, p2, p3, p4, p5;
  ArrayList<Point> neigh = new ArrayList<>();
  ArrayList<Point> neigh2 = new ArrayList<>();
  ArrayList<Point> neigh3 = new ArrayList<>();

  @BeforeEach
  public void setup(){
    p1 = new Point(42, 50, "Begin", 0, neigh, 4);
    p2 = new Point(0, -5, "2t", 1, neigh2, 4);
    p3 = new Point(12, 30, "3", 2, neigh3, 5);
    p4 = new Point(5, 30, "end", 3, new ArrayList<Point>(), 5);
    p5 = new Point(5, 30, "no connection", 4, new ArrayList<Point>(), 5);
    p1.neighbors.add(p2);
    p1.neighbors.add(p3);
    p2.neighbors.add(p3);
    p2.neighbors.add(p1);
    p3.neighbors.add(p1);
    p3.neighbors.add(p4);
    p3.neighbors.add(p2);
    p4.neighbors.add(p3);
  }

  @Test
  void aStarAttemptTest() throws Exception {
    ArrayList<Point> expectedList = new ArrayList<Point>();
    expectedList.add(p4);
    expectedList.add(p3);
    //expectedList.add(p2);
    expectedList.add(p1);
    ListPoints expectedListPoint = new ListPoints(expectedList);
    ListPoints testList = data.Astar(p1, p4);
    //expectedList.add(point2);
    if (testList.getPoints().size() == expectedList.size()){
      for (int i = 0; i < expectedList.size(); i++){
        assertEquals(expectedList.get(i).xCoord, testList.getPoints().get(i).xCoord);
        assertEquals(expectedList.get(i).yCoord, testList.getPoints().get(i).yCoord);
      }
    }
    else {
     assertTrue(false);
    }

  }
  @Test
  void aStarAttempt2Test(){
    ArrayList<Point> expectedList = new ArrayList<Point>();
    expectedList.add(p1);
    expectedList.add(p3);
    //expectedList.add(p2);
    expectedList.add(p4);
    ListPoints expectedListPoint = new ListPoints(expectedList);
    ListPoints testList = null;
    try {
      testList = data.Astar(p4, p1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //expectedList.add(point2);
    if (testList.getPoints().size() == expectedList.size()){
      for (int i = 0; i < expectedList.size(); i++){
        assertEquals(expectedList.get(i).xCoord, testList.getPoints().get(i).xCoord);
        assertEquals(expectedList.get(i).yCoord, testList.getPoints().get(i).yCoord);
      }
    }
    else {
      assertTrue(false);
    }
  }

  @Test
  void aStarExceptionTest(){
    assertThrows(NoPathException.class, ()-> data.Astar(p4,p5));
  }
  /**  //List of Point-Empty
   private ArrayList<Point> neigh = new ArrayList<Point>();
   //Point 1
   private Point point1 = new Point(42, 50, "A56", "A56", neigh, 4);
   // Point 2
   private Point point2 = new Point(0, -5, "Basement", "Base",
   null, -1);
   //List of Points
   private ArrayList<Point> neigh2 = new ArrayList<Point>();
   boolean f = neigh2.add(point2); //adds point2

   // Point 3
   private Point point3 = new Point(12, 30, "5th FLoor", "5a", neigh2, 5);
   //List of Points 3
   private ArrayList<Point> neigh3 = new ArrayList<Point>();
   boolean h = neigh3.add(point3); //adds point2
   // Point 4
   private Point point4 = new Point(5, 30, "5th FLoor", "5a", neigh3, 5);
   //Point 2
   boolean x = neigh.add(point3);
   */


}

