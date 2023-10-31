package controller;
import database.tables.*;
import model.*;
import model.match.BotMatch;
import model.match.PVPMatch;
import delegates.UpdateWindowDelegate;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUpdateWindow implements UpdateWindowDelegate {
    private Connection connection = null;

    public Connection getConnection()  {
        return this.connection;
    }
    public DatabaseUpdateWindow(Connection connection) {this.connection = connection; }

    @Override
    public void updateAccount(String accountID, String accountName, float accountStats, int accountLevel, String accountStatus) throws SQLException {
        handleAccountUpdate(new Account(accountID,accountName,accountStats,accountLevel,accountStatus));
    }

    @Override
    public void updateAccountPlay(String championName, String teamColor, String accountID, String accountName, String matchID, float playStats) throws SQLException {
        handleAccountPlayUpdate(new AccountPlay(championName,teamColor,accountID,accountName,matchID,playStats));
    }

    @Override
    public void updateBlacklist(String accountID, String accountName, String blacklistedID, String blacklistedName) throws SQLException {
        handleBlacklistUpdate(new Blacklist(accountID,accountName,blacklistedID,blacklistedName));
    }

    @Override
    public void updateBotMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, String difficulty, boolean curses) throws SQLException {
        handleBotMatchUpdate(new BotMatch(matchID,matchMap,matchStartTime,matchEndTime,difficulty,curses));
    }

    @Override
    public void updateChampion(String championName, String championType, int championStats) throws SQLException {
        handleChampionUpdate(new Champion(championName,championType,championStats));
    }

    @Override
    public void updateFriendlist(String accountID, String accountName, String friendlistedID, String friendlistedName) throws SQLException {
        handleFriendlistUpdate(new Friendlist(accountID,accountName,friendlistedID,friendlistedName));
    }

    @Override
    public void updateInRank(String accountID, String accountName, String rankNumber, String rankTier) throws SQLException {
        handleInRankUpdate(new InRank(accountID,accountName,rankNumber,rankTier));
    }

    @Override
    public void updateInTeamUse(String accountID, String teamColor, String championName, String itemName, String summonerSpellName, String primaryPath, String secondaryPath) throws SQLException {
        handleInTeamUseUpdate(new InTeamUse(accountID,teamColor,championName,itemName,summonerSpellName,primaryPath,secondaryPath));
    }

    @Override
    public void updateItem(String itemName, String itemDescription) throws SQLException {
        handleItemUpdate(new Item(itemName,itemDescription));
    }

    @Override
    public void updatePVPMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, int rank) throws SQLException {
        handlePVPMatchUpdate(new PVPMatch(matchID,matchMap,matchStartTime,matchEndTime,rank));
    }

    @Override
    public void updateRank(String rankNumber, String rankTier) throws SQLException {
        handleRankUpdate(new Rank(rankNumber,rankTier));
    }

    @Override
    public void updateRole(String roleName, String championName) throws SQLException {
        handleRoleUpdate(new Role(roleName,championName));
    }

    @Override
    public void updateRunes(String runesPrimaryPath, String runesSecondaryPath, String keystone) throws SQLException {
        handleRunesUpdate(new Runes(runesPrimaryPath,runesSecondaryPath,keystone));
    }

    @Override
    public void updateSummonerSpell(String summonerSpellName, String summonerSpellNameDescription) throws SQLException {
        handleSummmonerSpellUpdate(new SummonerSpell(summonerSpellName,summonerSpellNameDescription));
    }

    @Override
    public void updateTeamParticipation(String matchID, String championName, String teamColor, int firstTowerTakeDownInSecond, int firstDragonInSecond, int inTeamStats, String participationWinner) throws SQLException {
        handleTeamParticipationUpdate(new TeamParticipation(matchID,championName,teamColor,firstTowerTakeDownInSecond, firstDragonInSecond,inTeamStats,participationWinner));
    }

    public void handleAccountUpdate(Account account) throws SQLException {
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.setConnection(this.connection);
        accountDatabase.updateAccountDatabaseTuple(account);
        accountDatabase.close();
    }

    private void handleAccountPlayUpdate(AccountPlay accountPlay) throws SQLException {
        AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase();
        accountPlayDatabase.setConnection(this.connection);
        accountPlayDatabase.updateAccountPlayDatabaseTuple(accountPlay);
        accountPlayDatabase.close();
    }

    private void handleBlacklistUpdate(Blacklist blacklist) throws SQLException {
        BlacklistDatabase blacklistDatabase = new BlacklistDatabase();
        blacklistDatabase.setConnection(this.connection);
        blacklistDatabase.updateBlacklistDatabaseTuple(blacklist);
        blacklistDatabase.close();
    }

    private void handleBotMatchUpdate(BotMatch botMatch) throws SQLException {
        BotMatchDatabase botMatchDatabase = new BotMatchDatabase();
        botMatchDatabase.setConnection(this.connection);
        botMatchDatabase.updateBotMatchDatabaseTuple(botMatch);
        botMatchDatabase.close();
    }

    private void handleChampionUpdate(Champion champion) throws SQLException {
        ChampionDatabase championDatabase = new ChampionDatabase();
        championDatabase.setConnection(this.connection);
        championDatabase.updateChampionDatabaseTuple(champion);
        championDatabase.close();
    }

    private void handleFriendlistUpdate(Friendlist friendlist) throws SQLException {
        FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
        friendlistDatabase.setConnection(this.connection);
        friendlistDatabase.updateFriendlistDatabaseTuple(friendlist);
        friendlistDatabase.close();
    }

    private void handleInRankUpdate(InRank inRank) throws SQLException {
        InRankDatabase inRankDatabase = new InRankDatabase();
        inRankDatabase.setConnection(this.connection);
        inRankDatabase.updateInRankDatabaseTuple(inRank);
        inRankDatabase.close();
    }

    private void handleInTeamUseUpdate(InTeamUse inTeamUse) throws SQLException {
        InTeamUseDatabase inTeamUseDatabase = new InTeamUseDatabase();
        inTeamUseDatabase.setConnection(this.connection);
        inTeamUseDatabase.updateInTeamUseDatabaseTuple(inTeamUse);
        inTeamUseDatabase.close();
    }

    private void handleItemUpdate(Item item) throws SQLException {
        ItemDatabase itemDatabase = new ItemDatabase();
        itemDatabase.setConnection(this.connection);
        itemDatabase.updateItemDatabaseTuple(item);
        itemDatabase.close();
    }

    private void handlePVPMatchUpdate(PVPMatch pvpMatch) throws SQLException {
        PVPMatchDatabase pvpMatchDatabase = new PVPMatchDatabase();
        pvpMatchDatabase.setConnection(this.connection);
        pvpMatchDatabase.updatePVPMatchDatabaseTuple(pvpMatch);
        pvpMatchDatabase.close();
    }

    private void handleRankUpdate(Rank rank) throws SQLException {
        RankDatabase rankDatabase = new RankDatabase();
        rankDatabase.setConnection(this.connection);
        rankDatabase.updateRankDatabaseTuple(rank);
        rankDatabase.close();
    }

    private void handleRoleUpdate(Role role) throws SQLException {
        RoleDatabase roleDatabase = new RoleDatabase();
        roleDatabase.setConnection(this.connection);
        roleDatabase.updateRoleDatabaseTuple(role);
        roleDatabase.close();
    }

    private void handleRunesUpdate(Runes runes) throws SQLException {
        RunesDatabase runesDatabase = new RunesDatabase();
        runesDatabase.setConnection(this.connection);
        runesDatabase.updateRunesDatabaseTuple(runes);
        runesDatabase.close();
    }

    private void handleSummmonerSpellUpdate(SummonerSpell summonerSpell) throws SQLException {
        SummonerSpellDatabase summonerSpellDatabase = new SummonerSpellDatabase();
        summonerSpellDatabase.setConnection(this.connection);
        summonerSpellDatabase.updateSummonerSpellDatabaseTuple(summonerSpell);
        summonerSpellDatabase.close();
    }

    //
    private void handleTeamParticipationUpdate(TeamParticipation teamParticipation) throws SQLException {
        TeamParticipationDatabase teamParticipationDatabase = new TeamParticipationDatabase();
        teamParticipationDatabase.setConnection(this.connection);
        teamParticipationDatabase.updateTeamParticipationDatabaseTuple(teamParticipation);

        teamParticipationDatabase.close();

    }

}


