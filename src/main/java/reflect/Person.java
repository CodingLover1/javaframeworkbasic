package reflect;

/**
 * @Author: wangshuo
 * @Date: 2019/5/16 5:49 PM
 * @Version 1.0
 */
public class Person implements Greet{
    @Value("wangshuo")
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int sayHello() {
        System.out.println("Hello");
        return 1;
    }
}
