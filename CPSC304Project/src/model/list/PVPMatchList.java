package model.list;

import model.match.PVPMatch;

import java.util.ArrayList;
import java.util.List;

public class PVPMatchList{
    private List<PVPMatch> pvpMatches;

    public PVPMatchList() {
        this.pvpMatches = new ArrayList<>();
        pvpMatches.add(new PVPMatch("m006", "mMap3", "9:00am", "10:00am", 1));
        pvpMatches.add(new PVPMatch("m007", "mMap1", "9:00am", "10:00am", 2));
        pvpMatches.add(new PVPMatch("m008", "mMap2", "8:00am", "9:00am", 3));
        pvpMatches.add(new PVPMatch("m009", "mMap2", "12:00pm", "1:00pm", 1));
        pvpMatches.add(new PVPMatch("m0010", "mMap2", "2:00pm", "3:00pm", 2));
        pvpMatches.add(new PVPMatch("m0011", "mMap2", "3:00pm", "4:00pm", 4));

    }

    public List<PVPMatch> getPvpMatches() {
        return pvpMatches;
    }

    public void printPVPMatchData() {
        System.out.printf("   %s %20s %20s %20s %20s\n", "MatchID", "MatchMap", "Match Start Time", "Match End Time", "Rank");
        for (int i = 0; i < pvpMatches.size(); i++) {
            System.out.printf("%10s %20s %20s %20s %20d\n",  pvpMatches.get(i).getMatchID(), pvpMatches.get(i).getMatchMap(), pvpMatches.get(i).getMatchStartTime(), pvpMatches.get(i).getMatchEndTime(), pvpMatches.get(i).getRank());
        }
    }

    public void add(PVPMatch[] pvpMatchArrayFromDatabase) {
        for (int i = 0; i < pvpMatchArrayFromDatabase.length; i++) {
            this.pvpMatches.add(pvpMatchArrayFromDatabase[i]);
        }
    }
}
