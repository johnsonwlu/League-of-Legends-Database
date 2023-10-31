package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.SQLException;

import database.tables.*;
import database.Query;
import delegates.TerminalTransactionsDelegate;
import model.*;
import model.list.*;
import model.match.BotMatch;
import model.match.PVPMatch;

//import ca.ubc.cs304.model.BranchModel;

/**
 * The class is only responsible for handling terminal text inputs.
 */
public class CommandLineUI {

	private String username;
	private String password;

	private String accountID;
	private String accountName;
	private float accountStats;
	private int accountLevel;

	private String accountStatus;

	private String blacklistedID;
	private String blacklistedName;

	private String championName;
	private String teamColor;
	private String matchID;
	private float playStats;

	private String matchMap;
	private String matchStartTime;
	private String matchEndTime;
	private String difficulty;
	private boolean curses;

	private String championType;
	private int championStats;

	private String friendlistedID;
	private String friendlistedName;

	private String rankNumber;
	private String rankTier;

	private String itemName;
	private String summonerSpellName;
	private String primaryPath;
	private String secondaryPath;

	private String itemDescription;

	private String roleName;

	private int rank;

	private String runesPrimaryPath;
	private String runesSecondaryPath;
	private String keystone;

	private String summonerSpellNameDescription;

	private int firstTowerTakeDownInSecond;
	private int firstDragonInSecond;
	private int inTeamStats;
	private String participationWinner;


	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	private Database dbHandler = new Database();
	private BufferedReader bufferedReader = null;
	private TerminalTransactionsDelegate delegate = null;

	private Query query = new Query();
	private Connection connection = null;


	public CommandLineUI(String username, String password) {
		this.username = username;
		this.password = password;
	}


	public void showMainMenu() throws IOException, SQLException {
		//this.delegate = delegate;

		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;

		while (choice != 5) {
			System.out.println();
			System.out.println("1. INSERT Operations");
			System.out.println("2. DELETE Operations");
			System.out.println("3. UPDATE Operations");
			System.out.println("4. SELECT Operations");
			System.out.println("5. Quit");
			System.out.print("Please select an option from 1 to 5: ");
			choice = readInteger(false);

			System.out.println(" ");

			switch (choice) {
				case 1:
					insertion();
					break;
				case 2:
					deletion();
					break;
				case 3:
					updates();
					break;
				case 4:
					selection();
					break;
				case 5:
					return;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
			}
		}

	}

	public void updates() throws IOException, SQLException {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;

		while (choice != 17) {
			System.out.println();
			System.out.println("1. UPDATE a new account tuple");
			System.out.println("2. UPDATE a new account play tuple");
			System.out.println("3. UPDATE a new blacklist tuple");
			System.out.println("4. UPDATE a new bot-match tuple");
			System.out.println("5. UPDATE a new champion tuple");
			System.out.println("6. UPDATE a new friendlist tuple");
			System.out.println("7. UPDATE a new in-rank tuple");
			System.out.println("8. UPDATE a new in-team-use tuple");
			System.out.println("9. UPDATE a new item tuple");
			System.out.println("10. UPDATE a new pvp-match tuple");
			System.out.println("11. UPDATE a new rank tuple");
			System.out.println("12. UPDATE a new role tuple");
			System.out.println("13. UPDATE a new runes tuple");
			System.out.println("14. UPDATE a new summoner spell tuple");
			System.out.println("15. UPDATE a new team participation tuple");
			System.out.println("16. Go Back to Main Menu");
			System.out.println("17. Quit");
			System.out.print("Please select an option from 1 to 17: ");
			choice = readInteger(false);

			System.out.println(" ");

			switch (choice) {
				case 1:
					AccountDatabase accountDatabase = new AccountDatabase();
					accountDatabase.getConnection();
					AccountList accounts = new AccountList();
					accounts.add(accountDatabase.getAccountArrayFromDatabase());
					accounts.printAccountData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the account id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();

					System.out.println("Enter the account name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();

					System.out.print("Enter new account stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountStats = readFloat(false);
					System.out.print("Enter new account level: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountLevel = readInteger(false);
					System.out.print("Enter new account status: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountStatus = bufferedReader.readLine();

					try {
						handleAccountUpdates(new Account(accountID, accountName, accountStats, accountLevel, accountStatus));
						System.out.println("You successfully updated a new account tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 2:
					AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase();
					accountPlayDatabase.getConnection();
					AccountPlayList accountPlays = new AccountPlayList();
					accountPlays.add(accountPlayDatabase.getAccountPlayArrayFromDatabase());
					accountPlays.printAccountPlayData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the account id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();

					System.out.print("Enter the champion name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();

					System.out.print("Enter the team color from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					teamColor = bufferedReader.readLine();
					System.out.print("Enter the account name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();

					System.out.print("Enter the match id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();

					System.out.print("Enter new play stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					playStats = readFloat(false);

					try {
						handleAccountPlayUpdates(new AccountPlay(championName, teamColor, accountID, accountName, matchID, playStats));
						System.out.println("You successfully updated a new account play tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 3:
					BlacklistDatabase blacklistDatabase = new BlacklistDatabase();
					blacklistDatabase.getConnection();
					BlacklistList blacklists = new BlacklistList();
					blacklists.add(blacklistDatabase.getBlacklistArrayFromDatabase());
					blacklists.printBlacklistListData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the account id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();

					System.out.print("Enter the account name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();

					System.out.print("Enter new blacklist id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					blacklistedID = bufferedReader.readLine();

					System.out.print("Enter new blacklist name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					blacklistedName = bufferedReader.readLine();

					try {
						handleBlacklistUpdates(new Blacklist(accountID, accountName, blacklistedID, blacklistedName));
						System.out.println("You successfully updated a new blacklist tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 4:
					BotMatchDatabase botMatchDatabase = new BotMatchDatabase(this.username, this.password);
					botMatchDatabase.getConnection();
					BotMatchList botMatchList = new BotMatchList();
					botMatchList.add(botMatchDatabase.getBotMatchArrayFromDatabase());
					botMatchList.printBotMatchData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the bot match id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();

					System.out.print("Enter new match map: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchMap = bufferedReader.readLine();

					System.out.print("Enter new match start time: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchStartTime = bufferedReader.readLine();

					System.out.print("Enter new match end time: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchEndTime = bufferedReader.readLine();

					System.out.print("Enter new difficulty: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					difficulty = bufferedReader.readLine();

					System.out.print("Enter new curses: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));

					if (bufferedReader.readLine().equals("false")) {
						curses = false;
					} else if (bufferedReader.equals("true")){
						curses = true;
					}


					try {
						handleBotMatchUpdates(new BotMatch(matchID, matchMap, matchStartTime, matchEndTime, difficulty, curses));
						System.out.println("You successfully updated a new bot match tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 5:
					ChampionDatabase championDatabase = new ChampionDatabase();
					championDatabase.getConnection();
					ChampionList championList = new ChampionList();
					championList.add(championDatabase.getChampionArrayFromDatabase());
					championList.printChampionData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the champion name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();

					System.out.print("Enter new champion type: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championType = bufferedReader.readLine();

					System.out.print("Enter new champion stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championStats = readInteger(false);

					try {
						handleChampionUpdates(new Champion(championName, championType, championStats));
						System.out.println("You successfully updated a new champion tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 6:
					FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
					friendlistDatabase.getConnection();
					FriendlistList friendLists = new FriendlistList();
					friendLists.add(friendlistDatabase.getFriendlistArrayFromDatabase());
					friendLists.printFriendlistData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the account id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();

					System.out.println("Enter the account name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();

					System.out.print("Enter new friendlist id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					friendlistedID = bufferedReader.readLine();

					System.out.print("Enter new friendlist name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					friendlistedName = bufferedReader.readLine();

					try {
						handleFriendlistUpdates(new Friendlist(accountID, accountName, friendlistedID, friendlistedName));
						System.out.println("You successfully updated a new friendlist tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 7:
					InRankDatabase inRankDatabase = new InRankDatabase();
					inRankDatabase.getConnection();
					InRankList inRankList = new InRankList();
					inRankList.add(inRankDatabase.getInRankArrayFromDatabase());
					inRankList.printInRankData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the account id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();

					System.out.println("Enter the account name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();

					System.out.print("Enter new rank tier: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankTier = bufferedReader.readLine();

					System.out.print("Enter new rank name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankNumber = bufferedReader.readLine();

					try {
						handleInRankUpdates(new InRank(accountID, accountName, rankNumber, rankTier));
						System.out.println("You successfully updated a new in-rank tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 8:
					ItemDatabase itemDatabase = new ItemDatabase();
					itemDatabase.getConnection();
					ItemList itemList = new ItemList();
					itemList.add(itemDatabase.getItemArrayFromDatabase());
					itemList.printItemData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the item name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					itemName = bufferedReader.readLine();

					System.out.println("Enter the item description: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					itemDescription = bufferedReader.readLine();

					try {
						handleItemUpdates(new Item(itemName, itemDescription));
						System.out.println("You successfully updated a new item tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 9:
					PVPMatchDatabase pvpMatchDatabase = new PVPMatchDatabase();
					pvpMatchDatabase.getConnection();
					PVPMatchList pvpMatchList = new PVPMatchList();
					pvpMatchList.add(pvpMatchDatabase.getPVPMatchArrayFromDatabase());
					pvpMatchList.printPVPMatchData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the match id from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();

					System.out.println("Enter new match start time: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchStartTime = bufferedReader.readLine();

					System.out.println("Enter new match end time: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchEndTime = bufferedReader.readLine();

					System.out.println("Enter new match map: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchMap = bufferedReader.readLine();

					System.out.println("Enter new match rank: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rank = readInteger(false);

					try {
						handlePVPMatchUpdates(new PVPMatch(matchID, matchStartTime, matchEndTime, matchMap, rank));
						System.out.println("You successfully updated a new pvp-match tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 10:
					RankDatabase rankDatabase = new RankDatabase();
					rankDatabase.getConnection();
					RankList rankList = new RankList();
					rankList.add(rankDatabase.getRankArrayFromDatabase());
					rankList.printRankData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the rank number from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankNumber = bufferedReader.readLine();

					System.out.println("Enter new rank tier: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankTier = bufferedReader.readLine();

					try {
						handleRankUpdates(new Rank(rankNumber, rankTier));
						System.out.println("You successfully updated a new rank tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;

				case 11:
					RoleDatabase roleDatabase = new RoleDatabase();
					roleDatabase.getConnection();
					RoleList roleList = new RoleList();
					roleList.add(roleDatabase.getRolesArrayFromDatabase());
					roleList.printRoleData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the role name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					roleName = bufferedReader.readLine();

					System.out.println("Enter new rank tier: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankTier = bufferedReader.readLine();

					try {
						handleRoleUpdates(new Role(roleName, championName));
						System.out.println("You successfully updated a new role tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 12:
					RunesDatabase runesDatabase = new RunesDatabase();
					runesDatabase.getConnection();
					RunesList runesList = new RunesList();
					runesList.add(runesDatabase.getRunesArrayFromDatabase());
					runesList.printRunesData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the runes primary path from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					primaryPath = bufferedReader.readLine();

					System.out.println("Enter the runes secondary path from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					secondaryPath = bufferedReader.readLine();

					System.out.println("Enter new keystone: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					keystone = bufferedReader.readLine();

					try {
						handleRunesUpdates(new Runes(primaryPath, secondaryPath, keystone));
						System.out.println("You successfully updated a new runes tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 13:
					SummonerSpellDatabase summonerSpellDatabase = new SummonerSpellDatabase();
					summonerSpellDatabase.getConnection();
					SummonerSpellList summonerSpellList = new SummonerSpellList();
					summonerSpellList.add(summonerSpellDatabase.getSummonerSpellArrayFromDatabase());
					summonerSpellList.printSummonerSpellData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the summoner spell name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					summonerSpellName = bufferedReader.readLine();

					System.out.println("Enter the summoner spell description: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					summonerSpellNameDescription = bufferedReader.readLine();

					try {
						handleSummonerSpellUpdates(new SummonerSpell(summonerSpellName, summonerSpellNameDescription));
						System.out.println("You successfully updated a new summoner spell tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 14:
					TeamParticipationDatabase teamParticipationDatabase = new TeamParticipationDatabase();
					teamParticipationDatabase.getConnection();

					TeamParticipationList teamParticipationList = new TeamParticipationList();
					teamParticipationList.add(teamParticipationDatabase.getTeamParticipationArrayFromDatabase());
					teamParticipationList.printTeamParticipationData();

					System.out.println();
					System.out.println();

					System.out.println("Enter the champion name from the list above: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();

					System.out.println("Enter new team color: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					teamColor = bufferedReader.readLine();

					System.out.println("Enter new match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();

					System.out.println("Enter new in-team stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					inTeamStats = readInteger(false);

					System.out.println("Enter new first tower take down: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					firstTowerTakeDownInSecond = readInteger(false);

					System.out.println("Enter new first dragon: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					firstDragonInSecond = readInteger(false);

					System.out.println("Enter new participation winner: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					participationWinner = bufferedReader.readLine();

					try {
						handleTeamParticipationUpdates(new TeamParticipation(matchID, championName, teamColor, firstTowerTakeDownInSecond, firstDragonInSecond, inTeamStats, participationWinner));
						System.out.println("You successfully updated a new team-participation tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 15:
					showMainMenu();
				case 16:
					return;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
			}
		}

	}

	public void deletion() throws IOException, SQLException {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;
		while(choice != 17){
			System.out.println();
			System.out.println("1. Delete account tuple");
			System.out.println("2. Delete account play tuple");
			System.out.println("3. Delete blacklist tuple");
			System.out.println("4. Delete bot-match tuple");
			System.out.println("5. Delete champion tuple");
			System.out.println("6. Delete friendlist tuple");
			System.out.println("7. Delete in-rank tuple");
			System.out.println("8. Delete in-team-use tuple");
			System.out.println("9. Delete item tuple");
			System.out.println("10. Delete pvp-match tuple");
			System.out.println("11. Delete rank tuple");
			System.out.println("12. Delete role tuple");
			System.out.println("13. Delete runes tuple");
			System.out.println("14. Delete new summoner spell tuple");
			System.out.println("15. Delete new team participation tuple");
			System.out.println("16. Go Back to Main Menu");
			System.out.println("17. Quit");
			System.out.print("Please select an option from 1 to 17: ");
			choice = readInteger(false);

			System.out.println(" ");

			switch (choice) {
				case 1:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					try {
						handleAccountDeletion(accountID,accountName);
						System.out.println("Deleted account!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 2:
					System.out.print("Enter champion name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();
					System.out.print("Enter team color: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					teamColor = bufferedReader.readLine();
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();
					try {
						handleAccountPlayDeletion(championName, teamColor, accountID, accountName, matchID);
						System.out.println("You successfully deleted a account play tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 3:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					blacklistedName = bufferedReader.readLine();
					try {
						handleBlacklistDeletion(accountID, accountName);
						System.out.println("You successfully deleted a blacklist tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 4:
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();
					try {
						handleBotMatchDeletion(matchID);
						System.out.println("You successfully deleted a bot match tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 5:
					System.out.print("Enter champion name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();
					try {
						handleChampionDeletion(championName);
						System.out.println("You successfully deleted a champion tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 6:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					try {
						handleFriendlistDeletion(accountID, accountName);
						System.out.println("You successfully deleted a friendlist tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 7:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					try {
						handleInRankDeletion(accountID);
						System.out.println("You successfully deleted a in-rank tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 8:
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();

					try {
						handleInTeamUseDeletion(matchID);
						System.out.println("You successfully deleted a in-team-use tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 9:
					System.out.print("Enter item name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					itemName = bufferedReader.readLine();
					try {
						handleItemDeletion(itemName);
						System.out.println("You successfully deleted a item tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 10:
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();

					try {
						handlePVPMatchDeletion(matchID);
						System.out.println("You successfully deleted a pvp-match tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();//
					}
					break;
				case 11:
					System.out.print("Enter rank number: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankNumber = bufferedReader.readLine();
					try {
						handleRankInsertion(new Rank(rankNumber, rankTier));
						System.out.println("You successfully added a new rank tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 12:
//					System.out.print("Enter role name: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					roleName = bufferedReader.readLine();
//					System.out.print("Enter champion name: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					championName = bufferedReader.readLine();
//					try {
//						handleRoleInsertion(new Role(roleName, championName));
//						System.out.println("You successfully added a new role tuple!");
//					} catch (SQLException e) {
//						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
//						connection.rollback();
//					}
//					break;
//				case 13:
//					System.out.print("Enter runes primary path: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					runesPrimaryPath = bufferedReader.readLine();
//					System.out.print("Enter runes secondary path: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					runesSecondaryPath = bufferedReader.readLine();
//					System.out.print("Enter keystone: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					keystone = bufferedReader.readLine();
//					try {
//						handleRunesInsertion(new Runes(runesPrimaryPath, runesSecondaryPath, keystone));
//						System.out.println("You successfully added a new runes tuple!");
//					} catch (SQLException e) {
//						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
//						connection.rollback();
//					}
//					break;
//				case 14:
//					System.out.print("Enter summoner spell name: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					summonerSpellName = bufferedReader.readLine();
//					System.out.print("Enter summoner name description: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					summonerSpellNameDescription = bufferedReader.readLine();
//					try {
//						handleSummonerSpellInsertion(new SummonerSpell(summonerSpellName, summonerSpellNameDescription));
//						System.out.println("You successfully added a new summoner spell tuple!");
//					} catch (SQLException e) {
//						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
//						connection.rollback();
//					}
//					break;
//				case 15:
//					System.out.print("Enter match id: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					matchID = bufferedReader.readLine();
//					System.out.print("Enter champion name: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					championName = bufferedReader.readLine();
//					System.out.print("Enter team color: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					teamColor = bufferedReader.readLine();
//					System.out.print("Enter first tower take down in second: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					firstTowerTakeDownInSecond = readInteger(false);
//					System.out.print("Enter first dragon in second: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					firstDragonInSecond = readInteger(false);
//					System.out.print("Enter in-team stats: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					inTeamStats = readInteger(false);
//					System.out.print("Enter participation winner: ");
//					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//					participationWinner = bufferedReader.readLine();
//					try {
//						handleTeamParticipationInsertion(new TeamParticipation(matchID, championName, teamColor, firstTowerTakeDownInSecond, firstDragonInSecond, inTeamStats, participationWinner));
//						System.out.println("You successfully added a new team participation tuple!");
//					} catch (SQLException e) {
//						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
//						connection.rollback();
//					}
//					break;
				case 16:
					showMainMenu();
				case 17:
					return;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
			}
		}
	}



	public void insertion() throws IOException, SQLException {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;

		while (choice != 17) {
			System.out.println();
			System.out.println("1. INSERT a new account tuple");
			System.out.println("2. INSERT a new account play tuple");
			System.out.println("3. INSERT a new blacklist tuple");
			System.out.println("4. INSERT a new bot-match tuple");
			System.out.println("5. INSERT a new champion tuple");
			System.out.println("6. INSERT a new friendlist tuple");
			System.out.println("7. INSERT a new in-rank tuple");
			System.out.println("8. INSERT a new in-team-use tuple");
			System.out.println("9. INSERT a new item tuple");
			System.out.println("10. INSERT a new pvp-match tuple");
			System.out.println("11. INSERT a new rank tuple");
			System.out.println("12. INSERT a new role tuple");
			System.out.println("13. INSERT a new runes tuple");
			System.out.println("14. INSERT a new summoner spell tuple");
			System.out.println("15. INSERT a new team participation tuple");
			System.out.println("16. Go Back to Main Menu");
			System.out.println("17. Quit");
			System.out.print("Please select an option from 1 to 17: ");
			choice = readInteger(false);

			System.out.println(" ");

			switch (choice) {
				case 1:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					System.out.print("Enter account stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountStats = readFloat(false);
					System.out.print("Enter account level: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountLevel = readInteger(false);
					System.out.print("Enter account status: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountStatus = bufferedReader.readLine();
					try {
						handleAccountInsertion(new Account(accountID, accountName, accountStats, accountLevel, accountStatus));
						System.out.println("You successfully added a new account tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 2:
					System.out.print("Enter champion name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();
					System.out.print("Enter team color: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					teamColor = bufferedReader.readLine();
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();
					System.out.print("Enter play stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					playStats = readFloat(false);
					try {
						handleAccountPlayInsertion(new AccountPlay(championName, teamColor, accountID, accountName, matchID, playStats));
						System.out.println("You successfully added a new account play tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 3:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					System.out.print("Enter blacklisted id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					blacklistedID = bufferedReader.readLine();
					System.out.print("Enter blacklisted name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					blacklistedName = bufferedReader.readLine();
					try {
						handleBlacklistInsertion(new Blacklist(accountID, accountName, blacklistedID, blacklistedName));
						System.out.println("You successfully added a new blacklist tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 4:
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();
					System.out.print("Enter match map: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchMap = bufferedReader.readLine();
					System.out.print("Enter match start time(e.g. 9:00am): ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchStartTime = bufferedReader.readLine();
					System.out.print("Enter match end time(e.g. 9:00am): ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchEndTime = bufferedReader.readLine();
					System.out.print("Enter difficulty: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					difficulty = bufferedReader.readLine();
					System.out.print("Enter curses: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					String booleanString = bufferedReader.readLine();
					if (booleanString.equals("false")) {
						curses = false;
					} else if (booleanString.equals("true")) {
						curses = true;
					}
					try {
						handleBotMatchInsertion(new BotMatch(matchID, matchMap, matchStartTime, matchEndTime, difficulty, curses));
						System.out.println("You successfully added a new bot match tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 5:
					System.out.print("Enter champion name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();
					System.out.print("Enter champion type: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championType = bufferedReader.readLine();
					System.out.print("Enter champion stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championStats = readInteger(false);
					try {
						handleChampionInsertion(new Champion(championName, championType, championStats));
						System.out.println("You successfully added a new champion tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 6:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					System.out.print("Enter friendlisted id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					friendlistedID = bufferedReader.readLine();
					System.out.print("Enter friendlisted name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					friendlistedName = bufferedReader.readLine();
					try {
						handleFriendlistInsertion(new Friendlist(accountID, accountName, friendlistedID, friendlistedName));
						System.out.println("You successfully added a new friendlist tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 7:
					System.out.print("Enter account id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountID = bufferedReader.readLine();
					System.out.print("Enter account name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					accountName = bufferedReader.readLine();
					System.out.print("Enter rank number: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankNumber = bufferedReader.readLine();
					System.out.print("Enter rank tier: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankTier = bufferedReader.readLine();
					try {
						handleInRankInsertion(new InRank(accountID, accountName, rankNumber, rankTier));
						System.out.println("You successfully added a new in-rank tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 8:
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();
					System.out.print("Enter team color: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					teamColor = bufferedReader.readLine();
					System.out.print("Enter champion name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();
					System.out.print("Enter item name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					itemName = bufferedReader.readLine();
					System.out.print("Enter summoner spell name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					summonerSpellName = bufferedReader.readLine();
					System.out.print("Enter primary path: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					primaryPath = bufferedReader.readLine();
					System.out.print("Enter secondary path: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					secondaryPath = bufferedReader.readLine();
					try {
						handleInTeamUseInsertion(new InTeamUse(accountID, teamColor, championName, itemName, summonerSpellName, primaryPath, secondaryPath));
						System.out.println("You successfully added a new in-team-use tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 9:
					System.out.print("Enter item name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					itemName = bufferedReader.readLine();
					System.out.print("Enter item description: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					itemDescription = bufferedReader.readLine();
					try {
						handleItemInsertion(new Item(itemName, itemDescription));
						System.out.println("You successfully added a new item tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 10:
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();
					System.out.print("Enter match map: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchMap = bufferedReader.readLine();
					System.out.print("Enter match start time(e.g. 9:00am): ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchStartTime = bufferedReader.readLine();
					System.out.print("Enter match end time(e.g. 9:00am): ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchEndTime = bufferedReader.readLine();
					System.out.print("Enter rank: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rank = readInteger(false);

					try {
						handlePVPMatchInsertion(new PVPMatch(matchID, matchMap, matchStartTime, matchEndTime, rank));
						System.out.println("You successfully added a new pvp-match tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 11:
					System.out.print("Enter rank number: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankNumber = bufferedReader.readLine();
					System.out.print("Enter rank tier: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					rankTier = bufferedReader.readLine();
					try {
						handleRankInsertion(new Rank(rankNumber, rankTier));
						System.out.println("You successfully added a new rank tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 12:
					System.out.print("Enter role name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					roleName = bufferedReader.readLine();
					System.out.print("Enter champion name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();
					try {
						handleRoleInsertion(new Role(roleName, championName));
						System.out.println("You successfully added a new role tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 13:
					System.out.print("Enter runes primary path: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					runesPrimaryPath = bufferedReader.readLine();
					System.out.print("Enter runes secondary path: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					runesSecondaryPath = bufferedReader.readLine();
					System.out.print("Enter keystone: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					keystone = bufferedReader.readLine();
					try {
						handleRunesInsertion(new Runes(runesPrimaryPath, runesSecondaryPath, keystone));
						System.out.println("You successfully added a new runes tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 14:
					System.out.print("Enter summoner spell name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					summonerSpellName = bufferedReader.readLine();
					System.out.print("Enter summoner name description: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					summonerSpellNameDescription = bufferedReader.readLine();
					try {
						handleSummonerSpellInsertion(new SummonerSpell(summonerSpellName, summonerSpellNameDescription));
						System.out.println("You successfully added a new summoner spell tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 15:
					System.out.print("Enter match id: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					matchID = bufferedReader.readLine();
					System.out.print("Enter champion name: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					championName = bufferedReader.readLine();
					System.out.print("Enter team color: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					teamColor = bufferedReader.readLine();
					System.out.print("Enter first tower take down in second: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					firstTowerTakeDownInSecond = readInteger(false);
					System.out.print("Enter first dragon in second: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					firstDragonInSecond = readInteger(false);
					System.out.print("Enter in-team stats: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					inTeamStats = readInteger(false);
					System.out.print("Enter participation winner: ");
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					participationWinner = bufferedReader.readLine();
					try {
						handleTeamParticipationInsertion(new TeamParticipation(matchID, championName, teamColor, firstTowerTakeDownInSecond, firstDragonInSecond, inTeamStats, participationWinner));
						System.out.println("You successfully added a new team participation tuple!");
					} catch (SQLException e) {
						System.out.println(Database.EXCEPTION_TAG + " " + e.getMessage());
						connection.rollback();
					}
					break;
				case 16:
					showMainMenu();
				case 17:
					return;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
			}
		}
	}



	/**
	 * Displays simple text interface
	 */
	public void selection() {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;

		while (choice != 13) {
			System.out.println();
			System.out.println("1. Champions who used Two hundred Items");
			System.out.println("2. List of Matches starting at Nine AM");
			System.out.println("3. Account with at least Ten Friends and five blacklists");
			System.out.println("4. Online Status");
			System.out.println("5. Participated in Red Team Ten TImes");
			System.out.println("6. Twenty Account Won Twenty Hard Matches At Three PM");
			System.out.println("7. Never Participated in any Match");
			System.out.println("8. Name of Champion Who only participiates in Red Team");
			System.out.println("9. Name of never used Champion");
			System.out.println("10. Item that has never been used");
			System.out.println("11. Matches that use the curses");
			System.out.println("12. All accounts gold rank and above");
			System.out.println("13. Quit");

			choice = readInteger(false);

			System.out.println(" ");

			switch (choice) {
				case 1:
					handleFirstQuery();
					break;
				case 2:
					handleSecondQuery();
					break;
				case 3:
					handleThirdQuery();
					break;
				case 4:
					handleFourthQuery();
					break;
				case 5:
					handleFifthQuery();
					break;
				case 6:
					handleSixthQuery();
					break;
				case 7:
					handleSeventhQuery();
					break;
				case 8:
					handleEighthQuery();
					break;
				case 9:
					handleNinthQuery();
					break;
				case 10:
					handleTenthQuery();
					break;
				case 11:
					handleEleventhQuery();
					break;
				case 12:
					handleTwelfthQuery();
					break;
				case 13:
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
			}
		}

	}

	private void handleTeamParticipationUpdates(TeamParticipation teamParticipation) throws SQLException{
		TeamParticipationDatabase teamParticipationDatabase = new TeamParticipationDatabase();
		connection = teamParticipationDatabase.getConnection();
		teamParticipationDatabase.updateTeamParticipationDatabaseTuple(teamParticipation);
		teamParticipationDatabase.close();
	}



	private void handleSummonerSpellUpdates(SummonerSpell summonerSpell) throws SQLException {
		SummonerSpellDatabase summonerSpellDatabase = new SummonerSpellDatabase();
		connection = summonerSpellDatabase.getConnection();
		summonerSpellDatabase.updateSummonerSpellDatabaseTuple(summonerSpell);
		summonerSpellDatabase.close();
	}

	private void handleRunesUpdates(Runes runes) throws SQLException {
		RunesDatabase runesDatabase = new RunesDatabase();
		connection = runesDatabase.getConnection();
		runesDatabase.updateRunesDatabaseTuple(runes);
		runesDatabase.close();
	}

	private void handleRoleUpdates(Role role) throws SQLException {
		RoleDatabase roleDatabase = new RoleDatabase();
		connection = roleDatabase.getConnection();
		roleDatabase.updateRoleDatabaseTuple(role);
		roleDatabase.close();
	}

	private void handleRankUpdates(Rank rank) throws SQLException {
		RankDatabase rankDatabase = new RankDatabase();
		connection = rankDatabase.getConnection();
		rankDatabase.updateRankDatabaseTuple(rank);
		rankDatabase.close();
	}


	private void handlePVPMatchUpdates(PVPMatch pvpMatch) throws SQLException {
		PVPMatchDatabase pvpMatchDatabase = new PVPMatchDatabase();
		connection = pvpMatchDatabase.getConnection();
		pvpMatchDatabase.updatePVPMatchDatabaseTuple(pvpMatch);
		pvpMatchDatabase.close();
	}

	private void handleItemUpdates(Item item) throws SQLException {
		ItemDatabase itemDatabase = new ItemDatabase();
		connection = itemDatabase.getConnection();
		itemDatabase.updateItemDatabaseTuple(item);
		itemDatabase.close();
	}

	private void handleInRankUpdates(InRank inRank) throws SQLException {
		InRankDatabase inRankDatabase = new InRankDatabase();
		connection = inRankDatabase.getConnection();
		inRankDatabase.updateInRankDatabaseTuple(inRank);
		inRankDatabase.close();
	}


	private void handleFriendlistUpdates(Friendlist friendlist) throws SQLException {
		FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
		connection = friendlistDatabase.getConnection();
		friendlistDatabase.updateFriendlistDatabaseTuple(friendlist);
		friendlistDatabase.close();
	}

	private void handleChampionUpdates(Champion champion) throws SQLException {
		ChampionDatabase championDatabase = new ChampionDatabase();
		connection = championDatabase.getConnection();
		championDatabase.updateChampionDatabaseTuple(champion);
		championDatabase.close();
	}

	private void handleBotMatchUpdates(BotMatch botMatch) throws SQLException {
		BotMatchDatabase botMatchDatabase = new BotMatchDatabase(this.username, this.password);
		connection = botMatchDatabase.getConnection();
		botMatchDatabase.updateBotMatchDatabaseTuple(botMatch);
		botMatchDatabase.close();
	}

	private void handleBlacklistUpdates(Blacklist blacklist) throws SQLException {
		BlacklistDatabase blacklistListDatabase = new BlacklistDatabase();
		connection = blacklistListDatabase.getConnection();
		blacklistListDatabase.updateBlacklistDatabaseTuple(blacklist);
		blacklistListDatabase.close();
	}

	private void handleAccountPlayUpdates(AccountPlay accountPlay) throws SQLException {
		AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase();
		connection = accountPlayDatabase.getConnection();
		accountPlayDatabase.updateAccountPlayDatabaseTuple(accountPlay);
		accountPlayDatabase.close();
	}


	private void handleAccountUpdates(Account account) throws SQLException {
		AccountDatabase accountDatabase = new AccountDatabase();
		connection = accountDatabase.getConnection();
		accountDatabase.updateAccountDatabaseTuple(account);
		accountDatabase.close();
	}

	private void handleInTeamUseInsertion(InTeamUse inTeamUse) throws SQLException {
		InTeamUseDatabase inTeamUseDatabase = new InTeamUseDatabase();
		connection = inTeamUseDatabase.getConnection();
		inTeamUseDatabase.insertInTeamUseTuple(inTeamUse);
		inTeamUseDatabase.close();
	}

	private void handleTeamParticipationInsertion(TeamParticipation teamParticipation) throws SQLException {
		TeamParticipationDatabase teamParticipationDatabase = new TeamParticipationDatabase(this.username, this.password);
		connection = teamParticipationDatabase.getConnection();
		teamParticipationDatabase.insertTeamParticipationTuple(teamParticipation);
		teamParticipationDatabase.close();
	}

	private void handleSummonerSpellInsertion(SummonerSpell summonerSpell) throws SQLException {
		SummonerSpellDatabase summonerSpellDatabase = new SummonerSpellDatabase(this.username, this.password);
		connection = summonerSpellDatabase.getConnection();
		summonerSpellDatabase.insertSummonerSpellTuple(summonerSpell);
		summonerSpellDatabase.close();
	}

	private void handleRunesInsertion(Runes runes) throws SQLException {
		RunesDatabase runesDatabase = new RunesDatabase(this.username, this.password);
		connection = runesDatabase.getConnection();
		runesDatabase.insertRunesTuple(runes);
		runesDatabase.close();
	}

	private void handleRoleInsertion(Role role) throws SQLException {
		RoleDatabase roleDatabase = new RoleDatabase(this.username, this.password);
		connection = roleDatabase.getConnection();
		roleDatabase.insertRolesTuple(role);
		roleDatabase.close();
	}


	private void handlePVPMatchInsertion(PVPMatch pvpMatch) throws SQLException {
		PVPMatchDatabase pvpMatchDatabase = new PVPMatchDatabase(this.username, this.password);
		connection = pvpMatchDatabase.getConnection();
		pvpMatchDatabase.insertPVPMatchTuple(pvpMatch);
		pvpMatchDatabase.close();
	}

	private void handleRankInsertion(Rank rank) throws SQLException {
		RankDatabase rankDatabase = new RankDatabase(this.username, this.password);
		connection = rankDatabase.getConnection();
		rankDatabase.insertRankTuple(rank);
		rankDatabase.close();
	}

	private void handleItemInsertion(Item item) throws SQLException {
		ItemDatabase itemDatabase = new ItemDatabase(this.username, this.password);
		connection = itemDatabase.getConnection();
		itemDatabase.insertItemTuple(item);
		itemDatabase.close();
	}

	private void handleInRankInsertion(InRank inRank) throws SQLException {
		InRankDatabase inRankDatabase = new InRankDatabase(this.username, this.password);
		connection = inRankDatabase.getConnection();
		inRankDatabase.insertInRankTuple(inRank);
		inRankDatabase.close();
	}

	private void handleFriendlistInsertion(Friendlist friendlist) throws SQLException {
		FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
		connection = friendlistDatabase.getConnection();
		friendlistDatabase.insertFriendlistDatabaseTuple(friendlist);
		friendlistDatabase.close();
	}

	private void handleChampionInsertion(Champion champion) throws SQLException {
		ChampionDatabase championDatabase = new ChampionDatabase();
		connection = championDatabase.getConnection();
		championDatabase.insertChampionTuple(champion);
		championDatabase.close();
	}

	private void handleBotMatchInsertion(BotMatch botMatch) throws SQLException {
		BotMatchDatabase botMatchDatabase = new BotMatchDatabase(this.username, this.password);
		connection = botMatchDatabase.getConnection();
		botMatchDatabase.insertBotMatchDatabaseTuple(botMatch);
		botMatchDatabase.close();
	}

	private void handleAccountPlayInsertion(AccountPlay accountPlay) throws SQLException {
		AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase(this.username, this.password);
		connection = accountPlayDatabase.getConnection();
		accountPlayDatabase.insertAccountPlayDatabaseTuple(accountPlay);
		accountPlayDatabase.close();
	}

	public void handleAccountInsertion(Account account) throws SQLException {
		AccountDatabase accountDatabase = new AccountDatabase();
		connection = accountDatabase.getConnection();
		accountDatabase.insertAccountDatabaseTuple(account);
		accountDatabase.close();
	}

	public void handleBlacklistInsertion(Blacklist blacklist) throws SQLException {
		BlacklistDatabase blacklistDatabase = new BlacklistDatabase();
		connection = blacklistDatabase.getConnection();
		blacklistDatabase.insertBlacklistDatabaseTuple(blacklist);
		blacklistDatabase.close();
	}

	public void handleAccountDeletion(String id, String name) throws SQLException {
		AccountDatabase accountDatabase = new AccountDatabase();
		connection = accountDatabase.getConnection();
		accountDatabase.deleteAccountDatabaseTuple(id, name);
		accountDatabase.close();
	}

	public void handleAccountPlayDeletion(String accountID, String accountName, String championName, String teamColor, String matchID) throws SQLException {
		AccountPlayDatabase accountPlayDatabase = new AccountPlayDatabase();
		connection = accountPlayDatabase.getConnection();
		accountPlayDatabase.deleteAccountPlayDatabaseTuple(accountID, accountName, championName, teamColor, matchID);
		accountPlayDatabase.close();
	}

	public void handleBlacklistDeletion(String accountID, String accountName) throws SQLException {
		BlacklistDatabase blacklistDatabase = new BlacklistDatabase();
		connection = blacklistDatabase.getConnection();
		blacklistDatabase.deleteBlacklistDatabaseTuple(accountID, accountName);
		blacklistDatabase.close();
	}

	public void handleBotMatchDeletion(String matchID) throws SQLException {
		BotMatchDatabase botMatchDatabase = new BotMatchDatabase();
		connection = botMatchDatabase.getConnection();
		botMatchDatabase.deleteBotMatchDatabaseTuple(matchID);
		botMatchDatabase.close();
	}

	public void handleChampionDeletion(String championName) throws SQLException {
		ChampionDatabase championDatabase = new ChampionDatabase();
		connection = championDatabase.getConnection();
		championDatabase.deleteChampionDatabaseTuple(championName);
		championDatabase.close();
	}

	public void handleFriendlistDeletion(String accountID, String accountName) throws SQLException {
		FriendlistDatabase friendlistDatabase = new FriendlistDatabase();
		connection = friendlistDatabase.getConnection();
		friendlistDatabase.deleteFriendlistDatabaseTuple(accountID, accountName);
		friendlistDatabase.close();
	}

	public void handleInRankDeletion(String accountID) throws SQLException {
		InRankDatabase inRankDatabase = new InRankDatabase();
		connection = inRankDatabase.getConnection();
		inRankDatabase.deleteInRankDatabaseTuple(accountID);
		inRankDatabase.close();
	}

	public void handleInTeamUseDeletion(String matchID) throws SQLException {
		InTeamUseDatabase inTeamUseDatabase = new InTeamUseDatabase();
		connection = inTeamUseDatabase.getConnection();
		inTeamUseDatabase.deleteInTeamUseDatabaseTuple(matchID);
		inTeamUseDatabase.close();
	}

	public void handleItemDeletion(String itemName) throws SQLException {
		ItemDatabase itemDatabase = new ItemDatabase();
		connection = itemDatabase.getConnection();
		itemDatabase.deleteItemDatabaseTuple(itemName);
		itemDatabase.close();
	}

	public void handlePVPMatchDeletion(String matchID) throws  SQLException{
		PVPMatchDatabase pvpMatchDatabase = new PVPMatchDatabase();
		connection = pvpMatchDatabase.getConnection();
		pvpMatchDatabase.deletePVPMatchDatabaseTuple(matchID);
		pvpMatchDatabase.close();
	}
//
//	public void handleRankDeletion(Rank rank) throws SQLException {
//
//	}
//
//	public void handleRoleDeletion(Role role) throws SQLException {
//
//	}
//
//	public void handleRunesDeletion(Runes runes) throws SQLException {
//
//	}
//
//	public void handleSummonerSpellDeletion(SummonerSpell summonerSpell) throws SQLException {
//
//	}
//
//	public void handleTeamParticipationDeletion(TeamParticipation teamParticipation) throws SQLException {
//
//	}
	private void handleFirstQuery() {
		String[] names = query.getNameOfChampionWhoUsesTwoHundredsItems();
		System.out.println("First Query: ");
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleSecondQuery() {
		String[] matchIDs = query.getListOfMatchesStartingAtNineAM();

		System.out.println("Second Query: ");
		for (int i = 0; i < matchIDs.length; i++) {
			System.out.println(matchIDs[i]);
		}
	}

	private void handleThirdQuery() {
		System.out.println("Third Query: ");
		query.getAccountAtLeastTenFriendsAndFiveBlacklists();
	}

	private void handleFourthQuery() {
		System.out.println("Fourth Query: ");
		String[] names = query.getListOfAccountsOnlineStatus();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleFifthQuery() {
		System.out.println("Fifth Query: ");
		String[] names = query.getAccountParticipatingInRedTeamTenTimes();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleSixthQuery() {
		System.out.println("Sixth Query: ");
		String[] names = query.getAListOfLevelTwentyAccountWonTwentyHardMatchesAtThreePMAndOnlyUseTenItemsAndTenSummonerSpellsAndThreeRunes();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleSeventhQuery() {
		System.out.println("Seventh Query: ");
		String[] names = query.getAListOfTheLvTenAccountsHavingOneHundredFriendsAndZeroBlacklists();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleEighthQuery() {
		System.out.println("Eighth Query: ");
		String[] names = query.getAListOfAccountsHavingNeverParticipatedInAnyMatch();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleNinthQuery() {
		System.out.println("Ninth Query: ");
		String[] names = query.getNameOfTheChampionWhoOnlyParticipatesInTheRedTeam();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleTenthQuery() {
		System.out.println("Tenth Query: ");
		String[] names = query.getItemThatHasNeverBeenUsedByAnyChampionOrAnAccount();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleEleventhQuery() {
		System.out.println("Eleventh Query: ");
		String[] names = query.getMatchesThatUseTheCurses();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleTwelfthQuery() {
		System.out.println("Twelfth Query: ");
		String[] names = query.getAllAccountsThatAreGoldOrAboveInRank();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

	private void handleQuitOption() {
		System.out.println("Good Bye!");

		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}

		delegate.terminalTransactionsFinished();
	}

	private int readInteger(boolean allowEmpty) {
		String line = null;
		int input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Integer.parseInt(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}

	private float readFloat(boolean allowEmpty) {
		String line = null;
		float input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Float.parseFloat(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}

}
