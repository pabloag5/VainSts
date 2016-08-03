package dev.kodama.test;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HeroesFragment extends Fragment implements SortInterface {
    TextView textView;

    //the list containing our heroes list
    private List<gamestats> mListHeroes=new ArrayList<>();

    //the sorter that sorts our heroes based on sort button
    private dataSort mdataSort = new dataSort();

    //variables for recyclerview
    private RecyclerView recyclerView;
    private CustomViewAdapter adapter;
    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
    /*defining custom linearlayout to expand selection
    CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity());
    */

    public HeroesFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.heroessummarylayout, container, false);

        //code creates recyclerView
        recyclerView=(RecyclerView)view.findViewById(R.id.list_content);
        mListHeroes=getData();

        adapter =new CustomViewAdapter(getActivity(),mListHeroes, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //code to use saved data in savedInstanceState
        /*if (savedInstanceState==null) {

        } else {
            message=savedInstanceState.getString("current_message");
            TextView text = (TextView) view.findViewById(R.id.TxtVw_fragment2);
            text.setText(message);
        }*/
        //end of code to use saved data


        return view;


    }

    public List<gamestats> getData(){
        List<gamestats> data=new ArrayList<>();
        int[] images = {R.drawable.adagio, R.drawable.ardan, R.drawable.blackfeather, R.drawable.catherine,
                R.drawable.celeste, R.drawable.fortress, R.drawable.glaive, R.drawable.joule,
                R.drawable.kestrel, R.drawable.koshka, R.drawable.krul, R.drawable.petal, R.drawable.phinn,
                R.drawable.reim, R.drawable.ringo, R.drawable.rona, R.drawable.saw, R.drawable.skaarf,
                R.drawable.skye, R.drawable.taka, R.drawable.vox};
        double [] kdaRatio = new double[images.length];
        double [] winRatio=new  double[images.length];
        for (int i=0;i<images.length; i++)
        {
            kdaRatio[i]=new Random().nextDouble()*18;
            winRatio[i]=new Random().nextDouble()*100;
        }
        Resources res = getResources();
        String[] heroes=res.getStringArray(R.array.Heroes_name);
        for(int i=0;i<heroes.length && i<images.length; i++)
        {
            gamestats current=new gamestats();
            current.heroIcon=images[i];
            current.heroName=heroes[i];
            current.kdaRatio=kdaRatio[i];
            current.winRatio=winRatio[i];
            data.add(current);
        }

        return data;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView= (TextView) getActivity().findViewById(R.id.TxtVw_fragment2);
//        listView=(ListView) getActivity().findViewById(R.id.listViewHero);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
     //   outState.putString("current_message", message);
    }

    public void scrollRV(int position, boolean repeatPosition) {

        /*code for modifying layout: expanding item when pressed
        if (repeatPosition==false){
            layoutManager.scrollToPositionWithOffset(position,20);
            layoutManager.setScrollEnabled(false);
        } else layoutManager.setScrollEnabled(true);
*/
    }

    @Override
    public void onSortByHeroName() {
        mdataSort.sortHeroesByName(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSortByKdaRatio() {
        mdataSort.sortHeroesByKdaRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSortByWinRatio() {
        mdataSort.sortHeroesByWinRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }
/*
    @Override
    public void sendPosition(int position, boolean repeatPosition) {
        scrollRV(position, repeatPosition);
    }
    */

    public interface CommHeroDetailFragment {
        public void HerofragmentParent (int position, int secondpage, Fragment fragment);

    }
}
