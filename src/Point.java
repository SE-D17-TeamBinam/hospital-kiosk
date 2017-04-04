import java.util.ArrayList;

/**
 * Created by Alberto on 3/30/2017.
 */
public class Point {
  int xCoord;    //X coordinate
  int yCoord;    //Y coordinate
  String name;  //Name of the room
  String id;      //Unique Identifier
  int floor;
  public static ArrayList<Point> neighbors;
  //Attributes For A* only below.
  Point parent;
  int cost;

  //Constructor
  public Point(int xCoord, int yCoord, String name, String id, ArrayList <Point> new_neighbors, int floor){
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.name = name;
    this.id = id;
    this.parent = null;
    this.neighbors = new_neighbors;
    this.cost = 0;
    this.floor = floor;
  }

  //Methods
  public void addParent(Point padre){
    this.parent = padre;
  }

  public int Heuristic(Point End){
    int x = Math.abs(this.xCoord - End.xCoord);
    int y = Math.abs(this.yCoord - End.yCoord);
    return x+y;
  }

  public int Distance(Point End){//Straight Line Distance
    double x = End.xCoord - this.xCoord;
    double y = End.yCoord - this.yCoord;
    return (int) Math.sqrt(x*x + y*y);
  }
}
