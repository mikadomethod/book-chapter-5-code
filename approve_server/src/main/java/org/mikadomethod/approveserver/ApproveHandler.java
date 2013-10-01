package org.mikadomethod.approveserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.mikadomethod.loanserver.LoanRepository;
import org.mikadomethod.loanserver.RequestHelper;

import com.google.gson.Gson;

public class ApproveHandler extends AbstractHandler {
    public static final String APPROVE = "approve";
	private final LoanRepository repo;

    public ApproveHandler(LoanRepository loanRepository) {
		this.repo = loanRepository;
	}

	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        PrintWriter writer = response.getWriter();
	    if (isApproval(request) && RequestHelper.idSpecified(request)) {
	        writer.println(approveLoan(request.getParameter(RequestHelper.TICKET_ID)));
	    } else {
	        writer.println("Incorrect parameters provided");
	    }
	}

    private String approveLoan(String parameter) {
        return new Gson().toJson(repo.approve(parameter));
    }

    private boolean isApproval(HttpServletRequest request) {
        return APPROVE.equals(request.getParameter("action"));
    }


}
