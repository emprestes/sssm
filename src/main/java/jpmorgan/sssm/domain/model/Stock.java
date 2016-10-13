package jpmorgan.sssm.domain.model;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Stock {

    private String symbol;

    private StockType type;

    private BigDecimal lastDividend;

    private BigDecimal fixedDividend;

    private BigDecimal parValue;

    private final Set<Trade> trades = new TreeSet<>();

    public String getSymbol() {
        return symbol;
    }

    public StockType getType() {
        return type;
    }

    public Optional<BigDecimal> getLastDividend() {
        return Optional.of(lastDividend);
    }

    public void setLastDividend(BigDecimal lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Optional<BigDecimal> getFixedDividend() {
        return Optional.of(fixedDividend);
    }

    public void setFixedDividend(BigDecimal fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public Optional<BigDecimal> getParValue() {
        return Optional.of(parValue);
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    public Set<Trade> getTrades() {
        return trades;
    }
}
