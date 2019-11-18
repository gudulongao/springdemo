package demo.springdemo;

public class TestImpl implements ITest {
    @Override
    public void test() {
        throw new NullPointerException();
    }
}
