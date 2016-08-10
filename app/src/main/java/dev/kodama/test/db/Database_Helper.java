package dev.kodama.test.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.Stats;
import dev.kodama.test.utils.HalcyonUtils;

/**
 * Created by researcher on 12/05/16.
 */
public class Database_Helper extends SQLiteOpenHelper {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "database.db";

    private static Database_Helper sInstance;

    public static synchronized Database_Helper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new Database_Helper(context.getApplicationContext());
        }
        return sInstance;
    }

    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

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


    //    public void insertHeroes(SQLiteDatabase db){
//
//        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(DatabaseContract.Heroes.NAME, id);
//        values.put(DatabaseContract.Heroes.POSITION, title);
//        values.put(DatabaseContract.Heroes.TYPE, content);
//
//// Insert the new row, returning the primary key value of the new row
//        long newRowId;
//        newRowId = db.insert(
//                DatabaseContract.Heroes.TABLE_NAME,
//                null,
//                values);
//    }

    public Stats getTotalStats(SQLiteDatabase db, String total_type) {

        String type = "total";

        String position = HalcyonUtils.getPositionFromStatistics_DB_type(total_type);

        String tableName = DatabaseContract.Statistics.TABLE_NAME;
        String[] tableColumns = null;
        String whereClause = DatabaseContract.Statistics.TYPE + " = ?";
        String[] whereArgs = new String[] {
                total_type
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

            String heroe = null;
            return new Stats(wins, total_games, kills_per_game, deaths_per_game, assists_per_game, cs_min_per_game, gold_min_per_game, gold_per_game, kda_per_game, kill_participation_per_game, position, heroe, type);
        } else {
            return null;
        }


    }

    public ArrayList<Stats> getHeroeStats(SQLiteDatabase db, String heroe_type) {

        String type = "heroe";

        String position = HalcyonUtils.getPositionFromStatistics_DB_type(heroe_type);

        String tableName = DatabaseContract.Statistics.TABLE_NAME;
        String[] tableColumns = null;
        String whereClause = DatabaseContract.Statistics.TYPE + " = ?";
        String[] whereArgs = new String[] {
                heroe_type
        };
        Cursor c = db.query(tableName,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        ArrayList<Stats> statsArray = new ArrayList<>();
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

                String heroe = c.getString(c.getColumnIndex(DatabaseContract.Statistics.SECOND_TYPE));
                statsArray.add(new Stats(wins, total_games, kills_per_game, deaths_per_game, assists_per_game, cs_min_per_game, gold_min_per_game, gold_per_game, kda_per_game, kill_participation_per_game, position, heroe, type));
                c.moveToNext();
            }

            return statsArray;
        }
        else {
            return null;
        }
    }

    public ArrayList<Stats> getSpecificHeroeStats(SQLiteDatabase db, String heroe) {

        String type = "heroe";

        String tableName = DatabaseContract.Statistics.TABLE_NAME;
        String[] tableColumns = null;
        String whereClause = DatabaseContract.Statistics.SECOND_TYPE + " = ?";
        String[] whereArgs = new String[] {
                heroe
        };
        Cursor c = db.query(tableName,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        ArrayList<Stats> statsArray = new ArrayList<>();
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

                String position = HalcyonUtils.getPositionFromStatistics_DB_type(c.getString(c.getColumnIndex(DatabaseContract.Statistics.TYPE)));

                statsArray.add(new Stats(wins, total_games, kills_per_game, deaths_per_game, assists_per_game, cs_min_per_game, gold_min_per_game, gold_per_game, kda_per_game, kill_participation_per_game, position, heroe, type));
                c.moveToNext();
            }

            return statsArray;
        }
        else {
            return null;
        }
    }

//    public String getHeroeNameById(SQLiteDatabase db, int hero_id) {
//
//
//        String tableName = DatabaseContract.Heroes.TABLE_NAME;
//        String[] tableColumns = null;
//        String whereClause = DatabaseContract.Heroes._ID + " = ?";
//        String[] whereArgs = new String[] {
//                hero_id+""
//        };
//        Cursor c = db.query(tableName,
//                tableColumns,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null
//        );
//        ArrayList<Stats> statsArray = new ArrayList<>();
//        c.moveToFirst();
//        String heroe_name = c.getString(c.getColumnIndex(DatabaseContract.Heroes.NAME));
//
//        return heroe_name;
//    }
//
//    public int getHeroIdByName(SQLiteDatabase db, String name) {
//
//
//        String tableName = DatabaseContract.Heroes.TABLE_NAME;
//        String[] tableColumns = null;
//        String whereClause = DatabaseContract.Heroes.NAME + " = ?";
//        String[] whereArgs = new String[] {
//                name+""
//        };
//        Cursor c = db.query(tableName,
//                tableColumns,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null
//        );
//        ArrayList<Stats> statsArray = new ArrayList<>();
//        c.moveToFirst();
//        int hero_id = c.getInt(c.getColumnIndex(DatabaseContract.Heroes._ID));
//
//        return hero_id;
//    }

    public void addNewGame(SQLiteDatabase db, Game game) {
        long game_id = addGame(db, game);
        addPlayerResults(db, game.getResults(), game_id);

        String totalDBPosition = HalcyonUtils.getTotalStatistics_DBTypeFromPosition(game.getResults().getPosition());


        updateTotalStats(db, game, Constants.Statistics_DB.TOTAL_ALL);
        updateTotalStats(db, game, totalDBPosition);
        updateHeroStats(db, game);

    }

    private long addGame(SQLiteDatabase db, Game game) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Games.TIMESTAMP, System.currentTimeMillis());
        values.put(DatabaseContract.Games.LENGTH, game.getLength());
        values.put(DatabaseContract.Games.WIN, game.isWin());
        values.put(DatabaseContract.Games.GAME_TYPE, game.getGame_type());

    // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DatabaseContract.Games.TABLE_NAME,
                null,
                values);

        return newRowId;

    }

    private long addPlayerResults(SQLiteDatabase db, Player_Results results, long game_id) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //TODO: Fix hero
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

    private long updateTotalStats(SQLiteDatabase db, Game game, String total_type) {

        Stats totalStats = getTotalStats(db, total_type);
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
            if(game.isWin()){
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
            if(game.isWin()){
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
        //TODO: Fix hero
        values.put(DatabaseContract.Statistics.SECOND_TYPE, "");
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
        String whereClause = DatabaseContract.Statistics.TYPE + " = ?";
        String[] whereArgs = new String[] {
                total_type
        };
        long result = db.update(
                DatabaseContract.Statistics.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );

        return result;

    }

    private void updateHeroStats(SQLiteDatabase db, Game game) {
        String heroe = game.getResults().getHero();
        ArrayList<Stats> heroeStatsList = getSpecificHeroeStats(db, heroe);
        Stats totalHeroeStats = null;
        Stats positionHeroeStats = null;

        String type = HalcyonUtils.getHeroeStatistics_DBTypeFromPosition(game.getResults().getPosition());

        for(int i = 0; heroeStatsList != null && i<heroeStatsList.size(); i++) {
            Stats tmpStats = heroeStatsList.get(i);
            if(tmpStats.getPosition().equals(Constants.Statistics_DB.HEROE_ALL)){
                totalHeroeStats = tmpStats;
            }
            else if(tmpStats.getPosition().equals(type)) {
                positionHeroeStats = tmpStats;
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

        if(totalHeroeStats!=null){
            int prev_total_games = totalHeroeStats.getTotal_games();
            total_games = prev_total_games + 1;
            wins = totalHeroeStats.getWins();
            if(game.isWin()){
                wins++;
            }

            kills_per_game = (float)(totalHeroeStats.getKills_per_game()*prev_total_games + game.getResults().getKills())/total_games;
            deaths_per_game = (float)(totalHeroeStats.getDeaths_per_game()*prev_total_games + game.getResults().getDeaths())/total_games;
            assists_per_game = (float)(totalHeroeStats.getAssists_per_game()*prev_total_games + game.getResults().getAssists())/total_games;
            cs_min_per_game = (float)(totalHeroeStats.getCs_min_per_game()*prev_total_games + game.getResults().getCs_min())/total_games;
            gold_min_per_game = (float)(totalHeroeStats.getGold_min_per_game()*prev_total_games + game.getResults().getGold_min())/total_games;
            gold_per_game = (float)(totalHeroeStats.getGold_per_game()*prev_total_games + game.getResults().getGold())/total_games;
            kda_per_game = (float)(totalHeroeStats.getKda_per_game()*prev_total_games + game.getResults().getKda_ratio())/total_games;
            kill_participation_per_game = (float)(totalHeroeStats.getKill_participation_per_game()*prev_total_games + game.getResults().getKill_participation())/total_games;
        }
        else {
            total_games = 1;
            if(game.isWin()){
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
        //TODO: Fix hero
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
                DatabaseContract.Statistics.SECOND_TYPE + " = ?";
        String[] whereArgs = new String[] {
                Constants.Statistics_DB.HEROE_ALL,
                heroe
        };
        long result = db.update(
                DatabaseContract.Statistics.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );

        wins = 0;

        if(positionHeroeStats!=null){
            int prev_total_games = positionHeroeStats.getTotal_games();
            total_games = prev_total_games + 1;
            wins = positionHeroeStats.getWins();
            if(game.isWin()){
                wins++;
            }

            kills_per_game = (float)(positionHeroeStats.getKills_per_game()*prev_total_games + game.getResults().getKills())/total_games;
            deaths_per_game = (float)(positionHeroeStats.getDeaths_per_game()*prev_total_games + game.getResults().getDeaths())/total_games;
            assists_per_game = (float)(positionHeroeStats.getAssists_per_game()*prev_total_games + game.getResults().getAssists())/total_games;
            cs_min_per_game = (float)(positionHeroeStats.getCs_min_per_game()*prev_total_games + game.getResults().getCs_min())/total_games;
            gold_min_per_game = (float)(positionHeroeStats.getGold_min_per_game()*prev_total_games + game.getResults().getGold_min())/total_games;
            gold_per_game = (float)(positionHeroeStats.getGold_per_game()*prev_total_games + game.getResults().getGold())/total_games;
            kda_per_game = (float)(positionHeroeStats.getKda_per_game()*prev_total_games + game.getResults().getKda_ratio())/total_games;
            kill_participation_per_game = (float)(positionHeroeStats.getKill_participation_per_game()*prev_total_games + game.getResults().getKill_participation())/total_games;
        }
        else {
            total_games = 1;
            if(game.isWin()){
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
                DatabaseContract.Statistics.SECOND_TYPE + " = ?";
        whereArgs = new String[] {
                HalcyonUtils.getHeroeStatistics_DBTypeFromPosition(game.getResults().getPosition()),
                heroe
        };
        long secondResult = db.update(
                DatabaseContract.Statistics.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );
    }


}
