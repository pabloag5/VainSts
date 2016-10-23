package dev.kodama.test.utils;

/**
 * Created by JuanCamilo on 10/23/2016.
 */

public class GameStats {

    private boolean victory;

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

    private long timestamp;

    private int teamKills;

    private int teamDeaths;

    private int kills;
    private int deaths;
    private int assists;
    private int cs;
    private float cs_per_min;
    private int gold;
    private float kda;
    private float kill_participation;

    private float length;

    public GameStats(long timestamp, boolean victory, int heroId, int position, int game_type, int teamKills, int teamDeaths, int kills, int deaths, int assists, int cs, float cs_per_min, int gold, float kda, float kill_participation, float length) {
        this.timestamp = timestamp;
        this.victory = victory;
        this.heroId = heroId;
        this.position = position;
        this.game_type = game_type;
        this.teamKills = teamKills;
        this.teamDeaths = teamDeaths;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.cs = cs;
        this.cs_per_min = cs_per_min;
        this.gold = gold;
        this.kda = kda;
        this.kill_participation = kill_participation;
        this.length = length;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isVictory() {
        return victory;
    }

    public int getHeroId() {
        return heroId;
    }

    public int getPosition() {
        return position;
    }

    public int getGame_type() {
        return game_type;
    }

    public int getTeamKills() {
        return teamKills;
    }

    public int getTeamDeaths() {
        return teamDeaths;
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

    public float getCs_per_min() {
        return cs_per_min;
    }

    public int getGold() {
        return gold;
    }

    public float getKda() {
        return kda;
    }

    public float getKill_participation() {
        return kill_participation;
    }

    public float getLength() {
        return length;
    }
}
