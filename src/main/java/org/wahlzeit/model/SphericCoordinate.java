
package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;
public class SphericCoordinate implements Coordinate {

    private double radius;
    private double phi;
    private double theta;
    //final private double epsilon =1.0E-5;


    public SphericCoordinate(double radius, double phi, double theta){
        this.radius = radius;
        this.phi = phi;
        this.theta = theta;
    }

    public double getRadius(){
        return this.radius;
    }
    public double getPhi(){
        return this.phi;
    }
    public double getTheta(){
        return this.theta;
    }

    public void setRadius(double radius){
         this.radius= radius;
    }
    public void setPhi(double phi){
         this.phi = phi;
    }
    public void setTheta(double theta){
         this.theta = theta;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate(){
        return null;
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        return 0.0;
    }
    @Override 
    public SphericCoordinate asSphericCoordinate(){
        return this;
    }
    @Override 
    public double getCentralAngle(Coordinate other){
        return 0.0;
    }
    @Override
    public boolean isEqual(Coordinate other){
        return false;
    }
    protected boolean isEqual(SphericCoordinate coord){
        if(coord == null){
            return false;
        }
        if(this == coord){
            return true;
            }
         boolean erg = checkDoubleEqual(this.radius,coord.radius) && checkDoubleEqual(this.phi,coord.phi) && checkDoubleEqual(this.theta,coord.theta);
        return erg;
    }
    private boolean checkDoubleEqual(double a, double b){
        double difference = 0;
        if (a<=b){
            difference = b-a;
        } else {
            difference = a-b;
        }
        if( difference < epsilon) {
            return true;
        } else {
            return false;
        }
    }
}