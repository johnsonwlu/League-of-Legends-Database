package UI;

import controller.DatabaseMainWindow;
import delegates.UpdateWindowDelegate;
import delegates.UpdateWindowDelegate;

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

public class UpdateWindow extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 10;
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    // layout components using the GridBag layout manager
    private GridBagLayout gb;
    private GridBagConstraints c;
    private JPanel contentPane;
    private JButton updateButton;
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

    HashMap<String, UpdateWindow.TupleType> tuple_strings;


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
    // friendlist inputs
    String[] friendlistKeys = {"accountID", "accountName","friendlistedID","friendListedName"};
    // inRank input
    String[] inRankKeys = {"accountID","accountName","rankNumber","rankTier"};
    //InTeamUse Input
    String[] inTeamUseKeys = {"accountID","teamColor","championName","itemName","summonerSpellName","priamaryPath","secondaryPath"};
    //Item Input
    String[] itemKeys = {"itemName","itemDescription"};
    //PVPMatch Input
    String[] pvpMatchKeys = {"matchID","matchMap","matchStartTime","matchEndTime","rank"};
    //Rank Input
    String[] rankKeys = {"rankNumber","rankTier"};
    //Role Input
    String[] roleKeys = {"roleName","championName"};
    //Runes Input
    String[] runeKeys = {"runesPrimaryPath","runesSecondaryPath","keyStone"};
    //SummonerSpell Input
    String[] summonerSpellKeys = {"summonerSpellName","summonerSpellNameDescription"};
    //TeamParticipation Input
    String[] teamParticipationKeys = {"matchID","championName","teamColor","firstTowerTakeDownInSecond","firstDragonInSecond", "inTeamstats", "participationWinner"};
    // delegate
    private UpdateWindowDelegate delegate;

    public UpdateWindow() {
        super("Update Window");
        gb = new GridBagLayout();
        c = new GridBagConstraints();
        contentPane = new JPanel();
        // tuple strings
        tuple_strings = new HashMap<String, UpdateWindow.TupleType>();
        init_tuple_strings();
        // Selection Box
        String[] tupleStrings = {"Account", "Account play", "Blacklist", "Bot-match", "Champion","FriendList","InRank"
                ,"InTeamUse","Item","PVP_Match","Rank","Roles","Runes","SummonerSpell","Team_Participation"};
        tupleList = new JComboBox(tupleStrings);
        tupleList.addActionListener(this);
    }

    private void init_tuple_strings()
    {
        tuple_strings.put("Account", UpdateWindow.TupleType.ACCOUNT);
        tuple_strings.put("Account play", UpdateWindow.TupleType.ACCOUNT_PLAY);
        tuple_strings.put("Blacklist", UpdateWindow.TupleType.BLACKLIST);
        tuple_strings.put("Bot-match", UpdateWindow.TupleType.BOT_MATCH);
        tuple_strings.put("Champion", UpdateWindow.TupleType.CHAMPION);
        tuple_strings.put("FriendList", UpdateWindow.TupleType.FRIEND_LIST);
        tuple_strings.put("InRank", UpdateWindow.TupleType.IN_RANK);
        tuple_strings.put("InTeamUse", UpdateWindow.TupleType.IN_TEAM_USE);
        tuple_strings.put("Item", UpdateWindow.TupleType.ITEM);
        tuple_strings.put("PVP_Match", UpdateWindow.TupleType.PVP_MATCH);
        tuple_strings.put("Rank", UpdateWindow.TupleType.RANK);
        tuple_strings.put("Roles", UpdateWindow.TupleType.ROLE);
        tuple_strings.put("Runes", UpdateWindow.TupleType.RUNES);
        tuple_strings.put("SummonerSpell", UpdateWindow.TupleType.SUMMONER_SPELL);
        tuple_strings.put("Team_Participation", UpdateWindow.TupleType.TEAM_PARTICIPATION);
    }
    public void showFrame(UpdateWindowDelegate delegate) {
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
        JButton updateButton = new JButton("Update " + tupleType);
        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(updateButton, c);
        contentPane.add(updateButton);

        // register button with action event handler
        updateButton.addActionListener(this);
        backButton = new JButton("Back");
        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(backButton, c);
        contentPane.add(backButton);

        // register button with action event handler
        backButton.addActionListener(this);
        return updateButton;
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
                    this.updateButton = show_inputs(accountkeys, tupleName);
                    break;
                case ACCOUNT_PLAY:
                    this.updateButton = show_inputs(accountPlaykeys, tupleName);
                    break;
                case BLACKLIST:
                    this.updateButton = show_inputs(blacklistKeys, tupleName);
                    break;
                case BOT_MATCH:
                    this.updateButton = show_inputs(botMatchKeys, tupleName);
                    break;
                case CHAMPION:
                    this.updateButton = show_inputs(championKeys, tupleName);
                    break;
                case FRIEND_LIST:
                    this.updateButton = show_inputs(friendlistKeys, tupleName);
                    break;
                case IN_RANK:
                    this.updateButton = show_inputs(inRankKeys, tupleName);
                    break;
                case IN_TEAM_USE:
                    this.updateButton = show_inputs(inTeamUseKeys, tupleName);
                    break;
                case ITEM:
                    this.updateButton = show_inputs(itemKeys, tupleName);
                    break;
                case PVP_MATCH:
                    this.updateButton = show_inputs(pvpMatchKeys, tupleName);
                    break;
                case RANK:
                    this.updateButton = show_inputs(rankKeys, tupleName);
                    break;
                case ROLE:
                    this.updateButton = show_inputs(roleKeys, tupleName);
                    break;
                case RUNES:
                    this.updateButton = show_inputs(runeKeys, tupleName);
                    break;
                case SUMMONER_SPELL:
                    this.updateButton = show_inputs(summonerSpellKeys, tupleName);
                    break;
                case TEAM_PARTICIPATION:
                    this.updateButton = show_inputs(teamParticipationKeys, tupleName);
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
                if (this.updateButton.getText().equals("Update Account"))
                {
                    delegate.updateAccount(input_array[0], input_array[1], Float.parseFloat(input_array[2]), parseInt(input_array[3]), input_array[4]);
                }
                else if (this.updateButton.getText().equals("Update Account play"))
                {
                    delegate.updateAccountPlay(input_array[0], input_array[1], input_array[2], input_array[3], input_array[4], Float.parseFloat(input_array[5]));
                }
                else if (this.updateButton.getText().equals("Update Blacklist"))
                {
                    delegate.updateBlacklist(input_array[0], input_array[1], input_array[2], input_array[3]);
                }
                else if (this.updateButton.getText().equals("Update Champion"))
                {
                    delegate.updateChampion(input_array[0], input_array[1], parseInt(input_array[2]));
                }
                else if (this.updateButton.getText().equals("Update Bot-match"))
                {
                    delegate.updateBotMatch(input_array[0], input_array[1], input_array[2], input_array[3], input_array[4], to_boolean(input_array[5]));
                }
                else if (this.updateButton.getText().equals("Update Friendlist")) {
                    delegate.updateFriendlist(input_array[0],input_array[1],input_array[2],input_array[3]);
                }
                else if (this.updateButton.getText().equals("Update In-Rank"))
                {
                    delegate.updateInRank(input_array[0], input_array[1], input_array[2], input_array[3]);
                }
                else if (this.updateButton.getText().equals("Update In-Team-Use"))
                {
                    delegate.updateInTeamUse(input_array[0], input_array[1], input_array[2], input_array[3], input_array[4], input_array[5],input_array[6]);
                }
                else if (this.updateButton.getText().equals("Update Item"))
                {
                    delegate.updateItem(input_array[0], input_array[1]);
                }
                else if (this.updateButton.getText().equals("Update PVP-Match"))
                {
                    delegate.updatePVPMatch(input_array[0], input_array[1], input_array[2], input_array[3], parseInt(input_array[3]));
                }
                else if (this.updateButton.getText().equals("Update Rank"))
                {
                    delegate.updateRank(input_array[0], input_array[1]);
                }
                else if (this.updateButton.getText().equals("Update Role"))
                {
                    delegate.updateRole(input_array[0], input_array[1]);
                }
                else if (this.updateButton.getText().equals("Update Runes"))
                {
                    delegate.updateRunes(input_array[0], input_array[1], input_array[2]);
                }
                else if (this.updateButton.getText().equals("Update SummonerSpell"))
                {
                    delegate.updateSummonerSpell(input_array[0], input_array[1]);
                }
                else if (this.updateButton.getText().equals("Update Team Participation"))
                {
                    delegate.updateTeamParticipation(input_array[0], input_array[1], input_array[2],parseInt(input_array[3]),parseInt(input_array[4]),parseInt(input_array[5]),input_array[6]);
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
