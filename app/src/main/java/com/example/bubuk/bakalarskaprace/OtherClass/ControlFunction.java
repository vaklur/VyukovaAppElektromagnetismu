package com.example.bubuk.semestralniprace.OtherClass;

import android.widget.EditText;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ControlFunction {
    public boolean checkIsNull(EditText... editTexts){
        for (EditText editText: editTexts){
            if(editText.getText().length() == 0){
                return true;
            }
        }
        return false;
    }

    public String checkIsMaxMin (double[] maxValue,double[] minValue,String[] ValueName,EditText...editTexts) {
        int i = 0;
        boolean k=FALSE;
        boolean l=FALSE;
        String out = "Parametry:\n";
        for (EditText editText : editTexts) {
            if (Double.parseDouble(editText.getText().toString()) >= maxValue[i]) {
                out = out + ValueName[i]+ ",";
                k=TRUE;
            }
            i++;
        }
        if (k==TRUE) out= out + "jsou příliš velké, změntě je.\n";
        i=0;
        for (EditText editText : editTexts) {
            if (Double.parseDouble(editText.getText().toString()) <= minValue[i]) {
                out = out + ValueName[i]+ ",";
                l=TRUE;
            }
            i++;
        }
        if (l==TRUE) out= out + "jsou příliš malé, změntě je.";
        if (k==FALSE && l==FALSE){out="";}
        return out;
    }

}
