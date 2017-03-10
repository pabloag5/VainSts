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


//    public static abstract class Players implements BaseColumns {
//        public static final String TABLE_NAME       = "players";
//        public static final String IGN              = "ign";
//        public static final String NAME             = "name";
//        public static final String MAIN_POSITION    = "main_position";
//        public static final String MAIN_HERO        = "main_hero";
//
//        public static final String CREATE_TABLE = "CREATE TABLE " +
//                TABLE_NAME + " (" +
//                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
//                IGN + TEXT_TYPE + NOT_NULL + UNIQUE + COMMA_SEP +
//                NAME + TEXT_TYPE + COMMA_SEP +
//                MAIN_POSITION + TEXT_TYPE + COMMA_SEP +
//                MAIN_HERO + INTEGER_TYPE + COMMA_SEP +
//                " FOREIGN KEY(" + MAIN_HERO + ") REFERENCES " + Heroes.TABLE_NAME +
//                "(" + Heroes._ID + ")" +
//                " )";
//        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
//    }

//    public static abstract class Teams implements BaseColumns {
//        public static final String TABLE_NAME       = "teams";
//        public static final String NAME             = "name";
//
//        public static final String CREATE_TABLE = "CREATE TABLE " +
//                TABLE_NAME + " (" +
//                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
//                NAME + TEXT_TYPE + NOT_NULL + UNIQUE +
//                " )";
//        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
//    }

//    public static abstract class Players_Teams implements BaseColumns {
//        public static final String TABLE_NAME       = "players_teams";
//        public static final String PLAYER_ID        = "player_id";
//        public static final String TEAM_ID          = "team_id";
//
//
//        public static final String CREATE_TABLE = "CREATE TABLE " +
//                TABLE_NAME + " (" +
//                PLAYER_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
//                TEAM_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
//                " FOREIGN KEY(" + PLAYER_ID + ") REFERENCES " + Players.TABLE_NAME +
//                "(" + Players._ID + ")" + COMMA_SEP +
//                " FOREIGN KEY(" + TEAM_ID + ") REFERENCES " + Teams.TABLE_NAME +
//                "(" + Teams._ID + ")" +
//                " )";
//        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
//    }

    public static abstract class Games implements BaseColumns {
        public static final String TABLE_NAME       = "games";
        public static final String TIMESTAMP        = "timestamp";
        public static final String LENGTH           = "length";
//        public static final String HOME_TEAM_WIN    = "home_team_win";
//        public static final String HOME_TEAM_ID     = "home_team_id";
//        public static final String AWAY_TEAM_ID     = "away_team_id";
        public static final String VICTORY          = "victory";
        public static final String GAME_TYPE        = "game_type";
//        public static final String QUEUE_TYPE       = "queue_type";
//        public static final String VG_VERSION       = "vg_version";
        public static final String TOTAL_KILLS      = "total_kills";
        public static final String TOTAL_DEATHS     = "total_deaths";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                TIMESTAMP + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                LENGTH + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                VICTORY + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                TOTAL_KILLS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                TOTAL_DEATHS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                GAME_TYPE + INTEGER_TYPE + NOT_NULL +
//                " FOREIGN KEY(" + AWAY_TEAM_ID + ") REFERENCES " + Teams.TABLE_NAME +
//                "(" + Teams._ID + ")" +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Player_Results implements BaseColumns {
        public static final String TABLE_NAME           = "player_results";
//        public static final String PLAYER_ID            = "player_id";
        public static final String HERO                 = "hero";
        public static final String GAME_ID              = "game_id";
//        public static final String ELO                  = "elo";
//        public static final String ELO_DETAILS          = "elo_details";
//        public static final String HOME_TEAM            = "home_team";
//        public static final String BUILD_TYPE           = "build_type";
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
//                PLAYER_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                HERO + INTEGER_TYPE + COMMA_SEP +
                GAME_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
//                ELO + TEXT_TYPE + COMMA_SEP +
//                ELO_DETAILS + TEXT_TYPE + COMMA_SEP +
//                HOME_TEAM + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
//                BUILD_TYPE + TEXT_TYPE + COMMA_SEP +
                POSITION + INTEGER_TYPE + COMMA_SEP +
                KILLS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                DEATHS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                ASSISTS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                CS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                GOLD + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                KDA_RATIO + REAL_TYPE + NOT_NULL + COMMA_SEP +
                CS_MIN + REAL_TYPE + NOT_NULL + COMMA_SEP +
                GOLD_MIN + REAL_TYPE + NOT_NULL + COMMA_SEP +
                KILL_PARTICIPATION + REAL_TYPE + NOT_NULL + COMMA_SEP +
//                " FOREIGN KEY(" + PLAYER_ID + ") REFERENCES " + Players.TABLE_NAME +
//                "(" + Players._ID + ")" + COMMA_SEP +
//                " FOREIGN KEY(" + HERO + ") REFERENCES " + Heroes.TABLE_NAME +
//                "(" + Heroes._ID + ")" + COMMA_SEP +
                " FOREIGN KEY(" + GAME_ID + ") REFERENCES " + Games.TABLE_NAME +
                "(" + Games._ID + ")" +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Statistics implements BaseColumns {
        public static final String TABLE_NAME                   = "statistics";
        //Indicates if the Statistic is total or for a hero
        public static final String TYPE                         = "type";
        //The hero of the statistic or empty if it's a total statistic
        public static final String SECOND_TYPE                  = "second_type";
        //Indicates if it's casual or ranked
        public static final String GAME_TYPE                    = "game_type";
        public static final String WINS                         = "wins";
        public static final String TOTAL_GAMES                  = "total_games";
        public static final String KILLS_PER_GAME               = "kills_per_game";
        public static final String DEATHS_PER_GAME              = "deaths_per_game";
        public static final String ASSISTS_PER_GAME             = "assists_per_game";
        public static final String CS_MIN_PER_GAME              = "cs_min_per_game";
        public static final String GOLD_MIN_PER_GAME            = "gold_min_per_game";
        public static final String GOLD_PER_GAME                = "gold_per_game";
        public static final String KDA_PER_GAME                 = "kda_per_game";
        public static final String KILL_PARTICIPATION_PER_GAME  = "kill_participation_per_game";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                TYPE + TEXT_TYPE + NOT_NULL + COMMA_SEP + //deleted UNIQUE constraint
                SECOND_TYPE + INTEGER_TYPE + NOT_NULL + COMMA_SEP + //deleted UNIQUE constraint
                GAME_TYPE + INTEGER_TYPE + NOT_NULL + COMMA_SEP + //deleted UNIQUE constraint
                WINS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                TOTAL_GAMES + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                KILLS_PER_GAME + REAL_TYPE + NOT_NULL + COMMA_SEP +
                DEATHS_PER_GAME + REAL_TYPE + NOT_NULL + COMMA_SEP +
                ASSISTS_PER_GAME + REAL_TYPE + NOT_NULL + COMMA_SEP +
                CS_MIN_PER_GAME + REAL_TYPE + NOT_NULL + COMMA_SEP +
                GOLD_MIN_PER_GAME + REAL_TYPE + NOT_NULL + COMMA_SEP +
                GOLD_PER_GAME + REAL_TYPE + NOT_NULL + COMMA_SEP +
                KDA_PER_GAME + REAL_TYPE + NOT_NULL + COMMA_SEP +
                KILL_PARTICIPATION_PER_GAME + REAL_TYPE + NOT_NULL +
                " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
