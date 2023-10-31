package database.tables;

import database.tables.Database;
import model.Rank;
import model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoleDatabase extends Database {
    public RoleDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public RoleDatabase() {

    }

    public void createRolesTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE roles (" +
                            "roleName varchar2(50) PRIMARY KEY," +
                            "championName varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertRolesTuple(Role model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO roles VALUES(?,?) ");
            ps.setString(1, model.getRoleName());
            ps.setString(2, model.getChampionName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateRoleDatabaseTuple(Role model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE roles SET roleName = ?, " +
                                                                                    "championName = ? " +
                                                                                    "WHERE roleName = ?");

            ps.setString(1, model.getRoleName());
            ps.setString(2, model.getChampionName());
            ps.setString(3, model.getRoleName());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteRoleDatabaseTuple(String roleName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM roles WHERE rankName = ?");
            ps.setString(1, roleName);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    public Role[] getRolesArrayFromDatabase() {
        ArrayList<Role> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM roles");

            while (rs.next()) {
                Role model = new Role(rs.getString("roleName"),
                        rs.getString("championName"));
                result.add(model);

            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Role[] roles = result.toArray(new Role[result.size()]);
        return roles;
    }

    public void dropRoleTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("roles")) {
                    stmt.execute("DROP TABLE roles");
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