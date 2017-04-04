import java.util.ArrayList;

/**
 * Created by Alberto on 4/3/2017.
 */
public class ListPoints {
  private ArrayList<Point> points;  //List of Nodes that relate to each other

  //Constructor
  public ListPoints(ArrayList<Point> points){
    this.points = points;
  }

  //Methods
  private void sort(){}

  //Helpers
  public ArrayList<Point> getPoints(){
    return this.points;
  } //Getter

  public void include(Point n){
    this.points.add(n);  //adds node to ArrayList Nodes
  }
}
