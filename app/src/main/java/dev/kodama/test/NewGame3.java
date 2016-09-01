package dev.kodama.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kodama on 8/17/16.
 */
public class NewGame3 extends Fragment {

    //declaring private variables
    TextView kills;
    TextView deaths;
    TextView assists;
    CommGameData3 commGameData3;

    public NewGame3(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newgame3, container, false);

        //setting KDA textviews
        kills=(TextView) view.findViewById(R.id.killstxt);
        deaths=(TextView) view.findViewById(R.id.deathstxt);
        assists=(TextView)view.findViewById(R.id.assiststxt);

        kills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kills.setTextColor(Color.parseColor("#0BACDD"));
                deaths.setTextColor(Color.parseColor("#cccccc"));
                assists.setTextColor(Color.parseColor("#cccccc"));
                commGameData3.gameDataKDA(v);
            }
        });
        deaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kills.setTextColor(Color.parseColor("#cccccc"));
                deaths.setTextColor(Color.parseColor("#0BACDD"));
                assists.setTextColor(Color.parseColor("#cccccc"));
                commGameData3.gameDataKDA(v);
            }
        });
        assists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kills.setTextColor(Color.parseColor("#cccccc"));
                deaths.setTextColor(Color.parseColor("#cccccc"));
                assists.setTextColor(Color.parseColor("#0BACDD"));
                commGameData3.gameDataKDA(v);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        commGameData3=(CommGameData3) context;
    }

    //method call from NewGameActivity to update TextViews text
    public void setValToView(int id, int val){
        switch (id){
            case R.id.killstxt:
                kills.setText(Integer.toString(val));
                break;
            case R.id.deathstxt:
                deaths.setText(Integer.toString(val));
                break;
            case R.id.assiststxt:
                assists.setText(Integer.toString(val));
                break;
            default:
                break;
        }
    }

    //interface to send data to NewGameActivity
    public interface CommGameData3 {
        public void gameDataKDA (View view);
    }
}
