package Tests;

import io.planner.grupa1.Room;
import org.junit.Test;
import org.junit.Assert;

public class RoomTest {
    @Test
    public void testRoom() throws Exception{
        Room r=new Room();
        Assert.assertNotNull(r);
    }

    @Test
    public void testRoomSetGet() throws Exception {

        Room r = new Room();
        r.setNumber("410");
        r.setBuilding("B5");
        r.setType(4);

        Assert.assertEquals("410",r.getNumber());
        Assert.assertEquals("B5",r.getBuilding());
        Assert.assertEquals(4, r.getType());
    }
}