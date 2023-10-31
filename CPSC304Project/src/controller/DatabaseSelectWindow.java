package controller;

import database.Query;
import delegates.SelectWindowDelegate;

import java.sql.Connection;

public class DatabaseSelectWindow implements SelectWindowDelegate {
    private Query query;
    private Connection connection = null;
    public DatabaseSelectWindow(Connection connection)
    {
        this.connection = connection;
        this.query = new Query();
        query.setConnection(connection);
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public String[] handleFirstQuery() {
        return query.getNameOfChampionWhoUsesTwoHundredsItems();
    }

    @Override
    public String[] handleSecondQuery() {
        return query.getListOfMatchesStartingAtNineAM();
    }

    @Override
    public String[] handleThirdQuery() {
        return query.getAccountAtLeastTenFriendsAndFiveBlacklists();
    }
    @Override
    public String[] handleFourthQuery() {
        return query.getListOfAccountsOnlineStatus();
    }
    @Override
    public String[] handleFifthQuery() {
        return query.getAccountParticipatingInRedTeamTenTimes();
    }
    @Override
    public String[] handleSixthQuery() {
        return query.getAListOfLevelTwentyAccountWonTwentyHardMatchesAtThreePMAndOnlyUseTenItemsAndTenSummonerSpellsAndThreeRunes();
    }
    @Override
    public String[] handleSeventhQuery() {
        return query.getAListOfTheLvTenAccountsHavingOneHundredFriendsAndZeroBlacklists();
    }
    @Override
    public String[] handleEighthQuery() {
        return query.getAListOfAccountsHavingNeverParticipatedInAnyMatch();
    }
    @Override
    public String[] handleNinthQuery() {
        return query.getNameOfTheChampionWhoOnlyParticipatesInTheRedTeam();
    }
    @Override
    public String[] handleTenthQuery() {
        return query.getItemThatHasNeverBeenUsedByAnyChampionOrAnAccount();
    }
    @Override
    public String[] handleEleventhQuery() {
        return query.getMatchesThatUseTheCurses();
    }
    @Override
    public String[] handleTwelfthQuery() {
        return query.getAllAccountsThatAreGoldOrAboveInRank();
    }

}