package org.mikadomethod.loanserver;

import java.util.HashMap;
import java.util.Map;

public class MemoryLoanRepository implements LoanRepository {

    private final Map<String, LoanApplication> applications = new HashMap<String, LoanApplication>();

    @Override
    public Ticket store(LoanApplication application) {
        int nextId = nextId();
        applications.put(nextId + "", application);
        return new Ticket(nextId);
    }

    private int nextId() {
        return currentId() + 1;
    }

    private int currentId() {
        return applications.size();
    }

    @Override
    public Ticket approve(String ticketId) {
        LoanApplication application = fetch(ticketId);
        application.approve();
        return new Ticket(application.getApplicationNo());
    }

    @Override
    public LoanApplication fetch(String ticketId) {
        return applications.get(ticketId);

    }
}
