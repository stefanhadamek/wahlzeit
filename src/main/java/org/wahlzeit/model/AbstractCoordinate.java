package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;

public abstract class AbstractCoordinate implements Coordinate{

    @Override
    public boolean isEqual(Coordinate coord){
        if(coord == null){
            return false;
        }
        if(this == coord){
            return true;
        }
        boolean erg = this.asCartesianCoordinate().isEqual(coord.asCartesianCoordinate());
        return erg;   
    }
    
    protected boolean checkDoubleEqual(double a, double b){
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
    public double getCartesianDistance(Coordinate other){
        return this.asCartesianCoordinate().getCartesianDistance(other);
    }

    @Override 
    public double getCentralAngle(Coordinate other) {
        return this.asSphericCoordinate().getCentralAngle(other);
    }
}