package dev.kodama.test.utils;

import android.content.Context;

/**
 * Created by JuanCamilo on 8/4/2016.
 */
public class HalcyonUtils {

    public static int getPositionFromStatistics_DB_type(String type) {
        int position = 0;
        switch (type){
            case Constants.Statistics_DB.HERO_ALL:
                position = Constants.Positions.ALL;
                break;
            case Constants.Statistics_DB.HERO_LANE:
                position = Constants.Positions.LANE;
                break;
            case Constants.Statistics_DB.HERO_JUNGLE:
                position = Constants.Positions.JUNGLE;
                break;
            case Constants.Statistics_DB.HERO_ROAM:
                position = Constants.Positions.ROAM;
                break;
            case Constants.Statistics_DB.TOTAL_ALL:
                position = Constants.Positions.ALL;
                break;
            case Constants.Statistics_DB.TOTAL_LANE:
                position = Constants.Positions.LANE;
                break;
            case Constants.Statistics_DB.TOTAL_JUNGLE:
                position = Constants.Positions.JUNGLE;
                break;
            case Constants.Statistics_DB.TOTAL_ROAM:
                position = Constants.Positions.JUNGLE;
                break;
        }
        return position;
    }

    public static String getHeroeStatistics_DBTypeFromPosition(int position) {
        String type = "";
        switch (position){
            case Constants.Positions.LANE:
                type = Constants.Statistics_DB.HERO_LANE;
                break;
            case Constants.Positions.JUNGLE:
                type = Constants.Statistics_DB.HERO_JUNGLE;
                break;
            case Constants.Positions.ROAM:
                type = Constants.Statistics_DB.HERO_ROAM;
                break;

        }
        return type;
    }

    public static String getTotalStatistics_DBTypeFromPosition(int position) {
        String type = "";
        switch (position){
            case Constants.Positions.LANE:
                type = Constants.Statistics_DB.TOTAL_LANE;
                break;
            case Constants.Positions.JUNGLE:
                type = Constants.Statistics_DB.TOTAL_JUNGLE;
                break;
            case Constants.Positions.ROAM:
                type = Constants.Statistics_DB.TOTAL_ROAM;
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
}
