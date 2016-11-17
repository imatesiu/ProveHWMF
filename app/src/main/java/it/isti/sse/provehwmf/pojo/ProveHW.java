
package it.isti.sse.provehwmf.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class ProveHW {

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
        return provaHW;
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
        return ToStringBuilder.reflectionToString(this);
    }

}
