package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTests{

    @Test
    public void createLocationTest(){
        double x = 42;
        double y= 187;
        double z= 1337;
        CartesianCoordinate coord = new CartesianCoordinate(x,y,z);
      
        assertEquals(42.0,coord.getX(),0);
        assertEquals(187.0,coord.getY(),0);
        assertEquals(1337.0,coord.getZ(),0);
    }
    @Test
    public void createLocationTestSetMethods(){
        double x = 42.0;
        double y= 187.0;
        double z= 1337.0;
        CartesianCoordinate coord = new CartesianCoordinate(0,0,0);
        coord.setX(x);
        coord.setY(y);
        coord.setZ(z);
        assertEquals(x,coord.getX(),0);
        assertEquals(y,coord.getY(),0);
        assertEquals(z,coord.getZ(),0);
    }
    @Test
    public void getDistanceTester(){
        CartesianCoordinate th = new CartesianCoordinate(1,1,4);
        CartesianCoordinate zwei = new CartesianCoordinate(1,1,2);
        //System.out.println();
        System.out.println(th.getDistance(zwei));
        
        assertEquals(2.0, th.getDistance(zwei),0);

    }
    @Test
    public void checkisEquals(){
        CartesianCoordinate th = new CartesianCoordinate(1,1,1);
        CartesianCoordinate zwei = new CartesianCoordinate(1,1,1);
        assertEquals(true,th.isEqual(zwei));
        assertEquals(false,th.isEqual(null));
        
    }

    //TODO test the overwritten equals method
    @Test
    public void checkequals(){
        CartesianCoordinate th = new CartesianCoordinate(1,1,1);
        CartesianCoordinate zwei = new CartesianCoordinate(1,1,1);
        assertEquals(true,th.isEqual(zwei));
        assertEquals(false,th.isEqual(null));
        
    }
}
