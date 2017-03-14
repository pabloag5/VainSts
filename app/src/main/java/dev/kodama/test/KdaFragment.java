package dev.kodama.test;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.kodama.test.db.DatabaseTransactions;
import dev.kodama.test.db.Game;
import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.GameStats;
import dev.kodama.test.utils.HalcyonUtils;
import dev.kodama.test.utils.HeroKDA;
import dev.kodama.test.utils.SummaryStats;


public class KdaFragment extends Fragment {
    private Button monthsBtn, seasonBtn, patchBtn;
    private LinearLayout bestkdaHeroesBtn, kdaexpandLayout, kdadetaillayout;
    private RadioGroup kdasortHeroes;
    private RadioButton highkdaHeroes;
    private DataSort mdataSort = new DataSort();
    private ArrayList<HeroKDA> genericArray=new ArrayList<>();
    private ArrayList<HeroKDA> monthsArray=new ArrayList<>();
    private ArrayList<HeroKDA> seasonArray=new ArrayList<>();
    private ArrayList<HeroKDA> patchArray=new ArrayList<>();
    private ArrayList<HeroKDA> rolesmonths=new ArrayList<>();
    private ArrayList<HeroKDA> rolesseason=new ArrayList<>();
    private ArrayList<HeroKDA> rolespatch=new ArrayList<>();
    private LayoutTransition layoutTransition;
    private RecyclerView recyclerView;
    private kdaViewAdapter adapter;
    LinearLayoutManager kdalayoutManager = new LinearLayoutManager(getActivity());
    private DatabaseTransactions dbTrans;
    private TextView kdakills, kdadeaths, kdaassists, kdaoverall, kdasortbtn;
    private ProgressBar kdaoverallgraph, killsbar, deathsbar, assistsbar;
    private float kills_m=0;
    private float deaths_m=0;
    private float assists_m=0;
    private float kda_m=0;
    private float kills_s=0;
    private float deaths_s=0;
    private float assists_s=0;
    private float kda_s=0;
    private float kills_p=0;
    private float deaths_p=0;
    private float assists_p=0;
    private float kda_p=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.kdalayout, container, false);

        dbTrans = DatabaseTransactions.getInstance(getContext());

        monthsBtn=(Button) view.findViewById(R.id.monthsbtn);
        seasonBtn=(Button) view.findViewById(R.id.seasonbtn);
        patchBtn=(Button) view.findViewById(R.id.patchbtn);
        kdaoverall=(TextView) view.findViewById(R.id.kdagraph);
        kdaoverallgraph=(ProgressBar) view.findViewById(R.id.kdadetailprogress);
        kdakills=(TextView) view.findViewById(R.id.killskda);
        killsbar=(ProgressBar) view.findViewById(R.id.killsbar);
        kdadeaths=(TextView) view.findViewById(R.id.deathskda);
        deathsbar=(ProgressBar) view.findViewById(R.id.deathsbar);
        kdaassists=(TextView) view.findViewById(R.id.assistskda);
        assistsbar=(ProgressBar) view.findViewById(R.id.assistsbar);
        kdasortHeroes=(RadioGroup) view.findViewById(R.id.kdasortHeroes);
        highkdaHeroes=(RadioButton) view.findViewById(R.id.byHighKDA);
        kdasortbtn=(TextView) view.findViewById(R.id.kdabtntext);
        recyclerView=(RecyclerView) view.findViewById(R.id.kdaheroeslist);
        bestkdaHeroesBtn=(LinearLayout) view.findViewById(R.id.kdasortbtn);
        kdaexpandLayout=(LinearLayout) view.findViewById(R.id.kdaExpandDetail);
        kdadetaillayout=(LinearLayout) view.findViewById(R.id.kdadetaillayout);


        /**
         * initiate page view
         */
        get3monthsstats();
        getSeasonstats();
        getLastpatchstats();
        monthsBtn.setSelected(true);
        genericArray=monthsArray;
        highkdaHeroes.setChecked(true);
        /**
         * set recyclerview
         */
        adapter =new kdaViewAdapter(getActivity(),genericArray);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(kdalayoutManager);
        updateKDAStats();
        sortByHighKDA();

        /**fill kda stats
         * first 3 months stats
         * second season stats
         * third patch stats
         */
        monthsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genericArray=monthsArray;
                adapter.setData(genericArray);
                monthsBtn.setSelected(true);
                seasonBtn.setSelected(false);
                patchBtn.setSelected(false);
                updateKDAStats();
                sortByHighKDA();
                highkdaHeroes.setChecked(true);
            }
        });
        seasonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genericArray=seasonArray;
                adapter.setData(genericArray);
                monthsBtn.setSelected(false);
                seasonBtn.setSelected(true);
                patchBtn.setSelected(false);
                updateKDAStats();
                sortByHighKDA();
                highkdaHeroes.setChecked(true);
            }
        });
        patchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genericArray=patchArray;
                adapter.setData(genericArray);
                monthsBtn.setSelected(false);
                seasonBtn.setSelected(false);
                patchBtn.setSelected(true);
                updateKDAStats();
                sortByHighKDA();
                highkdaHeroes.setChecked(true);
            }
        });

        /**
         * set Best heroes button text
         */
        switch (kdasortHeroes.getCheckedRadioButtonId()){
            case R.id.byHighKDA:
                kdasortbtn.setText("HIGH "+getResources().getText(R.string.kda_lbl)+" HEROES");
                break;
            case R.id.byLowKDA:
                kdasortbtn.setText("LOW "+getResources().getText(R.string.kda_lbl)+" HEROES");
                break;
            case R.id.byRole:
                kdasortbtn.setText("ROLE "+getResources().getText(R.string.kda_lbl)+" HEROES");
                break;
        }
        /**
         * set expand layout for sorting list option
         */
        bestkdaHeroesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandKDASortHeroes(kdaexpandLayout);
            }
        });
        kdadetaillayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKDALayout(kdaexpandLayout);
            }
        });
        /**
         * sort recyclerview
         */
        kdasortHeroes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sortKDAHeroes(checkedId);
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * expand sort options layout
     * @param linearLayout
     */
    private void expandKDASortHeroes(LinearLayout linearLayout){
        if (linearLayout.getVisibility()==View.GONE){
            linearLayout.setVisibility(View.VISIBLE);
        } else linearLayout.setVisibility(View.GONE);
    }

    /**
     * hide sort options layout
     * @param linearLayout
     */
    private void closeKDALayout(LinearLayout linearLayout){
        if (linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
        }
    }

    /**
     * sort heroes recylerview
     * @param checkedId
     */
    private void sortKDAHeroes(int checkedId){
        switch (checkedId){
            case R.id.byHighKDA:
                kdasortbtn.setText("HIGH "+getResources().getText(R.string.kda_lbl)+" HEROES");
                sortByHighKDA();
                break;
            case R.id.byLowKDA:
                kdasortbtn.setText("LOW "+getResources().getText(R.string.kda_lbl)+" HEROES");
                sortByLowKDA();
                break;
            case R.id.byRole:
                kdasortbtn.setText("ROLE "+getResources().getText(R.string.kda_lbl)+" HEROES");
                sortByRoleKDA();
                break;
        }
    }

    public void sortByHighKDA() {
        if (monthsBtn.isSelected()){
            genericArray=monthsArray;
        }
        if (seasonBtn.isSelected()){
            genericArray=seasonArray;
        }
        if (patchBtn.isSelected()){
            genericArray=patchArray;
        }
        mdataSort.sortKDAHighHeroes(genericArray);
        adapter.setData(genericArray);
    }

    public void sortByLowKDA() {
        if (monthsBtn.isSelected()){
            genericArray=monthsArray;
        }
        if (seasonBtn.isSelected()){
            genericArray=seasonArray;
        }
        if (patchBtn.isSelected()){
            genericArray=patchArray;
        }
        mdataSort.sortKDALowHeroes(genericArray);
        adapter.setData(genericArray);
    }

    public void sortByRoleKDA() {
        if (monthsBtn.isSelected()){
            genericArray=rolesmonths;
        }
        if (seasonBtn.isSelected()){
            genericArray=rolesseason;
        }
        if (patchBtn.isSelected()){
            genericArray=rolespatch;
        }
        adapter.setData(genericArray);
    }


    /**
     * cirle crop image
     * @param image
     * @return
     */
    public Drawable circleImage (int image){
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), image);
        RoundedBitmapDrawable circularImage = RoundedBitmapDrawableFactory.create(getResources(), bitmapImage);
        circularImage.setCircular(true);
        return circularImage;
    }

    private void get3monthsstats(){
        int i=0;
        int temp=0;
        int n=15;
        HeroKDA hero_info;
        ArrayList<GameStats> statsArray = new ArrayList<>();
        ArrayList<HeroKDA> tempArray = new ArrayList<>();
        HashMap<Integer,HeroKDA> allKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> laneKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> jungleKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> roamKDA=new HashMap<>();

        statsArray= dbTrans.getLastNGamesStats(Constants.Game_Types.RANKED,n,null,null,null,null);
        while (i<statsArray.size()){
            kills_m+=statsArray.get(i).getKills();
            deaths_m+=statsArray.get(i).getDeaths();
            assists_m+=statsArray.get(i).getAssists();
            kda_m = (float) (kills_m + assists_m)/(deaths_m+1);
            hero_info=new HeroKDA(statsArray.get(i).getHeroId(),statsArray.get(i).getKills(),
                    statsArray.get(i).getAssists(),statsArray.get(i).getDeaths(),1);
            if(allKDA.get(hero_info.getHero_id())!=null){
                allKDA.put(hero_info.getHero_id(),
                new HeroKDA(hero_info.getHero_id(),
                        hero_info.getKills()+ allKDA.get(hero_info.getHero_id()).getKills(),
                        hero_info.getAssists()+allKDA.get(hero_info.getHero_id()).getAssists(),
                        hero_info.getDeaths()+allKDA.get(hero_info.getHero_id()).getDeaths(),
                        hero_info.getGames()+allKDA.get(hero_info.getHero_id()).getGames())
                );
            } else {
                allKDA.put(hero_info.getHero_id(), hero_info);
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.LANE) {
                if(laneKDA.get(hero_info.getHero_id())!=null){
                    laneKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ laneKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+laneKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+laneKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+laneKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    laneKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.JUNGLE) {
                if(jungleKDA.get(hero_info.getHero_id())!=null){
                    jungleKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ jungleKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+jungleKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+jungleKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+jungleKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    jungleKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.ROAM) {
                if(roamKDA.get(hero_info.getHero_id())!=null){
                    roamKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ roamKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+roamKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+roamKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+roamKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    roamKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            i++;
        }
        kills_m=kills_m/n;
        deaths_m=deaths_m/n;
        assists_m=assists_m/n;
        for(Map.Entry<Integer, HeroKDA> entry : allKDA.entrySet()){
            monthsArray.add(entry.getValue());
        }
        for(Map.Entry<Integer, HeroKDA> entry : laneKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolesmonths.add(tempArray.get(0));
                tempArray.remove(0);
            }

        }

        for(Map.Entry<Integer, HeroKDA> entry : jungleKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolesmonths.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }

        for(Map.Entry<Integer, HeroKDA> entry : roamKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolesmonths.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }

    }
    private void getLastpatchstats(){
        int i=0;
        int temp=0;
        int n=5;
        HeroKDA hero_info;
        ArrayList<GameStats> statsArray = new ArrayList<>();
        ArrayList<HeroKDA> tempArray = new ArrayList<>();
        HashMap<Integer,HeroKDA> allKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> laneKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> jungleKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> roamKDA=new HashMap<>();

        statsArray= dbTrans.getLastNGamesStats(Constants.Game_Types.RANKED,n,null,null,null,null);
        while (i<statsArray.size()){
            kills_p+=statsArray.get(i).getKills();
            deaths_p+=statsArray.get(i).getDeaths();
            assists_p+=statsArray.get(i).getAssists();
            kda_p = (float) (kills_p + assists_p)/(deaths_p+1);
            hero_info=new HeroKDA(statsArray.get(i).getHeroId(),statsArray.get(i).getKills(),
                    statsArray.get(i).getAssists(),statsArray.get(i).getDeaths(),1);
            if(allKDA.get(hero_info.getHero_id())!=null){
                allKDA.put(hero_info.getHero_id(),
                        new HeroKDA(hero_info.getHero_id(),
                                hero_info.getKills()+ allKDA.get(hero_info.getHero_id()).getKills(),
                                hero_info.getAssists()+allKDA.get(hero_info.getHero_id()).getAssists(),
                                hero_info.getDeaths()+allKDA.get(hero_info.getHero_id()).getDeaths(),
                                hero_info.getGames()+allKDA.get(hero_info.getHero_id()).getGames())
                );
            } else {
                allKDA.put(hero_info.getHero_id(), hero_info);
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.LANE) {
                if(laneKDA.get(hero_info.getHero_id())!=null){
                    laneKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ laneKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+laneKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+laneKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+laneKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    laneKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.JUNGLE) {
                if(jungleKDA.get(hero_info.getHero_id())!=null){
                    jungleKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ jungleKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+jungleKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+jungleKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+jungleKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    jungleKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.ROAM) {
                if(roamKDA.get(hero_info.getHero_id())!=null){
                    roamKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ roamKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+roamKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+roamKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+roamKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    roamKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            i++;
        }
        kills_p=kills_p/n;
        deaths_p=deaths_p/n;
        assists_p=assists_p/n;
        for(Map.Entry<Integer, HeroKDA> entry : allKDA.entrySet()){
            patchArray.add(entry.getValue());
        }
        for(Map.Entry<Integer, HeroKDA> entry : laneKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null) {
            if (tempArray.isEmpty()!=true){
                rolespatch.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }

        for(Map.Entry<Integer, HeroKDA> entry : jungleKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolespatch.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }
        for(Map.Entry<Integer, HeroKDA> entry : roamKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolespatch.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }

    }
    private void getSeasonstats(){
        int i=0;
        int temp=0;
        int n=10;
        HeroKDA hero_info;
        ArrayList<GameStats> statsArray = new ArrayList<>();
        ArrayList<HeroKDA> tempArray = new ArrayList<>();
        HashMap<Integer,HeroKDA> allKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> laneKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> jungleKDA=new HashMap<>();
        HashMap<Integer, HeroKDA> roamKDA=new HashMap<>();

        statsArray= dbTrans.getLastNGamesStats(Constants.Game_Types.RANKED,n,null,null,null,null);
        while (i<statsArray.size()){
            kills_s+=statsArray.get(i).getKills();
            deaths_s+=statsArray.get(i).getDeaths();
            assists_s+=statsArray.get(i).getAssists();
            kda_s = (float) (kills_s + assists_s)/(deaths_s+1);
            hero_info=new HeroKDA(statsArray.get(i).getHeroId(),statsArray.get(i).getKills(),
                    statsArray.get(i).getAssists(),statsArray.get(i).getDeaths(),1);
            if(allKDA.get(hero_info.getHero_id())!=null){
                allKDA.put(hero_info.getHero_id(),
                        new HeroKDA(hero_info.getHero_id(),
                                hero_info.getKills()+ allKDA.get(hero_info.getHero_id()).getKills(),
                                hero_info.getAssists()+allKDA.get(hero_info.getHero_id()).getAssists(),
                                hero_info.getDeaths()+allKDA.get(hero_info.getHero_id()).getDeaths(),
                                hero_info.getGames()+allKDA.get(hero_info.getHero_id()).getGames())
                );
            } else {
                allKDA.put(hero_info.getHero_id(), hero_info);
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.LANE) {
                if(laneKDA.get(hero_info.getHero_id())!=null){
                    laneKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ laneKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+laneKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+laneKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+laneKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    laneKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.JUNGLE) {
                if(jungleKDA.get(hero_info.getHero_id())!=null){
                    jungleKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ jungleKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+jungleKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+jungleKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+jungleKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    jungleKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            if (statsArray.get(i).getPosition()==Constants.Positions.ROAM) {
                if(roamKDA.get(hero_info.getHero_id())!=null){
                    roamKDA.put(hero_info.getHero_id(),
                            new HeroKDA(hero_info.getHero_id(),
                                    hero_info.getKills()+ roamKDA.get(hero_info.getHero_id()).getKills(),
                                    hero_info.getAssists()+roamKDA.get(hero_info.getHero_id()).getAssists(),
                                    hero_info.getDeaths()+roamKDA.get(hero_info.getHero_id()).getDeaths(),
                                    hero_info.getGames()+roamKDA.get(hero_info.getHero_id()).getGames())
                    );
                } else {
                    roamKDA.put(hero_info.getHero_id(),hero_info);
                }
            }
            i++;
        }
        kills_s=kills_s/n;
        deaths_s=deaths_s/n;
        assists_s=assists_s/n;
        for(Map.Entry<Integer, HeroKDA> entry : allKDA.entrySet()){
            seasonArray.add(entry.getValue());
        }
        for(Map.Entry<Integer, HeroKDA> entry : laneKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolesseason.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }

        for(Map.Entry<Integer, HeroKDA> entry : jungleKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolesseason.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }

        for(Map.Entry<Integer, HeroKDA> entry : roamKDA.entrySet()){
            tempArray.add(entry.getValue());
        }
        while (temp<tempArray.size() && tempArray.size()!=1){
            if (tempArray.get(temp).getKda()<tempArray.get(temp+1).getKda()){
                tempArray.remove(temp);
            } else {
                tempArray.remove(temp+1);
            }
        }
        if (tempArray!=null){
            if (tempArray.isEmpty()!=true){
                rolesseason.add(tempArray.get(0));
                tempArray.remove(0);
            }
        }

    }
    /**
     * update page objects
     */
    private void updateKDAStats(){
        float kills=0;
        float assists=0;
        float deaths=0;
        float kda=0;
        if (genericArray!=null) {
            if (monthsBtn.isSelected()){
                kills=kills_m;
                assists=assists_m;
                deaths=deaths_m;
                kda=kda_m;
            }
            if (seasonBtn.isSelected()){
                kills=kills_s;
                assists=assists_s;
                deaths=deaths_s;
                kda=kda_s;
            }
            if (patchBtn.isSelected()){
                kills=kills_p;
                assists=assists_p;
                deaths=deaths_p;
                kda=kda_p;
            }
        }
        kdaoverall.setText(String.format("%.1f",kda));
        kdakills.setText(String.valueOf(Math.round(kills)));
        kdadeaths.setText(String.valueOf(Math.round(deaths)));
        kdaassists.setText(String.valueOf(Math.round(assists)));
        kdaoverallgraph.setProgress((int) kda);
        killsbar.setProgress((int)kills);
        deathsbar.setProgress((int)deaths);
        assistsbar.setProgress((int)assists);

    }
    /**
     * recyclerview adapter
     */
    private class kdaViewAdapter extends RecyclerView.Adapter<kdaViewAdapter.MyViewHolder>
    {

        private LayoutInflater inflater;
        List<HeroKDA> data= Collections.emptyList();

        public kdaViewAdapter(Context context, List<HeroKDA> data){
            inflater=LayoutInflater.from(context);
            this.data=data;

        }

        @Override
        public kdaViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view=inflater.inflate(R.layout.kdahero_row,parent,false);
            kdaViewAdapter.MyViewHolder holder=new kdaViewAdapter.MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(kdaViewAdapter.MyViewHolder holder, int position) {
            if (data!=null) {
                HeroKDA current=data.get(position);
                //set view content
                holder.imageView.setImageDrawable(circleImage(HalcyonUtils.getHeroIconFromId(getContext(),current.getHero_id())));
                holder.kda_hero.setText(String.format("%.1f",current.getKda()));
                holder.killsProgress.setProgress((int) Math.round(current.getKills()/current.getGames()));
                holder.deathsProgress.setProgress((int) Math.round(current.getDeaths()/current.getGames()));
                holder.assistsProgress.setProgress((int) Math.round(current.getAssists()/current.getGames()));
            }
        }

        @Override
        public int getItemCount() {
            //return data.size();
            return Math.min(3, data.size());
        }

        //create subclass ViewHolder
        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView kda_hero;
            ProgressBar killsProgress;
            ProgressBar deathsProgress;
            ProgressBar assistsProgress;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView=(ImageView)itemView.findViewById(R.id.hero_icon_kda);
                kda_hero=(TextView) itemView.findViewById(R.id.herokda);
                killsProgress=(ProgressBar) itemView.findViewById(R.id.killsherokdabar);
                deathsProgress=(ProgressBar) itemView.findViewById(R.id.deathsherokdabar);
                assistsProgress=(ProgressBar) itemView.findViewById(R.id.assistsherokdabar);
            }
        }

        //overwrites data list and then call notifyDataSetChanged()
        public void setData(List<HeroKDA> data) {
            this.data=data;
            notifyDataSetChanged();
        }
    }

}
