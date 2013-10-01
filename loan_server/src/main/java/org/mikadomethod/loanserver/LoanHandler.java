package org.mikadomethod.loanserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.Gson;

public class LoanHandler extends AbstractHandler {
    public static final String APPLICATION = "apply";
    public static final String FETCH = "fetch";
    public static final String APPROVE = "approve";

    private final LoanRepository repo;
    
    public LoanHandler(LoanRepository loanRepository) {    	
    	repo = loanRepository;
    }
    
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        PrintWriter writer = response.getWriter();
        try {
            if (isApplication(request)) {
                LoanApplication application = new LoanApplication();
                application.setAmount(amountFrom(request));
                application.setContact(contactFrom(request));
                Ticket ticket = repo.store(application);
                writer.println(new Gson().toJson(ticket));
            } else if (isStatusRequest(request) && RequestHelper.idSpecified(request)) {
                writer.println(fetchLoanInfo(request.getParameter(RequestHelper.TICKET_ID)));
            } else if (isApproval(request) && RequestHelper.idSpecified(request)) {
                writer.println(approveLoan(request.getParameter(RequestHelper.TICKET_ID)));
            } else {
                writer.println("Incorrect parameters provided");
            }
        } catch (ApplicationException e) {
            writer.println("Uh oh! Problem occured: " + e.getMessage());
        }
    }

    private String contactFrom(HttpServletRequest request) {
        return request.getParameter("contact");
    }

    private long amountFrom(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("amount"));
    }

    private String approveLoan(String parameter) {
        return new Gson().toJson(repo.approve(parameter));
    }

    private boolean isApproval(HttpServletRequest request) {
        return APPROVE.equals(request.getParameter("action"));
    }

    private boolean isStatusRequest(HttpServletRequest request) {
        return FETCH.equals(request.getParameter("action"));
    }

    private boolean isApplication(HttpServletRequest request) {
        return APPLICATION.equals(request.getParameter("action"));
    }

    private String fetchLoanInfo(String ticketId) {
        LoanApplication formerApplication = repo.fetch(ticketId);
        return new Gson().toJson(formerApplication);
    }

}
