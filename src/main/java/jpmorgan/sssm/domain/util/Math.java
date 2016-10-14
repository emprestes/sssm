package jpmorgan.sssm.domain.util;

import java.math.BigDecimal;

public final class Math {

    private Math() {
        super();
    }

    public static BigDecimal pow(BigDecimal value, long n) {
        return BigDecimal.valueOf(java.lang.Math.pow(value.doubleValue(), (1.0 / n)));
    }
}
