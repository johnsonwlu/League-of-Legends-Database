package database.tables;

import database.tables.Database;
import model.Rank;
import model.match.PVPMatch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RankDatabase extends Database {
    public RankDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public RankDatabase() {

    }


    public void createRankTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE rank ( " +
                            "rankNumber varchar2(50)," +
                            "rankTier  varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
                System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertRankTuple(Rank model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rank VALUES (?,?)");
            ps.setString(1, model.getRankNumber());
            ps.setString(2, model.getRankTier());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateRankDatabaseTuple(Rank model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE rank SET rank_number = ?, " +
                                                                                    "rank_tier = ? " +
                                                                                    "WHERE rank_number = ?");

            ps.setString(1, model.getRankNumber());
            ps.setString(2, model.getRankTier());
            ps.setString(3, model.getRankNumber());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteRankDatabaseTuple(String rankNumber) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rank WHERE rank_number = ?");
            ps.setString(1, rankNumber);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public Rank[] getRankArrayFromDatabase() {
        ArrayList<Rank> result = new ArrayList<Rank>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rank");

            while(rs.next()) {
                Rank model = new Rank(rs.getString("rankNumber"),
                        rs.getString("rankTier"));
                result.add(model);
            }

            rs.close();
            stmt.close();
    } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Rank[] ranks = result.toArray(new Rank[result.size()]);
        return ranks;
    }

    public void dropRankTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("rank")) {
                    stmt.execute("DROP TABLE rank");
                    break;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.print(EXCEPTION_TAG + " " +e.getMessage());
        }
    }
}
