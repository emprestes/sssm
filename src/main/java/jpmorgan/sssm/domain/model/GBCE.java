package jpmorgan.sssm.domain.model;

import java.util.HashSet;
import java.util.Set;

public class GBCE {

    private final Set<Stock> stocks = new HashSet<>();

    public static GBCE create() {
        return new GBCE();
    }

    private GBCE() {
        super();
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public GBCE add(Stock stock) {
        stocks.add(stock);
        return this;
    }

    public double calcAllShareIndexes() {
        // TODO Calculation here.
        return 0.0;
    }
}
