import java.util.ArrayList;

/**
 * Created by Alberto on 3/30/2017.
 */
public class Node {
  int xCoord;    //X coordinate
  int yCoord;    //Y coordinate
  String id;      //Name of the Node
  int floor;
  public static ArrayList<Node> neighbors;
  Node parent;
  int cost;

    //Constructor
  public void Node(int xCoord, int yCoord, String id, ArrayList <Node> new_neighbors, int floor){
  this.xCoord = xCoord;
  this.yCoord = yCoord;
  this.id = id;
  this.parent = null;
  this.neighbors = new_neighbors;
  this.cost = 0;
  this.floor = floor;
  }

  //Methods
  public void addParent(Node padre){
        this.parent = padre;
    }

  public long Heuristic(Node End){
    long x = this.xCoord - End.xCoord;
    long y = this.xCoord - End.xCoord;
    long Huer = Math.abs(x+y);

    return Huer;
  }
}
