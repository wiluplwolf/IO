package io.planner.grupa1;

public class Classes {
    int id;
    private int amountOfHours;
    private IDName subject;
    private Professor professor;
    private Room room;
    private Group group;

    public int getAmountOfHours() {
        return amountOfHours;
    }
    public int getId(){
        return id;
    }
    public IDName getSubject() {
        return subject;
    }
    public Professor getProfessor() {
        return professor;
    }
    public Room getRoom() {
        return room;
    }
    public Group getGroup() {
        return group;
    }
    public void setAmountOfHours(int amountOfHours) {
        this.amountOfHours = amountOfHours;
    }
    public void setSubject(IDName subject) {
        this.subject = subject;
    }
    public void setProfessor(Professor professor){
        this.professor = professor;
    }
    public void setRoom(Room room){
        this.room = room;
    }
    public void setGroup(Group group){
        this.group = group;
    }

    public Classes()
    {
        this.id = 1;
        this.amountOfHours = 15;
        this.subject = new IDName();
        this.professor = new Professor();
        this.room = new Room();
        this.group = new Group();
    }
}
