package jpmorgan.sssm.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class StockTest {

    @Test
    public void calcDividendYieldByZero() {
        double price = 0.0;
        Stock s = Stock.ofALE();

        Assert.assertEquals(BigDecimal.ZERO, s.calcDividendYield(price));
    }

    @Test
    public void calcDividendYieldCommon() {
        double price;
        BigDecimal result;
        Stock stock;

        price = 100.0;
        stock = Stock.ofALE();
        result = stock.calcDividendYield(price);

        Assert.assertTrue(stock.isCommon());
        Assert.assertEquals(BigDecimal.valueOf(0.0023), result);
    }

    @Test
    public void calcDividendYieldPreferred() {
        double price;
        BigDecimal result;
        Stock stock;

        price = -10.0;
        stock = Stock.ofGIN();
        result = stock.calcDividendYield(price);

        Assert.assertTrue(stock.isPreferred());
        Assert.assertEquals(BigDecimal.valueOf(-0.002), result);
    }
}
