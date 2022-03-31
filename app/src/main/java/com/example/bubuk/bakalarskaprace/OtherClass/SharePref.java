package com.example.bubuk.semestralniprace.OtherClass;

import android.content.Context;
import android.content.SharedPreferences;


public class SharePref {
    public boolean saveI(Integer number, String arrayName, Context mContext) {
        android.content.SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName , number);
        return editor.commit();
    }

    public boolean saveArrayS (String[] array, String arrayName, Context mContext) {
        android.content.SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public boolean saveArrayD (double[] arrayd, String arrayName, Context mContext) {
        String[] array = new String[arrayd.length];
        for(int i=0;i<arrayd.length;i++)
            array[i] = Double.toString(arrayd[i]);
        android.content.SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public double[] loadArrayD (String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "size", 0);
        String array[] = new String[size];
        double arrayd[] = new double[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        for(int i=0;i<size;i++)
            arrayd[i]=Double.parseDouble(array[i]);
        return arrayd;
    }


    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }


    public boolean saveArrayI(int [] array, String arrayName, Context mContext) {
        android.content.SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putInt(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public int [] loadArrayI (String arrayName, Context mContext) {
        android.content.SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "size", 0);
        int arrayint[] = new int [size];
        for(int i=0;i<size;i++)
            arrayint[i] = prefs.getInt(arrayName + "_" + i,0);
        return arrayint;
    }

    public Integer loadI(String arrayName, Context mContext) {
        android.content.SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int number = prefs.getInt(arrayName, 0);
        return number;
    }

}
