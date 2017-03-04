package dev.kodama.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.kodama.test.utils.HeroKDA;
import dev.kodama.test.utils.SummaryStats;

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
    public void sortRoleHeroesByWinRatio(List<SummaryStats> herostats) {
        Collections.sort(herostats, new Comparator<SummaryStats>() {
            @Override
            public int compare(SummaryStats lhs, SummaryStats rhs) {
                if (lhs.getWinRatio()<rhs.getWinRatio()) {
                    return 1;
                }
                else if (lhs.getWinRatio()>rhs.getWinRatio()) {
                    return -1;
                } else return 0;

            }
        });
    }
    public void sortRoleHeroesByKdaRatio(List<SummaryStats> herostats){
        Collections.sort(herostats, new Comparator<SummaryStats>() {
            @Override
            public int compare(SummaryStats lhs, SummaryStats rhs) {
                if (lhs.getKda_per_game()<rhs.getKda_per_game()) {
                    return 1;
                }
                else if (lhs.getKda_per_game()>rhs.getKda_per_game()){
                    return -1;
                } else return 0;
            }
        });
    }
    public void sortRoleHeroesByCS(List<SummaryStats> herostats){
        Collections.sort(herostats, new Comparator<SummaryStats>() {
            @Override
            public int compare(SummaryStats lhs, SummaryStats rhs) {
                if (lhs.getCs_min_per_game()<rhs.getCs_min_per_game()) {
                    return 1;
                }
                else if (lhs.getCs_min_per_game()>rhs.getCs_min_per_game()){
                    return -1;
                } else return 0;
            }
        });
    }
    public void sortRoleHeroesByGold(List<SummaryStats> herostats){
        Collections.sort(herostats, new Comparator<SummaryStats>() {
            @Override
            public int compare(SummaryStats lhs, SummaryStats rhs) {
                if (lhs.getGold_min_per_game()<rhs.getGold_min_per_game()) {
                    return 1;
                }
                else if (lhs.getGold_min_per_game()>rhs.getGold_min_per_game()){
                    return -1;
                } else return 0;
            }
        });
    }
    public void sortKDAHighHeroes(List<HeroKDA> herostats){
        Collections.sort(herostats, new Comparator<HeroKDA>() {
            @Override
            public int compare(HeroKDA lhs, HeroKDA rhs) {
                if (lhs.getKda()<rhs.getKda()) {
                    return 1;
                }
                else if (lhs.getKda()>rhs.getKda()){
                    return -1;
                } else return 0;
            }
        });
    }
    public void sortKDALowHeroes(List<HeroKDA> herostats){
        Collections.sort(herostats, new Comparator<HeroKDA>() {
            @Override
            public int compare(HeroKDA lhs, HeroKDA rhs) {
                if (lhs.getKda()>rhs.getKda()) {
                    return 1;
                }
                else if (lhs.getKda()<rhs.getKda()){
                    return -1;
                } else return 0;
            }
        });
    }
}
