package org.wahlzeit.model;
import org.wahlzeit.utils.PatternInstance;
import java.util.Objects;
import java.sql.*;


@PatternInstance(
    patternName = "Abstract Factory",
    participants= {
        "AbstractProduct",
    }
)
public class F1CarsPhoto extends Photo {

    private F1Cars car;

    public F1CarsPhoto(){
        super();
    }
    public F1CarsPhoto(PhotoId myID){
        super(myID);
    }
    public F1CarsPhoto(ResultSet rset) throws SQLException{
        readFrom(rset);
    }
    
    public F1Cars getF1Car(){
        return this.car;
    }
    public void setF1CarsPhotoModel(F1Cars car){
        this.car = car;
        incWriteCount();
    }
 
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        car = F1CarsManager.getManager().createObject(rset);
    }

    @Override 
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        if(Objects.nonNull(car)) car.writeOn(rset);
    }
}