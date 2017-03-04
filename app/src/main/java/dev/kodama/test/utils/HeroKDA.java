package dev.kodama.test.utils;

/**
 * Created by kodama on 3/2/17.
 */

public class HeroKDA {

    private int hero_id;
    //private String position;
    private int kills;
    private int assists;
    private int deaths;
    private float kda;
    private int games;

    public HeroKDA(int hero_id, int kills, int assists, int deaths, int games) {
        this.hero_id=hero_id;
        this.kills=kills;
        this.assists=assists;
        this.deaths=deaths;
        kda=(float) (kills+assists)/(deaths+1);
        this.games=games;
    }

    public int getHero_id(){
        return hero_id;
    }
    public int getKills() {
        return kills;
    }
    public int getAssists(){
        return assists;
    }
    public int getDeaths(){
        return deaths;
    }
    public float getKda(){
        return kda;
    }
    public int getGames(){
        return games;
    }

}
