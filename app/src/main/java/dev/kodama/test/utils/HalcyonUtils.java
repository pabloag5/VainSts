package dev.kodama.test.utils;

/**
 * Created by JuanCamilo on 8/4/2016.
 */
public class HalcyonUtils {

    public static String getPositionFromStatistics_DB_type(String type) {
        String position = "";
        switch (type){
            case Constants.Statistics_DB.HEROE_ALL:
                position = "all";
                break;
            case Constants.Statistics_DB.HEROE_LANE:
                position = "lane";
                break;
            case Constants.Statistics_DB.HEROE_JUNGLE:
                position = "jungle";
                break;
            case Constants.Statistics_DB.HEROE_ROAM:
                position = "roam";
                break;
            case Constants.Statistics_DB.TOTAL_ALL:
                position = "all";
                break;
            case Constants.Statistics_DB.TOTAL_LANE:
                position = "lane";
                break;
            case Constants.Statistics_DB.TOTAL_JUNGLE:
                position = "jungle";
                break;
            case Constants.Statistics_DB.TOTAL_ROAM:
                position = "roam";
                break;
        }
        return position;
    }

    public static String getHeroeStatistics_DBTypeFromPosition(String position) {
        String type = "";
        switch (position){
            case "lane":
                type = Constants.Statistics_DB.HEROE_LANE;
                break;
            case "jungle":
                type = Constants.Statistics_DB.HEROE_JUNGLE;
                break;
            case "roam":
                type = Constants.Statistics_DB.HEROE_ROAM;
                break;

        }
        return type;
    }

    public static String getTotalStatistics_DBTypeFromPosition(String position) {
        String type = "";
        switch (position){
            case "lane":
                type = Constants.Statistics_DB.TOTAL_LANE;
                break;
            case "jungle":
                type = Constants.Statistics_DB.TOTAL_JUNGLE;
                break;
            case "roam":
                type = Constants.Statistics_DB.TOTAL_ROAM;
                break;

        }
        return type;
    }
}
