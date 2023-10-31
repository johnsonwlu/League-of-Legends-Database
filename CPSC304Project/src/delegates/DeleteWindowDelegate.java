package delegates;
import java.sql.Connection;
import java.sql.SQLException;

public interface DeleteWindowDelegate {
    Connection getConnection();
    void deleteAccount(String accountID, String accountName) throws SQLException;
    void deleteAccountPlay(String championName, String teamColor, String accountID, String accountName, String matchID, float playStats) throws SQLException;
    void deleteBlacklist(String accountID, String accountName) throws SQLException;
    void deleteBotMatch(String matchID) throws SQLException;
    void deleteChampion(String championName) throws SQLException;
    void deleteFriendlist(String accountID, String accountName) throws SQLException;
    void deleteInRank(String accountIDr) throws SQLException;
    void deleteInTeamUse(String matchID) throws SQLException;
    void deleteItem(String itemName) throws SQLException;
    void deletePVPMatch(String matchID) throws SQLException;
    void deleteRank(String rankNumber) throws SQLException;
    void deleteRole(String roleName) throws SQLException;
    void deleteRunes(String runesPrimaryPath, String runesSecondaryPath) throws SQLException;
    void deleteSummonerSpell(String summonerSpellName) throws SQLException;
    void deleteTeamParticipation(String matchID, String championName, String teamColor) throws SQLException;
}
