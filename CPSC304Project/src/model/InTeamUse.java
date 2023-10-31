package model;

import java.util.ArrayList;
import java.util.List;

public class InTeamUse {
    private String matchID;
    private String teamColor;
    private String championName;
    private String itemName;
    private String summonerSpellName;
    private String primaryPath;
    private String secondaryPath;

    public InTeamUse(String matchID, String teamColor, String championName, String itemName, String summonerSpellName, String primaryPath, String secondaryPath) {
        this.matchID = matchID;
        this.teamColor = teamColor;
        this.championName = championName;
        this.itemName = itemName;
        this.summonerSpellName = summonerSpellName;
        this.primaryPath = primaryPath;
        this.secondaryPath = secondaryPath;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSummonerSpellName() {
        return summonerSpellName;
    }

    public void setSummonerSpellName(String summonerSpellName) {
        this.summonerSpellName = summonerSpellName;
    }

    public String getPrimaryPath() {
        return primaryPath;
    }

    public void setPrimaryPath(String primaryPath) {
        this.primaryPath = primaryPath;
    }

    public String getSecondaryPath() {
        return secondaryPath;
    }

    public void setSecondaryPath(String secondaryPath) {
        this.secondaryPath = secondaryPath;
    }

    @Override
    public String toString() {
        return "InTeamUse{" +
                "matchID='" + matchID + '\'' +
                ", teamColor='" + teamColor + '\'' +
                ", championName='" + championName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", summonerSpellName='" + summonerSpellName + '\'' +
                ", primaryPath='" + primaryPath + '\'' +
                ", secondaryPath='" + secondaryPath + '\'' +
                '}';
    }
}
