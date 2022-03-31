package com.example.bubuk.semestralniprace.Fragments.Resonator.Planar.Circle;

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


public class WaveResPlanarCircleSet extends Fragment implements View.OnClickListener,View.OnTouchListener {

    Button simulation,modes;
    View view;
    ImageView resplanarcirc;


    WaveresonatorsOwnModes mod = new WaveresonatorsOwnModes();



    EditText aET,hET,epsrET,tandeltaET,conductivityET;


    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] parameters = new double[5];
    int [] inmodes = new int [25];



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.wrPlanarCircSetAET:
                resplanarcirc.setImageResource(R.drawable.planarcircresonatora);
                break;
            case R.id.wrPlanarCircSetEpsrET:
                resplanarcirc.setImageResource(R.drawable.planarcircresonatorepsr);
                break;
            case R.id.wrPlanarCircSetHET:
                resplanarcirc.setImageResource(R.drawable.planarcircresonatorh);
                break;
            case R.id.wrPlanarCircSetTanDeltaET:
                resplanarcirc.setImageResource(R.drawable.planarcircresonator);
                break;
            case R.id.wrPlanarCircSetSigmaET:
                resplanarcirc.setImageResource(R.drawable.planarcircresonator);
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_planar_circle_set,container,false);
        parameters=pref.loadArrayD("wrPlanarCircSet",getActivity());
        inmodes=pref.loadArrayI("setModePlanarCircle",getActivity());

        initcontrol();

        aET.setText(Double.toString(parameters[0]*1000));
        hET.setText(Double.toString(parameters[1]*1000));
        epsrET.setText(Double.toString(parameters[2]));
        tandeltaET.setText(Double.toString(parameters[3]));
        conductivityET.setText(Double.toString(parameters[4]));

        return view;
    }

    public void initcontrol(){
        simulation = (Button) view.findViewById(R.id.wrPlanarCircSetBTN);
        simulation.setOnClickListener(this);
        modes = (Button) view.findViewById(R.id.wrPlanarCircModeBTN);
        modes.setOnClickListener(this);
        resplanarcirc=(ImageView) view.findViewById(R.id.wrPlanarCircSetIV);
        aET = (EditText) view.findViewById(R.id.wrPlanarCircSetAET);
        aET.setOnTouchListener(this);
        hET = (EditText) view.findViewById(R.id.wrPlanarCircSetHET);
        hET.setOnTouchListener(this);
        epsrET = (EditText) view.findViewById(R.id.wrPlanarCircSetEpsrET);
        epsrET.setOnTouchListener(this);
        tandeltaET = (EditText) view.findViewById(R.id.wrPlanarCircSetTanDeltaET);
        tandeltaET.setOnTouchListener(this);
        conductivityET = (EditText) view.findViewById(R.id.wrPlanarCircSetSigmaET);
        conductivityET.setOnTouchListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrPlanarCircSetBTN:
                boolean empty= control.checkIsNull(aET,hET,epsrET,tandeltaET,conductivityET);
                if (empty==false){
                    double maxValue [] = {1000,1000,5000,100,1E9};
                    double minValue [] = {0,0,0,0,0};
                    String ValueName [] = {"a","h","Permitivita","Ztrátový činitel","Měrná vodivost"};
                    String maxmin = control.checkIsMaxMin(maxValue,minValue,ValueName,aET,hET,epsrET,tandeltaET,conductivityET);
                    if (maxmin ==""){
                        parameters[0]= Double.parseDouble(aET.getText().toString())/1000;
                        parameters[1]=Double.parseDouble(hET.getText().toString())/1000;
                        parameters[2]=Double.parseDouble(epsrET.getText().toString());
                        parameters[3]=Double.parseDouble(tandeltaET.getText().toString());
                        parameters[4]=Double.parseDouble(conductivityET.getText().toString());
                        pref.saveArrayD(parameters,"wrPlanarCircSet",getActivity());
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
            case R.id.wrPlanarCircModeBTN:
                mod.SetOwnModes(inmodes,getActivity(),"setModePlanarCircle",4);
                break;
        }
    }
}
