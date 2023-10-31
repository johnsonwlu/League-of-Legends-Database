package model.list;

import model.Account;
import model.Blacklist;

import java.util.ArrayList;
import java.util.List;

public class AccountList {

    private List<Account> accounts;

    public AccountList() {
        accounts = new ArrayList<>();
        accounts.add(new Account("a001", "accountName0", 5, 10, "Online"));
        accounts.add(new Account("a002", "accountName1", 6, 11, "Idle"));
        accounts.add(new Account("a003", "accountName2", 7, 12, "Offline"));
        accounts.add(new Account("a004", "accountName2", 7, 12, "Offline"));
        accounts.add(new Account("a005", "accountName3", 8, 13, "Online"));
        accounts.add(new Account("a006", "accountName4", 9, 14, "Idle"));
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void add(Account[] accounts) {
        for (int i = 0; i < accounts.length; i++) {
            this.accounts.add(accounts[i]);
        }
    }

    public void printAccountData() {
        System.out.printf("%s %15s %15s %15s %15s\n", "AccountID", "AccountName", "AccountStats", "AccountLevel", "AccountStatus");//, "BlacklistID", "FriendlistID");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%9s %15s %15f %15d %15s\n", accounts.get(i).getAccountID(), accounts.get(i).getAccountName(), accounts.get(i).getAccountStats(), accounts.get(i).getAccountLevel(), accounts.get(i).getAccountStatus());
            //, accountA001.getBlacklistByID(0).getAccountID(), accountA001.getFriendlistByID(0).getAccountID());
        }
    }

}
