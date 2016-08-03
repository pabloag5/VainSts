package dev.kodama.test.utils;

/**
 * Created by JuanCamilo on 8/4/2016.
 */
public class Stats {

    /**
     * Total/heroe
     */
    private String type;
    /**
     * Can be null
     */
    private String heroe;
    /**
     * Can be null/all/lane/jungle/roam
     */
    private String position;

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

    public Stats(int wins, int total_games, float kills_per_game, float deaths_per_game, float assists_per_game, float cs_min_per_game, float gold_min_per_game, float gold_per_game, float kda_per_game, float kill_participation_per_game, String position, String heroe, String type) {
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
        this.heroe = heroe;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getHeroe() {
        return heroe;
    }

    public String getPosition() {
        return position;
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
}
