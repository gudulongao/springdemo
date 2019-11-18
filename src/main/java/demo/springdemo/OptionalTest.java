package demo.springdemo;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class OptionalTest {
    public static void testCreate() {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);

        Optional<Object> emptyOptional = Optional.of(null);
        System.out.println(emptyOptional);

        Optional<Integer> result = Optional.ofNullable(12);
        System.out.println(result);
    }

    public static void testEmpty() {
        Optional<Integer> empty1 = Optional.ofNullable(null);
        Optional<Integer> empty2 = Optional.ofNullable(null);
        Object obj1 = Optional.<Integer>ofNullable(null);
        Object obj2 = Optional.<String>ofNullable(null);
        System.out.println(empty1 == empty2);
        System.out.println(empty1 == Optional.<Integer>empty());
        System.out.println(obj1 == obj2);
    }

    public static void testIfPresent() {
        Consumer consumer = i -> System.out.println("value: " + i);
        Optional.of(12).ifPresent(consumer);
        Optional.ofNullable(null).ifPresent(consumer);
    }

    public static void testOrElse() {
        Integer obj = Optional.<Integer>ofNullable(null).orElse(22);
        System.out.println(obj);
        obj = Optional.<Integer>ofNullable(null).orElseGet(() -> 999);
        System.out.println(obj);
        obj = Optional.of(23).orElse(65);
        System.out.println(obj);
        Optional.ofNullable(null).orElseThrow(() -> new NullPointerException("空指针啦啊啦"));
    }

    public static void testMap() {
        Object result = Optional.ofNullable(1).map((a) -> "value:" + a);
        System.out.println(((Optional) result).get());

        result = Optional.ofNullable(2).flatMap((a) -> Optional.of("value:" + a));
        System.out.println(((Optional) result).get());
    }

    static class Node {
        private String name;
        private Node node;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public Node(String naem, Node node) {
            this.name = naem;
            this.node = node;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", node=" + node +
                    '}';
        }
    }

    public static void testOptional() throws Exception {
        Node demo1 = new Node("1", null);
        Node demo2 = new Node("2", demo1);
        Node demo3 = new Node("3", demo2);

        Function<Node, Node> mapper = a -> {
            System.out.println("node name: " + a.getName());
            return a.getNode();
        };
        Node result = Optional.of(demo3).map(mapper).map(mapper).orElseThrow(() -> new Exception("空指针啦啦啦"));
        System.out.println(result);
        result = Optional.of(demo1).map(mapper).map(mapper).orElseThrow(() -> new Exception("空指针啦啦啦"));
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
//        testCreate();
//        testEmpty();
//        testIfPresent();
//        testOrElse();
//        testMap();
        testOptional();
    }
}
