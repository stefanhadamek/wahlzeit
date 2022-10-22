
package org.wahlzeit.model;


 public class Coordinate{
    
    
    private double x;
    private double y;
    private double z;
    //Constructor to create a Coordinate

    public Coordinate(double x,double y,double z){
        this.x = x;
        this.y = y; 
        this.z = z;
    }
    //Getters for all Variables
    public double getX(){
        return this.x;   
    }
    public double getY(){
        return this.y;
    }
    public double getZ(){
        return this.z;
    }

    //Setters for all Variables
    public void setX(double setx){
        this.x = setx;
    }
    public void setY(double sety){
        this.y = sety;
    }
    public void setZ(double setz){
        this.z = setz;
    }
}