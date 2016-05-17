package dev.kodama.test.db;

/**
 * Created by researcher on 12/05/16.
 */
public class Hero {

    private String name;
    private String position;
    private String type;

    public Hero(String name, String position, String type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setType(String type) {
        this.type = type;
    }
}
