package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;
import org.wahlzeit.services.DataObject;
import java.sql.*;

public abstract class AbstractCoordinate extends DataObject implements Coordinate{

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

    @Override 
    public int hashCode(){
        int erg = this.asCartesianCoordinate().hashCode();
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
        writeOnCartesian(rset);
    }
    
    protected abstract void writeOnCartesian(ResultSet rset) throws SQLException;

    @Override
    public void readFrom(ResultSet rset)throws SQLException{
        readFromCartesian(rset);
    }

    protected abstract void readFromCartesian(ResultSet rset) throws SQLException;
}