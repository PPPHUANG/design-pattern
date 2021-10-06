package cn.ppphuang.designpattern.observer;

import java.util.Observer;
import java.util.Observable;

/**
 * 利用 Observable 类和 Observer 接口实现原油期货的观察者模式实例。
 * 分析：当原油价格上涨时，空方伤心，多方局兴；当油价下跌时，空方局兴，多方伤心。
 * 本实例中的抽象目标（Observable）类在 Java 中已经定义，可以直接定义其子类，即原油期货（OilFutures）类，
 * 它是具体目标类，该类中定义一个 SetPriCe(float price) 方法，
 * 当原油数据发生变化时调用其父类的 notifyObservers(Object arg) 方法来通知所有观察者；
 * 另外，本实例中的抽象观察者接口（Observer）在 Java 中已经定义，只要定义其子类，即具体观察者类（包括多方类 Bull 和空方类 Bear），
 * 并实现 update(Observable o,Object arg) 方法即可。
 */
public class CrudeOilFutures {
    public static void main(String[] args) {
        OilFutures oil = new OilFutures();
        Observer bull = new Bull(); //多方
        Observer bear = new Bear(); //空方
        oil.addObserver(bull);
        oil.addObserver(bear);
        oil.setPrice(10);
        oil.setPrice(-8);
    }
}

//具体目标类：原油期货
class OilFutures extends Observable {
    private float price;

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        super.setChanged();  //设置内部标志位，注明数据发生变化
        super.notifyObservers(price);    //通知观察者价格改变了
        this.price = price;
    }
}

//具体观察者类：多方
class Bull implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof OilFutures){
            System.out.println("OilFutures");
        }
        Float price = ((Float) arg).floatValue();
        if (price > 0) {
            System.out.println("油价上涨" + price + "元，多方高兴了！");
        } else {
            System.out.println("油价下跌" + (-price) + "元，多方伤心了！");
        }
    }
}

//具体观察者类：空方
class Bear implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Float price = ((Float) arg).floatValue();
        if (price > 0) {
            System.out.println("油价上涨" + price + "元，空方伤心了！");
        } else {
            System.out.println("油价下跌" + (-price) + "元，空方高兴了！");
        }
    }
}
