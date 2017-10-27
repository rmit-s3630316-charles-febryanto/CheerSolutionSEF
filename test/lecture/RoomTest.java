package lecture;

import com.conference.lecture.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {
    private Room room1, room2, room3, room4, room5;

    @Before
    public void setUp() throws Exception {
        room1 = new Room("R001", "Main Room", "Biggest Room", 200);
        room2 = new Room("R00219283781237981237982371", "Secondary Room", "Medium Room", 100);

        room3 = new Room("R003", "Secondary Roomouojaaskjhadskjhadskjhkajshkdjhsa", "Medium Room", 100);

        room4 = new Room("R004", "Secondary Room", "Medium Roomasdkhasdkjhadskjhdkjhakjahskjsahd", 100);

        room5 = new Room("R005", "Secondary Room", "Medium Room", -100);
    }

    @Test
    public void roomTest1() {
        roomCheck(room1);
    }

    @Test
    public void roomTest2() {
        roomCheck(room2);
    }

    @Test
    public void roomTest3() {
        roomCheck(room3);
    }

    @Test
    public void roomTest4() {
        roomCheck(room4);
    }

    @Test
    public void roomTest5() { roomCheck(room5); }

    public void roomCheck(Room room) {
        Assert.assertTrue("Room ID must be <= 20", room.getRoomId().length() <= 20);
        Assert.assertTrue("Room name must be <= 20", room.getName().length() <= 20);
        Assert.assertTrue("Room description must be <= 20", room.getDescription().length() <=20);
        Assert.assertTrue("Room seat must be >= 0", room.getSeat() >= 0);
    }
}
