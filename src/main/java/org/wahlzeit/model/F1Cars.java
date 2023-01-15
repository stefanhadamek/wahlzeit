package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import java.sql.*;



public class F1Cars extends DataObject{

    private String generation;
    private F1CarsType machine_type;

    public F1Cars(ResultSet rset) throws SQLException{
        readFrom(rset);
        this.assertClassInVariants();
    }

    public F1Cars(String gen, F1CarsType typ){
        this.generation= gen;
        this.machine_type = typ;
        this.assertClassInVariants();
    }

    public F1CarsType getMachineType(){
        return this.machine_type;
    }

    public String getGeneration(){
        return this.generation;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException{
        this.generation = rset.getString("car_model_generation");
        this.machine_type = F1CarsManager.getManager().createOrAquireF1CarsType(rset.getString("car_model_machine_type"));
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException{
        rset.updateString("car_model_generation",this.generation);
        rset.updateString("car_model_machine_type",machine_type.getMachineType());
    }

    @Override
    public String getIdAsString() {
        throw new UnsupportedOperationException("F1Car has no Id.");
    }

    @Override
    public void writeId(PreparedStatement state, int pos) throws SQLException {
        throw new UnsupportedOperationException("F1Car has no Id.");
    }

    //TODO equal and hashCode();

    public void assertClassInVariants(){
        if(this.machine_type == null){
            throw new IllegalArgumentException("F1Car must have a typeofmachine");
        }
        if(this.generation ==null){
            throw new IllegalArgumentException("F1Car must be a kind of generation");
        }
    }

    @Override
    public int hashCode() {
        int result;
        int id;

        if(getGeneration() != null){
            result = getGeneration().hashCode();
        }else{
            result = 0;

        }
        if(getMachineType() != null){
            id= getMachineType().hashCode();
        }else {
            id =0;
        }
        result = 31 * result + id;
        return result;
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object instanceof F1Cars){
            //
            return this.generation.equals(((F1Cars) object).getGeneration());
        }
        return false;
    }
}