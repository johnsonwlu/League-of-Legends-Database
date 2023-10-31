package controller;

import UI.*;
import delegates.MainWindowDelegate;
import delegates.ShowWindowDelegate;

import java.sql.Connection;

public class DatabaseMainWindow implements MainWindowDelegate {

    private Connection connection = null;


    public DatabaseMainWindow(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void insert() {
        DatabaseInsertWindow databaseInsertWindow = new DatabaseInsertWindow(connection);
        InsertWindow insertWindow = new InsertWindow();
        insertWindow.showFrame(databaseInsertWindow);
    }

    @Override
    public void select() {
        DatabaseSelectWindow databaseSelectWindow = new DatabaseSelectWindow(connection);
        SelectWindow selectWindow = new SelectWindow();
        selectWindow.showFrame(databaseSelectWindow);
    }

    @Override
    public void show() {
        DatabaseShowWindow databaseShowWindow = new DatabaseShowWindow(connection);
        ShowWindow showWindow = new ShowWindow();
        showWindow.showFrame(databaseShowWindow);
    }


    @Override
    public void delete() {
        DatabaseDeleteWindow databaseDeleteWindow = new DatabaseDeleteWindow(connection);
        DeleteWindow deleteWindow = new DeleteWindow();
        deleteWindow.showFrame(databaseDeleteWindow);
    }

    @Override
    public void update() {
        DatabaseUpdateWindow databaseUpdateWindow = new DatabaseUpdateWindow(connection);
        UpdateWindow updateWindow = new UpdateWindow();
        updateWindow.showFrame(databaseUpdateWindow);
    }
}
