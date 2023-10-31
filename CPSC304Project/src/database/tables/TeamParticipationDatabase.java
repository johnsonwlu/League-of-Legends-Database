package database.tables;

import database.tables.Database;
import model.SummonerSpell;
import model.TeamParticipation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamParticipationDatabase extends Database {
    public TeamParticipationDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public TeamParticipationDatabase() {

    }

    public void createTeamParticipationTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE team_participation (" +
                            "match_id varchar2(50)," +
                            "champion_name varchar2(50)," +
                            "team_color varchar2(50)," +
                            "first_tower_take_down_in_second integer," +
                            "first_dragon_in_second integer," +
                            "in_team_stats integer," +
                            "participation_winner varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertTeamParticipationTuple(TeamParticipation model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO team_participation VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getChampionName());
            ps.setString(3, model.getTeamColor());
            ps.setInt(4, model.getFirstTowerTakeDown());
            ps.setInt(5, model.getFirstDragonInSecond());
            ps.setInt(6, model.getInTeamStats());
            ps.setString(7, model.getParticipationWinner());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateTeamParticipationDatabaseTuple(TeamParticipation model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE team_participation SET match_id = ?, " +
                                                                                                "champion_name = ?, " +
                                                                                                "team_color = ?, " +
                                                                                                "first_tower_take_down_in_second = ?, " +
                                                                                                "first_dragon_in_second = ?, " +
                                                                                                "in_team_stats = ?, " +
                                                                                                "participation_winner = ? " +
                                                                                                "WHERE match_id = ? AND team_color = ? AND champion_name = ? ");

            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getChampionName());
            ps.setString(3, model.getTeamColor());
            ps.setInt(4, model.getFirstTowerTakeDown());
            ps.setInt(5, model.getFirstDragonInSecond());
            ps.setInt(6, model.getInTeamStats());
            ps.setString(7, model.getParticipationWinner());
            ps.setString(8, model.getMatchID());
            ps.setString(9, model.getTeamColor());
            ps.setString(10, model.getChampionName());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteTeamParticipationDatabaseTuple(String matchID, String teamColor, String championName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM team_participation WHERE match_id = ? AND team_color = ? AND champion_name = ? ");
            ps.setString(1, matchID);
            ps.setString(2, teamColor);
            ps.setString(3, championName);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    public TeamParticipation[] getTeamParticipationArrayFromDatabase() {
        ArrayList<TeamParticipation> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM team_participation");

            while(rs.next()) {
                TeamParticipation model = new TeamParticipation(
                        rs.getString("match_id"),
                        rs.getString("champion_name"),
                        rs.getString("team_color"),
                        rs.getInt("first_tower_take_down_in_second"),
                        rs.getInt("first_dragon_in_second"),
                        rs.getInt("in_team_stats"),
                        rs.getString("participation_winner"));
                result.add(model);

            }
            rs.close();
            stmt.close();


        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        TeamParticipation[] teamParticipations = result.toArray(new TeamParticipation[result.size()]);
        return teamParticipations;
    }

    public void dropTeamParticipationTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("team_participation")) {
                    stmt.execute("DROP TABLE team_participation");
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
