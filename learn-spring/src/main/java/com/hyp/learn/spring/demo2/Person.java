package com.hyp.learn.spring.demo2;

import org.springframework.stereotype.Component;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo1
 * hyp create at 19-12-15
 **/
@Component("Blacksmith")
public class Person implements Creature {
    private Axe axe;
    private Metal metal;

    public Person() {

    }

    public Person(Axe axe, Metal metal) {
        this.axe = axe;
        this.metal = metal;
    }

    // 设值注入所需的setter方法
    public void setMetal(Metal metal) {
        this.metal = metal;

    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    @Override
    public void useTool() {
        System.out.println("我打算去砍点柴火！");
        // 调用axe的chop()方法，metal的make()方法
        // 表明Person对象依赖于axe对象，metal对象
        System.out.println(metal.make());
        System.out.println(axe.chop());
    }
}
