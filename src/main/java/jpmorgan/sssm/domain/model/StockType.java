package jpmorgan.sssm.domain.model;

enum StockType {
    COMMON, PREFERRED;

    public static boolean isCommon(StockType type) {
        return COMMON == type;
    }

    public static boolean isPreferred(StockType type) {
        return PREFERRED == type;
    }
}
