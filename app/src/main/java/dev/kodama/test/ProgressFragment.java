package dev.kodama.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.zip.Inflater;

/**
 * Created by kodama on 4/20/16.
 */
public class ProgressFragment extends Fragment {

    Button kdabutton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summarylayout,container,false);
        kdabutton= (Button) getActivity().findViewById(R.id.kda_button);
        kdabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }



}
