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
public class NewGame4 extends Fragment {
    //declaring private variables
    TextView cs;
    TextView gold;
    CommGameData4 commGameData4;
    String temptxt;

    public NewGame4(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newgame4, container, false);

        //setting cs and gold textviews
        cs=(TextView) view.findViewById(R.id.cstxt);
        gold=(TextView) view.findViewById(R.id.goldtxt);


        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cs.setTextColor(Color.parseColor("#0BACDD"));
                gold.setTextColor(Color.parseColor("#cccccc"));
                commGameData4.gameDataCSGold(v, cs.getText().toString());
            }
        });
        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cs.setTextColor(Color.parseColor("#cccccc"));
                gold.setTextColor(Color.parseColor("#0BACDD"));
                commGameData4.gameDataCSGold(v, gold.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        commGameData4=(CommGameData4) context;
    }

    //method to update textview text
    public void setValToView(int id, String val){
        switch (id){
            case R.id.cstxt:
                if (cs.getText().equals("0")){
                    cs.setText(val);
                } else cs.append(val);
                break;
            case R.id.goldtxt:
                if (gold.getText().equals("0")) {
                    gold.setText(val);
                } else gold.append(val);
                break;
            default:
                break;
        }
    }
    //method to erase last character from textview
    public void eraseLastChar (int id){
        switch (id){
            case R.id.cstxt:
                temptxt=cs.getText().toString();
                if (temptxt.length()>0){
                    cs.setText(temptxt.substring(0, temptxt.length() - 1 ));
                }
                break;
            case R.id.goldtxt:
                temptxt=gold.getText().toString();
                if (temptxt.length()>0){
                    gold.setText(temptxt.substring(0, temptxt.length() - 1 ));
                }
                break;
            default:
                break;
        }
    }

    //interface to send data to NewGameActivity
    public interface CommGameData4 {
        public void gameDataCSGold (View view, String currentVal);
    }
}
