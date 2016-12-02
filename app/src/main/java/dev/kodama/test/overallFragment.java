package dev.kodama.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.HorizontalBarChart;

/**
 * Created by kodama on 4/20/16.
 */
public class overallFragment extends Fragment  {

    HorizontalBarChart rankchart;
    Button kdabutton;
    Button bestherobutton;
    CommunicatorKdaFragment comm;
    ImageView overallImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overalllayout,container,false);

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

        /*
        comm = (CommunicatorKdaFragment) getActivity();
        kdabutton= (Button) view.findViewById(R.id.kda_button);
        String kdabtn = getString(R.string.kda_lbl)+"  "+getString(R.string.kda_value);
        kdabutton.setText(kdabtn);
        kdabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.fragmentParent(new overallFragment(),0);

                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                trans.replace(R.id.progressroot, new KdaFragment());
                */

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                //trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                /*
                trans.addToBackStack(null);

                trans.commit();

            }
        });
        */
        /*
        bestherobutton= (Button) view.findViewById(R.id.best_hero_button);
        bestherobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.fragmentParent(new overallFragment(),0);
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                trans.replace(R.id.progressroot, new BestheroFragment());

				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });
        */
        int image = R.drawable.catherine;

        overallImage = (ImageView)view.findViewById(R.id.overallimage);
        overallImage.setImageDrawable(circleImage(image));

        return view;
    }

    public Drawable circleImage (int image){
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), image);
        RoundedBitmapDrawable circularImage = RoundedBitmapDrawableFactory.create(getResources(), bitmapImage);
        circularImage.setCircular(true);
        return circularImage;
    }
    public interface CommunicatorKdaFragment {
        public void fragmentParent (Fragment fragment, int secondpage);

    }
}
