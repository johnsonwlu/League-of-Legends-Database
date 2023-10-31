package delegates;

import java.sql.SQLException;
import java.sql.Connection;

public interface UpdateWindowDelegate {
    Connection getConnection();
    void updateAccount(String accountID, String accountName, float accountStats, int accountLevel, String accountStatus) throws SQLException;
    void updateAccountPlay(String championName, String teamColor, String accountID, String accountName, String matchID, float playStats) throws SQLException;
    void updateBlacklist(String accountID, String accountName, String blacklistedID, String blacklistedName) throws SQLException;
    void updateBotMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, String difficulty, boolean curses) throws SQLException;
    void updateChampion(String championName, String championType, int championStats) throws SQLException;
    void updateFriendlist(String accountID, String accountName, String friendlistedID, String friendlistedName) throws SQLException;
    void updateInRank(String accountID, String accountName, String rankNumber, String rankTier) throws SQLException;
    void updateInTeamUse(String accountID, String teamColor, String championName, String itemName, String summonerSpellName, String primaryPath, String secondaryPath) throws SQLException;
    void updateItem(String itemName, String itemDescription) throws SQLException;
    void updatePVPMatch(String matchID, String matchMap, String matchStartTime, String matchEndTime, int rank) throws SQLException;
    void updateRank(String rankNumber, String rankTier) throws SQLException;
    void updateRole(String roleName, String championName) throws SQLException;
    void updateRunes(String runesPrimaryPath, String runesSecondaryPath, String keystone) throws SQLException;
    void updateSummonerSpell(String summonerSpellName, String summonerSpellNameDescription) throws SQLException;
    void updateTeamParticipation(String matchID, String championName, String teamColor, int firstTowerTakeDownInSecond,
                                 int firstDragonInSecond, int inTeamStats, String participationWinner) throws SQLException;
}
