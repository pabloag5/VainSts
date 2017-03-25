package dev.kodama.test;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dev.kodama.test.utils.Constants;

public class DetailActivity extends AppCompatActivity {

    int detail_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * get detail_type to start detail fragment
         */
        Bundle bundle=getIntent().getExtras();
        if (bundle !=null)
            detail_fragment=bundle.getInt("detail");

        if (detail_fragment<0){
            setContentView(R.layout.activity_detail);

            /**
             * set fragment
             */
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

            switch(detail_fragment){
                case Constants.Detail_Types.KDA_DETAIL:
                    KdaFragment kdafragment = new KdaFragment();
                    transaction.add(R.id.activity_detail,kdafragment);
                    break;
                case Constants.Detail_Types.WINRATIO_DETAIL:
                    WinRatioFragment winratiofragment= new WinRatioFragment();
                    transaction.add(R.id.activity_detail,winratiofragment);
                    break;
            }
            transaction.commit();
        } else {

            setContentView(R.layout.herodetaillayout);
            Bundle args = new Bundle();
            Toolbar herotoolbar = (Toolbar) findViewById(R.id.hero_toolbar);
            setSupportActionBar(herotoolbar);
            //herotoolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

            // Get a support ActionBar corresponding to this toolbar
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(Constants.Heroes.heroesMap.get(detail_fragment+1));

            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitleEnabled(false);

            ImageView heroimage=(ImageView) findViewById(R.id.hero_detail_image);
            heroimage.setImageResource(Constants.Heroes.iconsMap.get(detail_fragment+1));

            final ViewPager heroviewPager = (ViewPager) findViewById(R.id.heroviewpager);
            if (heroviewPager!= null) {
                heroCustomAdapter adapter=new heroCustomAdapter(getSupportFragmentManager());
                heroviewPager.setAdapter(adapter);

            }
            TabLayout herotabLayout = (TabLayout) findViewById(R.id.herotabs);
            herotabLayout.setupWithViewPager(heroviewPager);
            /*
            heroviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(herotabLayout));
            herotabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    heroviewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    //heroviewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    //heroviewPager.setCurrentItem(tab.getPosition());
                }
            });
            */

        }

    }
    private class heroCustomAdapter extends FragmentStatePagerAdapter {
        private String fragments[] = {getResources().getText(R.string.overview).toString(),
                getResources().getText(R.string.performance).toString(),
                getResources().getText(R.string.matchup).toString()};


        public heroCustomAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HeroOverviewFragment();
                case 1:
                    return new HeroPerformanceFragment();
                case 2:
                    return new HeroMatchupFragment();
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
            return fragments[position];
        }
    }
}
