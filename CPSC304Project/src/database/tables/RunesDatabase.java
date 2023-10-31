package database.tables;

import database.tables.Database;
import model.Role;
import model.Runes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RunesDatabase extends Database {

    public RunesDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public RunesDatabase() {

    }

    public void createRunesTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE runes (" +
                            "runes_primary_path varchar2(50)," +
                            "runes_secondary_path varchar2(50)," +
                            "runes_keystone varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertRunesTuple(Runes model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO runes VALUES (?,?,?)");
            ps.setString(1, model.getRunesPrimaryPath());
            ps.setString(2, model.getRunesSecondaryPath());
            ps.setString(3, model.getKeystone());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateRunesDatabaseTuple(Runes model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE runes SET runes_primary_path = ?, " +
                                                                                    "runes_secondary_path = ?, " +
                                                                                    "runes_keystone = ? " +
                                                                                    "WHERE runes_primary_path = ? AND " +
                                                                                            "runes_secondary_path = ? ");

            ps.setString(1, model.getRunesPrimaryPath());
            ps.setString(2, model.getRunesSecondaryPath());
            ps.setString(3, model.getKeystone());
            ps.setString(4, model.getRunesPrimaryPath());
            ps.setString(5, model.getRunesSecondaryPath());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteRunesDatabaseTuple(String runesPrimaryPath, String runesSecondaryPath) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM runes WHERE runes_primary_path = ? AND runes_secondary_path = ? ");
            ps.setString(1, runesPrimaryPath);
            ps.setString(2, runesSecondaryPath);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public Runes[] getRunesArrayFromDatabase() {
        ArrayList<Runes> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM runes");

            while(rs.next()) {
                Runes model = new Runes(rs.getString("runes_primary_path"),
                        rs.getString("runes_secondary_path"),
                        rs.getString("runes_keystone"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Runes[] runes = result.toArray(new Runes[result.size()]);
        return runes;
    }

    public void dropRunesTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("runes")) {
                    stmt.execute("DROP TABLE runes");
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
