package jpmorgan.sssm.domain.model;


import jpmorgan.sssm.domain.util.Math;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public GBCE add(Stock... stock) {
        stocks.addAll(Arrays.asList(stock));
        return this;
    }

    public BigDecimal calcAllShareIndexes() {
        BigDecimal value;
        Function<Stock, BigDecimal> f;
        long n;

        f = Stock::calcVolumeWeighted;
        n = stocks.size();
        value = BigDecimal.ZERO;

        value = stocks.stream()
                .sorted((i1, i2) -> i1.getSymbol().compareToIgnoreCase(i2.getSymbol()))
                .collect(Collectors.groupingBy(Stock::getSymbol))
                .values().stream()
                .map(list -> list.stream()
                        .map(Stock::calcVolumeWeighted)
                        .findFirst().get())
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        value = Math.pow(value, n);

        return value;
    }
}
