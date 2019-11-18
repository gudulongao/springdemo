package demo.paralle.basic.thread.votailedemo;

import java.util.Optional;

public class NotifyByVolatile {
    static class User {
        private String name;
        private String address;
        private boolean flag = true;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", flag=" + flag +
                    '}';
        }

        public User(String name, String address, boolean flag) {
            this.name = name;
            this.address = address;
            this.flag = flag;
        }
    }

    static class UserMgr {
        private volatile User user;

        public void printUser() {
            while (user.flag) {
                Optional.ofNullable(user).ifPresent((user) -> {
                    System.out.println(user);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    static class OutprintThread extends Thread {
        private UserMgr userMgr;

        @Override
        public void run() {
            userMgr.printUser();
        }

        public OutprintThread(UserMgr userMgr) {
            this.userMgr = userMgr;
        }
    }

    public static void testVolatileUser() {
        User user = new User("a", "abc", true);
        UserMgr userMgr = new UserMgr();
        userMgr.setUser(user);
        OutprintThread a = new OutprintThread(userMgr);
        OutprintThread b = new OutprintThread(userMgr);
        a.start();
        b.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " change !");
        user.setName("b");
        user.setAddress("def");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " stop !");
        user.setFlag(false);
    }

    public static void main(String[] args) {
        testVolatileUser();
    }
}
