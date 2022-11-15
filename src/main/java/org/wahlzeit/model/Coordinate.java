
package org.wahlzeit.model;

public interface Coordinate {
    
    public CartesianCoordinate asCartesianCoordinate();
    public double getCartesianDistance(Coordinate other);
    public SphericCoordinate asSphericCoordinate();
    public double getCentralAngle(Coordinate other);
    public boolean isEqual(Coordinate other);
}
