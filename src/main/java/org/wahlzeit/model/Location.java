package org.wahlzeit.model;

public class Location {

    /*
    Locations 'Coordinates
    */

    public Coordinate coordinate;

    public Location(Coordinate coordinatenew){
        this.coordinate =coordinatenew;
    }

    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    public void setCoordinate(Coordinate coord){
         this.coordinate =coord;
    }
}