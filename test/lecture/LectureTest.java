package lecture;

import com.conference.lecture.Lecture;
import com.conference.lecture.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class LectureTest {
    private Lecture lecture1, lecture2, lecture3, lecture4, lecture5, lecture6;
    private Room room1, room2, room3, room4;

    @Before
    public void setUp() throws Exception {
        room1 = new Room("R001", "Main Room", "Main Room", 100);
        room2 = new Room("R001", "Main Room", "Main Room", 100);
        room3 = new Room("R001", "Main Room", "Main Room", 100);
        room4 = new Room("R001", "Main Room", "Main Room", 100);

        lecture1 = new Lecture("L001", "Programming", room1, Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now()), 120);
        lecture2 = new Lecture("L00212093809123802918302813", "Marketing", room2, Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now()), 120);
        lecture3 = new Lecture("L003", "ajhdjkahkjahdkadhakjhasjkhd", room3,  Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now()), 120);
        lecture4 = new Lecture("L004", "Title 4", room4,  Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now()), -1);

        lecture5 = new Lecture("L005", "Title 5", 0);
        lecture6 = new Lecture("L006", "Title 6", -10);
    }

    @Test
    public void lectureTest1() {
        lectureCheck(lecture1);
    }

    @Test
    public void lectureTest2() {
        lectureCheck(lecture2);
    }

    @Test
    public void lectureTest3() {
        lectureCheck(lecture3);
    }

    @Test
    public void lectureTest4() { lectureCheck(lecture4); }

    @Test
    public void lectureTest5() { lectureCheck(lecture5); }

    @Test
    public void lectureTest6() { lectureCheck(lecture6); }

    public void lectureCheck(Lecture lecture) {
        Assert.assertTrue("Lecture ID Length <= 20", lecture.getLectureId().length() <= 20);
        Assert.assertTrue("Lecture Name Length <= 20", lecture.getTitle().length() <= 20);
        Assert.assertTrue("Lecture Duration >= 0",lecture.getDuration() >= 0);
        Assert.assertTrue("Lecture Attends >= 0", lecture.getAttends() >=0);
        Assert.assertTrue(lecture.getRoom() instanceof Room);
    }
}
