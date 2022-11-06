package org.wahlzeit.model;


import org.junit.*;
import  org.junit.Assert.*;


public class F1CarsPhotoFactoryTest {


    @Test
    public void testCreation(){
        F1CarsPhoto ph = F1CarsPhotoFactory.getFactory().createPhoto();
        Assert.assertNotNull(ph); 

    }
    @Test
    public void testCreationWithIds(){
        PhotoId one = new PhotoId(1);
        Assert.assertNotNull(F1CarsPhotoFactory.getFactory().createPhoto(one));
        PhotoId two = new PhotoId(2);
        Assert.assertNotNull(F1CarsPhotoFactory.getFactory().createPhoto(two));
        PhotoId three = new PhotoId(3);
        Assert.assertNotNull(F1CarsPhotoFactory.getFactory().createPhoto(three));
    }

    @Test
    public void testgetFactory(){
        Assert.assertNotNull(F1CarsPhotoFactory.getFactory());
    }
}