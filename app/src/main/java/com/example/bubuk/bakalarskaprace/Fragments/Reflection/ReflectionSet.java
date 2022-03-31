package com.example.bubuk.semestralniprace.Fragments.Reflection;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.ControlFunction;
import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;


/* Fragment, than set transmission line parameters*/
public class ReflectionSet extends Fragment implements View.OnClickListener, View.OnTouchListener {

    View view;
    Spinner setspinner;
    Button setReflect;

    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    ImageView reflectImage;

    EditText impedanacez0, impedancezkre, impedancezkim, psiET, betaET, length, frequence, value;

    // Global variables/
    double f;                                                          // frequency/
    double[] set = new double[9]; // l,psi,f,beta,z0,zkRE,zkIM,pos,val/


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.z0reflectionET:
                reflectImage.setImageResource(R.drawable.reflectionz0);
                break;
            case R.id.zkREreflectionET:
                reflectImage.setImageResource(R.drawable.reflectionzk);
                break;
            case R.id.zkIMreflectionET:
                reflectImage.setImageResource(R.drawable.reflectionzk);
                break;
            case R.id.lenghtreflectionET:
                reflectImage.setImageResource(R.drawable.reflectionl);
                break;
            case R.id.betareflectionET:
                reflectImage.setImageResource(R.drawable.reflection);
                break;
            case R.id.psireflectionET:
                reflectImage.setImageResource(R.drawable.reflection);
                break;
            case R.id.setDipolFreqET:
                reflectImage.setImageResource(R.drawable.reflection);
                break;
            case R.id.valuereflectionET:
                double pos = setspinner.getLastVisiblePosition();
                if (pos == 0) reflectImage.setImageResource(R.drawable.reflectionup);
                if (pos == 1) reflectImage.setImageResource(R.drawable.reflectionip);
                if (pos == 2) reflectImage.setImageResource(R.drawable.reflectionuk);
                if (pos == 3) reflectImage.setImageResource(R.drawable.reflectionik);
                break;
        }
        return false;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reflectionsetBTN:
                boolean empty = control.checkIsNull(impedanacez0, impedancezkre, impedancezkim, psiET, betaET, length, frequence,value);
                if (empty == false) {
                    double maxValue[] = {1000, 1000, 5000, 1, 1000, 1000, 100000000,1000};
                    double minValue[] = {0, -1, -1000, 0, -1, 0, 0,-1000};
                    String ValueName[] = {"Z0", "Zk realná", "Zk imaginární", "Činitel zkrácení", "Měrný útlum","Délka vedení","Frekvence","Naměřená veličina"};
                    String maxmin = control.checkIsMaxMin(maxValue, minValue, ValueName, impedanacez0, impedancezkre, impedancezkim, psiET, betaET, length, frequence);
                    if (maxmin == "") {
                        double pos = setspinner.getLastVisiblePosition();

                        set[0] = Double.parseDouble(length.getText().toString());
                        set[1] = Double.parseDouble(psiET.getText().toString());
                        f = (Double.parseDouble(frequence.getText().toString())) * 1000000;
                        set[2] = f;
                        set[3] = Double.parseDouble(betaET.getText().toString());
                        set[4] = Double.parseDouble(impedanacez0.getText().toString());
                        set[5] = Double.parseDouble(impedancezkre.getText().toString());
                        set[6] = Double.parseDouble(impedancezkim.getText().toString());
                        set[7] = pos;
                        set[8] = Double.parseDouble(value.getText().toString());
                        pref.saveArrayD(set,"reflectSet",getActivity());
                        Toast toast = Toast.makeText(getActivity(), "Nastavili jste nové parametry", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getActivity(), maxmin, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else {
                    Toast toast = Toast.makeText(getActivity(), "Nastavte všechny parametry", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reflection_set, container, false);

        set=pref.loadArrayD("reflectSet",getActivity());
        reflectImage = (ImageView) view.findViewById(R.id.reflectionIV);

        /*inicialize widgets*/
        initcontrol();

        /*Spinner*/

        setspinner = (Spinner) view.findViewById(R.id.reflectionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerreflect, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setspinner.setAdapter(adapter);


        // set[]
        // l,psi,alfa,beta,z0,zkRE,zkIM,pos,val/

        length.setText(Double.toString(set[0]));
        psiET.setText(Double.toString(set[1]));
        frequence.setText(Double.toString(set[2]/1000000));
        betaET.setText(Double.toString(set[3]));
        impedanacez0.setText(Double.toString(set[4]));
        impedancezkre.setText(Double.toString(set[5]));
        impedancezkim.setText(Double.toString(set[6]));
        setspinner.setSelection((int)set[7]);
        value.setText(Double.toString(set[8]));



        return view;

    }

    public void initcontrol(){
        setReflect = (Button) view.findViewById(R.id.reflectionsetBTN);
        setReflect.setOnClickListener(this);
        impedanacez0 = (EditText) view.findViewById(R.id.z0reflectionET);
        impedanacez0.setOnTouchListener(this);
        impedancezkre = (EditText) view.findViewById(R.id.zkREreflectionET);
        impedancezkre.setOnTouchListener(this);
        impedancezkim = (EditText) view.findViewById(R.id.zkIMreflectionET);
        impedancezkim.setOnTouchListener(this);
        psiET = (EditText) view.findViewById(R.id.psireflectionET);
        psiET.setOnTouchListener(this);
        betaET = (EditText) view.findViewById(R.id.betareflectionET);
        betaET.setOnTouchListener(this);
        frequence = (EditText) view.findViewById(R.id.frequencereflectionET);
        frequence.setOnTouchListener(this);
        length = (EditText) view.findViewById(R.id.lenghtreflectionET);
        length.setOnTouchListener(this);
        value = (EditText) view.findViewById(R.id.valuereflectionET);
        value.setOnTouchListener(this);
    }
}
