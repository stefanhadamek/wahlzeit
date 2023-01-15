package org.wahlzeit.model;

import org.junit.*;
import java.sql.*;


public class F1CarsTest {

    @Test
    public void testgetF1Type(){
        F1Cars car = new F1Cars("3",new F1CarsType("rb_engine"));
        Assert.assertEquals(new F1CarsType("rb_engine"),car.getMachineType());
    }
    @Test
    public void testgetF1Gen(){
        F1Cars car = new F1Cars("3",new F1CarsType("rb_engine"));
        Assert.assertEquals("3",car.getGeneration());
    }

    @Test
    public void testEquals(){
        F1Cars car = new F1Cars("3",new F1CarsType("rb_engine"));
        Assert.assertTrue(car.equals(new F1Cars("3",new F1CarsType("rb_engine"))));
        Assert.assertFalse(car.equals(new F1Cars("4",new F1CarsType("rb_engine"))));
    }
}