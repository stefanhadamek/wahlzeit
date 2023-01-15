package org.wahlzeit.model;

public class F1CarsType{

    private String machine_type;

    public F1CarsType(String type){
        this.machine_type = type;
        assertClassInVariants();
    }

    public String getMachineType(){
        return machine_type;
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