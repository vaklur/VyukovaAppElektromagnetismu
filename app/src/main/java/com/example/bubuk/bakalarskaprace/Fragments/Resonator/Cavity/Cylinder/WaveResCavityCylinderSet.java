package com.example.bubuk.semestralniprace.Fragments.Resonator.Cavity.Cylinder;

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
import com.example.bubuk.semestralniprace.OtherClass.WaveresonatorsOwnModes;
import com.example.bubuk.semestralniprace.R;

public class WaveResCavityCylinderSet extends Fragment implements View.OnClickListener,View.OnTouchListener {

    Button simulation,modes;
    View view;
    ImageView waverescavcyl;

    WaveresonatorsOwnModes mod = new WaveresonatorsOwnModes();
    EditText aET,lET,epsrET,mirET,tandeltaET,conductivityET;

    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] parameters = new double[6];
    int [] inmodes = new int [25];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wrCavCylSetAET:
                waverescavcyl.setImageResource(R.drawable.cavcylresonatora);
                break;
            case R.id.wrCavCylSetEpsrET:
                waverescavcyl.setImageResource(R.drawable.cavcylresonatorepsr);
                break;
            case R.id.wrCavCylSetMirET:
                waverescavcyl.setImageResource(R.drawable.cavcylresonatormir);
                break;
            case R.id.wrCavCylSetLET:
                waverescavcyl.setImageResource(R.drawable.cavcylresonatorl);
                break;
            case R.id.wrCavCylSetSigmaET:
                waverescavcyl.setImageResource(R.drawable.cavcylresonator);
                break;
            case R.id.wrCavCylSetTanDeltaET:
                waverescavcyl.setImageResource(R.drawable.cavcylresonator);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_cavity_cylinder_set,container,false);
        parameters=pref.loadArrayD("wrCavCylSet",getActivity());
        inmodes=pref.loadArrayI("setModeCavCyl",getActivity());

        initcontrol();

        aET.setText(Double.toString(parameters[0]*1000));
        lET.setText(Double.toString(parameters[1]*1000));
        epsrET.setText(Double.toString(parameters[2]));
        mirET.setText(Double.toString(parameters[3]));
        tandeltaET.setText(Double.toString(parameters[4]));
        conductivityET.setText(Double.toString(parameters[5]));

        return view;}



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrCavCylSetBTN:
                boolean empty= control.checkIsNull(aET,lET,epsrET,mirET,tandeltaET,conductivityET);
                if (empty==false){
                    double maxValue [] = {1000,1000,5000,5000,100,1E9};
                    double minValue [] = {0,0,0,0,0,0};
                    String ValueName [] = {"a","l","Permitivita","Permeabilita","Ztrátový činitel","Měrná vodivost"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,aET,lET,epsrET,mirET,tandeltaET,conductivityET);
                    if (maxmin ==""){
                        parameters[0]= Double.parseDouble(aET.getText().toString())/1000;
                        parameters[1]=Double.parseDouble(lET.getText().toString())/1000;
                        parameters[2]=Double.parseDouble(epsrET.getText().toString());
                        parameters[3]=Double.parseDouble(mirET.getText().toString());
                        parameters[4]=Double.parseDouble(tandeltaET.getText().toString());
                        parameters[5]=Double.parseDouble(conductivityET.getText().toString());
                        pref.saveArrayD(parameters,"wrCavCylSet",getActivity());
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
            case R.id.wrCavCylModeBTN:
                mod.SetOwnModes(inmodes,getActivity(),"setModeCavCyl",2);
                break;
        }
    }

    public void initcontrol(){
        simulation = (Button) view.findViewById(R.id.wrCavCylSetBTN);
        simulation.setOnClickListener(this);
        modes = (Button) view.findViewById(R.id.wrCavCylModeBTN);
        modes.setOnClickListener(this);
        waverescavcyl=(ImageView) view.findViewById(R.id.wrCavCylSetIV);
        aET = (EditText) view.findViewById(R.id.wrCavCylSetAET);
        aET.setOnTouchListener(this);
        lET = (EditText) view.findViewById(R.id.wrCavCylSetLET);
        lET.setOnTouchListener(this);
        epsrET = (EditText) view.findViewById(R.id.wrCavCylSetEpsrET);
        epsrET.setOnTouchListener(this);
        mirET = (EditText) view.findViewById(R.id.wrCavCylSetMirET);
        mirET.setOnTouchListener(this);
        tandeltaET = (EditText) view.findViewById(R.id.wrCavCylSetTanDeltaET);
        tandeltaET.setOnTouchListener(this);
        conductivityET = (EditText) view.findViewById(R.id.wrCavCylSetSigmaET);
        conductivityET.setOnTouchListener(this);
    }
}
