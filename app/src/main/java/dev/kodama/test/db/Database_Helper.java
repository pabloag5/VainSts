package dev.kodama.test.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
        db.execSQL(DatabaseContract.Heroes.CREATE_TABLE);
        db.execSQL(DatabaseContract.Players.CREATE_TABLE);
        db.execSQL(DatabaseContract.Teams.CREATE_TABLE);
        db.execSQL(DatabaseContract.Players_Teams.CREATE_TABLE);
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

}
