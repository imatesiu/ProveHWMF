package isti.cnr.sse.rest.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Ditte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508221557887925709L;


	
	
	@SerializedName("ListaDitte")
    @Expose
	private List<Ditta> listaDitte = new  ArrayList<>();

	public List<Ditta> getListaDitte() {
		return listaDitte;
	}


	public void setListaDitte(List<Ditta> listaDitte) {
		this.listaDitte = listaDitte;
	}


	@Override
	public String toString() {
		return "Ditte [listaDitte=" + listaDitte + "]";
	}


	public List<String> getDitteString() {
		List<String> l = new ArrayList<>();
		for (Ditta ditta : listaDitte) {
			l.add(ditta.getNomeDitta());
		}
		return l;
	}

	
	
	
	
	

}
