package io.planner.grupa1;

public class Room {
    public int id;
    public String number;
    public String building;
    public int type;

    public int getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public String getBuilding() {
        return building;
    }
    public int getType() {
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setBuilding(String building) {
        this.building = building;
    }

    public Room(){
        this.id = 1;
        this.number = "1";
        this.building = "1";
        this.type = 0;
    }

}