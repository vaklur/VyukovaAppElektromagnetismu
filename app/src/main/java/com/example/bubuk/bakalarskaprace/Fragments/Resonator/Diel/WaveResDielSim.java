package com.example.bubuk.semestralniprace.Fragments.Resonator.Diel;

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
import com.example.bubuk.semestralniprace.OtherClass.WaveresonatorsCalc;
import com.example.bubuk.semestralniprace.R;

public class WaveResDielSim extends Fragment implements View.OnClickListener {

    View view;
    Button setBTN;

    TextView paramsTV, modesTV;
    WaveResDielSet fragmentWRDielSet;

    WaveresonatorsCalc calculations = new WaveresonatorsCalc();

    SharePref pref = new SharePref();

    double[] parameters;

    double a;
    double b;
    double l;
    double epsr;
    double tandelta;

    int [] outmodes = new int [25];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_diel_sim,container,false);

        parameters=pref.loadArrayD("wrDielSet",getActivity());
        outmodes=pref.loadArrayI("setModeDiel",getActivity());

        a= parameters[0];
        b= parameters[1];
        l =parameters[2];
        epsr=parameters[3];
        tandelta=parameters[4];

        setBTN = (Button) view.findViewById(R.id.wrDielSimBTN);
        setBTN.setOnClickListener(this);

        fragmentWRDielSet = new WaveResDielSet();

        paramsTV = (TextView) view.findViewById(R.id.wrDielWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wrDielWaveguideModesTV);
        paramsTV.setText("Rozměry a="+a*1000+", b="+b*1000+" a l="+l*1000+" mm.\n"+
                            "Dielektrikum s εr="+epsr+".\n"+
                            "Ztrátový činitel dielektrika tanδ="+tandelta+".");

        Spinner modesspinner = (Spinner) view.findViewById(R.id.wrDielSimS);

        String[] modes = new String[]{"Rezonanční kmitočet pro dominantní vid",
                "Rezonanční kmitočty pro zvolené vidy"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,modes
        );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        modesspinner.setAdapter(spinnerArrayAdapter);

        modesspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    String modes = calculations.DielectricResonance101(a,b,l,epsr,tandelta);

                    modesTV.setText(modes);


                }
                if (position == 1) {
                    String ownmodes = calculations.OwnDielMode(outmodes,a,b,l,epsr,tandelta);

                    modesTV.setText(ownmodes);


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
            case R.id.wrDielSimBTN:
                replaceFragment(fragmentWRDielSet);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wrDielFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
