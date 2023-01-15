package org.wahlzeit.model;

import org.wahlzeit.services.*;
import java.util.*;
import java.sql.*;


public class F1CarsManager extends ObjectManager{
    
    private static final F1CarsManager manager= new F1CarsManager();
    private Map<String, F1CarsType> F1CarsTypeMap = new HashMap<>();

    public F1CarsManager(){}

    public static F1CarsManager getManager(){
        return manager;
    }
    public Map<String,F1CarsType> getF1CarsTypeMap(){
        return F1CarsTypeMap;
    }

    public F1CarsType createOrAquireF1CarsType(String name) {
        return F1CarsTypeMap.computeIfAbsent(name, F1CarsType::new);
    }

    @Override
    protected F1Cars createObject(ResultSet rset)throws SQLException{
        return new F1Cars(rset);
    }
}