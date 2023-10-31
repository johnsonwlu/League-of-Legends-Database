package database.tables;

import model.Friendlist;
import model.InRank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InRankDatabase extends Database {
    public InRankDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public InRankDatabase() {

    }

    public void createInRankTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE in_rank (" +
                            "account_id varchar2(50) PRIMARY KEY," +
                            "account_name varchar2(50)," +
                            "rank_number varchar2(50)," +
                            "rank_tier varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertInRankTuple(InRank model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO in_rank VALUES (?,?,?,?)");
            ps.setString(1, model.getAccountID());
            ps.setString(2, model.getAccountName());
            ps.setString(3, model.getRankNumber());
            ps.setString(4, model.getRankTier());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateInRankDatabaseTuple(InRank model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE in_rank SET account_id = ?, " +
                                                                                        "account_name = ?, " +
                                                                                        "rank_number = ?," +
                                                                                        "rank_tier = ? " +
                                                                                        "WHERE account_id = ?");
            ps.setString(1, model.getAccountID());
            ps.setString(2, model.getAccountName());
            ps.setString(3, model.getRankNumber());
            ps.setString(4, model.getRankTier());
            ps.setString(5, model.getAccountID());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteInRankDatabaseTuple(String accountID) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM in_rank WHERE account_id = ?");
            ps.setString(1, accountID);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    public InRank[] getInRankArrayFromDatabase() {
        ArrayList<InRank> result = new ArrayList<InRank>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM in_rank");

            while(rs.next()) {
                InRank model = new InRank(rs.getString("account_id"),
                        rs.getString("account_name"),
                        rs.getString("rank_number"),
                        rs.getString("rank_tier"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        InRank[] inRanks = result.toArray(new InRank[result.size()]);
        return inRanks;
    }

    public void dropInRankTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("in_rank")) {
                    stmt.execute("DROP TABLE in_rank");
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
