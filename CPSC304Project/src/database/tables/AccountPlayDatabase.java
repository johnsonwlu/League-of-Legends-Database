package database.tables;

import model.Account;
import model.AccountPlay;
import model.Blacklist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountPlayDatabase extends Database {
    public AccountPlayDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public AccountPlayDatabase() {
        super();
    }

    public void createAccountPlayTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE account_play (" +
                            "champion_name varchar2(50)," +
                            "team_color varchar2(50)," +
                            "account_id varchar2(50)," +
                            "account_name varchar2(50)," +
                            "match_id varchar2(50)," +
                            "play_stats float)");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertAccountPlayDatabaseTuple(AccountPlay model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO account_play VALUES (?,?,?,?,?,?)");
            ps.setString(1, model.getChampionName());
            ps.setString(2, model.getTeamColor());
            ps.setString(3, model.getAccountID());
            ps.setString(4, model.getAccountName());
            ps.setString(5, model.getMatchID());
            ps.setFloat(6, model.getPlayStats());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateAccountPlayDatabaseTuple(AccountPlay model) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("UPDATE account_play SET champion_name = ?, " +
                                                                                        "team_color = ?," +
                                                                                        "account_id = ?," +
                                                                                        "account_name = ?," +
                                                                                        "match_id = ?," +
                                                                                        "play_stats = ? " +
                                                                                        "WHERE account_id = ? " +
                                                                                        "AND account_name = ? " +
                                                                                        "AND champion_name = ? " +
                                                                                        "AND team_color = ? " +
                                                                                        "AND match_id = ? ");
        ps.setString(1, model.getChampionName());
        ps.setString(2, model.getTeamColor());
        ps.setString(3, model.getAccountID());
        ps.setString(4, model.getAccountName());
        ps.setString(5, model.getMatchID());
        ps.setFloat(6, model.getPlayStats());
        ps.setString(7, model.getAccountID());
        ps.setString(8, model.getAccountName());
        ps.setString(9, model.getChampionName());
        ps.setString(10, model.getTeamColor());
        ps.setString(11, model.getMatchID());

        ps.executeUpdate();
        connection.commit();
        ps.close();


    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteAccountPlayDatabaseTuple(String accountID, String accountName, String championName, String teamColor, String matchID) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM account_play WHERE account_id = ? " +
                                                                                            "AND account_name = ? " +
                                                                                            "AND champion_name = ? " +
                                                                                            "AND team_color = ? " +
                                                                                            "AND match_id = ? ");
            ps.setString(1, accountID);
            ps.setString(2, accountName);
            ps.setString(3, championName);
            ps.setString(4, teamColor);
            ps.setString(5, matchID);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }



    public AccountPlay[] getAccountPlayArrayFromDatabase() {
        ArrayList<AccountPlay> result = new ArrayList<AccountPlay>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM account_play");

            while(rs.next()) {
                AccountPlay model = new AccountPlay(rs.getString("champion_name"),
                        rs.getString("team_color"),
                        rs.getString("account_id"),
                        rs.getString("account_name"),
                        rs.getString("match_id"),
                        rs.getFloat("play_stats"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        AccountPlay[] accountPlays = result.toArray(new AccountPlay[result.size()]);
        return accountPlays;

    }

    public void dropAccountPlayTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("account_play")) {
                    stmt.execute("DROP TABLE account_play");
                    break;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
