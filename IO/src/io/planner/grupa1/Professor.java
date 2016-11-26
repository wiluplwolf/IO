package io.planner.grupa1;

public class Professor {
    int id;

    public void setId(int id) {

        this.id = id;
    }
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getDegree(){
        return degree;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDegree(String degree){
        if(degree.equals("in?ynier"))
            degree = "inżynier";
        else if(degree.equals("magister in?ynier"))
            degree = "magister inżynier";
        else if(degree.equals("doktor in?ynier"))
            degree = "doktor inżynier";
        else if(degree.equals("profesor in?ynier"))
            degree = "profesor inżynier";
        
        for(int i=0; i<GlobalLists.degrees.size(); i++){
            if(GlobalLists.degrees.get(i).name.equals(degree))
                this.degree = GlobalLists.degrees.get(i).id;
        }

    }

    private String name;
    private String surname;
    private String mail;
    private int degree;

    public Professor()
    {
        this.id = 1;
        this.name = "Jan";
        this.surname = "Kowalski";
        this.mail = putMailTogether();
        this.degree = 0;
    }

    String putMailTogether(){
        String mail;
        mail = name.substring(0, 1);
        mail += surname;
        mail += "@mail.pl";
        return mail;
    }
}