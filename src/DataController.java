
import java.util.Dictionary;
import java.util.PriorityQueue;

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
    PriorityQueue<Node> visited = new PriorityQueue<Node>();
    PriorityQueue <Node> possible = new PriorityQueue<Node>();
    Node next = null;

    visited.add(start); //adds the start to visited.

    if(start == goal){  //is the start and the goal in the same location?
      return ListPath(goal); // add the goal to the path list
    }
    else if (start.neighbors.isEmpty()){
      throw new Exception("Start has no neighbors");
    }

  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic

    start.neighbors.get(i).cost = 1;
    start.neighbors.get(i).parent = start;
    long ManHatten_distance = start.neighbors.get(i).Heuristic(goal);
    long total_cost = ManHatten_distance +  start.neighbors.get(i).cost;

    long j = 200000;
    if(total_cost<j){
      next = start.neighbors.get(i);
        j = total_cost;

    }
    possible.add(start.neighbors.get(i));

  }

    possible.remove(next); // removes next node to check from possible
    next = Funneling(next, goal, visited, possible, start.cost); //starts funneling
    return ListPath(next); //sends goal to create a list based on parents
  }

  public ListNodes DFS (Node start, Node goal){
  Node next = null;
  PriorityQueue<Node> visited = new PriorityQueue<Node>();
  PriorityQueue<Node> possible = new PriorityQueue<Node>();

  visited.add(start);

  if(start == goal){  //is the start and the goal in the same location?
    return ListPath(goal); // add the goal to the path list
  }
  else if (start.neighbors.isEmpty()){
    throw new Exception("Start has no neighbors");
  }
  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic

    start.neighbors.get(i).parent = start;  //formatting parent
    long ManHatten_distance = start.neighbors.get(i).Heuristic(goal);

    long j = 200000;
    if(ManHatten_distance<j){
        next = start.neighbors.get(i);
        j = ManHatten_distance;
    }
    possible.add(start.neighbors.get(i));
  }
  possible.remove(next);
  next = DFSfunnel(next,goal,visited,possible);
  return ListPath(next);
  }

  //Helpers
  public  Node Funneling(Node start, Node goal, PriorityQueue<Node> visited, PriorityQueue<Node> possible, int cost){
  Node next = null;

  visited.add(start); //adds the start to visited.

  if(start == goal){  //is the start and the goal in the same location?
    return goal; // add the goal to the path list
  }
  else if(possible.isEmpty()){
    throw new Exception("No possible path between start and goal");
  }

  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic

    start.neighbors.get(i).cost = start.cost +1;
    start.neighbors.get(i).parent = start;
    long ManHatten_distance = start.neighbors.get(i).Heuristic(goal);
    long total_cost = ManHatten_distance +  start.neighbors.get(i).cost;

    long j = 200000;
    if((total_cost<j)&& !(visited.contains(start))){

        next = start.neighbors.get(i);
        j = total_cost;

    }
    if(!(possible.contains(start.neighbors.get(i)))){ //makes sure the possible list doesnt
        //have the node
        possible.add(start.neighbors.get(i));   //adds it to possible list
    }
  }

  if(next == null){
    throw new Exception("No possible path between start and goal");
  }

  possible.remove(next); //removes new node from possible

  next = Funneling(start, goal, visited, possible, start.cost);
  return goal;
  }

  public Node DFSfunnel(Node start, Node goal, PriorityQueue<Node> visited, PriorityQueue<Node> possible){
  Node next = null;

  visited.add(start);     //adds to visited

  if(start == goal){  //is the start and the goal in the same location?
    return goal; // add the goal to the path list
  }

  else if (start.neighbors.isEmpty()){        //if empty, go up to redo
    return DFSfunnel(start.parent,goal,visited,possible);
  }
  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic

    start.neighbors.get(i).parent = start;  //formatting parent
    long ManHatten_distance = start.neighbors.get(i).Heuristic(goal);

    long j = 200000;
    if((ManHatten_distance<j)&&!(visited.contains(start.neighbors.get(i)))){    //if not already visited
        next = start.neighbors.get(i);
        j = ManHatten_distance;
    }
  }
  if (next == null){
    throw new Exception("No possible Path between Start and Goal");
  }
  possible.remove(next);

  return DFSfunnel(next,goal,visited,possible);
  }

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