package com.example.bubuk.semestralniprace.Activity.Examples.Microstrip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.ControlFunction;
import com.example.bubuk.semestralniprace.R;


public class MicrostripSynthesis extends AppCompatActivity implements View.OnTouchListener {

    Button setparametresBTN;
    EditText Z01ET,ep1ET,h1ET;
    TextView wET;
    ImageView synthesisimg;

    ControlFunction control = new ControlFunction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microstrip_synthesis);

        initcontrol();

        setparametresBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                boolean empty= control.checkIsNull(Z01ET,ep1ET,h1ET);
                    if (empty==false){
                        double maxValue [] = {10000,1000,100};
                        double minValue [] = {1e-6,1e-6,1e-6};
                        String ValueName [] = {"Impedance","Permitivita","Výška"};
                        String max = control.checkIsMaxMin(maxValue,minValue,ValueName,Z01ET,ep1ET,h1ET);
                        if (max ==""){
                    double w;
                    double h = Double.parseDouble(h1ET.getText().toString() )/1000;
                    double Z0 = Double.parseDouble(Z01ET.getText().toString() );
                    double eps1 = Double.parseDouble(ep1ET.getText().toString() );

                    //calculation------------------------------------------/
                    // w/h > 1 /
                    double wh= ((120*Math.PI)/(Z0*Math.sqrt(eps1)))-(2/Math.PI)-(((2/Math.PI)-((eps1-1)/(3.7*eps1)))*Math.log(((120*Math.PI*Math.PI)/(Z0*Math.sqrt(eps1)))-1+(1.84*((eps1-1)/eps1))));
                    w = wh*h*1000;
                    //end of calculation-----------------------------------/
                    double roundw=Math.round(w*100.0)/100.0;
                    String textw = Double.toString(roundw);
                    wET = (TextView) findViewById(R.id.widthTV);
                    wET.setText(textw);}
                        else {Toast toast=Toast.makeText(getApplicationContext(),max,Toast.LENGTH_SHORT);
                            toast.show();}
                }
                else {
                    Toast toast=Toast.makeText(getApplicationContext(),"Nastavte všechny parametry",Toast.LENGTH_SHORT);
                    toast.show();
                }




            }
        });
    }

    /* change images */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.impedanceET:
                synthesisimg.setImageResource(R.drawable.microstrip1);
                break;
            case R.id.epsylonET:
                synthesisimg.setImageResource(R.drawable.microstripepsr);
                break;
            case R.id.heightSyntET:
                synthesisimg.setImageResource(R.drawable.microstriph);
                break;
        }
        return false;
    }

    /* initialize widgets */
    public void initcontrol(){
        synthesisimg = (ImageView) findViewById(R.id.synthesisIV);
        setparametresBTN = (Button) findViewById(R.id.setDipolBTN);
        Z01ET = (EditText) findViewById(R.id.impedanceET);
        ep1ET = (EditText) findViewById(R.id.epsylonET);
        h1ET = (EditText) findViewById(R.id.heightSyntET);
        Z01ET.setOnTouchListener(this);
        ep1ET.setOnTouchListener(this);
        h1ET.setOnTouchListener(this);
    }
}
