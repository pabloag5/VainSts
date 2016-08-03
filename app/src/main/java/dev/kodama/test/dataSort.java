package dev.kodama.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kodama on 8/2/16.
 */
public class dataSort {
    public void sortHeroesByName(List<gamestats> herostats) {
        Collections.sort(herostats, new Comparator<gamestats>() {
            @Override
            public int compare(gamestats lhs, gamestats rhs) {
                return lhs.getHeroName().compareTo(rhs.getHeroName());
            }
        });
    }
    public void sortHeroesByWinRatio(List<gamestats> herostats) {
        Collections.sort(herostats, new Comparator<gamestats>() {
            @Override
            public int compare(gamestats lhs, gamestats rhs) {
                if (lhs.getWinRatio()<rhs.getWinRatio()) {
                    return 1;
                }
                else if (lhs.getWinRatio()>rhs.getWinRatio()) {
                    return -1;
                } else return 0;

            }
        });
    }
    public void sortHeroesByKdaRatio(List<gamestats> herostats){
        Collections.sort(herostats, new Comparator<gamestats>() {
            @Override
            public int compare(gamestats lhs, gamestats rhs) {
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
