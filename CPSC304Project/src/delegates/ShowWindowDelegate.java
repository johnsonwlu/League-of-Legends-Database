package delegates;

import model.*;
import model.match.BotMatch;

import java.sql.Connection;
import java.sql.SQLException;

public interface ShowWindowDelegate {
    Connection getConnection();
    public Account[] showAccount() throws SQLException;
    public AccountPlay[] showAccountPlay() throws SQLException;
    public Blacklist[] showBlacklist() throws SQLException;
    public Champion[] showChampion() throws SQLException;
    public BotMatch[] showBotMatch() throws SQLException;
    public Friendlist[] showFriendlist() throws SQLException;
    public InRank[] showInRank() throws SQLException;
    public InTeamUse[] showInTeamUse() throws SQLException;
    public Item[] showItem() throws SQLException;
    public Rank[] showRank() throws SQLException;
    public Runes[] showRunes() throws SQLException;
    public SummonerSpell[] showSummonerSpell() throws SQLException;
    public TeamParticipation[] showTeamParticipation() throws SQLException;
}
