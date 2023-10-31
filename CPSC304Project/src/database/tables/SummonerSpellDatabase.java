package database.tables;

import database.tables.Database;
import model.Runes;
import model.SummonerSpell;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SummonerSpellDatabase extends Database {
    public SummonerSpellDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public SummonerSpellDatabase() {

    }

    public void createSummonerSpellTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE summoner_spell (" +
                            "summoner_spell_name varchar2(50) PRIMARY KEY," +
                            "summoner_spell_description varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertSummonerSpellTuple(SummonerSpell model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO summoner_spell VALUES (?,?)");
            ps.setString(1, model.getSummonerSpellName());
            ps.setString(2, model.getSummonerSpellNameDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateSummonerSpellDatabaseTuple(SummonerSpell model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE summoner_spell SET summoner_spell_name = ?, " +
                                                                                                "summoner_spell_description = ?, " +
                                                                                                "WHERE summoner_spell_name = ?");

            ps.setString(1, model.getSummonerSpellName());
            ps.setString(2, model.getSummonerSpellNameDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteSummonerSpellDatabaseTuple(String summonerSpellName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM summoner_spell WHERE summoner_spell_name = ?");
            ps.setString(1, summonerSpellName);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    public SummonerSpell[] getSummonerSpellArrayFromDatabase() {
        ArrayList<SummonerSpell> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM summoner_spell");

            while(rs.next()) {
                SummonerSpell model = new SummonerSpell(rs.getString("summoner_spell_name"),
                        rs.getString("summoner_spell_description"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        SummonerSpell[] summonerSpells = result.toArray(new SummonerSpell[result.size()]);
        return summonerSpells;
    }

    public void dropSummonerSpellTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("summoner_spell")) {
                    stmt.execute("DROP TABLE summoner_spell");
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
