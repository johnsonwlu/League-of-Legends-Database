package model.list;

import model.Rank;

import java.util.ArrayList;
import java.util.List;

public class RankList {
    private List<Rank> ranks;

    public RankList() {
        this.ranks = new ArrayList<>();
        this.ranks.add(new Rank("r001","Iron"));
        this.ranks.add(new Rank("r002","Bronze"));
        this.ranks.add(new Rank("r003","Silver"));
        this.ranks.add(new Rank("r004","Gold"));
        this.ranks.add(new Rank("r005","Platinum"));
        this.ranks.add(new Rank("r006","Diamond"));
        this.ranks.add(new Rank("r007","Master"));
        this.ranks.add(new Rank("r008","Grandmaster"));
        this.ranks.add(new Rank("r009","Challenger"));
    }

    public List<Rank> getRanks(){
        return this.ranks;
    }

    public void printRankData() {
        System.out.printf("%s %15s\n", "Rank Number", "Rank Tier");
        for (int i = 0; i < ranks.size(); i++) {
            System.out.printf("%9s %15s\n", ranks.get(i).getRankNumber(), ranks.get(i).getRankTier());
        }
    }

    public void add(Rank[] rankArrayFromDatabase) {
        for (int i = 0; i < rankArrayFromDatabase.length; i++) {
            this.ranks.add(rankArrayFromDatabase[i]);
        }
    }
}
