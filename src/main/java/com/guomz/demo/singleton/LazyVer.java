package com.guomz.demo.singleton;

/**
 * 懒汉单例模式
 */
public class LazyVer {

    private static volatile LazyVer instance;

    private LazyVer(){}

    public static LazyVer getInstance(){
        if (instance == null){
            synchronized (LazyVer.class){
                if (instance == null){
                    instance = new LazyVer();
                }
            }
        }
        return instance;
    }
}
