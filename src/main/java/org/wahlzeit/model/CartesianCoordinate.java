package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;
import java.sql.*;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

public class CartesianCoordinate extends AbstractCoordinate {

    final private Double x;
    final private Double y;
    final private Double z;
    //final private double epsilon =1.0E-5;
    private static final Map<Integer, CartesianCoordinate> lookupCoordinates = new HashMap<>();

    private CartesianCoordinate(double x,double y,double z){
        
        this.x=x;
        this.y=y;
        this.z=z;
        assertClassInVariants();
    }
    /*
    public CartesianCoordinate(ResultSet rset) throws SQLException{
        this.x = 0d;
        this.y = 0d;
        this.z = 0d;
        this.readFrom(rset);
    }
    */
     public static CartesianCoordinate GetNewCoord(ResultSet rset) throws SQLException{
        final double xnew = rset.getDouble("loc_first_coord");
        final double ynew = rset.getDouble("loc_second_coord");
        final double znew = rset.getDouble("loc_third_coord");
        return GetDBResult(xnew,ynew,znew);
    }

    public static CartesianCoordinate GetDBResult(double x,double y, double z){
        int key = Objects.hash(x,y,z);
        if(!lookupCoordinates.containsKey(key)){
            lookupCoordinates.put(key, new CartesianCoordinate(x,y,z));
        }
        CartesianCoordinate erg = lookupCoordinates.get(key);
        return erg; 
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

    /*
    public void setX(double setx){
        assertIsANumberAndNotInfinite(setx);
        this.x = setx;
        incWriteCount();
    }
    public void setY(double sety){
        assertIsANumberAndNotInfinite(sety);
        this.y = sety;
        incWriteCount();
    }
    public void setZ(double setz){
        assertIsANumberAndNotInfinite(setz);
        this.z = setz;
        incWriteCount();
    }
    */

    protected double getDistance(CartesianCoordinate coord){
        double zwierg= Math.pow(coord.x - this.x,2) + Math.pow(coord.y - this.y,2) + Math.pow(coord.z-this.z,2);
        double erg = Math.sqrt(zwierg);
        return erg;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate(){
        return this;
    }

    public boolean isEqual(CartesianCoordinate coord){
        if(coord == null){
            return false;
        }
        if(this == coord){
            return true;
        }
        
        boolean erg = checkDoubleEqual(this.x,coord.x) && checkDoubleEqual(this.y,coord.y) && checkDoubleEqual(this.z, coord.z);
        return erg;   
    }

    @Override
    public SphericCoordinate asSphericCoordinate(){
        CartesianCoordinate start = CartesianCoordinate.GetDBResult(0,0,0);
        double radius = this.getCartesianDistance(start);

        if(((Double) radius).equals(0.00)){
            return SphericCoordinate.GetDBResult(0,0,0);
        }
        double phi = Math.atan2(y,x);
        double theta= Math.acos(z/radius);
        return SphericCoordinate.GetDBResult(radius,phi,theta);
    }
    
    @Override
    public double getCartesianDistance(Coordinate other){
        double erg= this.getDistance(other.asCartesianCoordinate());
        return erg;
    }
    
    public int hashCode(){
        return Objects.hash(this.getX(),this.getY(),this.getZ());
    }
    /*
    @Override
    protected void readFromCartesian(ResultSet rset) throws SQLException {
        if((x == null)&& (y == null) && (z == null)){
            incWriteCount();
        }
        x = rset.getDouble("loc_x_coord");
        y = rset.getDouble("loc_y_coord");
        z = rset.getDouble("loc_z_coord"); 
        assertClassInVariants();
    }
    */
    @Override 
    protected void writeOnCartesian(ResultSet rset) throws SQLException{
        rset.updateDouble("loc_first_coord",x);
        rset.updateDouble("loc_second_coord",y);
        rset.updateDouble("loc_third_coord",z);
        rset.updateInt("coordinate_type",CoordinateType.CART.ordinal());
    }

    @Override
    protected void assertClassInVariants() {
        assertIsANumberAndNotInfinite(x);
        assertIsANumberAndNotInfinite(y);
        assertIsANumberAndNotInfinite(z);
        
    }
   
}