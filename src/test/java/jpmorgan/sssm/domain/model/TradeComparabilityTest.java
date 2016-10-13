package jpmorgan.sssm.domain.model;

import junitx.extensions.ComparabilityTestCase;

public class TradeComparabilityTest extends ComparabilityTestCase {

    public TradeComparabilityTest(String name) {
        super(name);
    }

    @Override
    protected Comparable createLessInstance() throws Exception {
        return Trade.of(1111111111L);
    }

    @Override
    protected Comparable createEqualInstance() throws Exception {
        return Trade.of(3333333333L);
    }

    @Override
    protected Comparable createGreaterInstance() throws Exception {
        return Trade.of(9999999999L);
    }
}
