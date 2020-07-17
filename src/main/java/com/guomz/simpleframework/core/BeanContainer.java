package com.guomz.simpleframework.core;

import com.guomz.simpleframework.core.annotations.Component;
import com.guomz.simpleframework.core.annotations.Controller;
import com.guomz.simpleframework.core.annotations.Service;
import com.guomz.simpleframework.util.ClassUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作为存放bean的容器，模拟spring的ioc容器
 * 判断被ClassUtil加载的类有无被注解标注，将标注的的类实例化存入容器
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)//私有构造器
public class BeanContainer {

    //用于存放注入的注解
    private static final List<Class<? extends Annotation>> annotationList = Arrays.asList(Controller.class, Service.class, Component.class);
    //存放被注入的bean
    private static final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();
    //加载标记
    private boolean loaded = false;

    /**
     * 根据包名与注解装载类实例
     * 设置为同步方法防止被多次加载
     * @param packageName
     */
    public synchronized void loadBeans(String packageName){
        //如果已加载则不执行后续步骤
        if (loaded){
            return;
        }
        //获取该包下的全部class
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (classSet == null || classSet.isEmpty()){
            log.warn("未取到该包下的类信息");
            return;
        }
        //循环判断加载的类是否被注解标注
        for (Class c: classSet){
            for (Class annotation: annotationList){
                if (c.isAnnotationPresent(annotation)){
                    beanMap.put(c, ClassUtil.getNewInstance(c, true));
                }
            }
        }
        //设置为已加载
        loaded = true;
    }

    /**
     * 用于外界判断是否容器已被加载
     * @return
     */
    public boolean isLoaded(){
        return this.loaded;
    }

    /**
     * 获取bean
     * @param clazz
     * @return
     */
    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     * 获取全部实例
     * @return
     */
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    /**
     * 获取全部类信息
     * @return
     */
    public Set<Class<?>> getClasses(){
        return beanMap.keySet();
    }

    /**
     * 向容器中加入bean，若已经存在则返回null
     * @param clazz
     * @param object
     * @return
     */
    public Object addBean(Class<?> clazz, Object object){
        return beanMap.put(clazz, object);
    }

    /**
     * 移除指定的bean
     * @param clazz
     * @return
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    /**
     * 根据注解筛选被其标注的类
     * @param annotation
     * @return
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation){
        if (beanMap.isEmpty()){
            return new HashSet<>();
        }

        Set<Class<?>> classSet = new HashSet<>();
        for (Class clazz: beanMap.keySet()){
            if (clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * 根据传入的类获取其子类信息
     * @param superClass
     * @return
     */
    public Set<Class<?>> getClassesBySuper(Class<?> superClass){
        if (beanMap.isEmpty()){
            return new HashSet<>();
        }

        Set<Class<?>> classSet = new HashSet<>();
        for (Class clazz: beanMap.keySet()){
            if (superClass.isAssignableFrom(clazz) && !clazz.equals(superClass)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    public static BeanContainer getInstance(){
        return ContainerSingleEnum.HOLDER.beanContainer;
    }

    /**
     * 使用枚举单例模式确保容器只有一个实例
     */
    private enum ContainerSingleEnum{
        HOLDER;
        private BeanContainer beanContainer;
        ContainerSingleEnum(){
            this.beanContainer = new BeanContainer();
        }
    }

    public static void main(String[] args) {
        BeanContainer.getInstance().loadBeans("com.guomz.service");
    }
}
