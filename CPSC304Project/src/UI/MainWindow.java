package UI;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.DatabaseInsertWindow;
import delegates.MainWindowDelegate;

/**
 * The class is only responsible for displaying and handling the login GUI.
 */
public class MainWindow extends JFrame implements ActionListener {
    // delegate
    private MainWindowDelegate delegate;

    public MainWindow() {
        super();
    }

    public void showFrame(MainWindowDelegate delegate) {

        this.delegate = delegate;
        JButton insert = new JButton("Insert");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton show = new JButton("Show");
        JButton loginButton = new JButton("Select");
        JButton exit = new JButton("Exit");
        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        contentPane.add(insert);
        contentPane.add(update);
        contentPane.add(delete);
        contentPane.add(show);
        contentPane.add(loginButton);
        contentPane.add(exit);
        // register all buttons with action event handler
        loginButton.addActionListener(this);
        insert.addActionListener(this);
        update.addActionListener(this);
        show.addActionListener(this);
        delete.addActionListener(this);
        exit.addActionListener(this);


        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

        // make the window visible
        this.setVisible(true);

    }

    /**
     * ActionListener Methods
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        handleInsertion(e);
        handleDelete(e);
        handleUpdate(e);
        handleShow(e);
        handleSelect(e);
        handleExit(e);
    }

    public void handleInsertion(ActionEvent e) {
        if (e.getActionCommand().equals("Insert")) {
            delegate.insert();
            this.dispose();
        }
    }

    public void handleDelete(ActionEvent e) {
        if (e.getActionCommand().equals("Delete")) {
           delegate.delete();
           this.dispose();

        }
    }

    public void handleUpdate(ActionEvent e) {
        if (e.getActionCommand().equals("Update")) {
            delegate.update();
            this.dispose();

        }
    }

    public void handleShow(ActionEvent e) {
        if (e.getActionCommand().equals("Show")) {
            setVisible(false);
            delegate.show();
            this.dispose();
        }
    }

    public void handleSelect(ActionEvent e) {
        if (e.getActionCommand().equals("Select")) {
            setVisible(false);
            delegate.select();
            this.dispose();
        }
    }

    public void handleExit(ActionEvent e) {
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }
}