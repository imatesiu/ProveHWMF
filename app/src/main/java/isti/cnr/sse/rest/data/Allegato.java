
package isti.cnr.sse.rest.data;

import javax.annotation.Generated;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Allegato implements Serializable, Comparable<Allegato> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8351933523945481995L;


	/**
     * 
     * (Required)
     * 
     */
	
	@SerializedName("NRapportoProva")
	@Expose
	private String numeroRapportoProva;
	
    public String getNumeroRapportoProva() {
		return numeroRapportoProva;
	}

	public void setNumeroRapportoProva(String numeroRapportoProva) {
		this.numeroRapportoProva = numeroRapportoProva;
	}

	public void setTipoprova(String tipoprova) {
		this.tipoprova = tipoprova;
	}

	@SerializedName("Matricola")
    @Expose
    private String matricola;


    @SerializedName("TipoProva")
    @Expose
    private String tipoprova;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("TipoFile")
    @Expose
    private String tipo;

    @SerializedName("Nome")
    @Expose
    private String nome;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("Url")
    @Expose
    private String url;
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

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Userid")
    @Expose
    private String userid;

    @SerializedName("Modello")
    @Expose
    private String modello;



    private boolean edited;

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
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

  
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Allegato) {
            Allegato a = (Allegato) other;
            if(a.getNome().equals(this.getNome())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Allegato o) {

        int time = this.getTime().compareTo(o.getTime());
        if( time !=0 ){
            return time;
        }
        return this.getNome().compareTo(o.getNome());
    }

    public String getTipoprova() {
        return tipoprova;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String model) {
        modello = model;
    }

	public void init(String numeroRapportoProva2, String nomeModello, String nomeProva, String nome, String conttype) {
		this.numeroRapportoProva = numeroRapportoProva2;
		this.modello = nomeModello;
		this.tipoprova = nomeProva;
		this.nome = nome;
		this.tipo = conttype;
		
		
	}
	
	
    
    
    
    
    
}
