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

    @Test
    public void checkIsSupType(){
        F1CarsType car = new F1CarsType("rb_engine");
        F1CarsType car2 = new F1CarsType("alp_tauri_engine");
        car.addSubtype(car2);
        Assert.assertTrue(car.isSubtype(new F1CarsType("alp_tauri_engine")));
        Assert.assertFalse(car2.isSubtype(new F1CarsType("ferrari_engine")));
    }

}