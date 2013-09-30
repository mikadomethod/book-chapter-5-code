package org.mikadomethod.loanserver;

public class LoanApplication {

    private long applicationNo;
    private long amount;
    private String email;
    private String contact;
    private boolean approved;

    public LoanApplication() {
        applicationNo = LoanHandler.getNextId();
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void approve() {
        setApproved(true);
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setContact(String who) {
        this.contact = who;
    }

    public String getContact() {
        return contact;
    }
}
