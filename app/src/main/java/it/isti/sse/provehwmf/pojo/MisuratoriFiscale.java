
package it.isti.sse.provehwmf.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class MisuratoriFiscale implements Serializable {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("MisuratoreFiscale")
    @Expose
    private List<MisuratoreFiscale> misuratoreFiscale = new ArrayList<MisuratoreFiscale>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The misuratoreFiscale
     */
    public List<MisuratoreFiscale> getMisuratoreFiscale() {
        if(misuratoreFiscale!=null) {
            return misuratoreFiscale;
        }else{
            return new ArrayList<>();
        }
    }

    public String getStatus(int misutatore){
        try {
            MisuratoreFiscale MF = getMisuratoreFiscale().get(misutatore);
            return MF.getStatus();
        }catch (NullPointerException e){
            return "Errore";
        }

    }

    /**
     * 
     * (Required)
     * 
     * @param misuratoreFiscale
     *     The MisuratoreFiscale
     */
    public void setMisuratoreFiscale(List<MisuratoreFiscale> misuratoreFiscale) {
        this.misuratoreFiscale = misuratoreFiscale;
    }

    @Override
    public String toString() {
        return "MisuratoriFiscale{" +
                "misuratoreFiscale=" + misuratoreFiscale +
                '}';
    }

    public int size() {
       return getMisuratoreFiscale().size();
    }


    public boolean insert(ProvaHW nphw) {
        String Matricola = nphw.getMatricola();
        for (MisuratoreFiscale MF:
             getMisuratoreFiscale()) {
            if(MF.getMatricola().equals(Matricola)){
                MF.getProveHW().getProvaHW().add(nphw);
                return true;
            }
        }
        return false;

    }

    public ArrayList<String> getMatricoleFiscali() {
        ArrayList<String> ListaMF = new ArrayList<>();
        ListaMF.add("");
        for (MisuratoreFiscale MF:getMisuratoreFiscale()
             ) {
            ListaMF.add(MF.getMatricola());
        }
        return ListaMF;
    }
}
