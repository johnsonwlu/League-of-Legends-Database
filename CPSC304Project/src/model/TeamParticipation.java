package model;

import model.match.Match;

import java.util.ArrayList;
import java.util.List;

public class TeamParticipation {
    private String matchID;
    private String championName;
    private String teamColor;
    private int firstTowerTakeDownInSecond;
    private int firstDragonInSecond;
    private int inTeamStats;
    private String participationWinner;

    public TeamParticipation(String matchID, String championName, String teamColor, int firstTowerTakeDownInSecond, int firstDragonInSecond, int inTeamStats, String participationWinner) {
        this.matchID = matchID;
        this.championName = championName;
        this.teamColor = teamColor;
        this.firstTowerTakeDownInSecond = firstTowerTakeDownInSecond;
        this.firstDragonInSecond = firstDragonInSecond;
        this.inTeamStats = inTeamStats;
        this.participationWinner = participationWinner;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatch(String matchID) {
        this.matchID = matchID;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampion(String championName) {
        this.championName = championName;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public int getFirstTowerTakeDown() {
        return firstTowerTakeDownInSecond;
    }

    public void setFirstTowerTakeDown(int firstTowerTakeDownInSecond) {
        this.firstTowerTakeDownInSecond = firstTowerTakeDownInSecond;
    }

    public int getFirstDragonInSecond() {
        return firstDragonInSecond;
    }

    public void setFirstDragonInSecond(int firstDragonInSecond) {
        this.firstDragonInSecond = firstDragonInSecond;
    }

    public int getInTeamStats() {
        return inTeamStats;
    }

    public void setInTeamStats(int inTeamStats) {
        this.inTeamStats = inTeamStats;
    }

    public String getParticipationWinner() {
        return participationWinner;
    }

    public void setParticipationWinner(String participationWinner) {
        this.participationWinner = participationWinner;
    }

    @Override
    public String toString() {
        return "TeamParticipation{" +
                "matchID='" + matchID + '\'' +
                ", championName='" + championName + '\'' +
                ", teamColor='" + teamColor + '\'' +
                ", firstTowerTakeDownInSecond=" + firstTowerTakeDownInSecond +
                ", firstDragonInSecond=" + firstDragonInSecond +
                ", inTeamStats=" + inTeamStats +
                ", participationWinner='" + participationWinner + '\'' +
                '}';
    }
}
