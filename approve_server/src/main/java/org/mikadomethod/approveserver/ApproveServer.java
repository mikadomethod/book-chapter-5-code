package org.mikadomethod.approveserver;

import org.eclipse.jetty.server.Server;
import org.mikadomethod.loanserver.FileBasedLoanRepository;

public class ApproveServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        server.setHandler(new ApproveHandler(new FileBasedLoanRepository()));
        server.start();
        server.join();
    }
}