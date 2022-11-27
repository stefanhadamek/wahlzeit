package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;
import java.sql.*;
import java.util.Objects;
public class CartesianCoordinate extends AbstractCoordinate {

    private Double x;
    private Double y;
    private Double z;
    //final private double epsilon =1.0E-5;


    public CartesianCoordinate(double x,double y,double z){
        
        this.x=x;
        this.y=y;
        this.z=z;
        assertClassInVariants();
    }

    public CartesianCoordinate(ResultSet rset) throws SQLException{
        this.x = 0d;
        this.y = 0d;
        this.z = 0d;
        this.readFrom(rset);
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
        assertIsANumberAndNotInfinite(setx);
        this.x = setx;
    }
    public void setY(double sety){
        assertIsANumberAndNotInfinite(sety);
        this.y = sety;
    }
    public void setZ(double setz){
        assertIsANumberAndNotInfinite(setz);
        this.z = setz;
    }

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
    public double getCartesianDistance(Coordinate other){
        double erg= this.getDistance(other.asCartesianCoordinate());
        return erg;
    }
    
    public int hashCode(){
        return Objects.hash(this.getX(),this.getY(),this.getZ());
    }

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

    @Override 
    protected void writeOnCartesian(ResultSet rset) throws SQLException{
        rset.updateDouble("loc_x_coord",x);
        rset.updateDouble("loc_y_coord",y);
        rset.updateDouble("loc_z_coord",z);
    }

    @Override
    protected void assertClassInVariants() {
        assertIsANumberAndNotInfinite(x);
        assertIsANumberAndNotInfinite(y);
        assertIsANumberAndNotInfinite(z);
        
    }
   
}