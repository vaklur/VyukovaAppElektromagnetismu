package com.example.bubuk.semestralniprace.Activity.Examples.PowerLine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.R;

public class ReflectionTheory extends AppCompatActivity{

    TextView Theory1,Theory2,HyperLink;
    Spanned Text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflection_theory);

        initcontrol();

        String theory1s = getString(R.string.ReflectionTheory);
        String theory2s = getString(R.string.ReflectionTheory1);

        Text = Html.fromHtml("Další teorii k odrazům na vedení najdete " +
                "<a href='https://en.wikipedia.org/wiki/Transmission_line'>zde</a>.");
        Theory1.setText(Html.fromHtml(theory1s));
        Theory2.setText(Html.fromHtml(theory2s));

        HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink.setText(Text);
    }

    public void initcontrol(){
        HyperLink = (TextView)findViewById(R.id.reflectionTheoryTV4);
        Theory1 = (TextView)findViewById(R.id.reflectionTheoryTV2);
        Theory2 = (TextView)findViewById(R.id.reflectionTheoryTV3);
    }
}
