package com.hyp.learn.netty.inaction.esp01.reactor;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.reactor
 * hyp create at 20-3-7
 **/
public abstract class EventHandler {

    private InputSource source;
    public abstract void handle(Event event);

    public InputSource getSource() {
        return source;
    }

    public void setSource(InputSource source) {
        this.source = source;
    }
}
