package model;

import java.util.ArrayList;
import java.util.List;

public class InRank {

    private String accountID;
    private String accountName;
    private String rankNumber;
    private String rankTier;

    public InRank(String accountID, String accountName, String rankNumber, String rankTier) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.rankNumber = rankNumber;
        this.rankTier = rankTier;
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

    public String getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(String rankNumber) {
        this.rankNumber = rankNumber;
    }

    public String getRankTier() {
        return rankTier;
    }

    public void setRankTier(String rankTier) {
        this.rankTier = rankTier;
    }

    @Override
    public String toString() {
        return "InRank{" +
                "accountID='" + accountID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", rankNumber='" + rankNumber + '\'' +
                ", rankTier='" + rankTier + '\'' +
                '}';
    }
}
