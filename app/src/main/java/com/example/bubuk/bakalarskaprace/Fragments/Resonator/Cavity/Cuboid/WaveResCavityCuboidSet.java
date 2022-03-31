package com.example.bubuk.semestralniprace.Fragments.Resonator.Cavity.Cuboid;

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
import com.example.bubuk.semestralniprace.OtherClass.WaveresonatorsOwnModes;
import com.example.bubuk.semestralniprace.R;


public class WaveResCavityCuboidSet extends Fragment implements View.OnClickListener,View.OnTouchListener {

    Button simulation,modes;
    View view;
    ImageView waverescavvub;
    WaveresonatorsOwnModes mod = new WaveresonatorsOwnModes();
    EditText aET,bET,lET,epsrET,mirET,tandeltaET,conductivityET;


    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] parameters = new double[7];
    int [] inmodes = new int [25];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wrCavCubSetAET:
                waverescavvub.setImageResource(R.drawable.cavcubresonatora);
                break;
            case R.id.wrCavCubSetBET:
                waverescavvub.setImageResource(R.drawable.cavcubresonatorb);
                break;
            case R.id.wrCavCubSetEpsrET:
                waverescavvub.setImageResource(R.drawable.cavcubresonatorepsr);
                break;
            case R.id.wrCavCubSetMirET:
                waverescavvub.setImageResource(R.drawable.cavcubresonatormir);
                break;
            case R.id.wrCavCubSetLET:
                waverescavvub.setImageResource(R.drawable.cavcubresonatorl);
                break;
            case R.id.wrCavCubSetSigmaET:
                waverescavvub.setImageResource(R.drawable.cavcubresonator);
                break;
            case R.id.wrCavCubSetTanDeltaET:
                waverescavvub.setImageResource(R.drawable.cavcubresonator);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_cavity_cuboid_set,container,false);
        parameters=pref.loadArrayD("wrCavCubSet",getActivity());
        inmodes=pref.loadArrayI("setModeCavCub",getActivity());

        initcontrol();

        conductivityET.setOnTouchListener(this);
        aET.setText(Double.toString(parameters[0]*1000));
        bET.setText(Double.toString(parameters[1]*1000));
        lET.setText(Double.toString(parameters[2]*1000));
        epsrET.setText(Double.toString(parameters[3]));
        mirET.setText(Double.toString(parameters[4]));
        tandeltaET.setText(Double.toString(parameters[5]));
        conductivityET.setText(Double.toString(parameters[6]));

        return view;
    }

    public void initcontrol(){
        simulation = (Button) view.findViewById(R.id.wrCavCubSetBTN);
        simulation.setOnClickListener(this);
        modes = (Button) view.findViewById(R.id.wrCavCubModeBTN);
        modes.setOnClickListener(this);
        waverescavvub=(ImageView) view.findViewById(R.id.wrCavCubSetIV);
        aET = (EditText) view.findViewById(R.id.wrCavCubSetAET);
        aET.setOnTouchListener(this);
        bET = (EditText) view.findViewById(R.id.wrCavCubSetBET);
        bET.setOnTouchListener(this);
        lET = (EditText) view.findViewById(R.id.wrCavCubSetLET);
        lET.setOnTouchListener(this);
        epsrET = (EditText) view.findViewById(R.id.wrCavCubSetEpsrET);
        epsrET.setOnTouchListener(this);
        mirET = (EditText) view.findViewById(R.id.wrCavCubSetMirET);
        mirET.setOnTouchListener(this);
        tandeltaET = (EditText) view.findViewById(R.id.wrCavCubSetTanDeltaET);
        tandeltaET.setOnTouchListener(this);
        conductivityET = (EditText) view.findViewById(R.id.wrCavCubSetSigmaET);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrCavCubSetBTN:
                boolean empty= control.checkIsNull(aET,bET,lET,epsrET,mirET,tandeltaET,conductivityET);
                if (empty==false){
                    double maxValue [] = {1000,1000,1000,5000,5000,100,1E9};
                    double minValue [] = {0,0,0,0,0,0,0};
                    String ValueName [] = {"a","b","l","Permitivita","Permeabilita","Ztrátový činitel","Měrná vodivost"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,aET,bET,lET,epsrET,mirET,tandeltaET,conductivityET);
                    if (maxmin ==""){
                        parameters[0]= Double.parseDouble(aET.getText().toString())/1000;
                        parameters[1]=Double.parseDouble(bET.getText().toString())/1000;
                        parameters[2]=Double.parseDouble(lET.getText().toString())/1000;
                        parameters[3]=Double.parseDouble(epsrET.getText().toString());
                        parameters[4]=Double.parseDouble(mirET.getText().toString());
                        parameters[5]=Double.parseDouble(tandeltaET.getText().toString());
                        parameters[6]=Double.parseDouble(conductivityET.getText().toString());
                        pref.saveArrayD(parameters,"wrCavCubSet",getActivity());
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
            case R.id.wrCavCubModeBTN:
                mod.SetOwnModes(inmodes,getActivity(),"setModeCavCub",1);
                break;
        }
    }
}
