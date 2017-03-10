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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    Button laneBtn, jungleBtn, roamBtn;
    LinearLayout bestHeroesBtn, expandLayout;
    RadioGroup sortHeroes;
    RadioButton sortHeroesWR;
    private DataSort mdataSort = new DataSort();
    private List<SummaryStats> mListHeroes=new ArrayList<>();
    private SummaryStats roleStats;
    private RecyclerView recyclerView;
    private bestHeroesViewAdapter adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    DatabaseTransactions dbTrans;
    TextView rolebtntext, roleWR, roleKDA, roleKP, roleKills, roleDeaths, roleAssists, roleCS, roleGold, roleTime;
    ProgressBar roleKillsprogress, roleDeathsprogress, roleAssistsprogress, roleCSprogress, roleGoldprogress, roleTimeprogress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.rolelayout, container, false);

        dbTrans = DatabaseTransactions.getInstance(getContext());
        laneBtn=(Button) view.findViewById(R.id.lanebtn);
        jungleBtn=(Button) view.findViewById(R.id.junglebtn);
        roamBtn=(Button) view.findViewById(R.id.roambtn);
        roleWR=(TextView) view.findViewById(R.id.roleWR);
        roleKDA=(TextView) view.findViewById(R.id.roleKDA);
        roleKP=(TextView) view.findViewById(R.id.roleKP);
        bestHeroesBtn=(LinearLayout) view.findViewById(R.id.bestheroesbtn);
        rolebtntext=(TextView) view.findViewById(R.id.rolebtntext);
        expandLayout=(LinearLayout) view.findViewById(R.id.llExpandDetail);
        sortHeroes=(RadioGroup) view.findViewById(R.id.sortHeroes);
        sortHeroesWR=(RadioButton) view.findViewById(R.id.byWinratio);
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
        sortHeroesWR.setSelected(true);
        sortHeroesWR.setActivated(true);
        laneBtn.setSelected(true);
        roleStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_LANE,Constants.Game_Types.RANKED);
        mListHeroes=getData(Constants.Positions.LANE);
        updateRoleStats();

        /**
         * set recyclerview
         */
        adapter =new bestHeroesViewAdapter(getActivity(),mListHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

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
                updateRoleStats();
                laneBtn.setSelected(true);
                jungleBtn.setSelected(false);
                roamBtn.setSelected(false);
            }
        });
        jungleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.JUNGLE);
                adapter.notifyDataSetChanged();
                roleStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_JUNGLE, Constants.Game_Types.RANKED);
                updateRoleStats();
                laneBtn.setSelected(false);
                jungleBtn.setSelected(true);
                roamBtn.setSelected(false);
            }
        });
        roamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.ROAM);
                adapter.notifyDataSetChanged();
                roleStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_ROAM, Constants.Game_Types.RANKED);
                updateRoleStats();
                laneBtn.setSelected(false);
                jungleBtn.setSelected(false);
                roamBtn.setSelected(true);
            }
        });



        /**
         * set Best heroes button text
         */
        switch (sortHeroes.getCheckedRadioButtonId()){
            case R.id.byWinratio:
                rolebtntext.setText("BEST "+getResources().getText(R.string.win_ratio_lbl)+" HEROES");
                break;
            case R.id.byCS:
                rolebtntext.setText("BEST "+getResources().getText(R.string.CS_label)+" HEROES");
                break;
            case R.id.byKda:
                rolebtntext.setText("BEST "+getResources().getText(R.string.kda_lbl)+" HEROES");
                break;
            case R.id.byGold:
                rolebtntext.setText("BEST "+getResources().getText(R.string.gold_label)+" HEROES");
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
                rolebtntext.setText("BEST "+getResources().getText(R.string.win_ratio_lbl)+" HEROES");
                sortByWinRatio();
                break;
            case R.id.byKda:
                rolebtntext.setText("BEST "+getResources().getText(R.string.kda_lbl)+" HEROES");
                sortByKda();
                break;
            case R.id.byCS:
                rolebtntext.setText("BEST "+getResources().getText(R.string.CS_label)+" HEROES");
                sortByCS();
                break;
            case R.id.byGold:
                rolebtntext.setText("BEST "+getResources().getText(R.string.gold_label)+" HEROES");
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
     * fill role heroes recylerview list
     * @param gamePosition
     * @return
     */
    public List<SummaryStats> getData(int gamePosition){
        ArrayList<SummaryStats> heroeslist = new ArrayList<>();
        heroeslist=dbTrans.getHeroeStats(HalcyonUtils.getHeroeStatistics_DBTypeFromPosition(gamePosition),Constants.Game_Types.RANKED);
        return heroeslist;
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
     * update page objects
     */
    private void updateRoleStats(){
        if (roleStats!=null) {
            roleWR.setText(String.valueOf(roleStats.getWinRatio()));
            roleKDA.setText(String.valueOf(roleStats.getKda_per_game()));
            roleKP.setText(String.valueOf(roleStats.getKill_participation_per_game()));
            roleKills.setText(String.valueOf(roleStats.getKills_per_game()));
            roleKillsprogress.setProgress((int) roleStats.getKills_per_game());
            roleDeaths.setText(String.valueOf(roleStats.getDeaths_per_game()));
            roleDeathsprogress.setProgress(((int) roleStats.getDeaths_per_game()-6)*-1);
            roleAssists.setText(String.valueOf(roleStats.getAssists_per_game()));
            roleAssistsprogress.setProgress((int) roleStats.getAssists_per_game());
            roleCS.setText(String.valueOf(roleStats.getCs_min_per_game()));
            roleCSprogress.setProgress((int) roleStats.getCs_min_per_game());
            roleGold.setText(String.valueOf(roleStats.getGold_per_game()));
            roleGoldprogress.setProgress((int) roleStats.getGold_per_game());
            //roleTime.setText(String.valueOf());
            //roleTimeprogress.setProgress((int));
        }

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
        public bestHeroesViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view=inflater.inflate(R.layout.besthero_row,parent,false);
            bestHeroesViewAdapter.MyViewHolder holder=new bestHeroesViewAdapter.MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(bestHeroesViewAdapter.MyViewHolder holder, int position) {
            if (data!=null) {
                SummaryStats current=data.get(position);
                //set view content
                holder.imageView.setImageDrawable(circleImage(HalcyonUtils.getHeroIconFromId(getContext(),current.getHeroId())));
                holder.heroName.setText(HalcyonUtils.getHeroNameFromId(getContext(),current.getHeroId()));
                holder.winProgress.setProgress((int) Math.round(current.getWinRatio()));
                holder.kdaProgress.setProgress((int) Math.round(current.getKda_per_game()));
                holder.csProgress.setProgress((int) Math.round(current.getCs_min_per_game()));
                holder.goldProgress.setProgress((int) Math.round(current.getGold_per_game()));
                holder.winRatioTxt.setText(String.valueOf(current.getWinRatio()));
                holder.kdaTxt.setText(String.valueOf(current.getKda_per_game()));
                holder.csTxt.setText(String.valueOf(current.getCs_min_per_game()));
                holder.goldTxt.setText(String.valueOf(current.getGold_per_game()));
            }
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
