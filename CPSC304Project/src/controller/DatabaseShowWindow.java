package controller;

import database.tables.*;
import delegates.ShowWindowDelegate;
import model.*;
import model.match.BotMatch;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseShowWindow implements ShowWindowDelegate {
    public Connection connection = null;

    public Connection getConnection() {
        return this.connection;
    }

    public DatabaseShowWindow(Connection connection)
    {
        this.connection = connection;
    }

    public Account[] showAccount() throws SQLException
    {
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.setConnection(this.connection);
        Account[] result = accountDatabase.getAccountArrayFromDatabase();
        return result;
    }

    public AccountPlay[] showAccountPlay() throws SQLException
    {
        AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase();
        accountPlayDatabase.setConnection(this.connection);
        AccountPlay[] result = accountPlayDatabase.getAccountPlayArrayFromDatabase();
        return result;
    }

    public Blacklist[] showBlacklist() throws SQLException
    {
        BlacklistDatabase blacklistDatabase = new BlacklistDatabase();
        blacklistDatabase.setConnection(this.connection);
        Blacklist[] result = blacklistDatabase.getBlacklistArrayFromDatabase();
        return result;
    }

    public BotMatch[] showBotMatch() throws SQLException
    {
        BotMatchDatabase botMatchDatabase = new BotMatchDatabase();
        botMatchDatabase.setConnection(this.connection);
        BotMatch[] result = botMatchDatabase.getBotMatchArrayFromDatabase();

        return result;
    }

    public Champion[] showChampion() throws SQLException
    {
        ChampionDatabase championDatabase = new ChampionDatabase();
        championDatabase.setConnection(this.connection);
        Champion[] result = championDatabase.getChampionArrayFromDatabase();
        championDatabase.close();
        return result;
    }


    public Friendlist[] showFriendlist() throws SQLException
    {
        FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
        friendlistDatabase.setConnection(this.connection);
        Friendlist[] result = friendlistDatabase.getFriendlistArrayFromDatabase();
        friendlistDatabase.close();
        return result;
    }

    public InRank[] showInRank() throws SQLException
    {
        InRankDatabase inRankDatabase = new InRankDatabase();
        inRankDatabase.setConnection(this.connection);
        InRank[] result = inRankDatabase.getInRankArrayFromDatabase();
        inRankDatabase.close();
        return result;
    }

    public InTeamUse[] showInTeamUse() throws SQLException
    {
        InTeamUseDatabase inTeamUseDatabase = new InTeamUseDatabase();
        inTeamUseDatabase.setConnection(this.connection);
        InTeamUse[] result = inTeamUseDatabase.getInTeamUseArrayFromDatabase();
        inTeamUseDatabase.close();
        return result;
    }

    public Item[] showItem() throws SQLException
    {
        ItemDatabase itemDatabase = new ItemDatabase();
        itemDatabase.setConnection(this.connection);
        Item[] result = itemDatabase.getItemArrayFromDatabase();
        itemDatabase.close();
        return result;
    }

    public Rank[] showRank() throws SQLException
    {
        RankDatabase rankDatabase = new RankDatabase();
        rankDatabase.setConnection(this.connection);
        Rank[] result = rankDatabase.getRankArrayFromDatabase();
        rankDatabase.close();
        return result;
    }

    public Runes[] showRunes() throws SQLException
    {
        RunesDatabase runesDatabase = new RunesDatabase();
        runesDatabase.setConnection(this.connection);
        Runes[] result = runesDatabase.getRunesArrayFromDatabase();
        runesDatabase.close();
        return result;
    }

    public SummonerSpell[] showSummonerSpell() throws SQLException
    {
        SummonerSpellDatabase summonerSpellDatabase = new SummonerSpellDatabase();
        summonerSpellDatabase.setConnection(this.connection);
        SummonerSpell[] result = summonerSpellDatabase.getSummonerSpellArrayFromDatabase();
        summonerSpellDatabase.close();
        return result;
    }

    public TeamParticipation[] showTeamParticipation() throws SQLException
    {
        TeamParticipationDatabase teamParticipationDatabase = new TeamParticipationDatabase();
        teamParticipationDatabase.setConnection(this.connection);
        TeamParticipation[] result = teamParticipationDatabase.getTeamParticipationArrayFromDatabase();
        teamParticipationDatabase.close();
        return result;
    }

}
