package com.example.bubuk.semestralniprace.Fragments.Dipol;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

/* fragment, than visualised Dipole radiation in H and Polar coordinate system*/
public class DipolHPolar extends Fragment  {

    SharePref pref = new SharePref();

    /*global variables------------------------------------*/
    View view;
    double f;
    double l;
    double h;
    double reflenab;

    double[] set = new double[4];

    /* Create fragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dipol_h_polar, container, false);
        set=pref.loadArrayD("dipolSet",getActivity());
        f=set[0];
        l=set[1];
        h=set[2];
        reflenab=set[3];
        /*local variables------------------------------------*/
        double z1;
        double c=3e8;
        double pi=Math.PI;
        double k=(2*pi*f)/c;
        double [] FH = new double[361];
        int i;

        RadarChart radarchart = (RadarChart) view.findViewById(R.id.HpolarRCH);
        ArrayList<RadarEntry> graf = new ArrayList<RadarEntry>();

        /*calculation radiating function -------------------------*/
        i=0;
        for (double th =-180 ; th < 181; th++) {
            double thr1 = Math.toRadians(th);
            double spy1=Math.sin(thr1)*Math.sin(0) ;
            double spz1=Math.cos(thr1);
            double dn1=Math.sqrt(1-(spy1*spy1));
            if (reflenab==1){
                if (th<-90 || th>90){
                    z1=(2*Math.sin(k*h*spz1));
                }
                else {
                    z1=0;
                }
            }
            else{
                z1=1;
            }
            FH [i]=Math.abs((z1*(Math.cos(k*l*spy1)-Math.cos(k*l)))/dn1);
            i++;
        }

        /*norm radiating function --------------------------------------------*/
        double maxFH=0;
        for (i = 0; i < 180; i++) {
            if (FH[i] > maxFH) {
                maxFH = FH[i];
            }
        }

        /*plot radar chart ----------------------------------------------*/
        i=0;
        for (double th =-180 ; th < 181; th++) {
            double thr = Math.toRadians(th);
            String sthr = Double.toString(thr);
            float x = Float.parseFloat(sthr);
            FH[i]=FH[i]/maxFH;
            String sfh= Double.toString(FH[i]);
            float y = Float.parseFloat(sfh);
            graf.add(new RadarEntry (y,x));
            i++;
        }

        RadarDataSet set1;

        set1 = new RadarDataSet(graf,"Dipól o délce "+l+" m na kterém se šíří vlna o frekvenci "+f+" Hz");

        ArrayList<IRadarDataSet> dataSets1 = new ArrayList<IRadarDataSet>();
        dataSets1.add(set1);
        RadarData data1 = new RadarData(dataSets1);

        /*Set axis x -------------------------------------------------------------*/
        XAxis xAxis1 = radarchart.getXAxis();
        xAxis1.setTextSize(9f);
        xAxis1.setDrawLabels(false);

        /*Set axis y -------------------------------------------------------------*/
        YAxis yAxis = radarchart.getYAxis();
        yAxis.setAxisMaximum(1);


        /*Set chart -------------------------------------------------------------*/
        set1.setColor(Color.BLACK);
        set1.setFillColor(Color.BLACK);
        set1.setLineWidth(1.8f);
        radarchart.setSkipWebLineCount(10);
        radarchart.setData(data1);
        data1.setDrawValues(false);
        Description description = new Description();
        description.setText("");
        radarchart.setDescription(description);
        radarchart.notifyDataSetChanged();
        radarchart.invalidate();

        return view;
    }
}

