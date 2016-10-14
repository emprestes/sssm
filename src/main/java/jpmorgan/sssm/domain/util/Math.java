package jpmorgan.sssm.domain.util;

import java.math.BigDecimal;

/**
 * Math helper.
 *
 * @author Prestes, E. M.
 * @since October 2016
 */
public final class Math {

    /**
     * Default constructor
     */
    private Math() {
        super();
    }

    /**
     * Calculate pow.
     *
     * @param base the base.
     * @param exp  the exponent.
     * @return BigDecimal
     */
    public static BigDecimal pow(BigDecimal base, long exp) {
        return BigDecimal.valueOf(java.lang.Math.pow(base.doubleValue(), (1.0 / exp)));
    }
}
