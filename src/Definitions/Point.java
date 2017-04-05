package Definitions;

import java.util.ArrayList;

/**
 * Created by Leon Zhang on 2017/4/2.
 */
public class Point {

  private double xCoord;
  private double yCoord;
  private String name;
  private int floor;

  ArrayList<Point> connections = new ArrayList<Point>();

  public Point(double xCoord, double yCoord, String name) {
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.name = name;
  }

  public Point(double xCoord, double yCoord, int floor){
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.floor = floor;
  }

  public Point() {
    xCoord = 0;
    yCoord = 0;
  }

  public void connectTo(Point node) {
    node.getNeighbors().add(this);
    this.connections.add(node);
  }

  public void severFrom(Point node){
    if(connections.contains(node)){
      node.getNeighbors().remove(this);
      connections.remove(node);
    }
  }

  public void severConnections(){
    for(int i = 0; i < connections.size(); i++){
      connections.get(i).severFrom(this);
    }
  }

  public double getXCoord() {
    return xCoord;
  }

  public double getYCoord() {
    return yCoord;
  }

  public ArrayList<Point> getNeighbors() {
    return connections;
  }

  public String getName() {
    return name;
  }

  public void setName(String newName) {
    name = newName;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getFloor() {
    return floor;
  }

  public void setXCoord(double xCoord){
    this.xCoord = xCoord;
  }

  public void setYCoord(double yCoord){
    this.yCoord = yCoord;
  }
}
