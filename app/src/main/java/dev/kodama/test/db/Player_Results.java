package dev.kodama.test.db;

/**
 * Created by researcher on 12/05/16.
 */
public class Player_Results {

//    private Player player;
    private int hero;
//    private String build_type;
    private int position;
    private int kills;
    private int deaths;
    private int assists;
    private int cs;
    private int gold;
    private float kda_ratio;
    private float cs_min;
    private float gold_min;
    private float kill_participation;

    public Player_Results(int hero, int position, int kills, int deaths, int assists, int cs, int gold, float length, int total_kills) {
//        this.player = player;
        this.hero = hero;
//        this.build_type = build_type;
        this.position = position;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.cs = cs;
        this.gold = gold;

        kda_ratio = (float) (kills + assists)/(deaths+1);
        cs_min = (float) cs/length;
        gold_min = (float) gold/length;
        kill_participation = (float) (kills + assists) / total_kills;

    }

//    public Player_Results(Player player, Hero hero, String build_type, String position, int kills, int deaths, int assists, int cs, int gold, float kda_ratio, float cs_min, float gold_min, float kill_participation) {
//        this.player = player;
//        this.hero = hero;
//        this.build_type = build_type;
//        this.position = position;
//        this.kills = kills;
//        this.deaths = deaths;
//        this.assists = assists;
//        this.cs = cs;
//        this.gold = gold;
//        this.kda_ratio = kda_ratio;
//        this.cs_min = cs_min;
//        this.gold_min = gold_min;
//        this.kill_participation = kill_participation;
//    }

//    public Player getPlayer() {
//        return player;
//    }

    public int getHero() {
        return hero;
    }

//    public String getBuild_type() {
//        return build_type;
//    }

    public int getPosition() {
        return position;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getCs() {
        return cs;
    }

    public int getGold() {
        return gold;
    }

    public float getKda_ratio() {
        return kda_ratio;
    }

    public float getCs_min() {
        return cs_min;
    }

    public float getGold_min() {
        return gold_min;
    }

    public float getKill_participation() {
        return kill_participation;
    }
}
