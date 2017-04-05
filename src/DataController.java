
import java.util.ArrayList;
import java.util.Collections;
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
  public ListPoints Astar (Point start, Point goal) throws Exception{ //A* algorithm to find efficient path
    Point next = new Point(500,500,"start",0, new ArrayList<Point>(),4);
    start.parent = start;
    start.cost = 0;
    ArrayList<Point> open = new ArrayList<Point>(); //Seen but not checked
    ArrayList<Point> close = new ArrayList<Point>(); //Seen and checked
    int finding_lowest = 0; // helps find lowest total cost in open
    open.add(start);


    //Collections.min(open);

    while(!(open.isEmpty())){
      int total = 10000; // comparitng to function.
      int i;
      for(i = 0; i < open.size(); i++){ //finds the lowest total in the open

        if(total > (open.get(i).cost + open.get(i).Distance(goal))) {
          total = open.get(i).cost + open.get(i).Distance(goal);
          finding_lowest = i;
        }
      }
      if(open.get(finding_lowest) == goal){ //found path
        return ListPath(open.get(finding_lowest), start);
//        break;
      }
      next = open.get(finding_lowest); //stores next option as next
      open.remove(next);
      if(next.neighbors.size() == 0){
        throw new NoPathException();
      }
      for (int j = 0; j < next.neighbors.size(); j++ ){ //searching through neighbors

        //int total = next.neighbors.get(j).Distance(goal)+next.neighbors.get(j).cost;
        System.out.print(next.id);
        if(open.contains(next.neighbors.get(j))){ // visited but seen
          if (next.neighbors.get(j).cost >= next.cost){ // successor cost <= current cost
            break;
          }
        }else if(close.contains(next.neighbors.get(j))){ //visited and seen sucessor
          if(next.neighbors.get(j).cost >= next.cost){  //successor cost <= current cost
            open.add(next.neighbors.get(j));  //add to open
            break;
          }

          //close.remove(next.neighbors.get(j));  //remove sucessor from close
        }else{
          open.add(next.neighbors.get(j));    //add sucessor to open
        }
        next.neighbors.get(j).cost = next.cost + 1; //update cost
        next.neighbors.get(j).parent = next;  //update parent
      }
      close.add(next);      //add current to close
    }
    //if(open.get(finding_lowest) != goal){ //if lowest is not the same as goal
      throw new NoPathException();  //throw error
    //}
       //return
  }



//  public ListPoints Astar (Point start, Point goal) throws Exception{ //A* algorithm to find efficient path
//    start.parent = null; //for finding a path
//    ArrayList<Point> visited = new ArrayList<Point>();
//    ArrayList<Point> possible = new ArrayList<Point>();
//    Point next = new Point(0,0,"test","test",null,2);
//
//    visited.add(start); //adds the start to visited.
//
//    if(start.xCoord == goal.xCoord && start.yCoord == goal.yCoord){  //is the start and the goal in the same location?
//      return ListPath(start); // add the goal to the path list
//    }
//    else if (start.neighbors == null){
//      System.out.print("Neighbors is empty or null");
//     throw new NullPointerException("Start has no neighbors");
//    }
//    else if (start.neighbors.isEmpty()){
//      throw new Exception("Neighbors is empty");
//    }
//
//  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic
//
//    start.neighbors.get(i).cost = 1;
//    start.neighbors.get(i).parent = start;
//    long ManHatten_distance = start.neighbors.get(i).Heuristic(goal);
//    long total_cost = ManHatten_distance +  start.neighbors.get(i).cost;
//
//    long j = 200000;
//    if(total_cost<j){
//      next = start.neighbors.get(i);
//        j = total_cost;
//
//    }
//    possible.add(start.neighbors.get(i));
//
//  }
//
//    possible.remove(next); // removes next node to check from possible
//    next = AFunneling(next, goal, visited, possible, start.cost); //starts funneling
//    return ListPath(next); //sends goal to create a list based on parents
//  }
//
//  public ListPoints DFS (Point start, Point goal){
//
//    start.parent = null;
//  Point next = null;
//  PriorityQueue<Point> visited = new PriorityQueue<Point>();
//  PriorityQueue<Point> possible = new PriorityQueue<Point>();
//
//  visited.add(start);
//
//  if(start == goal){  //is the start and the goal in the same location?
//    return ListPath(goal, start); // add the goal to the path list
//  }
//  else if ((start.neighbors.isEmpty())||(start.neighbors == null)){
//    //throw new Exception("Start has no neighbors");
//  }
//  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic
//
//    start.neighbors.get(i).parent = start;  //formatting parent
//    long ManHatten_distance = start.neighbors.get(i).Heuristic(goal);
//
//    long j = 200000;
//    if(ManHatten_distance<j){
//        next = start.neighbors.get(i);
//        j = ManHatten_distance;
//    }
//    possible.add(start.neighbors.get(i));
//  }
//  possible.remove(next);
//  next = DFSfunnel(next,goal,visited,possible);
//  return ListPath(next, start);
//  }
//
//  //Helpers
//  public Point AFunneling (Point start, Point goal, ArrayList<Point> visited,
//      ArrayList<Point> possible, int cost) throws Exception{
//    Point next = null;
//    visited.add(start); //adds the start to visited.
//
//    if((start.xCoord == goal.xCoord) && (start.yCoord == goal.yCoord)){  //is the start and the goal in the same location?
//      return start; // add the goal to the path list
//    }
//
//    else if(start.neighbors == null){
//      next = FindNextBest(possible, goal);
//      return AFunneling(next, goal, visited, possible, next.cost);
//    }
//
//  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic
//
//    start.neighbors.get(i).cost = start.cost +1;
//    start.neighbors.get(i).parent = start;
//    int ManHatten_distance = start.neighbors.get(i).Distance(goal);
//    int total_cost = ManHatten_distance +  start.neighbors.get(i).cost;
//
//    long j = 200000;
//    if((total_cost<j)&& !(visited.contains(start))){
//
//        next = start.neighbors.get(i);
//        j = total_cost;
//
//    }
//    if(!(possible.contains(start.neighbors.get(i)))){ //makes sure the possible list doesnt
//        //have the node
//        possible.add(start.neighbors.get(i));   //adds it to possible list
//    }
//  }
//
//  if(next == null){
//    System.out.print("Next option doesnt exist");
//    throw new NullPointerException("No possible path between start and goal");
//  }
//
//  possible.remove(next); //removes new node from possible
//  if(possible.isEmpty()){
//      throw new NoPathException();
//    }
//  next = AFunneling(start, goal, visited, possible, start.cost);
//  return next;
//  }
//
//  public Point DFSfunnel(Point start, Point goal, PriorityQueue<Point> visited, PriorityQueue<Point> possible){
//  Point next = null;
//
//  visited.add(start);     //adds to visited
//
//  if(start == goal){  //is the start and the goal in the same location?
//    return goal; // add the goal to the path list
//  }
//
//  else if (start.neighbors.isEmpty()){        //if empty, go up to redo
//    return DFSfunnel(start.parent,goal,visited,possible);
//  }
//  for (int i = 0; i > start.neighbors.size(); i++ ){ //check neighbors for smallest heuristic
//    start.neighbors.get(i).parent = start;  //formatting parent
//    long ManHatten_distance = start.neighbors.get(i).Heuristic(goal);
//
//    long j = 200000;
//    if((ManHatten_distance<j)&&!(visited.contains(start.neighbors.get(i)))){    //if not already visited
//        next = start.neighbors.get(i);
//        j = ManHatten_distance;
//    }
//  }
//  if (next == null){
//    //throw new Exception("No possible Path between Start and Goal");
//  }
//  possible.remove(next);
//
//  return DFSfunnel(next,goal,visited,possible);
//  }

  public ListPoints ListPath(Point destination, Point begin){    //ceate a path list by reading parents
    ArrayList<Point> order = new ArrayList<Point>();


    while(destination != begin){  //while destination is not start,
    // since start should not have a parent
      order.add(destination);
      destination = destination.parent; //set the parent as new destination and try again.
           //Add destination to path list
    }
   order.add(destination);  //add "start" to listpath
    ListPoints path = new ListPoints(order);    //listNode as path. in theory, in reverse order
    return path;
  }
//
//  public Point FindNextBest(ArrayList<Point> NextBest, Point goal){
//    Point next = null;
//    int j = 100000;
//    for(int i = 0; i < NextBest.size(); i++){
//      if(j<(NextBest.get(i).Distance(goal) + NextBest.get(i).cost)){
//         j = NextBest.get(i).Distance(goal) + NextBest.get(i).cost;
//         next = NextBest.get(i);
//      }
//    }
//    return next;
//  }
}
