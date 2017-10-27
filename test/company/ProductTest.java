package company;

import com.conference.company.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {
    private Product product1, product2, product3, product4, product5, product6, product7;

    @Before
    public void setUp() throws Exception {
        // 4 parameter
        product1 = new Product("P001", "Product 1", 5.00, 5);
        product2 = new Product("P002", "Product 2", -10.00, 5);
        product3 = new Product("P0038916723897123891738912739817129387", "Product 3", 10, 10);

        product4 = new Product("P004", "OAJSJWQASKHASKJHASKJASDHKADSHAKJHADKJADH", 10, 10);

        product5 = new Product("P005", "Product 5", 10, -10);

        // 3 parameter
        product6 = new Product("P006", "Product 6", 0);
        product7 = new Product("P007", "Product 7", -10);
    }

    @Test
    public void product1Test() {
        productCheck(product1);
    }

    @Test
    public void product2Test() {
        productCheck(product2);
    }

    @Test
    public void product3Test() { productCheck(product3); }

    @Test
    public void product4Test() {
        productCheck(product4);
    }

    @Test
    public void product5Test() {
        productCheck(product5);
    }

    @Test
    public void product6Test() {
        productCheck(product6);
    }

    @Test
    public void product7Test() {
        productCheck(product7);
    }

    private void productCheck(Product product) {
        Assert.assertTrue("Product ID Length <= 20",product.getProductId().length() <= 20);
        Assert.assertTrue("Product Name Length <= 20", product.getName().length() <= 20);
        Assert.assertTrue("Price >= 0", product.getPrice() >= 0);
        Assert.assertTrue("Stock >= 0", product.getStock() >= 0);
        Assert.assertTrue("Sold >= 0", product.getSold() >= 0);
    }
}
