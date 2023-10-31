package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountID;
    private String accountName;
    private float accountStats;
    private int accountLevel;
    private String accountStatus;

    public Account(String accountID, String accountName, float accountStats, int accountLevel, String accountStatus) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountStats = accountStats;
        this.accountLevel = accountLevel;
        this.accountStatus = accountStatus;
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

    public float getAccountStats() {
        return accountStats;
    }

    public void setAccountStats(int accountStats) {
        this.accountStats = accountStats;
    }

    public int getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(int accountLevel) {
        this.accountLevel = accountLevel;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }



    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountStats=" + accountStats +
                ", accountLevel=" + accountLevel +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
