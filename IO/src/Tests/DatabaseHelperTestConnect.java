package Tests;

import io.planner.grupa1.*;
import io.planner.grupa1.Classes;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelperTestConnect {
    @Test
    public void testConnect() throws Exception {
        DatabaseHelper.connect();
        Assert.assertEquals(false, DatabaseHelper.con.isClosed());
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testgetAllSubjectsLists() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllSubjects();
        int size_function = GlobalLists.subjects.size();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet r=s.executeQuery("SELECT COUNT(*) AS rows FROM przedmiot");
        r.next();
        int size_test_expected=r.getInt("rows");
        r.close();
        Assert.assertEquals(size_test_expected,size_function);
        DatabaseHelper.closeConnection();
    }


    @Test
    public void testgetAllProfessors() throws Exception {
        DatabaseHelper.getAllProfessors();
        int size_function = GlobalLists.professors.size();
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet r=s.executeQuery("SELECT COUNT(*) AS rows FROM prowadzacy");
        r.next();
        int size_test_expected=r.getInt("rows");
        r.close();
        Assert.assertEquals(size_test_expected,size_function);
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testgetAllSpecializations() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllSpecializations();
        int size_function = GlobalLists.specialization.size();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet r=s.executeQuery("SELECT COUNT(*) AS rows FROM kierunek");
        r.next();
        int size_test_expected=r.getInt("rows");
        r.close();
        Assert.assertEquals(size_test_expected,size_function);
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testgetAllGroups() throws Exception {
        DatabaseHelper.getAllGroups();
        DatabaseHelper.connect();
        int size_function = GlobalLists.groups.size();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet r=s.executeQuery("SELECT COUNT(*) AS rows FROM grupy");
        r.next();
        int size_test_expected=r.getInt("rows");
        r.close();
        Assert.assertEquals(size_test_expected,size_function);
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testgetAllRooms() throws Exception {
        DatabaseHelper.getAllRooms();
        int size_function = GlobalLists.rooms.size();
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet r=s.executeQuery("SELECT COUNT(*) AS rows FROM sale");
        r.next();
        int size_test_expected=r.getInt("rows");
        r.close();
        Assert.assertEquals(size_test_expected,size_function);
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testgetAllClasses() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllClasses();
        int size_function = GlobalLists.classes.size();
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet r=s.executeQuery("SELECT COUNT(*) AS rows FROM zajecia");
        r.next();
        int size_test_expected=r.getInt("rows");
        r.close();
        Assert.assertEquals(size_test_expected,size_function);
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testAddGrupa() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllSpecializations();
        DatabaseHelper.getAllGroups();
        Group g = new Group();
        g.setYear(2017);
        g.setSpecialization("IS");
        g.setName();
        String nazwa=g.getName();
        System.out.println(nazwa);
        DatabaseHelper.addGrupa(g);
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet rs=s.executeQuery("SELECT COUNT(*) AS records FROM grupy WHERE nazwa='"+nazwa+"' AND rok_rozp='2017' AND kierunek=1");
        rs.next();
        int test_records=rs.getInt("records");
        Assert.assertEquals(1,test_records);
        rs.close();
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testAddRoom () throws Exception {
        DatabaseHelper.connect();
        GlobalLists.fillTypes();
        DatabaseHelper.closeConnection();
        Room room = new Room();
        room.setBuilding("T1");
        room.setNumber("Wirtualna");
        room.setType(4);
        DatabaseHelper.addRoom(room);
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        DatabaseHelper.connect();
        ResultSet rs=s.executeQuery("SELECT COUNT(*) AS records FROM sale WHERE sala='Wirtualna' AND budynek='T1' AND typ=4");
        rs.next();
        int test_records=rs.getInt("records");
        Assert.assertEquals(1,test_records);
        rs.close();
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testAddClass() throws Exception {
        DatabaseHelper.connect();
        GlobalLists.fillTypes();
        DatabaseHelper.getAllSubjects();
        DatabaseHelper.getAllProfessors();
        DatabaseHelper.getAllRooms();
        DatabaseHelper.getAllGroups();
        Classes c = new Classes();
        c.setProfessor(GlobalLists.professors.get(1));
        c.setRoom(GlobalLists.rooms.get(1));
        c.setSubject(GlobalLists.subjects.get(1));
        c.setGroup(GlobalLists.groups.get(1));
        c.setAmountOfHours(66);
        DatabaseHelper.connect();
        int numerid = DatabaseHelper.addClass(c);
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet rs=s.executeQuery("SELECT * FROM zajecia WHERE ID='"+numerid+"'");
        rs.next();
        Assert.assertEquals(GlobalLists.professors.get(1).getId(),rs.getInt("prowadzacy"));
        Assert.assertEquals(GlobalLists.rooms.get(1).getId(),rs.getInt("sala"));
        Assert.assertEquals(GlobalLists.subjects.get(1).id,rs.getInt("przedmiot"));
        Assert.assertEquals(GlobalLists.groups.get(1).getId(),rs.getInt("grupa"));
        Assert.assertEquals(66,rs.getInt("lgodzin"));
        DatabaseHelper.closeConnection();

    }

    @Test
    public void testAddProfessor() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllDegrees();
        Professor p = new Professor();
        p.setName("Jan");
        p.setSurname("Testowy");
        p.setMail("mail.janatestowego@testy.com");
        p.setDegree("magister");
        DatabaseHelper.addProfessor(p);
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet rs=s.executeQuery("SELECT COUNT(*) AS records FROM prowadzacy WHERE nazwisko='Testowy'");
        rs.next();
        int test_records=rs.getInt("records");
        Assert.assertEquals(1,test_records);
        rs.close();
        ResultSet r=s.executeQuery("SELECT * FROM prowadzacy WHERE nazwisko='Testowy'");
        boolean test=r.next();
        Assert.assertEquals(true,test);
        Assert.assertEquals("Jan",r.getString("imie"));
        Assert.assertEquals("mail.janatestowego@testy.com",r.getString("mail"));
        Assert.assertEquals("magister",r.getString("tytul"));
        r.close();
        DatabaseHelper.closeConnection();
    }

    @Test
    public void testdelete() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllSpecializations();
        DatabaseHelper.getAllDegrees();
        DatabaseHelper.getAllSubjects();
        DatabaseHelper.getAllProfessors();
        DatabaseHelper.getAllRooms();
        DatabaseHelper.getAllGroups();
        //Prowadzacy
        Professor p = new Professor();
        p.setName("Jan");
        p.setSurname("Testowy");
        p.setMail("mail.janatestowego@testy.com");
        p.setDegree("magister");
        DatabaseHelper.addProfessor(p);
        DatabaseHelper.connect();
        Statement s=DatabaseHelper.con.createStatement();
        ResultSet rs=s.executeQuery("SELECT * FROM prowadzacy WHERE nazwisko='Testowy' AND mail='mail.janatestowego@testy.com' AND imie='Jan'");
        rs.next();
        DatabaseHelper.delete(rs.getInt("ID"),"prowadzacy");
        rs.close();
        rs=s.executeQuery("SELECT * FROM prowadzacy WHERE nazwisko='Testowy' AND mail='mail.janatestowego@testy.com' AND imie='Jan'");
        Assert.assertFalse(rs.next());
        rs.close();
        //Grupa

        Group g = new Group();
        g.setYear(2017);
        g.setSpecialization("IS");
        g.setName();
        String nazwa=g.getName();
        DatabaseHelper.addGrupa(g);
        rs=s.executeQuery("SELECT * FROM grupy WHERE nazwa='"+nazwa+"' AND rok_rozp='2017' AND kierunek=1");
        rs.next();
        DatabaseHelper.delete(rs.getInt("ID"),"grupy");
        rs.close();
        rs=s.executeQuery("SELECT * FROM grupy WHERE nazwa='"+nazwa+"' AND rok_rozp='2017' AND kierunek=1");
        Assert.assertFalse(rs.next());
        rs.close();

        //Zajecia
        Classes c = new Classes();
        c.setProfessor(GlobalLists.professors.get(1));
        c.setRoom(GlobalLists.rooms.get(1));
        c.setSubject(GlobalLists.subjects.get(1));
        c.setGroup(GlobalLists.groups.get(1));
        c.setAmountOfHours(66);
        int numerid=DatabaseHelper.addClass(c);
        DatabaseHelper.delete(numerid,"zajecia");


        rs=s.executeQuery("SELECT * FROM zajecia WHERE ID='"+numerid+"'");
        Assert.assertFalse(rs.next());
        rs.close();


    }
}