package com.whuaz.java.concurrent.sync;

/**
 * 单例模式
 * @author grez
 * @since 19-5-27
 **/
public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        // 判断对象是否实例化
        if (instance == null) {
            // 类对象加锁
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
