package isti.cnr.sse.rest.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import isti.cnr.sse.rest.data.TipoProve;


@Generated("org.jsonschema2pojo")
public class Prova implements Serializable, Comparable<Prova> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SerializedName("nomeProva")
    @Expose
	public String nomeProva = new String();
	@SerializedName("DescProva")
    @Expose
	public String descrizioneProva = new String();
	
	@SerializedName("NRapportoProva")
	@Expose
	private String numeroRapportoProva;
	
	@SerializedName("nomeDitta")
	@Expose
	private String nomeDitta = new String();
	
	@SerializedName("nomeModello")
    @Expose
	private String nomeModello = new String();
	
	@SerializedName("note")
    @Expose
	private String note = new String();

	
	public TipoProve tp;
	
	public Esito stato = Esito.Incorso;
	
	
	// @Expose(serialize = false, deserialize = false)
	public transient ModelloMF mf = new ModelloMF();
	
	@SerializedName("allegati")
    @Expose
	public List<Allegato> listallegato = new ArrayList<>();

	@SerializedName("editated")
	public boolean selezionabile;
	
	
	@SerializedName("TimeStartPHW")
    @Expose
    private String timeStartPHW;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("TimeEndPHW")
    @Expose
    private String timeEndPHW;
    
	

	public Prova() {}


	public Prova(String nome, String desc, TipoProve tp, boolean sold, ModelloMF mf) {
		this.nomeProva = nome;
		this.descrizioneProva = desc;
		this.selezionabile = sold;
		this.tp  = tp; 
		this.stato = Esito.Incorso;
		if(mf!=null){
			this.mf = mf;
			setNomeModello(mf.getNomeModello());
			setNomeDitta(mf.getNomeDitta());
			setNumeroRapportoProva(mf.getNumeroRapportoProva());
		}
		
	}
	
	public Prova(TipoProve tp, String nrp) {
		
		this.tp  = tp; 
		this.stato = Esito.Incorso;
		setNumeroRapportoProva(nrp);
		
		
	}


    

	public String getTimeStartPHW() {
		return timeStartPHW;
	}


	public void setTimeStartPHW(String timeStartPHW) {
		this.timeStartPHW = timeStartPHW;
	}


	public String getTimeEndPHW() {
		return timeEndPHW;
	}


	public void setTimeEndPHW(String timeEndPHW) {
		this.timeEndPHW = timeEndPHW;
	}


	public String getNumeroRapportoProva() {
		return numeroRapportoProva;
	}

	public void setNumeroRapportoProva(String numeroRapportoProva) {
		this.numeroRapportoProva = numeroRapportoProva;
	}

	/*public ModelloMF getMf() {
		return mf;
	}


	public void setMf(ModelloMF mf) {
		this.mf = mf;
	}*/


	public void setListallegato(List<Allegato> listallegato) {
		this.listallegato = listallegato;
	}


	public Esito getStato() {
		return stato;
	}


	public void setStato(Esito state) {
		this.stato = state;
	}


	public TipoProve getTp() {
		return tp;
	}


	public void setTp(TipoProve tp) {
		this.tp = tp;
	}


	public String getNomeProva() {
		return nomeProva;
	}


	public void setNomeProva(String nomeProva) {
		this.nomeProva = nomeProva;
	}


	public String getDescrizioneProva() {
		return descrizioneProva;
	}


	public void setDescrizioneProva(String descrizioneProva) {
		this.descrizioneProva = descrizioneProva;
	}


	public boolean isSelezionabile() {
		return selezionabile;
	}
	public void setSelezionabile(boolean sold) {
		this.selezionabile = sold;
	}


	@Override
	public String toString() {
		return nomeProva;
	}


	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + (this.nomeProva != null ? this.nomeProva.hashCode() : 0);
		return hash;
	}

	


	public List<Allegato> getListallegato() {
		return listallegato;
	}


	public String getNomeDitta() {
		return nomeDitta;
	}


	public void setNomeDitta(String nomeDitta) {
		this.nomeDitta = nomeDitta;
	}


	public String getNomeModello() {
		return nomeModello;
	}


	public void setNomeModello(String nomeModello) {
		this.nomeModello = nomeModello;
	}
	
	


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public int compareTo(Prova o) {
		int numrp = this.numeroRapportoProva.compareTo(o.getNumeroRapportoProva());
		if(numrp!=0)
			return numrp;
		return this.getTp().compareTo(o.tp);
	}

	public boolean equals(Object o)
    {
        if(o instanceof Prova && ((Prova)o).tp.equals(this.tp ))
            return true;
        else
            return false;   
    }



}
