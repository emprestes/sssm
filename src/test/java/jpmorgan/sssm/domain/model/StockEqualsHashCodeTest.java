package jpmorgan.sssm.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

public class StockEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public StockEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return Stock.ofALE();
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return Stock.ofTEA();
    }
}
