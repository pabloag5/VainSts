package dev.kodama.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Fragment1 extends Fragment implements View.OnClickListener {

    boolean mDuelPane;
    int mCurCheckPosition = 0;
    FrameLayout fldetail;
    FrameLayout flinfo;
    RadioGroup rgresult;
    EditText editText;
    Button button;
    String message;
    Communicator comm;

    int wrapContent=FrameLayout.LayoutParams.WRAP_CONTENT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){

        } else {
            message= savedInstanceState.getString("message_send");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        comm= (Communicator) getActivity();
        fldetail=(FrameLayout) getActivity().findViewById(R.id.fragment2_container);
        flinfo=(FrameLayout) getActivity().findViewById(R.id.fragment_container);
        rgresult= (RadioGroup) getActivity().findViewById(R.id.rgresult);
        button= (Button) getActivity().findViewById(R.id.send_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message_send",message);

    }

    @Override
    public void onClick(View v) {
        FrameLayout.LayoutParams lParams = new FrameLayout.LayoutParams(wrapContent,wrapContent);
        //FrameLayout.LayoutParams lParams2 = new FrameLayout.LayoutParams(30,30,0);

        if (rgresult.getCheckedRadioButtonId()==R.id.win_result) {
            message="win game";
        } else message="loss game";

        TextView textResult= new TextView(getActivity());
        textResult.setText(message);
        textResult.setBackgroundColor(Color.GREEN);
        fldetail.addView(textResult, lParams);
        fldetail.measure(50,50);
        //fldetail.setLayoutParams(lParams);

        //flinfo.setBackgroundColor(Color.argb(255, 255, 255, 255));
        //flinfo.setLayoutParams(lParams2);
        //comm.respond(message);
    }
}
