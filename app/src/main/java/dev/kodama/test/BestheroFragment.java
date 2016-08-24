package dev.kodama.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class BestheroFragment extends Fragment {
    Button lanebtn;
    Button junglebtn;
    Button roambtn;
    ImageView heroimage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.bestherolayout, container, false);

        //setting graph text
        heroimage=(ImageView) view.findViewById(R.id.heroimage);

        //setting buttons
        lanebtn=(Button) view.findViewById(R.id.lanebtn);
        junglebtn=(Button) view.findViewById(R.id.junglebtn);
        roambtn=(Button) view.findViewById(R.id.roambtn);
        lanebtn.setSelected(true);
        lanebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanebtn.setSelected(true);
                junglebtn.setSelected(false);
                roambtn.setSelected(false);
                besthero(v);
            }
        });
        junglebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanebtn.setSelected(false);
                junglebtn.setSelected(true);
                roambtn.setSelected(false);
                besthero(v);
            }
        });
        roambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanebtn.setSelected(false);
                junglebtn.setSelected(false);
                roambtn.setSelected(true);
                besthero(v);
            }
        });
        return view;


    }


    private void besthero(View btn) {
        //games queries
        switch (btn.getId()){
            case R.id.lanebtn:
                //query recent games
                heroimage.setImageResource(R.drawable.celeste);
                break;
            case R.id.junglebtn:
                //query weekly games
                heroimage.setImageResource(R.drawable.glaive);
                break;
            case R.id.roambtn:
                //query season games
                heroimage.setImageResource(R.drawable.phinn);
                break;
            default:
                break;
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
