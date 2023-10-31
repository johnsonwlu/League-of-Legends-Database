package database;

import database.tables.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Query extends Database {

    //getNameOfChampionWhoUsesTwoHundredsItems
    public static final String FIRST_QUERY = "SELECT in_team_use.champion_name " +
                                             "FROM in_team_use " +
                                             "GROUP BY in_team_use.champion_name " +
                                             "HAVING COUNT(*) = 2";

    //getListOfMatchesStartingAtNineAM
    public static final String SECOND_QUERY = "SELECT UNIQUE bot_match.match_id " +
                                              "FROM bot_match " +
                                              "WHERE bot_match.bot_match_start_time = '9:00am' " +
                                              "UNION " +
                                              "SELECT UNIQUE pvp_match.match_id " +
                                              "FROM pvp_match " +
                                              "WHERE pvp_match.match_start_time = '9:00am'";

    // getAccountAtLeastTenFriendsAndFiveBlacklists
    public static final String THIRD_QUERY = "SELECT account_id, account_name " +
                                             "FROM account account " +
                                             "WHERE account.account_id IN (SELECT blacklist.account_id " +
                                             "FROM blacklist blacklist " +
                                             "GROUP BY blacklist.account_id " +
                                             "HAVING COUNT(*) >= 10) " +
                                             "INTERSECT " +
                                             "SELECT account_id, account_name " +
                                             "FROM account account " +
                                             "WHERE account.account_id IN (SELECT friendlist.account_id " +
                                             "FROM friendlist friendlist " +
                                             "GROUP BY friendlist.account_id " +
                                             "HAVING COUNT(*) >= 5) ";

    // getListOfAccountsOnlineStatus
    public static final String FOURTH_QUERY = "SELECT account.account_id, account.account_name " +
                                              "FROM account " +
                                              "WHERE account.account_status = 'Online'";

    // Find the account that participates in red team 10 times
    public static final String FIFTH_QUERY = "SELECT account_play.account_id, account_play.account_name " +
                                             "FROM account_play account_play, account account, team_participation team_participation " +
                                             "WHERE ((account_play.account_id = account.account_id AND account_play.account_name = account.account_name) " +
                                             "AND (account_play.champion_name = team_participation.champion_name " +
                                             "AND account_play.team_color = team_participation.team_color " +
                                             "AND account_play.match_id = team_participation.match_id) " +
                                             "AND (team_participation.team_color = 'Red')) " +
                                             "GROUP BY account_play.account_id, account_play.account_name " +
                                             "HAVING COUNT(*) = 10 ";


    // ORA-00913: too many values
    // Find a list of the lv-20 accounts that won 20 hard matches at 3:00pm and only use 10 items and 10 summoner spells and 3 Runes.
    public static final String SIXTH_QUERY = "SELECT account.account_id, account.account_name " +
                                             "FROM account account, account_play, team_participation, bot_match, in_team_use, item, summoner_spell, runes " +
                                             "WHERE (account.account_name = account_play.account_name AND account.account_id = account_play.account_id) " +
                                             "AND (team_participation.champion_name = account_play.champion_name " +
                                             "AND team_participation.team_color = account_play.team_color AND account_play.match_id = team_participation.match_id)" +
                                             "AND (team_participation.match_id = bot_match.match_id) AND (team_participation.champion_name = in_team_use.champion_name " +
                                             "AND team_participation.team_color = in_team_use.team_color AND team_participation.match_id = in_team_use.match_id) " +
                                             "AND (in_team_use.item_name = item.item_name " +
                                             "AND in_team_use.summoner_spell_name = summoner_spell.summoner_spell_name AND in_team_use.runes_primary_path = runes.runes_primary_path " +
                                             "AND in_team_use.runes_secondary_path = runes.runes_secondary_path)" +
                                             "AND account.account_level = 20 " +
                                             "AND bot_match.bot_difficulty = 'Hard'  AND bot_match.bot_match_start_time = '3:00pm'" +
                                             "AND account.account_id IN (SELECT * FROM in_team_use in_team_use2, summoner_spell summoner_spell2 " +
                                                                        "WHERE in_team_use2.summoner_spell_name = summoner_spell2.summoner_spell_name " +
                                                                        "AND account.account_id IN" +
                                                                        "(SELECT * FROM in_team_use in_team_use3, item item2 " +
                                                                        "WHERE in_team_use3.item_name = item2.item_name " +
                                                                        "AND account.account_id IN" +
                                                                        "(SELECT * FROM in_team_use in_team_use4, runes runes2 " +
                                                                        "WHERE in_team_use4.runes_primary_path = runes2.runes_primary_path " +
                                                                        "AND in_team_use4.runes_secondary_path = runes2.runes_secondary_path " +
                                                                        "GROUP BY account.account_name, account.account_id " +
                                                                        "HAVING COUNT(account.account_id) >= 20 AND " +
                                                                        "COUNT(summoner_spell2.summoner_spell) >= 10 AND COUNT(item2.item_name) >= 10 AND COUNT(runes2.runes_primary_path) >= 3)))";


    // Find a list of the lv-10 accounts that has 100 friends and 0 blacklists.
    public static final String SEVENTH_QUERY =  "SELECT account_id, account_name " +
                                                "FROM account account1 " +
                                                "WHERE account1.account_level = 10 AND " +
                                                "account1.account_id IN (SELECT blacklist2.account_id " +
                                                "FROM blacklist blacklist2 " +
                                                "GROUP BY blacklist2.account_id " +
                                                "HAVING COUNT(*) = 10) " +
                                                "INTERSECT " +
                                                "SELECT account_id, account_name " +
                                                "FROM account account1 " +
                                                "WHERE account1.account_level = 10 AND " +
                                                "account1.account_id IN (SELECT friendlist2.account_id " +
                                                "FROM friendlist friendlist2 " +
                                                "GROUP BY friendlist2.account_id " +
                                                "HAVING COUNT(*) = 100)";


    // Find a list of accounts that have never participated in any match.
    public static final String EIGHTH_QUERY = "(SELECT account1.account_id, account1.account_name " +
                                              "FROM account account1) " +
                                              "MINUS " +
                                              "(SELECT account2.account_id, account2.account_name " +
                                              "FROM account account2, account_play account_play " +
                                              "WHERE account2.account_name = account_play.account_name AND account2.account_id = account_play.account_id)";

    // Find the name of the champion who only participates in the red team.
    public static final String NINTH_QUERY = "SELECT team_participation.champion_name " +
                                             "FROM team_participation " +
                                             "WHERE team_participation.team_color = 'Red' AND " +
                                             "NOT EXISTS " +
                                             "(SELECT team_participation.champion_name " +
                                             "FROM team_participation " +
                                             "WHERE team_participation.team_color = 'Blue')";

    // Find the item that has never been used by any champion or an account.
    public static final String TENTH_QUERY = "(SELECT item.item_name " +
                                             "FROM item) " +
                                             "MINUS " +
                                             "(SELECT in_team_use.item_name " +
                                             "FROM in_team_use)";

    // Find the matches that use the curses.
    public static final String ELEVENTH_QUERY = "(SELECT bot_match1.match_id " +
                                                "FROM bot_match bot_match1) " +
                                                "MINUS " +
                                                "(SELECT bot_match2.match_id " +
                                                "FROM bot_match bot_match2 " +
                                                "WHERE bot_match2.bot_curses = 'false')";
    // Find all accounts that are gold or above in rank.
    public static final String TWELFTH_QUERY =  "SELECT account_id, account_name " +
                                                "FROM account A " +
                                                "WHERE NOT EXISTS " +
                                                "((SELECT rankTier " +
                                                "FROM rank)" +
                                                "MINUS " +
                                                "(SELECT IR.rank_tier " +
                                                "FROM in_rank IR " +
                                                "Where IR.account_name = A.account_name AND IR.account_id = A.account_name " +
                                                "AND IR.rank_Tier = 'Silver' or IR.rank_tier = 'Bronze' or IR.rank_tier = 'Iron'))";

    // First Query
    public String[] getNameOfChampionWhoUsesTwoHundredsItems() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(FIRST_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("champion_name");
                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] names = result.toArray(new String[result.size()]);
        return names;
    }

    // Second Query
    public String[] getListOfMatchesStartingAtNineAM() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SECOND_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("match_id");
                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    // Third Query
    public String[] getAccountAtLeastTenFriendsAndFiveBlacklists() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(THIRD_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    //Fourth Query
    public String[] getListOfAccountsOnlineStatus() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(FOURTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    //Fifth Query
    //Find the account that participates in red team 10 times
    public String[] getAccountParticipatingInRedTeamTenTimes() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(FIFTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    //Sixth Query
    //Find a list of the lv-20 accounts that won 20 hard matches at 3:00pm and only use 10 items and 10 summoner spells and 3 Runes.
    public String[] getAListOfLevelTwentyAccountWonTwentyHardMatchesAtThreePMAndOnlyUseTenItemsAndTenSummonerSpellsAndThreeRunes() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SIXTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    // Seventh Query
    // Find a list of the lv-10 accounts that has 100 friends and 0 blacklists.
    public String[] getAListOfTheLvTenAccountsHavingOneHundredFriendsAndZeroBlacklists() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SEVENTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    // Eighth Query
    // Find a list of accounts that have never participated in any match.
    public String[] getAListOfAccountsHavingNeverParticipatedInAnyMatch() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(EIGHTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    // Ninth Query
    // Find the name of the champion who only participates in the red team.
    public String[] getNameOfTheChampionWhoOnlyParticipatesInTheRedTeam() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(NINTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    // Tenth Query
    // Find the item that has never been used by any champion or an account.
    public String[] getItemThatHasNeverBeenUsedByAnyChampionOrAnAccount() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(TENTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("item_name");
           //     String column2 = rs.getString("account_name");

                System.out.println(column1);

                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;

    }

    // Eleventh Query
    // Find the matches that use the curses.
    public String[] getMatchesThatUseTheCurses() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(ELEVENTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("match_id");
               // String column2 = rs.getString("account_name");

                System.out.println(column1);
                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }

    // Twelfth Query
    // Find all accounts that are gold or above in rank.
    public String[] getAllAccountsThatAreGoldOrAboveInRank() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(TWELFTH_QUERY);
            while(rs.next()) {
                String column1 = rs.getString("account_id");
                String column2 = rs.getString("account_name");

                System.out.println(column1 + " " + column2);
                result.add(column1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
        String[] matchIDs = result.toArray(new String[result.size()]);
        return matchIDs;
    }
}
