package user;

import com.conference.company.Product;
import com.conference.user.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class TransactionTest {
    private Transaction transaction1, transaction2, transaction3, transaction4;
    private Product product1, product2, product3, product4;
    private ObservableList<Product> products1 = FXCollections.observableArrayList();
    private ObservableList<Product> products2 = FXCollections.observableArrayList();

    @Before
    public void setUp() throws Exception {

        product1 = new Product("P001", "Coke", 5, 5);
        product2 = new Product("P002", "Cokee", 5, 5);

        products1.addAll(product1, product2);


        product3 = new Product("P003", "Cokeee", 5, 5);
        product4 = new Product("P004", "Cokeeee", 5, 5);
        products2.addAll(product3, product4);

        transaction1 = new Transaction("8789878929", products1, 10, Date.valueOf(LocalDate.now()));
        transaction2 = new Transaction("76787678902222", products2, 10, Date.valueOf(LocalDate.now()));
        transaction3 = new Transaction("76787", products2, 10, Date.valueOf(LocalDate.now()));
        transaction4 = new Transaction("klsajdnciqqiwue", products2, -10, Date.valueOf(LocalDate.now()));
    }

    @Test
    public void transactionTest1() {
        transactionCheck(transaction1);
    }

    @Test
    public void transactionTest2() {
        transactionCheck(transaction2);
    }

    @Test
    public void transactionTest3() { transactionCheck(transaction3); }

    @Test
    public void transactionTest4() { transactionCheck(transaction4); }

    public void transactionCheck(Transaction transaction) {
        Assert.assertTrue("Number only for transaction ID", numberCheck(transaction.getTransactionId()));
        Assert.assertTrue("Transaction ID == 10", transaction.getTransactionId().length() == 10);
        Assert.assertTrue(transaction.getProducts() instanceof ObservableList);
        Assert.assertTrue("Transaction Total >= 0", transaction.getTotal() >= 0);
    }

    public boolean numberCheck(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
