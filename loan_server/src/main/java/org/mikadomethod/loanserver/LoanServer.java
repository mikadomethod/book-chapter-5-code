package org.mikadomethod.loanserver;

import org.eclipse.jetty.server.Server;

public class LoanServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new LoanHandler());
        server.start();
        server.join();
    }
}