package com.example.bubuk.semestralniprace.Activity.Examples;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.R;

public class ExamplesAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public ExamplesAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.examplelist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.examplelist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.examplesTV);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.examplesIV);


        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;

    }
}