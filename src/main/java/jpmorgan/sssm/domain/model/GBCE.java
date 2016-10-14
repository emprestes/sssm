package jpmorgan.sssm.domain.model;


import jpmorgan.sssm.domain.util.Math;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GBCE {

    private final Set<Stock> stocks = new HashSet<>();

    private GBCE() {
        super();
    }

    public static GBCE create() {
        return new GBCE();
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public GBCE add(Stock... stock) {
        stocks.addAll(Arrays.asList(stock));
        return this;
    }

    public BigDecimal calcAllShareIndexes() {
        BigDecimal value;
        long n;

        n = stocks.size();
        value = stocks.stream()
                .sorted((i1, i2) -> i1.getSymbol().compareToIgnoreCase(i2.getSymbol()))
                .collect(Collectors.groupingBy(Stock::getSymbol))
                .values().stream()
                .map(list -> list.stream()
                        .map(Stock::calcVolumeWeighted)
                        .reduce(BigDecimal.ZERO, (zero, first) -> first))
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        value = Math.pow(value, n);

        return value;
    }
}
