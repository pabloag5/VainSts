package dev.kodama.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kodama on 8/17/16.
 */
public class NewGame2 extends Fragment {

    //declaring private variables
    TextView minutes;
    TextView seconds;
    TextView totalKills;
    float gametime;
    CommGameData2 commGameData2;

    public NewGame2(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.newgame2, container, false);

        //setting gametime textviews
        minutes=(TextView) view.findViewById(R.id.minutestxt);
        seconds=(TextView) view.findViewById(R.id.secondstxt);
        totalKills=(TextView)view.findViewById(R.id.totalkills);

        minutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minutes.setTextColor(Color.parseColor("#0BACDD"));
                seconds.setTextColor(Color.parseColor("#cccccc"));
                totalKills.setTextColor(Color.parseColor("#cccccc"));
                commGameData2.gameDataView(v);
            }
        });
        seconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minutes.setTextColor(Color.parseColor("#cccccc"));
                seconds.setTextColor(Color.parseColor("#0BACDD"));
                totalKills.setTextColor(Color.parseColor("#cccccc"));
                commGameData2.gameDataView(v);
            }
        });
        totalKills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minutes.setTextColor(Color.parseColor("#cccccc"));
                seconds.setTextColor(Color.parseColor("#cccccc"));
                totalKills.setTextColor(Color.parseColor("#0BACDD"));
                commGameData2.gameDataView(v);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        commGameData2=(CommGameData2) context;
    }

    public interface CommGameData2 {
        public void gameDataView (View view);
    }
}
