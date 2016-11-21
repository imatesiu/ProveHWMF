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
        Allegato a = new Allegato();
        a.setMatricola("1122334455");
        a.setNome("Foto00000000000000000000000000000000.jpg");
        a.setTipo("JPG");
        a.setUserid("Gio");
        a.setTime("12/12/2016 12:15:16");
        Allegati aa = new Allegati();
        aa.getAllegato().add(a);

        ProvaHW phw = new ProvaHW();
        phw.setEsito(Esito.Positivo);
        phw.setMatricola("1122334455");
        phw.setTipo(TipoProve.DisturbiElettromagnetici.toString());
        phw.setModello("TIPO C");
        phw.setUserid("Gio");
        phw.setTimeStartPHW("12/12/2016 12:15:16");
        phw.setTimeEndPHW("13/12/2016 12:15:16");
        phw.setAllegati(aa);
        phw.setNote(new Note());

        ProveHW LPHW = new ProveHW();
        LPHW.getProvaHW().add(phw);


        MisuratoreFiscale MF = new MisuratoreFiscale();
        MF.setDitta("SHS");
        MF.setModello("TIPO C");
        MF.setMatricola("1122334455");
        MF.setTimeMFStart("12/12/2016 12:15:16");
        MF.setTimeMFEnd("13/12/2016 12:15:16");
        MF.setNome("TIPO");
        MF.setProveHW(LPHW);
        MF.setNote(new Note());

        LMF = new MisuratoriFiscale();
        LMF.getMisuratoreFiscale().add(MF);

    }

    public MisuratoriFiscale getMisuratoriFiscale() {
        return LMF;
    }


}
