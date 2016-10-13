package jpmorgan.sssm.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class StockTest {

    static final long _1SECOND = 1000;
    static final long _5SECONDS = 5 * _1SECOND;

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

    @Test
    public void calcVolumeWeightedTest() throws Exception {
        Stock stock = Stock.ofGIN();
        BigDecimal result;

        stock.buy(System.currentTimeMillis())
                .withQuantity(6)
                .withPrice(99);
        Thread.sleep(_1SECOND);
        stock.sell(System.currentTimeMillis())
                .withQuantity(4)
                .withPrice(23.48);
        Thread.sleep(_1SECOND);
        stock.buy(System.currentTimeMillis())
                .withQuantity(3)
                .withPrice(60.0);
        Thread.sleep(_1SECOND);
        stock.sell(System.currentTimeMillis())
                .withQuantity(7)
                .withPrice(98.34);
        Thread.sleep(_1SECOND);
        stock.buy(System.currentTimeMillis())
                .withQuantity(2)
                .withPrice(100.0);
        Thread.sleep(_1SECOND);
        stock.sell(System.currentTimeMillis())
                .withQuantity(20)
                .withPrice(253.39);
        Thread.sleep(_1SECOND);
        stock.buy(System.currentTimeMillis())
                .withQuantity(45)
                .withPrice(88.01);
        Thread.sleep(_1SECOND);
        stock.sell(System.currentTimeMillis())
                .withQuantity(67)
                .withPrice(122.98);

        result = stock.calcVolumeWeighted(System.currentTimeMillis() - _5SECONDS);

        Assert.assertEquals(BigDecimal.valueOf(128.77), result);
    }
}
