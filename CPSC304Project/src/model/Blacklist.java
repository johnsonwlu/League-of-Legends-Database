package model;

public class Blacklist {
    private String accountID;
    private String accountName;
    private String blacklistedID;
    private String blacklistedName;

    public Blacklist(Account account, Account blacklisted) {
        if (account.getAccountID() != blacklisted.getAccountID()) {
            this.accountID = account.getAccountID();
            this.accountName = account.getAccountName();
            this.blacklistedID = account.getAccountID();
            this.blacklistedName = account.getAccountName();
        }
    }

    public Blacklist(String accountID, String accountName, String blacklistedID, String blacklistedName) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.blacklistedID = blacklistedID;
        this.blacklistedName = blacklistedName;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBlacklistedID() {
        return blacklistedID;
    }

    public void setBlacklistedID(String blacklistedID) {
        this.blacklistedID = blacklistedID;
    }

    public String getBlacklistedName() {
        return blacklistedName;
    }

    public void setBlacklistedName(String blacklistedName) {
        this.blacklistedName = blacklistedName;
    }

    @Override
    public String toString() {
        return "Blacklist{" +
                "accountID='" + accountID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", blacklistedID='" + blacklistedID + '\'' +
                ", blacklistedName='" + blacklistedName + '\'' +
                '}';
    }
}
