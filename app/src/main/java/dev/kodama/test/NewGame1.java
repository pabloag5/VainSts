package dev.kodama.test;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import dev.kodama.test.utils.Constants;

/**
 * Created by kodama on 8/17/16.
 */
public class NewGame1 extends Fragment {

    //the list containing our heroes list
    private List<Gamestats> mListHeroes=new ArrayList<>();

    //declaring private variables
    private RecyclerView recyclerView;
    private ViewAdapter adapter;
    private RadioButton queueR;
    private RadioButton queueC;
    private RadioButton resultwin;
    private RadioButton resultloss;
    CustomLinearLayoutManager layoutManager= (CustomLinearLayoutManager) new CustomLinearLayoutManager(getActivity(),CustomLinearLayoutManager.HORIZONTAL,false);

    CommGameData1 commGameDataG;

    boolean win;
    int queuetype;

    public NewGame1(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.newgame1, container, false);

        //setting recyclerView
        recyclerView=(RecyclerView)view.findViewById(R.id.heroselector);
        mListHeroes=getHero();
        adapter =new ViewAdapter(getActivity(),mListHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //declaring and setting type queue radiobuttons
        queueR=(RadioButton) view.findViewById(R.id.rankedqueue);
        queueC=(RadioButton) view.findViewById(R.id.casualqueue);
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

        //declaring and setting game result radiobuttons
        resultwin=(RadioButton) view.findViewById(R.id.win_result);
        resultloss=(RadioButton) view.findViewById(R.id.loss_result);
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
        commGameDataG=(CommGameData1) context;
    }


    public interface CommGameData1 {
        public void gameDataGwin(boolean win);
        public void gameDataGqueuetype(int queuetype);

    }
}
