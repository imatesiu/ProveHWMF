
package it.isti.sse.provehwmf.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class ProveHW  implements Serializable {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("ProvaHW")
    @Expose
    private List<ProvaHW> provaHW = new ArrayList<ProvaHW>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The provaHW
     */
    public List<ProvaHW> getProvaHW() {
        if (provaHW!=null)
            return provaHW;
        else
            return new ArrayList<>();
    }

    /**
     * 
     * (Required)
     * 
     * @param provaHW
     *     The ProvaHW
     */
    public void setProvaHW(List<ProvaHW> provaHW) {
        this.provaHW = provaHW;
    }

    @Override
    public String toString() {
        return "ProveHW{" +
                "provaHW=" + provaHW +
                '}';
    }

    public void remove(int position) {
        getProvaHW().remove(position);
    }

    public int size() {
        return getProvaHW().size();
    }

    public void add(ProvaHW nphw) {
        if(nphw.isEdited()){
            for (ProvaHW p:
                 getProvaHW()) {
                if(p.equals(nphw)){
                    p.merge(nphw);
                }
            }
        }else{
            getProvaHW().add(nphw);
        }
    }

    public void insert(Allegato a) {
        for (ProvaHW p:getProvaHW()) {
            if(p.getMatricola().equals(a.getMatricola())){
                p.getAllegati().getAllegato().add(a);
                return;
            }
        }
        ProvaHW p = new ProvaHW();
        p.setTipo(a.getTipoProva());
        p.setMatricola(a.getMatricola());
        p.setModello(a.getModello());
        p.setTimeStartPHW(a.getTime());
        p.setEsito(Esito.Incorso);
        getProvaHW().add(p);
        Allegati oo = p.getAllegati();
        oo.getAllegato().add(a);


    }
}
