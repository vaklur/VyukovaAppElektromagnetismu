package com.example.bubuk.semestralniprace.Activity.Examples.Waveguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.R;

public class WaveguideTheory extends AppCompatActivity {

    TextView Theory1,Theory2,Theory3,HyperLink;
    Spanned Text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveguide_theory);

        initcontrol();

        String theory1s = getString(R.string.WaveguideTheory1);
        String theory2s = getString(R.string.WaveguideTheory2);
        String theory3s = getString(R.string.WaveguideTheory3);


        Text = Html.fromHtml("Další teorii k vlnovodům najdete " +
                "<a href='https://en.wikipedia.org/wiki/Waveguide_(electromagnetism)'>zde</a>.");
        Theory1.setText(Html.fromHtml(theory1s));
        Theory2.setText(Html.fromHtml(theory2s));
        Theory3.setText(Html.fromHtml(theory3s));

        HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink.setText(Text);
    }

    public void initcontrol(){
        HyperLink = (TextView)findViewById(R.id.waveguideTheoryTV5);
        Theory1 = (TextView)findViewById(R.id.waveguideTheoryTV2);
        Theory2 = (TextView)findViewById(R.id.waveguideTheoryTV3);
        Theory3 = (TextView)findViewById(R.id.waveguideTheoryTV4);

    }
}
