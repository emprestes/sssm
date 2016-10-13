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

    @Test
    public void calcPERatioCommon() {
        double price;
        BigDecimal result;
        Stock stock;

        price = 100.0;
        stock = Stock.ofALE();
        result = stock.calcPERatio(price);

        Assert.assertTrue(stock.isCommon());
        Assert.assertEquals(BigDecimal.valueOf(43478.26), result);
    }

    @Test
    public void calcPERatioPreferred() {
        double price;
        BigDecimal result;
        Stock stock;

        price = -10.0;
        stock = Stock.ofGIN();
        result = stock.calcPERatio(price);

        Assert.assertTrue(stock.isPreferred());
        Assert.assertEquals(BigDecimal.valueOf(500000, 2), result);
    }

    @Test
    public void stockBuySellTest() {
        Stock stock = Stock.ofALE();

        Assert.assertTrue(stock.isEmpty());

        stock.buy(System.currentTimeMillis())
                .withQuantity(2)
                .withPrice(100.0);
        stock.sell(System.currentTimeMillis())
                .withQuantity(3)
                .withPrice(25.00);
        stock.buy(System.currentTimeMillis())
                .withQuantity(3)
                .withPrice(60.0);
        stock.sell(System.currentTimeMillis())
                .withQuantity(7)
                .withPrice(98.34);

        Assert.assertFalse(stock.isEmpty());
        Assert.assertEquals(4, stock.size());
    }
}
