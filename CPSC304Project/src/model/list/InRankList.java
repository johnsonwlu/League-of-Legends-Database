package model.list;

import model.InRank;

import java.util.ArrayList;
import java.util.List;

public class InRankList {
    private List<InRank> inRanks;

    public InRankList(AccountList accounts, RankList ranks) {
        inRanks = new ArrayList<>();
        inRanks.add(new InRank(accounts.getAccounts().get(0).getAccountID(), accounts.getAccounts().get(0).getAccountName(), ranks.getRanks().get(0).getRankNumber(), ranks.getRanks().get(0).getRankTier()));
        inRanks.add(new InRank(accounts.getAccounts().get(1).getAccountID(), accounts.getAccounts().get(1).getAccountName(), ranks.getRanks().get(2).getRankNumber(), ranks.getRanks().get(2).getRankTier()));
        inRanks.add(new InRank(accounts.getAccounts().get(2).getAccountID(), accounts.getAccounts().get(2).getAccountName(), ranks.getRanks().get(3).getRankNumber(), ranks.getRanks().get(3).getRankTier()));
        inRanks.add(new InRank(accounts.getAccounts().get(3).getAccountID(), accounts.getAccounts().get(3).getAccountName(), ranks.getRanks().get(0).getRankNumber(), ranks.getRanks().get(0).getRankTier()));
        inRanks.add(new InRank(accounts.getAccounts().get(4).getAccountID(), accounts.getAccounts().get(4).getAccountName(), ranks.getRanks().get(1).getRankNumber(), ranks.getRanks().get(5).getRankTier()));
        inRanks.add(new InRank(accounts.getAccounts().get(5).getAccountID(), accounts.getAccounts().get(5).getAccountName(), ranks.getRanks().get(2).getRankNumber(), ranks.getRanks().get(2).getRankTier()));
    }

    public InRankList() {
        inRanks = new ArrayList<>();
    }

    public List<InRank> getInRanks() {
        return inRanks;
    }

    public void printInRankData() {
        System.out.printf("%s %15s %15s %15s\n", "Account Name", "Account ID", "Rank Number", "Rank Tier");
        for (int i = 0; i < inRanks.size(); i++) {
            System.out.printf("%9s %15s %15s %15s\n", inRanks.get(i).getAccountName(),
                    inRanks.get(i).getAccountID(),
                    inRanks.get(i).getRankNumber(),
                    inRanks.get(i).getRankTier());

        }
    }

    public void add(InRank[] inRankArrayFromDatabase) {
        for(int i = 0; i < inRankArrayFromDatabase.length; i++) {
            this.inRanks.add(inRankArrayFromDatabase[i]);
        }
    }
}
