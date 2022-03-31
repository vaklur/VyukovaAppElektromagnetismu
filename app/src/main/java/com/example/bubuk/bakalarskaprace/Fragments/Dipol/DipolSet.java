package com.example.bubuk.semestralniprace.Fragments.Dipol;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.ControlFunction;
import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;

/* Fragment, that set dipole parameters*/
public class DipolSet extends Fragment implements View.OnTouchListener,View.OnClickListener {

    /* global variables*/
    TextView reflectortext;
    View view;
    Button setDipol;
    EditText frequence,lenght,height;
    CheckBox reflector;
    ImageView dipoleimg;

    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    double[] set = new double[4];

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.setDipolFreqET:
                if(set[3]==1) dipoleimg.setImageResource(R.drawable.dipolref);
                else dipoleimg.setImageResource(R.drawable.dipol);
                break;
            case R.id.setDipolLET:
                if(set[3]==1) dipoleimg.setImageResource(R.drawable.dipolrefl);
                else dipoleimg.setImageResource(R.drawable.dipoll);
                break;
            case R.id.setDipolHET:
                dipoleimg.setImageResource(R.drawable.dipolrefh);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.setDipolBTN:
                boolean empty= control.checkIsNull(frequence,lenght,height);
                if (empty==false){
                    double maxValue [] = {1000,100,100};
                    double minValue [] = {1,1e-6,1e-6};
                    String ValueName [] = {"Frekvence","Délka","Výška"};
                    String max = control.checkIsMaxMin(maxValue,minValue,ValueName,frequence,lenght,height);
                    if (max ==""){
                        set[1]=Double.parseDouble(lenght.getText().toString());
                        set[0]=Double.parseDouble(frequence.getText().toString())*1000000;
                        set[2]=Double.parseDouble(height.getText().toString());

                        pref.saveArrayD(set,"dipolSet",getActivity());
                        Toast toast=Toast.makeText(getActivity(),"Nastavili jste nové parametry",Toast.LENGTH_SHORT);
                        toast.show();}
                    else {Toast toast=Toast.makeText(getActivity(),max,Toast.LENGTH_SHORT);
                        toast.show();}
                }
                else {
                    Toast toast=Toast.makeText(getActivity(),"Nastavte všechny parametry",Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

            case R.id.setDipolReflectorCHB:
                if (reflector.isChecked()){
                    dipoleimg.setImageResource(R.drawable.dipolref);
                    set[3]=1;
                    height.setVisibility(View.VISIBLE);
                    reflectortext.setVisibility(View.VISIBLE);
                    reflector.setChecked(true);
                }
                else{
                    dipoleimg.setImageResource(R.drawable.dipol);
                    set[3]=0;
                    height.setVisibility(View.GONE);
                    reflectortext.setVisibility(View.GONE);
                }
                break;
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dipol_set, container, false);
        set=pref.loadArrayD("dipolSet",getActivity());

        initcontrol();

        frequence.setText(Double.toString(set[0]/1000000));
        lenght.setText(Double.toString(set[1]));
        height.setText(Double.toString(set[2]));

        if(set[3]==1){
            height.setVisibility(View.VISIBLE);
            reflectortext.setVisibility(View.VISIBLE);
            reflector.setChecked(true);
        }
        else {
            height.setVisibility(View.GONE);
            reflectortext.setVisibility(View.GONE);
        }

        return view;
    }

    /* initialize widgets*/
    public void initcontrol(){
        dipoleimg = (ImageView) view.findViewById(R.id.setDipolIV) ;
        frequence = (EditText) view.findViewById(R.id.setDipolFreqET);
        lenght = (EditText) view.findViewById(R.id.setDipolLET);
        height = (EditText) view.findViewById(R.id.setDipolHET);
        reflector = (CheckBox) view.findViewById(R.id.setDipolReflectorCHB);
        reflectortext = (TextView) view.findViewById(R.id.setDipolTV4);
        setDipol = (Button) view.findViewById(R.id.setDipolBTN);
        frequence.setOnTouchListener(this);
        lenght.setOnTouchListener(this);
        height.setOnTouchListener(this);
        reflector.setOnClickListener(this);
        setDipol.setOnClickListener(this);

    }
}
