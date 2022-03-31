package com.example.bubuk.semestralniprace.Fragments.Dipol;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/* fragment, than visualised Dipole radiation in H and Cartesian coordinate system*/
public class DipolHKartez extends Fragment {

    SharePref pref = new SharePref();

    /*global variables------------------------------------*/
    View view;
    double f;
    double l;
    double h;
    double reflenab;

    double[] set = new double[4];

    /*Create fragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dipol_h_kartez, container, false);
        set=pref.loadArrayD("dipolSet",getActivity());
        f=set[0];
        l=set[1];
        h=set[2];
        reflenab=set[3];
        /*local variables------------------------------------*/
        double z;
        double c=3e8;
        double pi=Math.PI;
        double k=(2*pi*f)/c;
        double [] FH = new double[181];
        int i;

        LineChart linechart = (LineChart) view.findViewById(R.id.HKartezLCH);
        ArrayList<Entry> graf = new ArrayList<Entry>();

        /*calculation radiating function -------------------------*/
        i=0;
        for (double th =-90 ; th < 91; th++) {
            double thr=Math.toRadians(th);
            double spy=Math.sin(thr)*Math.sin(0) ;
            double spz=Math.cos(thr);
            double dn=Math.sqrt(1-(spy*spy));
            if (reflenab==1){
                z=(2*Math.sin(k*h*spz));
            }
            else{
                z=1;
            }
            FH [i]=Math.abs((z*(Math.cos(k*l*spy)-Math.cos(k*l)))/dn);
            i++;
        }

        /*norm radiating function --------------------------------------------*/
        double maxFH=0;
        for (i = 0; i < 180; i++) {
            if (FH[i] > maxFH) {
                maxFH = FH[i];
            }
        }

        /*plot linear chart ----------------------------------------------*/
        i=0;
        for (double th =-90 ; th < 91; th++) {
            String sth = Double.toString(th);
            float x = Float.parseFloat(sth);
            FH[i]=FH[i]/maxFH;
            String sfh = Double.toString(FH[i]);
            float y = Float.parseFloat(sfh);
            graf.add(new Entry (x,y));
            i++;
        }

        LineDataSet set1;

        set1 = new LineDataSet(graf,  "Dipól o délce "+l+" m na kterém se šíří vlna o frekvenci "+f+" Hz");

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        /*Set axis x -------------------------------------------------------------*/
        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);

        /*Set axis y -------------------------------------------------------------*/
        YAxis yAxis = linechart.getAxisLeft();
        yAxis.setTextSize(10f);
        yAxis.setAxisMinimum(0);

        /*Set chart -------------------------------------------------------------*/
        set1.setColor(Color.BLACK);
        set1.setFillColor(Color.BLACK);
        set1.setLineWidth(1.8f);
        set1.setDrawCircles(false);
        linechart.getAxisRight().setEnabled(false);
        linechart.getDescription().setText(".");
        linechart.setPinchZoom(false);
        linechart.setData(data);
        data.setValueTextSize(9f);
        linechart.notifyDataSetChanged();
        linechart.invalidate();
        return view;
    }
}