package database.tables;

import model.InRank;
import model.InTeamUse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InTeamUseDatabase extends Database {
    public InTeamUseDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public InTeamUseDatabase() {

    }

    public void createInTeamUseTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE in_team_use (" +
                            "match_id varchar2(50)," +
                            "team_color varchar2(50)," +
                            "champion_name varchar2(50)," +
                            "item_name varchar2(50)," +
                            "summoner_spell_name varchar2(50)," +
                            "runes_primary_path varchar2(50)," +
                            "runes_secondary_path varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertInTeamUseTuple(InTeamUse model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO in_team_use VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getTeamColor());
            ps.setString(3, model.getChampionName());
            ps.setString(4, model.getItemName());
            ps.setString(5, model.getSummonerSpellName());
            ps.setString(6, model.getPrimaryPath());
            ps.setString(7, model.getSecondaryPath());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateInTeamUseDatabaseTuple(InTeamUse model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE in_team_use SET match_id = ?, " +
                                                                                            "team_color = ?, " +
                                                                                            "champion_name = ?," +
                                                                                            "item_name = ?," +
                                                                                            "summoner_spell_name = ?," +
                                                                                            "runes_primary_path = ?," +
                                                                                            "runes_secondary_path = ?" +
                                                                                            "WHERE match_id = ?");


            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getTeamColor());
            ps.setString(3, model.getChampionName());
            ps.setString(4, model.getItemName());
            ps.setString(5, model.getSummonerSpellName());
            ps.setString(6, model.getPrimaryPath());
            ps.setString(7, model.getSecondaryPath());
            ps.setString(8, model.getMatchID());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteInTeamUseDatabaseTuple(String matchID) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM in_team_use WHERE match_id = ?");
            ps.setString(1, matchID);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public InTeamUse[] getInTeamUseArrayFromDatabase() {
        ArrayList<InTeamUse> result = new ArrayList<InTeamUse>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM in_team_use");

            while(rs.next()) {
                InTeamUse model = new InTeamUse(rs.getString("match_id"),
                        rs.getString("team_color"),
                        rs.getString("champion_name"),
                        rs.getString("item_name"),
                        rs.getString("summoner_spell_name"),
                        rs.getString("runes_primary_path"),
                        rs.getString("runes_secondary_path"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        InTeamUse[] items = result.toArray(new InTeamUse[result.size()]);
        return items;
    }

    public void dropInTeamUseTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("in_team_use")) {
                    stmt.execute("DROP TABLE in_team_use");
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
