package jpmorgan.sssm.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class GBCETest {

    private static final long _1SECOND = 1000;

    @Test
    public void calcAllShareIndexesTest() throws Exception {
        GBCE gbce = GBCE.create();
        Stock tea = Stock.ofTEA();
        Stock pop = Stock.ofPOP();
        Stock ale = Stock.ofALE();
        Stock gin = Stock.ofGIN();
        Stock joe = Stock.ofJOE();
        BigDecimal result;
        long time;

        time = System.currentTimeMillis();
        tea.buy(time).withQuantity(1).withPrice(55);
        pop.buy(time).withQuantity(2).withPrice(44);
        ale.buy(time).withQuantity(3).withPrice(33);
        gin.buy(time).withQuantity(4).withPrice(22);
        joe.buy(time).withQuantity(5).withPrice(11);
        Thread.sleep(_1SECOND);

        time = System.currentTimeMillis();
        tea.buy(time).withQuantity(19).withPrice(102.78);
        pop.buy(time).withQuantity(18).withPrice(278.63);
        ale.buy(time).withQuantity(17).withPrice(167.28);
        gin.buy(time).withQuantity(16).withPrice(182.53);
        joe.buy(time).withQuantity(15).withPrice(173.92);
        Thread.sleep(_1SECOND);

        time = System.currentTimeMillis();
        tea.buy(time).withQuantity(9).withPrice(503.29);
        pop.buy(time).withQuantity(8).withPrice(78.63);
        ale.buy(time).withQuantity(7).withPrice(67.28);
        gin.buy(time).withQuantity(6).withPrice(12.53);
        joe.buy(time).withQuantity(5).withPrice(13.09);
        Thread.sleep(_1SECOND);

        time = System.currentTimeMillis();
        tea.buy(time).withQuantity(25).withPrice(202.78);
        pop.buy(time).withQuantity(14).withPrice(71.63);
        ale.buy(time).withQuantity(23).withPrice(17.28);
        gin.buy(time).withQuantity(12).withPrice(82.53);
        joe.buy(time).withQuantity(21).withPrice(73.92);

        gbce.add(tea, pop, ale, gin, joe);

        result = gbce.calcAllShareIndexes();

        Assert.assertEquals(BigDecimal.valueOf(121.26), result.setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
