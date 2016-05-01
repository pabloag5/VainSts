package dev.kodama.test;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
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
public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.MyViewHolder> implements View.OnClickListener {

    private LayoutInflater inflater;
    List<gamestats> data= Collections.emptyList();
    private int expandedPosition = -1;
    private boolean repeatPosition = false;
    private Context context;
    Communicator comm;


    public CustomViewAdapter (Context context, List<gamestats> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.context=context;

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

        //expand code
        if (position == expandedPosition && repeatPosition==false){
            holder.llExpandedDetail.setVisibility(View.VISIBLE);

        } else {
            holder.llExpandedDetail.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
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
    }

    //create subclass ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView icon;
        LinearLayout llExpandedDetail;


        public MyViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.Hero_name);
            icon= (ImageView) itemView.findViewById(R.id.Hero_image);
            llExpandedDetail= (LinearLayout) itemView.findViewById(R.id.llExpandDetail);
        }
    }
}
