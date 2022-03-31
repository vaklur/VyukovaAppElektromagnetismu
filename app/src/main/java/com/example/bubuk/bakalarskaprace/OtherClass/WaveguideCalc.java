package com.example.bubuk.semestralniprace.OtherClass;

public class WaveguideCalc {

    //constant
    double c = 299792458;           //speed of light
    double mi0 = 1.256637E-6;       //permeability of vacuum
    double eps0 = 8.854187818E-12;  //permitivity of vacuum

    double[][] bessel= new double[][]{                                                                          //m=0-10
            { 2.4048, 3.8317, 5.1356, 6.3802, 7.5883, 8.7715, 9.9361, 11.0864, 12.2251, 13.3543, 14.4755 },     //n=1
            { 5.5201, 7.0156, 8.4172, 9.7610, 11.0647, 12.3386, 13.5893, 14.8213, 16.03777, 17.2412, 18.4335 }, //n=2
            { 8.6537, 10.1735, 11.6198,	13.0152, 14.3725,15.7002, 17.0038, 18.2876, 19.5545, 20.8070, 22.04670},//n=3
            {11.7915, 13.3237,14.7960, 16.2235, 17.6160, 18.9801, 20.3208, 21.6415, 22.9452, 24.2339, 25.5095}, //n=4
            {14.9309, 16.4706, 17.9598, 19.4094, 20.8269, 22.2178, 23.5861,24.9349,26.2668,27.5837, 28.8874},   //n=5
            { 3.8317, 1.8412, 3.0542, 4.2012, 5.3176, 6.4156, 7.5013, 8.5778,  9.6474,  10.7114, 11.7709 },     //n=1 derivation
            { 7.0156, 5.3314, 6.7061, 8.0152, 9.2824, 10.5199, 11.7349,12.9324, 14.1155, 15.2867, 16.4478 },    //n=2 derivation
            {10.1735, 8.5363, 9.9695,11.3459,12.6819,13.9872,15.2682,16.5294,17.7740,	19.0046,20.2230},       //n=3 derivation
            {13.3237, 11.7060,13.1704,14.5858,15.9641,17.3128,18.6374,19.9419,21.2291,	22.5014,23.7607},       //n=4 derivation
            {16.4706, 14.8636,16.3475,17.7888,19.1960,20.5755,21.9317,23.2681,24.5872,	25.8913,27.1820}        //n=5 derivation

    };


    public String RectMode (double a, double b, double epsr, double mir) {
        int modenumb = 136;
        double [] modes = new double[modenumb];
        String [] modesnumb = new String[modenumb];
        int k=0;
        int i;
        if (a==b) {
            for (i = 1; i < 12; i++) {
                for (int j = 0; j < 12; j++) {
                    modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((i * Math.PI) / a, 2) + Math.pow((j * Math.PI) / b, 2));
                    if (j == 0) modesnumb[k] = "TE " + i + "" + j + "/" + j + "" + i + "";
                    else if (j==i) modesnumb[k] = "TE/TM " + i + "" + j + "";
                    else modesnumb[k] = "TE/TM " + i + "" + j + "/" + j + "" + i + "";
                    k++;
                }
            }
            for (i=0;i<4;i++){
                modes[k] = 10E15;
                modesnumb[k] = "overlimit";
                k++;
            }
        }
        else {
            for (i = 1; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((i * Math.PI) / a, 2) + Math.pow((j * Math.PI) / b, 2));
                    if (j == 0) modesnumb[k] = "TE " + i + "" + j + " ";
                    else modesnumb[k] = "TE/TM " + i + "" + j + "";
                    k++;
                    if (i != j) {
                        modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((j * Math.PI) / a, 2) + Math.pow((i * Math.PI) / b, 2));
                        if (j == 0) modesnumb[k] = "TE " + j + "" + i + " ";
                        else modesnumb[k] = "TE/TM " + j + "" + i + "";
                        k++;
                    }
                }
            }
        }

        for (i=1;i<modenumb;i++){
            double hodnota = modes[i];
            String znak = modesnumb[i];
            int j=i-1;
            while (j>=0 && modes[j]>hodnota){
                modes[j+1]=modes[j];
                modes[j]=hodnota;
                modesnumb[j+1]=modesnumb[j];
                modesnumb[j]=znak;
                j--;}

        }


        for (i=0;i<modenumb;i++){
            modes[i] = Math.round((modes [i]/1000000000)*1000)/1000.0d;
        }

        double domalpha = 3 / (modes[0] * 10);
        domalpha = Math.round(domalpha * 1000) / 1000.0d;
        String outS="Dominantním videm je vid " + modesnumb[0] + ".<br>" +
                "Mezní kmitočet vidu " + modesnumb[0] + "je " + modes[0] + " Ghz.<br>" +
                "Mezní vlnová délka vidu " + modesnumb[0] + " je " + domalpha + " m.<br>" +
                "Pásmo jednovidovosti: &lt;" + modes[0] + ";" + modes[1] + ") Ghz.<br><br>" +
                "&emsp;&emsp;&emsp;&emsp;<b>Vyšší vlnové vidy:</b> <br>"+
                "" + modesnumb[1] + " s mezním kmitočtem " + modes[1] + " Ghz.<br>" +
                "" + modesnumb[2] + " s mezním kmitočtem " + modes[2] + " Ghz.<br>" +
                "" + modesnumb[3] + " s mezním kmitočtem " + modes[3] + " Ghz.<br>" +
                "" + modesnumb[4] + " s mezním kmitočtem " + modes[4] + " Ghz.";
        return outS;
    }

    public String RectModeSetFreq (double a, double b, double epsr, double mir,double freq) {
        int modenumb = 136;
        double [] modes = new double[modenumb];
        String [] modesnumb = new String[modenumb];
        String end;
        int k=0;
        int i;
        if (a==b) {
            for (i = 1; i < 12; i++) {
                for (int j = 0; j < 12; j++) {
                    modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((i * Math.PI) / a, 2) + Math.pow((j * Math.PI) / b, 2));
                    if (j == 0) modesnumb[k] = "TE " + i + "" + j + "/" + j + "" + i + "";
                    else if (j==i) modesnumb[k] = "TE/TM " + i + "" + j + "";
                    else modesnumb[k] = "TE/TM " + i + "" + j + "/" + j + "" + i + "";
                    k++;
                }
            }
            for (i=0;i<4;i++){
                modes[k] = 10E15;
                modesnumb[k] = "overlimit";
                k++;
            }
        }
        else {
            for (i = 1; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((i * Math.PI) / a, 2) + Math.pow((j * Math.PI) / b, 2));
                    if (j == 0) modesnumb[k] = "TE " + i + "" + j + " ";
                    else modesnumb[k] = "TE/TM " + i + "" + j + "";
                    k++;
                    if (i != j) {
                        modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((j * Math.PI) / a, 2) + Math.pow((i * Math.PI) / b, 2));
                        if (j == 0) modesnumb[k] = "TE " + j + "" + i + " ";
                        else modesnumb[k] = "TE/TM " + j + "" + i + "";
                        k++;
                    }
                }
            }
        }

        for (i=1;i<modenumb;i++){
            double hodnota = modes[i];
            String znak = modesnumb[i];
            int j=i-1;
            while (j>=0 && modes[j]>hodnota){
                modes[j+1]=modes[j];
                modes[j]=hodnota;
                modesnumb[j+1]=modesnumb[j];
                modesnumb[j]=znak;
                j--;}
        }

        double maxmodef=0;
        String maxmode="";

        double phaseSpeed=(c/Math.sqrt(epsr * mir))/Math.sqrt(1-Math.pow(modes[0]/freq,2));
        phaseSpeed=Math.round(phaseSpeed/1000000)*1000000;
        double groupSpeed=(c/Math.sqrt(epsr * mir))*Math.sqrt(1-Math.pow(modes[0]/freq,2));
        groupSpeed=Math.round(groupSpeed/1000000)*1000000;
        double waveguideLength=(c/freq)/Math.sqrt(1-Math.pow(modes[0]/freq,2));
        waveguideLength=Math.round(waveguideLength*10000)/10000d;
        double charImpedance=Math.sqrt((mir*mi0)/(epsr*eps0))/Math.sqrt(1-Math.pow(modes[0]/freq,2));
        charImpedance=Math.round(charImpedance);

        if (modes[0]>freq){
            double attenuation = ((2 * Math.PI * freq)/(Math.sqrt(epsr * mir)*c))*Math.sqrt(Math.pow(modes[0]/freq,2)-1);
            attenuation=8.686*attenuation;
            attenuation= Math.round(attenuation/100)*100;
            end = "Zvolená vlna se ve vlnovodu nemůže šířit.\n" +
                    "Útlum vlny vlivem odrazu od vstupu:\n" +
                    ""+attenuation+" dB/m.";
        }
        else if (modes[50]<freq){
            end = "Vlnovodem se šíří více než 50 vidů."+
                    "V oblasti jednovidovosti má zvolená vlna:\n" +
                    "Fázovou rychlost "+phaseSpeed+" m/s.\n" +
                    "Skupinovou rychlost "+groupSpeed+" m/s.\n" +
                    "Vlnovou délku ve vlnovodu"+waveguideLength+" m.\n" +
                    "Charakteristickou impedanci "+charImpedance+" Ohm.";

        }
        else{
            for (i = 0; i < modenumb; i++) {
                if (modes[i] < freq) {
                    maxmodef = (Math.round((modes[i] / 1000000000) * 1000) / 1000.0d);
                    maxmode = modesnumb[i];
                }
            }
            end = "Nejvyšší vlnový vid který se vybudí:\n" +
                    "Vid "+maxmode+" s mezní frekvencí "+maxmodef+" GHz\n\n" +
                    "V oblasti jednovidovosti má zvolená vlna:\n" +
                    "Fázovou rychlost "+phaseSpeed+" m/s.\n" +
                    "Skupinovou rychlost "+groupSpeed+" m/s.\n" +
                    "Vlnovou délku ve vlnovodu"+waveguideLength+" m.\n" +
                    "Charakteristickou impedanci "+charImpedance+" Ohm."

            ;
        }

        return end;
    }

    public String CircMode (double a, double epsr, double mir) {
        int modenumb = 110;
        double [] modes = new double[modenumb];
        String [] modesnumb = new String[modenumb];
        int k=0;
        int l=0;
        int i;
        //TM
        for (i = 0; i < 11; i++) {
            l=0;
            for (int j = 0; j < 5; j++) {
                modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * (bessel[j][i]/a);
                l=l+1;
                modesnumb[k] = "TM " + i + "" + l + "";
                k++;}
        }
        //TE
        for (i = 0; i < 11; i++) {
            l=0;
            for (int j = 5; j < 10; j++) {
                modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * (bessel[j][i]/a);
                l=l+1;
                modesnumb[k] = "TE " + i + "" + l + "";
                k++;}
        }


        for (i=1;i<modenumb;i++){
            double hodnota = modes[i];
            String znak = modesnumb[i];
            int j=i-1;
            while (j>=0 && modes[j]>hodnota){
                modes[j+1]=modes[j];
                modes[j]=hodnota;
                modesnumb[j+1]=modesnumb[j];
                modesnumb[j]=znak;
                j--;}

        }


        for (i=0;i<modenumb;i++){
            modes[i] = Math.round((modes [i]/1000000000)*1000)/1000.0d;
        }

        double domalpha = 3 / (modes[0] * 10);
        domalpha = Math.round(domalpha * 1000) / 1000.0d;
        String outS="Dominantním videm je vid " + modesnumb[0] + ".\n" +
                "Mezní kmitočet vidu " + modesnumb[0] + "je " + modes[0] + " Ghz.\n" +
                "Mezní vlnová délka vidu " + modesnumb[0] + " je " + domalpha + " m.\n" +
                "Pásmo jednovidovosti: <" + modes[0] + ";" + modes[1] + ") Ghz\n\n" +
                "                   Vyšší vlnové vidy:\n" +
                "" + modesnumb[1] + " s mezním kmitočtem " + modes[1] + " Ghz.\n" +
                "" + modesnumb[2] + " s mezním kmitočtem " + modes[2] + " Ghz.\n" +
                "" + modesnumb[3] + " s mezním kmitočtem " + modes[3] + " Ghz.\n" +
                "" + modesnumb[4] + " s mezním kmitočtem " + modes[4] + " Ghz.";
        return outS;
    }

    public String CircModeSetFreq (double a, double epsr, double mir,double freq) {
        int modenumb = 110;
        double [] modes = new double[modenumb];
        String [] modesnumb = new String[modenumb];
        String end;
        int k=0;
        int l=0;
        int i;
        //TM
        for (i = 0; i < 11; i++) {
            l=0;
            for (int j = 0; j < 5; j++) {
                modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * (bessel[j][i]/a);
                l=l+1;
                modesnumb[k] = "TM " + i + "" + l + "";
                k++;}
        }
        //TE
        for (i = 0; i < 11; i++) {
            l=0;
            for (int j = 5; j < 10; j++) {
                modes[k] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * (bessel[j][i]/a);
                l=l+1;
                modesnumb[k] = "TE " + i + "" + l + "";
                k++;}
        }


        for (i=1;i<modenumb;i++){
            double hodnota = modes[i];
            String znak = modesnumb[i];
            int j=i-1;
            while (j>=0 && modes[j]>hodnota){
                modes[j+1]=modes[j];
                modes[j]=hodnota;
                modesnumb[j+1]=modesnumb[j];
                modesnumb[j]=znak;
                j--;}

        }


        double maxmodef=0;
        String maxmode="";

        double phaseSpeed=(c/Math.sqrt(epsr * mir))/Math.sqrt(1-Math.pow(modes[0]/freq,2));
        phaseSpeed=Math.round(phaseSpeed/1000000)*1000000;
        double groupSpeed=(c/Math.sqrt(epsr * mir))*Math.sqrt(1-Math.pow(modes[0]/freq,2));
        groupSpeed=Math.round(groupSpeed/1000000)*1000000;
        double waveguideLength=(c/freq)/Math.sqrt(1-Math.pow(modes[0]/freq,2));
        waveguideLength=Math.round(waveguideLength*10000)/10000d;
        double charImpedance=Math.sqrt((mir*mi0)/(epsr*eps0))/Math.sqrt(1-Math.pow(modes[0]/freq,2));
        charImpedance=Math.round(charImpedance);

        if (modes[0]>freq){
            double attenuation = ((2 * Math.PI * freq)/(Math.sqrt(epsr * mir)*c))*Math.sqrt(Math.pow(modes[0]/freq,2)-1);
            attenuation=8.686*attenuation;
            attenuation= Math.round(attenuation/100)*100;
            end = "Zvolená vlna se ve vlnovodu nemůže šířit.\n" +
                    "Útlum vlny vlivem odrazu od vstupu:\n" +
                    ""+attenuation+" dB/m.";
        }
        else if (modes[50]<freq){
            end = "Vlnovodem se šíří více než 50 vidů."+
                    "V oblasti jednovidovosti má zvolená vlna:\n" +
                    "Fázovou rychlost "+phaseSpeed+" m/s.\n" +
                    "Skupinovou rychlost "+groupSpeed+" m/s.\n" +
                    "Vlnovou délku ve vlnovodu"+waveguideLength+" m.\n" +
                    "Charakteristickou impedanci "+charImpedance+" Ohm.";

        }
        else{
            for (i = 0; i < modenumb; i++) {
                if (modes[i] < freq) {
                    maxmodef = (Math.round((modes[i] / 1000000000) * 1000) / 1000.0d);
                    maxmode = modesnumb[i];
                }
            }
            end = "Nejvyšší vlnový vid který se vybudí:\n" +
                    "Vid "+maxmode+" s mezní frekvencí "+maxmodef+" GHz\n\n" +
                    "V oblasti jednovidovosti má zvolená vlna:\n" +
                    "Fázovou rychlost "+phaseSpeed+" m/s.\n" +
                    "Skupinovou rychlost "+groupSpeed+" m/s.\n" +
                    "Vlnovou délku ve vlnovodu"+waveguideLength+" m.\n" +
                    "Charakteristickou impedanci "+charImpedance+" Ohm.";
        }

        return end;
    }

    public String CoaxMode (double r0, double br0, double epsr, double mir, double freq) {
        String outS="";
        double charimp = (60/Math.sqrt(epsr*mir))*Math.log(br0/r0);
        charimp= Math.round(charimp*1000)/1000.0d;
        double lambdate11 = Math.PI*(br0+r0);
        double freqte11 = c / (lambdate11);
        freqte11 = Math.round((freqte11 / 1000000000) * 1000) / 1000.0d;
        lambdate11 = Math.round(lambdate11 * 1000) / 1000.0d;
        String setfreq="";
        freq=freq/1000000000;
        if (r0<br0){
            if (freq<freqte11) setfreq="Zvolená vlna se šíří v pásmu jednovidovosti.";
            if (freq>=freqte11) setfreq="Zvolená vlna vybudí ve vlnovodu vlnové vidy.";
            outS=""+setfreq+"\n\n" +
                    "Dominantním videm je vid TEM.\n" +
                    "Vid TEM se šíří od nejnižší (nulové) frekvence.\n" +
                    "Char. impedance Z0 vlnovodu je "+charimp+"Ω.\n"+
                    "Pásmo jednovidovosti: <0;" + freqte11+ ") Ghz.\n\n" +
                    "                   Hlavní vlnový vid TE11:\n" +
                    "S mezním kmitočtem "+freqte11+" Ghz.\n"+
                    "S mezní vlnovou délkou "+lambdate11+"m.";
        }
        else{
            outS="Pruměr vnitřního vodiče nemůže být větší než průměr vnějšího vodiče.";
        }
        return outS;
    }

    public String OwnRectMode(int [] inmodes,double a, double b, double epsr, double mir){
        double modes [] = new double[5];
        String modesS [] = new String[5];
        int i;
        for (i=0;i<5;i++) {
            if (inmodes[0+(4*i)]>0) {
                modes[i] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((inmodes[2 + (4 * i)] * Math.PI) / a, 2) + Math.pow((inmodes[3 + (4 * i)] * Math.PI) / b, 2));
                modes[i] = Math.round((modes[i] / 1000000000) * 1000) / 1000.0d; //Ghz
                String typeofmod;
                if (inmodes[1+(4*i)]==0) typeofmod="TE";
                else typeofmod = "TM";
                modesS[i]=typeofmod+""+inmodes[2 + (4 * i)]+""+inmodes[3 + (4 * i)]+" s mezním kmitočtem " + modes[i] + " Ghz.\n";
            }
            else modesS[i]="";
        }
        String out ="Zvolené vidy:\n\n"+modesS[0]+modesS[1]+modesS[2]+modesS[3]+modesS[4];

        return out;
    }

    public String OwnCircMode(int [] inmodes,double a, double epsr, double mir){
        double modes [] = new double[5];
        String modesS [] = new String[5];
        int i;
        for (i=0;i<5;i++) {
            if (inmodes[0+(4*i)]>0) {
                String typeofmod;
                if (inmodes[1+(4*i)]==0) {
                    typeofmod="TE";
                    modes[i] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * (bessel[4+inmodes[3 + (4 * i)]][inmodes[2 + (4 * i)]]/a);
                }
                else {
                    typeofmod = "TM";
                    modes[i] = (c / (2 * Math.PI * Math.sqrt(epsr * mir))) * (bessel[inmodes[3 + (4 * i)]-1][inmodes[2 + (4 * i)]]/a);
                }
                modes[i] = Math.round((modes[i] / 1000000000) * 1000) / 1000.0d; //Ghz
                modesS[i]=typeofmod+""+inmodes[2 + (4 * i)]+""+inmodes[3 + (4 * i)]+" s mezním kmitočtem " + modes[i] + " Ghz.\n";
            }
            else modesS[i]="";
        }
        String out ="Zvolené vidy:\n\n"+modesS[0]+modesS[1]+modesS[2]+modesS[3]+modesS[4];

        return out;
    }
}
