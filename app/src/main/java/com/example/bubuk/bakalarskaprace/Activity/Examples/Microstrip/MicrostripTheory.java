package com.example.bubuk.semestralniprace.Activity.Examples.Microstrip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.R;


public class MicrostripTheory extends AppCompatActivity {

    TextView hyperlink, theory;
    Spanned link;

    /* create activity*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microstrip_info);

        initcontrol();

        String theory1 = getString(R.string.MicrostripInfo);

        link = Html.fromHtml("Vztahy použité při výpočtech a teorii ohledně návrhu nesymetrickkého mikropáskové vedení najdete " + "<a href='http://qucs.sourceforge.net/tech/node75.html#SECTION001213100000000000000'>zde</a>.");

        theory.setText(Html.fromHtml(theory1));
        hyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperlink.setText(link);

    }

    /* initialize widgets*/
    public void initcontrol(){
        hyperlink = (TextView) findViewById(R.id.linkMicrostripTV);
        theory = (TextView) findViewById(R.id.infoMikrostripTV2);
    }
}
