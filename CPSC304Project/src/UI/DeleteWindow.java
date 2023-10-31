package UI;

import controller.DatabaseMainWindow;
import delegates.DeleteWindowDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class DeleteWindow extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 10;
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    // layout components using the GridBag layout manager
    private GridBagLayout gb;
    private GridBagConstraints c;
    private JPanel contentPane;
    private JButton deleteButton;
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
        CHAMPION,
        FRIEND_LIST,
        IN_RANK,
        IN_TEAM_USE,
        ITEM,
        PVP_MATCH,
        RANK,
        ROLE,
        RUNES,
        SUMMONER_SPELL,
        TEAM_PARTICIPATION
    }

    HashMap<String, DeleteWindow.TupleType> tuple_strings;

    // account inputs
    String[] accountkeys = { "Account ID", "Account Name"};
    // account play inputs
    String[] accountPlaykeys = { "Champion Name", "Team color", "Account ID", "Account Name", "Match ID", "Play stats"};
    // blacklist inputs
    String[] blacklistKeys = { "Account ID", "Account Name"};
    // bot match
    String[] botMatchKeys = { "Match ID"};
    // champion inputs
    String[] championKeys = { "Champion Name"};
    // friendlist inputs
    String[] friendlistKeys = {"accountID", "accountName"};
    // inRank input
    String[] inRankKeys = {"accountID"};
    //InTeamUse Input
    String[] inTeamUseKeys = {"accountID"};
    //Item Input
    String[] itemKeys = {"itemName"};
    //PVPMatch Input
    String[] pvpMatchKeys = {"matchID"};
    //Rank Input
    String[] rankKeys = {"rankNumber"};
    //Role Input
    String[] roleKeys = {"roleName"};
    //Runes Input
    String[] runeKeys = {"runesPrimaryPath","runesSecondaryPath"};
    //SummonerSpell Input
    String[] summonerSpellKeys = {"summonerSpellName"};
    //TeamParticipation Input
    String[] teamParticipationKeys = {"matchID","championName","teamColor"};

    // delegate
    private DeleteWindowDelegate delegate;

    public DeleteWindow() {
        super("Delete Window");
        gb = new GridBagLayout();
        c = new GridBagConstraints();
        contentPane = new JPanel();
        // tuple strings
        tuple_strings = new HashMap<String, DeleteWindow.TupleType>();
        init_tuple_strings();
        // Selection Box
        String[] tupleStrings = {"Account", "Account play", "Blacklist", "Bot-match", "Champion","FriendList","InRank"
                ,"InTeamUse","Item","PVP_Match","Rank","Roles","Runes","SummonerSpell","Team_Participation"};
        tupleList = new JComboBox(tupleStrings);
        tupleList.addActionListener(this);
    }

    private void init_tuple_strings()
    {
        tuple_strings.put("Account", DeleteWindow.TupleType.ACCOUNT);
        tuple_strings.put("Account play", DeleteWindow.TupleType.ACCOUNT_PLAY);
        tuple_strings.put("Blacklist", DeleteWindow.TupleType.BLACKLIST);
        tuple_strings.put("Bot-match", DeleteWindow.TupleType.BOT_MATCH);
        tuple_strings.put("Champion", DeleteWindow.TupleType.CHAMPION);
        tuple_strings.put("FriendList", DeleteWindow.TupleType.FRIEND_LIST);
        tuple_strings.put("InRank", DeleteWindow.TupleType.IN_RANK);
        tuple_strings.put("InTeamUse", DeleteWindow.TupleType.IN_TEAM_USE);
        tuple_strings.put("Item", DeleteWindow.TupleType.ITEM);
        tuple_strings.put("PVP_Match", DeleteWindow.TupleType.PVP_MATCH);
        tuple_strings.put("Rank", DeleteWindow.TupleType.RANK);
        tuple_strings.put("Roles", DeleteWindow.TupleType.ROLE);
        tuple_strings.put("Runes", DeleteWindow.TupleType.RUNES);
        tuple_strings.put("SummonerSpell", DeleteWindow.TupleType.SUMMONER_SPELL);
        tuple_strings.put("Team_Participation", DeleteWindow.TupleType.TEAM_PARTICIPATION);
    }

    public void showFrame(DeleteWindowDelegate delegate) {
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
        for (String keyName : key_list)
        {
            put_text_box(keyName);
        }
        JButton deleteButton = new JButton("Delete " + tupleType);
        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(deleteButton, c);
        contentPane.add(deleteButton);

        // register button with action event handler
        deleteButton.addActionListener(this);
        backButton = new JButton("Back");
        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(backButton, c);
        contentPane.add(backButton);

        // register button with action event handler
        backButton.addActionListener(this);
        return deleteButton;
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
                    this.deleteButton = show_inputs(accountkeys, tupleName);
                    break;
                case ACCOUNT_PLAY:
                    this.deleteButton = show_inputs(accountPlaykeys, tupleName);
                    break;
                case BLACKLIST:
                    this.deleteButton = show_inputs(blacklistKeys, tupleName);
                    break;
                case BOT_MATCH:
                    this.deleteButton = show_inputs(botMatchKeys, tupleName);
                    break;
                case CHAMPION:
                    this.deleteButton = show_inputs(championKeys, tupleName);
                    break;
                case FRIEND_LIST:
                    this.deleteButton = show_inputs(friendlistKeys, tupleName);
                    break;
                case IN_RANK:
                    this.deleteButton = show_inputs(inRankKeys, tupleName);
                    break;
                case IN_TEAM_USE:
                    this.deleteButton = show_inputs(inTeamUseKeys, tupleName);
                    break;
                case ITEM:
                    this.deleteButton = show_inputs(itemKeys, tupleName);
                    break;
                case PVP_MATCH:
                    this.deleteButton = show_inputs(pvpMatchKeys, tupleName);
                    break;
                case RANK:
                    this.deleteButton = show_inputs(rankKeys, tupleName);
                    break;
                case ROLE:
                    this.deleteButton = show_inputs(roleKeys, tupleName);
                    break;
                case RUNES:
                    this.deleteButton = show_inputs(runeKeys, tupleName);
                    break;
                case SUMMONER_SPELL:
                    this.deleteButton = show_inputs(summonerSpellKeys, tupleName);
                    break;
                case TEAM_PARTICIPATION:
                    this.deleteButton = show_inputs(teamParticipationKeys, tupleName);
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
            String[] input_array = get_input_array();
            try {
                if (this.deleteButton.getText().equals("Delete Account"))
                {
                    delegate.deleteAccount(input_array[0], input_array[1]);
                }
                else if (this.deleteButton.getText().equals("Delete Account play"))
                {
                    delegate.deleteAccountPlay(input_array[0], input_array[1], input_array[2], input_array[3], input_array[4], Float.parseFloat(input_array[5]));
                }
                else if (this.deleteButton.getText().equals("Delete Blacklist"))
                {
                    delegate.deleteBlacklist(input_array[0], input_array[1]);
                }
                else if (this.deleteButton.getText().equals("Delete Champion"))
                {
                    delegate.deleteChampion(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete Bot-match"))
                {
                    delegate.deleteBotMatch(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete Friendlist")) {
                    delegate.deleteFriendlist(input_array[0],input_array[1]);
                }
                else if (this.deleteButton.getText().equals("Delete In-Rank"))
                {
                    delegate.deleteInRank(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete In-Team-Use"))
                {
                    delegate.deleteInTeamUse(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete Item"))
                {
                    delegate.deleteItem(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete PVP-Match"))
                {
                    delegate.deletePVPMatch(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete Rank"))
                {
                    delegate.deleteRank(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete Role"))
                {
                    delegate.deleteRole(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete Runes"))
                {
                    delegate.deleteRunes(input_array[0], input_array[1]);
                }
                else if (this.deleteButton.getText().equals("Delete SummonerSpell"))
                {
                    delegate.deleteSummonerSpell(input_array[0]);
                }
                else if (this.deleteButton.getText().equals("Delete Team Participation"))
                {
                    delegate.deleteTeamParticipation(input_array[0], input_array[1], input_array[2]);
                }
                else
                {
                    System.out.println("Nothing done");
                }
                System.out.println("Success");
            } catch(SQLException sqlException) {
                System.out.println("Failed");
            }
        }
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