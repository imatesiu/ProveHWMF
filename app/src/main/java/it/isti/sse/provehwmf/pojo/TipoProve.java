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

    public static TipoProve get(String tipo) {
        if (tipo.equals("Termiche")) {
            return Termiche;
        }
        if (tipo.equals("DisturbiElettromagnetici")) {
            return DisturbiElettromagnetici;
        }
        if (tipo.equals("Disturbi Elettromagnetici")) {
            return DisturbiElettromagnetici;
        }
        if (tipo.equals("Impermeabilità")) {
            return Impermeabilita;
        }
        if (tipo.equals("Vibrazione")) {
            return Vibrazione;
        }
        if (tipo.equals("Disturbi Condotti")) {
            return DisturbiCondotti;
        }
        if (tipo.equals("Batteria Sotto Protezione SF")) {
            return BatteriaSottoProtezioneSF;
        }
        if (tipo.equals("Alimentazione Senza Vincolo Fiscale")) {
            return AlimentazioneSenzaVincoloFiscale;
        }
        return ScaricheElettrostatiche;
    }
}
