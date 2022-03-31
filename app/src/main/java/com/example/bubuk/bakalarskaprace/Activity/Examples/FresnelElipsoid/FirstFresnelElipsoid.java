package com.example.bubuk.semestralniprace.Activity.Examples.FresnelElipsoid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.ControlFunction;
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
import java.lang.Math;
import java.lang.String;


/*Activity, than visualised first Fresnel zone*/
public class FirstFresnelElipsoid extends AppCompatActivity implements View.OnClickListener {

    /* global variables*/
    double frequency =0;    // Hz
    double distance=0;      // m

    EditText frequenceET, lenghtET;
    TextView radiusTV;
    TextView dParam, rParam;
    LineChart lchart;
    Button setFresnel;

    ControlFunction control = new ControlFunction();

    /*create activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresnel_elipsoid_sim);

        initcontrol();
    }

    /*initialize widgets */
    public void initcontrol(){
        dParam = (TextView) findViewById(R.id.fresnelElipTV7);
        rParam = (TextView) findViewById(R.id.fresnelElipTV6);
        frequenceET = (EditText) findViewById(R.id.frequenceFresnelElipET);
        lenghtET = (EditText) findViewById(R.id.lenghtFresnelElipET);
        lchart = (LineChart) findViewById(R.id.fresnelElipLN);
        setFresnel = (Button) findViewById(R.id.setFresnelElipBTN);
        setFresnel.setOnClickListener(this);
    }

    /*calculate first Fresnel ellipse for specified parameters*/
    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.setFresnelElipBTN:
                ArrayList<Entry> radius = new ArrayList<Entry>();
                ArrayList<Entry> radius1 = new ArrayList<Entry>();
                ArrayList<Entry> radius2 = new ArrayList<Entry>();
                ArrayList<Entry> radius3 = new ArrayList<Entry>();

                boolean cntrl = control.checkIsNull(frequenceET, lenghtET);

                if (cntrl == false) {
                    double freghz = Double.parseDouble(frequenceET.getText().toString());
                    double kilometer = Double.parseDouble(lenghtET.getText().toString());
                    frequency = freghz * 1000000000;       // GHz to Hz
                    distance = kilometer * 1000;           // km to m
                    double maxValue[] = {1000, 1000};
                    double minValue[] = {0, 0};
                    String ValueName[] = {"Frekvence", "Vzdálenost"};
                    String maxmin = control.checkIsMaxMin(maxValue, minValue, ValueName, frequenceET, lenghtET);
                    if (maxmin == "") {
                        dParam.setVisibility(View.VISIBLE);
                        rParam.setVisibility(View.VISIBLE);
                        lchart.setVisibility(View.VISIBLE);

            /* elipse parameters*/
                        double e = distance / 2;
                        double b = Math.sqrt(distance * (300000000) / (4 * frequency));
                        double a = Math.sqrt((e * e) + (b * b));
                        double step = a / 1000;


            /* fresnel zone calculation*/
                        for (double i = 0; i < 2002; i++) {

                            double x = (step * i) - a;
                            String sx = Double.toString(x + a);
                            float l = Float.parseFloat(sx);
                            double y = Math.sqrt(((a * a * b * b) - (x * x * b * b)) / (a * a));
                            String sy = Double.toString(y);
                            float result = Float.parseFloat(sy);
                            radius.add(new Entry(l, result));
                        }

                        for (double i = 0; i < 2002; i++) {

                            double x = (step * i) - a;
                            String sx = Double.toString(x + a);
                            float l = Float.parseFloat(sx);
                            double y = -Math.sqrt(((a * a * b * b) - (x * x * b * b)) / (a * a));
                            String sy = Double.toString(y);
                            float result = Float.parseFloat(sy);
                            radius1.add(new Entry(l, result));
                        }
            /* visualised by linear chart*/
                        String sd = Double.toString(a - e);
                        float fd = Float.parseFloat(sd);
                        String sd1 = Double.toString(a + e);
                        float fd1 = Float.parseFloat(sd1);
                        radius2.add(new Entry(fd, 0));
                        radius2.add(new Entry(fd1, 0));

                        String sa = Double.toString(a);
                        float fa = Float.parseFloat(sa);
                        String sb = Double.toString(b);
                        float fb = Float.parseFloat(sb);
                        radius3.add(new Entry(fa, fb));
                        radius3.add(new Entry(fa, 0));

                        radiusTV = (TextView) findViewById(R.id.fresnelElipTV5);
                        double bRound = Math.round(b * 100.0) / 100.0;
                        radiusTV.setText("Poloměr zóny je " + bRound + " m.");

                        LineDataSet set1;
                        LineDataSet set2;
                        LineDataSet set3;
                        LineDataSet set4;

                        set1 = new LineDataSet(radius, "");
                        set2 = new LineDataSet(radius1, "");
                        set3 = new LineDataSet(radius2, "Vzdálenost mezi anténami");
                        set4 = new LineDataSet(radius3, "Poloměr zóny");

                        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                        dataSets.add(set1);
                        dataSets.add(set2);
                        dataSets.add(set3);
                        dataSets.add(set4);

                        LineData data = new LineData(dataSets);

            /* Set axis x -------------------------------------------------------------*/
                        XAxis xAxis = lchart.getXAxis();
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setTextSize(10f);

            /* Set axis y -------------------------------------------------------------*/
                        YAxis yAxis = lchart.getAxisLeft();
                        yAxis.setTextSize(10f);
                        lchart.getAxisRight().setEnabled(false);


            /* Set chart -------------------------------------------------------------*/
                        lchart.getDescription().setText("");
                        set1.setColor(Color.BLACK);
                        set1.setFillColor(Color.BLACK);
                        set1.setLineWidth(1.8f);
                        set1.setDrawCircles(false);

                        set2.setColor(Color.BLACK);
                        set2.setFillColor(Color.BLACK);
                        set2.setLineWidth(1.8f);
                        set2.setDrawCircles(false);

                        set3.setColor(Color.BLUE);
                        set3.setCircleColor(Color.BLUE);

                        set4.setColor(Color.RED);
                        set4.setCircleColor(Color.RED);

                        Legend legend = lchart.getLegend();
                        legend.setEnabled(true);

                        lchart.setPinchZoom(false);
                        lchart.setData(data);
                        data.setValueTextSize(9f);
                        lchart.notifyDataSetChanged();
                        lchart.invalidate();}
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), maxmin, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Nastavte všechny parametry", Toast.LENGTH_SHORT);
                    toast.show();
                }
        }}

    }

