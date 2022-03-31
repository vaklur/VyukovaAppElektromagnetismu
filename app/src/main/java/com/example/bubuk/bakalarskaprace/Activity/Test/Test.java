package com.example.bubuk.semestralniprace.Activity.Test;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;

public class Test extends AppCompatActivity implements View.OnClickListener {

    Button runtest,runhistory,runset,settestsettings;
    SeekBar questionseekbar;
    TextView questionnumb;
    final Context set = this;
    SharePref share = new SharePref();

    int progressValue;


    /*create activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        initcontrol();
    }

    /*initialize widgets */
    public void initcontrol(){
        runtest = (Button) findViewById(R.id.testrunBTN);
        runhistory = (Button) findViewById(R.id.testhistoryBTN);
        runset = (Button) findViewById(R.id.testsetBTN);

        runtest.setOnClickListener(this);
        runhistory.setOnClickListener(this);
        runset.setOnClickListener(this);
    }

    /*open other activity */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.testrunBTN:
                Intent intent = new Intent(Test.this,TestRun.class);
                startActivity(intent);
                break;
            case R.id.testhistoryBTN:
                Intent intent1 = new Intent(Test.this,TestHistory.class);
                startActivity(intent1);
                break;
            case R.id.testsetBTN:
                testset();
                break;
        }
    }

    /*dialog for set number of test questions*/
    private void testset() {
        /*create dialog*/
        final Dialog setHistory = new Dialog(set);
        setHistory.setContentView(R.layout.test_set);
        setHistory.setTitle("Nastavení testu");
        /* initialize dialog widgets*/
        questionnumb = (TextView) setHistory.findViewById(R.id.numbQuesSetTV);
        questionseekbar = (SeekBar) setHistory.findViewById(R.id.setTestSB);
        settestsettings = (Button) setHistory.findViewById(R.id.numbQuesSetBTN);
        /* load number of question which have been set*/
        int l=share.loadI("choosequestnumb",set);
        questionseekbar.setProgress((l/5)-1);
        questionnumb.setText(+(questionseekbar.getProgress() + 1) * 5 + "");
        questionseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = 5 * (progress + 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //do nothing...
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                questionnumb.setText(progressValue + "");
            }
        });
        settestsettings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*save number of set questions*/
                        share.saveI(progressValue,"choosequestnumb",set);
                        setHistory.dismiss();

                    }
                }
        );
        setHistory.show();
    }
}
