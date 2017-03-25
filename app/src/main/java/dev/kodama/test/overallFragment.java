package dev.kodama.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import dev.kodama.test.db.DatabaseTransactions;
import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.SummaryStats;

/**
 * Created by kodama on 4/20/16.
 */
public class overallFragment extends Fragment  {

    ImageView overallImage;
    TextView totalgames, wingames, lossgames, kdaoverall, winrateoverall;
    SummaryStats summaryStats;
    private DatabaseTransactions dbtrans;
    LinearLayout kdadetail, winratedetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overalllayout,container,false);


        dbtrans = DatabaseTransactions.getInstance(this.getContext());

        overallImage = (ImageView)view.findViewById(R.id.overallimage);
        totalgames=(TextView) view.findViewById(R.id.games_total);
        wingames=(TextView) view.findViewById(R.id.wins_total);
        lossgames=(TextView) view.findViewById(R.id.losses_total);
        kdaoverall=(TextView) view.findViewById(R.id.kda_overall);
        winrateoverall=(TextView) view.findViewById(R.id.winrate_overall);
        kdadetail=(LinearLayout) view.findViewById(R.id.kdaDetail);
        winratedetail=(LinearLayout) view.findViewById(R.id.winratioDetail);


        /**
         * get overall stats
         */
        summaryStats=dbtrans.getTotalStats(Constants.Statistics_DB.Totals.TOTAL_ALL,Constants.Game_Types.RANKED);

        int image = R.drawable.catherine;
        overallImage.setImageDrawable(circleImage(image));

        /**
         * fill overall values
         */
        if (summaryStats!=null){

            totalgames.setText(String.valueOf(summaryStats.getTotal_games()));
            wingames.setText(String.valueOf(summaryStats.getWins()));
            lossgames.setText(String.valueOf(summaryStats.getLosses()));
            kdaoverall.setText(String.format("%.1f",summaryStats.getKda_per_game()));
            winrateoverall.setText(String.valueOf(Math.round(summaryStats.getWinRatio()*100))+"%");

        }

        kdadetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),DetailActivity.class);
                intent.putExtra("detail",Constants.Detail_Types.KDA_DETAIL);
                startActivity(intent);
            }
        });
        winratedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),DetailActivity.class);
                intent.putExtra("detail",Constants.Detail_Types.WINRATIO_DETAIL);
                startActivity(intent);
            }
        });


        return view;
    }

    public Drawable circleImage (int image){
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), image);
        RoundedBitmapDrawable circularImage = RoundedBitmapDrawableFactory.create(getResources(), bitmapImage);
        circularImage.setCircular(true);
        return circularImage;
    }

}
