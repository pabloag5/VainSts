package dev.kodama.test.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by researcher on 12/05/16.
 */
public class Database_Helper extends SQLiteOpenHelper {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "database.db";

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

//        insertHeroes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Player_Results.DELETE_TABLE);
        db.execSQL(DatabaseContract.Games.DELETE_TABLE);
        db.execSQL(DatabaseContract.Players_Teams.DELETE_TABLE);
        db.execSQL(DatabaseContract.Teams.DELETE_TABLE);
        db.execSQL(DatabaseContract.Players.DELETE_TABLE);
        db.execSQL(DatabaseContract.Heroes.DELETE_TABLE);
        onCreate(db);
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

}
