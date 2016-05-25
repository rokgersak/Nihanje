package gui;

import java.util.HashSet;
import java.util.Set;

public class Tocka {
	
	public Object ime;
	public Set<Tocka> sosedi = new HashSet<Tocka>();
	public boolean dusenje;
	public int masa;

	public Double x;
	public Double y;
	
	public Tocka(Object naziv) {
		super();
		this.ime = naziv;
	}
}
