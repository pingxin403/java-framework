package com.hyp.learn.netty.reactor;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.reactor
 * hyp create at 20-3-7
 **/
public class InputSource {
    private Object data;
    private long id;

    public InputSource(Object data, long id) {
        this.data = data;
        this.id = id;
    }

    @Override
    public String toString() {
        return "InputSource{" +
                "data=" + data +
                ", id=" + id +
                '}';
    }
}