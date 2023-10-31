package database.tables;

import model.Item;
import model.match.PVPMatch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PVPMatchDatabase extends Database {
    public PVPMatchDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public PVPMatchDatabase() {

    }

    public void createPVPMatchTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE pvp_match (" +
                            "match_id varchar2(50)," +
                            "match_map varchar2(50)," +
                            "match_start_time varchar2(50)," +
                            "match_end_time varchar2(50)," +
                            "match_rank integer)");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertPVPMatchTuple(PVPMatch model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pvp_match VALUES (?,?,?,?,?)");
            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getMatchMap());
            ps.setString(3, model.getMatchStartTime());
            ps.setString(4, model.getMatchEndTime());
            ps.setInt(5, model.getRank());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updatePVPMatchDatabaseTuple(PVPMatch model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE pvp_match SET match_id = ?, " +
                                                                                        "match_map = ?, " +
                                                                                        "match_start_time = ?," +
                                                                                        "match_end_time = ?," +
                                                                                        "match_rank = ?" +
                                                                                        "WHERE match_id = ?");

            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getMatchMap());
            ps.setString(3, model.getMatchStartTime());
            ps.setString(4, model.getMatchEndTime());
            ps.setInt(5, model.getRank());
            ps.setString(6, model.getMatchID());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deletePVPMatchDatabaseTuple(String matchID) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM pvp_match WHERE match_id = ?");
            ps.setString(1, matchID);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public PVPMatch[] getPVPMatchArrayFromDatabase() {
        ArrayList<PVPMatch> result = new ArrayList<PVPMatch>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pvp_match");

            while(rs.next()) {
                PVPMatch model = new PVPMatch(rs.getString("match_id"),
                        rs.getString("match_map"),
                        rs.getString("match_start_time"),
                        rs.getString("match_end_time"),
                        rs.getInt("match_rank"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        PVPMatch[] pvpMatches = result.toArray(new PVPMatch[result.size()]);
        return pvpMatches;
    }

    public void dropPVPMatchTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("pvp_match")) {
                    stmt.execute("DROP TABLE pvp_match");
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
