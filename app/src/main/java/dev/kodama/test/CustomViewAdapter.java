package dev.kodama.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import dev.kodama.test.utils.Constants;

import static java.security.AccessController.getContext;

/**
 * Created by kodama on 3/20/16.
 */
public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.MyViewHolder>
        implements View.OnClickListener {

    private LayoutInflater inflater;
    List<Integer> data= Collections.emptyList();
    private Context context;
    //private int expandedPosition = -1;
    //private boolean repeatPosition = false;
    //private HeroesFragment.CommHeroDetailFragment comm;


    public CustomViewAdapter (Context context, List<Integer> data){
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.data=data;
        DisplayMetrics dm = new DisplayMetrics();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;
        //comm = (HeroesFragment.CommHeroDetailFragment) context;

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
        int current=data.get(position);
        //holder.name.setText(current.getHeroName());
        holder.icon.setImageResource(current);

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
        Intent intent=new Intent(context,DetailActivity.class);
        intent.putExtra("detail", ((MyViewHolder) v.getTag()).getAdapterPosition());
        context.startActivity(intent);

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

        ImageView icon;

        /*defining layout to expand
        LinearLayout llExpandedDetail;
         */


        public MyViewHolder(View itemView) {
            super(itemView);


            icon= (ImageView) itemView.findViewById(R.id.hero_image);
            icon.setAdjustViewBounds(true);
            /*code to expand layout
            llExpandedDetail= (LinearLayout) itemView.findViewById(R.id.llExpandDetail);
             */
        }
    }

}


