package org.wahlzeit.model;

public class Location {

    /*
    Locations 'Coordinates
    */

    public Coordinate coordinate;

    public Location(Coordinate coordinatenew){
        if(coordinatenew != null ){
        this.coordinate =coordinatenew.asCartesianCoordinate();
        }
        else{
            // create default Coord to ensure no failure
            Coordinate coord = new CartesianCoordinate(0,0,0);
            this.coordinate = coord;
            
        }
    }

    public Coordinate getCoordinate(){
        return this.coordinate.asCartesianCoordinate();
    }
    public void setCoordinate(Coordinate coord){
         this.coordinate =coord;
    }
}