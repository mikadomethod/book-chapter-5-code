package org.mikadomethod.loanserver;

public interface LoanRepository {

	LoanApplication fetch(String ticketId);

	Ticket store(LoanApplication application);

	Ticket approve(String ticketId);

}