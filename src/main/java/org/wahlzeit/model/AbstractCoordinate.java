package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;
import org.wahlzeit.services.DataObject;
import java.sql.*;

public abstract class AbstractCoordinate extends DataObject implements Coordinate{

    @Override
    public boolean isEqual(Coordinate coord){
        assertClassInVariants();
        assertNotNull(coord);
        if(this == coord){
            return true;
        }
        boolean erg = this.asCartesianCoordinate().isEqual(coord.asCartesianCoordinate());
        assertClassInVariants();
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
        assertClassInVariants();
        assertNotNull(other);
        double distance = this.asCartesianCoordinate().getCartesianDistance(other);
        
        assertClassInVariants();
        assertNotNegative(distance);
        return distance;
    }

    @Override 
    public double getCentralAngle(Coordinate other) {
        assertClassInVariants();
        assertNotNull(other);
        double erg =this.asSphericCoordinate().getCentralAngle(other);
        assertValidCenterAngle(erg);
        assertClassInVariants();
        return erg;
    }

    @Override 
    public int hashCode(){
        assertClassInVariants();

        int erg = this.asCartesianCoordinate().hashCode();
        assertPositive(erg);
        assertClassInVariants();
        return erg;
    }
    @Override
    public String getIdAsString(){
        throw new UnsupportedOperationException("Coord got no Id");
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos){
        throw new UnsupportedOperationException("Coord got no Id");
    }

    @Override 
    public void writeOn(ResultSet rset) throws SQLException{
        assertClassInVariants();
        assertNotNull(rset);
        writeOnCartesian(rset);
        assertClassInVariants();
    }
    
    protected abstract void writeOnCartesian(ResultSet rset) throws SQLException;

    @Override
    public void readFrom(ResultSet rset)throws SQLException{
        assertClassInVariants();
        assertNotNull(rset);
        readFromCartesian(rset);
        assertClassInVariants();
    }

    protected abstract void readFromCartesian(ResultSet rset) throws SQLException;

    protected void assertNotNull(Object obj){
        if(obj == null){
            throw new NullPointerException();
        }
    }

    protected void assertIsANumberAndNotInfinite(Double d){
        if(d.isNaN()){
            throw new IllegalArgumentException("Parameters are not a number!");
        }
        if(d.isInfinite()){
            throw new IllegalArgumentException("Parameter musnt be infinite!");
        }
    }

    protected abstract void assertClassInVariants();

    protected void assertNotNegative(double d){
        if( d < 0) {
            throw new IllegalArgumentException("Value must be positive");
        }
    }

    protected void assertValidCenterAngle(double ang){
        if(ang < Math.toRadians(0) || ang > Math.toRadians(180)){
            throw new RuntimeException("Valid degrees value should be [0,360]");
        }
    }

    protected void assertPositive(double d){
        if (d<0){
            throw new IllegalArgumentException("should be positive");
        }
    }
}