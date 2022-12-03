package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationTests{

    @Test
    public void CreateLocationTest(){
        double x = 42;
        double y= 187;
        double z= 1337;
        CartesianCoordinate coord = new CartesianCoordinate(x,y,z);
        Location loc = new Location(coord);
        assertEquals(coord,loc.getCoordinate());
    }
  
    @Test
    public void setCoordinateTestonEmptyLoc(){
        Coordinate co = null;
        Location loc = new Location(co);
        CartesianCoordinate coord = new CartesianCoordinate(1,8,7);
        loc.setCoordinate(coord);
        assertEquals(coord,loc.getCoordinate().asCartesianCoordinate());

    }
}