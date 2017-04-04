import java.util.ArrayList;

/**
 * Created by Alberto on 3/30/2017.
 */
public class Point {
    int xCoord;    //X coordinate
    int yCoord;    //Y coordinate
    String name;
    String id;      //Name of the Point
    private static ArrayList<Point> neighbors;    //HashMap of all of the nieghbors, assigned by name
    Point parent;
    int cost;

    //Constructor
    public Point(int xCoord, int yCoord, String id,ArrayList<Point> neighbors){
      this.xCoord = xCoord;
      this.yCoord = yCoord;
      this.id = id;
      this.neighbors = neighbors;
      this.parent = null;
      this.cost = 0;
    }

    //Methods
    public void addParent(Point padre){
        this.parent = padre;
    }
}
