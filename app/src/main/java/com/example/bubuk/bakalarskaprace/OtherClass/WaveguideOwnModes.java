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

import static java.lang.Boolean.TRUE;



public class WaveguideOwnModes {
    Dialog ModesDialog;
    SharePref pref = new SharePref();
    ControlFunction control = new ControlFunction();

    CheckBox oneModeCheckbox,twoModeCheckbox,threeModeCheckbox,fourModeCheckbox,fiveModeCheckbox;
    EditText mOneModeET,nOneModeET,mTwoModeET,nTwoModeET,mThreeModeET,nThreeModeET,mFourModeET,nFourModeET,mFiveModeET,nFiveModeET;
    TextView mOneModeTV,nOneModeTV,mTwoModeTV,nTwoModeTV,mThreeModeTV,nThreeModeTV,mFourModeTV,nFourModeTV,mFiveModeTV,nFiveModeTV;
    Spinner oneModeS,twoModeS,threeModeS,fourModeS,fiveModeS;

    Button modesSet;



    public void SetOwnModes(final int [] outmodes, final Context mContext, final String preference, final int TypeOfWaveguide){
        /*type resonator value:
        * 1 - Rectangular     TE,TM modes: m=0,1,2.. n=1,2,3.. .. or m=1,2,3.. n=0,1,2,3..
        * 2 - Circular        TE,TM modes: m=0,1,2.. n=1,2,3.. Max m=10 n=5!
        * */
        ModesDialog = new Dialog(mContext);
        ModesDialog.setContentView(R.layout.waveguide_modes);
        ModesDialog.setTitle("Zvolení vlastních vidů");
        initWidgets();

        String[] modes = new String[]{"TE","TM"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,modes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //1------------------------------------------------------------------------
        oneModeS.setAdapter(spinnerArrayAdapter);
        twoModeS.setAdapter(spinnerArrayAdapter);
        threeModeS.setAdapter(spinnerArrayAdapter);
        fourModeS.setAdapter(spinnerArrayAdapter);
        fiveModeS.setAdapter(spinnerArrayAdapter);
        preSet(outmodes);
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
                    mOneModeTV.setVisibility(View.VISIBLE);
                    nOneModeTV.setVisibility(View.VISIBLE);
                    oneModeS.setVisibility(View.VISIBLE);
                    outmodes[0]=1;

                } else {
                    mOneModeET.setVisibility(View.GONE);
                    nOneModeET.setVisibility(View.GONE);
                    mOneModeTV.setVisibility(View.GONE);
                    nOneModeTV.setVisibility(View.GONE);
                    oneModeS.setVisibility(View.GONE);
                    outmodes[0]=0;

                }
            }
        });
        //2------------------------------------------------------------------------

        twoModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[5]=0;
                if (position == 1) outmodes[5]=1;
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
                    mTwoModeTV.setVisibility(View.VISIBLE);
                    nTwoModeTV.setVisibility(View.VISIBLE);
                    twoModeS.setVisibility(View.VISIBLE);
                    outmodes[4]=1;

                } else {
                    mTwoModeET.setVisibility(View.GONE);
                    nTwoModeET.setVisibility(View.GONE);
                    mTwoModeTV.setVisibility(View.GONE);
                    nTwoModeTV.setVisibility(View.GONE);
                    twoModeS.setVisibility(View.GONE);
                    outmodes[4]=0;

                }
            }
        });
        //3------------------------------------------------------------------------

        threeModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[9]=0;
                if (position == 1) outmodes[9]=1;
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
                    mThreeModeTV.setVisibility(View.VISIBLE);
                    nThreeModeTV.setVisibility(View.VISIBLE);
                    threeModeS.setVisibility(View.VISIBLE);
                    outmodes[8]=1;

                } else {
                    mThreeModeET.setVisibility(View.GONE);
                    nThreeModeET.setVisibility(View.GONE);
                    mThreeModeTV.setVisibility(View.GONE);
                    nThreeModeTV.setVisibility(View.GONE);
                    threeModeS.setVisibility(View.GONE);
                    outmodes[8]=0;

                }
            }
        });
        //4------------------------------------------------------------------------

        fourModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[13]=0;
                if (position == 1) outmodes[13]=1;
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
                    mFourModeTV.setVisibility(View.VISIBLE);
                    nFourModeTV.setVisibility(View.VISIBLE);
                    fourModeS.setVisibility(View.VISIBLE);
                    outmodes[12]=1;

                } else {
                    mFourModeET.setVisibility(View.GONE);
                    nFourModeET.setVisibility(View.GONE);
                    mFourModeTV.setVisibility(View.GONE);
                    nFourModeTV.setVisibility(View.GONE);
                    fourModeS.setVisibility(View.GONE);
                    outmodes[12]=0;

                }
            }
        });
        //5------------------------------------------------------------------------

        fiveModeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) outmodes[17]=0;
                if (position == 1) outmodes[17]=1;
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
                    mFiveModeTV.setVisibility(View.VISIBLE);
                    nFiveModeTV.setVisibility(View.VISIBLE);
                    fiveModeS.setVisibility(View.VISIBLE);
                    outmodes[16]=1;

                } else {
                    mFiveModeET.setVisibility(View.GONE);
                    nFiveModeET.setVisibility(View.GONE);
                    mFiveModeTV.setVisibility(View.GONE);
                    nFiveModeTV.setVisibility(View.GONE);
                    fiveModeS.setVisibility(View.GONE);
                    outmodes[16]=0;

                }
            }
        });
        modesSet.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean empty = control.checkIsNull(mOneModeET,nOneModeET,mTwoModeET,nTwoModeET,mThreeModeET,nThreeModeET,mFourModeET,nFourModeET,mFiveModeET,nFiveModeET);
                if (empty == false) {
                    if (outmodes[0] == 1) {
                        outmodes[2] = Integer.parseInt(mOneModeET.getText().toString());
                        outmodes[3] = Integer.parseInt(nOneModeET.getText().toString());
                    } else {
                        outmodes[2] = -1;
                        outmodes[3] = -1;
                    }
                    if (outmodes[4] == 1) {
                        outmodes[6] = Integer.parseInt(mTwoModeET.getText().toString());
                        outmodes[7] = Integer.parseInt(nTwoModeET.getText().toString());
                    } else {
                        outmodes[6] = -1;
                        outmodes[7] = -1;
                    }
                    if (outmodes[8] == 1) {
                        outmodes[10] = Integer.parseInt(mThreeModeET.getText().toString());
                        outmodes[11] = Integer.parseInt(nThreeModeET.getText().toString());
                    } else {
                        outmodes[10] = -1;
                        outmodes[10] = -1;
                    }
                    if (outmodes[12] == 1) {
                        outmodes[14] = Integer.parseInt(mFourModeET.getText().toString());
                        outmodes[15] = Integer.parseInt(nFourModeET.getText().toString());
                    } else {
                        outmodes[14] = -1;
                        outmodes[15] = -1;
                    }
                    if (outmodes[16] == 1) {
                        outmodes[18] = Integer.parseInt(mFiveModeET.getText().toString());
                        outmodes[19] = Integer.parseInt(nFiveModeET.getText().toString());
                    } else {
                        outmodes[18] = -1;
                        outmodes[19] = -1;
                    }
                String wrongModes = CheckModesNumber(TypeOfWaveguide,outmodes);
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
        oneModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmModeOneCB);
        mOneModeET = (EditText) ModesDialog.findViewById(R.id.wmModeOneMET);
        nOneModeET = (EditText) ModesDialog.findViewById(R.id.wmModeOneNET);
        mOneModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeOneTV);
        nOneModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeOneTV1);
        oneModeS = (Spinner) ModesDialog.findViewById(R.id.wmModeOneS);

        twoModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmModeTwoCB);
        mTwoModeET = (EditText) ModesDialog.findViewById(R.id.wmModeTwoMET);
        nTwoModeET = (EditText) ModesDialog.findViewById(R.id.wmModeTwoNET);
        mTwoModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeTwoTV);
        nTwoModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeTwoTV1);
        twoModeS = (Spinner) ModesDialog.findViewById(R.id.wmModeTwoS);

        threeModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmModeThreeCB);
        mThreeModeET = (EditText) ModesDialog.findViewById(R.id.wmModeThreeMET);
        nThreeModeET = (EditText) ModesDialog.findViewById(R.id.wmModeThreeNET);
        mThreeModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeThreeTV);
        nThreeModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeThreeTV1);
        threeModeS = (Spinner) ModesDialog.findViewById(R.id.wmModeThreeS);

        fourModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmModeFourCB);
        mFourModeET = (EditText) ModesDialog.findViewById(R.id.wmModeFourMET);
        nFourModeET = (EditText) ModesDialog.findViewById(R.id.wmModeFourNET);
        mFourModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeFourTV);
        nFourModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeFourTV1);
        fourModeS = (Spinner) ModesDialog.findViewById(R.id.wmModeFourS);

        fiveModeCheckbox = (CheckBox) ModesDialog.findViewById(R.id.wmModeFiveCB);
        mFiveModeET = (EditText) ModesDialog.findViewById(R.id.wmModeFiveMET);
        nFiveModeET = (EditText) ModesDialog.findViewById(R.id.wmModeFiveNET);
        mFiveModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeFiveTV);
        nFiveModeTV = (TextView)  ModesDialog.findViewById(R.id.wmModeFiveTV1);
        fiveModeS = (Spinner) ModesDialog.findViewById(R.id.wmModeFiveS);


        modesSet = (Button) ModesDialog.findViewById(R.id.wmModeSetBTN);
    }

    private void preSet(int [] outmodes){
        if(outmodes[0]==1){
            oneModeCheckbox.setChecked(TRUE);
            if(outmodes[1]==0) oneModeS.setSelection(0);
            else oneModeS.setSelection(1);
            mOneModeET.setText(Integer.toString(outmodes[2]));
            nOneModeET.setText(Integer.toString(outmodes[3]));
            mOneModeET.setVisibility(View.VISIBLE);
            nOneModeET.setVisibility(View.VISIBLE);
            mOneModeTV.setVisibility(View.VISIBLE);
            nOneModeTV.setVisibility(View.VISIBLE);
            oneModeS.setVisibility(View.VISIBLE);
        }
        if(outmodes[4]==1){
            twoModeCheckbox.setChecked(TRUE);
            if(outmodes[5]==0) twoModeS.setSelection(0);
            else twoModeS.setSelection(1);
            mTwoModeET.setText(Integer.toString(outmodes[6]));
            nTwoModeET.setText(Integer.toString(outmodes[7]));
            mTwoModeET.setVisibility(View.VISIBLE);
            nTwoModeET.setVisibility(View.VISIBLE);
            mTwoModeTV.setVisibility(View.VISIBLE);
            nTwoModeTV.setVisibility(View.VISIBLE);
            twoModeS.setVisibility(View.VISIBLE);
        }
        if(outmodes[8]==1){
            threeModeCheckbox.setChecked(TRUE);
            if(outmodes[9]==0) threeModeS.setSelection(0);
            else threeModeS.setSelection(1);
            mThreeModeET.setText(Integer.toString(outmodes[10]));
            nThreeModeET.setText(Integer.toString(outmodes[11]));
            mThreeModeET.setVisibility(View.VISIBLE);
            nThreeModeET.setVisibility(View.VISIBLE);
            mThreeModeTV.setVisibility(View.VISIBLE);
            nThreeModeTV.setVisibility(View.VISIBLE);
            threeModeS.setVisibility(View.VISIBLE);
        }
        if(outmodes[12]==1){
            fourModeCheckbox.setChecked(TRUE);
            if(outmodes[13]==0) fourModeS.setSelection(0);
            else fourModeS.setSelection(1);
            mFourModeET.setText(Integer.toString(outmodes[14]));
            nFourModeET.setText(Integer.toString(outmodes[15]));
            mFourModeET.setVisibility(View.VISIBLE);
            nFourModeET.setVisibility(View.VISIBLE);
            mFourModeTV.setVisibility(View.VISIBLE);
            nFourModeTV.setVisibility(View.VISIBLE);
            fourModeS.setVisibility(View.VISIBLE);
        }
        if(outmodes[16]==1){
            fiveModeCheckbox.setChecked(TRUE);
            if(outmodes[17]==0) fiveModeS.setSelection(0);
            else fiveModeS.setSelection(1);
            mFiveModeET.setText(Integer.toString(outmodes[18]));
            nFiveModeET.setText(Integer.toString(outmodes[19]));
            mFiveModeET.setVisibility(View.VISIBLE);
            nFiveModeET.setVisibility(View.VISIBLE);
            mFiveModeTV.setVisibility(View.VISIBLE);
            nFiveModeTV.setVisibility(View.VISIBLE);
            fiveModeS.setVisibility(View.VISIBLE);
        }
    }

    public String CheckModesNumber (int TypeOfResonator, int[] outmodes){
        String out = "";
        boolean wrongmodes=false;
        if (TypeOfResonator==1){
            for (int i=0;i<5;i++){
                if (outmodes[0+(4*i)]==1) {
                    if (outmodes[1+(4*i)]==0) {
                        if (outmodes[2 + (4 * i)] == 0 && outmodes[3 + (4 * i)] == 0)
                            wrongmodes = true;
                        }
                    else{
                        if (outmodes[2 + (4 * i)] < 1 || outmodes[3 + (4 * i)] < 1)
                            wrongmodes = true;
                        }
                    if (outmodes[2 + (4 * i)] > 100 || outmodes[3 + (4 * i)] > 100)
                        wrongmodes = true;
                }
            }

        }
        else if (TypeOfResonator==2){
            for (int i=0;i<5;i++){
                if (outmodes[0+(5*i)]==1) {
                    if (outmodes[3 + (4 * i)] < 0 ) wrongmodes = true;
                    if (outmodes[2 + (4 * i)] > 10 || outmodes[3 + (4 * i)] > 5 ) wrongmodes = true;
                }
            }
        }
        if (wrongmodes==true) out="Špatně zvolené vidová čísla, změňtě je!";
        return out;
    }
}
