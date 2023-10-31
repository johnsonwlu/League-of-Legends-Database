package model;

import java.util.ArrayList;
import java.util.List;

public class AccountPlay {
    private String championName;
    private String teamColor;
    private String accountID;
    private String accountName;
    private String matchID;
    private float playStats;

    public AccountPlay(String championName, String teamColor, String accountID, String accountName, String matchID, float playStats) {
        this.championName = championName;
        this.teamColor = teamColor;
        this.accountID = accountID;
        this.accountName = accountName;
        this.matchID = matchID;
        this.playStats = playStats;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
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

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public float getPlayStats() {
        return playStats;
    }

    public void setPlayStats(float playStats) {
        this.playStats = playStats;
    }

    @Override
    public String toString() {
        return "AccountPlay{" +
                "championName='" + championName + '\'' +
                ", teamColor='" + teamColor + '\'' +
                ", accountID='" + accountID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", matchID='" + matchID + '\'' +
                ", playStats=" + playStats +
                '}';
    }
}
