package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTests{

    @Test
    public void createLocationTest(){
        double x = 42;
        double y= 187;
        double z= 1337;
        Coordinate coord = new Coordinate(x,y,z);
      
        assertEquals(42.0,coord.getX(),0);
        assertEquals(187.0,coord.getY(),0);
        assertEquals(1337.0,coord.getZ(),0);
    }
    @Test
    public void createLocationTestSetMethods(){
        double x = 42.0;
        double y= 187.0;
        double z= 1337.0;
        Coordinate coord = new Coordinate(0,0,0);
        coord.setX(x);
        coord.setY(y);
        coord.setZ(z);
        assertEquals(x,coord.getX(),0);
        assertEquals(y,coord.getY(),0);
        assertEquals(z,coord.getZ(),0);
    }
}