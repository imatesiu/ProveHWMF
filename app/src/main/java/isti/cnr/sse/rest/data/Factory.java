package isti.cnr.sse.rest.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Factory {

	private List<Ditta> list;
	private Map<String, Ditta> map;

	public Factory() {
		map = Init.getini();
		list = new ArrayList<>(map.values());
	}
	
	public Map<String, Ditta> getMap(){
		
		return map;
	}
	
	public Ditte getDitte(){
		Ditte d = new Ditte();
		d.setListaDitte(list);
		return d;
	}

	public static List<String> getNomeDitta(List<Ditta> d){
		List<String> r = new ArrayList<String>();
		for (Ditta e : d) {
			r.add(e.getNomeDitta());
		}

		return r;
	}

	public static List<String> getNomeModello(List<Ditta> d, String ditta){
		List<String> r = new ArrayList<String>();
		for (Ditta e : d) {
			if(e.getNomeDitta().equals(ditta)){

				return e.getInfoMF().first;
			}else{
				r.addAll(e.getInfoMF().second);
			}

		}



		return r;

	}


	public List<Ditta> queryByName(String name){

		// Assumed search using the startsWith

		List<Ditta> queried = new ArrayList<Ditta>();

		for(Ditta player: this.list){

			if(player.getNomeDitta().startsWith(name)){

				queried.add(player);

			}

		}

		return queried;
	}

	public List<Ditta> getList() {
		// TODO Auto-generated method stub
		return list;
	}


}
