package controller;

import database.tables.*;
import delegates.InsertWindowDelegate;
import model.*;
import model.match.BotMatch;
import model.match.PVPMatch;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInsertWindow implements InsertWindowDelegate {
    public Connection connection = null;

    public Connection getConnection() {
        return this.connection;
    }

    public DatabaseInsertWindow(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void insertAccount(String accountID, String accountName, float accountStats, int accountLevel, String accountStatus) throws SQLException {
        handleAccountInsertion(new Account(accountID, accountName, accountStats, accountLevel, accountStatus));
    }

    @Override
    public void insertAccountPlay(String championName, String teamColor, String accountID, String accountName, String matchID, float playStats) throws SQLException {
        handleAccountPlayInsertion(new AccountPlay(championName, teamColor, accountID, accountName, matchID, playStats));
    }

    @Override
    public void insertBlacklist(String accountID, String accountName, String blacklistedID, String blacklistedName) throws SQLException {
        handleBlacklistInsertion(new Blacklist(accountID, accountName, blacklistedID, blacklistedName));
    }

    @Override
    public void insertBotMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, String difficulty, boolean curses) throws SQLException {
        handleBotMatchInsertion(new BotMatch(matchID, matchMap, matchStartTime, matchEndTime, difficulty, curses));
    }

    @Override
    public void insertChampion(String championName, String championType, int championStats) throws SQLException {
        handleChampionInsertion(new Champion(championName, championType, championStats));
    }

    @Override
    public void insertFriendlist(String accountID, String accountName, String friendlistedID, String friendlistedName) throws SQLException {
        handleFriendlistInsertion(new Friendlist(accountID, accountName, friendlistedID, friendlistedName));
    }

    @Override
    public void insertInRank(String accountID, String accountName, String rankNumber, String rankTier) throws SQLException {
        handleInRankInsertion(new InRank(accountID, accountName, rankNumber, rankTier));
    }

    @Override
    public void insertInTeamUse(String accountID, String teamColor, String championName, String itemName, String summonerSpellName, String primaryPath, String secondaryPath) throws SQLException {
        handleInTeamUseInsertion(new InTeamUse(accountID, teamColor, championName, itemName, summonerSpellName, primaryPath, secondaryPath));
    }

    @Override
    public void insertItem(String itemName, String itemDescription) throws SQLException {
        handleItemInsertion(new Item(itemName, itemDescription));
    }

    @Override
    public void insertPVPMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, int rank) throws SQLException {
        handlePVPMatchInsertion(new PVPMatch(matchID, matchMap, matchStartTime, matchEndTime, rank));
    }

    @Override
    public void insertRank(String rankNumber, String rankTier) throws SQLException {
        handleRankInsertion(new Rank(rankNumber, rankTier));
    }

    @Override
    public void insertRole(String roleName, String championName) throws SQLException {
        handleRoleInsertion(new Role(roleName, championName));
    }

    @Override
    public void insertRunes(String runesPrimaryPath, String runesSecondaryPath, String keystone) throws SQLException {
        handleRunesInsertion(new Runes(runesPrimaryPath, runesSecondaryPath, keystone));
    }

    @Override
    public void insertSummonerSpell(String summonerSpellName, String summonerSpellNameDescription) throws SQLException {
        handleSummmonerSpellInsertion(new SummonerSpell(summonerSpellName, summonerSpellNameDescription));
    }

    @Override
    public void insertTeamParticipation(String matchID, String championName, String teamColor, int firstTowerTakeDownInSecond, int firstDragonInSecond, int inTeamStats, String participationWinner) throws SQLException {
        handleTeamParticipationInsertion(new TeamParticipation(matchID, championName, teamColor, firstTowerTakeDownInSecond, firstDragonInSecond, inTeamStats, participationWinner));
    }


    public void handleAccountInsertion(Account account) throws SQLException {
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.setConnection(this.connection);
        accountDatabase.insertAccountDatabaseTuple(account);
        accountDatabase.close();
    }

    private void handleAccountPlayInsertion(AccountPlay accountPlay) throws SQLException {
        AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase();
        accountPlayDatabase.setConnection(this.connection);
        accountPlayDatabase.insertAccountPlayDatabaseTuple(accountPlay);
        accountPlayDatabase.close();
    }

    private void handleBlacklistInsertion(Blacklist blacklist) throws SQLException {
        BlacklistDatabase blacklistDatabase = new BlacklistDatabase();
        blacklistDatabase.setConnection(this.connection);
        blacklistDatabase.insertBlacklistDatabaseTuple(blacklist);
        blacklistDatabase.close();
    }

    private void handleBotMatchInsertion(BotMatch botMatch) throws SQLException {
        BotMatchDatabase botMatchDatabase = new BotMatchDatabase();
        botMatchDatabase.setConnection(this.connection);
        botMatchDatabase.insertBotMatchDatabaseTuple(botMatch);
        botMatchDatabase.close();
    }

    private void handleChampionInsertion(Champion champion) throws SQLException {
        ChampionDatabase championDatabase = new ChampionDatabase();
        championDatabase.setConnection(this.connection);
        championDatabase.insertChampionTuple(champion);
        championDatabase.close();
    }

    private void handleFriendlistInsertion(Friendlist friendlist) throws SQLException {
        FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
        friendlistDatabase.setConnection(this.connection);
        friendlistDatabase.insertFriendlistDatabaseTuple(friendlist);
        friendlistDatabase.close();
    }

    private void handleInRankInsertion(InRank inRank) throws SQLException {
        InRankDatabase inRankDatabase = new InRankDatabase();
        inRankDatabase.setConnection(this.connection);
        inRankDatabase.insertInRankTuple(inRank);
        inRankDatabase.close();
    }

    private void handleInTeamUseInsertion(InTeamUse inTeamUse) throws SQLException {
        InTeamUseDatabase inTeamUseDatabase = new InTeamUseDatabase();
        inTeamUseDatabase.setConnection(this.connection);
        inTeamUseDatabase.insertInTeamUseTuple(inTeamUse);
        inTeamUseDatabase.close();
    }

    private void handleItemInsertion(Item item) throws SQLException {
        ItemDatabase itemDatabase = new ItemDatabase();
        itemDatabase.setConnection(this.connection);
        itemDatabase.insertItemTuple(item);
        itemDatabase.close();
    }

    private void handlePVPMatchInsertion(PVPMatch pvpMatch) throws SQLException {
        PVPMatchDatabase pvpMatchDatabase = new PVPMatchDatabase();
        pvpMatchDatabase.setConnection(this.connection);
        pvpMatchDatabase.insertPVPMatchTuple(pvpMatch);
        pvpMatchDatabase.close();
    }

    private void handleRankInsertion(Rank rank) throws SQLException {
        RankDatabase rankDatabase = new RankDatabase();
        rankDatabase.setConnection(this.connection);
        rankDatabase.insertRankTuple(rank);
        rankDatabase.close();
    }

    private void handleRoleInsertion(Role role) throws SQLException {
        RoleDatabase roleDatabase = new RoleDatabase();
        roleDatabase.setConnection(this.connection);
        roleDatabase.insertRolesTuple(role);
        roleDatabase.close();
    }

    private void handleRunesInsertion(Runes runes) throws SQLException {
        RunesDatabase runesDatabase = new RunesDatabase();
        runesDatabase.setConnection(this.connection);
        runesDatabase.insertRunesTuple(runes);
        runesDatabase.close();
    }

    private void handleSummmonerSpellInsertion(SummonerSpell summonerSpell) throws SQLException {
        SummonerSpellDatabase summonerSpellDatabase = new SummonerSpellDatabase();
        summonerSpellDatabase.setConnection(this.connection);
        summonerSpellDatabase.insertSummonerSpellTuple(summonerSpell);
        summonerSpellDatabase.close();
    }

    //
    private void handleTeamParticipationInsertion(TeamParticipation teamParticipation) throws SQLException {
        TeamParticipationDatabase teamParticipationDatabase = new TeamParticipationDatabase();
        teamParticipationDatabase.setConnection(this.connection);
        teamParticipationDatabase.insertTeamParticipationTuple(teamParticipation);

        teamParticipationDatabase.close();

    }

}
