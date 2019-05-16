package reflect;

import java.lang.reflect.*;

/**
 * @Author: wangshuo
 * @Date: 2019/5/16 5:48 PM
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception{
        Class c=Class.forName("reflect.Person");
        Constructor constructor=c.getConstructor();
        Object obj=constructor.newInstance();
        System.out.println(obj);
        System.out.println(Modifier.toString(c.getModifiers()));
        System.out.println("--------");
        System.out.println(c.getAnnotatedSuperclass().getType().getTypeName());
        System.out.println("----------");


        Method[] methods=c.getMethods();
        for(Method method:methods){
            System.out.println(method.getName());
        }
        System.out.println("--------");
        Field field=c.getDeclaredField("name");
        Value value=field.getAnnotation(Value.class);
        System.out.println(value.value());


        System.out.println("--------------");
        Person person=new Person();
        person.setName("ws");
        person.setAge(24);



        //注意这里类型转换应该是转换成接口的类型
        Greet p= (Greet)Proxy.newProxyInstance(person.getClass().getClassLoader(),person.getClass().getInterfaces(),new InvocationHandler(){
            @Override
            public Object invoke(Object proxy,Method method,Object[] args)throws Exception{
                System.out.println("----befor----");
                Object result=method.invoke(person,args);
                System.out.println("----after----");
                return result;
            }
        });

        p.sayHello();

    }

}
