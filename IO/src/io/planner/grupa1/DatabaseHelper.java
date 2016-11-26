package io.planner.grupa1;

import java.lang.*;
import java.sql.*;

public class DatabaseHelper {
    public static Connection con;

    public static void connect () {
        try{
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://mysql.agh.edu.pl:3306/wwydryc1","wwydryc1","V8XwxsFN");
            }
        catch(Exception e){
            System.out.println(e);
        }
    }

    //CONSTANTS
    public static void getAllDegrees() {
        String getAllDegrees = "SELECT * FROM stopnie";
        try {
            PreparedStatement stmt = con.prepareStatement(getAllDegrees);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                while(rs.next()){
                    IDName d = new IDName();
                    d.id = rs.getInt("ID");
                    d.name = rs.getString("nazwa");
                    GlobalLists.degrees.add(d);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void getAllSubjects() {
        String getAllSubjects = "SELECT * FROM przedmiot";
        try {
            PreparedStatement stmt = con.prepareStatement(getAllSubjects);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                while(rs.next()){
                    IDName s = new IDName();
                    s.id = rs.getInt("ID");
                    s.name = rs.getString("nazwa");
                    GlobalLists.subjects.add(s);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void getAllSpecializations() {
        String getAllSpecializations = "SELECT * FROM kierunek";
        try {
            PreparedStatement stmt = con.prepareStatement(getAllSpecializations);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                while(rs.next()){
                    IDName s = new IDName();
                    s.id = rs.getInt("ID");
                    s.name = rs.getString("nazwa_kierunku");
                    GlobalLists.specialization.add(s);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    //DOWNLOAD
    public static void getAllProfessors(){
        connect();
        String getAllProfessors = "SELECT * FROM prowadzacy";
        try {
            PreparedStatement stmt = con.prepareStatement(getAllProfessors);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            System.out.println("wykonano RS");
            if(rs != null){
                GlobalLists.professors.clear();
                while(rs.next()){
                    Professor p = new Professor();
                    p.setId(rs.getInt("ID"));
                    p.setName(rs.getString("imie"));
                    p.setSurname(rs.getString("nazwisko"));
                    p.setDegree(rs.getString("tytul"));
                    p.setMail(rs.getString("mail"));
                    GlobalLists.professors.add(p);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
    }

    public static void getAllGroups (){
        connect();
        String getAllGroups = "SELECT * FROM grupy";
        try {
            PreparedStatement stmt = con.prepareStatement(getAllGroups);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            System.out.println("wykonano RS");
            if(rs != null){
                GlobalLists.groups.clear();
                while(rs.next()){
                    Group g = new Group();
                    g.id = rs.getInt("ID");
                    g.name = rs.getString("nazwa");
                    g.year = rs.getInt("rok_rozp");
                    g.specialization = rs.getInt("kierunek");
                    GlobalLists.groups.add(g);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
    }

    public static void getAllRooms(){
        String getAllRooms = "SELECT * FROM sale";
        connect();
        try {
            PreparedStatement stmt = con.prepareStatement(getAllRooms);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            System.out.println("wykonano RS");
            if(rs != null){
                GlobalLists.rooms.clear();
                while(rs.next()){
                    Room r = new Room();
                    r.id = rs.getInt("ID");
                    r.number = rs.getString("sala");
                    r.building = rs.getString("budynek");
                    String typ = rs.getString("typ");
                    for(int i=0; i<GlobalLists.types.size(); i++){
                        if(GlobalLists.types.get(i).name.equals(typ))
                            r.type = GlobalLists.types.get(i).id;
                    }
                    GlobalLists.rooms.add(r);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
    }

    public static void getAllClasses(){
        String getAllClasses = "SELECT * FROM zajecia";
        connect();
        try {
            PreparedStatement stmt = con.prepareStatement(getAllClasses);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                GlobalLists.classes.clear();
                while(rs.next()){
                    Classes c = new Classes();
                    c.id = rs.getInt("ID");
                    c.setAmountOfHours(rs.getInt("lgodzin"));

                    for(int i=0; i<GlobalLists.subjects.size(); i++){
                        if(GlobalLists.subjects.get(i).id == rs.getInt("przedmiot"))
                            c.setSubject(GlobalLists.subjects.get(i));
                    }
                    for(int i=0; i<GlobalLists.professors.size(); i++){
                        if(GlobalLists.professors.get(i).id == rs.getInt("prowadzacy"))
                            c.setProfessor(GlobalLists.professors.get(i));
                    }
                    for(int i=0; i<GlobalLists.rooms.size(); i++){
                        if(GlobalLists.rooms.get(i).id == rs.getInt("sala"))
                            c.setRoom(GlobalLists.rooms.get(i));
                    }
                    for(int i=0; i<GlobalLists.groups.size(); i++){
                        if(GlobalLists.groups.get(i).id == rs.getInt("grupa")){
                            c.setGroup(GlobalLists.groups.get(i));
                        }
                    }
                    GlobalLists.classes.add(c);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
    }

    //CREATE

    public static void addProfessor(Professor professor) throws SQLException
    {
        String addProfessor = "INSERT INTO prowadzacy (id, tytul, imie, nazwisko, mail) VALUES (NULL, ?, ?, ?, ?)";
        DatabaseHelper.getAllProfessors();
        boolean canAdd = true;
        for(int i=0; i<GlobalLists.professors.size(); i++){
            if(GlobalLists.professors.get(i).getMail().equals(professor.getMail()))
                canAdd = false;
        }
        if(canAdd){
            connect();
            try {
                PreparedStatement stmt = con.prepareStatement(addProfessor);
                stmt.setString(1, GlobalLists.getDegreeName(professor.getDegree()));
                stmt.setString(2, professor.getName());
                stmt.setString(3, professor.getSurname());
                stmt.setString(4, professor.getMail());
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                closeConnection();
            }
            getAllProfessors();
        }
    }

    public static void addGrupa (Group group)
    {
        String addGroup = "INSERT INTO grupy (id, nazwa, rok_rozp, kierunek) VALUES (NULL, ?, ?, ?)";
        connect();
        try {
            PreparedStatement stmt = con.prepareStatement(addGroup);
            stmt.setString(1, group.getName());
            stmt.setString(2, String.valueOf(group.year));
            stmt.setInt(3, group.getSpecialization());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
        getAllGroups();
    }

    public static void addRoom(Room room) {
        String addRoom = "INSERT INTO sale (id, sala, budynek, typ) VALUES (NULL, ?, ?, ?)";
        DatabaseHelper.getAllRooms();
        boolean canAdd = true;
        for(int i=0; i<GlobalLists.rooms.size(); i++) {
            if ((GlobalLists.rooms.get(i).building.equals(room.building) && GlobalLists.rooms.get(i).number.equals(room.number)))
                canAdd = false;
        }
        if(canAdd) {
            connect();
            try {
                PreparedStatement stmt = con.prepareStatement(addRoom);
                stmt.setString(1, room.number);
                stmt.setString(2, room.building);
                stmt.setInt(3, room.type);
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
            getAllRooms();
        }
    }

    public static int addClass(Classes newClasses)
    {
        String addClass = "INSERT INTO zajecia (ID, lgodzin, przedmiot, prowadzacy, grupa, sala) VALUES (NULL, ?, ?, ?, ?, ?)";
        connect();
        try {
            PreparedStatement stmt = con.prepareStatement(addClass, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, newClasses.getAmountOfHours());
            stmt.setInt(2, newClasses.getSubject().id);
            stmt.setInt(3, newClasses.getProfessor().id);
            stmt.setInt(4, newClasses.getGroup().id);
            stmt.setInt(5, newClasses.getRoom().id);
            stmt.executeUpdate();
            int i = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                i = rs.getInt(1);
            }
            return i;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        finally{
            closeConnection();
        }
    }

    //TODO update'y rekordów

    //DELETE
    public static void delete(int id, String tabela) {
        String delete = "DELETE FROM " + tabela + " WHERE ID = ?";
        connect();
        try{
            PreparedStatement stmt = con.prepareStatement(delete);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
    }

    public static void closeConnection(){
        try{
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

