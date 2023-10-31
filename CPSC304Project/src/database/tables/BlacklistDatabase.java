package database.tables;

import model.Account;
import model.Blacklist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BlacklistDatabase extends Database{
    public BlacklistDatabase() {
        super();
    }


    public void createBlacklistTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE blacklist (" +
                            "account_id varchar2(50)," +
                            "account_name varchar2(50)," +
                            "blacklisted_id varchar2(50)," +
                            "blacklisted_name varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertBlacklistDatabaseTuple(Blacklist model) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO blacklist VALUES (?,?,?,?)");
        System.out.println("line 36 ");

        ps.setString(1, model.getAccountID());
        ps.setString(2, model.getAccountName());
        ps.setString(3, model.getBlacklistedID());
        ps.setString(4, model.getBlacklistedName());

        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateBlacklistDatabaseTuple(Blacklist model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE blacklist SET account_id = ?, " +
                                                                    "account_name = ?, " +
                                                                    "blacklisted_id = ?," +
                                                                    "blacklisted_name = ? " +
                                                                    "WHERE account_id = ? AND account_name = ? ");

            ps.setString(1, model.getAccountID());
            ps.setString(2, model.getAccountName());
            ps.setString(3, model.getBlacklistedID());
            ps.setString(4, model.getBlacklistedName());
            ps.setString(5, model.getAccountID());
            ps.setString(6, model.getAccountName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteBlacklistDatabaseTuple(String accountID, String accountName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM blacklist WHERE account_id = ? AND account_name = ? ");
            ps.setString(1, accountID);
            ps.setString(2, accountName);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public Blacklist[] getBlacklistArrayFromDatabase() {
        ArrayList<Blacklist> result = new ArrayList<Blacklist>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM blacklist");

            while(rs.next()) {
                Blacklist model = new Blacklist(rs.getString("account_id"),
                        rs.getString("account_name"),
                        rs.getString("blacklisted_id"),
                        rs.getString("blacklisted_name"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Blacklist[] blacklists = result.toArray(new Blacklist[result.size()]);
        return blacklists;

    }

    public void dropBlacklistTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("blacklist")) {
                    stmt.execute("DROP TABLE blacklist");
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
