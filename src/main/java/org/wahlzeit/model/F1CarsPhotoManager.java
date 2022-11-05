package org.wahlzeit.model;

import java.sql.*;

public class F1CarsPhotoManager extends PhotoManager {

    private static final F1CarsPhotoManager instance = new F1CarsPhotoManager();

    public F1CarsPhotoManager(){
        super();
    }

    public static F1CarsPhotoManager getManager(){
        return instance;
    }

    public F1CarsPhoto createPhoto(File file ) throws Exception {
        return (F1CarsPhoto) (super.createPhoto(file));
    }

    protected F1CarsPhoto createObject(Result rset) throws SQLException {
        return F1CarsPhotoFactory.getFactory().createPhoto(rset);
    }
}


