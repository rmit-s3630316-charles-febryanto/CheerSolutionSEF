package user;

import com.conference.user.Member;
import com.conference.user.Receptionist;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class ReceptionistTest {
    private Receptionist receptionist1, receptionist2;

    @Before
    public void setUp() throws Exception {
        receptionist1 = new Receptionist("1234567890",
                "aaa",
                "Last Name",
                "Female",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                2);

        receptionist2 = new Receptionist("1234567890",
                "aaa",
                "Last Name",
                "Female",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                1);
    }

    @Test
    public void receptionist1Test() {
        receptionistCheck(receptionist1);
    }

    @Test
    public void receptionist2Test() {
        receptionistCheck(receptionist2);
    }

    public void receptionistCheck(Receptionist receptionist) {
        Assert.assertTrue(receptionist instanceof Receptionist);
        Assert.assertTrue(receptionist.getCompanies(receptionist.getMemberId()) instanceof ObservableList);
        Assert.assertTrue(receptionist.getLectures(receptionist.getMemberId()) instanceof ObservableList);

    }
}
