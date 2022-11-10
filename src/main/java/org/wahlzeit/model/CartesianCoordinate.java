package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;
public class CartesianCoordinate implements Coordinate {

    private double x;
    private double y;
    private double z;
    //final private double epsilon =1.0E-5;


    public CartesianCoordinate(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getZ(){
        return this.z;
    }

   
    public void setX(double setx){
        this.x = setx;
    }
    public void setY(double sety){
        this.y = sety;
    }
    public void setZ(double setz){
        this.z = setz;
    }

    protected double getDistance(CartesianCoordinate coord){
        double zwierg= Math.pow(coord.x - this.x,2) + Math.pow(coord.y - this.y,2) + Math.pow(coord.z-this.z,2);
        double erg = Math.sqrt(zwierg);
        return erg;
    }

    protected boolean isEqual(CartesianCoordinate coord){
        if(coord == null){
            return false;
        }
        if(this == coord){
            return true;
        }
        boolean erg = checkDoubleEqual(this.x,coord.x) && checkDoubleEqual(this.y,coord.y) && checkDoubleEqual(this.z,coord.z);
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

    @Override 
    public boolean equals(Object obj){
        if(obj instanceof Coordinate){
            return this.isEqual((Coordinate) obj);
        }
        return false;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate(){
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate other){
        double erg= this.getDistance(other.asCartesianCoordinate());
        return erg;
    }

    @Override
    public SphericCoordinate asSphericCoordinate(){
        CartesianCoordinate start = new CartesianCoordinate(0,0,0);
        double radius = this.getCartesianDistance(start);

        if(((Double) radius).equals(0.00)){
            return new SphericCoordinate(0,0,0);
        }
        double phi = Math.atan2(y,x);
        double theta= Math.acos(z/radius);
        return new SphericCoordinate(radius,phi,theta);
    }

    @Override 
    public double getCentralAngle(Coordinate other){
        double erg= this.asSphericCoordinate().getCentralAngle(other);
        return erg;
    }
    @Override
    public boolean isEqual(Coordinate other){
        other = other.asCartesianCoordinate();
        return this.isEqual(other.asCartesianCoordinate());
    }
}