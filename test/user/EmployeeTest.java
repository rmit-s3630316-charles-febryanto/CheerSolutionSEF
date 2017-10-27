package user;

import com.conference.user.Employee;
import com.conference.user.Member;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeTest {
    private Employee employee1, employee2;

    @Before
    public void setUp() throws Exception {
        employee1 = new Employee("1234567890",
                "aaa",
                "Last Name",
                "F",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                1);
        employee2 = new Employee("1234567890",
                "aaa",
                "Last Name",
                "F",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                0);
    }

    @Test
    public void employee1Test() {
        employeeCheck(employee1);
    }

    @Test
    public void employee2Test() {
        employeeCheck(employee2);
    }

    public void employeeCheck(Employee employee) {
        Assert.assertTrue(employee instanceof Employee);
    }
}
