package org.wahlzeit.model;

import java.sql.*;
import org.junit.*;
import org.mockito.*;
import org.mockito.Mockito.*;
import static org.mockito.Mockito.*;

public class F1CarsPhotoManagerTest{
    @Mock
    private ResultSet rset;

    @Before 
    public void init(){
        rset = mock(ResultSet.class);
    }
    /*
    @Test 
    public void testReadOn() throws SQLException {
        when(rset.getString("car_model")).thenReturn("rbv2");
        when(rset.getString("owner_email_address")).thenReturn("totto@wolff.com");
        when(rset.getString("owner_home_page")).thenReturn("http://redbull.com");
        F1CarsPhoto ph = F1CarsPhotoManager.getManager().createObject(rset);
        Assert.assertEquals("rbv2", ph.getF1CarsPhotoModel());
    }
    */

}

