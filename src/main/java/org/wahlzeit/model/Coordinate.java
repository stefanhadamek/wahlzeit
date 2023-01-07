
package org.wahlzeit.model;
import org.wahlzeit.utils.PatternInstance;
@PatternInstance(
    patternName ="Abstract Factory",
    participants ={}
)
public interface Coordinate {
    
    public CartesianCoordinate asCartesianCoordinate();
    public double getCartesianDistance(Coordinate other);
    public SphericCoordinate asSphericCoordinate();
    public double getCentralAngle(Coordinate other);
    public boolean isEqual(Coordinate other);
}
