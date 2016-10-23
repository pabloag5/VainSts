package dev.kodama.test.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.GameStats;
import dev.kodama.test.utils.SummaryStats;
import dev.kodama.test.utils.HalcyonUtils;

/**
 * Created by researcher on 12/05/16.
 */
public class Database_Helper extends SQLiteOpenHelper {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "database.db";

    private static Database_Helper sInstance;


    /**
     * Initializer of the Database_Helper
     * @param context Any context of the application
     * @return An instance of the Database_Helper
     */
    public static synchronized Database_Helper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new Database_Helper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor: use {@link #getInstance(Context)} instead of the constructor
     * @param context Any context of the application
     * @see #getInstance(Context)
     */
    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates all the databases of the application
     * @param db An instance of the {@link Database_Helper} from {@link #getInstance(Context)}
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(DatabaseContract.Heroes.CREATE_TABLE);
//        db.execSQL(DatabaseContract.Players.CREATE_TABLE);
//        db.execSQL(DatabaseContract.Teams.CREATE_TABLE);
//        db.execSQL(DatabaseContract.Players_Teams.CREATE_TABLE);
        db.execSQL(DatabaseContract.Games.CREATE_TABLE);
        db.execSQL(DatabaseContract.Player_Results.CREATE_TABLE);
        db.execSQL(DatabaseContract.Statistics.CREATE_TABLE);

//        insertHeroes(db);
    }

    /**
     * Used to update the version of the Database. Copies all the old information and migrates it to the new structure
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param oldVersion Integer identifying the old version
     * @param newVersion Integer identifying the new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(DatabaseContract.Player_Results.DELETE_TABLE);
//        db.execSQL(DatabaseContract.Games.DELETE_TABLE);
//        db.execSQL(DatabaseContract.Players_Teams.DELETE_TABLE);
//        db.execSQL(DatabaseContract.Teams.DELETE_TABLE);
//        db.execSQL(DatabaseContract.Players.DELETE_TABLE);
//        db.execSQL(DatabaseContract.Heroes.DELETE_TABLE);
//        db.execSQL(DatabaseContract.Statistics.DELETE_TABLE);
//        onCreate(db);
    }

    /**
     * Get the summary stats of a specific total_type and game_type by accessing the database {@link DatabaseContract.Statistics}
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param total_type One of {@link Constants.Statistics_DB.Totals}
     * @param game_type One of {@link Constants.Game_Types}
     * @return An instance of {@link SummaryStats} with the summary of the stats for those games
     * @see SummaryStats
     */
    public SummaryStats getTotalStats(SQLiteDatabase db, String total_type, int game_type) {

        int stats_type = SummaryStats.TYPE_TOTAL;

        int position = HalcyonUtils.getPositionFromStatistics_DB_type(total_type);

        String tableName = DatabaseContract.Statistics.TABLE_NAME;
        String[] tableColumns = null;
        String whereClause = DatabaseContract.Statistics.TYPE + " = ? AND " +
                DatabaseContract.Statistics.GAME_TYPE + " = ?";
        String[] whereArgs = new String[] {
                total_type,
                game_type+""
        };

        Cursor c = db.query(tableName,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null
                );
        if(c!=null && c.getCount()>0){
            c.moveToFirst();
            int wins = c.getInt(c.getColumnIndex(DatabaseContract.Statistics.WINS));
            int total_games = c.getInt(c.getColumnIndex(DatabaseContract.Statistics.TOTAL_GAMES));
            float kills_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KILLS_PER_GAME));
            float deaths_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.DEATHS_PER_GAME));
            float assists_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.ASSISTS_PER_GAME));
            float cs_min_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.CS_MIN_PER_GAME));
            float gold_min_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.GOLD_MIN_PER_GAME));
            float gold_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.GOLD_PER_GAME));
            float kda_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KDA_PER_GAME));
            float kill_participation_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KILL_PARTICIPATION_PER_GAME));

            int heroId = Constants.Heroes.Ids.NO_HERO;
            return new SummaryStats(wins, total_games, kills_per_game, deaths_per_game, assists_per_game, cs_min_per_game, gold_min_per_game, gold_per_game, kda_per_game, kill_participation_per_game, position, heroId, stats_type, game_type);
        } else {
            return null;
        }
    }

    /**
     * Gets statistics of all heroes filtered by heroe_type and game_type
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param heroe_type One of {@link Constants.Statistics_DB.Heroes}
     * @param game_type One of {@link Constants.Game_Types}
     * @return An {@link ArrayList} of {@link SummaryStats} with the summary of the stats of each heroe
     * @see SummaryStats
     */
    public ArrayList<SummaryStats> getHeroeStats(SQLiteDatabase db, String heroe_type, int game_type) {

        int stats_type = SummaryStats.TYPE_HERO;

        int position = HalcyonUtils.getPositionFromStatistics_DB_type(heroe_type);

        String tableName = DatabaseContract.Statistics.TABLE_NAME;
        String[] tableColumns = null;
        String whereClause = DatabaseContract.Statistics.TYPE + " = ? AND " +
            DatabaseContract.Statistics.GAME_TYPE + " = ?";
        String[] whereArgs = new String[] {
                heroe_type,
                game_type+""
        };
        Cursor c = db.query(tableName,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        ArrayList<SummaryStats> statsArray = new ArrayList<>();
        if(c!=null && c.getCount()>0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int wins = c.getInt(c.getColumnIndex(DatabaseContract.Statistics.WINS));
                int total_games = c.getInt(c.getColumnIndex(DatabaseContract.Statistics.TOTAL_GAMES));
                float kills_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KILLS_PER_GAME));
                float deaths_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.DEATHS_PER_GAME));
                float assists_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.ASSISTS_PER_GAME));
                float cs_min_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.CS_MIN_PER_GAME));
                float gold_min_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.GOLD_MIN_PER_GAME));
                float gold_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.GOLD_PER_GAME));
                float kda_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KDA_PER_GAME));
                float kill_participation_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KILL_PARTICIPATION_PER_GAME));

                int heroId = c.getInt(c.getColumnIndex(DatabaseContract.Statistics.SECOND_TYPE));
                statsArray.add(new SummaryStats(wins, total_games, kills_per_game, deaths_per_game, assists_per_game, cs_min_per_game, gold_min_per_game, gold_per_game, kda_per_game, kill_participation_per_game, position, heroId, stats_type, game_type));
                c.moveToNext();
            }

            return statsArray;
        }
        else {
            return null;
        }
    }

    /**
     * Get statistics of a specific heroe
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param heroId The id of the heroe from {@link Constants.Heroes.Ids}
     * @param game_type One of {@link Constants.Game_Types}
     * @return An {@link ArrayList} of {@link SummaryStats} with the summary of statistics for one hero overall and in each position
     * @see SummaryStats
     */
    public ArrayList<SummaryStats> getSpecificHeroeStats(SQLiteDatabase db, int heroId, int game_type) {

        int stats_type = SummaryStats.TYPE_HERO;

        String tableName = DatabaseContract.Statistics.TABLE_NAME;
        String[] tableColumns = null;
        String whereClause = DatabaseContract.Statistics.SECOND_TYPE + " = ? AND "+
                DatabaseContract.Statistics.GAME_TYPE + " = ?";
        String[] whereArgs = new String[] {
                heroId+"",
                game_type+""
        };
        Cursor c = db.query(tableName,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        ArrayList<SummaryStats> statsArray = new ArrayList<>();
        if(c!=null && c.getCount()>0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int wins = c.getInt(c.getColumnIndex(DatabaseContract.Statistics.WINS));
                int total_games = c.getInt(c.getColumnIndex(DatabaseContract.Statistics.TOTAL_GAMES));
                float kills_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KILLS_PER_GAME));
                float deaths_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.DEATHS_PER_GAME));
                float assists_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.ASSISTS_PER_GAME));
                float cs_min_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.CS_MIN_PER_GAME));
                float gold_min_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.GOLD_MIN_PER_GAME));
                float gold_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.GOLD_PER_GAME));
                float kda_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KDA_PER_GAME));
                float kill_participation_per_game = c.getFloat(c.getColumnIndex(DatabaseContract.Statistics.KILL_PARTICIPATION_PER_GAME));

                int position = HalcyonUtils.getPositionFromStatistics_DB_type(c.getString(c.getColumnIndex(DatabaseContract.Statistics.TYPE)));

                statsArray.add(new SummaryStats(wins, total_games, kills_per_game, deaths_per_game, assists_per_game, cs_min_per_game, gold_min_per_game, gold_per_game, kda_per_game, kill_participation_per_game, position, heroId, stats_type, game_type));
                c.moveToNext();
            }

            return statsArray;
        }
        else {
            return null;
        }
    }

    /**
     * Get statistics ({@link GameStats}) for the latest number of games the user wants
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param game_type One of {@link Constants.Game_Types}
     * @param numberOfGames Number of games wanted
     * @param qHeroId Id of the hero wanted (one of {@link Constants.Heroes.Ids}) may be null, in which case, the parameter will be ignored
     * @param qPosition Position played wanted (one of {@link Constants.Positions}) may be null, in which case, the parameter will be ignored
     * @param startTime The start date (unixtime milliseconds since 1970) from which games want to be filtered
     * @param endTime The end date (unixtime milliseconds since 1970) until games want to be filtered
     * @return An {@link ArrayList} of {@link GameStats} with all the statistics of the games queried
     * @see GameStats
     */
    public ArrayList<GameStats> getLastNGamesStats(SQLiteDatabase db, int game_type, int numberOfGames, Integer qHeroId, Integer qPosition, Long startTime, Long endTime) {
        ArrayList<GameStats> statsArray = new ArrayList<>();

        String gameTableName = DatabaseContract.Games.TABLE_NAME;
        String playerResultsTableName = DatabaseContract.Player_Results.TABLE_NAME;

        String tableName = gameTableName + " INNER JOIN " + playerResultsTableName + " ON " + gameTableName + "."
                + DatabaseContract.Games._ID + " = " + playerResultsTableName + "." + DatabaseContract.Player_Results.GAME_ID;

        String[] tableColumns = null;

        String whereClause = DatabaseContract.Games.GAME_TYPE + " = ?";
        ArrayList<String> whereArgsArrayList = new ArrayList<>();
        whereArgsArrayList.add(game_type+"");

        if(qHeroId!=null) {
            whereClause += " AND " + DatabaseContract.Player_Results.HERO + " = ?";
            whereArgsArrayList.add(qHeroId+"");
        }

        if(qPosition!=null) {
            whereClause += " AND " + DatabaseContract.Player_Results.POSITION + " = ?";
            whereArgsArrayList.add(qPosition+"");
        }

        if(startTime!=null) {
            whereClause += " AND " + DatabaseContract.Games.TIMESTAMP + " > ?";
            whereArgsArrayList.add(startTime+"");
        }

        if(endTime!=null) {
            whereClause += " AND " + DatabaseContract.Games.TIMESTAMP + " < ?";
            whereArgsArrayList.add(endTime+"");
        }

        String orderByClause = DatabaseContract.Games.TIMESTAMP + " DESC";

        String[] whereArgs = new String[ whereArgsArrayList.size()];
        whereArgsArrayList.toArray(whereArgs);

        Cursor c = db.query(tableName,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                orderByClause,
                numberOfGames+""
        );
        if(c!=null && c.getCount()>0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                long timestamp = c.getLong(c.getColumnIndex(DatabaseContract.Games.TIMESTAMP));
                boolean victory = (c.getInt(c.getColumnIndex(DatabaseContract.Games.VICTORY)) == 1);
                int heroId = c.getInt(c.getColumnIndex(DatabaseContract.Player_Results.HERO));
                int position = c.getInt(c.getColumnIndex(DatabaseContract.Player_Results.POSITION));
                int teamKills = c.getInt(c.getColumnIndex(DatabaseContract.Games.TOTAL_KILLS));
                int teamDeaths = c.getInt(c.getColumnIndex(DatabaseContract.Games.TOTAL_DEATHS));
                int kills = c.getInt(c.getColumnIndex(DatabaseContract.Player_Results.KILLS));
                int deaths = c.getInt(c.getColumnIndex(DatabaseContract.Player_Results.DEATHS));
                int assists = c.getInt(c.getColumnIndex(DatabaseContract.Player_Results.ASSISTS));
                int cs = c.getInt(c.getColumnIndex(DatabaseContract.Player_Results.CS));
                float cs_per_min = c.getFloat(c.getColumnIndex(DatabaseContract.Player_Results.CS_MIN));
                int gold = c.getInt(c.getColumnIndex(DatabaseContract.Player_Results.GOLD));
                float kda = c.getFloat(c.getColumnIndex(DatabaseContract.Player_Results.KDA_RATIO));
                float killParticipation = c.getFloat(c.getColumnIndex(DatabaseContract.Player_Results.KILL_PARTICIPATION));
                float length = c.getFloat(c.getColumnIndex(DatabaseContract.Games.LENGTH));

                statsArray.add(new GameStats(timestamp, victory,heroId, position, game_type, teamKills, teamDeaths, kills, deaths, assists, cs, cs_per_min, gold, kda, killParticipation, length));
                c.moveToNext();
            }

            return statsArray;
        }
        else {
            return null;
        }
    }

    /**
     * Add a game to the database and update the summary statistics
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param game Filled instance of {@link Game}
     * @see #addGame(SQLiteDatabase, Game)
     * @see #addPlayerResults(SQLiteDatabase, Player_Results, long)
     * @see #updateTotalStats(SQLiteDatabase, Game, String, int)
     * @see #updateHeroStats(SQLiteDatabase, Game, int)
     */
    public void addNewGame(SQLiteDatabase db, Game game) {
        long game_id = addGame(db, game);
        addPlayerResults(db, game.getResults(), game_id);

        String totalDBPosition = HalcyonUtils.getTotalStatistics_DBTypeFromPosition(game.getResults().getPosition());


        updateTotalStats(db, game, Constants.Statistics_DB.Totals.TOTAL_ALL, game.getGame_type());
        updateTotalStats(db, game, totalDBPosition, game.getGame_type());
        updateHeroStats(db, game, game.getGame_type());

    }

    /**
     * Insert {@link Game} in database
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param game Filled instance of {@link Game}
     * @return Id of the game inserted in the database
     */
    private long addGame(SQLiteDatabase db, Game game) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Games.TIMESTAMP, System.currentTimeMillis());
        values.put(DatabaseContract.Games.LENGTH, game.getLength());
        values.put(DatabaseContract.Games.VICTORY, game.isVictory());
        values.put(DatabaseContract.Games.GAME_TYPE, game.getGame_type());
        values.put(DatabaseContract.Games.TOTAL_KILLS, game.getTotalKills());
        values.put(DatabaseContract.Games.TOTAL_DEATHS, game.getTotalDeaths());

    // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DatabaseContract.Games.TABLE_NAME,
                null,
                values);

        return newRowId;

    }

    /**
     * Insert {@link Player_Results} in database
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param results Filled instance of {@link Player_Results}
     * @param game_id Id of the game this results belong to
     * @return Id of the row inserted in the database
     */
    private long addPlayerResults(SQLiteDatabase db, Player_Results results, long game_id) {

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Player_Results.HERO, results.getHero());
        values.put(DatabaseContract.Player_Results.GAME_ID, game_id);
        values.put(DatabaseContract.Player_Results.POSITION, results.getPosition());
        values.put(DatabaseContract.Player_Results.KILLS, results.getKills());
        values.put(DatabaseContract.Player_Results.DEATHS, results.getDeaths());
        values.put(DatabaseContract.Player_Results.ASSISTS, results.getAssists());
        values.put(DatabaseContract.Player_Results.CS, results.getCs());
        values.put(DatabaseContract.Player_Results.GOLD, results.getGold());
        values.put(DatabaseContract.Player_Results.KDA_RATIO, results.getKda_ratio());
        values.put(DatabaseContract.Player_Results.CS_MIN, results.getCs_min());
        values.put(DatabaseContract.Player_Results.GOLD_MIN, results.getGold_min());
        values.put(DatabaseContract.Player_Results.KILL_PARTICIPATION, results.getKill_participation());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DatabaseContract.Player_Results.TABLE_NAME,
                null,
                values);

        return newRowId;
    }

    /**
     * Update {@link DatabaseContract.Statistics} table for the total statistics
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param game Instance of {@link Game}
     * @param total_type One of {@link Constants.Statistics_DB.Totals}
     * @param game_type One of {@link Constants.Game_Types}
     * @return Number of rows affected
     */
    private long updateTotalStats(SQLiteDatabase db, Game game, String total_type, int game_type) {

        SummaryStats totalStats = getTotalStats(db, total_type, game_type);
        int total_games = 0;
        int wins = 0;

        float kills_per_game;
        float deaths_per_game;
        float assists_per_game;
        float cs_min_per_game;
        float gold_min_per_game;
        float gold_per_game;
        float kda_per_game;
        float kill_participation_per_game;

        if(totalStats!=null){
            int prev_total_games = totalStats.getTotal_games();
            total_games = prev_total_games + 1;
            wins = totalStats.getWins();
            if(game.isVictory()){
                wins++;
            }

            kills_per_game = (float)(totalStats.getKills_per_game()*prev_total_games + game.getResults().getKills())/total_games;
            deaths_per_game = (float)(totalStats.getDeaths_per_game()*prev_total_games + game.getResults().getDeaths())/total_games;
            assists_per_game = (float)(totalStats.getAssists_per_game()*prev_total_games + game.getResults().getAssists())/total_games;
            cs_min_per_game = (float)(totalStats.getCs_min_per_game()*prev_total_games + game.getResults().getCs_min())/total_games;
            gold_min_per_game = (float)(totalStats.getGold_min_per_game()*prev_total_games + game.getResults().getGold_min())/total_games;
            gold_per_game = (float)(totalStats.getGold_per_game()*prev_total_games + game.getResults().getGold())/total_games;
            kda_per_game = (float)(totalStats.getKda_per_game()*prev_total_games + game.getResults().getKda_ratio())/total_games;
            kill_participation_per_game = (float)(totalStats.getKill_participation_per_game()*prev_total_games + game.getResults().getKill_participation())/total_games;
        }
        else {
            total_games = 1;
            if(game.isVictory()){
                wins = 1;
            }
            kills_per_game = game.getResults().getKills();
            deaths_per_game = game.getResults().getDeaths();
            assists_per_game = game.getResults().getAssists();
            cs_min_per_game = game.getResults().getCs_min();
            gold_min_per_game = game.getResults().getGold_min();
            gold_per_game = game.getResults().getGold();
            kda_per_game = game.getResults().getKda_ratio();
            kill_participation_per_game = game.getResults().getKill_participation();
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Statistics.SECOND_TYPE, -1);
        values.put(DatabaseContract.Statistics.WINS, wins);
        values.put(DatabaseContract.Statistics.TOTAL_GAMES, total_games);
        values.put(DatabaseContract.Statistics.KILLS_PER_GAME, kills_per_game);
        values.put(DatabaseContract.Statistics.DEATHS_PER_GAME, deaths_per_game);
        values.put(DatabaseContract.Statistics.ASSISTS_PER_GAME, assists_per_game);
        values.put(DatabaseContract.Statistics.CS_MIN_PER_GAME, cs_min_per_game);
        values.put(DatabaseContract.Statistics.GOLD_MIN_PER_GAME, gold_min_per_game);
        values.put(DatabaseContract.Statistics.GOLD_PER_GAME, gold_per_game);
        values.put(DatabaseContract.Statistics.KDA_PER_GAME, kda_per_game);
        values.put(DatabaseContract.Statistics.KILL_PARTICIPATION_PER_GAME, kill_participation_per_game);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        String whereClause = DatabaseContract.Statistics.TYPE + " = ? AND " +
                DatabaseContract.Statistics.GAME_TYPE + " ?";
        String[] whereArgs = new String[] {
                total_type,
                game_type+""
        };
        long result = db.update(
                DatabaseContract.Statistics.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );

        return result;

    }

    /**
     * Update {@link DatabaseContract.Statistics} table for the hero statistics
     * @param db Instance of {@link Database_Helper} from {@link #getInstance(Context)}
     * @param game Instance of {@link Game}
     * @param game_type One of {@link Constants.Game_Types}
     */
    private void updateHeroStats(SQLiteDatabase db, Game game, int game_type) {
        int hero = game.getResults().getHero();
        ArrayList<SummaryStats> heroeStatsList = getSpecificHeroeStats(db, hero, game_type);
        SummaryStats totalHeroStats = null;
        SummaryStats positionHeroStats = null;

        for(int i = 0; heroeStatsList != null && i<heroeStatsList.size(); i++) {
            SummaryStats tmpStats = heroeStatsList.get(i);
            if(tmpStats.getPosition() == Constants.Positions.ALL){
                totalHeroStats = tmpStats;
            }
            else if(tmpStats.getPosition() == game.getResults().getPosition()) {
                positionHeroStats = tmpStats;
            }
        }


        int total_games = 0;
        int wins = 0;

        float kills_per_game;
        float deaths_per_game;
        float assists_per_game;
        float cs_min_per_game;
        float gold_min_per_game;
        float gold_per_game;
        float kda_per_game;
        float kill_participation_per_game;

        if(totalHeroStats!=null){
            int prev_total_games = totalHeroStats.getTotal_games();
            total_games = prev_total_games + 1;
            wins = totalHeroStats.getWins();
            if(game.isVictory()){
                wins++;
            }

            kills_per_game = (float)(totalHeroStats.getKills_per_game()*prev_total_games + game.getResults().getKills())/total_games;
            deaths_per_game = (float)(totalHeroStats.getDeaths_per_game()*prev_total_games + game.getResults().getDeaths())/total_games;
            assists_per_game = (float)(totalHeroStats.getAssists_per_game()*prev_total_games + game.getResults().getAssists())/total_games;
            cs_min_per_game = (float)(totalHeroStats.getCs_min_per_game()*prev_total_games + game.getResults().getCs_min())/total_games;
            gold_min_per_game = (float)(totalHeroStats.getGold_min_per_game()*prev_total_games + game.getResults().getGold_min())/total_games;
            gold_per_game = (float)(totalHeroStats.getGold_per_game()*prev_total_games + game.getResults().getGold())/total_games;
            kda_per_game = (float)(totalHeroStats.getKda_per_game()*prev_total_games + game.getResults().getKda_ratio())/total_games;
            kill_participation_per_game = (float)(totalHeroStats.getKill_participation_per_game()*prev_total_games + game.getResults().getKill_participation())/total_games;
        }
        else {
            total_games = 1;
            if(game.isVictory()){
                wins = 1;
            }
            kills_per_game = game.getResults().getKills();
            deaths_per_game = game.getResults().getDeaths();
            assists_per_game = game.getResults().getAssists();
            cs_min_per_game = game.getResults().getCs_min();
            gold_min_per_game = game.getResults().getGold_min();
            gold_per_game = game.getResults().getGold();
            kda_per_game = game.getResults().getKda_ratio();
            kill_participation_per_game = game.getResults().getKill_participation();
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Statistics.WINS, wins);
        values.put(DatabaseContract.Statistics.TOTAL_GAMES, total_games);
        values.put(DatabaseContract.Statistics.KILLS_PER_GAME, kills_per_game);
        values.put(DatabaseContract.Statistics.DEATHS_PER_GAME, deaths_per_game);
        values.put(DatabaseContract.Statistics.ASSISTS_PER_GAME, assists_per_game);
        values.put(DatabaseContract.Statistics.CS_MIN_PER_GAME, cs_min_per_game);
        values.put(DatabaseContract.Statistics.GOLD_MIN_PER_GAME, gold_min_per_game);
        values.put(DatabaseContract.Statistics.GOLD_PER_GAME, gold_per_game);
        values.put(DatabaseContract.Statistics.KDA_PER_GAME, kda_per_game);
        values.put(DatabaseContract.Statistics.KILL_PARTICIPATION_PER_GAME, kill_participation_per_game);

        // Insert the new row, returning the primary key value of the new row

        String whereClause = DatabaseContract.Statistics.TYPE + " = ? AND " +
                DatabaseContract.Statistics.SECOND_TYPE + " = ? AND " +
                DatabaseContract.Statistics.GAME_TYPE + " = ?";
        String[] whereArgs = new String[] {
                Constants.Statistics_DB.Heroes.HERO_ALL,
                hero+"",
                game_type+""
        };
        long result = db.update(
                DatabaseContract.Statistics.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );

        wins = 0;

        if(positionHeroStats!=null){
            int prev_total_games = positionHeroStats.getTotal_games();
            total_games = prev_total_games + 1;
            wins = positionHeroStats.getWins();
            if(game.isVictory()){
                wins++;
            }

            kills_per_game = (float)(positionHeroStats.getKills_per_game()*prev_total_games + game.getResults().getKills())/total_games;
            deaths_per_game = (float)(positionHeroStats.getDeaths_per_game()*prev_total_games + game.getResults().getDeaths())/total_games;
            assists_per_game = (float)(positionHeroStats.getAssists_per_game()*prev_total_games + game.getResults().getAssists())/total_games;
            cs_min_per_game = (float)(positionHeroStats.getCs_min_per_game()*prev_total_games + game.getResults().getCs_min())/total_games;
            gold_min_per_game = (float)(positionHeroStats.getGold_min_per_game()*prev_total_games + game.getResults().getGold_min())/total_games;
            gold_per_game = (float)(positionHeroStats.getGold_per_game()*prev_total_games + game.getResults().getGold())/total_games;
            kda_per_game = (float)(positionHeroStats.getKda_per_game()*prev_total_games + game.getResults().getKda_ratio())/total_games;
            kill_participation_per_game = (float)(positionHeroStats.getKill_participation_per_game()*prev_total_games + game.getResults().getKill_participation())/total_games;
        }
        else {
            total_games = 1;
            if(game.isVictory()){
                wins = 1;
            }
            kills_per_game = game.getResults().getKills();
            deaths_per_game = game.getResults().getDeaths();
            assists_per_game = game.getResults().getAssists();
            cs_min_per_game = game.getResults().getCs_min();
            gold_min_per_game = game.getResults().getGold_min();
            gold_per_game = game.getResults().getGold();
            kda_per_game = game.getResults().getKda_ratio();
            kill_participation_per_game = game.getResults().getKill_participation();
        }

        values = new ContentValues();
        values.put(DatabaseContract.Statistics.WINS, wins);
        values.put(DatabaseContract.Statistics.TOTAL_GAMES, total_games);
        values.put(DatabaseContract.Statistics.KILLS_PER_GAME, kills_per_game);
        values.put(DatabaseContract.Statistics.DEATHS_PER_GAME, deaths_per_game);
        values.put(DatabaseContract.Statistics.ASSISTS_PER_GAME, assists_per_game);
        values.put(DatabaseContract.Statistics.CS_MIN_PER_GAME, cs_min_per_game);
        values.put(DatabaseContract.Statistics.GOLD_MIN_PER_GAME, gold_min_per_game);
        values.put(DatabaseContract.Statistics.GOLD_PER_GAME, gold_per_game);
        values.put(DatabaseContract.Statistics.KDA_PER_GAME, kda_per_game);
        values.put(DatabaseContract.Statistics.KILL_PARTICIPATION_PER_GAME, kill_participation_per_game);

        // Insert the new row, returning the primary key value of the new row

        whereClause = DatabaseContract.Statistics.TYPE + " = ? AND " +
                DatabaseContract.Statistics.SECOND_TYPE + " = ? AND " +
                DatabaseContract.Statistics.GAME_TYPE + " = ?";
        whereArgs = new String[] {
                HalcyonUtils.getHeroeStatistics_DBTypeFromPosition(game.getResults().getPosition()),
                hero + "",
                game_type+""
        };
        long secondResult = db.update(
                DatabaseContract.Statistics.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );
    }


}
