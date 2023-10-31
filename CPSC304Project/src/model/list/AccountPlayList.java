package model.list;

import model.Account;
import model.AccountPlay;

import java.util.ArrayList;
import java.util.List;

public class AccountPlayList {
    private List<AccountPlay> accountPlayList;

    public AccountPlayList(AccountList accounts, TeamParticipationList teamParticipations) {
        accountPlayList = new ArrayList<>();
        accountPlayList.add(new AccountPlay(teamParticipations.getTeamParticipations().get(0).getChampionName(),
                teamParticipations.getTeamParticipations().get(0).getTeamColor(),
                accounts.getAccounts().get(0).getAccountID(),
                accounts.getAccounts().get(0).getAccountName(),
                teamParticipations.getTeamParticipations().get(0).getMatchID(),
                10));
        accountPlayList.add(new AccountPlay(teamParticipations.getTeamParticipations().get(1).getChampionName(),
                teamParticipations.getTeamParticipations().get(1).getTeamColor(),
                accounts.getAccounts().get(1).getAccountID(),
                accounts.getAccounts().get(1).getAccountName(),
                teamParticipations.getTeamParticipations().get(1).getMatchID(),
                20));
        accountPlayList.add(new AccountPlay(teamParticipations.getTeamParticipations().get(2).getChampionName(),
                teamParticipations.getTeamParticipations().get(2).getTeamColor(),
                accounts.getAccounts().get(2).getAccountID(),
                accounts.getAccounts().get(2).getAccountName(),
                teamParticipations.getTeamParticipations().get(2).getMatchID(),
                30));
        accountPlayList.add(new AccountPlay(teamParticipations.getTeamParticipations().get(3).getChampionName(),
                teamParticipations.getTeamParticipations().get(3).getTeamColor(),
                accounts.getAccounts().get(3).getAccountID(),
                accounts.getAccounts().get(3).getAccountName(),
                teamParticipations.getTeamParticipations().get(3).getMatchID(),
                40));
        accountPlayList.add(new AccountPlay(teamParticipations.getTeamParticipations().get(4).getChampionName(),
                teamParticipations.getTeamParticipations().get(4).getTeamColor(),
                accounts.getAccounts().get(4).getAccountID(),
                accounts.getAccounts().get(4).getAccountName(),
                teamParticipations.getTeamParticipations().get(4).getMatchID(),
                50));

    }

    public AccountPlayList() {
        accountPlayList = new ArrayList<>();
    }

    public List<AccountPlay> getAccountPlayList() {
        return accountPlayList;
    }

    public void add(AccountPlay[] accountPlays) {
        for (int i = 0; i < accountPlays.length; i++) {
            accountPlayList.add(accountPlays[i]);
        }
    }

    public void printAccountPlayData() {
        System.out.printf(" %s %20s %20s %20s %20s %20s\n", "AccountID", "AccountName", "MatchID", "TeamColor", "ChampionName", "PlayStats");
        for (int i = 0; i < this.accountPlayList.size(); i++) {
            System.out.printf("%10s %20s %20s %20s %20s %20f\n", accountPlayList.get(i).getAccountID(), accountPlayList.get(i).getAccountName(), accountPlayList.get(i).getMatchID(), accountPlayList.get(i).getTeamColor(), accountPlayList.get(i).getChampionName(), accountPlayList.get(i).getPlayStats());
        }
    }

}
