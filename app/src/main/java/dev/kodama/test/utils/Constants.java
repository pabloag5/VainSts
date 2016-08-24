package dev.kodama.test.utils;

import java.util.HashMap;
import java.util.Map;

import dev.kodama.test.R;

/**
 * Created by researcher on 22/04/16.
 */
public final class Constants {

    public static class Build_Types {
        public static String CRYSTAL = "crystal";
        public static String WEAPON = "weapon";
        public static String HYBRID = "hybrid";
        public static String UTILITY = "utility";
    }

    public static class Heroes {

        public static int[] images = {
                R.drawable.adagio,
                R.drawable.ardan,
                R.drawable.blackfeather,
                R.drawable.catherine,
                R.drawable.celeste,
                R.drawable.fortress,
                R.drawable.glaive,
                R.drawable.joule,
                R.drawable.kestrel,
                R.drawable.koshka,
                R.drawable.krul,
                R.drawable.petal,
                R.drawable.phinn,
                R.drawable.reim,
                R.drawable.ringo,
                R.drawable.rona,
                R.drawable.saw,
                R.drawable.skaarf,
                R.drawable.skye,
                R.drawable.taka,
                R.drawable.vox
        };

        public final int ADAGIO = 1;
        public final int ARDAN = 2;
        public final int BLACKFEATHER = 3;
        public final int CATHERINE = 4;
        public final int CELESTE = 5;
        public final int FORTRESS = 6;
        public final int GLAIVE = 7;
        public final int JOULE = 8;
        public final int KESTREL = 9;
        public final int KOSHKA = 10;
        public final int KRUL = 11;
        public final int PETAL = 12;
        public final int PHINN = 13;
        public final int REIM = 14;
        public final int RINGO = 15;
        public final int RONA = 16;
        public final int SAMUEL = 17;
        public final int SAW = 18;
        public final int SKAARF = 19;
        public final int SKYE = 20;
        public final int TAKA = 21;
        public final int VOX = 22;
        public final int LANCE = 23;
        public final int LYRA = 24;
        public final int OZO = 25;
        public final int ALPHA = 26;

        public final static Map<Integer, Integer> heroesMap = new HashMap<Integer, Integer>() {{
            put(ADAGIO, R.string.adagio);
            put(ARDAN, R.string.ardan);
            put(BLACKFEATHER, R.string.blackfeather);
            put(CATHERINE, R.string.catherine);
            put(CELESTE, R.string.celeste);
            put(FORTRESS, R.string.fortress);
            put(GLAIVE, R.string.glaive);
            put(JOULE, R.string.joule);
            put(KESTREL, R.string.kestrel);
            put(KOSHKA, R.string.koshka);
            put(KRUL, R.string.krul);
            put(PETAL, R.string.petal);
            put(PHINN, R.string.phinn);
            put(REIM, R.string.reim);
            put(RINGO, R.string.ringo);
            put(RONA, R.string.rona);
            put(SAMUEL, R.string.samuel);
            put(SAW, R.string.saw);
            put(SKAARF, R.string.skaarf);
            put(SKYE, R.string.skye);
            put(TAKA, R.string.taka);
            put(VOX, R.string.vox);
            put(LANCE, R.string.lance);
            put(LYRA, R.string.lyra);
            put(OZO, R.string.ozo);
            put(ALPHA, R.string.alpha);
        }};


    }

    public static class Positions {
        public final static int LANE = 1;
        public final static int JUNGLE = 2;
        public final static int ROAM = 3;
        public final static int ALL = 4;

        public final static Map<Integer, Integer> positionsMap = new HashMap<Integer, Integer>() {{
            put(LANE, R.string.lane);
            put(JUNGLE, R.string.jungle);
            put(ROAM, R.string.roam);
            put(ALL, R.string.all);
        }};
    }

    public static class Game_Types {
        public static int CASUAL = 1;
        public static int RANKED = 2;
        public static int PRIVATE = 3;
        public static int TOURNAMENT = 4;

        public final static Map<Integer, Integer> gameTypeMap = new HashMap<Integer, Integer>() {{
            put(CASUAL, R.string.casual_type);
            put(RANKED, R.string.ranked_type);
            put(PRIVATE, R.string.private_type);
            put(TOURNAMENT, R.string.tournament_type);
        }};
    }

    public static class Queue_Types {
        public static String SOLO_QUEUE = "solo_queue";
        public static String TEAM_QUEUE = "team_queue";
        public static String GUILD_QUEUE = "guild_queue";
    }

    public static class Elo {

        public static int TIER_1 = 1;
        public static int TIER_2 = 2;
        public static int TIER_3 = 3;
        public static int TIER_4 = 4;
        public static int TIER_5 = 5;
        public static int TIER_6 = 6;
        public static int TIER_7 = 7;
        public static int TIER_8 = 8;
        public static int TIER_9 = 9;
        public static int TIER_10 = 10;

        public final static Map<Integer, Integer> EloMap = new HashMap<Integer, Integer>() {{
            put(TIER_1, R.string.tier_1);
            put(TIER_2, R.string.tier_2);
            put(TIER_3, R.string.tier_3);
            put(TIER_4, R.string.tier_4);
            put(TIER_5, R.string.tier_5);
            put(TIER_6, R.string.tier_6);
            put(TIER_7, R.string.tier_7);
            put(TIER_8, R.string.tier_8);
            put(TIER_9, R.string.tier_9);
            put(TIER_10, R.string.tier_10);
        }};
        
        public static class Sub_Elo {
            public static int BRONZE = 1;
            public static int SILVER = 2;
            public static int GOLD = 3;

            public final static Map<Integer, Integer> EloMap = new HashMap<Integer, Integer>() {{
                put(BRONZE, R.string.bronze);
                put(SILVER, R.string.silver);
                put(GOLD, R.string.gold);
            }};
        }
        
    }

    public final static class Statistics_DB {

        public final static String TOTAL_ALL = "total";
        public final static String TOTAL_LANE = "total_lane";
        public final static String TOTAL_JUNGLE = "total_jungle";
        public final static String TOTAL_ROAM = "total_roam";
        public final static String HERO_ALL = "heroe";
        public final static String HERO_LANE = "heroe_lane";
        public final static String HERO_JUNGLE = "heroe_jungle";
        public final static String HERO_ROAM = "heroe_roam";
    }


}
