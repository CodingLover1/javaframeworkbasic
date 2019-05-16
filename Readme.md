### Java框架基本知识


#### 泛型
> 编译期绑定数据类型的一种方式

* 泛型类的定义
```
public class ClassName <T> {
    
}
```

* 泛型方法的定义
```

public <T> void function(T data){
    
}
```

* 泛型类型的限定

```

<T extends Father> :T 必须是Father类型或者Father类型的子类

<T supper Child>: T必须是Child类型或者Child类型的父类

<?> :?与T类似 但是?是只读的，在运行期间不能对?类型的数据进行修改

```

#### 注解

* @Retention:此注解用在其他注解上，表明此注解应该如何存储
    * SOURCE:表名这个注解仅保存在源码中，编译后不会存在于字节码文件中
    * CLASS:表明这个注解编译后会保存在字节码中，但被jvm运行时会忽略
    * RUNTIME: 表明这个注解会被jvm获取，并在运行时可通过反射取到
    
* @Target:此注解用来设置注解可以作用的类型
    * ANNOTATION_TYPE 表示该注解可以应用到其他注解上
    * CONSTRUCTOR 表示可以使用到构造器上
    * FIELD 表示可以使用到域或属性上
    * LOCAL_VARIABLE 表示可以使用到局部变量上。
    * METHOD可 以使用到方法级别的注解上。
    * PACKAGE 可以使用到包声明上。
    * PARAMETER 可以使用到方法的参数上
    * TYPE 可以使用到一个类的任何元素上

* @Documented：被注解的元素将会作为Javadoc产生的文档中的内容。注解都默认不会成为成为文档中的内容。这个注解可以对其它注解使用。

* @Inherited：在默认情况下，注解不会被子类继承。被此注解标记的注解会被所有子类继承。这个注解可以对类使用。

* @Deprecated：说明被标记的元素不应该再度使用。这个注解会让编译器产生警告消息。可以使用到方法，类和域上。相应的解释和原因，
包括另一个可取代的方法应该同时和这个注解使用。


#### 反射

* 加载类 Class.forName()

相关概念

* Class 类
    * Modifier:修饰符例如 public static native
    
    
getClassLoader()

获取该类的类装载器。

getComponentType()

如果当前类表示一个数组，则返回表示该数组组件的Class对象，否则返回null。

getConstructor(Class[])

返回当前Class对象表示的类的指定的公有构造子对象。

getConstructors()

返回当前Class对象表示的类的所有公有构造子对象数组。

getDeclaredConstructor(Class[])

返回当前Class对象表示的类的指定已说明的一个构造子对象。

getDeclaredConstructors()

返回当前Class对象表示的类的所有已说明的构造子对象数组。

getDeclaredField(String)

返回当前Class对象表示的类或接口的指定已说明的一个域对象。

getDeclaredFields()

返回当前Class对象表示的类或接口的所有已说明的域对象数组。

getDeclaredMethod(String,Class[])

返回当前Class对象表示的类或接口的指定已说明的一个方法对象。

getDeclaredMethods()

返回Class对象表示的类或接口的所有已说明的方法数组。

getField(String)

返回当前Class对象表示的类或接口的指定的公有成员域对象。

getFields()

返回当前Class对象表示的类或接口的所有可访问的公有域对象数组。

getInterfaces()

返回当前对象表示的类或接口实现的接口。

getMethod(String,Class[])

返回当前Class对象表示的类或接口的指定的公有成员方法对象。

getMethods()

返回当前Class对象表示的类或接口的所有公有成员方法对象数组，包括已声明的和从父类继承的方法。

getModifiers()

返回该类或接口的Java语言修改器代码。

getName()

返回Class对象表示的类型(类、接口、数组或基类型)的完整路径名字符串。

getResource(String)

按指定名查找资源。

getResourceAsStream(String)

用给定名查找资源。

getSigners()

获取类标记。

getSuperclass()

如果此对象表示除Object外的任一类,那么返回此对象的父类对象。

isArray()

如果Class对象表示一个数组则返回true,否则返回false。

isAssignableFrom(Class)

判定Class对象表示的类或接口是否同参数指定的Class表示的类或接口相同，或是其父类。

isInstance(Object)

此方法是Java语言instanceof操作的动态等价方法。

isInterface()

判定指定的Class对象是否表示一个接口类型。

isPrimitive()

判定指定的Class对象是否表示一个Java的基类型。

newInstance()

创建类的新实例。

toString()

* 通过反射获取注解并拿到注解的值
```
Class c=Class.forName("reflect.Person");
//getDeclaredField能拿到私有属性，getField只能拿public属性
Field field=c.getDeclaredField("name");
        Value value=field.getAnnotation(Value.class);
        System.out.println(value.value());
```

#### jdk动态代理

```java

package reflect;

/**
 * @Author: wangshuo
 * @Date: 2019/5/16 9:06 PM
 * @Version 1.0
 */
public interface Greet {

    int sayHello();
}

```

```java

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

```

```
        Person person=new Person();
        person.setName("ws");
        person.setAge(24);

        //注意这里类型转换应该是转换成接口的类型
        Greet p= (Greet)Proxy.newProxyInstance(person.getClass().getClassLoader(),person.getClass().getInterfaces(),new InvocationHandler(){
            //invoke方法的返回值Object类型就是 method.invoke方法执行时 该方法在接口中定义的返回类型
            @Override
            public Object invoke(Object proxy,Method method,Object[] args)throws Exception{
                System.out.println("----befor----");
                Object result=method.invoke(person,args);
                System.out.println("----after----");
                return result;
            }
        });

        p.sayHello();
```




