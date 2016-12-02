package dev.kodama.test;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.kodama.test.db.Database_Helper;
import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.HalcyonUtils;
import dev.kodama.test.utils.Stats;
import dev.kodama.test.utils.SummaryStats;

/**
 * Created by kodama on 4/20/16.
 */
public class roleFragment extends Fragment {

    Button bestHeroesBtn;
    LinearLayout expandLayout;
    RecyclerView bestheroeslist;
    RadioGroup sortHeroes;
    private DataSort mdataSort = new DataSort();
    private List<SummaryStats> mListHeroes=new ArrayList<>();
    private RecyclerView recyclerView;
    private bestHeroesViewAdapter adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    SQLiteDatabase db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.rolelayout, container, false);

        bestHeroesBtn=(Button) view.findViewById(R.id.bestheroesbtn);
        expandLayout=(LinearLayout) view.findViewById(R.id.llExpandDetail);
        bestheroeslist=(RecyclerView) view.findViewById(R.id.bestheroeslist);
        sortHeroes=(RadioGroup) view.findViewById(R.id.sortHeroes);

        recyclerView=(RecyclerView)view.findViewById(R.id.bestheroeslist);
        mListHeroes=getData();

        adapter =new bestHeroesViewAdapter(getActivity(),mListHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        switch (sortHeroes.getCheckedRadioButtonId()){
            case R.id.byWinratio:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.win_ratio_lbl)+" HEROES");
                break;
            case R.id.byCS:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.CS_label)+" HEROES");
                break;
            case R.id.byKda:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.kda_lbl)+" HEROES");
                break;
            case R.id.byGold:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.gold_label)+" HEROES");
                break;
        }

        bestHeroesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandSortHeroes(expandLayout);
            }
        });
        bestheroeslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLayout(expandLayout);
            }
        });
        sortHeroes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sortBestHeroes(checkedId);
            }
        });


        return view;
    }

    private void expandSortHeroes(LinearLayout linearLayout){
        if (linearLayout.getVisibility()==View.GONE){
            linearLayout.setVisibility(View.VISIBLE);
        } else linearLayout.setVisibility(View.GONE);

    }
    private void closeLayout(LinearLayout linearLayout){
        if (linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
        }

    }
    private void sortBestHeroes(int checkedId){
        switch (checkedId){
            case R.id.byWinratio:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.win_ratio_lbl)+" HEROES");
                sortByWinRatio();
                break;
            case R.id.byKda:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.kda_lbl)+" HEROES");
                sortByKda();
                break;
            case R.id.byCS:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.CS_label)+" HEROES");
                sortByCS();
                break;
            case R.id.byGold:
                bestHeroesBtn.setText("BEST "+getResources().getText(R.string.gold_label)+" HEROES");
                sortByGold();
                break;
        }
    }

    public void sortByWinRatio() {
        mdataSort.sortRoleHeroesByWinRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public void sortByKda() {
        mdataSort.sortRoleHeroesByKdaRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public void sortByCS() {
        mdataSort.sortRoleHeroesByCS(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public void sortByGold() {
        mdataSort.sortRoleHeroesByGold(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public List<SummaryStats> getData(int gamePosition){
        List<SummaryStats> data=new ArrayList<>();
        switch (gamePosition){
            case Constants.Positions.LANE:
                return Database_Helper.getHeroeStats(db, HalcyonUtils.getHeroeStatistics_DBTypeFromPosition(gamePosition),Constants.Game_Types.RANKED);
            break;

        }
        return data;

    }

    public Drawable circleImage (int image){
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), image);
        RoundedBitmapDrawable circularImage = RoundedBitmapDrawableFactory.create(getResources(), bitmapImage);
        circularImage.setCircular(true);
        return circularImage;
    }

    private class bestHeroesViewAdapter extends RecyclerView.Adapter<bestHeroesViewAdapter.MyViewHolder>
    {

        private LayoutInflater inflater;
        List<SummaryStats> data= Collections.emptyList();

        public bestHeroesViewAdapter(Context context, List<SummaryStats> data){
            inflater=LayoutInflater.from(context);
            this.data=data;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view=inflater.inflate(R.layout.bestHero_row,parent,false);
            MyViewHolder holder=new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            SummaryStats current=data.get(position);
            //set view content
            holder.imageView.setImageDrawable(circleImage(HalcyonUtils.getHeroIconFromId(getContext(),current.getHeroId())));
            holder.heroName.setText(HalcyonUtils.getHeroNameFromId(getContext(),current.getHeroId()));
            holder.winProgress.setProgress((int) Math.round(current.getWinRatio()));
            holder.kdaProgress.setProgress((int) Math.round(current.getKda_per_game()));
            holder.csProgress.setProgress((int) Math.round(current.getCs_min_per_game()));
            holder.goldProgress.setProgress((int) Math.round(current.getGold_per_game()));
            holder.winRatioTxt.setText(Float.toString(current.getWinRatio()));
            holder.kdaTxt.setText(Float.toString(current.getKda_per_game()));
            holder.csTxt.setText(Float.toString(current.getCs_min_per_game()));
            holder.goldTxt.setText(Float.toString(current.getGold_per_game()));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        //create subclass ViewHolder
        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView heroName;
            ProgressBar winProgress;
            ProgressBar kdaProgress;
            ProgressBar csProgress;
            ProgressBar goldProgress;
            TextView winRatioTxt;
            TextView kdaTxt;
            TextView csTxt;
            TextView goldTxt;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView=(ImageView)itemView.findViewById(R.id.hero_icon);
                heroName=(TextView) itemView.findViewById(R.id.hero_name);
                winProgress=(ProgressBar) itemView.findViewById(R.id.wrprogress);
                kdaProgress=(ProgressBar) itemView.findViewById(R.id.kdaprogress);
                csProgress=(ProgressBar) itemView.findViewById(R.id.csprogress);
                goldProgress=(ProgressBar) itemView.findViewById(R.id.goldprogress);
                winRatioTxt=(TextView) itemView.findViewById(R.id.winratio_hero);
                kdaTxt=(TextView) itemView.findViewById(R.id.kda_hero);
                csTxt=(TextView) itemView.findViewById(R.id.CS_hero);
                goldTxt=(TextView) itemView.findViewById(R.id.gold_hero);
            }
        }

    }
}
