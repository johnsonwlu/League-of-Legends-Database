package database.tables;

import model.Blacklist;
import model.Friendlist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendlistDatabase extends Database {
    public FriendlistDatabase(){
        super();
    }

    public void createFriendlistTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE friendlist (" +
                            "account_id varchar2(50)," +
                            "account_name varchar2(50)," +
                            "friendlisted_id varchar2(50)," +
                            "friendlisted_name varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertFriendlistDatabaseTuple(Friendlist model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO friendlist VALUES (?,?,?,?)");
            ps.setString(1, model.getAccountID());
            ps.setString(2, model.getAccountName());
            ps.setString(3, model.getFriendlistedID());
            ps.setString(4, model.getFriendlistedName());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateFriendlistDatabaseTuple(Friendlist model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE friendlist SET account_id = ?, " +
                                                                                        "account_name = ?, " +
                                                                                        "friendlisted_id = ?," +
                                                                                        "friendlisted_name = ? " +
                                                                                        "WHERE account_id = ? " +
                                                                                        "AND account_name = ? ");
            ps.setString(1, model.getAccountID());
            ps.setString(2, model.getAccountName());
            ps.setString(3, model.getFriendlistedID());
            ps.setString(4, model.getFriendlistedName());
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
    public void deleteFriendlistDatabaseTuple(String accountID, String accountName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM friendlist WHERE account_id = ? AND account_name = ? ");
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


    public Friendlist[] getFriendlistArrayFromDatabase() {
        ArrayList<Friendlist> result = new ArrayList<Friendlist>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM friendlist");

            while(rs.next()) {
                Friendlist model = new Friendlist(rs.getString("account_id"),
                        rs.getString("account_name"),
                        rs.getString("friendlisted_id"),
                        rs.getString("friendlisted_name"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Friendlist[] friendlists = result.toArray(new Friendlist[result.size()]);
        return friendlists;

    }

    public void dropFriendlistTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("friendlist")) {
                    stmt.execute("DROP TABLE friendlist");
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
