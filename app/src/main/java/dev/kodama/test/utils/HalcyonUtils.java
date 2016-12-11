package dev.kodama.test.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import dev.kodama.test.db.Game;
import dev.kodama.test.db.Player_Results;

/**
 * Created by JuanCamilo on 8/4/2016.
 */
public class HalcyonUtils {

    public static int getPositionFromStatistics_DB_type(String type) {
        int position = 0;
        switch (type){
            case Constants.Statistics_DB.Heroes.HERO_ALL:
                position = Constants.Positions.ALL;
                break;
            case Constants.Statistics_DB.Heroes.HERO_LANE:
                position = Constants.Positions.LANE;
                break;
            case Constants.Statistics_DB.Heroes.HERO_JUNGLE:
                position = Constants.Positions.JUNGLE;
                break;
            case Constants.Statistics_DB.Heroes.HERO_ROAM:
                position = Constants.Positions.ROAM;
                break;
            case Constants.Statistics_DB.Totals.TOTAL_ALL:
                position = Constants.Positions.ALL;
                break;
            case Constants.Statistics_DB.Totals.TOTAL_LANE:
                position = Constants.Positions.LANE;
                break;
            case Constants.Statistics_DB.Totals.TOTAL_JUNGLE:
                position = Constants.Positions.JUNGLE;
                break;
            case Constants.Statistics_DB.Totals.TOTAL_ROAM:
                position = Constants.Positions.JUNGLE;
                break;
        }
        return position;
    }

    public static String getHeroeStatistics_DBTypeFromPosition(int position) {
        String type = "";
        switch (position){
            case Constants.Positions.LANE:
                type = Constants.Statistics_DB.Heroes.HERO_LANE;
                break;
            case Constants.Positions.JUNGLE:
                type = Constants.Statistics_DB.Heroes.HERO_JUNGLE;
                break;
            case Constants.Positions.ROAM:
                type = Constants.Statistics_DB.Heroes.HERO_ROAM;
                break;

        }
        return type;
    }

    public static String getTotalStatistics_DBTypeFromPosition(int position) {
        String type = "";
        switch (position){
            case Constants.Positions.LANE:
                type = Constants.Statistics_DB.Totals.TOTAL_LANE;
                break;
            case Constants.Positions.JUNGLE:
                type = Constants.Statistics_DB.Totals.TOTAL_JUNGLE;
                break;
            case Constants.Positions.ROAM:
                type = Constants.Statistics_DB.Totals.TOTAL_ROAM;
                break;

        }
        return type;
    }

    public static String getHeroNameFromId(Context context, int hero_id) {

        Integer hero_string_id = Constants.Heroes.heroesMap.get(hero_id);
        String heroName = null;
        if(hero_string_id!=null) {
            try {
                heroName = context.getResources().getString(hero_string_id);
            } catch (Exception e) {
                heroName = null;
            }
        }

        return heroName;

    }

    public static Integer getHeroIconFromId(Context context, int hero_id) {

        Integer hero_icon_id = Constants.Heroes.iconsMap.get(hero_id);

        return hero_icon_id;

    }

    public static String getTierNameFromId(Context context, int tier_id) {

        Integer tier_string_id = Constants.Elo.EloMap.get(tier_id);
        String tierName = null;
        if(tier_string_id!=null) {
            try {
                tierName = context.getResources().getString(tier_string_id);
            } catch (Exception e) {
                tierName = null;
            }
        }

        return tierName;

    }

    public static String getSubTierNameFromId(Context context, int subtier_id) {

        Integer sub_tier_string_id = Constants.Elo.Sub_Elo.EloMap.get(subtier_id);
        String subTierName = null;
        if(sub_tier_string_id!=null) {
            try {
                subTierName = context.getResources().getString(sub_tier_string_id);
            } catch (Exception e) {
                subTierName = null;
            }
        }

        return subTierName;
    }

    public static String getPositionNameFromId(Context context, int position_id) {

        Integer position_string_id = Constants.Positions.positionsMap.get(position_id);
        String positionName = null;
        if(position_string_id!=null) {
            try {
                positionName = context.getResources().getString(position_string_id);
            } catch (Exception e) {
                positionName = null;
            }
        }

        return positionName;
    }

    public static String getGameTypeStringFromId(Context context, int game_type_id) {

        Integer game_type_string_id = Constants.Positions.positionsMap.get(game_type_id);
        String gameTypeName = null;
        if(game_type_string_id!=null) {
            try {
                gameTypeName = context.getResources().getString(game_type_string_id);
            } catch (Exception e) {
                gameTypeName = null;
            }
        }

        return gameTypeName;
    }

    /**
     * Get the float value of the length of a game
     * @param minutes number of minutes
     * @param seconds number of seconds
     * @return float representing the time of the game
     */
    public static float getLengthFrom(int minutes, int seconds) {
        return minutes + ((float) seconds/60);
    }

    /**
     * Get the minutes and seconds of a game length in a float value
     * @param length float value of the length of a game
     * @return Array of size 2 where index 0 has minutes and index 1 has seconds
     */
    public static int[] getLengthFrom(float length) {
        return new int[] {
                (int) Math.floor(length),
                (int) ((length % 1)*60)
        };
    }

    public Game createRandomGame(int game_type) {
        int hero = (int)(Math.random() * 28) + 1;
        int position = (int)(Math.random()*3) + 1;
        int kills = (int)(Math.random()* 20) + 1;
        int deaths = (int)(Math.random()* 20) + 1;
        int assists = (int)(Math.random()* 20) + 1;
        float length = getLengthFrom((int)(Math.random()*15)+10,(int)(Math.random()*60)+1);
        int multiplier = (int)(Math.random()*9) + 3;
        int cs = multiplier * getLengthFrom(length)[0];
        int gold = cs * (int)(Math.random() * 20 + 20) + kills * (int)(Math.random() * 200 + 200);
        int total_kills = kills + (int)(Math.random()*25);
        Player_Results player_results = new Player_Results(hero, position, kills, deaths, assists, cs, gold, length, total_kills);
        boolean victory = Math.random()>0.5?true:false;
        long timestamp = System.currentTimeMillis();
        float vg_version = 1.24F;
        int rand_deaths = (int)(Math.max(total_kills,Math.random()*30));
        int total_deaths = victory?Math.max(total_kills-rand_deaths,deaths):Math.max(total_kills+rand_deaths,deaths);
        Game game = new Game(length, victory, timestamp, game_type, vg_version, player_results, total_kills, total_deaths);

        return game;
    }
}
