package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationTests{

    @Test
    public void CreateLocationTest(){
        double x = 42;
        double y= 187;
        double z= 1337;
        Coordinate coord = new Coordinate(x,y,z);
        Location loc = new Location(coord);
        assertEquals(coord,loc.getCoordinate());
    }
    @Test
    public void CreateEmptyLocationTest(){
        Location loc = new Location(null);
        assertEquals(null,loc.getCoordinate());
    }
    
    @Test
    public void setCoordinateTestonEmptyLoc(){
        Location loc = new Location(null);
        Coordinate coord = new Coordinate(1,8,7);
        loc.setCoordinate(coord);
        assertEquals(coord,loc.getCoordinate());

    }
}