package com.example.bubuk.semestralniprace.Activity.Examples.FresnelElipsoid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.R;

public class FresnelElipsoidTheory extends AppCompatActivity {

    TextView hyperLink, aboutpicture, theory;
    Spanned link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresnel_elipsoid_theory);

        initcontrol();

        String aboutpict = getString(R.string.FresnelTheory1);
        String theory1 = getString(R.string.FresnelTheory);

        link = Html.fromHtml("Více teorie k Fresnelovým zónám najdete " + "<a href='https://en.wikipedia.org/wiki/Fresnel_zone'>zde</a>.");
        aboutpicture.setText(Html.fromHtml(aboutpict));
        theory.setText(Html.fromHtml(theory1));

        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink.setText(link);
    }

    public void initcontrol(){
        theory = (TextView) findViewById(R.id.fresnelTheoryTV2);
        aboutpicture = (TextView) findViewById(R.id.fresnelTheoryTV3);
        hyperLink = (TextView) findViewById(R.id.fresnelTheoryTV4);

    }
}
