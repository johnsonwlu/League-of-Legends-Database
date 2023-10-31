package controller;

import database.tables.*;
import delegates.DeleteWindowDelegate;
import model.*;
import model.match.BotMatch;
import model.match.PVPMatch;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseDeleteWindow implements DeleteWindowDelegate {
    private Connection connection = null;

    public Connection getConnection() {
        return this.connection;
    }
    public DatabaseDeleteWindow(Connection connection) {this.connection = connection; }



    @Override
    public void deleteAccount(String accountID, String accountName) throws SQLException {
    handleAccountDelete(accountID,accountName);
    }

    @Override
    public void deleteAccountPlay(String championName, String teamColor, String accountID, String accountName, String matchID, float playStats) throws SQLException {
    handleAccountPlayDelete(championName,teamColor,accountID,accountName,matchID);
    }

    @Override
    public void deleteBlacklist(String accountID, String accountName) throws SQLException {
    handleBlacklistDelete(accountID,accountName);
    }

    @Override
    public void deleteBotMatch(String matchID) throws SQLException {
    handleBotMatchDelete(matchID);
    }

    @Override
    public void deleteChampion(String championName) throws SQLException {
    handleChampionDelete(championName);
    }

    @Override
    public void deleteFriendlist(String accountID, String accountName) throws SQLException {
    handleFriendlistDelete(accountID,accountName);
    }

    @Override
    public void deleteInRank(String accountID) throws SQLException {
    handleInRankDelete(accountID);
    }

    @Override
    public void deleteInTeamUse(String matchID) throws SQLException {
    handleInTeamUseDelete(matchID);
    }

    @Override
    public void deleteItem(String itemName) throws SQLException {
    handleItemDelete(itemName);
    }

    @Override
    public void deletePVPMatch(String matchID) throws SQLException {
    handlePVPMatchDelete(matchID);
    }

    @Override
    public void deleteRank(String rankNumber) throws SQLException {
    handleRankDelete(rankNumber);
    }

    @Override
    public void deleteRole(String roleName) throws SQLException {
    handleRoleDelete(roleName);
    }

    @Override
    public void deleteRunes(String runesPrimaryPath, String runesSecondaryPath) throws SQLException {
    handleRunesDelete(runesPrimaryPath,runesSecondaryPath);
    }

    @Override
    public void deleteSummonerSpell(String summonerSpellName) throws SQLException {
    handleSummmonerSpellDelete(summonerSpellName);
    }

    @Override
    public void deleteTeamParticipation(String matchID, String championName, String teamColor) throws SQLException {
    handleTeamParticipationDelete(matchID,teamColor,championName);
    }

    public void handleAccountDelete(String accountID, String accountName) throws SQLException {
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.setConnection(this.connection);
        accountDatabase.deleteAccountDatabaseTuple(accountID,accountName);
        accountDatabase.close();
    }

    private void handleAccountPlayDelete(String accountID, String accountName, String championName, String teamColor, String matchID) throws SQLException {
        AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase();
        accountPlayDatabase.setConnection(this.connection);
        accountPlayDatabase.deleteAccountPlayDatabaseTuple(accountID,accountName,championName,teamColor,matchID);
        accountPlayDatabase.close();
    }

    private void handleBlacklistDelete(String accountID, String accountName) throws SQLException {
        BlacklistDatabase blacklistDatabase = new BlacklistDatabase();
        blacklistDatabase.setConnection(this.connection);
        blacklistDatabase.deleteBlacklistDatabaseTuple(accountID,accountName);
        blacklistDatabase.close();
    }

    private void handleBotMatchDelete(String matchID) throws SQLException {
        BotMatchDatabase botMatchDatabase = new BotMatchDatabase();
        botMatchDatabase.setConnection(this.connection);
        botMatchDatabase.deleteBotMatchDatabaseTuple(matchID);
        botMatchDatabase.close();
    }

    private void handleChampionDelete(String championName) throws SQLException {
        ChampionDatabase championDatabase = new ChampionDatabase();
        championDatabase.setConnection(this.connection);
        championDatabase.deleteChampionDatabaseTuple(championName);
        championDatabase.close();
    }

    private void handleFriendlistDelete(String accountID, String accountName) throws SQLException {
        FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
        friendlistDatabase.setConnection(this.connection);
        friendlistDatabase.deleteFriendlistDatabaseTuple(accountID,accountName);
        friendlistDatabase.close();
    }

    private void handleInRankDelete(String accountID) throws SQLException {
        InRankDatabase inRankDatabase = new InRankDatabase();
        inRankDatabase.setConnection(this.connection);
        inRankDatabase.deleteInRankDatabaseTuple(accountID);
        inRankDatabase.close();
    }

    private void handleInTeamUseDelete(String matchID) throws SQLException {
        InTeamUseDatabase inTeamUseDatabase = new InTeamUseDatabase();
        inTeamUseDatabase.setConnection(this.connection);
        inTeamUseDatabase.deleteInTeamUseDatabaseTuple(matchID);
        inTeamUseDatabase.close();
    }

    private void handleItemDelete(String itemName) throws SQLException {
        ItemDatabase itemDatabase = new ItemDatabase();
        itemDatabase.setConnection(this.connection);
        itemDatabase.deleteItemDatabaseTuple(itemName);
        itemDatabase.close();
    }

    private void handlePVPMatchDelete(String matchID) throws SQLException {
        PVPMatchDatabase pvpMatchDatabase = new PVPMatchDatabase();
        pvpMatchDatabase.setConnection(this.connection);
        pvpMatchDatabase.deletePVPMatchDatabaseTuple(matchID);
        pvpMatchDatabase.close();
    }

    private void handleRankDelete(String rankNumber) throws SQLException {
        RankDatabase rankDatabase = new RankDatabase();
        rankDatabase.setConnection(this.connection);
        rankDatabase.deleteRankDatabaseTuple(rankNumber);
        rankDatabase.close();
    }

    private void handleRoleDelete(String roleName) throws SQLException {
        RoleDatabase roleDatabase = new RoleDatabase();
        roleDatabase.setConnection(this.connection);
        roleDatabase.deleteRoleDatabaseTuple(roleName);
        roleDatabase.close();
    }

    private void handleRunesDelete(String runesPrimaryPath, String runesSecondaryPath) throws SQLException {
        RunesDatabase runesDatabase = new RunesDatabase();
        runesDatabase.setConnection(this.connection);
        runesDatabase.deleteRunesDatabaseTuple(runesPrimaryPath,runesSecondaryPath);
        runesDatabase.close();
    }

    private void handleSummmonerSpellDelete(String summonerSpellName) throws SQLException {
        SummonerSpellDatabase summonerSpellDatabase = new SummonerSpellDatabase();
        summonerSpellDatabase.setConnection(this.connection);
        summonerSpellDatabase.deleteSummonerSpellDatabaseTuple(summonerSpellName);
        summonerSpellDatabase.close();
    }

    //
    private void handleTeamParticipationDelete(String matchID, String teamColor, String championName) throws SQLException {
        TeamParticipationDatabase teamParticipationDatabase = new TeamParticipationDatabase();
        teamParticipationDatabase.setConnection(this.connection);
        teamParticipationDatabase.deleteTeamParticipationDatabaseTuple(matchID,teamColor,championName);

        teamParticipationDatabase.close();

    }
}