package jpmorgan.sssm.domain.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 * Stocks definition.
 *
 * @author Prestes, E. M.
 * @see StockSymbol
 * @see StockType
 * @see Trade
 * @since October 2016
 */
public final class Stock {

    private static final int ZERO = 0;
    private final Set<Trade> trades = new TreeSet<>();
    private String symbol;
    private StockType type;
    private BigDecimal lastDividend;
    private BigDecimal fixedDividend;
    private BigDecimal parValue;

    /**
     * Default constructor.
     */
    private Stock() {
        super();
    }

    /**
     * Creating a Stock ALE.
     *
     * @return Stock
     */
    public static Stock ofALE() {
        return ofALE(0.23, 0.6);
    }

    /**
     * Creating a Stock ALE.
     *
     * @param lastDividend A last informed.
     * @param parValue     A par valeu informed.
     * @return Stock
     */
    private static Stock ofALE(Double lastDividend, Double parValue) {
        return of(StockSymbol.ALE.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    /**
     * Creating a Stock GIN.
     *
     * @return Stock
     */
    public static Stock ofGIN() {
        return ofGIN(0.08, 0.02, 1.0);
    }

    /**
     * Creating a Stock GIN.
     *
     * @param lastDividend  A last informed.
     * @param fixedDividend A fixed dividend informed.
     * @param parValue      A par valeu informed.
     * @return Stock
     */
    private static Stock ofGIN(Double lastDividend, Double fixedDividend, Double parValue) {
        return of(StockSymbol.GIN.toString(), StockType.PREFERRED,
                BigDecimal.valueOf(lastDividend), BigDecimal.valueOf(fixedDividend), BigDecimal.valueOf(parValue));
    }

    /**
     * Creating a Stock JOE.
     *
     * @return Stock
     */
    public static Stock ofJOE() {
        return ofJOE(0.13, 2.5);
    }

    /**
     * Creating a Stock JOE.
     *
     * @param lastDividend A last informed.
     * @param parValue A par valeu informed.
     * @return Stock
     */
    private static Stock ofJOE(Double lastDividend, Double parValue) {
        return of(StockSymbol.JOE.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    /**
     * Creating a Stock POP.
     *
     * @return Stock
     */
    public static Stock ofPOP() {
        return ofPOP(0.08, 1.0);
    }

    /**
     * Creating a Stock POP.
     *
     * @param lastDividend A last informed.
     * @param parValue A par valeu informed.
     * @return Stock
     */
    private static Stock ofPOP(Double lastDividend, Double parValue) {
        return of(StockSymbol.POP.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    /**
     * Creating a Stock TEA.
     *
     * @return Stock
     */
    public static Stock ofTEA() {
        return ofTEA(0.0, 1.0);
    }

    /**
     * Creating a Stock TEA.
     *
     * @param lastDividend A last informed.
     * @param parValue A par valeu informed.
     * @return Stock
     */
    private static Stock ofTEA(Double lastDividend, Double parValue) {
        return of(StockSymbol.TEA.toString(), StockType.COMMON,
                BigDecimal.valueOf(lastDividend), BigDecimal.ZERO, BigDecimal.valueOf(parValue));
    }

    /**
     * Creating a Stock.
     *
     * @param stock The symbol informed.
     * @param type The type informed.
     * @param lastDividend A last informed.
     * @param fixedDividend A fixed dividend informed.
     * @param parValue A par valeu informed.
     * @return Stock
     */
    public static Stock of(String stock, StockType type, double lastDividend, double fixedDividend, double parValue) {
        return of(stock, type,
                BigDecimal.valueOf(lastDividend), BigDecimal.valueOf(fixedDividend), BigDecimal.valueOf(parValue));
    }

    /**
     * Creating a Stock.
     *
     * @param stock The symbol informed.
     * @param type The type informed.
     * @param lastDividend A last informed.
     * @param fixedDividend A fixed dividend informed.
     * @param parValue A par valeu informed.
     * @return Stock
     */
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

    /**
     * Getting the stock symbol.
     *
     * @return Stock symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Getting the stock type.
     *
     * @return StockType
     */
    public StockType getType() {
        return type;
    }

    /**
     * Checking if Stock type is common.
     *
     * @return true if ok, false otherwise.
     */
    public boolean isCommon() {
        return StockType.isCommon(type);
    }

    /**
     * Checking if Stock type is preferred.
     *
     * @return true if ok, false otherwise.
     */
    public boolean isPreferred() {
        return StockType.isPreferred(type);
    }

    /**
     * Getting the last dividend.
     *
     * @return The last dividend
     */
    public Optional<BigDecimal> getLastDividend() {
        return Optional.of(lastDividend);
    }

    /**
     * Setting a last dividend.
     *
     * @param lastDividend A last dividend informed
     * @return Stock
     */
    public Stock setLastDividend(BigDecimal lastDividend) {
        this.lastDividend = lastDividend;
        return this;
    }

    /**
     * Getting the fixed dividend.
     *
     * @return The fixed dividend
     */
    public Optional<BigDecimal> getFixedDividend() {
        return Optional.of(fixedDividend);
    }

    /**
     * Setting a fixed dividend.
     *
     * @param fixedDividend A fixed dividend informed
     * @return Stock
     */
    public Stock setFixedDividend(BigDecimal fixedDividend) {
        this.fixedDividend = fixedDividend;
        return this;
    }

    /**
     * Getting the par value.
     *
     * @return The par value
     */
    public Optional<BigDecimal> getParValue() {
        return Optional.of(parValue);
    }

    /**
     * Setting a par value.
     *
     * @param parValue Par value informed
     * @return Stock
     */
    public Stock setParValue(BigDecimal parValue) {
        this.parValue = parValue;
        return this;
    }

    /**
     * Getting Stock trades.
     *
     * @return Set
     */
    public Set<Trade> getTrades() {
        return trades;
    }

    /**
     * Checking if no trades in stock.
     *
     * @return true for empty, false otherwise.
     */
    public boolean isEmpty() {
        return trades.isEmpty();
    }

    /**
     * Buying a trade.
     *
     * @param timeInMillis Buying moment.
     * @return Trade
     */
    public Trade buy(long timeInMillis) {
        Trade trade;

        trade = Trade.buy(timeInMillis);
        trades.add(trade);

        return trade;
    }

    /**
     * Selling a trade.
     *
     * @param timeInMillis Selling moment.
     * @return Trade
     */
    public Trade sell(long timeInMillis) {
        Trade trade;

        trade = Trade.sell(timeInMillis);
        trades.add(trade);

        return trade;
    }

    /**
     * A quantity of Stock trades.
     *
     * @return int
     */
    public int size() {
        return trades.size();
    }

    /**
     * Calculate the dividend yield.
     *
     * @param price Price informed.
     * @return BigDecimal
     */
    public BigDecimal calcDividendYield(double price) {
        return calcDividendYield(BigDecimal.valueOf(price));
    }

    /**
     * Calculate the dividend yield.
     *
     * @param price Price informed.
     * @return BigDecimal
     */
    public BigDecimal calcDividendYield(BigDecimal price) {
        BigDecimal value = BigDecimal.ZERO;

        if (price != null && price.doubleValue() != value.doubleValue()) {
            switch (type) {
                case COMMON:
                    value = lastDividend.divide(price, MathContext.DECIMAL64);
                    break;
                case PREFERRED:
                    value = fixedDividend.multiply(parValue)
                            .divide(price, MathContext.DECIMAL64);
                    break;
                default:
                    break;
            }
        }

        return value;
    }

    /**
     * Calculate the P/E Ratio.
     *
     * @param price Price informed.
     * @return BigDecimal
     */
    public BigDecimal calcPERatio(double price) {
        return calcPERatio(BigDecimal.valueOf(price));
    }

    /**
     * Calculate the P/E Ratio.
     *
     * @param price Price informed.
     * @return BigDecimal
     */
    public BigDecimal calcPERatio(BigDecimal price) {
        BigDecimal dividend;

        dividend = calcDividendYield(price);
        dividend = price.divide(dividend, 2, BigDecimal.ROUND_HALF_UP);

        return dividend;
    }

    /**
     * Calculate Volume Weighted Stock Price based on trades without past time.
     *
     * @return BigDecimal
     */
    public BigDecimal calcVolumeWeighted() {
        return calcVolumeWeighted(ZERO);
    }

    /**
     * Calculate Volume Weighted Stock Price based on trades in specific past time.
     *
     * @param time Past time
     * @return BigDecimal
     */
    public BigDecimal calcVolumeWeighted(long time) {
        BigDecimal divisor;
        BigDecimal dividend;
        Predicate<Trade> period;

        period = i -> time == ZERO || i.getTimestamp() >= time;

        dividend = trades.stream()
                .filter(period)
                .map(Trade::getPriceQuantity)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        divisor = BigDecimal.valueOf(trades.stream()
                .filter(period)
                .mapToLong(Trade::getQuantity)
                .sum());

        dividend =  dividend.divide(divisor, MathContext.DECIMAL64);
        return  dividend;
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hashCode(getSymbol(), getType(), getLastDividend(), getFixedDividend(), getParValue());
    }

    /** {@inheritDoc} */
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
