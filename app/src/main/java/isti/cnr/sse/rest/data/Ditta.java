package isti.cnr.sse.rest.data;

import android.support.v4.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Ditta  implements Serializable,Comparable<Ditta> {
	
	private static final long serialVersionUID = -7134057343859286897L;
	
	
	@SerializedName("nomeDitta")
	@Expose
	private String nomeDitta = new String();
	
	@SerializedName("Indirizzo")
	@Expose
	private String indirizzo = new String();;
	
	
	@SerializedName("PIVA_CF")
	@Expose
	private String piva = new String();
	
	@SerializedName("ModelloMisuratoreFiscale")
    @Expose
	private List<ModelloMF> misuratoriFiscali = new  ArrayList<>();
	
	public Ditta (){
		
	}
	
	

	public Ditta(String nomeDitta, String indirizzo, String piva) {
		super();
		this.nomeDitta = nomeDitta;
		this.indirizzo = indirizzo;
		this.piva = piva;
	}
	
	

	


	public Ditta(String nomeDitta, String indirizzo, String piva, List<ModelloMF> misuratoriFiscali) {
		super();
		this.nomeDitta = nomeDitta;
		this.indirizzo = indirizzo;
		this.piva = piva;
		this.misuratoriFiscali = misuratoriFiscali;
	}

	
	


	public List<ModelloMF> getMisuratoriFiscali() {
		if(misuratoriFiscali!=null){
			return misuratoriFiscali;
		}
		return misuratoriFiscali =  new ArrayList<ModelloMF>();
	}



	public void setMisuratoriFiscali(List<ModelloMF> misuratoriFiscali) {
		this.misuratoriFiscali = misuratoriFiscali;
	}



	public String getNomeDitta() {
		return nomeDitta;
	}



	public void setNomeDitta(String nomeDitta) {
		this.nomeDitta = nomeDitta;
	}



	public String getIndirizzo() {
		return indirizzo;
	}



	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}



	public String getPiva() {
		return piva;
	}



	public void setPiva(String piva) {
		this.piva = piva;
	}



	


	@Override
	public int compareTo(Ditta o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
	public Pair<List<String>,List<String>> getInfoMF(){
		List<String> r = new ArrayList<String>();
		List<String> l = new ArrayList<String>();
		
		for(ModelloMF m : getMisuratoriFiscali()){
			r.add(m.getNomeModello());
			l.add(m.getNumeroRapportoProva());
		}
		
		
		return Pair.create(l, r);
	}


	

	@Override
	public String toString() {
		return  nomeDitta;
	}



	public List<ModelloMF> queryByName(String query) {
		List<ModelloMF> queried = new ArrayList<ModelloMF>();

		for(ModelloMF player: this.misuratoriFiscali){

			if(player.getNomeModello().startsWith(query)){

				queried.add(player);

			}

		}

		return queried;

		
	}
	
	

	
	
}
