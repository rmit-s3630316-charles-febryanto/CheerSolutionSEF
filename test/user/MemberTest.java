package user;

import com.conference.user.Member;
import com.conference.user.Receptionist;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import java.time.LocalDate;

public class MemberTest {
    private Member member1, member2, member3, member4, member5, member6, member7, member8, member9;

    @Before
    public void setUp() throws Exception {
        member1 = new Member("1234567890", "First Name", "Last Name", "M",
                "123212", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member2 = new Member("123456789", "aaa", "Last Name", "M",
                "l123212", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member3 = new Member("12345678900", "aaa", "Last Name", "M",
                "123212", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member4 = new Member("123456789a", "aaa", "Last Name", "M",
                "+61321321", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member5 = new Member("1234567890",
                "F1i3r2s123t N273864872364873264328746328473624632487346287236123a123me1231",
                "Last Name", "M",
                "0321321", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member6 = new Member("1234567890", "First Name",
                "123123La12312312s3123t N123123213a12312m12312312783123987e1231231232131", "M",
                "123212", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member7 = new Member("1234567890", "First Name", "Last Name", "Male",
                "123212", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member8 = new Member("1234567890", "First Name", "Last Name", "P",
                "123212", "asdasd", Date.valueOf(LocalDate.now()), 0);
        member9 = new Member("1234567890", "First Name", "Last Name", "M",
                "123212", "asdasd", Date.valueOf(LocalDate.now()), 1);
    }

    @Test
    public void memberTest1() {
        memberCheck(member1);
    }

    @Test
    public void memberTest2() {
        memberCheck(member2);
    }

    @Test
    public void memberTest3() {
        memberCheck(member3);
    }

    @Test
    public void memberTest4() {
        memberCheck(member4);
    }

    @Test
    public void memberTest5() {
        memberCheck(member5);
    }

    @Test
    public void memberTest6() {
        memberCheck(member6);
    }

    @Test
    public void memberTest7() {
        memberCheck(member7);
    }

    @Test
    public void memberTest8() {
        memberCheck(member8);
    }

    @Test
    public void memberTest9() {
        memberCheck(member9);
    }

    public void memberCheck(Member member) {
        Assert.assertTrue("Number only for member ID", numberCheck(member.getMemberId()));
        Assert.assertTrue("Member ID Length == 10", member.getMemberId().length() == 10);

        Assert.assertTrue("String only for First Name", stringCheck(member.getFirstName()));
        Assert.assertTrue("First Name Length <= 20", member.getFirstName().length() <=20);

        Assert.assertTrue("String only for Last Name", stringCheck(member.getLastName()));
        Assert.assertTrue("Last Name Length <= 20", member.getLastName().length() <=20);

        Assert.assertTrue("Gender must be M/F Only", member.getGender().equals("F") || member.getGender().equals("M"));
//        Assert.assertTrue("Gender Length == 1", member.getGender().length() == 1);

        Assert.assertTrue("Number only for contact number", numberCheck(member.getContactNumber()));
        Assert.assertTrue("Contact Number <= 20", member.getContactNumber().length() <=20);

        Assert.assertTrue("Address Length <= 40", member.getAddress().length() <= 40);


        Assert.assertTrue(member.getLectures(member.getMemberId()) instanceof ObservableList);


        Assert.assertTrue(member.getTransactions(member.getMemberId()) instanceof ObservableList);


        Assert.assertTrue(member.getCompanies(member.getMemberId()) instanceof ObservableList);


        Assert.assertTrue(member instanceof Member);


        Assert.assertTrue(member.closeConnection());
    }

    public boolean numberCheck(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean stringCheck(String str) {
        int numberCount = 0;
        for(int i=0; i<str.length(); i++) {
            try {
                // parseInt success, go to numberCount++
                Long.parseLong(str.charAt(i) + "");
                numberCount++;
            } catch (NumberFormatException e) {
                // parseInt exception
            }
        }
        if(numberCount == 0) {
            return true;
        } else {
            return false;
        }
    }
}
