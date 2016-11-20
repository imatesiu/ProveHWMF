
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
}
