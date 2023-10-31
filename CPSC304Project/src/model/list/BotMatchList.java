package model.list;

import model.Account;
import model.match.BotMatch;

import java.util.ArrayList;
import java.util.List;

public class BotMatchList {
    private List<BotMatch> botMatches;

    public BotMatchList() {
        this.botMatches = new ArrayList<>();
        botMatches.add(new BotMatch("m001", "mMap1", "9:00am", "10:00am", "Easy", true));
        botMatches.add(new BotMatch("m002", "mMap1", "8:00am", "9:00am", "Medium", false));
        botMatches.add(new BotMatch("m003", "mMap2", "12:00am", "1:00pm", "Hard", true));
        botMatches.add(new BotMatch("m004", "mMap3", "2:00pm", "3:00pm", "Easy", false));
        botMatches.add(new BotMatch("m005", "mMap2", "3:00pm", "3:00pm", "Easy", false));
    }

    public List<BotMatch> getBotMatches() {
        return botMatches;
    }

    public void add(BotMatch[] botMatches) {
        for (int i = 0; i < botMatches.length; i++) {
            this.botMatches.add(botMatches[i]);
        }
    }


    public void printBotMatchData() {
        System.out.printf("   %s %20s %20s %20s %20s %20s\n", "MatchID", "MatchMap", "Match Start Time", "Match End Time", "Difficulty", "Curses");
        for (int i = 0; i < botMatches.size(); i++) {
            System.out.printf("%10s %20s %20s %20s %20s %20s\n",  botMatches.get(i).getMatchID(), botMatches.get(i).getMatchMap(), botMatches.get(i).getMatchStartTime(), botMatches.get(i).getMatchEndTime(), botMatches.get(i).getDifficulty(), botMatches.get(i).isCurses());
        }
    }
}
