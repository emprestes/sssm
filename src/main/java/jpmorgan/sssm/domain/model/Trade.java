package jpmorgan.sssm.domain.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Trades definition.
 *
 * @author Prestes, E. M.
 * @see Indicator
 * @since October 2016
 */
final class Trade implements Comparable<Trade> {

    private long timestamp;

    private int quantity;

    private Indicator indicator;

    private BigDecimal price;

    /**
     * Constructor informing the moment of trade.
     *
     * @param nowInMillis Moment of trade informed.
     */
    private Trade(long nowInMillis) {
        super();

        this.timestamp = nowInMillis;
    }

    /**
     * Creating a buy.
     *
     * @param nowInMillis Moment of buy informed.
     * @return Trade
     */
    static Trade buy(long nowInMillis) {
        return of(nowInMillis, 0, Indicator.BUY, 0.0);
    }

    /**
     * Creating a sell.
     *
     * @param nowInMillis Moment of sell informed.
     * @return Trade
     */
    static Trade sell(long nowInMillis) {
        return of(nowInMillis, 0, Indicator.SELL, 0.0);
    }

    /**
     * Creating a trade.
     *
     * @param nowInMillis Moment of trade informed.
     * @param quantity    A quantity informed.
     * @param indicator   An indicator informed.
     * @param price       A price informed.
     * @return Trade
     */
    private static Trade of(long nowInMillis, int quantity, Indicator indicator, double price) {
        return new Trade(nowInMillis)
                .withQuantity(quantity)
                .setIndicator(indicator)
                .withPrice(price);
    }

    /**
     * Getting the moment of trade in millis.
     *
     * @return time in millis
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Getting a quantity.
     *
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setting a quantity.
     *
     * @param quantity Quantity informed.
     * @return Trade
     */
    public Trade withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Getting an indicator.
     *
     * @return Indicator
     */
    public Indicator getIndicator() {
        return indicator;
    }

    /**
     * Setting an indicator (BUY or SEEL).
     *
     * @param indicator Inficator informed.
     * @return Trade
     */
    private Trade setIndicator(Indicator indicator) {
        this.indicator = indicator;
        return this;
    }

    /**
     * Getting the price.
     *
     * @return BigDecimal
     */
    public Optional<BigDecimal> getPrice() {
        return Optional.of(price);
    }

    /**
     * Getting the relation between price and quantity (price x quantity).
     *
     * @return BigDecimal
     */
    public BigDecimal getPriceQuantity() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Setting a price.
     *
     * @param price Price informed.
     * @return Trade
     */
    public Trade withPrice(double price) {
        return withPrice(BigDecimal.valueOf(price));
    }

    /**
     * Setting a price.
     *
     * @param price Price informed.
     * @return Trade
     */
    public Trade withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trade)) {
            return false;
        }
        final Trade other = (Trade) o;
        return Objects.equal(timestamp, other.timestamp)
                && Objects.equal(quantity, other.quantity)
                && Objects.equal(indicator, other.indicator)
                && Objects.equal(price, other.price);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(timestamp, quantity, indicator, price);
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(Trade o) {
        int comp = Long.compare(timestamp, o.timestamp);

        comp = comp == 0 ? indicator.compareTo(o.indicator) : comp;
        comp = comp == 0 ? Integer.compare(quantity, o.quantity) : comp;
        comp = comp == 0 ? price.compareTo(o.price) : comp;

        return comp;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("timestamp", getTimestamp())
                .add("quantity", getQuantity())
                .add("indicator", getIndicator())
                .add("price", getPrice())
                .toString();
    }
}
