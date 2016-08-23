package dev.kodama.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import dev.kodama.test.utils.Constants;


/**
 * Created by kodama on 3/20/16.
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder>
        implements View.OnClickListener {

    private LayoutInflater inflater;
    List<Gamestats> data= Collections.emptyList();
    //private int expandedPosition = -1;
    //private boolean repeatPosition = false;

    CommHeroDataG comm;



    public ViewAdapter(Context context, List<Gamestats> data){
        inflater=LayoutInflater.from(context);
        this.data=data;

        //declare communicator interface
        comm = (CommHeroDataG) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.heroselector,parent,false);
        MyViewHolder holder=new MyViewHolder(view);

        holder.itemView.setOnClickListener(ViewAdapter.this);
        holder.itemView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Gamestats current=data.get(position);
        holder.linearLayout.setBackgroundResource(current.getHeroIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        MyViewHolder holder = (MyViewHolder) v.getTag();
        if (holder.radioGroup.getVisibility()==View.GONE) {
            holder.radioGroup.setVisibility(View.VISIBLE);
            comm.HeroDataG(0,null,true);
            switch (holder.radioGroup.getCheckedRadioButtonId()){
                case (R.id.laneplayed):
                    comm.HeroDataG(Constants.Positions.LANE,data.get(holder.getAdapterPosition()).getHeroName(),true);
                    break;
                case (R.id.jungleplayed):
                    comm.HeroDataG(Constants.Positions.JUNGLE,data.get(holder.getAdapterPosition()).getHeroName(),true);
                    break;
                case (R.id.roamplayed):
                    comm.HeroDataG(Constants.Positions.LANE,data.get(holder.getAdapterPosition()).getHeroName(),true);
                    break;
                default:
                    break;
            }


        }else {
            holder.radioGroup.setVisibility(View.GONE);
            comm.HeroDataG(0,null,false);
        }

        //comm.HerofragmentParent(holder.getAdapterPosition(), 1, new HeroesFragment(),data);
    }


    //create subclass ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        RadioGroup radioGroup;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.heroImageLayout);
            radioGroup=(RadioGroup)itemView.findViewById(R.id.positionplayed);
        }
    }
    public static interface CommHeroDataG {
        public void HeroDataG(int position,String hero, boolean heroclick);
    }
}


