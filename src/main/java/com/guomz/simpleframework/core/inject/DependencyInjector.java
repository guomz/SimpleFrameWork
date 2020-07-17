package com.guomz.simpleframework.core.inject;

import com.guomz.simpleframework.core.BeanContainer;
import com.guomz.simpleframework.core.inject.annotations.Autowired;
import com.guomz.simpleframework.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * 用于实现依赖注入
 */
@Slf4j
public class DependencyInjector {

    private BeanContainer beanContainer;

    public DependencyInjector(){
        this.beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.guomz.service");
    }

    public void doIoc(){
        //获取被加载的类
        Set<Class<?>> classSet = beanContainer.getClasses();
        if (classSet == null || classSet.isEmpty()){
            log.warn("没有类被加载");
            return;
        }

        for (Class clazz: classSet){
            Field fields[] = clazz.getDeclaredFields();
            //找出每个类下面的属性，遍历属性找出被Autowired标记的
            for (Field field: fields){
                if (field.isAnnotationPresent(Autowired.class)){
                    //得到属性所属的类与注解标注的名称
                    Class fieldClass = field.getType();
                    String className = field.getAnnotation(Autowired.class).value();
                    //获取该类在容器中的实例
                    Object fieldInstance = getFieldInstance(fieldClass, className);
                    //注入
                    ClassUtil.setFieldValue(field, beanContainer.getBean(clazz), fieldInstance, true);
                }
            }
        }
    }

    /**
     * 根据属性的类获取其实例
     * @param fieldClass
     * @param className
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass, String className){
        //当直接获取类的实例获取不到时则可能被antowired标注的是接口
        if (beanContainer.getBean(fieldClass) == null){
            Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
            //获取全部实现类
            if (classSet == null || classSet.isEmpty()){
                log.error("未找到实现类");
                throw new RuntimeException("未找到实现类");
            }

            //如果没有指定实现类的类名且有多个实现类，则报错
            if (className == null || className.trim().equals("")){
                if (classSet.size() > 1){
                    log.error("未指定具体实现类，无法注入");
                    throw new RuntimeException("未指定具体实现类，无法注入");
                }
                return beanContainer.getBean(classSet.iterator().next());
            }
            //根据标注的类名找出需要被注入的类的实例
            for (Class clazz: classSet){
                if (clazz.getSimpleName().equals(className)){
                    return beanContainer.getBean(clazz);
                }
            }
            log.error("未找到实现类");
            throw new RuntimeException("未找到实现类");
        }
        //直接获取到则返回
        return beanContainer.getBean(fieldClass);
    }

    public static void main(String[] args) {
        new DependencyInjector().doIoc();
    }
}
