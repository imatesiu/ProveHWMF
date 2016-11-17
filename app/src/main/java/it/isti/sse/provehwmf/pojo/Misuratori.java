
package it.isti.sse.provehwmf.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Misuratori {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("MisuratoriFiscale")
    @Expose
    private MisuratoriFiscale misuratoriFiscale;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The misuratoriFiscale
     */
    public MisuratoriFiscale getMisuratoriFiscale() {
        return misuratoriFiscale;
    }

    /**
     * 
     * (Required)
     * 
     * @param misuratoriFiscale
     *     The MisuratoriFiscale
     */
    public void setMisuratoriFiscale(MisuratoriFiscale misuratoriFiscale) {
        this.misuratoriFiscale = misuratoriFiscale;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
