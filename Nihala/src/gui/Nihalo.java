package gui;

import java.util.HashMap;
import java.util.Map;

public class Nihalo {
	
	public Map<Object, Tocka> seznam = new HashMap<Object, Tocka>();
	
	public Nihalo(){
		super();
	}
	
	// Nasa tocka.
	public Tocka tocka(Object naziv){
		return seznam.get(naziv);
	}
	
	// Dodamo tocko v seznam.
	public void dodajTocko(Tocka tocka){
		if (!seznam.containsKey(tocka.ime)){
			seznam.put(tocka.ime, tocka);
		}
	}
	
	// Dodamo povezavo med tockama.
	public void dodajPovezavo(Tocka tocka1, Tocka tocka2){
		if (this.seznam.containsKey(tocka1.ime) && this.seznam.containsKey(tocka2.ime) && !tocka1.sosedi.contains(tocka2.ime)){
			tocka1.sosedi.add(tocka2);
			tocka2.sosedi.add(tocka1);
		}
	}
	
	// Dodamo/odstranimo dusenje.
	public void dodajDusenje(Tocka tocka){
		tocka.dusenje = !tocka.dusenje;
	}

	// Povecaj/zmanjsaj maso tocke.
	public void dodajMaso(Tocka tocka, int masa){
		tocka.masa = masa;
	}
	
	// Naredimo n tock.
	public static Nihalo tocke(int n){
		Nihalo x = new Nihalo();
		for (int i = 0; i < n; i++){
			x.dodajTocko(new Tocka(i));
		}
		return x;
	}
	
	// Naredimo nihalo iz n-1 tock.
	public static Nihalo povezave(int n){
		Nihalo x = tocke(n);
		for (int i = 0; i < n-1; i++){
			x.dodajPovezavo(x.tocka(i), x.tocka(i+1));
		}
		return x;
	}
}
