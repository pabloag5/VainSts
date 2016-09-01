package dev.kodama.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
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
        NewGame1.CommGameData1, ViewAdapter.CommHeroDataG, NewGame2.CommGameData2, NewGame3.CommGameData3,
        NewGame4.CommGameData4{

    ViewPager viewPager;
    RecyclerView recyclerView; //to freeze on click
    private LinearLayout dotIndicator, numberspad;
    private int dotsCnt;
    protected View view;
    private ImageView[] dots;
    private Button nextbtn;
    private Button backbtn;
    boolean win;
    float minutes;
    float seconds;
    int queuetype;
    String hero;
    String position;
    String csVal="";
    String goldVal="";
    NumberPicker numberPicker;
    int totalkills=0;
    int killsVal=0;
    int deathsVal=0;
    int assistsVal=0;
    View newgame2View;
    View newgame3View;
    View newgame4View;

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
                    Log.d("New Game",Integer.toString(queuetype)+" "+hero+" "+position+
                            " "+Boolean.toString(win)+ " "+Float.toString(minutes+seconds)+
                            " "+Integer.toString(totalkills)+ " "+Integer.toString(killsVal)+
                            " "+Integer.toString(deathsVal)+ " "+Integer.toString(assistsVal)+
                            " "+csVal+ " "+goldVal);
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
                    numberspad.setVisibility(View.GONE);
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
        numberPicker.setScaleX(1.2f);
        numberPicker.setScaleY(1.2f);
        numberPicker.setWrapSelectorWheel(true);

        //setting numberspad
        Button btn1=(Button) findViewById(R.id.btn1);
        Button btn2=(Button) findViewById(R.id.btn2);
        Button btn3=(Button) findViewById(R.id.btn3);
        Button btn4=(Button) findViewById(R.id.btn4);
        Button btn5=(Button) findViewById(R.id.btn5);
        Button btn6=(Button) findViewById(R.id.btn6);
        Button btn7=(Button) findViewById(R.id.btn7);
        Button btn8=(Button) findViewById(R.id.btn8);
        Button btn9=(Button) findViewById(R.id.btn9);
        Button btn0=(Button) findViewById(R.id.btn0);
        Button btndelete=(Button)findViewById(R.id.btndelete);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("9");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberpadClick("0");
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newgame4View != null) {
                    final NewGame4 f4 = (NewGame4) getSupportFragmentManager()
                            .findFragmentByTag("android:switcher:" + R.id.newgamepager + ":" + 3);
                    switch (newgame4View.getId()) {
                        case R.id.cstxt:
                            if (!csVal.equals("0")) {
                                if (csVal.length() > 0) {
                                    csVal = csVal.substring(0, csVal.length() - 1);
                                    f4.eraseLastChar(newgame4View.getId());
                                }
                            }
                            break;
                        case R.id.goldtxt:
                            if (!goldVal.equals("0")) {
                                if (goldVal.length() > 0) {
                                    goldVal=goldVal.substring(0, goldVal.length() - 1);
                                    f4.eraseLastChar(newgame4View.getId());
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
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

    public void numberpadClick(String number){
        if (newgame4View != null) {
            final NewGame4 f4 = (NewGame4) getSupportFragmentManager()
                    .findFragmentByTag("android:switcher:" + R.id.newgamepager + ":" + 3);
            switch (newgame4View.getId()) {
                case R.id.cstxt:
                    if (csVal.equals("0")) {
                        csVal = number;
                    } else csVal += number;
                    f4.setValToView(newgame4View.getId(), number);
                    break;
                case R.id.goldtxt:
                    if (goldVal.equals("0")){
                        goldVal = number;
                    } else goldVal+=number;
                    f4.setValToView(newgame4View.getId(), number);
                    break;
                default:
                    break;
            }
        }
    }

    //HeroDataG: method from hero selector recyclerView
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

    //gamedataGwin/gameDataGqueuetype: methods from NewGame1 fragment capturing win and queuetype radiobuttons
    @Override
    public void gameDataGwin(boolean win) {
        this.win=win;
    }

    @Override
    public void gameDataGqueuetype(int queuetype) {
        this.queuetype=queuetype;
    }

    //gameDataView: method call from NewGame2 fragment capturing data from total kills and game time
    @Override
    public void gameDataView(View view) {
        newgame2View=view;
        final NewGame2 f2= (NewGame2) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:"+R.id.newgamepager+":"+1);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch (newgame2View.getId()){
                    case (R.id.minutestxt):
                        f2.setValToView(newgame2View.getId(),newVal);
                        minutes=newVal;
                        break;
                    case (R.id.secondstxt):
                        f2.setValToView(newgame2View.getId(),newVal);
                        seconds= (float) (newVal/60.0);
                        break;
                    case (R.id.totalkills):
                        f2.setValToView(newgame2View.getId(),newVal);
                        totalkills=newVal;
                        Log.d("total kills", Integer.toString(totalkills));
                        break;
                    default:
                        break;
                }
            }
        });

    }

    //gameDataKDA: method call from NewGame3 fragment capturing KDA views
    @Override
    public void gameDataKDA(View view) {
        newgame3View=view;
        final NewGame3 f3= (NewGame3) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:"+R.id.newgamepager+":"+2);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch (newgame3View.getId()){
                    case (R.id.killstxt):
                        f3.setValToView(newgame3View.getId(),newVal);
                        killsVal=newVal;
                        Log.d("length",Integer.toString(killsVal));
                        break;
                    case (R.id.deathstxt):
                        f3.setValToView(newgame3View.getId(),newVal);
                        deathsVal=newVal;
                        Log.d("length",Integer.toString(deathsVal));
                        break;
                    case (R.id.assiststxt):
                        f3.setValToView(newgame3View.getId(),newVal);
                        assistsVal=newVal;
                        Log.d("total kills", Integer.toString(assistsVal));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //gameDataCSGold: method call from NewGame4 fragment capturing CS and Gold views
    @Override
    public void gameDataCSGold(View view, String currentVal) {
        newgame4View=view;
        if (newgame4View.getId()==R.id.cstxt){
            csVal=currentVal;
        } else goldVal=currentVal;
    }

    //ViewPager adapter class
    private class CustomAdapterAG extends FragmentStatePagerAdapter {

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
