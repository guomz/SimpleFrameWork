package com.guomz.simpleframework.core.inject.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    //该属性用来指明被注入的类，应对一个接口多实现类的情况
    String value() default "";
}
