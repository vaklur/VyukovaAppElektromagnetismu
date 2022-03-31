package com.example.bubuk.semestralniprace.Activity.Test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class TestRun extends AppCompatActivity {

    Button selectBTN, resultsBTN;
    RadioGroup answerRG;
    TextView question,resulttext;
    RadioButton answer1,answer2,answer3,answer4;
    final Context results = this;
    Random randnumb = new Random();
    SharePref share = new SharePref();

    String[] testresults;
    String [] testresultsfail;
    String [] failed = new String[50];
    String outfail = "<b>Nesprávně zodpovězené otázky:</b>";
    /*questions*/
    String[][] answers = {  {"Činitel odrazu může nabývat hodnot?","Hodnot, které leží v intervalu <0;1>.","Hodnot, které leží v intervalu <1;∞).","Hodnot, které leží v intervalu <-1;1>.","Hodnot, které leží v intervalu <0; ∞)."},     //String array of answers
                            {"Činitel odrazu impedančně přizpůsobeného vedení je roven?","ρ = 0","ρ = 1","ρ = 10","ρ = 3,14"},
                            {"Pro impedančně přizpůsobené vedení musí platit:","Impedance zátěže ZL je stejná jako impedance vedení Z0.","Impedance zátěže ZL je odlišná od impedance vedení Z0.","Impedance zátěže ZL je dvakrát větší než impedance vedení Z0.","Impedance zátěže ZL je mnohokrát větší než impedance vedení Z0."},
                            {"Poměr stojatých vln PSV může nabývat hodnot?","Hodnot, které leží v intervalu <0;1>.","Hodnot, které leží v intervalu <1;∞).","Hodnot, které leží v intervalu <-1;1>.","Hodnot, které leží v intervalu <0; ∞)."},
                            {"Poměr stojatých vln PSV je pro impedančně přizpůsobené vedení roven?","PSV = 0","PSV = 1","PSV = 0,5","PSV = ∞"},
                            {"Činitel odrazu je definován jako:","Poměr vlny odražené a původní.","Součet vlny odražené a původní.","Poměr vlny původní a odražené.","Poměr vlny původní a impedance zátěže Zk."},
                            {"Pro maximální přenos energie po vedení musí být vedení?","Impedančně přizpůsobené.","Stejně dlouhé jaká je délka šířící se vlny.","Ztrátové.","Co nejkratší."},
                            {"Poměr stojatých vln PSV je určen jako poměr:","Napětí v kmitně a napětí v uzlu.","Činitele odrazu na konci a činitele odrazu na začátku vedení.","Proudu v uzlu a proudu na zátěži.","Impedance zátěže ZL a impedance vedení Z0."},
                            {"Činitel zkrácení na vedení udává:","Poměr délky vlny na vedení k délce vlny ve vakuu.","Poměr délky našeho vedení k vedení o délce jeden metr.","O kolik se zkrátí nebo prodlouží vedení vlivem teploty.","Poměr impedance zátěže a impedance vedení."},
                            {"Dipólová anténa nevyzařuje:","Ve směru své osy.","Ve směru kolmém na osu dipólu.","Ve vakuu.","Rotačně souměrně, kde osou symetrie je osa dipólu."},
                            {"První Fresnelova zóna nám ohraničuje zónu:","Ve které by se neměli nacházet žádné překážky, aby se signál mohl šířit mezi dvěma anténami s maximální energií.","Ve které se nesmí měnit parametry prostředí, které se nachází mezi dvěma anténami.","Ve které se mění pouze teplota vzduchu a tlak zůstává stejný.","Která je důležitá pro instalaci optických spojů, které přenáší velké množství dat."},
                            {"První Fresnelova zóna má tvar:","Elipsoidu, který obklopuje obě antény.","Válce, který obklopuje obě antény.","Koule, která obklopuje obě antény.","Kužele, který obklopuje obě antény."},
                            {"V ideálním vlnovodu se mohou bez útlumu šířit signály, jejichž kmitočet leží:","V pásmu propustnosti.","V pásmu nepropustnosti.","V pásmu ideálního přenosu.","V pásmu polopropustnosti."},
                            {"Šíření elektromagnetické vlny uvnitř vlnovodu lze popsat:","Paprsky šířícími se rovnoběžně se stěnami vlnovodu.","Postupnými odrazy šířící se vlny od pláště vlnovodu.","Difrakcí šířící se vlny o plášť vlnovodu.","Difúzí elektromagnetické energie uvnitř vlnovodu."},
                            {"Elektromagnetická vlna se šíří vlnovodem:","Je-li kmitočet vlny vyšší než kmitočet kritický.","Je-li kmitočet vlny nižší než kmitočet kritický.","Vždy, nezávisle na kmitočtu vlny.","Je-li délky vlny větší než kritická vlnová délka."},
                            {"Mezní kmitočet vlnovodu je:","Nejvyšší kmitočet vlny, která se šíří vlnovodem.","Nejnižší kmitočet vlny, která se šíří vlnovodem.","Kmitočet, při kterém vzniknou ve vlnovodu netlumené oscilace.","Kmitočet, na kterém má vlnovod nejvyšší útlum."},
                            {"Vlnovody jsou používány na vyšších kmitočtech, protože:","Délka vlnovodu souvisí s vlnovou délkou.","Ztráty v kovovém plášti jsou na nižších kmitočtech příliš velké.","Příčné rozměry souvisí s vlnovou délkou.","Na nižších kmitočtech mají vlnovody velmi velký útlum. "},
                            {"Přenos energie vlnovodem popisuje:","Fázová rychlost.","Rychlost světla.","Skupinová rychlost.","Rychlost zvuku."},
                            {"Délka vlny ve vlnovodu je:","Stejná jako délka vlny vstupující do vlnovodu.","Větší než délka vlny, která vstupuje do vlnovodu.","Menší než délka vlny, která vstupuje do vlnovodu.","Větší nebo stejná jako délka vlny, která vstupuje do vlnovodu."},
                            {"Elektromagnetické vlny se vlnovodem šíří vlnovodem různými způsoby (vidy). Při zvyšování kmitočtu:","Se vlnovodem šíří stále víc vidů.","Se vlnovodem šíří jediný vid.","Se vlnovodem šíří stále méně vidů.","Se vlnovodem přestanou šířit elektromagnetické vlny."},
                            {"Dominantním videm je vid:","S nejnižším pracovním kmitočtem.","S nejvyšší pracovním kmitočtem.","Který se šíří vlnovodem při libovolném kmitočtu.","S největšími vidovými čísly."},
                            {"Jednotlivé vidy mají:","Stejnou fázovou rychlost.","Stejnou skupinovou rychlost.","Mají různé rozložený elektrického a magnetické pole ve vlnovodu.","Stejnou charakteristickou impedanci."},
                            {"Při přenosu signálu vlnovodem nejčastěji pracujeme:","V pásmu jednovidovosti.","V pásmu nepropustnosti.","V pásmu, kde vlnovod vykazuje nejmenší charakteristickou impedanci.","V pásmu, kdy se vlnovodem šíří vlny magnetická i elektrická."},
                            {"Pro delší cm a dm vlny, se kvůli rozměrům nejčastěji využívá:","Vlnovod obdélníkového průřezu.","Vlnovod kruhové průřezu.","Koaxiální vedení.","Vlnovod čtvercového průřezu."},
                            {"V pásmu propustnosti je útlum vlnovodu:","Stejný jako v pásmu nepropustnosti.","Výrazně menší než v pásmu nepropustnosti.","Závislí pouze na kmitočtu šířící se vlny.","Výrazně větší než v pásmu nepropustnosti."},
                            {"Mikropáskovým vedením se šíří vlna:","HEM – hybridní elektromagnetická vlna.","TEM – tranzverzálně elektromagnetická vlna.","TE – příčně elektrická vlna.","TM – příčně magnetická vlna."},
                            {"Směrová charakteristika antény nám udává:","Rozložení vysílaného výkonu v prostoru okolo antény.","Jak je umístěná anténa v prostoru.","Rozložení magnetického pole okolo antény.","Rozložení elektrického pole okolo antény."},
                            {"Základními parametry rezonátoru nejsou:","Kvalita rezonátoru","Rezonanční kmitočet","Činitel jakosti","Rozměry rezonátoru"},
                            {"Čím se vyznačují rotačně symetrické vidy?","Vidy, které mají první vidové číslo m nulové.","Vidy, které mají obě vidová čísla nulová.","Vidy, které mají první vidové číslo n nulové.","Vidy, které mají mají obě vidová čísla stejné."},
                            {"Dominantním videm v koaxiální vlnovodu je vid:","TEM","TE10","TE11","TM10"},
                            {"Otazka31","ano", "ne", "ne","ne"},
                            {"Otazka32","ano", "ne", "ne","ne"},
                            {"Otazka33","ano", "ne", "ne","ne"},
                            {"Otazka34","ano", "ne", "ne","ne"},
                            {"Otazka35","ano", "ne", "ne","ne"},
                            {"Otazka36","ano", "ne", "ne","ne"},
                            {"Otazka37","ano", "ne", "ne","ne"},
                            {"Otazka38","ano", "ne", "ne","ne"},
                            {"Otazka39","ano", "ne", "ne","ne"},
                            {"Otazka40","ano", "ne", "ne","ne"},
                            {"Otazka41","ano", "ne", "ne","ne"},
                            {"Otazka42","ano", "ne", "ne","ne"},
                            {"Otazka43","ano", "ne", "ne","ne"},
                            {"Otazka44","ano", "ne", "ne","ne"},
                            {"Otazka45","ano", "ne", "ne","ne"},
                            {"Otazka46","ano", "ne", "ne","ne"},
                            {"Otazka47","ano", "ne", "ne","ne"},
                            {"Otazka48","ano", "ne", "ne","ne"},
                            {"Otazka49","ano", "ne", "ne","ne"},
                            {"Otazka50","ano", "ne", "ne","ne"}
                        };
    /*Position of right answer for used question*/
    int [] answerscontrol = {1,1,1,2,2,1,1,1,1,1,
                            1,1,1,2,1,2,3,3,2,1,
                            1,3,1,3,2,1,1,1,1,1,
                            1,1,1,1,1,1,1,1,1,1,
                            1,1,1,1,1,1,1,1,1,1};
    int [] usequestion = new int [50];      // check against repeating questions
    boolean cntrl=false;                    // control variable
    int questnumb=0;                        // question number
    int usequestnumb=0;                     // number of all used questions
    int allquestnumb=30;                    // number of all questions
    int choosequestnumb;                    // number of question, which be answered
    int check=0;                            // variable for check right/wrong answer
    int right=0;                            // number of right answered questions
    int wrong=0;                            // number of wrong answered questions


    /*create activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_run);
        testRun();

        Arrays.fill(usequestion,-1);
        Arrays.fill(failed,"");

        choosequestnumb=share.loadI("choosequestnumb",results);


        /* set first question*/
        questnumb = randnumb.nextInt(allquestnumb);
        question = (TextView) findViewById(R.id.questionTV);
        question.setText(answers[questnumb][0]);
        answer1 = (RadioButton) findViewById(R.id.aoneRBTN);
        answer1.setText(answers[questnumb][1]);
        answer2 = (RadioButton) findViewById(R.id.atwoRBTN);
        answer2.setText(answers[questnumb][2]);
        answer3 = (RadioButton) findViewById(R.id.athreeRBTN);
        answer3.setText(answers[questnumb][3]);
        answer4 = (RadioButton) findViewById(R.id.afourRBTN);
        answer4.setText(answers[questnumb][4]);
        usequestion [usequestnumb] = questnumb;
        selectBTN.setText("Zkontroluj odpověď");
        cntrl=true;
    }

    /* check question ant go to next question onClick listener*/
    public void testRun () {
        selectBTN = (Button) findViewById(R.id.selectBTN);
        selectBTN.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (usequestnumb<choosequestnumb){

                            /*check which radiobutton is checked*/
                            answerRG = (RadioGroup) findViewById(R.id.aRG);
                            int checkrg = answerRG.getCheckedRadioButtonId();
                            if (checkrg==answer1.getId())    check=1;
                            if (checkrg==answer2.getId())    check=2;
                            if (checkrg==answer3.getId())    check=3;
                            if (checkrg==answer4.getId())    check=4;


                            /*go to the next question*/
                            if (cntrl==false){

                                int n=0;

                                /*random question generator*/
                                while(n<=1){
                                    questnumb = randnumb.nextInt(allquestnumb);
                                    n=1;
                                   for (int i=0;i<allquestnumb;i++){
                                        if(questnumb==usequestion[i]){
                                            n--;
                                        }
                                    }
                                    if (n==1) n=5;
                                }

                                usequestion [usequestnumb] =questnumb;

                                /*set new question*/
                                question.setText(answers[questnumb][0]);
                                answer1.setText(answers[questnumb][1]);
                                answer1.setBackgroundColor(Color.WHITE);
                                answer2.setText(answers[questnumb][2]);
                                answer2.setBackgroundColor(Color.WHITE);
                                answer3.setText(answers[questnumb][3]);
                                answer3.setBackgroundColor(Color.WHITE);
                                answer4.setText(answers[questnumb][4]);
                                answer4.setBackgroundColor(Color.WHITE);

                                cntrl=true;
                                selectBTN.setText("Zkontroluj odpověď");
                            }
                            /* check if the question is right/wrong*/
                            else if (cntrl==true){
                                /* right answer*/
                                if(check==answerscontrol[questnumb]){
                                    if (check==1) answer1.setBackgroundColor(Color.GREEN);
                                    if (check==2) answer2.setBackgroundColor(Color.GREEN);
                                    if (check==3) answer3.setBackgroundColor(Color.GREEN);
                                    if (check==4) answer4.setBackgroundColor(Color.GREEN);
                                    right++;
                                }

                                /*wrong answer*/
                                else{
                                    if (check==1) answer1.setBackgroundColor(Color.RED);
                                    if (check==2) answer2.setBackgroundColor(Color.RED);
                                    if (check==3) answer3.setBackgroundColor(Color.RED);
                                    if (check==4) answer4.setBackgroundColor(Color.RED);

                                    if (answerscontrol[questnumb]==1) answer1.setBackgroundColor(Color.GRAY);
                                    if (answerscontrol[questnumb]==2) answer2.setBackgroundColor(Color.GRAY);
                                    if (answerscontrol[questnumb]==3) answer3.setBackgroundColor(Color.GRAY);
                                    if (answerscontrol[questnumb]==4) answer4.setBackgroundColor(Color.GRAY);

                                    /*save wrong answered question*/
                                    failed[wrong]= "<br><br><b>"+(wrong+1)+". Otázka:</b><br>"+answers[questnumb][0]+
                                                    "<br><b>Vaše odpověd:</b><br>"+answers[questnumb][check]+
                                                    "<br><b>Správná odpověď:</b><br>"+answers[questnumb][answerscontrol[questnumb]];
                                    wrong++;

                                }

                                if (usequestnumb<choosequestnumb-1) selectBTN.setText("Další otázka");
                                else selectBTN.setText("Výsledky");

                                cntrl=false;
                                usequestnumb++;
                            }
                        }

                        /* test end dialog*/
                        else if (usequestnumb>=choosequestnumb){
                            endTestDialog();
                        }
                    }
                }
        );
    }

    /* create end test dialog*/
    private void endTestDialog () {
        final Dialog resultsD = new Dialog(results);
        resultsD.setContentView(R.layout.test_results);
        resultsD.setTitle("Výsledky testu");
        resulttext = (TextView) resultsD.findViewById(R.id.resultsTV);
        resulttext.setText("Správně jste odpověděl na "+right+" otázek z "+choosequestnumb+"");
        resultsBTN = (Button) resultsD.findViewById(R.id.resultsBTN);
        resultsBTN.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
                        String date = df.format(Calendar.getInstance().getTime());
                        int i;
                        for(i=0; i<choosequestnumb;i++){
                            outfail=outfail+failed[i];
                        }

                        int testnumb = share.loadI("testnumb",results);

                        if (testnumb==0){
                            testresults= new String[] { "Test  "+date+" \nSprávně "+right+"/"+choosequestnumb+" otázek.", "", "",
                                    "","","",
                                    "","",""};
                            testresultsfail= new String[] { outfail, "", "",
                                    "","","",
                                    "","",""};
                            share.saveI(1,"firsttestnum",results);


                        }
                        else{
                            testresults=share.loadArray("testresults",results);
                            testresultsfail=share.loadArray("testresultsfail",results);
                            testresults[testnumb]="Test  "+date+" \nSprávně "+right+"/"+choosequestnumb+" otázek.";
                            testresultsfail[testnumb]= outfail;
                            if (testnumb>=8) testnumb=-1;
                        }

                        testnumb++;
                        share.saveI(testnumb,"testnumb",results);
                        share.saveArrayS(testresults,"testresults",results);
                        share.saveArrayS(testresultsfail,"testresultsfail",results);

                        finish();
                    }
                });
        resultsD.show();
    }
}









