/**
 * Created by Alberto on 4/3/2017.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeTest {
  private ArrayList<Node> neigh = new ArrayList<Node>(); //List of node-Empty
  private Node node1 = new Node( 42, 50, "A56", neigh, 4); //Node 1
  private Node node2 = new Node( 0, -5, "Basement", null, -1);// Node 2

  ;
  @Test
  public void isHueristicCorrect(){
    //One test since Nodes that enter this fucntion will have numbers
    assertEquals(87L, node1.Heuristic(node2));
  }


}
