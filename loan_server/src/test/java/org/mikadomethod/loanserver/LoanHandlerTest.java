package org.mikadomethod.loanserver;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class LoanHandlerTest {

    LoanHandler loanHandler;
    RequestStub baseRequest;
    ResponseStub response;

    @Before
    public void setUp() {
        loanHandler = new LoanHandler(new MemoryLoanRepository());
        baseRequest = new RequestStub();
        response = new ResponseStub();
    }

    @Test
    public void incompleteRequest() throws Exception {
        Map<String, String> params = Collections
                .<String, String> emptyMap();
        ServletRequestStub request = new ServletRequestStub(params);
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        String actual = response.responseAsText();
        assertEquals("Incorrect parameters provided\n", actual);
    }

    @Test
    public void completeApplication() throws Exception {
        ServletRequestStub request = new ServletRequestStub(applyParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("{\"id\":1}\n", response.responseAsText());
    }

    @Test
    public void loanApplicationsCanBeApproved() throws Exception {
        ServletRequestStub apply = new ServletRequestStub(
                applyParams());
        loanHandler.handle(null, baseRequest, apply, new ResponseStub());
        ServletRequestStub request = new ServletRequestStub(
                approveParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("{\"id\":0}\n", response.responseAsText());
    }


    @Test
    public void givenAnIdTheStatusOfLoanIsReturned() throws Exception {
    	ServletRequestStub apply = new ServletRequestStub(applyParams());
        loanHandler.handle(null, baseRequest, apply, new ResponseStub());
    	
        response = new ResponseStub();
    	ServletRequestStub request = new ServletRequestStub(fetchParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();

        assertEquals("{\"applicationNo\":0," + "\"amount\":100,"
                + "\"contact\":\"donald@ducks.burg\",\"approved\":false}\n",
                response.responseAsText());
    }

    private HashMap<String, String> approveParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPROVE);
        params.put("ticketId", "1");
        return params;
    }

    private HashMap<String, String> applyParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPLICATION);
        params.put("amount", "100");
        params.put("contact", "donald@ducks.burg");
        return params;
    }

    private HashMap<String, String> fetchParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.FETCH);
        params.put("ticketId", "1");
        return params;
    }
}
