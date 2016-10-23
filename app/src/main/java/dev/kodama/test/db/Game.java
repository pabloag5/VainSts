package dev.kodama.test.db;

/**
 * Created by researcher on 12/05/16.
 */
public class Game {

    private float length;
//    private boolean home_team_win;
    private boolean victory;
//    private Team home_team;
//    private Team away_team;
    /**
     * Date the game was played in unixtime milliseconds since 1970
     */
    private long timestamp;
    private int game_type;
//    private int queue_type;
    private float vg_version;
//    private ArrayList<Player_Results> home_team_results;
//    private ArrayList<Player_Results> away_team_results;
    private Player_Results results;

    private int total_kills;

    private int total_deaths;

    public Game(float length, boolean victory, long timestamp, int game_type, float vg_version, Player_Results results, int total_kills, int total_deaths) {
        this.length = length;
        this.victory = victory;
        this.timestamp = timestamp;
        this.game_type = game_type;
//        this.queue_type = queue_type;
        this.vg_version = vg_version;
        this.total_kills = total_kills;
        this.total_deaths = total_deaths;

        this.results = results;
    }

    public float getLength() {
        return length;
    }

//    public boolean isHome_team_win() {
//        return home_team_win;
//    }

//    public Team getHome_team() {
//        return home_team;
//    }

//    public Team getAway_team() {
//        return away_team;
//    }

    public boolean isVictory() {
        return victory;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getGame_type() {
        return game_type;
    }

//    public String getQueue_type() {
//        return queue_type;
//    }

    public float getVg_version() {
        return vg_version;
    }

    public Player_Results getResults() {
        return results;
    }

    public int getTotalDeaths() {
        return total_deaths;
    }

    public int getTotalKills() {
        return total_kills;
    }

//    public ArrayList<Player_Results> getAway_team_results() {
//        return away_team_results;
//    }
}
