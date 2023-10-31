package controller;
import UI.MainWindow;
import database.Query;
import database.tables.*;
import model.*;
import model.list.*;
import model.match.BotMatch;
import model.match.PVPMatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utility.Utility;
import java.util.ArrayList;
import UI.LoginWindow;
import delegates.LoginWindowDelegate;
import delegates.TerminalTransactionsDelegate;


//References: CPSC304 - Bank.java
public class LeagueOfLegendDatabase implements LoginWindowDelegate, TerminalTransactionsDelegate {
    private Database dbHandler = null;
    private UI.LoginWindow loginWindow =null;
    private ChampionDatabase championDatabase = null;
    private BotMatchDatabase botMatchDatabase = null;
    private PVPMatchDatabase pvpMatchDatabase = null;
    private ItemDatabase itemDatabase = null;
    private RunesDatabase runesDatabase = null;
    private RoleDatabase roleDatabase = null;
    private InRankDatabase inRankDatabase = null;
    private AccountPlayDatabase accountPlayDatabase = null;
    private SummonerSpellDatabase summonerSpellDatabase = null;
    private RankDatabase rankDatabase = null;
    private TeamParticipationDatabase teamParticipationDatabase = null;
    private AccountDatabase accountDatabase = null;
    private BlacklistDatabase blacklistDatabase = null;
    private FriendlistDatabase friendlistDatabase = null;
    private InTeamUseDatabase inTeamUseDatabase = null;
    private AccountList accounts;
    private FriendlistList friendlists;
    private BlacklistList blacklists;
    private RankList ranks;
    private InRankList inRanks;
    private ChampionList champions;
    private MatchList matches;
    private BotMatchList botMatches;
    private PVPMatchList pvpMatches;
    private TeamParticipationList teamParticipations;
    private AccountPlayList accountPlayList;
    private ItemList items;
    private SummonerSpellList summonerSpells;
    private RunesList runes;
    private InTeamUseList inTeamUseList;
    private RoleList roles;
    // credentials
    public String username;
    public String password;
    // connection
    private Connection connection;
    public static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";

    public static final String EXCEPTION_TAG = "[EXCEPTION]";

    public LeagueOfLegendDatabase() {

    }

    private void start() {


    }


    public void terminalTransactionsFinished() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }

    private void startWindow() {
        this.dbHandler = new Database();
        loginWindow = new UI.LoginWindow();
        loginWindow.showFrame(this);


    }


    public void login(String username, String password) throws IOException, SQLException {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            while (loginWindow.getUserName() == null || loginWindow.getPassword() == null)
            {

            }
            //
            this.username = loginWindow.getUserName();
            this.password = loginWindow.getPassword();

            try{
                //this.setTablesAndData();
            } catch (Exception e) {

            }
            boolean didConnection = this.dbHandler.login(this.username, this.password);
            System.out.println(didConnection);
            Query query = new Query();
            query.setCredentials(this.username, this.password);
            query.getConnection();

            loginWindow.dispose();

            DatabaseMainWindow databaseMainWindow = new DatabaseMainWindow(this.get_connection());
            MainWindow mainWindow = new UI.MainWindow();
            mainWindow.showFrame(databaseMainWindow);
        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }



    public void setAccountDatabase() throws SQLException {
        accountDatabase = new AccountDatabase();
        accountDatabase.getConnection();
        accountDatabase.dropAccountTableIfExists();
        accountDatabase.createAccountTable();
        accounts = new AccountList();

        for (int i = 0; i < this.accounts.getAccounts().size(); i++) {
            accountDatabase.insertAccountDatabaseTuple(this.accounts.getAccounts().get(i));
        }

        Account[] accounts = accountDatabase.getAccountArrayFromDatabase();
        for (int i = 0; i < accounts.length; i++) {
            System.out.println(accounts[i]);
        }
        accountDatabase.close();
    }

    public void setFriendlistDatabase() throws SQLException {
        friendlistDatabase = new FriendlistDatabase();
        friendlistDatabase.getConnection();
        friendlistDatabase.dropFriendlistTableIfExists();
        friendlistDatabase.createFriendlistTable();

        friendlists = new FriendlistList(accounts);
        for (int i = 0; i < this.friendlists.getFriendlists().size(); i++) {
            friendlistDatabase.insertFriendlistDatabaseTuple(this.friendlists.getFriendlists().get(i));
        }

        Friendlist[] friendlists = friendlistDatabase.getFriendlistArrayFromDatabase();
        for (int i = 0; i < friendlists.length; i++) {
            System.out.println(friendlists[i]);
        }
        friendlistDatabase.close();
    }

    public void setBlacklistDatabase() throws SQLException {
        blacklistDatabase = new BlacklistDatabase();
        blacklistDatabase.getConnection();
        blacklistDatabase.dropBlacklistTableIfExists();
        blacklistDatabase.createBlacklistTable();

        blacklists = new BlacklistList(accounts);
        for (int i = 0; i < this.blacklists.getBlacklistList().size(); i++) {
            blacklistDatabase.insertBlacklistDatabaseTuple(this.blacklists.getBlacklistList().get(i));
        }

        Blacklist[] blacklists = blacklistDatabase.getBlacklistArrayFromDatabase();
        for (int i = 0; i < blacklists.length; i++) {
            System.out.println(blacklists[i]);
        }
        blacklistDatabase.close();
    }

    public void setChampionDatabase() throws SQLException {
        championDatabase = new ChampionDatabase(this.username, this.password);
        championDatabase.getConnection();
        championDatabase.dropChampionTableIfExists();
        championDatabase.createChampionTable();
        champions = new ChampionList();

        for (int i = 0; i < this.champions.getChampions().size(); i++) {
            championDatabase.insertChampionTuple(this.champions.getChampions().get(i));
        }

        Champion[] champions = championDatabase.getChampionArrayFromDatabase();
        for (int i = 0; i < champions.length; i++) {
            System.out.println(champions[i]);
        }
        championDatabase.close();
    }

    public void setBotMatchDatabase() throws SQLException {
        botMatchDatabase = new BotMatchDatabase(this.username, this.password);
        botMatchDatabase.getConnection();
        botMatchDatabase.dropBotMatchTableIfExists();
        botMatchDatabase.createBotMatchTable();
        botMatches = new BotMatchList();
        for (int i = 0; i < this.botMatches.getBotMatches().size(); i++) {
            botMatchDatabase.insertBotMatchDatabaseTuple(this.botMatches.getBotMatches().get(i));
        }

        BotMatch[] botMatches = botMatchDatabase.getBotMatchArrayFromDatabase();
        for (int i = 0; i < botMatches.length; i++) {
            System.out.println(botMatches[i]);
        }
        botMatchDatabase.close();
    }

    public void setPVPMatchDatabase() throws SQLException {
        pvpMatchDatabase = new PVPMatchDatabase(this.username, this.password);
        pvpMatchDatabase.getConnection();
        pvpMatchDatabase.dropPVPMatchTableIfExists();
        pvpMatchDatabase.createPVPMatchTable();

        pvpMatches = new PVPMatchList();
        for (int i = 0; i < this.pvpMatches.getPvpMatches().size(); i++) {
            pvpMatchDatabase.insertPVPMatchTuple(this.pvpMatches.getPvpMatches().get(i));
        }
        PVPMatch[] pvpMatches = pvpMatchDatabase.getPVPMatchArrayFromDatabase();
        for (int i = 0; i < pvpMatches.length; i++) {
            System.out.println(pvpMatches[i]);
        }
        pvpMatchDatabase.close();
    }

    public void setItemDatabase() throws SQLException {
        itemDatabase = new ItemDatabase(this.username, this.password);
        itemDatabase.getConnection();
        itemDatabase.dropItemTableIfExists();
        itemDatabase.createItemTable();
        items = new ItemList();

        for (int i = 0; i < this.items.getItems().size(); i++) {
            itemDatabase.insertItemTuple(this.items.getItems().get(i));
        }

        Item[] items = itemDatabase.getItemArrayFromDatabase();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        itemDatabase.close();
    }

    public void setRunesDatabase() throws SQLException {
        runesDatabase = new RunesDatabase(this.username, this.password);
        runesDatabase.getConnection();
        runesDatabase.dropRunesTableIfExists();
        runesDatabase.createRunesTable();
        runes = new RunesList();
        for (int i = 0; i < this.runes.getRunes().size(); i++) {
            runesDatabase.insertRunesTuple(this.runes.getRunes().get(i));
        }
        Runes[] runes = runesDatabase.getRunesArrayFromDatabase();
        for (int i = 0; i < runes.length; i++) {
            System.out.println(runes[i]);
        }
        runesDatabase.close();
    }

    public void setSummonerSpellDatabase() throws SQLException {
        summonerSpellDatabase = new SummonerSpellDatabase(this.username, this.password);
        summonerSpellDatabase.getConnection();
        summonerSpellDatabase.dropSummonerSpellTableIfExists();
        summonerSpellDatabase.createSummonerSpellTable();
        summonerSpells = new SummonerSpellList();

        for (int i = 0; i < this.summonerSpells.getSummonerSpells().size(); i++) {
            summonerSpellDatabase.insertSummonerSpellTuple(this.summonerSpells.getSummonerSpells().get(i));
        }

        SummonerSpell[] runes = summonerSpellDatabase.getSummonerSpellArrayFromDatabase();
        for (int i = 0; i < runes.length; i++) {
            System.out.println(runes[i]);
        }
        summonerSpellDatabase.close();
    }

    public void setRanksDatabase() throws SQLException {
        rankDatabase = new RankDatabase(this.username, this.password);
        rankDatabase.getConnection();
        rankDatabase.dropRankTableIfExists();
        rankDatabase.createRankTable();

        ranks = new RankList();

        for (int i = 0; i < this.ranks.getRanks().size(); i++) {
            rankDatabase.insertRankTuple(this.ranks.getRanks().get(i));
        }

        Rank[] ranks = rankDatabase.getRankArrayFromDatabase();
        for (int i = 0; i < ranks.length; i++) {
            System.out.println(ranks[i]);
        }
        rankDatabase.close();
    }

    public void setRolesDatabase() throws SQLException {
        roleDatabase = new RoleDatabase(this.username, this.password);
        roleDatabase.getConnection();
        roleDatabase.dropRoleTableIfExists();
        roleDatabase.createRolesTable();

        roles = new RoleList();

        for (int i = 0; i < this.roles.getRoles().size(); i++) {
            roleDatabase.insertRolesTuple(this.roles.getRoles().get(i));
        }

        Role[] roles = roleDatabase.getRolesArrayFromDatabase();
        for (int i = 0; i < roles.length; i++) {
            System.out.println(roles[i]);
        }
        roleDatabase.close();
    }

    public void setInRanksDatabase() throws SQLException {
        inRankDatabase = new InRankDatabase(this.username, this.password);
        inRankDatabase.getConnection();
        inRankDatabase.dropInRankTableIfExists();
        inRankDatabase.createInRankTable();

        inRanks = new InRankList(accounts, ranks);

        for (int i = 0; i < this.inRanks.getInRanks().size(); i++) {
            inRankDatabase.insertInRankTuple(this.inRanks.getInRanks().get(i));
        }

        InRank[] inRanks = inRankDatabase.getInRankArrayFromDatabase();
        for (int i = 0; i < inRanks.length; i++) {
            System.out.println(inRanks[i]);
        }
        inRankDatabase.close();
    }

    public void setTeamParticipationDatabase() throws SQLException {
        teamParticipationDatabase = new TeamParticipationDatabase(this.username, this.password);
        teamParticipationDatabase.getConnection();
        teamParticipationDatabase.dropTeamParticipationTableIfExists();
        teamParticipationDatabase.createTeamParticipationTable();

        matches = new MatchList(botMatches, pvpMatches);
        teamParticipations = new TeamParticipationList(champions, matches);

        for (int i = 0; i < this.teamParticipations.getTeamParticipations().size(); i++) {
            teamParticipationDatabase.insertTeamParticipationTuple(this.teamParticipations.getTeamParticipations().get(i));
        }

        TeamParticipation[] teamParticipations = teamParticipationDatabase.getTeamParticipationArrayFromDatabase();
        for (int i = 0; i < teamParticipations.length; i++) {
            System.out.println(teamParticipations[i]);
        }
        teamParticipationDatabase.close();
    }

    public void setAccountPlayDatabase() throws SQLException {
        accountPlayDatabase = new AccountPlayDatabase(this.username, this.password);
        accountPlayDatabase.getConnection();
        accountPlayDatabase.dropAccountPlayTableIfExists();
        accountPlayDatabase.createAccountPlayTable();

        accountPlayList = new AccountPlayList(accounts, teamParticipations);

        for (int i = 0; i < this.accountPlayList.getAccountPlayList().size(); i++) {
            accountPlayDatabase.insertAccountPlayDatabaseTuple(this.accountPlayList.getAccountPlayList().get(i));
        }

        AccountPlay[] accountPlays = accountPlayDatabase.getAccountPlayArrayFromDatabase();
        for (int i = 0; i < accountPlays.length; i++) {
            System.out.println(accountPlays[i]);
        }
        accountPlayDatabase.close();
    }

    public void setInTeamUseDatabase() throws SQLException {
        inTeamUseDatabase = new InTeamUseDatabase(this.username, this.password);
        inTeamUseDatabase.getConnection();
        inTeamUseDatabase.dropInTeamUseTableIfExists();
        inTeamUseDatabase.createInTeamUseTable();

        inTeamUseList = new InTeamUseList(teamParticipations, items, summonerSpells, runes);

        for (int i = 0; i < this.inTeamUseList.getInTeamUseList().size(); i++) {
            inTeamUseDatabase.insertInTeamUseTuple(this.inTeamUseList.getInTeamUseList().get(i));
        }

        InTeamUse[] inTeamUses = inTeamUseDatabase.getInTeamUseArrayFromDatabase();
        for (int i = 0; i < inTeamUses.length; i++) {
            System.out.println(inTeamUses[i]);
        }
        inTeamUseDatabase.close();
    }

    public void setTablesAndData() throws SQLException {
        setChampionDatabase();
        setBotMatchDatabase();
        setPVPMatchDatabase();
        setItemDatabase();
        setRunesDatabase();
        setSummonerSpellDatabase();
        setAccountDatabase();
        setBlacklistDatabase();
        setFriendlistDatabase();
        setRanksDatabase();
        setRolesDatabase();
        setInRanksDatabase();
        setTeamParticipationDatabase();
        setAccountPlayDatabase();
        setInTeamUseDatabase();
    }

    private Connection get_connection()
    {
        // get connection
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            this.connection = DriverManager.getConnection(ORACLE_URL, this.username, this.password);
            this.connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return connection;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        LeagueOfLegendDatabase leagueOfLegendDatabase = new LeagueOfLegendDatabase();
        leagueOfLegendDatabase.startWindow();
        //leagueOfLegendDatabase.setTablesAndData();

    }

}
