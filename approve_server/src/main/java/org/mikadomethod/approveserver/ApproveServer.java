package org.mikadomethod.approveserver;

import org.eclipse.jetty.server.Server;

public class ApproveServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        server.setHandler(new ApproveHandler());
        server.start();
        server.join();
    }
}