package model.match;

import model.match.Match;

import java.util.Date;

public class PVPMatch extends Match {
    private int rank;

    public PVPMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, int rank) {
        super(matchID, matchMap, matchStartTime, matchEndTime);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return super.toString() + "PVPMatch{" +
                "rank=" + rank +
                '}';
    }
}
