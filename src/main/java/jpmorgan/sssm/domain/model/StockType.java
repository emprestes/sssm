package jpmorgan.sssm.domain.model;

/**
 * Types of stock.
 *
 * @author Prestes, E. M.
 * @since October 2016
 */
enum StockType {
    COMMON, PREFERRED;

    /**
     * Checking if stock type is common.
     *
     * @param type A type of stock informed.
     * @return true if ok, false otherwise.
     */
    public static boolean isCommon(StockType type) {
        return COMMON == type;
    }

    /**
     * Checking if stock type is preferred.
     *
     * @param type A type of stock informed.
     * @return true if ok, false otherwise.
     */
    public static boolean isPreferred(StockType type) {
        return PREFERRED == type;
    }
}
