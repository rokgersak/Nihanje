package gui;

import java.util.HashMap;
import java.util.Map;

public class Nihalo {
	
	public Map<Integer, Tocka> seznam = new HashMap<Integer, Tocka>();
	
	public Nihalo(){
		super();
	}
	
	// Nasa tocka.
	public Tocka tocka(Integer naziv){
		return seznam.get(naziv);
	}
	
	// Dodamo tocko v seznam.
	public void dodajTocko(Tocka tocka){
		if (!seznam.containsKey(tocka.ime)){
			seznam.put(tocka.ime, tocka);
		}
	}
	
	// Naredimo nihalo iz n tock.
	public static Nihalo tocke(int n){
		Nihalo x = new Nihalo();
		for (int i = 0; i < n; i++){
			x.dodajTocko(new Tocka(i));
		}
		return x;
	}
}
