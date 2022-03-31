package com.example.bubuk.semestralniprace.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.Activity.Examples.Examples;
import com.example.bubuk.semestralniprace.Activity.Test.Test;
import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;

import java.util.Arrays;


public class Menu extends AppCompatActivity implements View.OnClickListener{

    Button test, examples, aboutapp;
    Intent intent,intent1,intent2;
    private Boolean firstTime = null;

    SharePref share = new SharePref();

    /*create activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        initcontrol();
        isFirstTime();
    }

    /*initialize widgets */
    public void initcontrol(){
        examples = (Button) findViewById(R.id.examplesBTN);
        aboutapp = (Button) findViewById(R.id.aboutAppBTN);
        test = (Button) findViewById(R.id.testBTN);

        examples.setOnClickListener(this);
        test.setOnClickListener(this);
        aboutapp.setOnClickListener(this);
    }

    /*open other activity */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.examplesBTN:
                intent = new Intent(Menu.this, Examples.class);
                startActivity(intent);
                break;
            case R.id.testBTN:
                intent1 = new Intent(Menu.this, Test.class);
                startActivity(intent1);
                break;
            case R.id.aboutAppBTN:
                intent2 = new Intent(Menu.this, AboutApp.class);
                startActivity(intent2);
                break;
        }
    }

    /*initialize parameters and variables, when first app run on device*/
    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
                /*test variables initialize*/
                String [] testresults= new String[] { "", "", "", "","","", "","",""};
                String [] testresultsfail= new String[] { "", "", "", "","","", "","",""};
                int testnumb=0;                                                 // number of tests
                int choosequestnumb=5;                                          // number of quests, that have been selected
                /*waveguide variables initialize*/
                double [] cwSet = {0.005,1,1,10E9};                             // circle waveguide     (a,epsr,mir,frequency)
                double [] rwSet = {0.032,0.017,1,1,10E9};                       // rectangle waveguide  (a,b,epsr,mir,frequency)
                double [] coaxwSet = {0.010,0.036,1,1,10E9};                    // coaxial waveguide    (r0,R0,epsr,mir,frequency)
                /*other variables initialize*/
                double [] dipolSet = {3000000,1,0.5,0};                         // dipole               (f,l,h,enable reflector)
                double [] reflectionSet = {1,0.6,30000000,0,100,100,0,0,0};       // reflection on line   (f,psi,freq,beta,z0,zkRE,zkIM,position,value)
                /*wave resonators variables initialize*/
                double [] wrCavCubSet = {0.010,0.005,0.020,1,1,2E-4,56E6};      // cavity cuboid        (a,b,l,epsr,mir,tandel,conductivity)
                double [] wrCavCylSet = {0.010,0.020,1,1,2E-4,56E6};            // cavity cylinder      (a,l,epsr,mir,tandel,conductivity)
                double [] wrPlanarRectSet = {0.010,0.050,0.001,1,2E-4,56E6};    // planar rectangle     (w,l,h,epsr,tandel,conductivity)
                double [] wrPlanarCircSet = {0.010,0.050,1,2E-4,56E6};          // planar circular      (a,h,epsr,tandel,conductivity)
                double [] wrDielSet = {0.010,0.005,0.020,1,2E-4};               // dielectric cuboid    (a,b,l,epsr,tandel)
                double [] wrCoaxSet = {0.010,0.050,0.020,1,1,2E-4,56E6};        // coaxial              (r0,R0,l,epsr,mir,tandel,conductivity)
                int [] setModeRect = new int[20];
                int [] setModeCirc = new int[20];
                int [] setModeCavCub = new int[25];
                int [] setModeCavCyl = new int[25];
                int [] setModePlanarRect = new int[25];
                int [] setModePlanarCircle = new int[25];
                int [] setModeDiel = new int[25];
                Arrays.fill(setModeRect,-1);
                Arrays.fill(setModeCirc,-1);
                Arrays.fill(setModeCavCub,-1);
                Arrays.fill(setModeCavCyl,-1);
                Arrays.fill(setModePlanarRect,-1);
                Arrays.fill(setModePlanarCircle,-1);
                Arrays.fill(setModeDiel,-1);
                /*save all variables */
                share.saveArrayI(setModeRect,"setModeRect",getApplicationContext());
                share.saveArrayI(setModeCirc,"setModeCirc",getApplicationContext());
                share.saveArrayI(setModeCavCub,"setModeCavCub",getApplicationContext());
                share.saveArrayI(setModeCavCyl,"setModeCavCyl",getApplicationContext());
                share.saveArrayI(setModePlanarRect,"setModePlanarRect",getApplicationContext());
                share.saveArrayI(setModePlanarCircle,"setModePlanarCircle",getApplicationContext());
                share.saveArrayI(setModeDiel,"setModeDiel",getApplicationContext());
                share.saveArrayS(testresults,"testResults",getApplicationContext());
                share.saveArrayS(testresultsfail,"testResultsFail",getApplicationContext());
                share.saveArrayD(cwSet,"cwSet",getApplicationContext());
                share.saveArrayD(dipolSet,"dipolSet",getApplicationContext());
                share.saveArrayD(reflectionSet,"reflectSet",getApplicationContext());
                share.saveArrayD(rwSet,"rwSet",getApplicationContext());
                share.saveArrayD(coaxwSet,"coaxwSet",getApplicationContext());
                share.saveArrayD(wrCavCubSet,"wrCavCubSet",getApplicationContext());
                share.saveArrayD(wrCavCylSet,"wrCavCylSet",getApplicationContext());
                share.saveArrayD(wrPlanarRectSet,"wrPlanarRectSet",getApplicationContext());
                share.saveArrayD(wrPlanarCircSet,"wrPlanarCircSet",getApplicationContext());
                share.saveArrayD(wrCoaxSet,"wrCoaxSet",getApplicationContext());
                share.saveArrayD(wrDielSet,"wrDielSet",getApplicationContext());
                share.saveI(testnumb,"testnumb",getApplicationContext());
                share.saveI(choosequestnumb,"choosequestnumb",getApplicationContext());
            }
        }
        return firstTime;
    }
}


