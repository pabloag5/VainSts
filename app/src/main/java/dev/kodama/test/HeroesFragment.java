package dev.kodama.test;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.kodama.test.utils.Constants;


public class HeroesFragment extends Fragment  {
    //TextView textView;

    //the list containing our heroes list
    private List<Gamestats> mListHeroes=new ArrayList<>();

    //variables for recyclerview
    private RecyclerView recyclerView;
    private CustomViewAdapter adapter;
    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

    public HeroesFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.heroessummarylayout, container, false);


//get display width & height
        DisplayMetrics dm = new DisplayMetrics();

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;

        //code creates recyclerView
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView=(RecyclerView)view.findViewById(R.id.list_content);
        mListHeroes=getData();

        adapter =new CustomViewAdapter(getActivity(),mListHeroes, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;


    }

    public List<Gamestats> getData(){
        List<Gamestats> data=new ArrayList<>();

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

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

}
