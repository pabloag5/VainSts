package dev.kodama.test;

import android.content.Context;
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
import dev.kodama.test.db.DatabaseTransactions;
import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.HalcyonUtils;
import dev.kodama.test.utils.SummaryStats;

/**
 * Created by kodama on 4/20/16.
 */
public class RoleFragment extends Fragment {

    Button bestHeroesBtn, laneBtn, jungleBtn, roamBtn;
    LinearLayout expandLayout;
    RadioGroup sortHeroes;
    private DataSort mdataSort = new DataSort();
    private List<SummaryStats> mListHeroes=new ArrayList<>();
    private SummaryStats roleStats;
    private RecyclerView recyclerView;
    private bestHeroesViewAdapter adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    DatabaseTransactions dbTrans = DatabaseTransactions.getInstance(getContext());
    TextView roleWR, roleKDA, roleKP, roleKills, roleDeaths, roleAssists, roleCS, roleGold, roleTime;
    ProgressBar roleKillsprogress, roleDeathsprogress, roleAssistsprogress, roleCSprogress, roleGoldprogress, roleTimeprogress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.rolelayout, container, false);

        laneBtn=(Button) view.findViewById(R.id.lanebtn);
        jungleBtn=(Button) view.findViewById(R.id.junglebtn);
        roamBtn=(Button) view.findViewById(R.id.roambtn);
        roleWR=(TextView) view.findViewById(R.id.roleWR);
        roleKDA=(TextView) view.findViewById(R.id.roleKDA);
        roleKP=(TextView) view.findViewById(R.id.roleKP);
        bestHeroesBtn=(Button) view.findViewById(R.id.bestheroesbtn);
        expandLayout=(LinearLayout) view.findViewById(R.id.llExpandDetail);
        sortHeroes=(RadioGroup) view.findViewById(R.id.sortHeroes);
        recyclerView=(RecyclerView)view.findViewById(R.id.bestheroeslist);
        roleKills=(TextView) view.findViewById(R.id.kills_role);
        roleDeaths=(TextView) view.findViewById(R.id.deaths_role);
        roleAssists=(TextView) view.findViewById(R.id.assists_role);
        roleCS=(TextView) view.findViewById(R.id.cs_role);
        roleGold=(TextView) view.findViewById(R.id.gold_role);
        roleTime=(TextView) view.findViewById(R.id.time_role);
        roleKillsprogress=(ProgressBar) view.findViewById(R.id.rolekillsprogress);
        roleDeathsprogress=(ProgressBar) view.findViewById(R.id.roledeathsprogress);
        roleAssistsprogress=(ProgressBar) view.findViewById(R.id.roleassistsprogress);
        roleCSprogress=(ProgressBar) view.findViewById(R.id.rolecsprogress);
        roleGoldprogress=(ProgressBar) view.findViewById(R.id.rolegoldprogress);
        roleTimeprogress=(ProgressBar) view.findViewById(R.id.roletimeprogress);
        /**
         * initiate page view
         */
        laneBtn.setSelected(true);
        roleStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_LANE,Constants.Game_Types.RANKED);
        /**fill role stats
         * first lane stats
         * second jungle stats
         * third roam stats
         */
        laneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.LANE);
                adapter.notifyDataSetChanged();
                roleStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_LANE, Constants.Game_Types.RANKED);
            }
        });
        jungleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.JUNGLE);
                adapter.notifyDataSetChanged();
                roleStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_JUNGLE, Constants.Game_Types.RANKED);
            }
        });
        roamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.ROAM);
                adapter.notifyDataSetChanged();
                roleStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_ROAM, Constants.Game_Types.RANKED);
            }
        });
        roleWR.setText(Float.toString(roleStats.getWinRatio()));
        roleKDA.setText(Float.toString(roleStats.getKda_per_game()));
        roleKP.setText(Float.toString(roleStats.getKill_participation_per_game()));
        roleKills.setText(Float.toString(roleStats.getKills_per_game()));
        roleKillsprogress.setProgress((int) roleStats.getKills_per_game());
        roleDeaths.setText(Float.toString(roleStats.getDeaths_per_game()));
        roleDeathsprogress.setProgress((int) roleStats.getDeaths_per_game());
        roleAssists.setText(Float.toString(roleStats.getAssists_per_game()));
        roleAssistsprogress.setProgress((int) roleStats.getAssists_per_game());
        roleCS.setText(Float.toString(roleStats.getCs_min_per_game()));
        roleCSprogress.setProgress((int) roleStats.getCs_min_per_game());
        roleGold.setText(Float.toString(roleStats.getGold_per_game()));
        roleGoldprogress.setProgress((int) roleStats.getGold_per_game());
        //roleTime.setText(Float.toString());
        //roleTimeprogress.setProgress((int));

        /**
         * set recyclerview
         */
        adapter =new bestHeroesViewAdapter(getActivity(),mListHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        /**
         * set Best heroes button text
         */
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
        /**
         * set expand layout for sorting list option
         */
        bestHeroesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandSortHeroes(expandLayout);
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLayout(expandLayout);
            }
        });
        /**
         * sort recyclerview
         */
        sortHeroes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sortBestHeroes(checkedId);
            }
        });


        return view;
    }

    /**
     * expand sort options layout
     * @param linearLayout
     */
    private void expandSortHeroes(LinearLayout linearLayout){
        if (linearLayout.getVisibility()==View.GONE){
            linearLayout.setVisibility(View.VISIBLE);
        } else linearLayout.setVisibility(View.GONE);

    }

    /**
     * hide sort options layout
     * @param linearLayout
     */
    private void closeLayout(LinearLayout linearLayout){
        if (linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
        }

    }

    /**
     * sort heroes recylerview
     * @param checkedId
     */
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

    /**
     * fill role heroes recylerview
     * @param gamePosition
     * @return
     */
    public List<SummaryStats> getData(int gamePosition){

        return dbTrans.getHeroeStats(HalcyonUtils.getHeroeStatistics_DBTypeFromPosition(gamePosition),Constants.Game_Types.RANKED);

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

    /**
     * recyclerview adapter
     */
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