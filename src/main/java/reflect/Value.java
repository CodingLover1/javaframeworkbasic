package reflect;

import java.lang.annotation.*;

/**
 * @Author: wangshuo
 * @Date: 2019/5/16 5:49 PM
 * @Version 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Value {
    String value();
}
