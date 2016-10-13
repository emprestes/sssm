package jpmorgan.sssm.domain.model;

import java.math.BigDecimal;
import java.util.Optional;

class Trade implements Comparable<Trade> {

    private long timestamp;

    private int quantity;

    private Indicator indicator;

    private BigDecimal price;

    public static Trade of(long nowInMillis) {
        return of(nowInMillis, 0, null, 0.0);
    }

    public static Trade of(long nowInMillis, int quantity, Indicator indicator, double price) {
        return new Trade(nowInMillis)
                .setQuantity(quantity)
                .setIndicator(indicator)
                .setPrice(price);
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

    public Trade setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public Trade setIndicator(Indicator indicator) {
        this.indicator = indicator;
        return this;
    }

    public Optional<BigDecimal> getPrice() {
        return Optional.of(price);
    }

    public Trade setPrice(double price) {
        return setPrice(BigDecimal.valueOf(price));
    }

    public Trade setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (getTimestamp() != trade.getTimestamp()) return false;
        if (getQuantity() != trade.getQuantity()) return false;
        if (getIndicator() != trade.getIndicator()) return false;
        return getPrice() != null ? getPrice().equals(trade.getPrice()) : trade.getPrice() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getTimestamp() ^ (getTimestamp() >>> 32));
        result = 31 * result + getQuantity();
        result = 31 * result + (getIndicator() != null ? getIndicator().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Trade o) {
        int comp = timestamp < o.timestamp ? -1 : 0;

        comp = comp == 0 && timestamp > o.timestamp ? 1 : 0;

        return comp;
    }
}
