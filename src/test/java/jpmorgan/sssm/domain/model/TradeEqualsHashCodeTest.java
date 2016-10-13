package jpmorgan.sssm.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

public class TradeEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public TradeEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return Trade.of(System.currentTimeMillis());
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return Trade.of(98798798798879L);
    }
}
