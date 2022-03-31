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

/* fragment, than visualised Dipole radiation in E and Polar coordinate system*/
public class DipolEPolar extends Fragment  {


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

        view = inflater.inflate(R.layout.dipol_e_polar, container, false);
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
        double [] FE = new double[361];
        int i;

        RadarChart radarchart = (RadarChart) view.findViewById(R.id.EPolarRCH);
        ArrayList<RadarEntry> graf = new ArrayList<RadarEntry>();

        /*calculation radiating function -------------------------*/
        i=0;
        for (double th =-180 ; th < 181; th++) {
            double thr = Math.toRadians(th);
            double spy = Math.sin(thr) * Math.sin(pi / 2);
            double spz = Math.cos(thr);
            double dn = Math.sqrt(1 - (spy * spy));
            if (reflenab==1){
                if (th<-90 || th>90){
                    z=(2*Math.sin(k*h*spz));
                }
                else {
                    z=0;
                }

            }
            else{
                z=1;
            }
            FE[i] = Math.abs((z*(Math.cos(k * l * spy) - Math.cos(k * l))) / dn);
             i++;
        }

        /*norm radiating function --------------------------------------------*/
        double maxfe=0;
            for (i = 0; i < 180; i++) {
            if (FE[i] > maxfe) {
                maxfe = FE[i];
            }
        }

        /*plot radar chart ----------------------------------------------*/
        i=0;
        for (double th =-180 ; th < 181; th++) {
            double thr = Math.toRadians(th);
            String sthr = Double.toString(thr);
            float x = Float.parseFloat(sthr);
            FE[i]=FE[i]/maxfe;
            String sfe = Double.toString(FE[i]);
            float y = Float.parseFloat(sfe);
            graf.add(new RadarEntry (y,x));
            i++;
        }

        RadarDataSet set1;
        set1 = new RadarDataSet(graf,"Dipól o délce "+l+" m na kterém se šíří vlna o frekvenci "+f+" Hz");
        ArrayList<IRadarDataSet> dataSets = new ArrayList<IRadarDataSet>();
        dataSets.add(set1);
        RadarData data = new RadarData(dataSets);

        /*Set axis x -------------------------------------------------------------*/
        XAxis xAxis = radarchart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setDrawLabels(false);

        /*Set axis y -------------------------------------------------------------*/
        YAxis yAxis = radarchart.getYAxis();
        yAxis.setAxisMaximum(1);

        /*Set chart -------------------------------------------------------------*/
        set1.setColor(Color.BLACK);
        set1.setFillColor(Color.BLACK);
        set1.setLineWidth(1.8f);
        radarchart.setSkipWebLineCount(10);
        radarchart.setData(data);
        Description description = new Description();
        description.setText("");
        radarchart.setDescription(description);
        data.setDrawValues(false);
        radarchart.notifyDataSetChanged();
        radarchart.invalidate();

        return view;
    }
}
