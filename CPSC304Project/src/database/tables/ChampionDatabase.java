package database.tables;

import model.Champion;
import model.match.BotMatch;

import java.sql.*;
import java.util.ArrayList;

public class ChampionDatabase extends Database {


    public ChampionDatabase() {
        super();
    }

    public ChampionDatabase(String username, String password) {
        super.setCredentials(username, password);
    }

    public void createChampionTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE champion (" +
                            "champion_name varchar2(50) PRIMARY KEY," +
                            "champion_type varchar2(50) not null," +
                            "champion_stats integer)");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("line 24");
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertChampionTuple(Champion model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO champion VALUES (?,?,?)");
            ps.setString(1, model.getChampionName());
            ps.setString(2, model.getChampionType());
            ps.setInt(3, model.getChampionStats());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateChampionDatabaseTuple(Champion model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE champion SET champion_name = ?, " +
                                                                                        "champion_type = ?, " +
                                                                                        "champion_stats = ? " +
                                                                                        "WHERE champion_name = ?");
            ps.setString(1, model.getChampionName());
            ps.setString(2, model.getChampionType());
            ps.setInt(3, model.getChampionStats());
            ps.setString(4, model.getChampionName());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteChampionDatabaseTuple(String championName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM champion WHERE champion_name = ?");
            ps.setString(1, championName);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public Champion[] getChampionArrayFromDatabase() {
        ArrayList<Champion> result = new ArrayList<Champion>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM champion");

            while(rs.next()) {
                Champion model = new Champion(rs.getString("champion_name"),
                        rs.getString("champion_type"),
                        rs.getInt("champion_stats"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Champion[] champions = result.toArray(new Champion[result.size()]);
        return champions;
//
//        for (int i = 0; i < accounts.length; i++) {
//            System.out.println(accounts[i].getAccountID());
//            System.out.println(accounts[i].getAccountName());
//            System.out.println(accounts[i].getAccountLevel());
//            System.out.println(accounts[i].getAccountStatus());
//            System.out.println(accounts[i].getAccountStats());
//        }
    }

    public void dropChampionTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("champion")) {
                    stmt.execute("DROP TABLE champion");
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
