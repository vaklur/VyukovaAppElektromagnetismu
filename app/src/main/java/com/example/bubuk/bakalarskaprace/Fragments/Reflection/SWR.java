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

/* Fragment, than visualised standing wave ratio in transmission line*/
public class SWR extends Fragment {
    View view;
    LineChart linechart;



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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reflection_swr, container, false);

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

        Complex gama = new Complex (beta,alfa);                                 // propagation constant/
        Complex Z0 = new Complex (z0,0);                                       // impedance of line/
        Complex Zk = new Complex (zkre,zkim);                                       // load impedance/
        Complex tw1 = new Complex (-2,0);
        Complex twpop = gama.times(tw1);
        Complex rok;

        double i;

        rok = ((Zk.minus(Z0)).divides(Zk.plus(Z0)));                 //reflection factor on load/

        linechart = (LineChart) view.findViewById(R.id.reflectionpsvLCH);

        ArrayList<Entry> graf = new ArrayList<Entry>();


        for (i = 0; i < l * 100; i++) {
            Complex x1 = new Complex(i / 100, 0);
            double ro = (rok.times((twpop.times(x1)).exp())).abs();
            double PSV = (1+ro)/(1-ro);

            String sx = Double.toString(i / 100);
            float fx = Float.parseFloat(sx);
            String fs = Double.toString(PSV);
            float fpsv = Float.parseFloat(fs);
            graf.add(new Entry(fx, fpsv));
        }

        LineDataSet set2;

        set2 = new LineDataSet(graf, "PSV");

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set2);

        LineData data = new LineData(dataSets);

        /* Set axis x -------------------------------------------------------------*/
        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);

        /* Set axis y -------------------------------------------------------------*/
        YAxis yAxis = linechart.getAxisLeft();
        yAxis.setTextSize(10f);

        /* Set chart -------------------------------------------------------------*/
        Legend legend = linechart.getLegend();
        legend.setEnabled(false);
        set2.setColor(Color.BLACK);
        set2.setFillColor(Color.BLACK);
        set2.setLineWidth(1.8f);
        set2.setDrawCircles(false);
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
