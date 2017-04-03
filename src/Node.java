import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alberto on 3/30/2017.
 */
public class Node {
  long xCoord;    //X coordinate
  long yCoord;    //Y coordinate
  String id;      //Name of the Node
  public static List <Node> neighbors;
  Node parent;
  int cost;

    //Constructor
  public void Node(long xCoord, long yCoord, String id, List <Node> new_neighbors){
  this.xCoord = xCoord;
  this.yCoord = yCoord;
  this.id = id;
  this.parent = null;
  this.neighbors = new_neighbors;
  this.cost = 0;
  }

  //Methods
  public void addParent(Node padre){
        this.parent = padre;
    }
  public long Heuristic(Node End){
    long x = this.xCoord - End.xCoord;
    long y = this.xCoord - End.xCoord;
    long Huer = x+y;

    return Huer;
  }
}
