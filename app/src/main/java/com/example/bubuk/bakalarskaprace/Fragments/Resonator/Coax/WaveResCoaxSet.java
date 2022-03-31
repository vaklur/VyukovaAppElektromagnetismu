package com.example.bubuk.semestralniprace.Fragments.Resonator.Coax;

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

public class WaveResCoaxSet extends Fragment implements View.OnClickListener,View.OnTouchListener {

    Button simulation;
    View view;
    ImageView waverescoax;





    EditText r0ET,br0ET,lET,epsrET,mirET,tandeltaET,conductivityET;


    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] parameters = new double[7];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wrCoaxSetRET:
                waverescoax.setImageResource(R.drawable.coaxresonatorr0);
                break;
            case R.id.wrCoaxSetBRET:
                waverescoax.setImageResource(R.drawable.coaxresonatorbr0);
                break;
            case R.id.wrCoaxSetEpsrET:
                waverescoax.setImageResource(R.drawable.coaxresonatorepsr);
                break;
            case R.id.wrCoaxSetMirET:
                waverescoax.setImageResource(R.drawable.coaxresonatormir);
                break;
            case R.id.wrCoaxSetLET:
                waverescoax.setImageResource(R.drawable.coaxresonatorl);
                break;
            case R.id.wrCoaxSetSigmaET:
                waverescoax.setImageResource(R.drawable.coaxresonator);
                break;
            case R.id.wrCoaxSetTanDeltaET:
                waverescoax.setImageResource(R.drawable.coaxresonator);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_coax_set,container,false);

        /*load configuration data*/
        parameters=pref.loadArrayD("wrCoaxSet",getActivity());

        /*initialize widgets*/
        initcontrol();

        /*preset edittext*/
        r0ET.setText(Double.toString(parameters[0]*1000));
        br0ET.setText(Double.toString(parameters[1]*1000));
        lET.setText(Double.toString(parameters[2]*1000));
        epsrET.setText(Double.toString(parameters[3]));
        mirET.setText(Double.toString(parameters[4]));
        tandeltaET.setText(Double.toString(parameters[5]));
        conductivityET.setText(Double.toString(parameters[6]));

        return view;}

    public void initcontrol(){
        simulation = (Button) view.findViewById(R.id.wrCoaxSetBTN);
        simulation.setOnClickListener(this);
        waverescoax=(ImageView) view.findViewById(R.id.wrCoaxSetIV);
        r0ET = (EditText) view.findViewById(R.id.wrCoaxSetRET);
        r0ET.setOnTouchListener(this);
        br0ET = (EditText) view.findViewById(R.id.wrCoaxSetBRET);
        br0ET.setOnTouchListener(this);
        lET = (EditText) view.findViewById(R.id.wrCoaxSetLET);
        lET.setOnTouchListener(this);
        epsrET = (EditText) view.findViewById(R.id.wrCoaxSetEpsrET);
        epsrET.setOnTouchListener(this);
        mirET = (EditText) view.findViewById(R.id.wrCoaxSetMirET);
        mirET.setOnTouchListener(this);
        tandeltaET = (EditText) view.findViewById(R.id.wrCoaxSetTanDeltaET);
        tandeltaET.setOnTouchListener(this);
        conductivityET = (EditText) view.findViewById(R.id.wrCoaxSetSigmaET);
        conductivityET.setOnTouchListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrCoaxSetBTN:
                boolean empty= control.checkIsNull(r0ET,br0ET,lET,epsrET,mirET,tandeltaET,conductivityET);
                if (empty==false){
                    double maxValue [] = {1000,1000,1000,5000,5000,100,1E9};
                    double minValue [] = {0,0,0,0,0,0,0};
                    String ValueName [] = {"r0","R0","l","Permitivita","Permeabilita","Ztrátový činitel","Měrná vodivost"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,r0ET,br0ET,lET,epsrET,mirET,tandeltaET,conductivityET);
                    if (maxmin ==""){
                        parameters[0]= Double.parseDouble(r0ET.getText().toString())/1000;
                        parameters[1]=Double.parseDouble(br0ET.getText().toString())/1000;
                        parameters[2]=Double.parseDouble(lET.getText().toString())/1000;
                        parameters[3]=Double.parseDouble(epsrET.getText().toString());
                        parameters[4]=Double.parseDouble(mirET.getText().toString());
                        parameters[5]=Double.parseDouble(tandeltaET.getText().toString());
                        parameters[6]=Double.parseDouble(conductivityET.getText().toString());
                        pref.saveArrayD(parameters,"wrCoaxSet",getActivity());
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
        }
    }
}
