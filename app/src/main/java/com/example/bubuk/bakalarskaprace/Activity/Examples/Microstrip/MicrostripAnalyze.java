package com.example.bubuk.semestralniprace.Activity.Examples.Microstrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.ControlFunction;
import com.example.bubuk.semestralniprace.R;

/* Activity, than calculate microstrip*/
public class MicrostripAnalyze extends AppCompatActivity implements View.OnTouchListener{

    Button      setparamBTN;
    EditText    w1ET,h1ET,t1ET,eps1ET;
    TextView    impZ0;
    ImageView   analyzeimg;

    ControlFunction control = new ControlFunction();

    /* create activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microstrip_analyze);

        initcontrol();

        setparamBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean empty= control.checkIsNull(w1ET,h1ET,t1ET,eps1ET);
                if (empty==false){
                    double maxValue [] = {100,100,100,1000};
                    double minValue [] = {1e-6,1e-6,1e-6,0};
                    String ValueName [] = {"Šířka","Výška","Tloušťka","Permitivita"};
                    String max = control.checkIsMaxMin(maxValue,minValue,ValueName,w1ET,h1ET,t1ET,eps1ET);
                    if (max ==""){
                    double dw;
                    double wef;
                    double kw = 0;
                    double epsef;
                    double Z0= 0;

                    double w = Double.parseDouble(w1ET.getText().toString() )/1000;
                    double h = Double.parseDouble(h1ET.getText().toString() )/1000;
                    double t = Double.parseDouble(t1ET.getText().toString() )/1000000;
                    double eps = Double.parseDouble(eps1ET.getText().toString() );

                    /*calculation*/

                    if (w/h > (1/(2*Math.PI))){
                        //effect of non-zero t/
                        if (t!=0){
                            dw =1.25*(t/Math.PI)*(Math.log(2*h/t)+1);
                        }
                        else{
                            dw=0;
                        }
                        kw=w+dw;
                    }
                    else if (w/h <= (1/(2*Math.PI))){
                        //effect of non-zero t/
                        if (t!=0){
                            dw =1.25*(t/Math.PI)*(Math.log(2*w*Math.PI)+1);
                        }
                        else{
                            dw=0;
                        }
                        kw=w+dw;
                    }


                    if (w/h >= 1){
                        //effective width/
                        wef=kw+2.42*h-(0.44*h*h)/kw+h*Math.pow(1-(h/kw),6);
                        //effective permittivity/
                        epsef=eps-((eps-1)/2)*(Math.log(6.28*((kw/(2*h))+0.85)))/(kw/h+(2/Math.PI)*(Math.log(17.08*((kw/(2*h))+0.85))));
                        //wave impedance/
                        Z0=((120*Math.PI)/Math.sqrt(epsef))*(h/wef);
                    }

                    else if (w/h < 1){
                        //effective width/
                        wef=(2*Math.PI*h)/(Math.log(((8*h)/kw)+(kw/(4*h))));
                        //effective permittivity/
                        epsef=(eps+1)/2+(0.9/Math.PI)*((eps-1)/(Math.log((8*h)/kw)));
                        //wave impedance/
                        Z0=((120*Math.PI)/Math.sqrt(epsef))*(h/wef);
                    }

                    //end of calculation-----------------------------------/
                    double roundz0=Math.round(Z0*1000.0)/1000.0;
                    String textZ0 = Double.toString(roundz0);
                    impZ0 = (TextView) findViewById(R.id.impedanceTV);
                    impZ0.setText(textZ0);}
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
            case R.id.widthET:
                analyzeimg.setImageResource(R.drawable.microstripw);
                break;
            case R.id.setDipolHET:
                analyzeimg.setImageResource(R.drawable.microstriph);
                break;
            case R.id.depthET:
                analyzeimg.setImageResource(R.drawable.microstript);
                break;
            case R.id.epsylonrET:
                analyzeimg.setImageResource(R.drawable.microstripepsr);
                break;
        }
        return false;
    }

    /* initialize widgets */
    public void initcontrol(){
        analyzeimg=(ImageView) findViewById(R.id.AnalyzeIV);
        setparamBTN = (Button) findViewById(R.id.set1BTN);
        w1ET = (EditText) findViewById(R.id.widthET);
        h1ET= (EditText) findViewById(R.id.setDipolHET);
        t1ET = (EditText) findViewById(R.id.depthET);
        eps1ET = (EditText) findViewById(R.id.epsylonrET);
        w1ET.setOnTouchListener(this);
        h1ET.setOnTouchListener(this);
        t1ET.setOnTouchListener(this);
        eps1ET.setOnTouchListener(this);
    }


}
