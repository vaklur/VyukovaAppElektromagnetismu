package com.example.bubuk.semestralniprace.Fragments.Waveguide.Circular;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.ControlFunction;
import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.OtherClass.WaveguideOwnModes;
import com.example.bubuk.semestralniprace.R;

public class WaveguideCircSet extends Fragment implements View.OnClickListener,View.OnTouchListener{

    Button simulation,modes;
    View view;
    ImageView circWg;
    EditText aET,epsrET,mirET,freqET;

    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();
    WaveguideOwnModes mod = new WaveguideOwnModes();

    double[] parameters;
    int [] inmodes = new int [20];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wcSetAET:
                circWg.setImageResource(R.drawable.circawg);
                break;
            case R.id.wcSetEpsrET:
                circWg.setImageResource(R.drawable.circepswg);
                break;
            case R.id.wcSetMiET:
                circWg.setImageResource(R.drawable.circmiwg);
                break;
            case R.id.wcSetFreqET:
                circWg.setImageResource(R.drawable.circwg);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveguide_circular_set,container,false);

        parameters=pref.loadArrayD("cwSet",getActivity());
        inmodes=pref.loadArrayI("setModeCirc",getActivity());

        initcontrol();

        aET.setText(Double.toString(parameters[0]*1000));
        epsrET.setText(Double.toString(parameters[1]));
        mirET.setText(Double.toString(parameters[2]));
        freqET.setText(Double.toString(parameters[3]/1000000000));


        return view;
    }

    /* initialize widgets*/
    public void initcontrol(){
        circWg=(ImageView) view.findViewById(R.id.wcSetIV);
        simulation = (Button) view.findViewById(R.id.wcSimBTN);
        simulation.setOnClickListener(this);
        modes = (Button) view.findViewById(R.id.wcModeBTN);
        modes.setOnClickListener(this);
        aET = (EditText) view.findViewById(R.id.wcSetAET);
        epsrET = (EditText) view.findViewById(R.id.wcSetEpsrET);
        mirET = (EditText) view.findViewById(R.id.wcSetMiET);
        freqET = (EditText) view.findViewById(R.id.wcSetFreqET);
        aET.setOnTouchListener(this);
        epsrET.setOnTouchListener(this);
        mirET.setOnTouchListener(this);
        freqET.setOnTouchListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wcSimBTN:
                boolean empty= control.checkIsNull(aET,epsrET,mirET,freqET);
                if (empty==false){
                    double maxValue [] = {1000,5000,5000,100};
                    double minValue [] = {1e-6,1,1,1};
                    String ValueName [] = {"a","Permeabilita","Permitivita","Frekvence"};
                    String max = control.checkIsMaxMin(maxValue,minValue,ValueName,aET,epsrET,mirET,freqET);
                    if (max ==""){
                        parameters[0]= Double.parseDouble(aET.getText().toString())/1000;
                        parameters[1]=Double.parseDouble(epsrET.getText().toString());
                        parameters[2]=Double.parseDouble(mirET.getText().toString());
                        parameters[3]=Double.parseDouble(freqET.getText().toString())*1000000000;

                        pref.saveArrayD(parameters,"cwSet",getActivity());

                        Toast toast=Toast.makeText(getActivity(),"Nastavili jste parametry",Toast.LENGTH_SHORT);
                        toast.show();}
                    else {Toast toast=Toast.makeText(getActivity(),max,Toast.LENGTH_SHORT);
                        toast.show();}
                }
                else {
                    Toast toast=Toast.makeText(getActivity(),"Nastavte všechny parametry",Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.wcModeBTN:
                mod.SetOwnModes(inmodes,getActivity(),"setModeCirc",2);
                break;
        }
    }
}
