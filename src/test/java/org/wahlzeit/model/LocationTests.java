package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.wahlzeit.model.Constants;
public class LocationTests{

    @Test
    public void CreateLocationTest(){
        double x = 42;
        double y= 187;
        double z= 1337;
        CartesianCoordinate coord = CartesianCoordinate.GetDBResult(x,y,z);
        Location loc = new Location(coord);
        assertEquals(coord,loc.getCoordinate());
        assertEquals(coord.getX(),loc.getCoordinate().asCartesianCoordinate().getX(),Constants.epsilon);
        assertEquals(coord.getY(),loc.getCoordinate().asCartesianCoordinate().getY(),Constants.epsilon);
        assertEquals(coord.getZ(),loc.getCoordinate().asCartesianCoordinate().getZ(),Constants.epsilon);
    }
  
    @Test
    public void setCoordinateTestonEmptyLoc(){
        Coordinate co = null;
        Location loc = new Location(co);
        CartesianCoordinate coord = CartesianCoordinate.GetDBResult(1,8,7);
        loc.setCoordinate(coord);
        assertEquals(coord,loc.getCoordinate().asCartesianCoordinate());
       


    }


}