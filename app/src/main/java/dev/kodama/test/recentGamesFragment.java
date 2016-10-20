package dev.kodama.test;

import android.content.Context;
import android.content.res.Resources;
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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.kodama.test.utils.Constants;


public class recentGamesFragment extends Fragment  {

    //the list containing our heroes list
    private List<Gamestats> mListGames=new ArrayList<>();

    //variables for recyclerview
    private RecyclerView recyclerView;
    private RCViewAdapter adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

    public recentGamesFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.recentgamessummarylayout, container, false);


        //code creates recyclerView

        recyclerView=(RecyclerView)view.findViewById(R.id.games_content);
        mListGames=getData();

        adapter =new RCViewAdapter(getActivity(),mListGames);
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
            current.heroImage= circleImage(Constants.Heroes.images[i]);
            current.heroName=heroes[i];
            data.add(current);
        }

        return data;

    }

    public Drawable circleImage (int image){
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), image);
        RoundedBitmapDrawable circularImage = RoundedBitmapDrawableFactory.create(getResources(), bitmapImage);
        circularImage.setCircular(true);
        return circularImage;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
     }

    private class RCViewAdapter extends RecyclerView.Adapter<RCViewAdapter.MyViewHolder>
             {

        private LayoutInflater inflater;
        List<Gamestats> data= Collections.emptyList();

        public RCViewAdapter(Context context, List<Gamestats> data){
            inflater=LayoutInflater.from(context);
            this.data=data;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view=inflater.inflate(R.layout.game_row,parent,false);
            MyViewHolder holder=new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Gamestats current=data.get(position);
            holder.imageView.setImageDrawable(current.getHeroImage());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        //create subclass ViewHolder
        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView=(ImageView)itemView.findViewById(R.id.hero_game);
            }
        }
    }

}
