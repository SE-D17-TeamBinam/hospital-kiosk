package org;

import java.util.ArrayList;

/**
 * @author ajanagal and aramirez2
 *
 * @since 1.0
 */

/**
 * The purpose of this class is to store the values from the database,
 * and to make pathfinding easier.
 */
public class Point {

  int xCoord;    //X coordinate
  int yCoord;    //Y coordinate
  String name;  //Name of the room
  int id;      //Unique Identifier
  int floor;
  public ArrayList<Point> neighbors;
  //Attributes For A* only below.
  Point parent;
  int cost;


  //Constructor
  public Point(int xCoord, int yCoord, String name, int id, ArrayList <Point> new_neighbors, int floor){
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.name = name;
    this.id = id;
    this.parent = null;
    this.neighbors = new_neighbors;
    this.cost = 0;
    this.floor = floor;

  }

  //Methods
  public void addParent(Point padre){
        this.parent = padre;
    }
  /**Heurstic will give the manhattan straight line distance from one point to another
   * <p>
   *   it does the distance formula dist =difference of x  and difference of y
   * </p>
   * @param End
   * @return int The return will be the manhattan line distance
   */
  public int Heuristic(Point End){
    int x = Math.abs(this.xCoord - End.xCoord);
    int y = Math.abs(this.yCoord - End.yCoord);
    return x+y;
  }

  /**
   * Distance will find the straight line distance from one point to another
   * <p>
   *   it does the distance formula dist = sqrt(diffence of x squared and difference of y squared)
   * </p>
   * @param End
   * @return int The return will be the straight line distance
   */
  public int Distance(Point End){//Straight Line Distance
    double x = End.xCoord - this.xCoord;
    double y = End.yCoord - this.yCoord;
    return (int) Math.sqrt(x*x + y*y);
  }
}
