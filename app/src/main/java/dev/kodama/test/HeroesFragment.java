package dev.kodama.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import dev.kodama.test.utils.Constants;


public class HeroesFragment extends Fragment  {

    //the list containing our heroes list
    private List<Integer> mListHeroes=new ArrayList<>();

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


        //code creates recyclerView
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView=(RecyclerView)view.findViewById(R.id.list_content);
        mListHeroes=getData();

        adapter =new CustomViewAdapter(getActivity(),mListHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;


    }

    public List<Integer> getData(){
        List<Integer> data=new ArrayList<>();

        for(int i = 0; i<Constants.Heroes.images.length; i++)
        {
            data.add(Constants.Heroes.images[i]);
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


}
