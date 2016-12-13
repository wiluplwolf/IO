package Tests;
import io.planner.grupa1.Classes;
import io.planner.grupa1.DatabaseHelper;
import io.planner.grupa1.GlobalLists;
import org.junit.Assert;
import org.junit.Test;

public class ClassesTest {
    @Test
    public void testClass() throws Exception{
        Classes c=new Classes();
        Assert.assertNotNull(c);
    }

    @Test
    public void testClassSetGet() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllSubjects();
        DatabaseHelper.getAllGroups();
        DatabaseHelper.getAllProfessors();
        DatabaseHelper.getAllRooms();
        Classes c = new Classes();
        c.setProfessor(GlobalLists.professors.get(1));
        c.setRoom(GlobalLists.rooms.get(1));
        c.setSubject(GlobalLists.subjects.get(1));
        c.setGroup(GlobalLists.groups.get(1));
        c.setAmountOfHours(20);
        Assert.assertEquals(GlobalLists.professors.get(1),c.getProfessor());
        Assert.assertEquals(GlobalLists.rooms.get(1),c.getRoom());
        Assert.assertEquals(GlobalLists.subjects.get(1),c.getSubject());
        Assert.assertEquals(GlobalLists.groups.get(1),c.getGroup());
        Assert.assertEquals(20,c.getAmountOfHours());
    }
}