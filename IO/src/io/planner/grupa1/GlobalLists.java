package io.planner.grupa1;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class GlobalLists {
    public static List<Professor> professors = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();
    public static List<Group> groups = new ArrayList<>();
    public static List<Classes> classes = new ArrayList<>();

    public static List<IDName> types = new ArrayList<>();
    public static List<IDName> subjects = new ArrayList<>();
    public static List<IDName> degrees = new ArrayList<>();
    public static List<IDName> specialization = new ArrayList<>();

    public static void fillTypes(){
        IDName GA = new IDName(1, "audytoryjna");
        types.add(GA);
        IDName GL = new IDName(2, "laboratoryjna");
        types.add(GL);
        IDName GP = new IDName(3, "projektowa");
        types.add(GP);
        IDName WF = new IDName(4, "WF");
        types.add(WF);
        IDName GW = new IDName(5, "wykładowa");
        types.add(GW);
    }

    public static String getSpecializationName(int id) {
        String specializationName = "NOT FOUND";
        for (int i = 0; i < specialization.size(); i++) {
            if (id == specialization.get(i).id)
                specializationName = specialization.get(i).name;
        }
        return specializationName;
    }

    public static String getDegreeName(int degree) {
        String degreeName = "NOT FOUND";
        for(int i=0; i<degrees.size(); i++){
            if(degree == degrees.get(i).id)
                degreeName = degrees.get(i).name;
        }
        return degreeName;
    }
}
