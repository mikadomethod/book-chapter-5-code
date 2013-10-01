package org.mikadomethod.loanserver;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

	public static final String TICKET_ID = "ticketId";

	public static long validId(HttpServletRequest request) {
	    String ticketId = request.getParameter(TICKET_ID);
	    try {
	        return Long.parseLong(ticketId);
	    } catch (NumberFormatException e) {
	        return -1L;
	    }
	}

	public static boolean idSpecified(HttpServletRequest request) {
	    return request.getParameter(TICKET_ID) != null && validId(request) >= 0;
	}

}
