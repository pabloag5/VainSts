package dev.kodama.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupWindow;

import java.util.List;

import dev.kodama.test.db.DatabaseTransactions;
import dev.kodama.test.db.Game;
import dev.kodama.test.utils.Constants;
import dev.kodama.test.utils.HalcyonUtils;

//Main Activity
public class MainActivity extends AppCompatActivity implements HeroesFragment.CommHeroDetailFragment {
    private PopupWindow newgame;
    private LayoutInflater layoutInflater;
    private CoordinatorLayout layout;
    TabLayout tabLayout;
    ViewPager viewPager;
    int secondpage;
    Fragment fragment;
    private DatabaseTransactions dbTrans;
    private int[] tabicons = {R.drawable.ic_progress_white_24dp, R.drawable.ic_heroes_white_24dp, R.drawable.ic_position_white_24dp,
            R.drawable.ic_progress_black_24dp, R.drawable.ic_heroes_black_24dp, R.drawable.ic_position_black_24dp};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * populate database
         */
        dbTrans = DatabaseTransactions.getInstance(this.getApplicationContext());

        createNewGames(30);

        /**
         * start view
         */
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        layout = (CoordinatorLayout) findViewById(R.id.main_layout);

        viewPager = (ViewPager) findViewById(R.id.viewcontent);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tabmenu);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabicons[3]);
        tabLayout.getTabAt(1).setIcon(tabicons[1]);
        tabLayout.getTabAt(2).setIcon(tabicons[2]);
        tabLayout.getTabAt(3).setIcon(tabicons[2]);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorBackground));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0: {
                        tabLayout.getTabAt(0).setIcon(tabicons[3]);
                        tabLayout.getTabAt(1).setIcon(tabicons[1]);
                        tabLayout.getTabAt(2).setIcon(tabicons[2]);
                        tabLayout.getTabAt(3).setIcon(tabicons[2]);
                        getSupportActionBar().setTitle(getResources().getText(R.string.overall_title));
                        /*
                        if (secondpage==0) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        } else getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        */
                        break;

                    }
                    case 1: {
                        tabLayout.getTabAt(0).setIcon(tabicons[0]);
                        tabLayout.getTabAt(1).setIcon(tabicons[4]);
                        tabLayout.getTabAt(2).setIcon(tabicons[2]);
                        tabLayout.getTabAt(3).setIcon(tabicons[2]);
                        getSupportActionBar().setTitle(getResources().getText(R.string.heroes_title));
                        /*
                        if (secondpage==1) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        } else getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        */
                        break;

                    }
                    case 2: {
                        tabLayout.getTabAt(0).setIcon(tabicons[0]);
                        tabLayout.getTabAt(1).setIcon(tabicons[1]);
                        tabLayout.getTabAt(2).setIcon(tabicons[5]);
                        tabLayout.getTabAt(3).setIcon(tabicons[2]);
                        getSupportActionBar().setTitle(getResources().getText(R.string.roles_title));
                        /*
                        if (secondpage==2) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        } else getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        */
                        break;

                    }
                    case 3: {
                        tabLayout.getTabAt(0).setIcon(tabicons[0]);
                        tabLayout.getTabAt(1).setIcon(tabicons[1]);
                        tabLayout.getTabAt(2).setIcon(tabicons[2]);
                        tabLayout.getTabAt(3).setIcon(tabicons[5]);
                        getSupportActionBar().setTitle(getResources().getText(R.string.recent_games_title));
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });


        //get display width & height
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;

    }

    private void createNewGames(int numberofgames) {
        int i=0;
        Game game;
        while (i<numberofgames){
            game=HalcyonUtils.createRandomGame(Constants.Game_Types.RANKED);
            dbTrans.addNewGame(game);
            i++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_newgame:
                newActivity(NewgameActivity.class);
                return true;
            /*
            case R.id.action_refresh:
                Snackbar.make(findViewById(R.id.main_layout),"DONE",Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                return true;
            */
            case android.R.id.home:
                /*
                switch(secondpage){
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.progressroot, fragment)
                                .commit();
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        secondpage=3;
                        return true;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.heroesroot, fragment)
                                .commit();
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        secondpage=3;
                        return true;
                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.positionroot, fragment)
                                .commit();
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        secondpage=3;
                        return true;
                }
                   */
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void newActivity(Class activity) {
        startActivity(new Intent(getApplicationContext(), activity));

    }

    public void add_fragment(Fragment fragment, int container) {
        getSupportFragmentManager().beginTransaction()
                .add(container, fragment)
                .commit();
    }

    private class CustomAdapter extends FragmentStatePagerAdapter {
        private String fragments[] = {getResources().getText(R.string.overall_title).toString(),
                getResources().getText(R.string.heroes_title).toString(),
                getResources().getText(R.string.roles_title).toString(),
                getResources().getText(R.string.recent_games_title).toString()};


        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new overallRoot();
                case 1:
                    return new HeroesRoot();
                case 2:
                    return new RoleRoot();
                case 3:
                    return new recentGamesFragment();
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return fragments[position];
            return null;
        }
    }

    public void fragmentParent(Fragment fragment, int secondpage) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.fragment = fragment;
        this.secondpage = secondpage;
    }

    @Override
    public void HerofragmentParent(int position, int secondpage, Fragment fragment, List<Gamestats> data) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.fragment = fragment;
        this.secondpage = secondpage;

        HeroDetailFragment frag = new HeroDetailFragment();
        Bundle args = new Bundle();
        args.putInt(HeroDetailFragment.ARG_POSITION, position);
        frag.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.heroesroot, frag)
                .addToBackStack(null)
                .commit();


    }

}

