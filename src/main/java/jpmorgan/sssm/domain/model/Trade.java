package jpmorgan.sssm.domain.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.util.Optional;

class Trade implements Comparable<Trade> {

    private long timestamp;

    private int quantity;

    private Indicator indicator;

    private BigDecimal price;

    public static Trade buy(long nowInMillis) {
        return of(nowInMillis, 0, Indicator.BUY, 0.0);
    }

    public static Trade sell(long nowInMillis) {
        return of(nowInMillis, 0, Indicator.SELL, 0.0);
    }

    private static Trade of(long nowInMillis, int quantity, Indicator indicator, double price) {
        return new Trade(nowInMillis)
                .withQuantity(quantity)
                .setIndicator(indicator)
                .withPrice(price);
    }

    private Trade(long nowInMillis) {
        super();

        this.timestamp = nowInMillis;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public Trade withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    private Trade setIndicator(Indicator indicator) {
        this.indicator = indicator;
        return this;
    }

    public Optional<BigDecimal> getPrice() {
        return Optional.of(price);
    }

    public BigDecimal getPriceQuantity() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Trade withPrice(double price) {
        return withPrice(BigDecimal.valueOf(price));
    }

    public Trade withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(timestamp, quantity, indicator, price);
    }

    @Override
    public int compareTo(Trade o) {
        int comp = Long.compare(timestamp, o.timestamp);

        comp = comp == 0 ? indicator.compareTo(o.indicator) : comp;
        comp = comp == 0 ? Integer.compare(quantity, o.quantity) : comp;
        comp = comp == 0 ? price.compareTo(o.price) : comp;

        return comp;
    }

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
