package dev.kodama.test;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
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
    CustomLinearLayoutManager layoutManager= (CustomLinearLayoutManager) new CustomLinearLayoutManager(getActivity(),CustomLinearLayoutManager.HORIZONTAL,false);

    CommGameDataG commGameDataG;
    boolean win;
    String queuetype;
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




        final NumberPicker minutePicker=(NumberPicker)view.findViewById(R.id.minutePicker);
        minutePicker.setValue(0);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(45);

        final NumberPicker secondPicker=(NumberPicker)view.findViewById(R.id.secondPicker);
        secondPicker.setValue(0);
        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);

        final RadioGroup queue=(RadioGroup) view.findViewById(R.id.queue);
        final RadioGroup rgresult=(RadioGroup) view.findViewById(R.id.rgresult);
        if (queue.getCheckedRadioButtonId()==R.id.casualqueue) {
            queuetype=Constants.Game_Types.CASUAL;
        } else queuetype=Constants.Game_Types.RANKED;
        if (rgresult.getCheckedRadioButtonId()==R.id.win_result) {
            win=true;
        } else win=false;
        length=minutePicker.getValue()+secondPicker.getValue()/60;

        if (hero!=null&&position!=null&&minutePicker.getValue()!=0&&secondPicker.getValue()!=0) {
            commGameDataG.GameDataG(win,length,queuetype,hero,position);
        }

        return view;

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

    public void HeroDataGame(int position, String hero, boolean heroclick) {
        if (heroclick) {
            recyclerView.setLayoutFrozen(true);
            //layoutManager.setScrollEnabled(false);
            switch (position){
                case 0:
                    this.position=null;
                    break;
                case 1:
                    this.position="Lane";
                    break;
                case 2:
                    this.position="Jungle";
                    break;
                case 3:
                    this.position="Roam";
                    break;
                default:
                    break;
            }
            this.hero=hero;

        }else recyclerView.setLayoutFrozen(false);//layoutManager.setScrollEnabled(true);
    }
    public interface CommGameDataG {
        public void GameDataG(boolean win, float length, String queuetype, String hero, String position);
    }

}
