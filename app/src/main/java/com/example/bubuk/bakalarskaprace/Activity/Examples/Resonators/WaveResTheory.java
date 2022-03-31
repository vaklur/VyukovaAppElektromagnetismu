package com.example.bubuk.semestralniprace.Activity.Examples.Resonators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.R;

public class WaveResTheory extends AppCompatActivity {

    TextView Theory1,Theory2,Theory3, Theory4,HyperLink;
    Spanned Text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_theory);
        initcontrol();

        String theory1s = getString(R.string.WaveResTheory1);
        String theory2s = getString(R.string.WaveResTheory2);
        String theory3s = getString(R.string.WaveResTheory3);
        String theory4s = getString(R.string.WaveResTheory4);


        Text = Html.fromHtml("Další teorii k vlnovým rezonátorům najdete " +
                "<a href='https://en.wikipedia.org/wiki/Microwave_cavity'>zde</a>.");
        Theory1.setText(Html.fromHtml(theory1s));
        Theory2.setText(Html.fromHtml(theory2s));
        Theory3.setText(Html.fromHtml(theory3s));
        Theory4.setText(Html.fromHtml(theory4s));

        HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink.setText(Text);
    }

    public void initcontrol(){
        HyperLink = (TextView)findViewById(R.id.waveresonatorTheoryTV6);
        Theory1 = (TextView)findViewById(R.id.waveresonatorTheoryTV2);
        Theory2 = (TextView)findViewById(R.id.waveresonatorTheoryTV3);
        Theory3 = (TextView)findViewById(R.id.waveresonatorTheoryTV4);
        Theory4 = (TextView)findViewById(R.id.waveresonatorTheoryTV5);

    }
}