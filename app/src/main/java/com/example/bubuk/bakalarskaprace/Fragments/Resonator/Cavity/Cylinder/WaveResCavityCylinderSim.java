package com.example.bubuk.semestralniprace.Fragments.Resonator.Cavity.Cylinder;

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


public class WaveResCavityCylinderSim extends Fragment implements View.OnClickListener {

    View view;
    Button setBTN;

    TextView paramsTV, modesTV;
    WaveResCavityCylinderSet fragmentWRCCylinderSet;

    WaveresonatorsCalc calculations = new WaveresonatorsCalc();
    SharePref pref = new SharePref();

    double[] x;

    double a;
    double l;
    double epsr;
    double mir;
    double tandelta;
    double conduction;

    int [] outmodes = new int [25];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_cavity_cylinder_sim,container,false);

        x=pref.loadArrayD("wrCavCylSet",getActivity());
        outmodes=pref.loadArrayI("setModeCavCyl",getActivity());

        a= x[0];
        l= x[1];
        epsr=x[2];
        mir=x[3];
        tandelta=x[4];
        conduction = x[5];

        setBTN = (Button) view.findViewById(R.id.wrCavCylSimBTN);
        setBTN.setOnClickListener(this);

        fragmentWRCCylinderSet = new WaveResCavityCylinderSet();

        paramsTV = (TextView) view.findViewById(R.id.wrCavCylWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wrCavCylWaveguideModesTV);
        paramsTV.setText("Rezonátor s rozměry a="+a*1000+" a l="+l*1000+" mm.\n" +
                " Dielektrikum s parametry epsr=" + epsr + " a mir=" + mir + ".\n" +
                " Ztrátový činitel dielektrika tanδ="+tandelta+"\n"+
                " Měrná vodivost dielektrika "+conduction+" S/m.");

        Spinner modesspinner = (Spinner) view.findViewById(R.id.wrCavCylSimS);

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
                    String modes = calculations.CavCylResonance011(a,l,epsr,mir,tandelta,conduction);

                    modesTV.setText(modes);


                }
                if (position == 1) {
                    String ownmodes = calculations.OwnCavCylMode(outmodes,a,l,epsr,mir,tandelta,conduction);

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
            case R.id.wrCavCylSimBTN:
                replaceFragment(fragmentWRCCylinderSet);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wrCavCylFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
