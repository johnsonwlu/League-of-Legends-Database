package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import controller.DatabaseMainWindow;
import delegates.ShowWindowDelegate;
import model.Account;
import model.AccountPlay;
import model.Blacklist;

import static java.lang.Integer.parseInt;

/**
 * The class is only responsible for displaying and handling the login GUI.
 */
public class ShowWindow extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 10;
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    // layout components using the GridBag layout manager
    private GridBagLayout gb;
    private GridBagConstraints c;
    private JPanel contentPane;
    private JButton showButton;
    private JButton backButton;

    // running accumulator for login attempts
    private int loginAttempts;

    // components of the window
    private JComboBox tupleList;

    // tuple types
    private enum TupleType {
        ACCOUNT,
        ACCOUNT_PLAY,
        BLACKLIST,
        BOT_MATCH,
        CHAMPION
    }

    HashMap<String, TupleType> tuple_strings;

    // account inputs
    String[] accountkeys = { "Account ID", "Account Name", "Account Stats", "Account Level", "Account Status"};
    // account play inputs
    String[] accountPlaykeys = { "Champion Name", "Team color", "Account ID", "Account Name", "Match ID", "Play stats"};
    // blacklist inputs
    String[] blacklistKeys = { "Account ID", "Account Name", "Blacklisted ID", "Blacklisted Name"};
    // bot match
    String[] botMatchKeys = { "Match ID", "Match Map", "Match Start Time", "Match End Time", "Difficulty", "Curses"};
    // champion inputs
    String[] championKeys = { "Champion Name", "Champion type", "Champion Stats"};
    //
    // delegate
    private ShowWindowDelegate delegate;

    public ShowWindow() {
        super("Show Window");
        gb = new GridBagLayout();
        c = new GridBagConstraints();
        contentPane = new JPanel();
        // tuple strings
        tuple_strings = new HashMap<String, TupleType>();
        init_tuple_strings();
        // Selection Box
        String[] tupleStrings = {"Account", "Account play", "Blacklist", "Bot-match", "Champion"};
        tupleList = new JComboBox(tupleStrings);
        tupleList.addActionListener(this);
    }

    private void init_tuple_strings()
    {
        tuple_strings.put("Account", TupleType.ACCOUNT);
        tuple_strings.put("Account play", TupleType.ACCOUNT_PLAY);
        tuple_strings.put("Blacklist", TupleType.BLACKLIST);
        tuple_strings.put("Bot-match", TupleType.BOT_MATCH);
        tuple_strings.put("Champion", TupleType.CHAMPION);
    }

    public void showFrame(ShowWindowDelegate delegate) {
        this.delegate = delegate;

        this.setContentPane(contentPane);

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // place selection box
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(tupleList, c);
        contentPane.add(tupleList);

        backButton = new JButton("Back");
        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(backButton, c);
        contentPane.add(backButton);

        // register button with action event handler
        backButton.addActionListener(this);

        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

        // make the window visible
        this.setVisible(true);

    }

    private void put_text_box(String label_string)
    {
        JLabel iLabel = new JLabel(label_string + ": ");
        JTextField iTextField = new JTextField(TEXT_FIELD_WIDTH);
        // place the iLabel
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(iLabel, c);
        contentPane.add(iLabel);
        // place the accountID
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(iTextField, c);
        contentPane.add(iTextField);
    }

    private JButton show_inputs(String[] key_list, String tupleType)
    {
        this.getContentPane().removeAll();
        // Selection Box
        String[] tupleStrings = {"Account", "Account play", "Blacklist", "Bot-match", "Champion"};
        tupleList = new JComboBox(tupleStrings);
        tupleList.addActionListener(this);

        JButton insertButton = new JButton("Show " + tupleType);
        // place selection box
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(tupleList, c);
        contentPane.add(tupleList);

        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(insertButton, c);
        contentPane.add(insertButton);

        // register button with action event handler
        insertButton.addActionListener(this);
        backButton = new JButton("Back");
        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(backButton, c);
        contentPane.add(backButton);

        // register button with action event handler
        backButton.addActionListener(this);
        return insertButton;
    }

    /**
     * ActionListener Methods
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tupleList)
        {
            JComboBox cb = (JComboBox)e.getSource();
            String tupleName = (String)cb.getSelectedItem();
            switch (tuple_strings.get(tupleName))
            {
                case ACCOUNT:
                    this.showButton = show_inputs(accountkeys, tupleName);
                    break;
                case ACCOUNT_PLAY:
                    this.showButton = show_inputs(accountPlaykeys, tupleName);
                    break;
                case BLACKLIST:
                    this.showButton = show_inputs(blacklistKeys, tupleName);
                    break;
                case BOT_MATCH:
                    this.showButton = show_inputs(botMatchKeys, tupleName);
                    break;
                case CHAMPION:
                    this.showButton = show_inputs(championKeys, tupleName);
                    break;
                default:
                    System.out.println("case not included");
            }
            this.pack();
        }
        else if (e.getSource() == backButton)
        {
            DatabaseMainWindow databaseMainWindow = new DatabaseMainWindow(this.delegate.getConnection());
            MainWindow mainWindow = new UI.MainWindow();
            mainWindow.showFrame(databaseMainWindow);
            this.dispose();
        }
        else
        {
            try {
                if (this.showButton.getText().equals("Show Account"))
                {
                    show_list(this.delegate.showAccount());
                }
                else if (this.showButton.getText().equals("Show Account play"))
                {
                    show_list(this.delegate.showAccountPlay());
                }
                else if (this.showButton.getText().equals("Show Blacklist"))
                {
                    show_list(this.delegate.showBlacklist());
                }
                else if (this.showButton.getText().equals("Show Champion"))
                {
                    show_list(this.delegate.showChampion());
                }
                else if (this.showButton.getText().equals("Show Bot-match"))
                {
                    show_list(this.delegate.showBotMatch());
                }
                else if (this.showButton.getText().equals("Show Friendlist"))
                {
                    show_list(this.delegate.showFriendlist());
                }
                else
                {
                    System.out.println("Nothing done");
                }
                System.out.println("Success");
            } catch(Exception exception) {
                System.out.println("Failed");
            }
        }
    }



    private void show_list(Object[] tuple_array)
    {
        JList list = new JList(tuple_array); //data has type Object[]
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(list, c);
        contentPane.add(list);
        this.pack();
    }


    private String[] get_input_array()
    {
        ArrayList<String> inputs_str = new ArrayList<String>();
        for(Component c : this.contentPane.getComponents()) {
            if(c instanceof JTextField){
                JTextField iTextField = (JTextField) c;
                String text = iTextField.getText();
                inputs_str.add(text);
            }
        }
        String[] input_array = inputs_str.toArray(new String[inputs_str.size()]);
        return input_array;
    }

    private boolean to_boolean(String string)
    {
        return (string.equals("true"));

    }
}
