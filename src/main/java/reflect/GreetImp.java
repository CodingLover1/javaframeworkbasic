package reflect;

/**
 * @Author: wangshuo
 * @Date: 2019/5/16 9:19 PM
 * @Version 1.0
 */
public class GreetImp implements Greet {

    @Override
    public int sayHello() {
        System.out.println("Hello");
        return 1;
    }
}