package org.mikadomethod.loanserver;

public class Ticket {
    private long id;

    public Ticket(long applicationNo) {
        id = applicationNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
