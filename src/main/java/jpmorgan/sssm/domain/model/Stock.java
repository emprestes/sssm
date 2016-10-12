package jpmorgan.sssm.domain.model;

import java.util.Set;
import java.util.TreeSet;

class Stock {

    private String symbol;

    private StockType type;

    private float lastDividend;

    private float fixedDividend;

    private double parValue;

    private final Set<Trade> trades = new TreeSet<Trade>();
}
