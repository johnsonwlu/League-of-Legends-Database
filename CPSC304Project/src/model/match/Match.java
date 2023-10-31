package model.match;

import model.TeamParticipation;

import java.util.Date;
import java.util.List;

public class Match {
    private String matchID;
    private String matchMap;
    private String matchStartTime;
    private String matchEndTime;

    public Match(String matchID, String matchMap, String matchStartTime, String matchEndTime) {
        this.matchID = matchID;
        this.matchMap = matchMap;
        this.matchStartTime = matchStartTime;
        this.matchEndTime = matchEndTime;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getMatchMap() {
        return matchMap;
    }

    public void setMatchMap(String matchMap) {
        this.matchMap = matchMap;
    }

    public String getMatchStartTime() {
        return matchStartTime;
    }

    public void setMatchStartTime(String matchStartTime) {
        this.matchStartTime = matchStartTime;
    }

    public String getMatchEndTime() {
        return matchEndTime;
    }

    public void setMatchEndTime(String matchEndTime) {
        this.matchEndTime = matchEndTime;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchID='" + matchID + '\'' +
                ", matchMap='" + matchMap + '\'' +
                ", matchStartTime='" + matchStartTime + '\'' +
                ", matchEndTime='" + matchEndTime + '\'' +
                '}';
    }
}
