package Tests;

import io.planner.grupa1.DatabaseHelper;
import io.planner.grupa1.Group;

import org.junit.Assert;
import org.junit.Test;

public class GroupTest {

    @Test
    public void testGroup() throws Exception{
        Group g=new Group();
        Assert.assertNotNull(g);
    }

    @Test
    public void testGroupSetGet() throws Exception {
        DatabaseHelper.connect();
        DatabaseHelper.getAllSpecializations();
        Group g = new Group();
        g.setSpecialization("IO");
        g.setYear(2017);
        g.setName();
        Assert.assertEquals(2017, g.getYear());
        Assert.assertEquals(2, g.getSpecialization());
    }
}