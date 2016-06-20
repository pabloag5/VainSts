package dev.kodama.test.db;

import java.util.ArrayList;

/**
 * Created by researcher on 12/05/16.
 */
public class Game {

    private float length;
    private boolean home_team_win;
    private Team home_team;
    private Team away_team;
    private long timestamp;
    private String game_type;
    private String queue_type;
    private float vg_version;
    private ArrayList<Player_Results> home_team_results;
    private ArrayList<Player_Results> away_team_results;

    public Game(float length, boolean home_team_win, Team home_team, Team away_team, long timestamp, String game_type, String queue_type, float vg_version, ArrayList<Player_Results> home_team_results, ArrayList<Player_Results> away_team_results) {
        this.length = length;
        this.home_team_win = home_team_win;
        this.home_team = home_team;
        this.away_team = away_team;
        this.timestamp = timestamp;
        this.game_type = game_type;
        this.queue_type = queue_type;
        this.vg_version = vg_version;
        this.home_team_results = home_team_results;
        this.away_team_results = away_team_results;
    }

    public float getLength() {
        return length;
    }

    public boolean isHome_team_win() {
        return home_team_win;
    }

    public Team getHome_team() {
        return home_team;
    }

    public Team getAway_team() {
        return away_team;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getGame_type() {
        return game_type;
    }

    public String getQueue_type() {
        return queue_type;
    }

    public float getVg_version() {
        return vg_version;
    }

    public ArrayList<Player_Results> getHome_team_results() {
        return home_team_results;
    }

    public ArrayList<Player_Results> getAway_team_results() {
        return away_team_results;
    }
}
