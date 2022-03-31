package com.example.bubuk.semestralniprace.OtherClass;


public class WaveresonatorsCalc {

    //constant
    double c = 299792458;           //speed of light
    double mi0 = 1.256637E-6;       //permeability of vacuum

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

    public String CavCubResonance101 (double a, double b,double l, double epsr, double mir, double tandelta, double conductivity) {
        double f0= (c / (2 * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((1 /a), 2) + Math.pow((0/b),2)+Math.pow((1/l), 2));
        double lambda0= c/f0;
        double delta = Math.sqrt(2/(2*Math.PI*f0*mi0*1*conductivity));
        double Q0 = (2/delta)*((a*b*l)/(2*(a*b+b*l+a*l)));
        double Qc = 1/((1/Q0)+tandelta);

        f0 = Math.round((f0 / 1000000000) * 1000) / 1000.0d; //Ghz
        lambda0 = Math.round(lambda0 * 1000) / 1000.0d;
        Qc=Math.round(Qc);

        String OutS = "Nejčastěji pracujeme s videm TE101, který má pro náš rezonátor:\n"+
                        "Rezonanční kmitočet "+f0+" Hz.\n"+
                        "Rezonanční vlnovou délku "+lambda0+" m.\n"+
                        "Celkový činitel jakosti Qc: "+Qc+"." ;
        return OutS;
    }

    public String OwnCavCubMode(int [] inmodes,double a, double b,double l, double epsr, double mir, double tandelta, double conductivity){
        double resfreq [] = new double[5];
        double qualityfactor [] = new double[5];
        String modesS [] = new String[5];
        int i;
        for (i=0;i<5;i++) {
            if (inmodes[0+(5*i)]>0) {
                String typeofmod;
                if (inmodes[1+(5*i)]==0) {
                    typeofmod="TE";
                    resfreq[i] = (c / (2 * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((inmodes[2 + (5 * i)] /a), 2) + Math.pow((inmodes[3 + (5 * i)]/b),2)+Math.pow((inmodes[4 + (5 * i)]/l), 2));
                    double delta = Math.sqrt(2/(2*Math.PI*resfreq[i]*mi0*1*conductivity));
                    double Q0 = (2/delta)*((a*b*l)/(2*(a*b+b*l+a*l)));
                    qualityfactor[i] = 1/((1/Q0)+tandelta);
                }
                else {
                    typeofmod = "TM";
                    resfreq[i] = (c / (2 * Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((inmodes[2 + (5 * i)] /a), 2) + Math.pow((inmodes[3 + (5 * i)]/b),2)+Math.pow((inmodes[4 + (5 * i)]/l), 2));
                    double delta = Math.sqrt(2/(2*Math.PI*resfreq[i]*mi0*1*conductivity));
                    double Q0 = (2/delta)*((a*b*l)/(2*(a*b+b*l+a*l)));
                    qualityfactor[i] = 1/((1/Q0)+tandelta);
                }
                resfreq[i] = Math.round((resfreq[i] / 1000000000) * 1000) / 1000.0d; //Ghz
                qualityfactor [i] = Math.round(qualityfactor[i]); //Ghz
                modesS[i]=typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" s rezonančním kmitočtem " + resfreq[i] + " Ghz.\n"+
                        "Činitel jakosti rezonátoru "+typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" je "+qualityfactor[i]+"\n\n";
            }
            else modesS[i]="";
        }
        String out ="Zvolené vidy:\n\n"+modesS[0]+modesS[1]+modesS[2]+modesS[3]+modesS[4];

        return out;
    }


    public String CavCylResonance011 (double a,double l, double epsr, double mir, double tandelta, double conductivity) {
        double f0= (c / (2 * Math.PI*Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((bessel[5][0]/a), 2) +Math.pow((1*Math.PI/l), 2));
        double lambda0= c/f0;
        double delta = Math.sqrt(2/(2*Math.PI*f0*mi0*1*conductivity));
        double Q0 = (2/delta)*((a*l)/(2*a+2*l));
        double Qc = 1/((1/Q0)+tandelta);

        f0 = Math.round((f0 / 1000000000) * 1000) / 1000.0d; //Ghz
        lambda0 = Math.round(lambda0 * 1000) / 1000.0d;
        Qc=Math.round(Qc);

        String OutS = "Nejčastěji pracujeme s videm TE011, který má pro náš rezonátor:\n"+
                "Rezonanční kmitočet "+f0+" Hz.\n"+
                "Rezonanční vlnovou délku "+lambda0+" m.\n"+
                "Celkový činitel jakosti Qc: "+Qc+"." ;
        return OutS;
    }

    public String OwnCavCylMode(int [] inmodes,double a,double l, double epsr, double mir, double tandelta, double conductivity){
        double resfreq [] = new double[5];
        double qualityfactor [] = new double[5];
        String modesS [] = new String[5];
        int i;
        for (i=0;i<5;i++) {
            if (inmodes[0+(5*i)]>0) {
                String typeofmod;
                if (inmodes[1+(5*i)]==0) {
                    typeofmod="TE";
                    resfreq[i]= (c / (2 * Math.PI*Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((bessel[4+inmodes[3 + (5 * i)]][inmodes[2 + (5 * i)]]/a), 2) +Math.pow((inmodes[4 + (5 * i)]*Math.PI/l), 2));
                    double delta = Math.sqrt(2/(2*Math.PI*resfreq[i]*mi0*1*conductivity));
                    double Q0 = (2/delta)*((a*l)/(2*a+2*l));
                    qualityfactor [i] = 1/((1/Q0)+tandelta);
                }
                else {
                    typeofmod = "TM";
                    resfreq[i]= (c / (2 * Math.PI*Math.sqrt(epsr * mir))) * Math.sqrt(Math.pow((bessel[inmodes[3 + (5 * i)]-1][inmodes[2 + (5 * i)]]/a), 2) +Math.pow((inmodes[4 + (5 * i)]*Math.PI/l), 2));
                    double delta = Math.sqrt(2/(2*Math.PI*resfreq[i]*mi0*1*conductivity));
                    double Q0 = (2/delta)*((a*l)/(2*a+2*l));
                    qualityfactor [i] = 1/((1/Q0)+tandelta);
                }
                resfreq[i] = Math.round((resfreq[i] / 1000000000) * 1000) / 1000.0d; //Ghz
                qualityfactor [i] = Math.round(qualityfactor[i]); //Ghz
                modesS[i]=typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" s rezonančním kmitočtem " + resfreq[i] + " Ghz.\n"+
                        "Činitel jakosti rezonátoru "+typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" je "+qualityfactor[i]+"\n\n";
            }
            else modesS[i]="";
        }
        String out ="Zvolené vidy:\n\n"+modesS[0]+modesS[1]+modesS[2]+modesS[3]+modesS[4];

        return out;
    }

    public String CoaxResonanceTEM (double r0,double br0,double l, double epsr, double mir, double tandelta, double conductivity) {
        double f0= (c*1 / (2 *l*Math.sqrt(epsr * mir)));
        double lambda0= c/f0;
        double delta = Math.sqrt(2/(2*Math.PI*f0*mi0*1*conductivity));
        double Q0 = ((2*br0)/delta)*(Math.log(br0/r0)/(1+(br0/r0)+((4*br0/l)*Math.log(br0/r0))));
        double Qc = 1/((1/Q0)+tandelta);

        f0 = Math.round((f0 / 1000000000) * 1000) / 1000.0d; //Ghz
        lambda0 = Math.round(lambda0 * 1000) / 1000.0d;
        Qc=Math.round(Qc);

        String OutS = "Pracujeme s videm TEM, který má pro náš rezonátor:\n"+
                "Rezonanční kmitočet "+f0+" Hz.\n"+
                "Rezonanční vlnovou délku "+lambda0+" m.\n"+
                "Celkový činitel jakosti Qc: "+Qc+"." ;
        return OutS;
    }

    public String PlanarRectResonance100 (double w,double l,double h, double epsr,double tandelta, double conductivity) {
        double f0= (c / (2 *Math.sqrt(epsr))) * Math.sqrt(Math.pow((1/w), 2) +Math.pow((0/l), 2));
        double lambda0= c/f0;
        double delta = Math.sqrt(2/(2*Math.PI*f0*mi0*1*conductivity));
        double Q0 = h/delta;
        double Qc = 1/((1/Q0)+tandelta);

        f0 = Math.round((f0 / 1000000000) * 1000) / 1000.0d; //Ghz
        lambda0 = Math.round(lambda0 * 1000) / 1000.0d;
        Qc=Math.round(Qc);

        String OutS = "Nejnižší rezonančí vid je TE100 pro w<l, který má pro náš rezonátor:\n"+
                "Rezonanční kmitočet "+f0+" Hz.\n"+
                "Rezonanční vlnovou délku "+lambda0+" m.\n"+
                "Celkový činitel jakosti Qc: "+Qc+"." ;
        return OutS;
    }

    public String OwnPlanarRectMode(int [] inmodes,double w,double l,double h, double epsr,double tandelta, double conductivity){
        double resfreq [] = new double[5];
        double qualityfactor [] = new double[5];
        String modesS [] = new String[5];
        int i;
        for (i=0;i<5;i++) {
            if (inmodes[0+(5*i)]>0) {
                String typeofmod="TE";
                resfreq[i]= (c / (2 *Math.sqrt(epsr))) * Math.sqrt(Math.pow((inmodes[2 + (5 * i)]/w), 2) +Math.pow((inmodes[4 + (5 * i)]/l), 2));
                double delta = Math.sqrt(2/(2*Math.PI*resfreq[i]*mi0*1*conductivity));
                double Q0 = h/delta;
                qualityfactor [i] = 1/((1/Q0)+tandelta);

                resfreq[i] = Math.round((resfreq[i] / 1000000000) * 1000) / 1000.0d; //Ghz
                qualityfactor [i] = Math.round(qualityfactor[i]); //Ghz
                modesS[i]=typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" s rezonančním kmitočtem " + resfreq[i] + " Ghz.\n"+
                        "Činitel jakosti rezonátoru "+typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" je "+qualityfactor[i]+"\n\n";
            }
            else modesS[i]="";
        }
        String out ="Zvolené vidy:\n\n"+modesS[0]+modesS[1]+modesS[2]+modesS[3]+modesS[4];

        return out;
    }

    public String PlanarCircleResonance001 (double a,double h, double epsr,double tandelta, double conductivity) {
        double f0= (c / (2 *Math.sqrt(epsr))) * (bessel[5][0]/a);
        double lambda0= c/f0;
        double delta = Math.sqrt(2/(2*Math.PI*f0*mi0*1*conductivity));
        double Q0 = h/delta;
        double Qc = 1/((1/Q0)+tandelta);

        f0 = Math.round((f0 / 1000000000) * 1000) / 1000.0d; //Ghz
        lambda0 = Math.round(lambda0 * 1000) / 1000.0d;
        Qc=Math.round(Qc);

        String OutS = "Nejnižší rezonančí vid je TE010, který má pro náš rezonátor:\n"+
                "Rezonanční kmitočet "+f0+" Hz.\n"+
                "Rezonanční vlnovou délku "+lambda0+" m.\n"+
                "Celkový činitel jakosti Qc: "+Qc+"." ;
        return OutS;
    }

    public String OwnPlanarCircMode(int [] inmodes,double a,double h, double epsr,double tandelta, double conductivity){
        double resfreq [] = new double[5];
        double qualityfactor [] = new double[5];
        String modesS [] = new String[5];
        int i;
        for (i=0;i<5;i++) {
            if (inmodes[0+(5*i)]>0) {
                String typeofmod="TE";
                resfreq[i]= (c / (2 *Math.sqrt(epsr))) * (bessel[4+inmodes[3 + (5 * i)]][inmodes[2 + (5 * i)]]/a);
                double delta = Math.sqrt(2/(2*Math.PI*resfreq[i]*mi0*1*conductivity));
                double Q0 = h/delta;
                qualityfactor [i] = 1/((1/Q0)+tandelta);

                resfreq[i] = Math.round((resfreq[i] / 1000000000) * 1000) / 1000.0d; //Ghz
                qualityfactor [i] = Math.round(qualityfactor[i]); //Ghz
                modesS[i]=typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" s rezonančním kmitočtem " + resfreq[i] + " Ghz.\n"+
                        "Činitel jakosti rezonátoru "+typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" je "+qualityfactor[i]+"\n\n";
            }
            else modesS[i]="";
        }
        String out ="Zvolené vidy:\n\n"+modesS[0]+modesS[1]+modesS[2]+modesS[3]+modesS[4];

        return out;
    }

    public String DielectricResonance101 (double a, double b,double l, double epsr, double tandelta) {
        double f0= (c / (2 * Math.sqrt(epsr))) * Math.sqrt(Math.pow((1 /a), 2) + Math.pow((0/b),2)+Math.pow((1/l), 2));
        double lambda0= c/f0;
        double Qc = 1/tandelta;

        f0 = Math.round((f0 / 1000000000) * 1000) / 1000.0d; //Ghz
        lambda0 = Math.round(lambda0 * 1000) / 1000.0d;
        Qc=Math.round(Qc);

        String OutS = "Nejčastěji pracujeme s videm TE101, který má pro náš rezonátor:\n"+
                "Rezonanční kmitočet "+f0+" Hz.\n"+
                "Rezonanční vlnovou délku "+lambda0+" m.\n"+
                "Celkový činitel jakosti Qc: "+Qc+"." ;
        return OutS;
    }

    public String OwnDielMode(int [] inmodes,double a, double b,double l, double epsr, double tandelta){
        double resfreq [] = new double[5];
        double qualityfactor [] = new double[5];
        String modesS [] = new String[5];
        int i;
        for (i=0;i<5;i++) {
            if (inmodes[0+(5*i)]>0) {
                String typeofmod;
                if (inmodes[1+(5*i)]==0) {
                    typeofmod="TE";
                    resfreq[i] = (c / (2 * Math.sqrt(epsr))) * Math.sqrt(Math.pow((inmodes[2 + (5 * i)] /a), 2) + Math.pow((inmodes[3 + (5 * i)]/b),2)+Math.pow((inmodes[4 + (5 * i)]/l), 2));
                    qualityfactor[i] = 1/tandelta;
                }
                else {
                    typeofmod = "TM";
                    resfreq[i] = (c / (2 * Math.sqrt(epsr))) * Math.sqrt(Math.pow((inmodes[2 + (5 * i)] /a), 2) + Math.pow((inmodes[3 + (5 * i)]/b),2)+Math.pow((inmodes[4 + (5 * i)]/l), 2));
                    qualityfactor[i] = 1/tandelta;
                }
                resfreq[i] = Math.round((resfreq[i] / 1000000000) * 1000) / 1000.0d; //Ghz
                qualityfactor [i] = Math.round(qualityfactor[i]); //Ghz
                modesS[i]=typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" s rezonančním kmitočtem " + resfreq[i] + " Ghz.\n"+
                        "Činitel jakosti rezonátoru "+typeofmod+""+inmodes[2 + (5 * i)]+""+inmodes[3 + (5 * i)]+""+inmodes[4 + (5 * i)]+" je "+qualityfactor[i]+"\n\n";
            }
            else modesS[i]="";
        }
        String out ="Zvolené vidy:\n\n"+modesS[0]+modesS[1]+modesS[2]+modesS[3]+modesS[4];

        return out;
    }













}
