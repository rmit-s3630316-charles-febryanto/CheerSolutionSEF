package company;

import com.conference.company.Company;
import com.conference.company.Product;
import com.conference.user.Employee;
import com.conference.user.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyTest {
    private Company company1, company2, company3, company4, company5;
    private Employee employee1, employee2;
    private Product product1, product2, product3, product4;
    private ObservableList<Member> employees1 = FXCollections.observableArrayList();
    private ObservableList<Member> employees2 = FXCollections.observableArrayList();
    private ObservableList<Product> products1 = FXCollections.observableArrayList();
    private ObservableList<Product> products2 = FXCollections.observableArrayList();

    @Before
    public void setUp() throws Exception {
        company1 = new Company("C001", "Company One");

        product1 = new Product("P001", "Coke", 5, 5);
        product2 = new Product("P002", "Cokee", 5, 5);

        products1.addAll(product1, product2);

        employee1 = new Employee("1234567890",
                "aaa",
                "Last Name",
                "M",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                1);
        employees1.add(employee1);

        company2 = new Company("C0081273891723982173982137981237291837219837", "Company Two",
                products1, employees1);


        product3 = new Product("P003", "Cokeee", 5, 5);
        product4 = new Product("P004", "Cokeeee", 5, 5);
        products2.addAll(product3, product4);

        employee2 = new Employee("1234567892",
                "aaa",
                "Last Name",
                "M",
                "l123212",
                "asdasd",
                Date.valueOf(LocalDate.now()),
                1);
        employees2.add(employee2);
        company3 = new Company("C003", "Company Threeeeeeeeeeeeeeeeeeeeeeeeeeeee",
                products2, employees2);

        company4 = new Company("C004", "Company Four", 0);

        company5 = new Company("C005", "Company Five", -10);
    }

    @Test
    public void testCompany1() {
            companyCheck(company1);
    }

    @Test
    public void testCompany2() {
        companyCheck(company2);
    }

    @Test
    public void testCompany3() {
        companyCheck(company3);
    }

    @Test
    public void testCompany4() { companyCheck(company4); }

    @Test
    public void testCompany5() { companyCheck(company5); }

    public void companyCheck(Company company) {
        Assert.assertTrue("Company ID Length < 20", company.getCompanyId().length() <= 20);
        Assert.assertTrue("Company Name Length < 20", company.getName().length() <= 20);
        Assert.assertTrue(company.getStaff().size() >= 0);
        Assert.assertTrue(company.getProducts().size() >= 0);
        Assert.assertTrue(company.getStaff() instanceof ObservableList);
        Assert.assertTrue(company.getProducts() instanceof ObservableList);
        Assert.assertTrue("Engagements >= 0", company.getEngagements() >= 0);
    }
}
