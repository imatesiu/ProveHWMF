
package it.isti.sse.provehwmf.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Allegati {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Allegato")
    @Expose
    private List<Allegato> allegato = new ArrayList<Allegato>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The allegato
     */
    public List<Allegato> getAllegato() {
        return allegato;
    }

    /**
     * 
     * (Required)
     * 
     * @param allegato
     *     The Allegato
     */
    public void setAllegato(List<Allegato> allegato) {
        this.allegato = allegato;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}