package dev.kodama.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kodama on 6/24/16.
 */
public class PositionsRoot extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.positionrootlayout, container, false);
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		Create fragment with fragment container
		 */
        transaction.replace(R.id.positionroot, new PositionsFragment());
        transaction.commit();

        return view;
    }
}
