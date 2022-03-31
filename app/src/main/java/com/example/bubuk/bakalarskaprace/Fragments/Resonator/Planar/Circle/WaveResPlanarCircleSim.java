package com.example.bubuk.semestralniprace.Fragments.Resonator.Planar.Circle;

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


public class WaveResPlanarCircleSim extends Fragment implements View.OnClickListener {

    View view;
    Button setBTN;

    TextView paramsTV, modesTV;
    WaveResPlanarCircleSet fragmentWRPCircleSet;

    WaveresonatorsCalc calculations = new WaveresonatorsCalc();

    SharePref pref = new SharePref();

    double[] x;

    double a;
    double h;
    double epsr;
    double tandelta;
    double conduction;

    int [] outmodes = new int [25];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_planar_circle_sim,container,false);

        x=pref.loadArrayD("wrPlanarCircSet",getActivity());
        outmodes=pref.loadArrayI("setModePlanarCircle",getActivity());

        a= x[0];
        h =x[1];
        epsr=x[2];
        tandelta=x[3];
        conduction = x[4];

        setBTN = (Button) view.findViewById(R.id.wrPlanarCircleSimBTN);
        setBTN.setOnClickListener(this);

        fragmentWRPCircleSet = new WaveResPlanarCircleSet();

        paramsTV = (TextView) view.findViewById(R.id.wrPlanarCircleWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wrPlanarCircleWaveguideModesTV);
        paramsTV.setText("Rezonátor s rozměry a="+a*1000+" a h="+h*1000+" mm.\n" +
                " Dielektrikum s parametry epsr=" + epsr + ".\n" +
                " Ztrátový činitel dielektrika tanδ="+tandelta+"\n"+
                " Měrná vodivost dielektrika "+conduction+" S/m.");

        Spinner modesspinner = (Spinner) view.findViewById(R.id.wrPlanarCircleSimS);

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
                    String modes = calculations.PlanarCircleResonance001(a,h,epsr,tandelta,conduction);

                    modesTV.setText(modes);


                }
                if (position == 1) {
                    String ownmodes = calculations.OwnPlanarCircMode(outmodes,a,h,epsr,tandelta,conduction);

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
            case R.id.wrPlanarCircleSimBTN:
                replaceFragment(fragmentWRPCircleSet);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wrPlanCircFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
