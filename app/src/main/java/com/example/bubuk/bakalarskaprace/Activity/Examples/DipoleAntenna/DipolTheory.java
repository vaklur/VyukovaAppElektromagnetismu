package com.example.bubuk.semestralniprace.Activity.Examples.DipoleAntenna;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.R;

public class DipolTheory extends AppCompatActivity {

    TextView theory1,theory2,hyperLink;
    Spanned link;

    /* create activity */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipoltheory);

        initcontrol();

        String theory1s = getString(R.string.DipolTheory);
        String theory2s = getString(R.string.DipolTheory1);

        theory1.setText(Html.fromHtml(theory1s));
        theory2.setText(Html.fromHtml(theory2s));
        link = Html.fromHtml("Další teorii ohledně vyzařování dipólu najdete " + "<a href='https://en.wikipedia.org/wiki/Dipole_antenna'>zde</a>.");

        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink.setText(link);
    }

    /* initialize widgets */
    public void initcontrol(){
        hyperLink = (TextView)findViewById(R.id.dipolTheoryTV4);
        theory1 = (TextView)findViewById(R.id.dipolTheoryTV2);
        theory2 = (TextView)findViewById(R.id.dipolTheoryTV3);

    }
}
