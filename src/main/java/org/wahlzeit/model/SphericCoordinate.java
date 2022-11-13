
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
        double x = this.radius * Math.cos(this.phi) * Math.sin(this.theta);
        double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
        double z = this.radius * Math.cos(this.theta);
        return new CartesianCoordinate(x,y,z);
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        return this.asCartesianCoordinate().getCartesianDistance(other);
    }
    @Override
    public boolean equals(Object other){
      if(other instanceof SphericCoordinate ) {
        return this.isEqual((SphericCoordinate) other);
      }
      return false;
    }


    @Override 
    public SphericCoordinate asSphericCoordinate(){
        return this;
    }
    //TODO 
    @Override 
    public double getCentralAngle(Coordinate other){
        SphericCoordinate coord = other.asSphericCoordinate();
        double angle = Math.toRadians(90);

        double newPhi = angle -this.getTheta();
        double newPhi2 = angle - coord.getTheta();
        double delta = Math.abs(this.getPhi()-coord.getPhi());

        
        double numerator_first_sum = Math.pow(Math.sin(delta)*Math.cos(newPhi2),2);
        double numerator_second_sum = Math.pow(Math.sin(newPhi2)*Math.cos(newPhi)-Math.sin(newPhi)*Math.cos(newPhi2)*Math.cos(delta),2);
        double numerator = Math.sqrt(numerator_first_sum+numerator_second_sum);
        double denumerator = Math.sin(newPhi)*Math.sin(newPhi2)+Math.cos(newPhi)*Math.cos(newPhi2)*Math.cos(delta);
        
        double erg = Math.atan(numerator /denumerator);
        return erg;
    }


    @Override
    public boolean isEqual(Coordinate other){
        return this.isEqual(other.asSphericCoordinate());
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