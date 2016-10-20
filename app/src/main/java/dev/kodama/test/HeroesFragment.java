package dev.kodama.test;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.kodama.test.utils.Constants;


public class HeroesFragment extends Fragment  {
    //TextView textView;

    //the list containing our heroes list
    private List<Gamestats> mListHeroes=new ArrayList<>();

    //the sorter that sorts our heroes based on sort button
    //private DataSort mdataSort = new DataSort();

    //sort buttons
    //Button byName;
    //Button byKdaRatio;
    //Button byWinRatio;

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

        //byName=(Button) view.findViewById(R.id.bynamebtn);
        //byKdaRatio=(Button)view.findViewById(R.id.bykdaratiobtn);
        //byWinRatio=(Button)view.findViewById(R.id.bywinratiobtn);
        /*
        byName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byName.setSelected(true);
                byKdaRatio.setSelected(false);
                byWinRatio.setSelected(false);
                Sorthero(v);
            }
        });
        byKdaRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byName.setSelected(false);
                byKdaRatio.setSelected(true);
                byWinRatio.setSelected(false);
                Sorthero(v);
            }
        });
        byWinRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byName.setSelected(false);
                byKdaRatio.setSelected(false);
                byWinRatio.setSelected(true);
                Sorthero(v);
            }
        });
        */


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

    /*
    private void Sorthero(View btn) {
        //games queries
        switch (btn.getId()){
            case R.id.bynamebtn:
                SortByHeroName();
                break;
            case R.id.bykdaratiobtn:
                SortByKdaRatio();
                break;
            case R.id.bywinratiobtn:
                SortByWinRatio();
                break;
            default:
                break;
        }
    }
    */

    public List<Gamestats> getData(){
        List<Gamestats> data=new ArrayList<>();
        /*
        float [] kdaRatio = new float[images.length];
        float [] winRatio=new  float[images.length];
        for (int i=0;i<images.length; i++)
        {
            kdaRatio[i]=new Random().nextFloat()*18;
            winRatio[i]=new Random().nextFloat()*100;
        }
        */
        Resources res = getResources();
        String[] heroes=res.getStringArray(R.array.Heroes_name);
        for(int i = 0; i<heroes.length && i< Constants.Heroes.images.length; i++)
        {
            Gamestats current=new Gamestats();
            current.heroIcon= Constants.Heroes.images[i];
            current.heroName=heroes[i];
            data.add(current);
        }

        return data;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        listView=(ListView) getActivity().findViewById(R.id.listViewHero);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
     //   outState.putString("current_message", message);
    }

    /*
    public void scrollRV(int position, boolean repeatPosition) {

        //code for modifying layout: expanding item when pressed
        if (repeatPosition==false){
            layoutManager.scrollToPositionWithOffset(position,20);
            layoutManager.setScrollEnabled(false);
        } else layoutManager.setScrollEnabled(true);

    }
    */
    /*
    public void SortByHeroName() {
        mdataSort.sortHeroesByName(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public void SortByKdaRatio() {
        mdataSort.sortHeroesByKdaRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }

    public void SortByWinRatio() {
        mdataSort.sortHeroesByWinRatio(mListHeroes);
        adapter.notifyDataSetChanged();
    }
    */
/*
    @Override
    public void sendPosition(int position, boolean repeatPosition) {
        scrollRV(position, repeatPosition);
    }
    */

    public interface CommHeroDetailFragment {
        public void HerofragmentParent (int position, int secondpage, Fragment fragment, List<Gamestats> data);

    }
}
