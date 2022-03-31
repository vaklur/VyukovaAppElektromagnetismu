package com.example.bubuk.semestralniprace.Fragments.Resonator.Cavity.Cuboid;

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



public class WaveResCavityCuboidSim extends Fragment implements View.OnClickListener {

    View view;
    Button setBTN;

    TextView paramsTV, modesTV;
    WaveResCavityCuboidSet fragmentWRCCuboidSet;

    WaveresonatorsCalc calculations = new WaveresonatorsCalc();
    SharePref pref = new SharePref();

    int [] outmodes = new int [25];

    double[] x;

    double a;
    double b;
    double l;
    double epsr;
    double mir;
    double tandelta;
    double conduction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_cavity_cuboid_sim,container,false);

        x=pref.loadArrayD("wrCavCubSet",getActivity());
        outmodes=pref.loadArrayI("setModeCavCub",getActivity());

        a= x[0];
        b =x[1];
        l= x[2];
        epsr=x[3];
        mir=x[4];
        tandelta=x[5];
        conduction = x[6];

        setBTN = (Button) view.findViewById(R.id.wrCavCubSimBTN);
        setBTN.setOnClickListener(this);

        fragmentWRCCuboidSet = new WaveResCavityCuboidSet();

        paramsTV = (TextView) view.findViewById(R.id.wrCavCubWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wrCavCubWaveguideModesTV);
        paramsTV.setText("Rezonátor s rozměry "+a*1000+"x"+b*1000+"x"+l*1000+" mm.\n" +
                " Dielektrikum s parametry epsr=" + epsr + " a mir=" + mir + ".\n" +
                " Ztrátový činitel dielektrika tanδ="+tandelta+"\n"+
                " Měrná vodivost dielektrika "+conduction+" S/m.");



        Spinner modesspinner = (Spinner) view.findViewById(R.id.wrCavCubSimS);

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
                    String modes = calculations.CavCubResonance101(a,b,l,epsr,mir,tandelta,conduction);

                    modesTV.setText(modes);


                }
                if (position == 1) {
                    String ownmodes = calculations.OwnCavCubMode(outmodes,a,b,l,epsr,mir,tandelta,conduction);

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
            case R.id.wrCavCubSimBTN:
                replaceFragment(fragmentWRCCuboidSet);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wrCavCubFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
