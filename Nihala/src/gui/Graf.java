package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Graf {
	
	public Map<Object, Tocka> seznam;
	
	public Graf(){
		this.seznam = new HashMap<Object, Tocka>();
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
		if (tocka1.ime != tocka2.ime && this.seznam.containsKey(tocka1.ime) && this.seznam.containsKey(tocka2.ime)  && !tocka1.sosedi.contains(tocka2.ime)){
			tocka1.sosedi.add(tocka2);
			tocka2.sosedi.add(tocka1);
		}
	}
	
	// Odstranimo povezavo med tockama.
	public void odstraniPovezavo(Tocka tocka1, Tocka tocka2){
		if (tocka1.ime != tocka2.ime && this.seznam.containsKey(tocka1.ime) && this.seznam.containsKey(tocka2.ime)  && !tocka1.sosedi.contains(tocka2.ime)){
			tocka1.sosedi.remove(tocka2);
			tocka2.sosedi.remove(tocka1);
		}	
	}
	
	// Odstranimo tocko.
	public void odstraniTocko(Tocka tocka1){
		if (seznam.containsKey(tocka1.ime)){
			for (Tocka tocka : tocka1.sosedi){
				odstraniPovezavo(tocka1, tocka);
			}
			seznam.remove(tocka1);
		}
	}
	
	// Seznam n tock.
	public static Graf seznamTock(int n){
		Graf x = new Graf();
		for (int i = 0; i < n; i++){
			x.dodajTocko(new Tocka(i));
		}
		return x;
	}
	
	// Seznam povezav zaporednih tock.
	public static Graf seznamPovezav(int n){
		Graf x = seznamTock(n);
		for (int i = 0; i < n; i++){
			x.dodajPovezavo(x.tocka(i), x.tocka((i+1)%n));
		}
		return x;
	}
	
	// Preveri, èe je graf povezan.
	public boolean povezan(){
		List<Object> kljuè = new ArrayList<Object>(this.seznam.keySet());
		List<Set<Tocka>> komponente = new LinkedList<Set<Tocka>>();
		
		for (int i = 0; i < this.seznam.size(); i++){
			Object tocka = kljuè.get(i);
			if (komponente.size() != 0){
				for (int j = 0; j < komponente.size(); j++){
					if(komponente.get(j).contains(tocka)){
						komponente.get(j).addAll(this.seznam.get(tocka).sosedi);
						break;
					} else {
						if (j + 1 == komponente.size())
							return false;
					}
				}
			} else {
				komponente.add(this.seznam.get(tocka).sosedi);
			}
		}
		return true;
	}
	
	// Koliko komponent je v seznamu.
	public int steviloKomponent(){
		Vector<Object> kljuè = new Vector<Object>(this.seznam.keySet());
		Vector<Set<Tocka>> komponente = new Vector<Set<Tocka>>();
		
		for (int i = 0; i < this.seznam.size(); i++){
			Object tocka = kljuè.get(i);
			if (komponente.size() != 0){
				for (int j = 0; j < komponente.size(); j++){
					if(komponente.get(j).contains(tocka)){
						komponente.get(j).addAll(this.seznam.get(tocka).sosedi);
						break;
					} else {
						if (j + 1 == komponente.size())
							break;
					}
				}
			} else {
				komponente.add(this.seznam.get(tocka).sosedi);
			}
		}
		return komponente.size();
	}
}
