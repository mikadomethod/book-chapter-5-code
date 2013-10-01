package org.mikadomethod.approveserver;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mikadomethod.loanserver.LoanApplication;
import org.mikadomethod.loanserver.MemoryLoanRepository;
import org.mikadomethod.loanserver.RequestStub;
import org.mikadomethod.loanserver.ResponseStub;
import org.mikadomethod.loanserver.ServletRequestStub;

public class ApproveHandlerTest {
    ApproveHandler approveHandler;
    RequestStub baseRequest;
    ResponseStub response;
	private MemoryLoanRepository loanRepository;

    @Before
    public void setUp() {
        loanRepository = new MemoryLoanRepository();
		approveHandler = new ApproveHandler(loanRepository);
        baseRequest = new RequestStub();
        response = new ResponseStub();
    }

    @Test
    public void loanApplicationsCanBeApproved() throws Exception {
    	LoanApplication loanApplication = new LoanApplication();
    	loanApplication.setAmount(100);
		loanRepository.store(loanApplication);
        ServletRequestStub request = new ServletRequestStub(
                approveParams());
        approveHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("{\"id\":1}\n", response.responseAsText());
    }

    private HashMap<String, String> approveParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", ApproveHandler.APPROVE);
        params.put("ticketId", "1");
        return params;
    }
    
}
