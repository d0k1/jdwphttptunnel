package com.focusit.debugee;

import org.apache.commons.lang3.StringUtils;
import ratpack.server.RatpackServer;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by doki on 16.08.17.
 */
public class DebugeeApplication {
    static private int readJdwpListenerPort() {
        String listenerAddress = null;
        try {
            Class<?> theClass = Class.forName("sun.misc.VMSupport");
            Method m = theClass.getMethod("getAgentProperties");
            Properties p = (Properties) m.invoke(null);
            listenerAddress = p.getProperty("sun.jdwp.listenerAddress");
            if (listenerAddress != null) {
                listenerAddress = StringUtils.substringAfter(listenerAddress, ":");
                return Integer.parseInt(listenerAddress);
            }
        } catch (Exception ex) {
            System.err.println("Failed to read sun.jdwp.listenerAddress, ignore");
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server.handlers(chain -> chain
                .get(ctx -> ctx.render("Welcome to Baeldung ratpack!!!"))));
        System.out.println("Current jdwp port is :" +readJdwpListenerPort());
    }
}
