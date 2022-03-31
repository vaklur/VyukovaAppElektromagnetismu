package com.example.bubuk.semestralniprace.Fragments.Resonator.Planar.Rectangular;

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


public class WaveResPlanarRectangularSim extends Fragment implements View.OnClickListener {

    View view;
    Button setBTN;

    TextView paramsTV, modesTV;
    WaveResPlanarRectangularSet fragmentWRPRectSet;

    WaveresonatorsCalc calculations = new WaveresonatorsCalc();
    SharePref pref = new SharePref();

    double[] x;

    double w;
    double h;
    double l;
    double epsr;
    double tandelta;
    double conduction;

    int [] outmodes = new int [25];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_planar_rectangular_sim,container,false);

        x=pref.loadArrayD("wrPlanarRectSet",getActivity());
        outmodes=pref.loadArrayI("setModePlanarRect",getActivity());

        w= x[0];
        l= x[1];
        h =x[2];
        epsr=x[3];
        tandelta=x[4];
        conduction = x[5];

        setBTN = (Button) view.findViewById(R.id.wrPlanarRectSimBTN);
        setBTN.setOnClickListener(this);

        fragmentWRPRectSet = new WaveResPlanarRectangularSet();

        paramsTV = (TextView) view.findViewById(R.id.wrPlanarRectWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wrPlanarRectWaveguideModesTV);
        paramsTV.setText("Rezonátor s rozměry w="+w*1000+", h="+h*1000+" a l="+h*1000+" mm.\n" +
                " Dielektrikum s parametry epsr=" + epsr + ".\n" +
                " Ztrátový činitel dielektrika tanδ="+tandelta+"\n"+
                " Měrná vodivost dielektrika "+conduction+" S/m.");

        Spinner modesspinner = (Spinner) view.findViewById(R.id.wrPlanarRectSimS);

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
                    String modes = calculations.PlanarRectResonance100(w,l,h,epsr,tandelta,conduction);

                    modesTV.setText(modes);


                }
                if (position == 1) {
                    String ownmodes = calculations.OwnPlanarRectMode(outmodes,w,l,h,epsr,tandelta,conduction);

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
            case R.id.wrPlanarRectSimBTN:
                replaceFragment(fragmentWRPRectSet);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wrPlanRectFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
