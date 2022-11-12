package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTests{

    @Test
    public void createCarCoordTest(){
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
        //System.out.println(th.getCartesianDistance(zwei));
        
        assertEquals(2.0, th.getCartesianDistance(zwei),0);

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
    public void checkequalsSpheric(){
        SphericCoordinate th = new SphericCoordinate(1,1,1);
        SphericCoordinate zwei = new SphericCoordinate(1,1,1);
        assertEquals(true,th.isEqual(zwei));
        assertEquals(false,th.isEqual(null));
        
    }

    @Test 
    public void testCentralAngle(){
        CartesianCoordinate coord = new CartesianCoordinate(1,2,3);
        CartesianCoordinate other = new CartesianCoordinate(3,2,1);

        double same = coord.getCentralAngle(coord);
        assertEquals(0.0, same,0.001);
        same = other.getCentralAngle(other);
        assertEquals(0.0,same,0.001);

        double tooother = coord.getCentralAngle(other);
        assertEquals(0.7751933733103613,tooother,0.0001);
    }
    
    @Test
    public void testCartesianToSpheric(){
        CartesianCoordinate coord = new CartesianCoordinate(1,2,3);
        SphericCoordinate neu = coord.asSphericCoordinate();

        assertEquals(3.7416573867739413,neu.getRadius(),0.001);
        assertEquals(1.1071487177940904,neu.getPhi(),0.001);
       

    }
}
