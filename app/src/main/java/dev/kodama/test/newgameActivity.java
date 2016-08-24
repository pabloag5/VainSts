package dev.kodama.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;


/**
 * Created by kodama on 4/22/16.
 */
public class NewgameActivity extends AppCompatActivity implements NewGameG.CommGameDataG, ViewAdapter.CommHeroDataG{
    ViewPager viewPager;
    RecyclerView recyclerView;
    private LinearLayout dotIndicator;
    private LinearLayout btnLayout;
    private int dotsCnt;
    protected View view;
    private ImageView[] dots;
    private Button nextbtn;
    boolean win;
    float length;
    String queuetype;
    String hero;
    String position;

    private String newgamepager [] = {"generalInfo", "detailInfo"};
    private int[] dotsIcon ={R.drawable.unselecteddot,R.drawable.selecteddot};



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

        dotIndicator=(LinearLayout) findViewById(R.id.PagerCountDots);
        btnLayout=(LinearLayout)findViewById(R.id.PagerBtn);

        viewPager = (ViewPager) findViewById(R.id.newgamepager);
        viewPager.setAdapter(new CustomAdapterAG(getSupportFragmentManager(),getApplicationContext() ));
        viewPager.setCurrentItem(0);

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

        nextbtn=(Button)findViewById(R.id.btn_next);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("gameinfo",hero+" "+position+" "+queuetype+" "+String.valueOf(win)+" "+Float.toString(length));
            }
        });

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
                    nextbtn.setVisibility(View.VISIBLE);
                } else nextbtn.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


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
        //NewGameG newGameG = (NewGameG)getSupportFragmentManager().findFragmentById(R.id.newgamepager);
        //newGameG.HeroDataGame(position,hero,heroclick);
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
    public void gameDataG(boolean win, float length, String queuetype) {
        this.win=win;
        this.length=length;
        this.queuetype=queuetype;
    }

    @Override
    public void gameDataGwin(boolean win) {
        this.win=win;
    }

    @Override
    public void gameDataGqueuetype(String queuetype) {
        this.queuetype=queuetype;
    }

    @Override
    public void gameDataGlength(float length) {

    }


    private class CustomAdapterAG extends FragmentPagerAdapter {

        public CustomAdapterAG(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NewGameG();
                case 1:
                    return new NewGameD();
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
