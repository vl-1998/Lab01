package it.polito.tdp.parole.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Parole {
	LinkedList <String> elenco = new LinkedList <String>();

	public Parole() {
		//TODO
	}
	
	public void addParola(String p) {
			elenco.add(p);
		//TODO
	}
	
	public List<String> getElenco() {
		//TODO
		LinkedList <String> lTemp = elenco;
		Collections.sort(lTemp, new Comparator <String>(){
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				
				return o1.compareTo(o2);
			}
		});
			
		return lTemp;
	}
	
	public void cancelWord (String p) {
		for (String s : elenco) {
			if (s!=null) {
				if (s.compareTo(p)==0) {
					elenco.remove(s);
				}
			}
		}	
	}
	
	public void reset() {
		// TODO
		elenco.removeAll(elenco);
	}

}
