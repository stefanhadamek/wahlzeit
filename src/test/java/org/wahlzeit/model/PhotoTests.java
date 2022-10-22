package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PhotoTests{

    @Test
    public void createPhotoWithNoLocation(){
        Photo ph= new Photo();
        assertEquals(null,ph.getLocation());
    }
    @Test
    public void createLocationTestSetMethods(){
        Photo ph= new Photo();
        Coordinate coord = new Coordinate(42,187,1337);
        Location loc = new Location(coord);
        ph.setLocation(loc);
        assertEquals(loc,ph.getLocation());

        Photo ph2= new Photo();
        //Coordinate coord2 = new Coordinate(42,187,1337);
        Location loc2 = null;
        ph.setLocation(loc2);
        assertEquals(null,ph.getLocation());
    }

    
}