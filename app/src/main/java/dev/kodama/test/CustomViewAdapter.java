package dev.kodama.test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by kodama on 3/20/16.
 */
public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.MyViewHolder>
        implements View.OnClickListener {

    private LayoutInflater inflater;
    List<gamestats> data= Collections.emptyList();
    //private int expandedPosition = -1;
    //private boolean repeatPosition = false;
    private HeroesFragment.CommHeroDetailFragment comm;


    public CustomViewAdapter (Context context, List<gamestats> data, HeroesFragment fragment){
        inflater=LayoutInflater.from(context);
        this.data=data;
        comm = (HeroesFragment.CommHeroDetailFragment) context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.hero_row,parent,false);
        MyViewHolder holder=new MyViewHolder(view);

        holder.itemView.setOnClickListener(CustomViewAdapter.this);
        holder.itemView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        gamestats current=data.get(position);
        holder.name.setText(current.heroName);
        holder.icon.setImageResource(current.heroIcon);
        holder.kdaratio.setText(String.format("KDA ratio: %.1f",current.kdaRatio));
        holder.winratio.setText(String.format("Win ratio: %.1f",current.winRatio));
        /*
        //expand code
        if (position == expandedPosition && repeatPosition==false){
            holder.llExpandedDetail.setVisibility(View.VISIBLE);

        } else {
            holder.llExpandedDetail.setVisibility(View.GONE);
        }
        */

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        MyViewHolder holder = (MyViewHolder) v.getTag();
        comm.HerofragmentParent(holder.getAdapterPosition(), 1, new HeroesFragment(),data);



        /*code to find position and send it to the layoutmanager to scroll and block layout
        MyViewHolder holder = (MyViewHolder) v.getTag();

        //find position
        if (holder.llExpandedDetail.getVisibility()==View.VISIBLE) {
            repeatPosition=true;
        } else repeatPosition=false;
        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev);
        }
        expandedPosition = holder.getAdapterPosition();
        notifyItemChanged(expandedPosition);
        comm.sendPosition(expandedPosition, repeatPosition);
        */
    }

    //create subclass ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView icon;
        TextView kdaratio;
        TextView winratio;

        /*defining layout to expand
        LinearLayout llExpandedDetail;
         */


        public MyViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.Hero_name);
            icon= (ImageView) itemView.findViewById(R.id.Hero_image);
            kdaratio= (TextView) itemView.findViewById(R.id.KDA_Ratio);
            winratio= (TextView) itemView.findViewById(R.id.Win_ratio);

            /*code to expand layout
            llExpandedDetail= (LinearLayout) itemView.findViewById(R.id.llExpandDetail);
             */
        }
    }

}


