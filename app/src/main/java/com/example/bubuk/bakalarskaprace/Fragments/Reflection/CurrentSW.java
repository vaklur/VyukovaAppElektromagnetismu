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

/* Fragment, than visualised current standing wave in transmission line*/
public class CurrentSW extends Fragment implements View.OnClickListener {

    View view;
    Button setmodulCSW,setargumentCSW;
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
    double cv = 299792458;

    double i;
    int c=0;
    double I;


    @Override
    public void onClick(View v) {


        switch ( v.getId()) {
            case R.id.modulsviBTN:
                c=0;

                break;
            case R.id.argumentsviBTN:
                c=1;

                break;
        }
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reflection_current_sw, container, false);

        set=pref.loadArrayD("reflectSet",getActivity());

        l=set[0];
        psi=set[1];
        double lambda0 = cv / set[2];                                                   // wave length in vacuum/
        double lambdav = psi * lambda0;
        alfa = (2 * Math.PI) / lambdav;
        beta=set[3]/8.686;
        z0=set[4];
        zkre=set[5];
        zkim=set[6];
        posd=set[7];
        val=set[8];

        setmodulCSW = (Button) view.findViewById(R.id.modulsviBTN);
        setmodulCSW.setOnClickListener(this);

        setargumentCSW = (Button) view.findViewById(R.id.argumentsviBTN);
        setargumentCSW.setOnClickListener(this);

        // Complex variables //
        Complex gama = new Complex (beta,alfa);                                 // propagation constant/
        Complex Z0 = new Complex (z0,0);                                       // impedance of line/
        Complex Zk = new Complex (zkre,zkim);                                       // load impedance/
        Complex twog = new Complex (-2*l,0);                                    //help variable -2l/
        Complex tw = new Complex (-l,0);
        Complex Ukp;
        Complex Ikz = new Complex(0,0);
        Complex Ikp = new Complex(0,0);
        Complex Ukz;
        Complex Ip;
        Complex Ipp;
        Complex Upp;
        Complex Up;
        Complex Ik;
        Complex Uk;
        Complex rok;
        Complex rop;

        rok = ((Zk.minus(Z0)).divides(Zk.plus(Z0)));                 //reflection_sim factor on load/
        rop = rok.times((twog.times(gama)).exp());                  //reflection_sim factor on enter/

        int pos = (int) posd;

        switch (pos) {
            case 1:
                Ip = new Complex(val, 0);
                Ipp = Ip.divides(rop.minusdoub(1));
                Ikp = Ipp.times((tw.times(gama)).exp());
                Ikz = Ikp.times(rok);
                break;
            case 0:
                Up = new Complex(val, 0);
                Upp = Up.divides(rop.plusdoub(1));
                Ipp = Upp.divides(Z0);
                Ikp = Ipp.times((tw.times(gama)).exp());
                Ikz = Ikp.times(rok);
                break;
            case 2:
                Uk = new Complex(val, 0);
                Ukp = Uk.divides(rok.plusdoub(1));
                Ukz = Uk.minus(Ukp);
                Ikp = Ukp.divides(Z0);
                Ikz = Ukz.divides(Z0);
                break;
            case 3:
                Ik = new Complex(val, 0);
                Ikp = Ik.divides(rok.minusdoub(1));
                Ikz = Ikp.minus(Ik);
                break;
        }

        linechart = (LineChart) view.findViewById(R.id.currentswLCH);

        ArrayList<Entry> graf = new ArrayList<Entry>();


        for (i = 0; i < l * 100; i++) {
            Complex x1 = new Complex(i / 100, 0);
            Complex Ip1=Ikp.times((gama.times(x1)).exp());
            Complex Iz1=Ikz.times((tw.times(gama.times(x1))).exp());
            Complex Ip2=Ip1.minus(Iz1);
            axisyps = (TextView)view.findViewById(R.id.currentswTV1);
            if (c==0){
                I = Ip2.abs();
                axisyps.setText("Velikost proudu [A]");
            }
            else {
                I = Ip2.phase();
                I=(I*180)/Math.PI;
                axisyps.setText("Argument proudu [°]");
            }

            String sx = Double.toString(i / 100);
            float fx = Float.parseFloat(sx);
            String si = Double.toString(I);
            float fi = Float.parseFloat(si);
            graf.add(new Entry(fx, fi));
        }

        LineDataSet set2;

        set2 = new LineDataSet(graf, "Stojaté vlnění proudu");

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set2);

        LineData data = new LineData(dataSets);

        /* Set axis x-------------------------------------------------------------*/
        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);

        /* Set axis y -------------------------------------------------------------*/
        YAxis yAxis = linechart.getAxisLeft();
        yAxis.setTextSize(10f);
        if (c==1){
            yAxis.setAxisMaximum(180);
            yAxis.setAxisMinimum(-180);
        }

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
