package com.example.bubuk.semestralniprace.Fragments.Waveguide.Rectangular;



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



public class WaveguideRectSet extends Fragment implements View.OnClickListener, View.OnTouchListener {


    Button simulation,modes;
    View view;
    ImageView rectWg;

    WaveguideOwnModes mod = new WaveguideOwnModes();

    EditText aET,bET,epsrET,mirET,freqET;


    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] parameters = new double[5];
    int [] inmodes = new int [20];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wrSetAET:
                rectWg.setImageResource(R.drawable.wga);
                break;
            case R.id.wrSetBET:
                rectWg.setImageResource(R.drawable.wgb);
                break;
            case R.id.wrSetEpsrET:
                rectWg.setImageResource(R.drawable.wgeps);
                break;
            case R.id.wrSetMiET:
                rectWg.setImageResource(R.drawable.wgmi);
                break;
            case R.id.wrSetFreqET:
                rectWg.setImageResource(R.drawable.wg);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.waveguide_rectangular_set, container, false);

        parameters=pref.loadArrayD("rwSet",getActivity());
        inmodes=pref.loadArrayI("setModeRect",getActivity());

        initcontrol();

        aET.setText(Double.toString(parameters[0]*1000));
        bET.setText(Double.toString(parameters[1]*1000));
        epsrET.setText(Double.toString(parameters[2]));
        mirET.setText(Double.toString(parameters[3]));
        freqET.setText(Double.toString(parameters[4]/1000000000));

        return view;
    }

    public void initcontrol(){
        simulation = (Button) view.findViewById(R.id.wrSimBTN);
        simulation.setOnClickListener(this);
        modes = (Button) view.findViewById(R.id.wrModeBTN);
        modes.setOnClickListener(this);
        rectWg=(ImageView) view.findViewById(R.id.wrSetIV);
        aET = (EditText) view.findViewById(R.id.wrSetAET);
        aET.setOnTouchListener(this);
        bET = (EditText) view.findViewById(R.id.wrSetBET);
        bET.setOnTouchListener(this);
        epsrET = (EditText) view.findViewById(R.id.wrSetEpsrET);
        epsrET.setOnTouchListener(this);
        mirET = (EditText) view.findViewById(R.id.wrSetMiET);
        mirET.setOnTouchListener(this);
        freqET = (EditText) view.findViewById(R.id.wrSetFreqET);
        freqET.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrSimBTN:
                boolean empty= control.checkIsNull(aET,bET,epsrET,mirET,freqET);
                if (empty==false){
                    double maxValue [] = {1000,1000,5000,5000,100};
                    double minValue [] = {0,0,0,0,0};
                    String ValueName [] = {"a","b","Permitivita","Permeabilita","Frekvence"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,aET,bET,epsrET,mirET,freqET);
                    if (maxmin ==""){
                    parameters[0]= Double.parseDouble(aET.getText().toString())/1000;
                    parameters[1]=Double.parseDouble(bET.getText().toString())/1000;
                    parameters[2]=Double.parseDouble(epsrET.getText().toString());
                    parameters[3]=Double.parseDouble(mirET.getText().toString());
                    parameters[4]=Double.parseDouble(freqET.getText().toString())*1000000000;
                    pref.saveArrayD(parameters,"rwSet",getActivity());
                    Toast toast=Toast.makeText(getActivity(),"Nastavili jste parametry",Toast.LENGTH_SHORT);
                    toast.show();}
                    else {Toast toast=Toast.makeText(getActivity(),maxmin,Toast.LENGTH_SHORT);
                        toast.show();}
                }
                else {
                    Toast toast=Toast.makeText(getActivity(),"Nastavte všechny parametry",Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.wrModeBTN:
                mod.SetOwnModes(inmodes,getActivity(),"setModeRect",1);

                break;
        }
    }











}
