package it.isti.sse.provehwmf.pojo;

/**
 * Created by m4rt3 on 20/11/2016.
 */

public enum TipoProve {

    Termiche (1),
    Impermeabilita (2),
    Vibrazione (3),
    DisturbiElettromagnetici (4),
    DisturbiCondotti (5),
    BatteriaSottoProtezioneSF (6),
    AlimentazioneSenzaVincoloFiscale (7),
    ScaricheElettrostatiche (8);

    TipoProve(int i) {
    }
}
