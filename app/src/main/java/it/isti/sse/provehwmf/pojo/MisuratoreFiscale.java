
package it.isti.sse.provehwmf.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class MisuratoreFiscale implements Serializable {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Nome")
    @Expose
    private String nome;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Modello")
    @Expose
    private String modello;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Ditta")
    @Expose
    private String ditta;
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
    @SerializedName("TimeMFStart")
    @Expose
    private String timeMFStart;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("TimeMFEnd")
    @Expose
    private String timeMFEnd;
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
    @SerializedName("ProveHW")
    @Expose
    private ProveHW proveHW;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * (Required)
     * 
     * @param nome
     *     The Nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The modello
     */
    public String getModello() {
        return modello;
    }

    /**
     * 
     * (Required)
     * 
     * @param modello
     *     The Modello
     */
    public void setModello(String modello) {
        this.modello = modello;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The ditta
     */
    public String getDitta() {
        return ditta;
    }

    /**
     * 
     * (Required)
     * 
     * @param ditta
     *     The Ditta
     */
    public void setDitta(String ditta) {
        this.ditta = ditta;
    }

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
     *     The timeMFStart
     */
    public String getTimeMFStart() {
        return timeMFStart;
    }

    /**
     * 
     * (Required)
     * 
     * @param timeMFStart
     *     The TimeMFStart
     */
    public void setTimeMFStart(String timeMFStart) {
        this.timeMFStart = timeMFStart;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The timeMFEnd
     */
    public String getTimeMFEnd() {
        return timeMFEnd;
    }

    /**
     * 
     * (Required)
     * 
     * @param timeMFEnd
     *     The TimeMFEnd
     */
    public void setTimeMFEnd(String timeMFEnd) {
        this.timeMFEnd = timeMFEnd;
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
     *     The proveHW
     */
    public ProveHW getProveHW() {
        return proveHW;
    }

    /**
     * 
     * (Required)
     * 
     * @param proveHW
     *     The ProveHW
     */
    public void setProveHW(ProveHW proveHW) {
        this.proveHW = proveHW;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
