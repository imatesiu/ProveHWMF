package it.isti.sse.provehwmf.util;

import it.isti.sse.provehwmf.pojo.Allegati;
import it.isti.sse.provehwmf.pojo.Allegato;
import it.isti.sse.provehwmf.pojo.Esito;
import it.isti.sse.provehwmf.pojo.MisuratoreFiscale;
import it.isti.sse.provehwmf.pojo.MisuratoriFiscale;
import it.isti.sse.provehwmf.pojo.Note;
import it.isti.sse.provehwmf.pojo.ProvaHW;
import it.isti.sse.provehwmf.pojo.ProveHW;
import it.isti.sse.provehwmf.pojo.TipoProve;

/**
 * Created by m4rt3 on 20/11/2016.
 */

public class JsonFactory {


    private MisuratoriFiscale LMF;

    public JsonFactory(){
        init();
    }

    private void init() {
        Allegato a2 = new Allegato();
        a2.setMatricola("1122334455");
        a2.setNome("Foto00000000000000000000000000000000.jpg");
        a2.setTipo("JPG");
        a2.setUserid("Gio");
        a2.setTime("12/12/2016 12:15:16");

        Allegato a1 = new Allegato();
        a1.setMatricola("1122334455");
        a1.setNome("Foto00000000000000000000000000000000.jpg");
        a1.setTipo("JPG");
        a1.setUserid("Gio");
        a1.setTime("12/12/2016 12:15:16");


        Allegati aa1 = new Allegati();
        aa1.getAllegato().add(a2);
        aa1.getAllegato().add(a1);


        ProvaHW phw2 = new ProvaHW();
        phw2.setEsito(Esito.Positivo);
        phw2.setMatricola("1122334455");
        phw2.setTipo(TipoProve.DisturbiElettromagnetici.toString());
        phw2.setModello("TIPO C");
        phw2.setUserid("Gio");
        phw2.setTimeStartPHW("12/12/2016 12:15:16");
        phw2.setTimeEndPHW("13/12/2016 12:15:16");
        phw2.setAllegati(aa1);
        phw2.setNote(new Note());

        ProvaHW phw1 = new ProvaHW();
        phw1.setEsito(Esito.Positivo);
        phw1.setMatricola("1122334455");
        phw1.setTipo(TipoProve.DisturbiElettromagnetici.toString());
        phw1.setModello("TIPO C");
        phw1.setUserid("Gio");
        phw1.setTimeStartPHW("12/12/2016 12:15:16");
        phw1.setTimeEndPHW("13/12/2016 12:15:16");
        phw1.setAllegati(aa1);
        phw1.setNote(new Note());

        ProveHW LPHW1 = new ProveHW();
        LPHW1.getProvaHW().add(phw2);
        LPHW1.getProvaHW().add(phw1);

        MisuratoreFiscale MF = new MisuratoreFiscale();
        MF.setDitta("SHS");
        MF.setModello("TIPO C");
        MF.setMatricola("1122334455");
        MF.setTimeMFStart("12/12/2016 12:15:16");
        MF.setTimeMFEnd("13/12/2016 12:15:16");
        MF.setNome("TIPO");
        MF.setProveHW(LPHW1);
        MF.setNote(new Note());

        Allegato a = new Allegato();
        a.setMatricola("112233445566");
        a.setNome("Foto00000000000000000000000000000000.jpg");
        a.setTipo("JPG");
        a.setUserid("Gio");
        a.setTime("12/12/2016 12:15:16");
        Allegati aa = new Allegati();
        aa.getAllegato().add(a);

        ProvaHW phw = new ProvaHW();
        phw.setEsito(Esito.Positivo);
        phw.setMatricola("112233445566");
        phw.setTipo(TipoProve.DisturbiElettromagnetici.toString());
        phw.setModello("TIPO C");
        phw.setUserid("Gio");
        phw.setTimeStartPHW("12/12/2016 12:15:16");
        phw.setTimeEndPHW("13/12/2016 12:15:16");
        phw.setAllegati(aa);
        phw.setNote(new Note());

        ProveHW LPHW = new ProveHW();
        LPHW.getProvaHW().add(phw);

        MisuratoreFiscale MF2 = new MisuratoreFiscale();
        MF2.setDitta("SHS");
        MF2.setModello("TIPO C");
        MF2.setMatricola("112233445566");
        MF2.setTimeMFStart("12/12/2016 12:15:16");
        MF2.setTimeMFEnd("13/12/2016 12:15:16");
        MF2.setNome("TIPO");
        MF2.setProveHW(LPHW);
        MF2.setNote(new Note());

        LMF = new MisuratoriFiscale();
        LMF.getMisuratoreFiscale().add(MF);
        LMF.getMisuratoreFiscale().add(MF2);

    }

    public MisuratoriFiscale getMisuratoriFiscale() {
        return LMF;
    }


}
