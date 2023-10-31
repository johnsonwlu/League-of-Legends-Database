package model;

public class Friendlist {
    private String accountID;
    private String accountName;
    private String friendlistedID;
    private String friendlistedName;

    public Friendlist(Account account, Account friendlisted) {
        if (account.getAccountID() != friendlisted.getAccountID()) {
            this.accountID = account.getAccountID();
            this.accountName = account.getAccountName();
            this.friendlistedID = friendlisted.getAccountID();
            this.friendlistedName = friendlisted.getAccountName();
        }
    }

    public Friendlist(String accountID, String accountName, String friendlistedID, String friendlistedName) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.friendlistedID = friendlistedID;
        this.friendlistedName = friendlistedName;
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

    public String getFriendlistedID() {
        return friendlistedID;
    }

    public void setFriendlistedID(String friendlistedID) {
        this.friendlistedID = friendlistedID;
    }

    public String getFriendlistedName() {
        return friendlistedName;
    }

    public void setFriendlistedName(String friendlistedName) {
        this.friendlistedName = friendlistedName;
    }

    @Override
    public String toString() {
        return "Friendlist{" +
                "accountID='" + accountID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", friendlistedID='" + friendlistedID + '\'' +
                ", friendlistedName='" + friendlistedName + '\'' +
                '}';
    }
}
