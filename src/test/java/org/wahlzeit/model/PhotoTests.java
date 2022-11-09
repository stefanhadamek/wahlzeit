package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import java.sql.ResultSet;
import java.sql.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.utils.StringUtil;
import org.wahlzeit.services.Language;

@RunWith(MockitoJUnitRunner.class)
public class PhotoTests{

    @Test
    public void createPhotoWithNoLocation(){
        Photo ph= new Photo();
        assertEquals(null,ph.getLocation());
    }
    @Test
    public void createLocationTestSetMethods(){
        Photo ph= new Photo();
        CartesianCoordinate coord = new CartesianCoordinate(42,187,1337);
        Location loc = new Location(coord);
        ph.setLocation(loc);
        assertEquals(loc,ph.getLocation());

        Photo ph2= new Photo();
        //Coordinate coord2 = new Coordinate(42,187,1337);
        Location loc2 = null;
        ph.setLocation(loc2);
        assertEquals(null,ph.getLocation());
    }
    @Mock
    private ResultSet rset;

    private Photo initPhotoMocking(ResultSet rset) throws SQLException{
        when(rset.getInt("id")).thenReturn(1);
        when(rset.getInt("owner_id")).thenReturn(1);
        when(rset.getString("owner_name")).thenReturn("totto");
        when(rset.getBoolean("owner_notify_about_praise")).thenReturn(false);
        when(rset.getString("owner_email_address")).thenReturn("totto@totto");
        when(rset.getInt("owner_language")).thenReturn(0);
        when(rset.getString("owner_home_page")).thenReturn("https://www.google.com/");
        when(rset.getInt("width")).thenReturn(187);
        when(rset.getInt("height")).thenReturn(6969);
        when(rset.getString("tags")).thenReturn("rbv10");
        when(rset.getInt("status")).thenReturn(6);
        when(rset.getInt("praise_sum")).thenReturn(8);
        when(rset.getInt("no_votes")).thenReturn(0);
        when(rset.getLong("creation_time")).thenReturn(187187187L);
        when(rset.getDouble(eq("loc_x_coord"))).thenReturn(1.87);
        when(rset.getDouble(eq("loc_y_coord"))).thenReturn(1.337);
        when(rset.getDouble(eq("loc_z_coord"))).thenReturn(1.69);
        
        return new Photo(rset);
    }

    @Test
    public void testReadFrom() throws SQLException {
        final Photo ph = initPhotoMocking(rset);
        assertEquals(new PhotoId(1), ph.getId());
        assertEquals(1, ph.getOwnerId());
        assertEquals("totto", ph.getOwnerName());
        assertFalse(ph.getOwnerNotifyAboutPraise());
        assertEquals(EmailAddress.getFromString("totto@totto"), ph.getOwnerEmailAddress());
        assertEquals(Language.ENGLISH, ph.getOwnerLanguage());
        assertEquals(StringUtil.asUrl("https://www.google.com/"), ph.getOwnerHomePage());
        assertEquals(187, ph.getWidth());
        assertEquals(6969, ph.getHeight());

        assertEquals(new Tags("rbv10"), ph.getTags());
        assertEquals(PhotoStatus.getFromInt(6), ph.getStatus());
        
        assertEquals(187187187L, ph.getCreationTime());
        Location loc = new Location(new CartesianCoordinate(1.87,1.337,1.69));

        //assertEquals(true,loc.getCoordinate().isEqual(ph.getLocation().getCoordinate().asCartesianCoordinate()));
    }

    @Test(expected = NullPointerException.class)
    public void test_WriteOn_Null() throws SQLException {
        Photo ph = new Photo();
        ph.writeOn(rset);
    }
    
    @Test
    public void testwriteOn() throws SQLException{
        Photo ph = initPhotoMocking(rset);
        ph.writeOn(rset);
        verify(rset, times(1)).updateInt(eq("id"), anyInt());
        verify(rset, times(1)).updateInt(eq("owner_id"), anyInt());
        verify(rset, times(1)).updateString(eq("owner_name"), anyString());
        verify(rset, times(1)).updateBoolean(eq("owner_notify_about_praise"), anyBoolean());
        verify(rset, times(1)).updateString(eq("owner_email_address"), anyString());
        verify(rset, times(1)).updateInt(eq("owner_language"), anyInt());
        verify(rset, times(1)).updateString(eq("owner_home_page"), anyString());
        verify(rset, times(1)).updateInt(eq("width"), anyInt());
        verify(rset, times(1)).updateInt(eq("height"), anyInt());
        verify(rset, times(1)).updateString(eq("tags"), anyString());
        verify(rset, times(1)).updateInt(eq("status"), anyInt());
        verify(rset, times(1)).updateInt(eq("praise_sum"), anyInt());
        verify(rset, times(1)).updateInt(eq("no_votes"), anyInt());
        verify(rset, times(1)).updateLong(eq("creation_time"), anyLong());
        verify(rset, times(1)).updateDouble(eq("loc_x_coord"), anyDouble());
        verify(rset, times(1)).updateDouble(eq("loc_y_coord"), anyDouble());
        verify(rset, times(1)).updateDouble(eq("loc_z_coord"), anyDouble());
    }
}