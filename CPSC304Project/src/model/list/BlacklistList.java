package model.list;

import model.Account;
import model.Blacklist;

import java.util.ArrayList;
import java.util.List;

public class BlacklistList {
    private List<Blacklist> blacklistList;

    public BlacklistList(AccountList accounts) {
        this.blacklistList = new ArrayList<>();
        blacklistList.add(new Blacklist(accounts.getAccounts().get(0), accounts.getAccounts().get(1)));
        blacklistList.add(new Blacklist(accounts.getAccounts().get(1), accounts.getAccounts().get(2)));
        blacklistList.add(new Blacklist(accounts.getAccounts().get(0), accounts.getAccounts().get(2)));
        blacklistList.add(new Blacklist(accounts.getAccounts().get(0), accounts.getAccounts().get(3)));
        blacklistList.add(new Blacklist(accounts.getAccounts().get(0), accounts.getAccounts().get(4)));
        blacklistList.add(new Blacklist(accounts.getAccounts().get(2), accounts.getAccounts().get(0)));
    }

    public BlacklistList() {
        this.blacklistList = new ArrayList<>();
    }

    public List<Blacklist> getBlacklistList() {
        return blacklistList;
    }

    public void add(Blacklist[] blacklists) {
        for (int i = 0; i < blacklists.length; i++) {
            this.blacklistList.add(blacklists[i]);
        }
    }

    public void printBlacklistListData() {
        System.out.printf("%s %15s %15s %15s\n", "AccountID", "AccountName", "BlacklistedID", "BlacklistedName");//, "BlacklistID", "FriendlistID");
        for (int i = 0; i < blacklistList.size(); i++) {
            System.out.printf("%9s %15s %15s %15s\n", blacklistList.get(i).getAccountID(), blacklistList.get(i).getAccountName(),
                    blacklistList.get(i).getBlacklistedID(), blacklistList.get(i).getBlacklistedName());
        }
    }

}
