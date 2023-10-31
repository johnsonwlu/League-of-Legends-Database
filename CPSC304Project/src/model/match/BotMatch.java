package model.match;

import model.match.Match;

import java.util.Date;

public class BotMatch extends Match {
    private String difficulty;
    private boolean curses;

    public BotMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, String difficulty, boolean curses) {
        super(matchID, matchMap, matchStartTime, matchEndTime);
        this.difficulty = difficulty;
        this.curses = curses;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isCurses() {
        return curses;
    }

    public void setCurses(boolean curses) {
        this.curses = curses;
    }



    @Override
    public String toString() {
        return super.toString() +
                "BotMatch{" +
                "difficulty='" + difficulty + '\'' +
                ", curses=" + curses +
                '}';
    }
}
