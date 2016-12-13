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
                R.drawable.samuel,
                R.drawable.saw,
                R.drawable.skaarf,
                R.drawable.skye,
                R.drawable.taka,
                R.drawable.vox,
                R.drawable.lance,
                R.drawable.lyra,
                R.drawable.ozo,
                R.drawable.alpha,
                R.drawable.gwen,
                R.drawable.baron
        };

        public static class Ids {

            public final static int NO_HERO = -1;

            public final static int ADAGIO = 1;
            public final static int ARDAN = 2;
            public final static int BLACKFEATHER = 3;
            public final static int CATHERINE = 4;
            public final static int CELESTE = 5;
            public final static int FORTRESS = 6;
            public final static int GLAIVE = 7;
            public final static int JOULE = 8;
            public final static int KESTREL = 9;
            public final static int KOSHKA = 10;
            public final static int KRUL = 11;
            public final static int PETAL = 12;
            public final static int PHINN = 13;
            public final static int REIM = 14;
            public final static int RINGO = 15;
            public final static int RONA = 16;
            public final static int SAMUEL = 17;
            public final static int SAW = 18;
            public final static int SKAARF = 19;
            public final static int SKYE = 20;
            public final static int TAKA = 21;
            public final static int VOX = 22;
            public final static int LANCE = 23;
            public final static int LYRA = 24;
            public final static int OZO = 25;
            public final static int ALPHA = 26;
            public final static int GWEN = 27;
            public final static int BARON = 28;
        }

        public final static Map<Integer, Integer> iconsMap = new HashMap<Integer, Integer>() {{
            put(Ids.ADAGIO, R.drawable.adagio);
            put(Ids.ARDAN, R.drawable.ardan);
            put(Ids.BLACKFEATHER, R.drawable.blackfeather);
            put(Ids.CATHERINE, R.drawable.catherine);
            put(Ids.CELESTE, R.drawable.celeste);
            put(Ids.FORTRESS, R.drawable.fortress);
            put(Ids.GLAIVE, R.drawable.glaive);
            put(Ids.JOULE, R.drawable.joule);
            put(Ids.KESTREL, R.drawable.kestrel);
            put(Ids.KOSHKA, R.drawable.koshka);
            put(Ids.KRUL, R.drawable.krul);
            put(Ids.PETAL, R.drawable.petal);
            put(Ids.PHINN, R.drawable.phinn);
            put(Ids.REIM, R.drawable.reim);
            put(Ids.RINGO, R.drawable.ringo);
            put(Ids.RONA, R.drawable.rona);
            put(Ids.SAMUEL, R.drawable.samuel);
            put(Ids.SAW, R.drawable.saw);
            put(Ids.SKAARF, R.drawable.skaarf);
            put(Ids.SKYE, R.drawable.skye);
            put(Ids.TAKA, R.drawable.taka);
            put(Ids.VOX, R.drawable.vox);
            put(Ids.LANCE, R.drawable.lance);
            put(Ids.LYRA, R.drawable.lyra);
            put(Ids.OZO, R.drawable.ozo);
            put(Ids.ALPHA, R.drawable.alpha);
            put(Ids.GWEN, R.drawable.gwen);
            put(Ids.BARON, R.drawable.baron);
        }};


        public final static Map<Integer, Integer> heroesMap = new HashMap<Integer, Integer>() {{
            put(Ids.ADAGIO, R.string.adagio);
            put(Ids.ARDAN, R.string.ardan);
            put(Ids.BLACKFEATHER, R.string.blackfeather);
            put(Ids.CATHERINE, R.string.catherine);
            put(Ids.CELESTE, R.string.celeste);
            put(Ids.FORTRESS, R.string.fortress);
            put(Ids.GLAIVE, R.string.glaive);
            put(Ids.JOULE, R.string.joule);
            put(Ids.KESTREL, R.string.kestrel);
            put(Ids.KOSHKA, R.string.koshka);
            put(Ids.KRUL, R.string.krul);
            put(Ids.PETAL, R.string.petal);
            put(Ids.PHINN, R.string.phinn);
            put(Ids.REIM, R.string.reim);
            put(Ids.RINGO, R.string.ringo);
            put(Ids.RONA, R.string.rona);
            put(Ids.SAMUEL, R.string.samuel);
            put(Ids.SAW, R.string.saw);
            put(Ids.SKAARF, R.string.skaarf);
            put(Ids.SKYE, R.string.skye);
            put(Ids.TAKA, R.string.taka);
            put(Ids.VOX, R.string.vox);
            put(Ids.LANCE, R.string.lance);
            put(Ids.LYRA, R.string.lyra);
            put(Ids.OZO, R.string.ozo);
            put(Ids.ALPHA, R.string.alpha);
            put(Ids.GWEN, R.string.Gwen);
            put(Ids.BARON, R.string.Baron);
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
        public final static int CASUAL = 1;
        public final static int RANKED = 2;
        public final static int PRIVATE = 3;
        public final static int TOURNAMENT = 4;

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

        public final static int TIER_1 = 1;
        public final static int TIER_2 = 2;
        public final static int TIER_3 = 3;
        public final static int TIER_4 = 4;
        public final static int TIER_5 = 5;
        public final static int TIER_6 = 6;
        public final static int TIER_7 = 7;
        public final static int TIER_8 = 8;
        public final static int TIER_9 = 9;
        public final static int TIER_10 = 10;

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
            public final static int BRONZE = 1;
            public final static int SILVER = 2;
            public final static int GOLD = 3;

            public final static Map<Integer, Integer> EloMap = new HashMap<Integer, Integer>() {{
                put(BRONZE, R.string.bronze);
                put(SILVER, R.string.silver);
                put(GOLD, R.string.gold);
            }};
        }
        
    }

    public final static class Statistics_DB {

        public final static class Totals {
            public final static String TOTAL_ALL = "total";
            public final static String TOTAL_LANE = "total_lane";
            public final static String TOTAL_JUNGLE = "total_jungle";
            public final static String TOTAL_ROAM = "total_roam";
        }

        public final static class Heroes {
            public final static String HERO_ALL = "heroe";
            public final static String HERO_LANE = "heroe_lane";
            public final static String HERO_JUNGLE = "heroe_jungle";
            public final static String HERO_ROAM = "heroe_roam";
        }
    }

    public static class Detail_Types {
        public final static int KDA_DETAIL = 1;
        public final static int WINRATIO_DETAIL = 2;
        public final static int HERO_DETAIL = 3;

        public final static Map<Integer, Integer> gameTypeMap = new HashMap<Integer, Integer>() {{
            put(KDA_DETAIL, R.string.kda_detail);
            put(WINRATIO_DETAIL, R.string.winratio_detail);
            put(HERO_DETAIL, R.string.hero_detail);
        }};
    }



}
