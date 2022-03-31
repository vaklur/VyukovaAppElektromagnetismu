package com.example.bubuk.semestralniprace.Fragments.Waveguide.Rectangular;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
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

import static android.content.Context.MODE_PRIVATE;


public class WaveguideRectSim extends Fragment implements View.OnClickListener{

    View view;
    TextView paramsTV, modesTV;
    Button setBTN;


    SharePref pref = new SharePref();

    int [] outmodes = new int [20];

    double[] x;
    double a ;
    double b;
    double epsr;
    double mir;
    double frequence;

    WaveguideCalc calculations = new WaveguideCalc();

    WaveguideRectSet fragmentRset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.waveguide_rectangular_sim, container, false);

        x=pref.loadArrayD("rwSet",getActivity());
        outmodes=pref.loadArrayI("setModeRect",getActivity());
        a =x[0];
        b =x[1];
        epsr =x[2];
        mir =x[3];
        frequence =x[4];


        setBTN = (Button) view.findViewById(R.id.wrSetBTN);
        setBTN.setOnClickListener(this);
        fragmentRset = new WaveguideRectSet();
        paramsTV = (TextView) view.findViewById(R.id.wrWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wrWaveguideModesTV);
        paramsTV.setText("Vlnovod od délce  "+a*1000+"x"+b*1000+" mm.\n" +
                "Dielektrikum s parametry epsr="+epsr+" a mir="+mir+".\n" +
                "Vlnovodem se šíří vlna o frekvenci "+frequence/1000000000+" Ghz.");

        Spinner modesspinner = (Spinner) view.findViewById(R.id.wrSimS);

        String[] modes = new String[]{"Dominantní vid a vyšší vidy",
                "Chování vlnovodu pro zvolenou frekvenci vlny",
                "Zvolené vidy"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,modes
        );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        modesspinner.setAdapter(spinnerArrayAdapter);

        modesspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    String modes = calculations.RectMode(a, b, epsr, mir);

                    modesTV.setText(Html.fromHtml(modes));
                }
                if (position == 1) {
                    String modes = calculations.RectModeSetFreq(a, b, epsr, mir, frequence);
                    modesTV.setText(modes);


                }
                if (position == 2) {
                    String modes = calculations.OwnRectMode(outmodes,a, b, epsr, mir);
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
            case R.id.wrSetBTN:
                replaceFragment(fragmentRset);
                break;
        }
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wgRectangularFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




}

