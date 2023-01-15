package org.wahlzeit.model;

import java.sql.*;
import org.junit.*;


public class F1CarsTypeTest {


    @Test
    public void testEqualsTypes(){
        F1CarsType car = new F1CarsType("merc_engine");
       
        Assert.assertTrue(car.equals(new F1CarsType("merc_engine")));
        Assert.assertFalse(car.equals(new F1CarsType("ferrari_engine")));
    }

}