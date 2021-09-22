package cn.ppphuang.designpattern.prototype;

/**
 * 原型模式
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype realizetype = new Realizetype();
        Realizetype cloneRealizetype = (Realizetype) realizetype.clone();
        System.out.println("realizetype == cloneRealizetype ? " + (realizetype == cloneRealizetype));
    }
}

class Realizetype implements Cloneable {
    public Realizetype() {
        System.out.println("具体原型创建成功");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功");
        return super.clone();
    }
}