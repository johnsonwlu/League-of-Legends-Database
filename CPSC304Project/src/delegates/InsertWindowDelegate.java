package delegates;

import model.*;
import model.match.BotMatch;
import model.match.PVPMatch;

import java.sql.Connection;
import java.sql.SQLException;

public interface InsertWindowDelegate {
    Connection getConnection();
    void insertAccount(String accountID, String accountName, float accountStats, int accountLevel, String accountStatus) throws SQLException;
    void insertAccountPlay(String championName, String teamColor, String accountID, String accountName, String matchID, float playStats) throws SQLException;
    void insertBlacklist(String accountID, String accountName, String blacklistedID, String blacklistedName) throws SQLException;
    void insertBotMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, String difficulty, boolean curses) throws SQLException;
    void insertChampion(String championName, String championType, int championStats) throws SQLException;
    void insertFriendlist(String accountID, String accountName, String friendlistedID, String friendlistedName) throws SQLException;
    void insertInRank(String accountID, String accountName, String rankNumber, String rankTier) throws SQLException;
    void insertInTeamUse(String accountID, String teamColor, String championName, String itemName, String summonerSpellName, String primaryPath, String secondaryPath) throws SQLException;
    void insertItem(String itemName, String itemDescription) throws SQLException;
    void insertPVPMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, int rank) throws SQLException;
    void insertRank(String rankNumber, String rankTier) throws SQLException;
    void insertRole(String roleName, String championName) throws SQLException;
    void insertRunes(String runesPrimaryPath, String runesSecondaryPath, String keystone) throws SQLException;
    void insertSummonerSpell(String summonerSpellName, String summonerSpellNameDescription) throws SQLException;
    void insertTeamParticipation(String matchID, String championName, String teamColor, int firstTowerTakeDownInSecond,
                                 int firstDragonInSecond, int inTeamStats, String participationWinner) throws SQLException;

}
