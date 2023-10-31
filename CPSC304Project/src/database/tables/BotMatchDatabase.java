package database.tables;

import model.Blacklist;
import model.match.BotMatch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BotMatchDatabase extends Database {
    public BotMatchDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public BotMatchDatabase() {

    }

    public void createBotMatchTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE bot_match (" +
                            "match_id varchar2(50)," +
                            "bot_match_map varchar2(50)," +
                            "bot_match_start_time varchar2(50)," +
                            "bot_match_end_time varchar2(50)," +
                            "bot_difficulty varchar2(50)," +
                            "bot_curses varchar2(10))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertBotMatchDatabaseTuple(BotMatch model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO bot_match VALUES (?,?,?,?,?,?)");
            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getMatchMap());
            ps.setString(3, model.getMatchStartTime());
            ps.setString(4, model.getMatchEndTime());
            ps.setString(5, model.getDifficulty());
            if (model.isCurses()) {
                ps.setString(6, "true");
            } else {
                ps.setString(6, "false");
            }
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateBotMatchDatabaseTuple(BotMatch model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE bot_match SET match_id = ?, " +
                                                                                        "bot_match_map = ?, " +
                                                                                        "bot_match_start_time = ?," +
                                                                                        "bot_match_end_time = ?," +
                                                                                        "bot_difficulty = ?," +
                                                                                        "bot_curses = ? " +
                                                                                        "WHERE match_id = ?");
            ps.setString(1, model.getMatchID());
            ps.setString(2, model.getMatchMap());
            ps.setString(3, model.getMatchStartTime());
            ps.setString(4, model.getMatchEndTime());
            ps.setString(5, model.getDifficulty());
            if (model.isCurses()) {
                ps.setString(6, "true");
            } else {
                ps.setString(6, "false");
            }
            ps.setString(7, model.getMatchID());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteBotMatchDatabaseTuple(String matchID) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM bot_match WHERE match_id = ?");
            ps.setString(1, matchID);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public BotMatch[] getBotMatchArrayFromDatabase() {
        ArrayList<BotMatch> result = new ArrayList<BotMatch>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bot_match");

            while(rs.next()) {
                boolean tempIsCurses = false;
                if (rs.getString("bot_curses").equals("true")) {
                    tempIsCurses = true;
                }

                BotMatch model = new BotMatch(rs.getString("match_id"),
                        rs.getString("bot_match_map"),
                        rs.getString("bot_match_start_time"),
                        rs.getString("bot_match_end_time"),
                        rs.getString("bot_difficulty"),
                        tempIsCurses);
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        BotMatch[] botMatches = result.toArray(new BotMatch[result.size()]);
        return botMatches;

    }

    public void dropBotMatchTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("bot_match")) {
                    stmt.execute("DROP TABLE bot_match");
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
