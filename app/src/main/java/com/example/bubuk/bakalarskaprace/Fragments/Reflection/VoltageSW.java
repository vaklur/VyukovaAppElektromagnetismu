package com.example.bubuk.semestralniprace.Fragments.Reflection;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.OtherClass.Complex;
import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/* Fragment, than visualised voltage standing wave in transmission line*/
public class VoltageSW extends Fragment implements View.OnClickListener {
    View view;
    Button setmodulVSW,setargumentVSW;
    LineChart linechart;
    TextView axisyps;

    SharePref pref = new SharePref();

    double [] set = new double [9];

    double posd;
    double zkre;
    double zkim;
    double l;                                                             // length of line/
    double psi;                                                         // shortening factor /
    double beta;                                                     // specific attenuation/
    double alfa;
    double z0;
    double val;
    double f;
    double cv = 299792458;

    int c=0;

    double i;
    double Up22;




    @Override
    public void onClick(View v) {

        switch ( v.getId()) {
            case R.id.modulsvvBTN:
                c=0;
                break;
            case R.id.argumentsvvBTN:
                c=1;
                break;
        }
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reflection_vol_sw, container, false);

        set=pref.loadArrayD("reflectSet",getActivity());

        l=set[0];
        psi=set[1];
        f=set[2];
        double lambda0 = cv / f;                                                   // wave length in vacuum/
        double lambdav = psi * lambda0;
        alfa = (2 * Math.PI) / lambdav;
        beta=set[3]/8.686;
        z0=set[4];
        zkre=set[5];
        zkim=set[6];
        posd=set[7];
        val=set[8];

        setmodulVSW = (Button) view.findViewById(R.id.modulsvvBTN);
        setmodulVSW.setOnClickListener(this);

        setargumentVSW = (Button) view.findViewById(R.id.argumentsvvBTN);
        setargumentVSW.setOnClickListener(this);

        // Complex variables//
        Complex gama = new Complex (beta,alfa);                                 // propagation constant/
        Complex Z0 = new Complex (z0,0);                                       // impedance of line/
        Complex Zk = new Complex (zkre,zkim);                                       // load impedance/
        Complex twog = new Complex (-2*l,0);                                    //help variable -2l/
        Complex tw = new Complex (-l,0);
        Complex Ukp = new Complex (0,0);
        Complex Ikz;
        Complex Ikp;
        Complex Ukz = new Complex (0,0);
        Complex rok;
        Complex rop;
        Complex Ip;
        Complex Ipp;
        Complex Upp;
        Complex Up;
        Complex Ik;
        Complex Uk;

        rok = ((Zk.minus(Z0)).divides(Zk.plus(Z0)));                 //reflection_sim factor on load/
        rop = rok.times((twog.times(gama)).exp());                  //reflection_sim factor on enter/

        int pos = (int) posd;

        switch (pos) {
            case 1:
                Ip = new Complex(val, 0);
                Ipp = Ip.divides(rop.minusdoub(1));
                Ikp = Ipp.times((tw.times(gama)).exp());
                Ikz = Ikp.times(rok);
                Ukp = Z0.times(Ikp);
                Ukz = Z0.times(Ikz);
                break;
            case 0:
                Up = new Complex(val, 0);
                Upp = Up.divides(rop.plusdoub(1));
                Ipp = Upp.divides(Z0);
                Ikp = Ipp.times((tw.times(gama)).exp());
                Ikz = Ikp.times(rok);
                Ukp = Z0.times(Ikp);
                Ukz = Z0.times(Ikz);
                break;
            case 2:
                Uk = new Complex(val, 0);
                Ukp = Uk.divides(rok.plusdoub(1));
                Ukz = Uk.minus(Ukp);
                break;
            case 3:
                Ik = new Complex(val, 0);
                Ikp = Ik.divides(rok.minusdoub(1));
                Ikz = Ikp.minus(Ik);
                Ukp = Z0.times(Ikp);
                Ukz = Z0.times(Ikz);
                break;
        }

        linechart = (LineChart) view.findViewById(R.id.voltageswLCH);

        ArrayList<Entry> graf = new ArrayList<Entry>();


        for (i = 0; i < l * 100; i++) {
            Complex x1 = new Complex(i / 100, 0);
            Complex Up1=Ukp.times((gama.times(x1)).exp());
            Complex Uz1=Ukz.times((tw.times(gama.times(x1))).exp());
            Complex Up2=Up1.plus(Uz1);
            axisyps = (TextView)view.findViewById(R.id.voltageswTV1);
            if (c==0){
                Up22 = Up2.abs();
                axisyps.setText("Velikost napětí [V]");

            }
            else {
                Up22 = Up2.phase();
                Up22=(Up22*180)/Math.PI;
                axisyps.setText("Argument napětí [°]");
            }

            String sx = Double.toString(i / 100);
            float fx = Float.parseFloat(sx);
            String su = Double.toString(Up22);
            float fu = Float.parseFloat(su);
            graf.add(new Entry(fx,fu));
        }

        LineDataSet set1;

        set1 = new LineDataSet(graf, "Stojaté vlnění napětí");

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        /* Set axis x -------------------------------------------------------------*/
        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);

        /* Set axis y-------------------------------------------------------------*/
        YAxis yAxis = linechart.getAxisLeft();
        yAxis.setTextSize(10f);
        if (c==1){
            yAxis.setAxisMaximum(180);
            yAxis.setAxisMinimum(-180);
        }

        /* Set chart-------------------------------------------------------------*/
        Legend legend = linechart.getLegend();
        legend.setEnabled(false);
        set1.setColor(Color.BLACK);
        set1.setFillColor(Color.BLACK);
        set1.setLineWidth(1.8f);
        set1.setDrawCircles(false);
        linechart.getDescription().setText(".");
        linechart.getAxisRight().setEnabled(true);
        linechart.setPinchZoom(false);
        linechart.setData(data);
        data.setValueTextSize(9f);
        linechart.notifyDataSetChanged();
        linechart.invalidate();

        return view;
    }
}
