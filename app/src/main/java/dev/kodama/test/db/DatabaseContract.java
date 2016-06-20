package dev.kodama.test.db;

import android.provider.BaseColumns;

/**
 * Created by JuanCamilo on 3/9/2016.
 */
public final class DatabaseContract {

    private static final String TEXT_TYPE          = " TEXT";
    private static final String INTEGER_TYPE       = " INTEGER";
    private static final String REAL_TYPE          = " REAL";
    private static final String COMMA_SEP          = ",";
    private static final String UNIQUE             = " UNIQUE";
    private static final String NOT_NULL           = " NOT NULL";
    private static final String AUTOINCREMENT      = " AUTOINCREMENT";
    private static final String PRIMARY_KEY        = " PRIMARY KEY";

    private DatabaseContract() {}


    public static abstract class Heroes implements BaseColumns {
        public static final String TABLE_NAME       = "heroes";
        public static final String NAME             = "name";
        public static final String POSITION         = "position";
        public static final String TYPE             = "type";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                NAME + TEXT_TYPE + NOT_NULL + UNIQUE + COMMA_SEP +
                POSITION + TEXT_TYPE + COMMA_SEP +
                TYPE + TEXT_TYPE +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Players implements BaseColumns {
        public static final String TABLE_NAME       = "players";
        public static final String IGN              = "ign";
        public static final String NAME             = "name";
        public static final String MAIN_POSITION    = "main_position";
        public static final String MAIN_HERO        = "main_hero";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                IGN + TEXT_TYPE + NOT_NULL + UNIQUE + COMMA_SEP +
                NAME + TEXT_TYPE + COMMA_SEP +
                MAIN_POSITION + TEXT_TYPE + COMMA_SEP +
                MAIN_HERO + INTEGER_TYPE + COMMA_SEP +
                " FOREIGN KEY(" + MAIN_HERO + ") REFERENCES " + Heroes.TABLE_NAME +
                "(" + Heroes._ID + ")" +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Teams implements BaseColumns {
        public static final String TABLE_NAME       = "teams";
        public static final String NAME             = "name";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                NAME + TEXT_TYPE + NOT_NULL + UNIQUE +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Players_Teams implements BaseColumns {
        public static final String TABLE_NAME       = "players_teams";
        public static final String PLAYER_ID        = "player_id";
        public static final String TEAM_ID          = "team_id";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                PLAYER_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
                TEAM_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                " FOREIGN KEY(" + PLAYER_ID + ") REFERENCES " + Players.TABLE_NAME +
                "(" + Players._ID + ")" + COMMA_SEP +
                " FOREIGN KEY(" + TEAM_ID + ") REFERENCES " + Teams.TABLE_NAME +
                "(" + Teams._ID + ")" +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Games implements BaseColumns {
        public static final String TABLE_NAME       = "games";
        public static final String TIMESTAMP        = "timestamp";
        public static final String LENGTH           = "length";
        public static final String HOME_TEAM_WIN    = "home_team_win";
        public static final String HOME_TEAM_ID     = "home_team_id";
        public static final String AWAY_TEAM_ID     = "away_team_id";
        public static final String GAME_TYPE        = "game_type";
        public static final String QUEUE_TYPE       = "queue_type";
        public static final String VG_VERSION       = "vg_version";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                TIMESTAMP + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                LENGTH + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                HOME_TEAM_WIN + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                HOME_TEAM_ID + INTEGER_TYPE + COMMA_SEP +
                AWAY_TEAM_ID + INTEGER_TYPE + COMMA_SEP +
                GAME_TYPE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                QUEUE_TYPE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                VG_VERSION + REAL_TYPE + NOT_NULL + COMMA_SEP +
                " FOREIGN KEY(" + HOME_TEAM_ID + ") REFERENCES " + Teams.TABLE_NAME +
                "(" + Teams._ID + ")" + COMMA_SEP +
                " FOREIGN KEY(" + AWAY_TEAM_ID + ") REFERENCES " + Teams.TABLE_NAME +
                "(" + Teams._ID + ")" +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Player_Results implements BaseColumns {
        public static final String TABLE_NAME           = "player_results";
        public static final String PLAYER_ID            = "player_id";
        public static final String HERO_ID              = "hero_id";
        public static final String GAME_ID              = "game_id";
        public static final String ELO                  = "elo";
        public static final String ELO_DETAILS          = "elo_details";
        public static final String HOME_TEAM            = "home_team";
        public static final String BUILD_TYPE           = "build_type";
        public static final String POSITION             = "position";
        public static final String KILLS                = "kills";
        public static final String DEATHS               = "deaths";
        public static final String ASSISTS              = "assists";
        public static final String CS                   = "cs";
        public static final String GOLD                 = "gold";
        public static final String KDA_RATIO            = "kda_ratio";
        public static final String CS_MIN               = "cs_min";
        public static final String GOLD_MIN             = "gold_min";
        public static final String KILL_PARTICIPATION   = "kill_participation";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                PLAYER_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                HERO_ID + INTEGER_TYPE + COMMA_SEP +
                GAME_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                ELO + TEXT_TYPE + COMMA_SEP +
                ELO_DETAILS + TEXT_TYPE + COMMA_SEP +
                HOME_TEAM + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                BUILD_TYPE + TEXT_TYPE + COMMA_SEP +
                POSITION + TEXT_TYPE + COMMA_SEP +
                KILLS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                DEATHS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                ASSISTS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                CS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                GOLD + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                KDA_RATIO + REAL_TYPE + NOT_NULL + COMMA_SEP +
                CS_MIN + REAL_TYPE + NOT_NULL + COMMA_SEP +
                GOLD_MIN + REAL_TYPE + NOT_NULL + COMMA_SEP +
                KILL_PARTICIPATION + REAL_TYPE + NOT_NULL + COMMA_SEP +
                " FOREIGN KEY(" + PLAYER_ID + ") REFERENCES " + Players.TABLE_NAME +
                "(" + Players._ID + ")" + COMMA_SEP +
                " FOREIGN KEY(" + HERO_ID + ") REFERENCES " + Heroes.TABLE_NAME +
                "(" + Heroes._ID + ")" + COMMA_SEP +
                " FOREIGN KEY(" + GAME_ID + ") REFERENCES " + Games.TABLE_NAME +
                "(" + Games._ID + ")" +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}
