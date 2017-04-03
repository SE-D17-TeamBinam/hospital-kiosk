
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by Alberto on 3/30/2017.
 */
public class DataController {
    ListNodes[] floors; //array of ListNodes that make up a whole floor?

    //Constructor
    public void DataController(ListNodes[] floors){
        this.floors = floors;
    }

    //Methods
    public Dictionary getInfo(Node lol){
        return null;
    }

    public ListNodes Astar (Node start, Node goal){ //A* algorithm to find efficient path
        List <Node> visited = new ArrayList<Node>();
        List <Node> possible = new ArrayList<Node>();

        visited.add(start); //adds the start to visited.

        if(start == goal){  //is the start and the goal in the same location?
            return ListPath(goal); // add the goal to the path list
        }

        for (int i = 0; i > start.new_neighbors.size(); i++ ){ //check neighbors for smallest heuristic
            long j = 100000;
            start.new_neighbors.get(i).cost = start.new_neighbors.get(i).cost + 1;

            if(start.new_neighbors.get(i).Heuristic(goal)<j){
                j = start.new_neighbors.get(i).Heuristic(goal);
            }
            long ManHatten_distance = start.new_neighbors.get(i).Heuristic(goal);



        }
        return null;
    }

    public ListNodes DFS (Node start, Node goal){
        if(start == goal){  //is the start and the goal in the same location?
            return ListPath(goal); // add the goal to the path list
        }
        return null;
    }

    //Helpers
    public ListNodes ListPath(Node destination){    //ceate a path list by reading parents

        ListNodes path = new ListNodes();    //listNode as path. in theory, in reverse order

        while(destination.parent != null){  //while destination is not start,
                                            // since start should not have a parent
            path.include(destination);      //Add destination to path list
            destination = destination.parent; //set the parent as new destination and try again.
        }
        path.include(destination);  //add "start" to listpath

        return path;
    }
}
