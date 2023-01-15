package org.wahlzeit.model;

import org.junit.*;


public class F1CarsManagerTest{

    @Test
    public void createObjectfromManagervsfromclass(){
        F1Cars car = F1CarsManager.getManager().createOrAquireF1Cars("3",new F1CarsType("rb_engine"));
        
        F1Cars car2 = new F1Cars("3",new F1CarsType("rb_engine"));
        Assert.assertEquals(car.getGeneration(),car2.getGeneration());
        Assert.assertEquals(car.getMachineType(),car2.getMachineType());

    }
}