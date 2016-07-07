package dev.kodama.test;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * Created by kodama on 4/20/16.
 */
public class ProgressFragment extends Fragment {

    HorizontalBarChart rankchart;
    Button kdabutton;
    Button bestherobutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summarylayout,container,false);

        /*
        //code using chart lib
        rankchart = (HorizontalBarChart) view.findViewById(R.id.rankchart);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(R.color.colorAccent);
        colors.add(R.color.colorBackground);
        ArrayList<BarEntry> barValue = new ArrayList<>();
        barValue.add(new BarEntry(15f,0));
        barValue.add(new BarEntry(8f,0));
        BarDataSet barDataSet = new BarDataSet(barValue,"entryValue");

        ArrayList<String> lbl = new ArrayList<>();
        lbl.add("top");
        lbl.add("real");

        BarData data = new BarData(lbl,barDataSet);

        rankchart.setData(data);
        rankchart.setMaxVisibleValueCount(15);


        rankchart.setScaleEnabled(false);
        rankchart.setDragEnabled(false);
        rankchart.setTouchEnabled(false);
        rankchart.setPinchZoom(false);
        rankchart.setDoubleTapToZoomEnabled(false);
        rankchart.setHighlightPerTapEnabled(false);
        rankchart.setDragDecelerationEnabled(false);
        rankchart.setDrawGridBackground(false);
        Legend lg = rankchart.getLegend();
        lg.setEnabled(false);
        rankchart.invalidate();
        */





/*
        rankchart.setDrawValueAboveBar(true);

        rankchart.setDescription("");
        rankchart.setX(5);
        rankchart.setY(15);

        XAxis xl = rankchart.getXAxis();
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);
        xl.setAxisLineColor(1);
        xl.setAxisMaxValue(15f);

        YAxis yl = rankchart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);
        yl.setAxisMinValue(0f);
        yl.setAxisMaxValue(15f);
        yl.setAxisLineColor(1);

                // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = rankchart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinValue(0f);
        yr.setAxisLineColor(1);
        yr.setAxisMaxValue(15f);
        // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        //setData(1, 15);*/

        kdabutton= (Button) view.findViewById(R.id.kda_button);
        kdabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                trans.replace(R.id.progressroot, new kdaFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });
        /*
        bestherobutton= (Button) view.findViewById(R.id.best_hero_button);
        bestherobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                trans.replace(R.id.progressroot, new bestheroFragment());

				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });
*/
        return view;
    }







}
