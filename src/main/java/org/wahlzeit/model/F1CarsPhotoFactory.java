package org.wahlzeit.model;


import java.sql.*;


public class F1CarsPhotoFactory extends PhotoFactory {

    private static F1CarsPhotoFactory factory = null;

    public F1CarsPhotoFactory(){
        super();
    }

    public static synchronized F1CarsPhotoFactory getFactory(){
        if(factory == null){

            setInstance(new F1CarsPhotoFactory());
        }
        return factory;
    }

    protected static synchronized void setFactory( F1CarsPhotoFactory carFactory){
        if(factory != null) {
            throw new IllegalStateException("Initialization of F1CaryPhotoFactory happened twice");

        }
        factory = carFactory;
    }
    public static void initialize() {
		getInstance();
    }

    @Override
    public F1CarsPhoto createPhoto(){
        return new F1CarsPhoto();
    }

    @Override
    public F1CarsPhoto createPhoto(PhotoId id){
        return new F1CarsPhoto(id);
    }
    @Override
    public F1CarsPhoto createPhoto(ResultSet rset) throws SQLException{
        return new F1CarsPhoto(rset);
    }
}