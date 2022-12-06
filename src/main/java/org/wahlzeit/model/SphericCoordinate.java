
package org.wahlzeit.model;
import static org.wahlzeit.model.Constants.epsilon;
import java.sql.*;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
public class SphericCoordinate extends AbstractCoordinate {

    private static final Map<Integer, SphericCoordinate> lookupCoordinates = new HashMap<>();

    final private Double radius;
    final private Double phi;
    final private Double theta;
    /*
    final private Double x_tocast;
    final private Double y_tocast;
    final private Double z_tocast;
    */
    //final private double epsilon =1.0E-5;


    public SphericCoordinate(double radius, double phi, double theta){
        this.radius = radius;
        this.phi = phi;
        this.theta = theta;
        assertClassInVariants();
    }

    public static SphericCoordinate GetNewCoord(ResultSet rset) throws SQLException{
        final double newRadius = rset.getDouble("loc_first_coord");
        final double newPhi = rset.getDouble("loc_second_coord");
        final double newTheta = rset.getDouble("loc_third_coord");
        final String type = rset.getString("loc_type_coord");
        return GetDBResult(newRadius,newPhi,newTheta);
    }
    /*public SphericCoordinate(final ResultSet rset) throws SQLException {
        this.radius = 0d;
        this.phi = 0d;
        this.theta = 0d; 
        readFrom(rset);
    }
    */
    public static SphericCoordinate GetDBResult(double radius,double phi, double theta){
        int key = Objects.hash(radius,phi,theta);
        if(!lookupCoordinates.containsKey(key)){
            lookupCoordinates.put(key, new SphericCoordinate(radius,phi,theta));
        }
        SphericCoordinate erg = lookupCoordinates.get(key);
        return erg; 
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

    /*
    public void setRadius(double radius){
        assertIsANumberAndNotInfinite(radius);
         this.radius= radius;
         incWriteCount();
    }
    public void setPhi(double phi){
        assertIsANumberAndNotInfinite(phi);
         this.phi = phi;
         incWriteCount();
    }
    public void setTheta(double theta){
        assertIsANumberAndNotInfinite(theta);
         this.theta = theta;
         incWriteCount();
    }
    */
    @Override
    public CartesianCoordinate asCartesianCoordinate(){
        double x = this.radius * Math.cos(this.phi) * Math.sin(this.theta);
        double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
        double z = this.radius * Math.cos(this.theta);
        return CartesianCoordinate.GetDBResult(x,y,z);
    }

    @Override 
    public SphericCoordinate asSphericCoordinate(){
        return this;
    }

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

    protected boolean isEqual(SphericCoordinate coord){
        if(coord == null){
            return false;
        }
        if(this == coord){
            return true;
            }
        boolean erg = this.isEqual((Coordinate) coord);
        return erg;
    }
    
    /*@Override
    protected void readFromCartesian(ResultSet rset) throws SQLException {
        if((radius == null)&& (phi == null) && (theta == null)){
            incWriteCount();
        }

        x_tocast = rset.getDouble("loc_x_coord");
        y_tocast = rset.getDouble("loc_y_coord");
        z_tocast = rset.getDouble("loc_z_coord");

        radius = Math.sqrt((x_tocast*x_tocast) +(y_tocast*y_tocast)+(z_tocast*z_tocast));
        phi = Math.atan2(y_tocast,x_tocast);
        theta = Math.acos(z_tocast/radius);
    }
    */
    @Override 
    protected void writeOnCartesian(ResultSet rset) throws SQLException{
        rset.updateDouble("loc_first_coord",radius);
        rset.updateDouble("loc_second_coord",phi);
        rset.updateDouble("loc_third_coord",theta);
        rset.updateInt("loc_type_coord",CoordinateType.SPH.ordinal());
    }

    @Override
    protected void assertClassInVariants() {
        assertIsANumberAndNotInfinite(radius);
        assertIsANumberAndNotInfinite(phi);
        assertIsANumberAndNotInfinite(theta);
    }



}