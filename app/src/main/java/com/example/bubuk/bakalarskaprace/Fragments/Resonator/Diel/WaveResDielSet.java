package com.example.bubuk.semestralniprace.Fragments.Resonator.Diel;

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

public class WaveResDielSet extends Fragment implements View.OnClickListener,View.OnTouchListener {

    Button simulation,modes;
    View view;
    ImageView dielresonator;


    WaveresonatorsOwnModes mod = new WaveresonatorsOwnModes();



    EditText aET,bET,lET,epsrET,tandeltaET;


    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] parameters = new double[5];
    int [] inmodes = new int [25];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wrDielSetAET3:
                dielresonator.setImageResource(R.drawable.dielresonatora);
                break;
            case R.id.wrDielSetBET3:
                dielresonator.setImageResource(R.drawable.dielresonatorb);
                break;
            case R.id.wrDielSetEpsrET3:
                dielresonator.setImageResource(R.drawable.dielresonatorepsr);
                break;
            case R.id.wrDielSetLET3:
                dielresonator.setImageResource(R.drawable.dielresonatorl);
                break;
            case R.id.wrDielSetTanDeltaET3:
                dielresonator.setImageResource(R.drawable.dielresonator);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_diel_set,container,false);
        parameters=pref.loadArrayD("wrDielSet",getActivity());
        inmodes=pref.loadArrayI("setModeDiel",getActivity());

        initcontrol();

        aET.setText(Double.toString(parameters[0]*1000));
        bET.setText(Double.toString(parameters[1]*1000));
        lET.setText(Double.toString(parameters[2]*1000));
        epsrET.setText(Double.toString(parameters[3]));
        tandeltaET.setText(Double.toString(parameters[4]));

        return view;
    }


    public void initcontrol(){
        simulation = (Button) view.findViewById(R.id.wrDielSetBTN);
        simulation.setOnClickListener(this);
        modes = (Button) view.findViewById(R.id.wrDielModeBTN);
        modes.setOnClickListener(this);
        dielresonator=(ImageView) view.findViewById(R.id.wrDielSetIV);
        aET = (EditText) view.findViewById(R.id.wrDielSetAET3);
        aET.setOnTouchListener(this);
        bET = (EditText) view.findViewById(R.id.wrDielSetBET3);
        bET.setOnTouchListener(this);
        lET = (EditText) view.findViewById(R.id.wrDielSetLET3);
        lET.setOnTouchListener(this);
        epsrET = (EditText) view.findViewById(R.id.wrDielSetEpsrET3);
        epsrET.setOnTouchListener(this);
        tandeltaET = (EditText) view.findViewById(R.id.wrDielSetTanDeltaET3);
        tandeltaET.setOnTouchListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrDielSetBTN:
                boolean empty= control.checkIsNull(aET,bET,lET,epsrET,tandeltaET);
                if (empty==false){
                    double maxValue [] = {1000,1000,1000,5000,100};
                    double minValue [] = {0,0,0,0,0};
                    String ValueName [] = {"a","b","l","Permitivita","Ztrátový činitel"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,aET,bET,lET,epsrET,tandeltaET);
                    if (maxmin ==""){
                        parameters[0]= Double.parseDouble(aET.getText().toString())/1000;
                        parameters[1]=Double.parseDouble(bET.getText().toString())/1000;
                        parameters[2]=Double.parseDouble(lET.getText().toString())/1000;
                        parameters[3]=Double.parseDouble(epsrET.getText().toString());
                        parameters[4]=Double.parseDouble(tandeltaET.getText().toString());
                        pref.saveArrayD(parameters,"wrDielSet",getActivity());
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
            case R.id.wrDielModeBTN:
                mod.SetOwnModes(inmodes,getActivity(),"setModeDiel",5);
                break;
        }
    }
}
