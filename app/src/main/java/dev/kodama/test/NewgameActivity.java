package dev.kodama.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by kodama on 4/22/16.
 */
public class NewgameActivity extends AppCompatActivity implements
        NewGame1.CommGameData1, ViewAdapter.CommHeroDataG, NewGame2.CommGameData2{
    ViewPager viewPager;
    RecyclerView recyclerView; //to freeze on click
    private LinearLayout dotIndicator, numberspad;
    private int dotsCnt;
    protected View view;
    private ImageView[] dots;
    private Button nextbtn;
    private Button backbtn;
    boolean win;
    float length;
    int queuetype;
    String hero;
    String position;
    NumberPicker numberPicker;
    TextView minutes;
    TextView seconds;
    TextView totalKills;
    int totalkills=0;
    View newgame2View;

    //declaring viewpager string
    private String newgamepager [] = {"newgame1", "newgame2", "newgame3","newgame4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewgame);

        // my_child_toolbar is defined in the layout file
        Toolbar ngToolbar = (Toolbar) findViewById(R.id.ngtoolbar);
        setSupportActionBar(ngToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        //viewpager setting
        viewPager = (ViewPager) findViewById(R.id.newgamepager);
        viewPager.setAdapter(new CustomAdapterAG(getSupportFragmentManager(),getApplicationContext() ));
        viewPager.setCurrentItem(0);

        //declaring numberspad layout
        numberspad=(LinearLayout)findViewById(R.id.numberspad);

        //setting viewer dots indicador
        dotIndicator=(LinearLayout) findViewById(R.id.PagerCountDots);
        dotsCnt=newgamepager.length;
        dots=new ImageView[dotsCnt];
        for (int i = 0; i < dotsCnt; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.unselecteddot);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(6, 0, 6, 0);
            dotIndicator.addView(dots[i],params);
        }
        dots[0].setImageResource(R.drawable.selecteddot);

        //setting next button listener
        nextbtn=(Button)findViewById(R.id.btn_next);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem()!=dotsCnt-1){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                } else {
                    //code to adding game
                }
            }
        });
        //setting back button listener
        backbtn=(Button)findViewById(R.id.btn_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
            }
        });

    //viewpager listener
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCnt; i++) {
                    dots[i].setImageResource(R.drawable.unselecteddot);
                }

                dots[position].setImageResource(R.drawable.selecteddot);
                if (position==dotsCnt-1){
                    nextbtn.setText(getString(R.string.addgametbtn));
                    numberspad.setVisibility(View.VISIBLE);
                    numberPicker.setVisibility(View.GONE);
                } else {
                    nextbtn.setText(getString(R.string.nextbtn));
                    numberPicker.setVisibility(View.VISIBLE);
                }
                if (position==0){
                    backbtn.setVisibility(View.INVISIBLE);
                } else backbtn.setVisibility(View.VISIBLE);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //setting numberPicker
        numberPicker=(NumberPicker)findViewById(R.id.numberPicker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(59);
        numberPicker.setValue(20);
        numberPicker.setScaleX(1.5f);
        numberPicker.setScaleY(1.5f);
        numberPicker.setWrapSelectorWheel(true);

        //initializing NewGame2 fragment views
        minutes=(TextView) view.findViewById(R.id.minutestxt);
        seconds=(TextView) view.findViewById(R.id.secondstxt);
        totalKills=(TextView)view.findViewById(R.id.totalkills);

        /*
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount=0.3f;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        */

        //getWindow().setLayout((int)(width*.95),(int)(height*.9));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newgame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_save:
                //save game data code
                this.finish();

                //Snackbar.make(findViewById(R.id.main_layout),"GAME SAVED",Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void HeroDataG(int position, String hero, boolean heroclick) {

        recyclerView=(RecyclerView)findViewById(R.id.heroselector);
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
        Log.d("HeroDataG",position+" "+hero);
    }


    @Override
    public void gameDataGwin(boolean win) {
        this.win=win;
    }

    @Override
    public void gameDataGqueuetype(int queuetype) {
        this.queuetype=queuetype;
    }

    @Override
    public void gameDataView(View view) {
        newgame2View=view;
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch (newgame2View.getId()){
                    case (R.id.minutestxt):
                        minutes.setText(newVal);
                        length=newVal;
                        break;
                    case (R.id.secondstxt):
                        seconds.setText(newVal);
                        length+=newVal/60;
                        Log.d("length",Float.toString(length));
                        break;
                    case (R.id.totalkills):
                        totalKills.setText(newVal);
                        totalkills=newVal;
                        Log.d("total kills", Integer.toString(totalkills));
                        break;
                    default:
                        break;
                }
            }
        });

    }


    private class CustomAdapterAG extends FragmentPagerAdapter {

        public CustomAdapterAG(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NewGame1();
                case 1:
                    return new NewGame2();
                case 2:
                    return new NewGame3();
                case 3:
                    return new NewGame4();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return newgamepager.length;
        }
    }


    }
