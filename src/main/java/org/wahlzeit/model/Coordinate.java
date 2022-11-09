
package org.wahlzeit.model;
/*import java.lang.*;

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
        double zwierg= Math.pow(coord.x - this.x,2) + Math.pow(coord.y - this.y,2) + Math.pow(coord.z-this.z,2);
        double erg = Math.sqrt(zwierg);
        // return erg
        return erg;

    }
    public boolean isEqual(Coordinate coord){
        if(coord == null){
            return false;
        }
        if(this == coord){
            return true;
            }
        return (this.x == coord.x) && (this.y == coord.y) && (this.z == coord.z);
    }

    @Override 
    public boolean equals( Object obj){
        if(obj instanceof Coordinate){
            return this.isEqual((Coordinate) obj);
        }
        return false;

    }
}
*/
public interface Coordinate {
    
    public CartesianCoordinate asCartesianCoordinate();
    public double getCartesianDistance(Coordinate other);
    public SphericCoordinate asSphericCoordinate();
    public double getCentralAngle(Coordinate other);
    public boolean isEqual(Coordinate other);
}
