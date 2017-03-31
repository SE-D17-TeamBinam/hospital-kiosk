import java.util.HashMap;

/**
 * Created by Alberto on 3/30/2017.
 */
public class Node {
    long xCoord;    //X coordinate
    long yCoord;    //Y coordinate
    String id;      //Name of the Node
    private static HashMap< String, Node> neighbors;    //HashMap of all of the nieghbors, assigned by name

    //Constructor
    public void Node(long xCoord, long yCoord, String id, HashMap< String, Node> neighbors){
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.id = id;
    this.neighbors = neighbors;
    }

    //Methods
}
