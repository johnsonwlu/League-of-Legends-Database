package model.list;

import model.match.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchList {
    private List<Match> matches;

    public MatchList(BotMatchList botMatches, PVPMatchList pvpMatches) {
        matches = new ArrayList<>();
        addBotMatch(botMatches);
        addPVPMatch(pvpMatches);
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void addBotMatch(BotMatchList botMatches) {
        for (int i = 0; i < botMatches.getBotMatches().size(); i++) {
            this.matches.add(botMatches.getBotMatches().get(i));
        }
    }

    public void addPVPMatch(PVPMatchList pvpMatches) {
        for (int i = 0; i < pvpMatches.getPvpMatches().size(); i++) {
            this.matches.add(pvpMatches.getPvpMatches().get(i));
        }
    }
}
