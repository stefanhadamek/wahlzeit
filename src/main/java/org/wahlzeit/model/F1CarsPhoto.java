package org.wahlzeit.model;

import java.sql.*;

public class F1CarsPhoto extends Photo {

    private String model;

    public F1CarsPhoto(){
        super();
    }
    public F1CarsPhoto(PhotoId myID){
        super(myID);
    }
    public F1CarsPhoto(ResultSet rset) throws SQLException{
        super(rset);
    }
    
    public String getF1CarsPhotoModel(){
        return this.model;
    }
    public void setF1CarsPhotoModel(String model){
        this.model = model;
        incWriteCount();
    }
 
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        model = rset.getString("F1_Car_Model");
    }

    @Override 
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString("F1_Car_Model", model);
    }
}