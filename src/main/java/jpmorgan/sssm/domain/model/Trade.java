package jpmorgan.sssm.domain.model;

import java.math.BigDecimal;
import java.util.Optional;

class Trade {

    private long timestamp;

    private int quantity;

    private Indicator indicator;

    private BigDecimal price;

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

    public Trade setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
