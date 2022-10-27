import java.lang.*;
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
    public double getDistance(Coordinate coord){
        //creating zwierg to plug in for eucleading distance 
        double zwierg= Math.pow(other.x - this.x,2) + Math.pow(other.y - this.y,2) + Math.pow(other.z-this.z,2);
        double erg = Math.sqrt(zwierg);
        // return erg
        return erg;

    }
    public boolean isEqual(Coordinate coord){
       //Checking Base-Case
        if(coord == null){
        return false;
        }
        //Using overwritten Method
        else if (this.equals(coord)){
        return this.equals(coord);
        }else {
            //Using Java implemented equals Method
            boolean erg=( ((Double) this.getX()).equals(coord.getX()) && ((Double) this.getY()).equals(coord.getY()) && ((Double) this.getZ()).equals(coord.getZ()) );
            return erg;
        }
    
    @Override 
    public boolean equals(Object coord){
        if(coord instanceof Coordinate){
            return this.isEqual((Coordinate) coord);
        } else{
            return false;
        }   
    }
}