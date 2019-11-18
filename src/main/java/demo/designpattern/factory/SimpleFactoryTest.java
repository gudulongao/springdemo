package demo.designpattern.factory;

public class SimpleFactoryTest {
    private static final String PAY_WEBCHAT = "wx";
    private static final String PAY_ALI = "ali";
    private static final String PAY_UNION = "union";

    static interface Pay {
        void pay();
    }

    static class WebChatPay implements Pay {
        @Override
        public void pay() {
            System.out.println("It is WeChat pay!");
        }
    }

    static class AliPay implements Pay {
        @Override
        public void pay() {
            System.out.println("It is Ali pay!");
        }
    }

    static class UnionPay implements Pay {
        @Override
        public void pay() {
            System.out.println("It is Union pay!");
        }
    }

    static class PayFactory {
        public static Pay getPay(String payType) {
            if (PAY_WEBCHAT.equals(payType)) {
                return new WebChatPay();
            } else if (PAY_ALI.equals(payType)) {
                return new AliPay();
            } else if (PAY_UNION.equals(payType)) {
                return new UnionPay();
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        PayFactory.getPay("ali").pay();
    }
}
