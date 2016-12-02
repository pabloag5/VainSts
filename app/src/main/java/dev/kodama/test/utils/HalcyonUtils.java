package dev.kodama.test.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

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
}
