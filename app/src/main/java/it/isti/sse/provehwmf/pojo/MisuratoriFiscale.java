
package it.isti.sse.provehwmf.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class MisuratoriFiscale {

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
        return misuratoreFiscale;
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
        return ToStringBuilder.reflectionToString(this);
    }

}
