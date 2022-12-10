package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.*;
import org.mockito.Mockito.*;
import org.junit.Test;
import java.sql.SQLException;
import java.sql.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CoordinateTests{

    @Test
    public void createCarCoordTest(){
        double x = 42;
        double y= 187;
        double z= 1337;
        CartesianCoordinate coord = CartesianCoordinate.GetDBResult(x,y,z);
      
        assertEquals(42.0,coord.getX(),0);
        assertEquals(187.0,coord.getY(),0);
        assertEquals(1337.0,coord.getZ(),0);
    }
    
    @Test
    public void getDistanceTester(){
        CartesianCoordinate th = CartesianCoordinate.GetDBResult(1,1,4);
        CartesianCoordinate zwei = CartesianCoordinate.GetDBResult(1,1,2);
        SphericCoordinate a = SphericCoordinate.GetDBResult(1,2,3);
        SphericCoordinate b = SphericCoordinate.GetDBResult(4,5,6);
        assertEquals(4.928711692867161, a.getCartesianDistance(b),0);
        assertEquals(2.0, th.getCartesianDistance(zwei),0);
    }
    @Test
    public void checkisEquals(){
        CartesianCoordinate one = CartesianCoordinate.GetDBResult(1,1,1);
        CartesianCoordinate two = CartesianCoordinate.GetDBResult(1,1,1);
        assertEquals(true,one.isEqual(two));
        assertEquals(false,one.isEqual(null));
        assertEquals(one,two);

        SphericCoordinate one2 = SphericCoordinate.GetDBResult(1,1,1);
        SphericCoordinate two2 = SphericCoordinate.GetDBResult(1,1,1);

        assertEquals(one2,two2);
        
    }

    @Test
    public void checkequalsSpheric(){
        SphericCoordinate th = SphericCoordinate.GetDBResult(1,1,1);
        SphericCoordinate zwei = SphericCoordinate.GetDBResult(1,1,1);
        assertEquals(true,th.isEqual(zwei));
        assertEquals(false,th.isEqual(null));
    }

    @Test 
    public void testCentralAngle(){
        CartesianCoordinate coord = CartesianCoordinate.GetDBResult(1,2,3);
        CartesianCoordinate other = CartesianCoordinate.GetDBResult(3,2,1);

        double same = coord.getCentralAngle(coord);
        assertEquals(0.0, same,0.001);
        same = other.getCentralAngle(other);
        assertEquals(0.0,same,0.001);

        double tooother = coord.getCentralAngle(other);
        assertEquals(0.7751933733103613,tooother,0.0001);
    }
    
    @Test
    public void testCartesianToSpheric(){
        CartesianCoordinate coord = CartesianCoordinate.GetDBResult(1,2,3);
        SphericCoordinate neu = coord.asSphericCoordinate();

        
        //angle in rad btw
        assertEquals(3.7416573867739413,neu.getRadius(),0.001);
        assertEquals(1.1071487177940904,neu.getPhi(),0.001);
        assertEquals(0.6405223126794245,neu.getTheta(),0.001);
               
    }
    @Test
    public void testSphericToCartesian(){
        SphericCoordinate coord = SphericCoordinate.GetDBResult(1,2,3);
        CartesianCoordinate neu = coord.asCartesianCoordinate();
        assertEquals(-0.05872664492762098,neu.getX(),0.001);
        assertEquals(0.12832006020245673,neu.getY(),0.001);
        assertEquals(-0.9899924966004454,neu.getZ(),0.001);       
    }
    @Mock
    private ResultSet rset;

    @Before 
    public void init(){
        rset = mock(ResultSet.class);
    }
    @Test
    public void testReadFromSpheric() throws SQLException{
        when(rset.getDouble(eq("loc_first_coord"))).thenReturn(1.37);
        when(rset.getDouble(eq("loc_second_coord"))).thenReturn(0.69); //nice
        when(rset.getDouble(eq("loc_third_coord"))).thenReturn(0.42);
        SphericCoordinate coord = SphericCoordinate.GetNewCoord(rset);
        SphericCoordinate other = SphericCoordinate.GetDBResult(1.37,0.69,0.42);
        assertEquals(coord, other);
    }
    @Test
    public void testReadFromCartesian() throws SQLException{
        when(rset.getDouble(eq("loc_first_coord"))).thenReturn(1.37);
        when(rset.getDouble(eq("loc_second_coord"))).thenReturn(0.69); //nice
        when(rset.getDouble(eq("loc_third_coord"))).thenReturn(0.42);
        CartesianCoordinate coord = CartesianCoordinate.GetNewCoord(rset);
        CartesianCoordinate other = CartesianCoordinate.GetDBResult(1.37, 0.69, 0.42);
        assertEquals(coord, other);
    }

    @Test
    public void testWriteOnCartesian() throws SQLException {
        CartesianCoordinate coord = CartesianCoordinate.GetDBResult(1.37, 0.69, 0.42);
        coord.writeOn(rset);
        verify(rset, times(1)).updateDouble(eq("loc_first_coord"), anyDouble());
        verify(rset, times(1)).updateDouble(eq("loc_second_coord"), anyDouble());
        verify(rset, times(1)).updateDouble(eq("loc_third_coord"), anyDouble());
    }

    @Test
    public void testEqualsCartesian() {
        final CartesianCoordinate coord = CartesianCoordinate.GetDBResult(1.37, 0.69, 0.42);
        final CartesianCoordinate other = CartesianCoordinate.GetDBResult(1.37, 0.69, 0.42);
        assertTrue(coord.equals(other));
    }

    @Test
    public void testEqualsSpheric() {
        final SphericCoordinate coord = SphericCoordinate.GetDBResult(1.37, 0.69, 0.42);
        final SphericCoordinate other = SphericCoordinate.GetDBResult(1.37, 0.69, 0.42);
        assertTrue(coord.equals(other));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetIdAsString() {
        SphericCoordinate coordA = SphericCoordinate.GetDBResult(1.37, 0.69, 0.42);
        CartesianCoordinate coordB = CartesianCoordinate.GetDBResult(1.37, 0.69, 0.42);
        coordA.getIdAsString();
        coordB.getIdAsString();
    }

}
