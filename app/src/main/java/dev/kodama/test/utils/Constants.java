package dev.kodama.test.utils;

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

        public static String[] list = {
            "Lyra",
            "Lance",
            "Alpha",
            "Ozo",
            "Reim",
            "Kestrel",
            "Blackfeather",
            "Phinn",
            "Skye",
            "Rona",
            "Fortress",
            "Joule",
            "Ardan",
            "Skaarf",
            "Taka",
            "Krul",
            "Saw",
            "Petal",
            "Glaive",
            "Koshka",
            "Adagio",
            "Ringo",
            "Catherine",
            "Celeste",
            "Vox",
            "Samuel"
        };
        public static int[] images = {R.drawable.adagio, R.drawable.ardan, R.drawable.blackfeather, R.drawable.catherine,
                R.drawable.celeste, R.drawable.fortress, R.drawable.glaive, R.drawable.joule,
                R.drawable.kestrel, R.drawable.koshka, R.drawable.krul, R.drawable.petal, R.drawable.phinn,
                R.drawable.reim, R.drawable.ringo, R.drawable.rona, R.drawable.saw, R.drawable.skaarf,
                R.drawable.skye, R.drawable.taka, R.drawable.vox};

    }

    public static class Positions {
        public static int LANE = 1;
        public static int JUNGLE = 2;
        public static int ROAM = 3;
    }

    public static class Game_Types {
        public static String CASUAL = "casual";
        public static String RANKED = "ranked";
        public static String PRIVATE = "private";
        public static String TOURNAMENT = "tournament";
    }

    public static class Queue_Types {
        public static String SOLO_QUEUE = "solo_queue";
        public static String TEAM_QUEUE = "team_queue";
        public static String GUILD_QUEUE = "guild_queue";
    }

    public static class Elo {

        public static String JUST_BEGINNING = "tier_1";
        public static String GETTING_THERE = "tier_2";
        public static String ROCK_SOLID = "tier_3";
        public static String WORTHY_FOE = "tier_4";
        public static String GOT_SWAGGER = "tier_5";
        public static String CREDIBLE_THREAT = "tier_6";
        public static String THE_HOTNESS = "tier_7";
        public static String SIMPLY_AMAZING = "tier_8";
        public static String PINNACLE_OF_AWESOME = "tier_9";
        public static String VAINGLORIOUS = "tier_10";

        public static String BRONZE = "sub_tier_1";
        public static String SILVER = "sub_tier_2";
        public static String GOLD = "sub_tier_3";
    }

    public final static class Statistics_DB {

        public final static String TOTAL_ALL = "total";
        public final static String TOTAL_LANE = "total_lane";
        public final static String TOTAL_JUNGLE = "total_jungle";
        public final static String TOTAL_ROAM = "total_roam";
        public final static String HEROE_ALL = "heroe";
        public final static String HEROE_LANE = "heroe_lane";
        public final static String HEROE_JUNGLE = "heroe_jungle";
        public final static String HEROE_ROAM = "heroe_roam";
    }


}
