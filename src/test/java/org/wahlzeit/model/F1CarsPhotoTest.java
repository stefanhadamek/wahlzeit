package org.wahlzeit.model;

import org.junit.*;
import java.sql.*;
import org.junit.*;
import org.mockito.*;
import org.mockito.Mockito.*;
import static org.mockito.Mockito.*;


public class F1CarsPhotoTest {

   @Mock
   private ResultSet rset;
   private ResultSet emptyrset; 

   @Before 
    public void init() throws SQLException{
        rset = mock(ResultSet.class);
        emptyrset = mock(ResultSet.class);
        when(rset.getString("car_model_generation")).thenReturn("3");
        when(rset.getString("car_model_machine_type")).thenReturn("rb_engine");
        when(rset.getString("owner_email_address")).thenReturn("totto@wolff.com");
        when(rset.getString("owner_home_page")).thenReturn("http://redbull.com");
   }

   
    
    @Test
    public void testwriteOn() throws SQLException {
        F1CarsPhoto ph = new F1CarsPhoto(rset);
        ph.writeOn(emptyrset);
        verify(emptyrset,times(1)).updateString(eq("car_model_generation"),eq("3"));
        verify(emptyrset,times(1)).updateString(eq("car_model_machine_type"),eq("rb_engine"));
    }
    
    @Test
    public void testReadOn() throws SQLException {
        F1CarsPhoto ph = new F1CarsPhoto(rset);
        
        Assert.assertEquals(new F1Cars("3",new F1CarsType("rb_engine")).getGeneration(), ph.getF1Car().getGeneration());
        Assert.assertEquals(new F1Cars("3",new F1CarsType("rb_engine")).getMachineType(), ph.getF1Car().getMachineType());
    }
    
    @Test
    public void testsetCars()throws SQLException{
        F1CarsPhoto ph = new F1CarsPhoto(rset);
        ph.setF1Cars(new F1Cars("3", new F1CarsType("rb_engine")));
        Assert.assertEquals("3",ph.getF1Car().getGeneration());
        Assert.assertEquals("rb_engine",ph.getF1Car().getMachineType().getMachineType());
    }
}