package dev.kodama.test;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class HeroDetailFragment extends Fragment {
    Button lanebtn;
    Button junglebtn;
    Button roambtn;
    ImageView heroimage;
    HeroesFragment fragment;
    final static String ARG_POSITION = "position";
    int currentposition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.herodetaillayout, container, false);

        if (savedInstanceState != null) {
            currentposition = savedInstanceState.getInt(ARG_POSITION);
        }


        //setting graph text
        heroimage=(ImageView) view.findViewById(R.id.herodetailimage);

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

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            // Set hero based on argument passed in
            HeroDetail(args.getInt(ARG_POSITION));
        } else if (currentposition != -1) {
            // Set hero based on saved instance state defined during onCreateView
            HeroDetail(currentposition);
        }
    }

    private void besthero(View btn) {
        //games queries
        switch (btn.getId()){
            case R.id.lanebtn:
                //query lane games
                break;
            case R.id.junglebtn:
                //query jungle games
                break;
            case R.id.roambtn:
                //query roam games
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
        // Save the current hero selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, currentposition);
    }


    public void HeroDetail (int position) {
        //query code selected hero
        String[] heroes=getResources().getStringArray(R.array.Heroes_name);
        Snackbar.make(getActivity().findViewById(R.id.main_layout),heroes[position],Snackbar.LENGTH_SHORT).show();

    }
}
