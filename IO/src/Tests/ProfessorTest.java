package Tests;

import io.planner.grupa1.DatabaseHelper;
import io.planner.grupa1.GlobalLists;
import io.planner.grupa1.Professor;
import org.junit.Assert;
import org.junit.Test;

public class ProfessorTest {

    @Test
    public void ProfessorTest() throws Exception {
        Professor p = new Professor();
        Assert.assertNotNull(p);
    }

    @Test
    public void ProfessorTest2() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllDegrees();
        DatabaseHelper.closeConnection();
        Professor p = new Professor();
        p.setName("Jan");
        p.setSurname("Kowalski");
        p.setMail("mail.testowy@test.pl");
        p.setDegree("inżynier");
        Assert.assertEquals("Kowalski", p.getSurname());
        Assert.assertEquals("Jan", p.getName());
        Assert.assertEquals("mail.testowy@test.pl", p.getMail());
        Assert.assertEquals("inżynier", GlobalLists.getDegreeName(p.getDegree()));
    }
}


