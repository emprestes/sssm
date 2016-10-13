package jpmorgan.sssm.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

public class TradeEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public TradeEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return Trade.buy(2323134234L);
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return Trade.sell(98798798798879L);
    }
}
