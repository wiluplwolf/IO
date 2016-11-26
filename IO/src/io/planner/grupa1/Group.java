package io.planner.grupa1;

public class Group {
    public int id;
    public String name;
    public int year;
    public int type;
    public int specialization;

    public String getName() {
        return name;
    }
    public int getId(){
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getSpecialization(){
        return specialization;
    }

    public void setSpecialization(String specialization){
        for(int i=0; i<GlobalLists.specialization.size(); i++){
            if(GlobalLists.specialization.get(i).name.equals(specialization))
                this.specialization = GlobalLists.specialization.get(i).id;
        }

    }
    public void setName() {
        this.name = generateName();
    }

    public void setYear(int year) {
        this.year = year;
    }

    //kierunek get i set
    public String generateName() {
        name = "CA-";
        String groupNumber;
        groupNumber = String.valueOf(groupsFromThisYear() + 1);
        name = name + groupNumber + "-";
        name = name + String.valueOf(year);
        return name;
    }
    public Group() {
        id = 0;
        year = 0;
        name = "NOT DEFINED";
        type = 0;
        specialization = 0;
    }

    int groupsFromThisYear(){
        int thisYearGroups = 0;
        for(int i=0; i<GlobalLists.groups.size(); i++){
            if(GlobalLists.groups.get(i).getYear() == this.year && GlobalLists.groups.get(i).specialization == this.specialization)
                thisYearGroups++;
        }
        return thisYearGroups;
    }


}