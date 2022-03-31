package com.example.bubuk.semestralniprace.OtherClass;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.R;
import android.graphics.Color;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class WaveresonatorsOwnModes {
    Dialog ModesDialog;
    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    CheckBox oneModeCheckbox,twoModeCheckbox,threeModeCheckbox,fourModeCheckbox,fiveModeCheckbox;
    EditText mOneModeET,nOneModeET,pOneModeET,mTwoModeET,nTwoModeET,pTwoModeET,mThreeModeET,nThreeModeET,pThreeModeET,mFourModeET,nFourModeET,pFourModeET,mFiveModeET,nFiveModeET,pFiveModeET;
    TextView mOneModeTV,nOneModeTV,pOneModeTV,mTwoModeTV,nTwoModeTV,pTwoModeTV,mThreeModeTV,nThreeModeTV,pThreeModeTV,mFourModeTV,nFourModeTV,pFourModeTV,mFiveModeTV,nFiveModeTV,pFiveModeTV;
    Spinner oneModeS,twoModeS,threeModeS,fourModeS,fiveModeS;

    Button modesSet;



    public void SetOwnModes(final int [] outmodes, final Context mContext, final String preference, final int TypeResonator){
        /*type resonator value:
        * 1 - Cavity Cuboid      TE,TM modes: m=0,1,2.. n=1,2,3.. p=1,2,3... or m=1,2.. n=0,1,2,3.. p=1,2,3...
        * 2 - Cavity Cilinder    TE,TM modes: m=0,1,2.. n=1,2,3.. p=0,1,2...  Max m=10 n=5!
        * 3 - Planar Rectangular TE modes: m=1,2,3... n=0 p=0,1,2...
        * 4 - Planar Circular    TE modes: m=0,1,2.. n=1,2,3.. p=0    Max m=10 n=5!
        * 5 - Dielectric         TE,TM modes: m=0,1,2.. n=1,2,3.. p=1,2,3... or m=1,2.. n=0,1,2,3.. p=1,2,3...
        * */
        ModesDialog = new Dialog(mContext);
        ModesDialog.setContentView(R.layout.waveresonators_modes);
        ModesDialog.setTitle("Zvolení vlastních vidů");
        initWidgets();

        String[] modes;
        if (TypeResonator==3 || TypeResonator==4) modes = new String[]{"TE"};
        else modes = new String[]{"TE","TM"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,modes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //1------------------------------------------------------------------------
        oneModeS.setAdapter(spinnerArrayAdapter);
        twoModeS.setAdapter(spinnerArrayAdapter);
        threeModeS.setAdapter(spinnerArrayAdapter);
        fourModeS.setAdapter(spinnerArrayAdapter);
        fiveModeS.setAdapter(spinnerArrayAdapter);
        preSet(outmodes,TypeResonator);
        oneModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[1]=0;
                if (position == 1) outmodes[1]=1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        oneModeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    mOneModeET.setVisibility(View.VISIBLE);
                    nOneModeET.setVisibility(View.VISIBLE);
                    pOneModeET.setVisibility(View.VISIBLE);
                    mOneModeTV.setVisibility(View.VISIBLE);
                    nOneModeTV.setVisibility(View.VISIBLE);
                    pOneModeTV.setVisibility(View.VISIBLE);
                    oneModeS.setVisibility(View.VISIBLE);
                    outmodes[0]=1;

                } else {
                    mOneModeET.setVisibility(View.GONE);
                    nOneModeET.setVisibility(View.GONE);
                    pOneModeET.setVisibility(View.GONE);
                    mOneModeTV.setVisibility(View.GONE);
                    nOneModeTV.setVisibility(View.GONE);
                    pOneModeTV.setVisibility(View.GONE);
                    oneModeS.setVisibility(View.GONE);
                    outmodes[0]=0;

                }
            }
        });
        //2------------------------------------------------------------------------

        twoModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[6]=0;
                if (position == 1) outmodes[6]=1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        twoModeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    mTwoModeET.setVisibility(View.VISIBLE);
                    nTwoModeET.setVisibility(View.VISIBLE);
                    pTwoModeET.setVisibility(View.VISIBLE);
                    mTwoModeTV.setVisibility(View.VISIBLE);
                    nTwoModeTV.setVisibility(View.VISIBLE);
                    pTwoModeTV.setVisibility(View.VISIBLE);
                    twoModeS.setVisibility(View.VISIBLE);
                    outmodes[5]=1;

                } else {
                    mTwoModeET.setVisibility(View.GONE);
                    nTwoModeET.setVisibility(View.GONE);
                    pTwoModeET.setVisibility(View.GONE);
                    mTwoModeTV.setVisibility(View.GONE);
                    nTwoModeTV.setVisibility(View.GONE);
                    pTwoModeTV.setVisibility(View.GONE);
                    twoModeS.setVisibility(View.GONE);
                    outmodes[5]=0;

                }
            }
        });
        //3------------------------------------------------------------------------

        threeModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[11]=0;
                if (position == 1) outmodes[11]=1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        threeModeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    mThreeModeET.setVisibility(View.VISIBLE);
                    nThreeModeET.setVisibility(View.VISIBLE);
                    pThreeModeET.setVisibility(View.VISIBLE);
                    mThreeModeTV.setVisibility(View.VISIBLE);
                    nThreeModeTV.setVisibility(View.VISIBLE);
                    pThreeModeTV.setVisibility(View.VISIBLE);
                    threeModeS.setVisibility(View.VISIBLE);
                    outmodes[10]=1;

                } else {
                    mThreeModeET.setVisibility(View.GONE);
                    nThreeModeET.setVisibility(View.GONE);
                    pThreeModeET.setVisibility(View.GONE);
                    mThreeModeTV.setVisibility(View.GONE);
                    nThreeModeTV.setVisibility(View.GONE);
                    pThreeModeTV.setVisibility(View.GONE);
                    threeModeS.setVisibility(View.GONE);
                    outmodes[10]=0;

                }
            }
        });
        //4------------------------------------------------------------------------

        fourModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[16]=0;
                if (position == 1) outmodes[16]=1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        fourModeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    mFourModeET.setVisibility(View.VISIBLE);
                    nFourModeET.setVisibility(View.VISIBLE);
                    pFourModeET.setVisibility(View.VISIBLE);
                    mFourModeTV.setVisibility(View.VISIBLE);
                    nFourModeTV.setVisibility(View.VISIBLE);
                    pFourModeTV.setVisibility(View.VISIBLE);
                    fourModeS.setVisibility(View.VISIBLE);
                    outmodes[15]=1;

                } else {
                    mFourModeET.setVisibility(View.GONE);
                    nFourModeET.setVisibility(View.GONE);
                    pFourModeET.setVisibility(View.GONE);
                    mFourModeTV.setVisibility(View.GONE);
                    nFourModeTV.setVisibility(View.GONE);
                    pFourModeTV.setVisibility(View.GONE);
                    fourModeS.setVisibility(View.GONE);
                    outmodes[15]=0;

                }
            }
        });
        //5------------------------------------------------------------------------

        fiveModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[21]=0;
                if (position == 1) outmodes[21]=1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        fiveModeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    mFiveModeET.setVisibility(View.VISIBLE);
                    nFiveModeET.setVisibility(View.VISIBLE);
                    pFiveModeET.setVisibility(View.VISIBLE);
                    mFiveModeTV.setVisibility(View.VISIBLE);
                    nFiveModeTV.setVisibility(View.VISIBLE);
                    pFiveModeTV.setVisibility(View.VISIBLE);
                    fiveModeS.setVisibility(View.VISIBLE);
                    outmodes[20]=1;

                } else {
                    mFiveModeET.setVisibility(View.GONE);
                    nFiveModeET.setVisibility(View.GONE);
                    pFiveModeET.setVisibility(View.GONE);
                    mFiveModeTV.setVisibility(View.GONE);
                    nFiveModeTV.setVisibility(View.GONE);
                    pFiveModeTV.setVisibility(View.GONE);
                    fiveModeS.setVisibility(View.GONE);
                    outmodes[20]=0;

                }
            }
        });
        modesSet.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean empty = control.checkIsNull(mOneModeET,nOneModeET,pOneModeET,mTwoModeET,nTwoModeET,pTwoModeET,mThreeModeET,nThreeModeET,pThreeModeET,mFourModeET,nFourModeET,pFourModeET,mFiveModeET,nFiveModeET,pFiveModeET);
                if (empty == false) {
                    if (outmodes[0]==1){
                        outmodes[2]= Integer.parseInt(mOneModeET.getText().toString());
                        outmodes[3]=Integer.parseInt(nOneModeET.getText().toString());
                        outmodes[4]=Integer.parseInt(pOneModeET.getText().toString());}
                    else {
                        outmodes[2]=-1;
                        outmodes[3]=-1;
                        outmodes[4]=-1;
                    }
                    if (outmodes[5]==1){
                        outmodes[7]= Integer.parseInt(mTwoModeET.getText().toString());
                        outmodes[8]=Integer.parseInt(nTwoModeET.getText().toString());
                        outmodes[9]=Integer.parseInt(pTwoModeET.getText().toString());}
                    else {
                        outmodes[7]=-1;
                        outmodes[8]=-1;
                        outmodes[9]=-1;
                    }
                    if (outmodes[10]==1){
                        outmodes[12]= Integer.parseInt(mThreeModeET.getText().toString());
                        outmodes[13]=Integer.parseInt(nThreeModeET.getText().toString());
                        outmodes[14]=Integer.parseInt(pThreeModeET.getText().toString());}
                    else {
                        outmodes[12]=-1;
                        outmodes[13]=-1;
                        outmodes[14]=-1;
                    }
                    if (outmodes[15]==1){
                        outmodes[17]= Integer.parseInt(mFourModeET.getText().toString());
                        outmodes[18]=Integer.parseInt(nFourModeET.getText().toString());
                        outmodes[19]=Integer.parseInt(pFourModeET.getText().toString());}
                    else {
                        outmodes[17]=-1;
                        outmodes[18]=-1;
                        outmodes[19]=-1;
                    }
                    if (outmodes[20]==1){
                        outmodes[22]= Integer.parseInt(mFiveModeET.getText().toString());
                        outmodes[23]=Integer.parseInt(nFiveModeET.getText().toString());
                        outmodes[24]=Integer.parseInt(pFiveModeET.getText().toString());}
                    else {
                        outmodes[22]=-1;
                        outmodes[23]=-1;
                        outmodes[24]=-1;
                    }
                    String wrongModes = CheckModesNumber(TypeResonator,outmodes);
                    if (wrongModes==""){
                        pref.saveArrayI(outmodes, preference, mContext);
                        Toast toast = Toast.makeText(mContext, "Nastavili jste zvolené vidy", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        Toast toast = Toast.makeText(mContext, wrongModes, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else {
                    Toast toast = Toast.makeText(mContext, "Zvolte všechna vidová čísla", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }));
        ModesDialog.show();



    }

    private void initWidgets(){
        oneModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmrModeOneCB);
        mOneModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeOneMET);
        nOneModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeOneNET);
        pOneModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeOnePET);
        mOneModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeOneTV);
        nOneModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeOneTV1);
        pOneModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeOneTV2);
        oneModeS = (Spinner) ModesDialog.findViewById(R.id.wmrModeOneS);

        twoModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmrModeTwoCB);
        mTwoModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeTwoMET);
        nTwoModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeTwoNET);
        pTwoModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeTwoPET);
        mTwoModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeTwoTV);
        nTwoModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeTwoTV1);
        pTwoModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeTwoTV2);
        twoModeS = (Spinner) ModesDialog.findViewById(R.id.wmrModeTwoS);

        threeModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmrModeThreeCB);
        mThreeModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeThreeMET);
        nThreeModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeThreeNET);
        pThreeModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeThreePET);
        mThreeModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeThreeTV);
        nThreeModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeThreeTV1);
        pThreeModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeThreeTV2);
        threeModeS = (Spinner) ModesDialog.findViewById(R.id.wmrModeThreeS);

        fourModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmrModeFourCB);
        mFourModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeFourMET);
        nFourModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeFourNET);
        pFourModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeFourPET);
        mFourModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeFourTV);
        nFourModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeFourTV1);
        pFourModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeFourTV2);
        fourModeS = (Spinner) ModesDialog.findViewById(R.id.wmrModeFourS);

        fiveModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmrModeFiveCB);
        mFiveModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeFiveMET);
        nFiveModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeFiveNET);
        pFiveModeET = (EditText) ModesDialog.findViewById(R.id.wmrModeFivePET);
        mFiveModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeFiveTV);
        nFiveModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeFiveTV1);
        pFiveModeTV = (TextView)  ModesDialog.findViewById(R.id.wmrModeFiveTV2);
        fiveModeS = (Spinner) ModesDialog.findViewById(R.id.wmrModeFiveS);


        modesSet = (Button) ModesDialog.findViewById(R.id.wmrModeSetBTN);
    }

    private void preSet(int [] outmodes, int TypeResonator){
        if (TypeResonator==3){
            nOneModeET.setEnabled(FALSE);
            nTwoModeET.setEnabled(FALSE);
            nThreeModeET.setEnabled(FALSE);
            nFourModeET.setEnabled(FALSE);
            nFiveModeET.setEnabled(FALSE);
            nOneModeTV.setTextColor(Color.LTGRAY);
            nTwoModeTV.setTextColor(Color.LTGRAY);
            nThreeModeTV.setTextColor(Color.LTGRAY);
            nFourModeTV.setTextColor(Color.LTGRAY);
            nFiveModeTV.setTextColor(Color.LTGRAY);
            nOneModeET.setText("0");
            nTwoModeET.setText("0");
            nThreeModeET.setText("0");
            nFourModeET.setText("0");
            nFiveModeET.setText("0");

        }
        else if (TypeResonator==4){
            pOneModeET.setEnabled(FALSE);
            pTwoModeET.setEnabled(FALSE);
            pThreeModeET.setEnabled(FALSE);
            pFourModeET.setEnabled(FALSE);
            pFiveModeET.setEnabled(FALSE);
            pOneModeTV.setTextColor(Color.LTGRAY);
            pTwoModeTV.setTextColor(Color.LTGRAY);
            pThreeModeTV.setTextColor(Color.LTGRAY);
            pFourModeTV.setTextColor(Color.LTGRAY);
            pFiveModeTV.setTextColor(Color.LTGRAY);
            pOneModeET.setText("0");
            pTwoModeET.setText("0");
            pThreeModeET.setText("0");
            pFourModeET.setText("0");
            pFiveModeET.setText("0");
        }
        //**********************
        if(outmodes[0]==1){
            oneModeCheckbox.setChecked(TRUE);
            if(outmodes[1]==0) oneModeS.setSelection(0);
            else oneModeS.setSelection(1);
            mOneModeET.setText(Integer.toString(outmodes[2]));
            nOneModeET.setText(Integer.toString(outmodes[3]));
            pOneModeET.setText(Integer.toString(outmodes[4]));
            mOneModeET.setVisibility(View.VISIBLE);
            nOneModeET.setVisibility(View.VISIBLE);
            pOneModeET.setVisibility(View.VISIBLE);
            mOneModeTV.setVisibility(View.VISIBLE);
            nOneModeTV.setVisibility(View.VISIBLE);
            pOneModeTV.setVisibility(View.VISIBLE);
            oneModeS.setVisibility(View.VISIBLE);

        }
        if(outmodes[5]==1){
            twoModeCheckbox.setChecked(TRUE);
            if(outmodes[6]==0) twoModeS.setSelection(0);
            else twoModeS.setSelection(1);
            mTwoModeET.setText(Integer.toString(outmodes[7]));
            nTwoModeET.setText(Integer.toString(outmodes[8]));
            pTwoModeET.setText(Integer.toString(outmodes[9]));
            mTwoModeET.setVisibility(View.VISIBLE);
            nTwoModeET.setVisibility(View.VISIBLE);
            pTwoModeET.setVisibility(View.VISIBLE);
            mTwoModeTV.setVisibility(View.VISIBLE);
            nTwoModeTV.setVisibility(View.VISIBLE);
            pTwoModeTV.setVisibility(View.VISIBLE);
            twoModeS.setVisibility(View.VISIBLE);
        }
        if(outmodes[10]==1){
            threeModeCheckbox.setChecked(TRUE);
            if(outmodes[11]==0) threeModeS.setSelection(0);
            else threeModeS.setSelection(1);
            mThreeModeET.setText(Integer.toString(outmodes[12]));
            nThreeModeET.setText(Integer.toString(outmodes[13]));
            pThreeModeET.setText(Integer.toString(outmodes[14]));
            mThreeModeET.setVisibility(View.VISIBLE);
            nThreeModeET.setVisibility(View.VISIBLE);
            pThreeModeET.setVisibility(View.VISIBLE);
            mThreeModeTV.setVisibility(View.VISIBLE);
            nThreeModeTV.setVisibility(View.VISIBLE);
            pThreeModeTV.setVisibility(View.VISIBLE);
            threeModeS.setVisibility(View.VISIBLE);
        }
        if(outmodes[15]==1){
            fourModeCheckbox.setChecked(TRUE);
            if(outmodes[16]==0) fourModeS.setSelection(0);
            else fourModeS.setSelection(1);
            mFourModeET.setText(Integer.toString(outmodes[17]));
            nFourModeET.setText(Integer.toString(outmodes[18]));
            pFourModeET.setText(Integer.toString(outmodes[19]));
            mFourModeET.setVisibility(View.VISIBLE);
            nFourModeET.setVisibility(View.VISIBLE);
            pFourModeET.setVisibility(View.VISIBLE);
            mFourModeTV.setVisibility(View.VISIBLE);
            nFourModeTV.setVisibility(View.VISIBLE);
            pFourModeTV.setVisibility(View.VISIBLE);
            fourModeS.setVisibility(View.VISIBLE);
        }
        if(outmodes[20]==1){
            fiveModeCheckbox.setChecked(TRUE);
            if(outmodes[21]==0) fiveModeS.setSelection(0);
            else fiveModeS.setSelection(1);
            mFiveModeET.setText(Integer.toString(outmodes[22]));
            nFiveModeET.setText(Integer.toString(outmodes[23]));
            pFiveModeET.setText(Integer.toString(outmodes[24]));
            mFiveModeET.setVisibility(View.VISIBLE);
            nFiveModeET.setVisibility(View.VISIBLE);
            pFiveModeET.setVisibility(View.VISIBLE);
            mFiveModeTV.setVisibility(View.VISIBLE);
            nFiveModeTV.setVisibility(View.VISIBLE);
            pFiveModeTV.setVisibility(View.VISIBLE);
            fiveModeS.setVisibility(View.VISIBLE);
        }
    }

    public String CheckModesNumber (int TypeOfResonator, int[] outmodes){
        String out = "";
        boolean wrongmodes=false;
        if (TypeOfResonator==1){
            for (int i=0;i<5;i++){
                if (outmodes[0+(5*i)]==1) {
                    if (outmodes[1+(5*i)]==0) {
                        if (outmodes[2 + (5 * i)] == 0 && outmodes[3 + (5 * i)] == 0)
                            wrongmodes = true;
                    }
                    else{
                        if (outmodes[2 + (5 * i)] < 1 || outmodes[3 + (5 * i)] < 1)
                            wrongmodes = true;
                    }
                    if (outmodes[4 + (5 * i)] < 1) wrongmodes = true;
                    if (outmodes[2 + (5 * i)] > 100 || outmodes[3 + (5 * i)] > 100 || outmodes[4 + (5 * i)] > 100)
                        wrongmodes = true;
                }
            }
        }
        else if (TypeOfResonator==2){
            for (int i=0;i<5;i++){
                if (outmodes[0+(5*i)]==1) {
                    if (outmodes[3 + (5 * i)] < 0 ) wrongmodes = true;
                    if (outmodes[2 + (5 * i)] > 10 || outmodes[3 + (5 * i)] > 5 || outmodes[4 + (5 * i)] > 100)
                        wrongmodes = true;
                }
            }
        }
        else if (TypeOfResonator==3){
            for (int i=0;i<5;i++){
                if (outmodes[0+(5*i)]==1) {
                    if (outmodes[2 + (5 * i)] < 1) wrongmodes = true;
                    if (outmodes[2 + (5 * i)] > 100  || outmodes[4 + (5 * i)] > 100)
                        wrongmodes = true;
                }
            }
        }
        else if (TypeOfResonator==4){
            for (int i=0;i<5;i++){
                if (outmodes[0+(5*i)]==1) {
                    if (outmodes[3 + (5 * i)] < 1) wrongmodes = true;
                    if (outmodes[2 + (5 * i)] > 10 || outmodes[3 + (5 * i)] > 5 || outmodes[4 + (5 * i)] > 100)
                        wrongmodes = true;
                }
            }
        }
        else if (TypeOfResonator==5){
            for (int i=0;i<5;i++){
                if (outmodes[0+(5*i)]==1) {
                    if (outmodes[1+(5*i)]==0) {
                        if (outmodes[2 + (5 * i)] == 0 && outmodes[3 + (5 * i)] == 0)
                            wrongmodes = true;
                    }
                    else{
                        if (outmodes[2 + (5 * i)] < 1 || outmodes[3 + (5 * i)] < 1)
                            wrongmodes = true;
                    }
                    if (outmodes[4 + (5 * i)] < 1) wrongmodes = true;
                    if (outmodes[2 + (5 * i)] > 100 || outmodes[3 + (5 * i)] > 100 || outmodes[4 + (5 * i)] > 100)
                        wrongmodes = true;
                }
            }
        }
        if (wrongmodes==true) out="Špatně zvolené vidová čísla, změňtě je!";
        return out;
    }
}
