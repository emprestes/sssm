package jpmorgan.sssm.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class StockTest {

    @Test
    public void calcDividendYieldByZero() {
        BigDecimal price = BigDecimal.ZERO;
        Stock s = Stock.ofALE();

        Assert.assertEquals(price, s.calcDividendYield(0.0));
    }
}
