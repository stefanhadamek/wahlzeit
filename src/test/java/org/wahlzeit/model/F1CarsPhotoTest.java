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
        when(rset.getString("car_model")).thenReturn("rbv2");
        when(rset.getString("owner_email_address")).thenReturn("totto@wolff.com");
        when(rset.getString("owner_home_page")).thenReturn("http://redbull.com");
   }
    /*
    @Test
    public void testwriteOn() throws SQLException {
        F1CarsPhoto ph = new F1CarsPhoto(rset);
        ph.writeOn(emptyrset);
        verify(emptyrset,times(1)).updateString(eq("car_model"),eq("rbv2"));
    }
    @Test
    public void testReadOn() throws SQLException {
        F1CarsPhoto ph = new F1CarsPhoto(rset);
        
        Assert.assertEquals("rbv2", ph.getF1CarsPhotoModel());
    }
    */
}