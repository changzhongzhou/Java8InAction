package lambdasinaction.chap3;

import java.io.IOException;

public class LocalVariable {
    private Integer memberVar = 100;
    private static Integer staticVar = 100;
    public static void main(String[] args) throws IOException {
        int var = System.in.read();
        funcLocal(var);
        var = System.in.read();
        System.out.println(var);
    }

    // 注意：局部变量指的是参数变量 param，而非 main 方法中定义的变量 var。param 和 var 是俩变量
    private static void funcLocal(int param) {
        Runnable r = () -> System.out.println(param);
        // param = 1;
        new Thread(r).start();
    }

    private static void funcStatic() {
        Runnable r = () -> System.out.println(staticVar);
        r.run();
        r = () -> staticVar += 1;
        r.run();
        staticVar += 1;
        r.run();
    }

    private void funcMember() {
        Runnable r = () -> System.out.println(memberVar);
        r.run();
        r = () -> memberVar += 1;
        r.run();
        memberVar += 1;
        r.run();
    }

    // 局部变量捕获：对象可变，局部变量不可变
    private static void funcObj(Apple apple) {
        Runnable r = () -> apple.setColor("red");
        apple.setColor("green");
        // apple = new Apple();
        r.run();
    }

    // 对象常量：对象可变，引用地址不可变
    private static void finalTest() {
        final Apple apple = new Apple();
        apple.setColor("green");
        // apple = new Apple();
    }

    // apple 是栈上的局部变量，存了一个堆对象的地址
    private static void instanceTest() {
        Apple apple = new Apple();
        System.out.println(apple);
        apple = new Apple();
        System.out.println(apple);
    }

    public static class Apple {
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
