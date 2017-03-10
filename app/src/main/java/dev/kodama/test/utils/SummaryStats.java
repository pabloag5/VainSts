package dev.kodama.test.utils;

import android.content.Context;

/**
 * Created by JuanCamilo on 8/4/2016.
 */
public class SummaryStats {

    public final static int TYPE_TOTAL = 1;
    public final static int TYPE_HERO = 2;


    /**
     * Either {@link #TYPE_TOTAL } or {@link #TYPE_HERO }
     */
    private int type;
    /**
     * One of {@link Constants.Heroes.Ids}
     */
    private int heroId;
    /**
     * One of {@link Constants.Positions}
     */
    private int position;

    /**
     * One of {@link Constants.Game_Types}
     */
    private int game_type;

    private int wins;
    private int losses;
    private int total_games;
    private float kills_per_game;
    private float deaths_per_game;
    private float assists_per_game;
    private float cs_min_per_game;
    private float gold_min_per_game;
    private float gold_per_game;
    private float kda_per_game;
    private float kill_participation_per_game;

    public SummaryStats(int wins, int total_games, float kills_per_game, float deaths_per_game, float assists_per_game, float cs_min_per_game, float gold_min_per_game, float gold_per_game, float kda_per_game, float kill_participation_per_game, int position, int heroId, int type, int game_type) {
        this.wins = wins;
        this.total_games = total_games;
        losses = total_games - wins;
        this.kills_per_game = kills_per_game;
        this.deaths_per_game = deaths_per_game;
        this.assists_per_game = assists_per_game;
        this.cs_min_per_game = cs_min_per_game;
        this.gold_min_per_game = gold_min_per_game;
        this.gold_per_game = gold_per_game;
        this.kda_per_game = kda_per_game;
        this.kill_participation_per_game = kill_participation_per_game;
        this.position = position;
        this.heroId = heroId;
        this.type = type;
        this.game_type = game_type;
    }

    public int getType() {
        return type;
    }

    public int getGameType() {
        return game_type;
    }

    public String getGameTypeString(Context context) {
        return HalcyonUtils.getGameTypeStringFromId(context, game_type);
    }

    public int getHeroId() {
        return heroId;
    }

    public String getHeroString(Context context) {
        if(heroId!= Constants.Heroes.Ids.NO_HERO)
            return HalcyonUtils.getHeroNameFromId(context, heroId);
        else
            return null;
    }

    public int getPosition() {
        return position;
    }

    public String getPositionString(Context context) {
        return HalcyonUtils.getPositionNameFromId(context, position);
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTotal_games() {
        return total_games;
    }

    public float getKills_per_game() {
        return kills_per_game;
    }

    public float getDeaths_per_game() {
        return deaths_per_game;
    }

    public float getAssists_per_game() {
        return assists_per_game;
    }

    public float getCs_min_per_game() {
        return cs_min_per_game;
    }

    public float getGold_min_per_game() {
        return gold_min_per_game;
    }

    public float getGold_per_game() {
        return gold_per_game;
    }

    public float getKda_per_game() {
        return kda_per_game;
    }

    public float getKill_participation_per_game() {
        return kill_participation_per_game;
    }

    public float getWinRatio() {
        return (float) getWins()/getTotal_games();
    }

}
