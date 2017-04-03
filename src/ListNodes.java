import java.util.ArrayList;

/**
 * Created by Alberto on 4/3/2017.
 */
public class ListNodes {
  private ArrayList<Node> nodes;  //List of Nodes that relate to each other

  //Constructor
  public void ListNodes(ArrayList<Node> nodes){
    this.nodes = nodes;
  }

  //Methods
  private void sort(){}

  //Helpers
  public void include(Node n){
    this.nodes.add(n);  //adds node to ArrayList Nodes
  }
}
