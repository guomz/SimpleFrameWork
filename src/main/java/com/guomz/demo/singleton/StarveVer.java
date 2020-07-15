package com.guomz.demo.singleton;

/**
 * 饿汉单例模式
 */
public class StarveVer {

    private static StarveVer instance = new StarveVer();

    private StarveVer(){}

    public static StarveVer getInstance(){
        return instance;
    }
}
