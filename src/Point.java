import java.util.ArrayList;

/**
 * @author ajanagal and aramirez2
 *
 * @since 1.0
 */

/**
 * The purpose of this class is to store the values from the database,
 * and to make pathfinding easier.
 */
public class Point {
  int xCoord;    //X coordinate
  int yCoord;    //Y coordinate
  String name;  //Name of the room
  int id;      //Unique Identifier
  int floor;
  public ArrayList<Point> neighbors;
  //Attributes For A* only below.
  Point parent;
  int cost;

    //Constructor
  public Point(int xCoord, int yCoord, String name, int id, ArrayList <Point> new_neighbors, int floor){
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
