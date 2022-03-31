package com.example.bubuk.semestralniprace.Activity.Test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.SharePref;

import com.example.bubuk.semestralniprace.R;


public class TestHistory extends AppCompatActivity {

    final Context results = this;
    Button delhistBTN,delhistyesBTN,delhistnoBTN;
    TextView answerlistTV;
    SharePref share = new SharePref();

    /*create activity*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_history);
        onClickButtonListener();
        createAnswerList();
    }

    /*open delete test history dialog*/
    public void onClickButtonListener () {
        delhistBTN = (Button) findViewById(R.id.delhistoryBTN);
        delhistBTN.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteTestHistory();
                    }

                }
        );
    }

    /*create delete test history dialog*/
    private void deleteTestHistory() {
        final Dialog delhistD = new Dialog(results);
        delhistD.setContentView(R.layout.test_history_delete);
        delhistD.setTitle("Smazání historie");
        delhistyesBTN = (Button) delhistD.findViewById(R.id.delhistoryYesBTN);
        delhistnoBTN = (Button) delhistD.findViewById(R.id.delhistoryNoBTN);
        delhistyesBTN.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] testresults;
                        String[] testresultswrong;
                        int testnumb=0;
                        testresults= new String[] { "", "", "",
                                "","","",
                                "","",""
                        };
                        testresultswrong= new String[] { "", "", "",
                                "","","",
                                "","",""
                        };
                        share.saveArrayS(testresults,"testresults",results);
                        share.saveArrayS(testresultswrong,"testresultsfail",results);
                        share.saveI(testnumb,"testnumb",results);
                        recreate();
                        delhistD.dismiss();

                    }
                }
        );
        delhistnoBTN.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delhistD.dismiss();

                    }
                }
        );
        delhistD.show();

    }

    /*create answer ListView*/
    private void createAnswerList(){
        final String testresults[]= share.loadArray("testresults",results);
        final String testresultswrong[]= share.loadArray("testresultsfail",results);

        ArrayAdapter adapter = new ArrayAdapter< >(this, R.layout.test_history_lv, testresults);

        ListView listView = (ListView) findViewById(R.id.historyLV);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog answerlist = new Dialog(results);
                answerlist.setContentView(R.layout.test_history_answerlist);
                answerlist.setTitle(testresults[position]);
                answerlistTV = (TextView) answerlist.findViewById(R.id.thAnswerlistTV);
                answerlistTV.setText(Html.fromHtml(testresultswrong[position]));
                answerlist.show();
            }
        });


    }

}
