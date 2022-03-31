package com.example.bubuk.semestralniprace.Fragments.Waveguide.Coaxial;

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


public class WaveguideCoaxSim extends Fragment implements View.OnClickListener{

    View view;
    TextView paramsTV, modesTV;
    Button setBTN;


    SharePref pref = new SharePref();

    double[] x;
    double r0 ;
    double br0;
    double epsr;
    double mir;
    double frequence;

    WaveguideCalc calculations = new WaveguideCalc();

    WaveguideCoaxSet fragmentCoaxset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.waveguide_coaxial_sim, container, false);

        x=pref.loadArrayD("coaxwSet",getActivity());
        r0 =x[0];
        br0 =x[1];
        epsr =x[2];
        mir =x[3];
        frequence =x[4];


        setBTN = (Button) view.findViewById(R.id.wxSetBTN);
        setBTN.setOnClickListener(this);
        fragmentCoaxset = new WaveguideCoaxSet();
        paramsTV = (TextView) view.findViewById(R.id.wxWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wxWaveguideModesTV);
        paramsTV.setText("Vlnovod o poloměru vnitřního vodiče "+r0*1000+" mm.\nVlnovod o poloměru vnějšího vodiče "+br0*1000+" mm.\nDielektrikum s parametry epsr="+epsr+" a mir="+mir+".\nVlnovodem se šíří vlna o frekvenci "+frequence/1000000000+" Ghz.");

        String modes = calculations.CoaxMode(r0,br0,epsr,mir,frequence);
        modesTV.setText(modes);

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wxSetBTN:
                replaceFragment(fragmentCoaxset);
                break;
        }
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wgCoaxialFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
