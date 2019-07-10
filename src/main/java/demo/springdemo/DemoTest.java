package demo.springdemo;

public class DemoTest {
    interface A {
        default void hello() {
            System.out.println("hello A");
        }
    }

    interface B {
        default void hello() {
            System.out.println("hello B");
        }

        static void statcHello() {
            System.out.println("static hello");
        }
    }

    static class Cimpl implements B, A {
        @Override
        public void hello() {
            System.out.println("hello C");
        }
    }

    static class Dimpl implements B {
    }

    public static void main(String[] args) {
        A a = new Cimpl();
        a.hello();

        B b = new Dimpl();
        b.hello();
    }
}
