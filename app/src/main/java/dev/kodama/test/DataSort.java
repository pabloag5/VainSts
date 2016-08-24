package dev.kodama.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kodama on 8/2/16.
 */
public class DataSort {
    public void sortHeroesByName(List<Gamestats> herostats) {
        Collections.sort(herostats, new Comparator<Gamestats>() {
            @Override
            public int compare(Gamestats lhs, Gamestats rhs) {
                return lhs.getHeroName().compareTo(rhs.getHeroName());
            }
        });
    }
    public void sortHeroesByWinRatio(List<Gamestats> herostats) {
        Collections.sort(herostats, new Comparator<Gamestats>() {
            @Override
            public int compare(Gamestats lhs, Gamestats rhs) {
                if (lhs.getWinRatio()<rhs.getWinRatio()) {
                    return 1;
                }
                else if (lhs.getWinRatio()>rhs.getWinRatio()) {
                    return -1;
                } else return 0;

            }
        });
    }
    public void sortHeroesByKdaRatio(List<Gamestats> herostats){
        Collections.sort(herostats, new Comparator<Gamestats>() {
            @Override
            public int compare(Gamestats lhs, Gamestats rhs) {
                if (lhs.getKdaRatio()<rhs.getKdaRatio()) {
                    return 1;
                }
                else if (lhs.getKdaRatio()>rhs.getKdaRatio()){
                    return -1;
                } else return 0;
            }
        });
    }
}
