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
    
    @Test 
    public void testReadOn() throws SQLException {
        when(rset.getString("car_model_generation")).thenReturn("3");
        when(rset.getString("car_model_machine_type")).thenReturn("rb_engine");
        when(rset.getString("owner_email_address")).thenReturn("totto@wolff.com");
        when(rset.getString("owner_home_page")).thenReturn("http://redbull.com");
        F1CarsPhoto ph = F1CarsPhotoManager.getManager().createObject(rset);
        Assert.assertEquals("3", ph.getF1Car().getGeneration());
        Assert.assertEquals("rb_engine", ph.getF1Car().getMachineType().getMachineType());
    }
    

}

