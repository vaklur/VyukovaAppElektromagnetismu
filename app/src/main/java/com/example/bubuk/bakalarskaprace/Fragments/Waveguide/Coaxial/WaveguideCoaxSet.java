package com.example.bubuk.semestralniprace.Fragments.Waveguide.Coaxial;

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
import com.example.bubuk.semestralniprace.R;


public class WaveguideCoaxSet extends Fragment implements View.OnClickListener,View.OnTouchListener{

    Button simulation;
    View view;

    ImageView coaxWg;
    EditText r0ET,br0ET,epsrET,mirET,freqET;

    ControlFunction control = new ControlFunction();

    SharePref pref = new SharePref();

    double[] parameters;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wxSetr0ET:
                coaxWg.setImageResource(R.drawable.coaxr0wg);
                break;
            case R.id.wxSetR0ET:
                coaxWg.setImageResource(R.drawable.coaxbr0wg);
                break;
            case R.id.wxSetEpsrET:
                coaxWg.setImageResource(R.drawable.coaxepswg);
                break;
            case R.id.wxSetMiET:
                coaxWg.setImageResource(R.drawable.coaxmiwg);
                break;
            case R.id.wxSetFreqET:
                coaxWg.setImageResource(R.drawable.coaxwg);
                break;
        }

        return false;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveguide_coaxial_set,container,false);

        parameters=pref.loadArrayD("coaxwSet",getActivity());

        initcontrol();

        r0ET.setText(Double.toString(parameters[0]*1000));
        br0ET.setText(Double.toString(parameters[1]*1000));
        epsrET.setText(Double.toString(parameters[2]));
        mirET.setText(Double.toString(parameters[3]));
        freqET.setText(Double.toString(parameters[4]/1000000000));

        return view;
    }

    public void initcontrol(){
        coaxWg= (ImageView) view.findViewById(R.id.wxSetIV);
        simulation = (Button) view.findViewById(R.id.wxSimBTN);
        simulation.setOnClickListener(this);
        r0ET = (EditText) view.findViewById(R.id.wxSetr0ET);
        br0ET = (EditText) view.findViewById(R.id.wxSetR0ET);
        epsrET = (EditText) view.findViewById(R.id.wxSetEpsrET);
        mirET = (EditText) view.findViewById(R.id.wxSetMiET);
        freqET = (EditText) view.findViewById(R.id.wxSetFreqET);
        r0ET.setOnTouchListener(this);
        br0ET.setOnTouchListener(this);
        epsrET.setOnTouchListener(this);
        mirET.setOnTouchListener(this);
        freqET.setOnTouchListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wxSimBTN:
                boolean empty= control.checkIsNull(r0ET,br0ET,epsrET,mirET,freqET);
                if (empty==false){
                    double maxValue [] = {1000,1000,5000,5000,100};
                    double minValue [] = {0,0,0,0,0};
                    String ValueName [] = {"r0","R0","Permitivita","Permeabilita","Frekvence"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,r0ET,br0ET,epsrET,mirET,freqET);
                    if (maxmin ==""){
                        parameters[0]= Double.parseDouble(r0ET.getText().toString())/1000;
                        parameters[1]= Double.parseDouble(br0ET.getText().toString())/1000;
                        parameters[2]=Double.parseDouble(epsrET.getText().toString());
                        parameters[3]=Double.parseDouble(mirET.getText().toString());
                        parameters[4]=Double.parseDouble(freqET.getText().toString())*1000000000;

                        pref.saveArrayD(parameters,"coaxwSet",getActivity());
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
