package com.hyp.learn.chat.util;

import io.netty.channel.Channel;

import java.net.SocketAddress;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.chat.util
 * hyp create at 20-3-21
 **/
public class NettyUtil {

    /**
     * 获取Channel的远程IP地址
     * @param channel
     * @return
     */
    public static String parseChannelRemoteAddr(final Channel channel) {
        if (null == channel) {
            return "";
        }
        SocketAddress remote = channel.remoteAddress();
        final String addr = remote != null ? remote.toString() : "";

        if (addr.length() > 0) {
            int index = addr.lastIndexOf("/");
            if (index >= 0) {
                return addr.substring(index + 1);
            }

            return addr;
        }

        return "";
    }
}
