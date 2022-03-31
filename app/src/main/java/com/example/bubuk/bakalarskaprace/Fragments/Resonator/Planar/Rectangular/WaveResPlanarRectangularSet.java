package com.example.bubuk.semestralniprace.Fragments.Resonator.Planar.Rectangular;

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

public class WaveResPlanarRectangularSet extends Fragment implements View.OnClickListener,View.OnTouchListener {

    Button simulation,modes;
    View view;
    ImageView resplanarrect;


    WaveresonatorsOwnModes mod = new WaveresonatorsOwnModes();



    EditText wET,lET,hET,epsrET,tandeltaET,conductivityET;


    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] parameters = new double[6];
    int [] inmodes = new int [25];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wrPlanarRectSetWET:
                resplanarrect.setImageResource(R.drawable.planarrectresonatorw);
                break;
            case R.id.wrPlanarRectSetHET:
                resplanarrect.setImageResource(R.drawable.planarrectresonatorh);
                break;
            case R.id.wrPlanarRectSetLET:
                resplanarrect.setImageResource(R.drawable.planarrectresonatorl);
                break;
            case R.id.wrPlanarRectSetEpsrET:
                resplanarrect.setImageResource(R.drawable.planarrectresonatorepsr);
                break;
            case R.id.wrPlanarRectSetTanDeltaET:
                resplanarrect.setImageResource(R.drawable.planarrectresonator);
                break;
            case R.id.wrPlanarRectSetSigmaET:
                resplanarrect.setImageResource(R.drawable.planarrectresonator);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_planar_rectangular_set,container,false);
        parameters=pref.loadArrayD("wrPlanarRectSet",getActivity());
        inmodes=pref.loadArrayI("setModePlanarRect",getActivity());

        initcontrol();

        wET.setText(Double.toString(parameters[0]*1000));
        lET.setText(Double.toString(parameters[1]*1000));
        hET.setText(Double.toString(parameters[2]*1000));
        epsrET.setText(Double.toString(parameters[3]));
        tandeltaET.setText(Double.toString(parameters[4]));
        conductivityET.setText(Double.toString(parameters[5]));

        return view;
    }

    public void initcontrol(){
        simulation = (Button) view.findViewById(R.id.wrPlanarRectSetBTN);
        simulation.setOnClickListener(this);
        modes = (Button) view.findViewById(R.id.wrPlanarRectModeBTN);
        modes.setOnClickListener(this);
        resplanarrect=(ImageView) view.findViewById(R.id.wrPlanarRectSetIV);
        wET = (EditText) view.findViewById(R.id.wrPlanarRectSetWET);
        wET.setOnTouchListener(this);
        hET = (EditText) view.findViewById(R.id.wrPlanarRectSetHET);
        hET.setOnTouchListener(this);
        lET = (EditText) view.findViewById(R.id.wrPlanarRectSetLET);
        lET.setOnTouchListener(this);
        epsrET = (EditText) view.findViewById(R.id.wrPlanarRectSetEpsrET);
        epsrET.setOnTouchListener(this);
        tandeltaET = (EditText) view.findViewById(R.id.wrPlanarRectSetTanDeltaET);
        tandeltaET.setOnTouchListener(this);
        conductivityET = (EditText) view.findViewById(R.id.wrPlanarRectSetSigmaET);
        conductivityET.setOnTouchListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrPlanarRectSetBTN:
                boolean empty= control.checkIsNull(wET,lET,hET,epsrET,tandeltaET,conductivityET);
                if (empty==false){
                    double maxValue [] = {1000,1000,1000,5000,100,1E9};
                    double minValue [] = {0,0,0,0,0,0};
                    String ValueName [] = {"w","l","h","Permitivita","Ztrátový činitel","Měrná vodivost"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,wET,lET,hET,epsrET,tandeltaET,conductivityET);
                    if (maxmin ==""){
                        parameters[0]= Double.parseDouble(wET.getText().toString())/1000;
                        parameters[1]=Double.parseDouble(lET.getText().toString())/1000;
                        parameters[2]=Double.parseDouble(hET.getText().toString())/1000;
                        parameters[3]=Double.parseDouble(epsrET.getText().toString());
                        parameters[4]=Double.parseDouble(tandeltaET.getText().toString());
                        parameters[5]=Double.parseDouble(conductivityET.getText().toString());
                        pref.saveArrayD(parameters,"wrPlanarRectSet",getActivity());
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
            case R.id.wrPlanarRectModeBTN:
                mod.SetOwnModes(inmodes,getActivity(),"setModePlanarRect",3);
                break;
        }
    }
}
