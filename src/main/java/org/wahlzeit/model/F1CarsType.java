package org.wahlzeit.model;
import java.util.*;
public class F1CarsType{

    private String machine_type;
    private F1CarsType upperType;
    private Set<F1CarsType> subtypes = new HashSet<>();


    public F1CarsType(String type){
        this.machine_type = type;
        assertClassInVariants();
    }

    public boolean isSubtype(F1CarsType type){
        if(type ==null){
            throw new IllegalArgumentException("type shouldnt be null");
        }
        if(subtypes.contains(type)){
            return true;
        }
        return subtypes.stream().anyMatch(subtype -> subtype.isSubtype(type));
    }
    public void addSubtype(F1CarsType type){
        if(type ==null){
            throw new IllegalArgumentException("type shouldnt be null");
        }
        try{
        type.upperType= this.upperType;
        subtypes.add(type);
        }catch(NullPointerException e){
            throw e;
        }
    }

    public F1CarsType getUpperType(){
        return this.upperType;
    }
    public Set<F1CarsType> getSubType(){
        return this.subtypes;
    }
    public String getMachineType(){
        return this.machine_type;
    }


    public void assertClassInVariants(){
        if(this.machine_type==null){
            throw new IllegalArgumentException("Machine_type von einem F1Car muss bekannt sein.");
        }
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object instanceof F1CarsType){
            return this.machine_type.equals(((F1CarsType) object).getMachineType());
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash;
        if(machine_type != null){
            hash=machine_type.hashCode();
            }else{
                hash =0;
            }
            return hash;

    }
}