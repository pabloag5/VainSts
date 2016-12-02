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


}
