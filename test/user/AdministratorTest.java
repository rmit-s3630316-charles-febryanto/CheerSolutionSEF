package user;

import com.conference.user.Administrator;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class AdministratorTest {
    private Administrator administrator1, administrator2;

    @Before
    public void setUp() throws Exception {
        administrator1 = new Administrator("1234567890",
                "aaa",
                "Last Name",
                "F",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                3);

        administrator2 = new Administrator("1234567890",
                "aaa",
                "Last Name",
                "F",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                2);
    }

    @Test
    public void administrator1Test() {
        administratorCheck(administrator1);
    }

    @Test
    public void administrator2Test() {
        administratorCheck(administrator2);
    }

    public void administratorCheck(Administrator administrator) {
        Assert.assertTrue(administrator instanceof Administrator);
        Assert.assertTrue(administrator.getLectureAttendance() instanceof ObservableList);
        Assert.assertTrue(administrator.getCompanyEngagements() instanceof ObservableList);
        Assert.assertTrue(administrator.getProductSold() instanceof ObservableList);
        Assert.assertTrue(administrator.getCompanies() instanceof ObservableList);
        Assert.assertTrue(administrator.getMembers() instanceof ObservableList);
        Assert.assertTrue(administrator.getLectures() instanceof ObservableList);
        Assert.assertTrue(administrator.getRooms() instanceof ObservableList);
    }
}
