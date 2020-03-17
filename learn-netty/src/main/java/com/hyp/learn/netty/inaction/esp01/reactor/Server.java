package com.hyp.learn.netty.inaction.esp01.reactor;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.reactor
 * hyp create at 20-3-7
 **/
public class Server {
    Selector selector = new Selector();
    Dispatcher eventLooper = new Dispatcher(selector);
    Acceptor acceptor;

    Server(int port) {
        acceptor = new Acceptor(selector, port);
    }

    public void start() {
        eventLooper.registEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
        eventLooper.handleEvents();
    }
}

