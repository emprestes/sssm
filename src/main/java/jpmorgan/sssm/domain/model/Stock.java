package jpmorgan.sssm.domain.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    private Stock() {
        super();
    }

    public static Stock ofALE() {
        return ofALE(0.23, 0.6);
    }

    private static Stock ofALE(Double lastDividend, Double parValue) {
        return of(StockSymbol.ALE.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    public static Stock ofGIN() {
        return ofGIN(0.08, 0.02, 1.0);
    }

    private static Stock ofGIN(Double lastDividend, Double fixedDividend, Double parValue) {
        return of(StockSymbol.GIN.toString(), StockType.PREFERRED,
                BigDecimal.valueOf(lastDividend), BigDecimal.valueOf(fixedDividend), BigDecimal.valueOf(parValue));
    }

    public static Stock ofJOE() {
        return ofJOE(0.13, 2.5);
    }

    private static Stock ofJOE(Double lastDividend, Double parValue) {
        return of(StockSymbol.JOE.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    public static Stock ofPOP() {
        return ofPOP(0.08, 1.0);
    }

    private static Stock ofPOP(Double lastDividend, Double parValue) {
        return of(StockSymbol.POP.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    public static Stock ofTEA() {
        return ofTEA(0.0, 1.0);
    }

    private static Stock ofTEA(Double lastDividend, Double parValue) {
        return of(StockSymbol.TEA.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    public static Stock of(String stock, StockType type, double lastDividend, double fixedDividend, double parValue) {
        return of(stock, type,
                BigDecimal.valueOf(lastDividend), BigDecimal.valueOf(fixedDividend), BigDecimal.valueOf(parValue));
    }

    private static Stock of(String stock, StockType type,
                            BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue) {
        Stock s = new Stock();

        s.symbol = stock;
        s.type = type;
        s.lastDividend = lastDividend;
        s.fixedDividend = fixedDividend;
        s.parValue = parValue;

        return s;
    }

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

    public BigDecimal calcDividendYield(double price) {
        return calcDividendYield(BigDecimal.valueOf(price));
    }

    public BigDecimal calcDividendYield(BigDecimal price) {
        BigDecimal value = BigDecimal.ZERO;

        if (price != null && price.doubleValue() > value.doubleValue()) {
            switch (type) {
                case COMMON:
                    value = lastDividend.divide(price, BigDecimal.ROUND_HALF_UP);
                    break;
                case PREFERRED:
                    value = fixedDividend.multiply(parValue)
                            .divide(price, BigDecimal.ROUND_HALF_UP);
                    break;
                default:
                    break;
            }
        }

        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        final Stock other = (Stock) o;
        return Objects.equal(getSymbol(), other.getSymbol())
                && Objects.equal(getType(), other.getType())
                && Objects.equal(getLastDividend(), other.getLastDividend())
                && Objects.equal(getFixedDividend(), other.getFixedDividend())
                && Objects.equal(getParValue(), other.getParValue());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSymbol(), getType(), getLastDividend(), getFixedDividend(), getParValue());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("symbol", symbol)
                .add("type", type)
                .add("lastDividend", lastDividend)
                .add("fixedDividend", fixedDividend)
                .add("parValue", parValue)
                .add("trades", trades.size())
                .toString();
    }
}
