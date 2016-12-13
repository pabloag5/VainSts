package dev.kodama.test;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.kodama.test.utils.Constants;

public class DetailActivity extends AppCompatActivity {

    int detail_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /**
         * get detail_type to start detail fragment
         */
        Bundle bundle=getIntent().getExtras();
        if (bundle !=null)
            detail_fragment=bundle.getInt("detail");

        /**
         * get fragment
         */
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        switch(detail_fragment){
            case Constants.Detail_Types.KDA_DETAIL:
                KdaFragment kdafragment = new KdaFragment();
                transaction.add(R.id.activity_detail,kdafragment);
                break;
            case Constants.Detail_Types.WINRATIO_DETAIL:
                //WinRatioFragment winratiofragment = new WinRatioFragment();
                //transaction.add(R.id.activity_detail,winratiofragment);
                break;
            case Constants.Detail_Types.HERO_DETAIL:
                HeroDetailFragment heroDetailFragment=new HeroDetailFragment();
                transaction.add(R.id.activity_detail,heroDetailFragment);
                break;
        }
        transaction.commit();
    }
}
