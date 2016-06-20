package dev.kodama.test.db;

/**
 * Created by researcher on 12/05/16.
 */
public class Player {

    private String ign;
    private String name;
    private String main_position;
    private Hero main_hero;

    public Player(String ign, String name, String main_position, Hero main_hero) {
        this.ign = ign;
        this.name = name;
        this.main_position = main_position;
        this.main_hero = main_hero;
    }

    public String getIgn() {
        return ign;
    }

    public String getName() {
        return name;
    }

    public String getMain_position() {
        return main_position;
    }

    public Hero getMain_hero() {
        return main_hero;
    }

    public void setIgn(String ign) {
        this.ign = ign;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMain_position(String main_position) {
        this.main_position = main_position;
    }

    public void setMain_hero(Hero main_hero) {
        this.main_hero = main_hero;
    }
}
