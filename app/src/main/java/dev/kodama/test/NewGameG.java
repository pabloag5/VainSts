package dev.kodama.test;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import dev.kodama.test.utils.Constants;

/**
 * Created by kodama on 8/17/16.
 */
public class NewGameG extends Fragment {

    //the list containing our heroes list
    private List<Gamestats> mListHeroes=new ArrayList<>();
    private RecyclerView recyclerView;
    private ViewAdapter adapter;
    private RadioButton queueR;
    private RadioButton queueC;
    private RadioButton resultwin;
    private RadioButton resultloss;
    private NumberPicker minutePicker;
    private NumberPicker secondPicker;
    private View view;
    CustomLinearLayoutManager layoutManager= (CustomLinearLayoutManager) new CustomLinearLayoutManager(getActivity(),CustomLinearLayoutManager.HORIZONTAL,false);

    CommGameDataG commGameDataG;

    boolean win;
    int queuetype;
    String hero;
    String position;
    float length;

    public NewGameG(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.newgamegeneral, container, false);

        //code creates recyclerView
        recyclerView=(RecyclerView)view.findViewById(R.id.heroselector);
        mListHeroes=getHero();

        adapter =new ViewAdapter(getActivity(),mListHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        //set time pickers
        minutePicker=(NumberPicker)view.findViewById(R.id.minutePicker);
        minutePicker.setValue(0);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(45);
        minutePicker.setDividerPadding(5);
        view=(View) minutePicker.findViewById(minutePicker.getNextFocusDownId());

        if(view instanceof EditText) {
            ((EditText) view).setTextSize(25);
            ((EditText) view).setTextColor(Color.parseColor("#cccccc"));
        }

        secondPicker=(NumberPicker)view.findViewById(R.id.secondPicker);
        secondPicker.setValue(0);
        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);

        queueR=(RadioButton) view.findViewById(R.id.rankedqueue);
        queueC=(RadioButton) view.findViewById(R.id.casualqueue);
        resultwin=(RadioButton) view.findViewById(R.id.win_result);
        resultloss=(RadioButton) view.findViewById(R.id.loss_result);
        queueR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioClickListener(v);
            }
        });
        queueC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioClickListener(v);
            }
        });
        resultwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioClickListener(v);
            }
        });
        resultloss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioClickListener(v);
            }
        });

        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                length=newVal;
            }
        });
        secondPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                length=+newVal/60;
            }
        });

        return view;

    }

    private void radioClickListener(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rankedqueue:
                if (checked)
                    this.queuetype=Constants.Game_Types.RANKED;
                    commGameDataG.gameDataGqueuetype(queuetype);
                    break;
            case R.id.casualqueue:
                if (checked)
                    this.queuetype=Constants.Game_Types.CASUAL;
                    commGameDataG.gameDataGqueuetype(queuetype);
                    break;
            case R.id.win_result:
                if (checked)
                    this.win=true;
                    commGameDataG.gameDataGwin(win);
                break;
            case R.id.loss_result:
                if (checked)
                    this.win=false;
                    commGameDataG.gameDataGwin(win);
                break;
        }
        //Log.d("queue",queuetype+" "+Boolean.toString(win));

    }

    public List<Gamestats> getHero(){
        List<Gamestats> data=new ArrayList<>();
        Resources res = getResources();
        String[] heroes=res.getStringArray(R.array.Heroes_name);
        for(int i=0;i<heroes.length && i<Constants.Heroes.images.length; i++)
        {
            Gamestats current=new Gamestats();
            current.heroIcon= Constants.Heroes.images[i];
            current.heroName=heroes[i];
            data.add(current);
        }

        return data;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        commGameDataG=(CommGameDataG) context;
    }


    public interface CommGameDataG {
        public void gameDataG(boolean win, float length, int queuetype);
        public void gameDataGwin(boolean win);
        public void gameDataGqueuetype(int queuetype);
        public void gameDataGlength(float length);
    }
}
