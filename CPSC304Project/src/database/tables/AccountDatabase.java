package database.tables;

import model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDatabase extends Database{

    public AccountDatabase() {
        super();
    }

    public void insertAccountDatabaseTuple(Account model) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO account VALUES (?,?,?,?,?)");
        ps.setString(1, model.getAccountID());
        ps.setString(2, model.getAccountName());
        ps.setFloat(3, model.getAccountStats());
        ps.setInt(4, model.getAccountLevel());
        ps.setString(5, model.getAccountStatus());

        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public void createAccountTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE account (" +
                            "account_id varchar2(50) PRIMARY KEY," +
                            "account_name varchar2(50)," +
                            "account_stats float ," +
                            "account_level integer," +
                            "account_status varchar2(50))");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    // Sources: https://www.w3schools.com/sql/sql_update.asp
    public void updateAccountDatabaseTuple(Account model) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE account SET account_id = ?, " +
                                                                                      "account_name = ?, " +
                                                                                      "account_stats = ?," +
                                                                                      "account_level = ?," +
                                                                                      "account_status = ? " +
                                                                    "WHERE account_id = ? AND account_name = ? ");
            ps.setString(1, model.getAccountID());
            ps.setString(2, model.getAccountName());
            ps.setFloat(3, model.getAccountStats());
            ps.setInt(4, model.getAccountLevel());
            ps.setString(5, model.getAccountStatus());
            ps.setString(6, model.getAccountID());
            ps.setString(7, model.getAccountName());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }

    // Sources: https://www.w3schools.com/sql/sql_delete.asp
    public void deleteAccountDatabaseTuple(String accountID, String accountName) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM account WHERE account_id = ? AND account_name = ? ");
            ps.setString(1, accountID);
            ps.setString(2, accountName);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
            connection.rollback();
        }
    }




    public Account[] getAccountArrayFromDatabase() {
        ArrayList<Account> result = new ArrayList<Account>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM account");

            while(rs.next()) {
                Account model = new Account(rs.getString("account_id"),
                        rs.getString("account_name"),
                        rs.getFloat("account_stats"),
                        rs.getInt("account_level"),
                        rs.getString("account_status"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
        }

        Account[] accounts = result.toArray(new Account[result.size()]);
        return accounts;

    }

    public void dropAccountTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("account")) {
                    stmt.execute("DROP TABLE account");
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
