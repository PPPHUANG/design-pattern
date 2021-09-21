package cn.ppphuang.designpattern.singleton;

public class DoubleCheckedSingleton {
    //保证 instance 在所有线程中同步
    private static volatile DoubleCheckedSingleton instance = null;

    //private 避免类在外部被实例化
    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {
        // 实例没创建，才会进入内部的 synchronized代码块
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                // 也许有其它线程已经创建实例，所以再判断一次
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
