package database.tables;

import model.InTeamUse;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDatabase extends Database{
    public ItemDatabase(String username, String password) {
        super();
        super.setCredentials(username, password);
    }

    public ItemDatabase() {

    }

    public void createItemTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE item (" +
                            "item_name varchar2(50) PRIMARY KEY," +
                            "item_description varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void insertItemTuple(Item model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO item VALUES (?,?)");
            ps.setString(1, model.getItemName());
            ps.setString(2, model.getItemDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateItemDatabaseTuple(Item model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE item SET item_name = ?, " +
                                                                                    "item_description = ? " +
                                                                                    "WHERE item_name = ?");

            ps.setString(1, model.getItemName());
            ps.setString(2, model.getItemDescription());
            ps.setString(3, model.getItemName());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteItemDatabaseTuple(String itemName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM item WHERE item_name = ?");
            ps.setString(1, itemName);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }


    public Item[] getItemArrayFromDatabase() {
        ArrayList<Item> result = new ArrayList<Item>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM item");

            while(rs.next()) {
                Item model = new Item(rs.getString("item_name"),
                        rs.getString("item_description"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Item[] items = result.toArray(new Item[result.size()]);
        return items;
    }

    public void dropItemTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("item")) {
                    stmt.execute("DROP TABLE item");
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
