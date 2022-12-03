package org.wahlzeit.model;
import java.sql.*;
import java.util.Objects;
import org.wahlzeit.services.DataObject;

public class Location extends DataObject{

    /*
    Locations 'Coordinates
    */

    public Coordinate coordinate;

    public Location(Coordinate coordinatenew){
        if(coordinatenew != null ){
        this.coordinate =coordinatenew.asCartesianCoordinate();
        }
        else{
            // create default Coord to ensure no failure
            Coordinate coord = new CartesianCoordinate(0,0,0);
            this.coordinate = coord; 
        }
    }
    public Location(ResultSet rset) throws SQLException{
        this.coordinate =new CartesianCoordinate(rset);
    }

    public Coordinate getCoordinate(){
        return this.coordinate.asCartesianCoordinate();
    }
    public void setCoordinate(Coordinate coord){
         this.coordinate =coord;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getCoordinate());
    }

    @Override
    public String getIdAsString(){
        throw new UnsupportedOperationException("Location has no Id");
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos){
        throw new UnsupportedOperationException("Location has no Id");
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        
        double x = rset.getDouble("loc_x_coord");
        double y = rset.getDouble("loc_y_coord");
        double z = rset.getDouble("loc_z_coord");
        this.coordinate = new CartesianCoordinate(x,y,z);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("loc_x_coord",this.coordinate.asCartesianCoordinate().getX());
        rset.updateDouble("loc_y_coord",this.coordinate.asCartesianCoordinate().getY());
        rset.updateDouble("loc_z_coord",this.coordinate.asCartesianCoordinate().getZ());
}
}