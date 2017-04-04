
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.PriorityQueue;

/**
* Created by Alberto on 3/30/2017.
*/
public class DataController {
ListPoints[] floors; //array of ListNodes that make up a whole floor?

//Constructor
public DataController(ListPoints[] floors){
this.floors = floors;
}

//Methods
  public Dictionary getInfo(Point lol){
    return null;
  }

  public ListPoints Astar (Point start, Point goal){ //A* algorithm to find efficient path
    PriorityQueue<Point> visited = new PriorityQueue<Point>();
    PriorityQueue <Point> possible = new PriorityQueue<Point>();
    Point next = null;

    visited.add(start); //adds the start to visited.

    if(start == goal){  //is the start and the goal in the same location?
      return ListPath(goal); // add the goal to the path list
    }
    else if (start.neighbors.isEmpty()){
     // throw new Exception("Start has no neighbors");
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
    next = AFunneling(next, goal, visited, possible, start.cost); //starts funneling
    return ListPath(next); //sends goal to create a list based on parents
  }

  public ListPoints DFS (Point start, Point goal){

    start.parent = null;
  Point next = null;
  PriorityQueue<Point> visited = new PriorityQueue<Point>();
  PriorityQueue<Point> possible = new PriorityQueue<Point>();

  visited.add(start);

  if(start == goal){  //is the start and the goal in the same location?
    return ListPath(goal); // add the goal to the path list
  }
  else if (start.neighbors.isEmpty()){
    //throw new Exception("Start has no neighbors");
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
  public Point AFunneling(
      Point start, Point goal, PriorityQueue<Point> visited, PriorityQueue<Point> possible, int cost){
  Point next = null;

  visited.add(start); //adds the start to visited.

  if(start == goal){  //is the start and the goal in the same location?
    return goal; // add the goal to the path list
  }
  else if(possible.isEmpty()){
   // throw new Exception("No possible path between start and goal");
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
    //throw new Exception("No possible path between start and goal");
  }

  possible.remove(next); //removes new node from possible

  next = AFunneling(start, goal, visited, possible, start.cost);
  return goal;
  }

  public Point DFSfunnel(Point start, Point goal, PriorityQueue<Point> visited, PriorityQueue<Point> possible){
  Point next = null;

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
    //throw new Exception("No possible Path between Start and Goal");
  }
  possible.remove(next);

  return DFSfunnel(next,goal,visited,possible);
  }

  public ListPoints ListPath(Point destination){    //ceate a path list by reading parents
    ArrayList<Point> order = new ArrayList<Point>();


    while(destination.parent != null){  //while destination is not start,
    // since start should not have a parent
      order.add(destination);      //Add destination to path list
     destination = destination.parent; //set the parent as new destination and try again.

    }
   order.add(destination);  //add "start" to listpath
    ListPoints path = new ListPoints(order);    //listNode as path. in theory, in reverse order
    return path;
  }
}
