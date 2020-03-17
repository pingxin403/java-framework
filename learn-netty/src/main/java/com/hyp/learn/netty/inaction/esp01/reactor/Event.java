package com.hyp.learn.netty.inaction.esp01.reactor;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.reactor
 * hyp create at 20-3-7
 **/
public class Event {
    private InputSource source;
    private EventType type;

    public InputSource getSource() {
        return source;
    }

    public void setSource(InputSource source) {
        this.source = source;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}

