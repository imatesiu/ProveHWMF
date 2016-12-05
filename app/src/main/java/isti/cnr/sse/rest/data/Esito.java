package isti.cnr.sse.rest.data;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by m4rt3 on 20/11/2016.
 */
public enum Esito {Positivo(1), Negativo(2), Incerto(3),  Incorso(4), Sospeso (5);

    

    Esito(int i) {
    }

    public static List<Esito> getListEsiti(){
    	List<Esito> r =  new ArrayList<>();
    	r.add(Incorso);
    	r.add(Incerto);
    	r.add(Negativo);
    	r.add(Positivo);
    	r.add(Sospeso);
    	return r;
    }

};


