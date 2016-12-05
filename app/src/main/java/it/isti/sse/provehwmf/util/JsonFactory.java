package it.isti.sse.provehwmf.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isti.cnr.sse.rest.data.Prova;
import isti.cnr.sse.rest.data.Ditta;
import isti.cnr.sse.rest.data.ModelloMF;
import isti.cnr.sse.rest.data.TipoProve;
import isti.cnr.sse.rest.data.Allegato;

/**
 * Created by m4rt3 on 20/11/2016.
 */

public class JsonFactory {




    public JsonFactory(){

    }

    public static List<Ditta> ini(){
        List<Ditta> e = new ArrayList<Ditta>();
        Ditta a = new Ditta("Custom", "Parma", "11");
        List<ModelloMF> misuratoriFiscali = new ArrayList<>();
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
        ModelloMF mf = new ModelloMF("TIPO1","14E","Custom", timeStamp);
        misuratoriFiscali.add(mf);
        a.setMisuratoriFiscali(misuratoriFiscali );



        e.add(a);
        Prova pp = new Prova(TipoProve.AlimentazioneBatteriaSenzaVincoloFiscale.toString(), "",
                TipoProve.AlimentazioneBatteriaSenzaVincoloFiscale, true,mf);
        pp.setTimeStartPHW("12/12/2016 12:15:16");
        Allegato a2 = new Allegato();
        a2.setMatricola("1122334455");
        a2.setNome("Foto00000000000000000000000000000000.jpg");
        a2.setTipo("JPG");
        a2.setUserid("Gio");
        a2.setUrl("http://localhost:9090/cnr/sse/testhw/allegati/162023DD/"+a2.getNome());
        a2.setNumeroRapportoProva("162023DD");
        a2.setTime("12/12/2016 12:15:16");

        Allegato a1 = new Allegato();
        a1.setMatricola("1122334455");
        a1.setNome("Foto00000000000000000000000000000000.jpg");
        a1.setTipo("JPG");
        a1.setUrl("http://localhost:9090/cnr/sse/testhw/allegati/162023DD/"+a1.getNome());
        a1.setUserid("Gio");
        a1.setNumeroRapportoProva("162023DD");
        a1.setTime("12/12/2016 12:15:16");

        pp.getListallegato().add(a1);
        pp.getListallegato().add(a2);

        mf.getProve().add(pp);
        pp = new Prova(TipoProve.Termiche.toString(), "",
                TipoProve.Termiche, true,mf);
        pp.setTimeStartPHW("12/12/2016 12:15:16");

        mf.getProve().add(pp);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(gson.toJson(a));



        Ditta b = new Ditta("SHS", "Roma", "1213");
        misuratoriFiscali = new ArrayList<>();
        mf = new ModelloMF("TIPO2","15E","SHS", timeStamp);
        misuratoriFiscali.add(mf);
        b.setMisuratoriFiscali(misuratoriFiscali );
        e.add(b);

        pp = new Prova(TipoProve.AlimentazioneBatteriaSenzaVincoloFiscale.toString(), "",
                TipoProve.AlimentazioneBatteriaSenzaVincoloFiscale, true,mf);
        mf.getProve().add(pp);
        pp.setTimeStartPHW("12/12/2016 12:15:16");
        Ditta c = new Ditta("HP", "Pisa", "121");
        misuratoriFiscali = new ArrayList<>();
        mf = new ModelloMF("TIPO3","17E","HP", timeStamp);
        misuratoriFiscali.add(mf);
        c.setMisuratoriFiscali(misuratoriFiscali );
        e.add(c);
        pp = new Prova(TipoProve.AlimentazioneBatteriaSenzaVincoloFiscale.toString(), "",
                TipoProve.AlimentazioneBatteriaSenzaVincoloFiscale, true,mf);
        pp.setTimeStartPHW("12/12/2016 12:15:16");
        mf.getProve().add(pp);
        return e;
    }

    public List<ModelloMF> getMisuratoriFiscale() {
        List<ModelloMF> modelli = new ArrayList<>();
        List<Ditta> element = ini();
        for (Ditta d: element) {
            modelli.addAll(d.getMisuratoriFiscali());
        }

        return  modelli;
    }


}
