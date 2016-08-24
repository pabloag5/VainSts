package dev.kodama.test;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class KdaFragment extends Fragment {
    Button btn1kda;
    Button btn2kda;
    Button btn3kda;
    TextView kdachart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.kdalayout, container, false);

        //setting graph text
        kdachart=(TextView) view.findViewById(R.id.kdagraph);
        kdachart.setSingleLine(false);
        kdachart.setText(getString(R.string.kda_value)+"\n"+getString(R.string.kda_lbl));

        //setting buttons
        btn1kda=(Button) view.findViewById(R.id.btn1kda);
        btn2kda=(Button) view.findViewById(R.id.btn2kda);
        btn3kda=(Button) view.findViewById(R.id.btn3kda);
        btn1kda.setSelected(true);
        btn1kda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1kda.setSelected(true);
                btn2kda.setSelected(false);
                btn3kda.setSelected(false);
                kdabtnqueries(v);
            }
        });
        btn2kda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1kda.setSelected(false);
                btn2kda.setSelected(true);
                btn3kda.setSelected(false);
                kdabtnqueries(v);
            }
        });
        btn3kda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1kda.setSelected(false);
                btn2kda.setSelected(false);
                btn3kda.setSelected(true);
                kdabtnqueries(v);
            }
        });
        return view;


    }

    private void kdabtnqueries(View btn) {
        //games queries
        switch (btn.getId()){
            case R.id.btn1kda:
                //query recent games
                break;
            case R.id.btn2kda:
                //query weekly games
                break;
            case R.id.btn3kda:
                //query season games
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
