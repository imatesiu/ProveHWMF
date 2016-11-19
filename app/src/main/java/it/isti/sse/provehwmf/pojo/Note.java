
package it.isti.sse.provehwmf.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Note implements Serializable {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Matricola")
    @Expose
    private String matricola;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Time")
    @Expose
    private String time;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Testo")
    @Expose
    private String testo;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The matricola
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * 
     * (Required)
     * 
     * @param matricola
     *     The Matricola
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * (Required)
     * 
     * @param time
     *     The Time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * 
     * (Required)
     * 
     * @param testo
     *     The Testo
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
