package com.example.bubuk.semestralniprace.Fragments.Resonator.Coax;

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

public class WaveResCoaxSim extends Fragment implements View.OnClickListener {

    View view;
    Button setBTN;

    TextView paramsTV, modesTV;
    WaveResCoaxSet fragmentWRCoaxSet;

    WaveresonatorsCalc calculations = new WaveresonatorsCalc();
    SharePref pref = new SharePref();

    double[] x;

    double r0;
    double br0;
    double l;
    double epsr=1;
    double mir=1;
    double tandelta;
    double conduction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waveresonators_coax_sim,container,false);

        x=pref.loadArrayD("wrCoaxSet",getActivity());

        r0= x[0];
        br0= x[1];
        l =x[2];
        epsr=x[3];
        mir=x[4];
        tandelta=x[5];
        conduction = x[6];

        setBTN = (Button) view.findViewById(R.id.wrCoaxSimBTN);
        setBTN.setOnClickListener(this);

        fragmentWRCoaxSet = new WaveResCoaxSet();

        paramsTV = (TextView) view.findViewById(R.id.wrCoaxWaveguideParamsTV);
        modesTV = (TextView) view.findViewById(R.id.wrCoaxWaveguideModesTV);
        paramsTV.setText("Rezonátor s rozměry r0="+r0*1000+" a R0="+br0*1000+" mm.\n" +
                " Dielektrikum s parametry epsr=" + epsr + " a mir=" + mir + ".\n" +
                " Ztrátový činitel dielektrika tanδ="+tandelta+"\n"+
                " Měrná vodivost dielektrika "+conduction+" S/m.");


                    String modes = calculations.CoaxResonanceTEM(r0,br0,l,epsr,mir,tandelta,conduction);

                    modesTV.setText(modes);

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wrCoaxSimBTN:
                replaceFragment(fragmentWRCoaxSet);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.wrCoaxFL, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
