package dev.kodama.test;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import dev.kodama.test.db.DatabaseTransactions;
import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.SummaryStats;


public class KdaFragment extends Fragment {
    private Button monthsBtn, seasonBtn, patchBtn;
    private LinearLayout bestkdaHeroesBtn, kdaexpandLayout, kdadetaillayout;
    private RadioGroup kdasortHeroes;
    private RadioButton highkdaHeroes;
    private DataSort mdataSort = new DataSort();
    private List<SummaryStats> mListHeroes=new ArrayList<>();
    private SummaryStats kdaStats;
    private RecyclerView recyclerView;
    private kdaViewAdapter adapter;
    LinearLayoutManager kdalayoutManager = new LinearLayoutManager(getActivity());
    private DatabaseTransactions dbTrans = DatabaseTransactions.getInstance(getContext());
    private TextView kdakills, kdadeaths, kdaassists, kdaoverall, kdasortbtn;
    private ProgressBar kdaoverallgraph, killsbar, deathsbar, assistsbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.kdalayout, container, false);

        monthsBtn=(Button) view.findViewById(R.id.monthsbtn);
        seasonBtn=(Button) view.findViewById(R.id.seasonbtn);
        patchBtn=(Button) view.findViewById(R.id.patchbtn);
        kdaoverall=(TextView) view.findViewById(R.id.kdagraph);
        kdakills=(TextView) view.findViewById(R.id.killskda);
        kdadeaths=(TextView) view.findViewById(R.id.deathskda);
        kdaassists=(TextView) view.findViewById(R.id.assistskda);
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
        highkdaHeroes.setSelected(true);
        monthsBtn.setSelected(true);
        kdaStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_ALL,Constants.Game_Types.RANKED);
        updateKDAStats();

        /**fill kda stats
         * first 3 months stats
         * second season stats
         * third patch stats
         */
        monthsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.LANE);
                adapter.notifyDataSetChanged();
                kdaStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_LANE, Constants.Game_Types.RANKED);
                updateKDAStats();
                monthsBtn.setSelected(true);
                seasonBtn.setSelected(false);
                patchBtn.setSelected(false);
            }
        });
        seasonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.JUNGLE);
                adapter.notifyDataSetChanged();
                kdaStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_JUNGLE, Constants.Game_Types.RANKED);
                updateKDAStats();
                monthsBtn.setSelected(false);
                seasonBtn.setSelected(true);
                patchBtn.setSelected(false);
            }
        });
        patchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListHeroes=getData(Constants.Positions.ROAM);
                adapter.notifyDataSetChanged();
                kdaStats=dbTrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_ROAM, Constants.Game_Types.RANKED);
                updateKDAStats();
                monthsBtn.setSelected(false);
                seasonBtn.setSelected(false);
                patchBtn.setSelected(true);
            }
        });

        /**
         * set recyclerview
         */
        adapter =new kdaViewAdapter(getActivity(),mListHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(kdalayoutManager);

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
            case R.id.byRoleKDA:
                kdasortbtn.setText("ROLE "+getResources().getText(R.string.kda_lbl)+" HEROES");
                sortByRoleKDA();
                break;
        }
    }

    public void sortByHighKDA() {
        mdataSort.sortRoleHeroesByWinRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public void sortByLowKDA() {
        mdataSort.sortRoleHeroesByKdaRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public void sortByRoleKDA() {
        mdataSort.sortRoleHeroesByCS(mListHeroes);
        adapter.notifyDataSetChanged();
    }


}
