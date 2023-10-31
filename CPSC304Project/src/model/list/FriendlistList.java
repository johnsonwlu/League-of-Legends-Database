package model.list;

import model.Friendlist;

import java.util.ArrayList;
import java.util.List;

public class FriendlistList {
    private List<Friendlist> friendlists;

    public FriendlistList(AccountList accounts) {
        this.friendlists = new ArrayList<>();

        friendlists.add(new Friendlist(accounts.getAccounts().get(1), accounts.getAccounts().get(0)));
        friendlists.add(new Friendlist(accounts.getAccounts().get(1), accounts.getAccounts().get(3)));
        friendlists.add(new Friendlist(accounts.getAccounts().get(0), accounts.getAccounts().get(5)));
        friendlists.add(new Friendlist(accounts.getAccounts().get(0), accounts.getAccounts().get(1)));
        friendlists.add(new Friendlist(accounts.getAccounts().get(0), accounts.getAccounts().get(2)));
        friendlists.add(new Friendlist(accounts.getAccounts().get(2), accounts.getAccounts().get(1)));
    }

    public FriendlistList() {
        this.friendlists = new ArrayList<>();
    }

    public List<Friendlist> getFriendlists() {
        return friendlists;
    }

    public void add(Friendlist[] friendlistArrayFromDatabase) {
        for (int i = 0; i < friendlistArrayFromDatabase.length; i++) {
            this.friendlists.add(friendlistArrayFromDatabase[i]);
        }
    }

    public void printFriendlistData() {
        System.out.printf("%s %15s %15s %15s\n", "AccountID", "AccountName", "FriendlistID", "FriendlistName");//, "BlacklistID", "FriendlistID");
        for (int i = 0; i < friendlists.size(); i++) {
            System.out.printf("%9s %15s %15s %15s\n", friendlists.get(i).getAccountID(), friendlists.get(i).getAccountName(), friendlists.get(i).getFriendlistedID(), friendlists.get(i).getFriendlistedName());
        }
    }
}
