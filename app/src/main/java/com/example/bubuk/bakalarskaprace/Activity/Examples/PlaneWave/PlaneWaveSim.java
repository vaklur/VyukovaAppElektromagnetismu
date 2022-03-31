package com.example.bubuk.semestralniprace.Activity.Examples.PlaneWave;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.ControlFunction;
import com.example.bubuk.semestralniprace.R;

import java.util.Timer;
import java.util.TimerTask;

public class PlaneWaveSim extends AppCompatActivity {
    int sizeimg = 700;
    int sizesimul =600;                               //size of Bitmap/
    int i=0 ;int k=100;int j=600;
    double f =0;                                     //frequency of wave/
    double [] A = new double [2400];
    private Canvas c;
    private Paint paint;
    private ImageView imageview;

    ControlFunction control = new ControlFunction();

    Button startAnimation;

    EditText frequencePW;
    TextView frequencetext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plane_wave_sim);

        startAnimation = (Button) findViewById(R.id.planeWaveBTN) ;
        onClickButtonListener();

        Bitmap b = Bitmap.createBitmap(sizeimg, sizeimg,Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        c.drawColor(Color.WHITE);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(30);
        paintText.setStyle(Paint.Style.FILL);

        String min= "0";
        c.drawText(min, 100,99, paintText);
        c.drawText(min, 80,130, paintText);
        String max = "600";
        c.drawText(max, 620,99, paintText);
        c.drawText(max, 20,700, paintText);

        String description = "Vzdálenost v metrech";
        c.drawText(description, 250,90, paintText);

        imageview=(ImageView) findViewById(R.id.planeWaveIV);
        imageview.setImageBitmap(b);

        Timer timer=new Timer();
        // PLANE WAVE/----------------------------------------------------------------------------
        for (i=0;i<2400;i++) {
            A[i] = 4;
        }
        //END OF PLANE WAVE/----------------------------------------------------------------------------------------------

        timer.schedule(                             //timer of animation//
                new TimerTask(){
                    @Override
                    public void run() {
                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        update();
                                    }
                                }
                        );
                    }
                },0,1);




    }

    public void onClickButtonListener () {
        startAnimation.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick (View v) {
                  frequencePW = (EditText) findViewById(R.id.planeWaveET);

                  boolean cntrl = control.checkIsNull(frequencePW);

                  if (cntrl == false) {
                      double maxValue[] = {1E6};
                      double minValue[] = {0.001};
                      String ValueName[] = {"Frekvence"};
                      String max = control.checkIsMaxMin(maxValue, minValue, ValueName, frequencePW);
                      if (max == "") {
                          f = Double.parseDouble(frequencePW.getText().toString()) * 1000000;
                          // PLANE WAVE/----------------------------------------------------------------------------
                          for (i = 0; i < 2400; i++) {
                              double lmb = 3e8 / f;                    //wave length/
                              if (i >= 1200) {
                                  A[i] = 4;
                              } else {
                                  A[i] = Math.cos(((2 * Math.PI) / lmb) * ((i - 1200) / Math.sqrt(2)));      //arbitrary direction/
                              }
                          }
                          //END OF PLANE WAVE/----------------------------------------------------------------------------------------------
                          frequencetext = (TextView) findViewById(R.id.planeWaveTV);
                          frequencetext.setText("Nastavená frekvence je " + f / 1000000 + " MHz.");
                          Toast toast = Toast.makeText(getApplicationContext(), "Nastavili jste novou frekvenci.", Toast.LENGTH_SHORT);
                          toast.show();

                      } else {
                          Toast toast = Toast.makeText(getApplicationContext(), max, Toast.LENGTH_SHORT);
                          toast.show();
                      }
                  } else {
                      Toast toast = Toast.makeText(getApplicationContext(), "Nastavte všechny parametry", Toast.LENGTH_SHORT);
                      toast.show();
                  }
                  j = 1200;
              }
          }
        );
    }

    //ANIMATION/-------------------------------------------------------------------------------------------------
    void update(){
        paint.setColor(Color.WHITE);
        i=i+1;
        if (i<2* sizesimul) {
            if (i < sizesimul) {
                if (A[i+j] < -0.75) {
                    paint.setColor(Color.rgb(0, 0, 128));
                    c.drawLine(100, i+k, i+k, 100, paint);
                } else if (A[i+j] >= -0.75 && (A[i+j] < -0.5)) {
                    paint.setColor(Color.rgb(128, 128, 255));
                    c.drawLine(100, i+k, i+k, 100, paint);
                } else if (A[i+j] >= -0.5 && (A[i+j] < -0.25)) {
                    paint.setColor(Color.rgb(0, 255, 255));
                    c.drawLine(100, i+k, i+k, 100, paint);
                } else if (A[i+j] >= -0.25 && (A[i+j] < 0)) {
                    paint.setColor(Color.rgb(0, 255, 128));
                    c.drawLine(100, i+k, i+k, 100, paint);
                } else if (A[i+j] >= 0 && (A[i+j] < 0.25)) {
                    paint.setColor(Color.rgb(255, 255, 128));
                    c.drawLine(100, i+k, i+k, 100, paint);
                } else if (A[i+j] >= 0.25 && (A[i+j] < 0.5)) {
                    paint.setColor(Color.rgb(255, 255, 0));
                    c.drawLine(100, i+k, i+k, 100, paint);
                } else if (A[i+j] >= 0.5 && (A[i+j] < 0.75)) {
                    paint.setColor(Color.rgb(255, 128, 0));
                    c.drawLine(100, i+k, i+k, 100, paint);
                } else if (A[i+j] >= 0.75 && (A[i+j] <= 1) ) {
                    paint.setColor(Color.rgb(255, 0, 0));
                    c.drawLine(100, i+k, i+k, 100, paint);
                }
                else if (A[i+j] == 4) {
                    paint.setColor(Color.rgb(255, 255, 255));
                    c.drawLine(100, i+k, i+k, 100, paint);
                }
            } else {
                int x = i - sizesimul;
                if (A[i+j] < -0.75) {
                    paint.setColor(Color.rgb(0, 0, 128));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                } else if (A[i+j] >= -0.75 && (A[i+j] < -0.5)) {
                    paint.setColor(Color.rgb(128, 128, 255));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                } else if (A[i+j] >= -0.5 && (A[i+j] < -0.25)) {
                    paint.setColor(Color.rgb(0, 255, 255));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                } else if (A[i+j] >= -0.25 && (A[i+j] < 0)) {
                    paint.setColor(Color.rgb(0, 255, 128));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                } else if (A[i+j] >= 0 && (A[i+j] < 0.25)) {
                    paint.setColor(Color.rgb(255, 255, 128));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                } else if (A[i+j] >= 0.25 && (A[i+j] < 0.5)) {
                    paint.setColor(Color.rgb(255, 255, 0));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                } else if (A[i+j] >= 0.5 && (A[i+j] < 0.75)) {
                    paint.setColor(Color.rgb(255, 128, 0));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                } else if (A[i+j] >= 0.75 && (A[i+j] <= 1)) {
                    paint.setColor(Color.rgb(255, 0, 0));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                }else if (A[i+j] == 4) {
                    paint.setColor(Color.rgb(255, 255, 255));
                    c.drawLine(x+k, sizeimg, sizeimg,x+k, paint);
                }
            }
        }
        else {
            i=i-2* sizesimul;
            if (j>0){
                j=j-10;
            }
            else {
            }
        }
        paint.setColor(Color.rgb(128, 0, 0));
        c.drawLine(100,101,400,399,paint);
        c.drawLine(101,100,399,400,paint);
        c.drawLine(100,100,400,400,paint);
        c.drawLine(400,400,400,390,paint);
        c.drawLine(400,400,390,400,paint);

        Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(30);
        paintText.setStyle(Paint.Style.FILL);
        String direction = "Směr šíření vlny.";
        c.drawText(direction, 410,390, paintText);
        imageview.invalidate();

    }
    //END OF ANIMATION/----------------------------------------------------------------------------------------------
}







