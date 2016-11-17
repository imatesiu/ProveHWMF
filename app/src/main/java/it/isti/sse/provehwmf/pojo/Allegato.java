
package it.isti.sse.provehwmf.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Allegato {

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
    @SerializedName("Tipo")
    @Expose
    private String tipo;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Dati")
    @Expose
    private String dati;
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
    @SerializedName("Note")
    @Expose
    private Note note;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Userid")
    @Expose
    private String userid;

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
     *     The tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * 
     * (Required)
     * 
     * @param tipo
     *     The Tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The dati
     */
    public String getDati() {
        return dati;
    }

    /**
     * 
     * (Required)
     * 
     * @param dati
     *     The Dati
     */
    public void setDati(String dati) {
        this.dati = dati;
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
     *     The note
     */
    public Note getNote() {
        return note;
    }

    /**
     * 
     * (Required)
     * 
     * @param note
     *     The Note
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 
     * (Required)
     * 
     * @param userid
     *     The Userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
