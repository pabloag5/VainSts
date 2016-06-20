package dev.kodama.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

//Main Activity
public class MainActivity extends AppCompatActivity
        /*implements Communicator*/ {

    private PopupWindow newgame;
    private LayoutInflater layoutInflater;
    private CoordinatorLayout layout;
    TabLayout tabLayout;
    ViewPager viewPager;
    private int[] tabicons = {R.drawable.ic_progress_white_24dp,R.drawable.ic_heroes_white_24dp, R.drawable.ic_position_white_24dp,
            R.drawable.ic_progress_black_24dp,R.drawable.ic_heroes_black_24dp, R.drawable.ic_position_black_24dp };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = (CoordinatorLayout) findViewById(R.id.main_layout);

        viewPager = (ViewPager) findViewById(R.id.viewcontent);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext() ));

        tabLayout = (TabLayout) findViewById(R.id.tabmenu);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabicons[3]);
        tabLayout.getTabAt(1).setIcon(tabicons[1]);
        tabLayout.getTabAt(2).setIcon(tabicons[2]);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.colorAccent));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorBackground));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch(position) {
                    case 0: {
                        tabLayout.getTabAt(0).setIcon(tabicons[3]);
                        tabLayout.getTabAt(1).setIcon(tabicons[1]);
                        tabLayout.getTabAt(2).setIcon(tabicons[2]);
                        break;

                    }
                    case 1: {
                        tabLayout.getTabAt(0).setIcon(tabicons[0]);
                        tabLayout.getTabAt(1).setIcon(tabicons[4]);
                        tabLayout.getTabAt(2).setIcon(tabicons[2]);
                        break;

                    }
                    case 2: {
                        tabLayout.getTabAt(0).setIcon(tabicons[0]);
                        tabLayout.getTabAt(1).setIcon(tabicons[1]);
                        tabLayout.getTabAt(2).setIcon(tabicons[5]);
                        break;

                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

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
        switch (item.getItemId()){
            case R.id.action_newgame:
                newgameWindow();
                return true;
            case R.id.action_refresh:
                Snackbar.make(findViewById(R.id.main_layout),"DONE",Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void newgameWindow (){
        startActivity(new Intent(getApplicationContext(),newgameActivity.class));

    }
    public void add_fragment(Fragment fragment, int container){
        getSupportFragmentManager().beginTransaction()
                .add(container, fragment)
                .commit();
    }

    private class CustomAdapter extends FragmentPagerAdapter {
        private String fragments [] = {"Progress", "hero","Position"};


        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new RecentFragment();
                case 1:
                    return new Fragment2();
                case 2:
                    return new PositionsFragment();

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
            //return fragments[position];
            return null;
        }
    }
}

