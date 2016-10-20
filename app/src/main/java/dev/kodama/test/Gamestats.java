package dev.kodama.test;

import android.graphics.drawable.Drawable;

/**
 * Created by kodama on 3/20/16.
 */
public class Gamestats {
    int heroIcon;
    Drawable heroImage;
    String  heroName;
    float kdaRatio;
    float winRatio;

    public Gamestats() {

    }
    public String getHeroName() {
        return  heroName;
    }
    public float getKdaRatio() {
        return kdaRatio;
    }
    public float getWinRatio() {
        return winRatio;
    }
    public int getHeroIcon(){
        return heroIcon;
    }
    public Drawable getHeroImage() {
        return heroImage;
    }

}
