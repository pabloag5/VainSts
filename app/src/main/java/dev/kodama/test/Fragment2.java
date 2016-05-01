package dev.kodama.test;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment implements Communicator {
    TextView textView;
    String message;

    //variables for recyclerview
    private RecyclerView recyclerView;
    private CustomViewAdapter adapter;
    LinearLayoutManager llmanager = new LinearLayoutManager(getActivity());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment2, container, false);

        //code create recyclerView
        recyclerView=(RecyclerView)view.findViewById(R.id.list_content);

        adapter =new CustomViewAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(llmanager);

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
        Resources res = getResources();
        String[] heroes=res.getStringArray(R.array.Heroes_name);
        for(int i=0;i<heroes.length && i<images.length; i++)
        {
            gamestats current=new gamestats();
            current.heroIcon=images[i];
            current.heroName=heroes[i];
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

    public void showText(String data){
        this.message=data;
        textView.setText(data);
    }

    public void scrollRV(int position) {
        llmanager.scrollToPositionWithOffset(position,20);
    }

    @Override
    public void sendPosition(int position, boolean repeatPosition) {
        scrollRV(position);
    }
}
