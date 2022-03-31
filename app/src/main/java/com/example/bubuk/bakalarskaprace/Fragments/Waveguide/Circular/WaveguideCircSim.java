package com.example.bubuk.semestralniprace.Fragments.Waveguide.Circular;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.OtherClass.WaveguideCalc;
import com.example.bubuk.semestralniprace.R;


public class WaveguideCircSim extends Fragment implements View.OnClickListener {

    View view;
    Button setBTN;

    TextView paramsTV, modesTV;
    WaveguideCircSet fragmentCset;

    SharePref pref = new SharePref();

    WaveguideCalc calculations = new WaveguideCalc();

    int [] outmodes = new int [20];
    double[] x;
    double a ;
    double epsr;
    double mir;
    double frequence;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.waveguide_circular_sim, container, false);


        x=pref.loadArrayD("cwSet",getActivity());
        outmodes=pref.loadArrayI("setModeCirc",getActivity());
        a =x[0];
        epsr =x[1];
        mir =x[2];
        frequence =x[3];


        setBTN = (Button) view.findViewById(R.id.wcSetBTN);
        setBTN.setOnClickListener(this);

        fragmentCset = new WaveguideCircSet();

        paramsTV = (TextView) view.findViewById(R.id.wcWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wcWaveguideModesTV);
        paramsTV.setText("Vlnovod od poloměru "+a*1000+" mm.\nDielektrikum s parametry epsr="+epsr+" a mir="+mir+".\nVlnovodem se šíří vlna o frekvenci "+frequence/1000000000+" Ghz.");

        Spinner modesspinner = (Spinner) view.findViewById(R.id.wcSimS);

        String[] modes = new String[]{"Dominantní vid a vyšší vidy",
                "Chování vlnovodu pro zvolenou frekvenci vlny",
                "Vlastní vidy"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,modes
        );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        modesspinner.setAdapter(spinnerArrayAdapter);

        modesspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    String modes = calculations.CircMode(a,epsr,mir);

                    modesTV.setText(modes);

                    
                }
                if (position == 1) {
                    String modes = calculations.CircModeSetFreq(a,epsr,mir,frequence);

                    modesTV.setText(modes);


                }
                if (position == 2) {
                    String modes = calculations.OwnCircMode(outmodes,a,epsr,mir);
                    modesTV.setText(modes);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
    return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wcSetBTN:
                replaceFragment(fragmentCset);
                break;
        }
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wgCircularFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
