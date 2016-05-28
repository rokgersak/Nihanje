package gui;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import algoritem.Razporeditev;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	
	private Container plosca;
	private Menu menu;
	private Platno platno;
	public Nihalo nihalo = Nihalo.povezave(3);
	private Razporeditev razporeditev = new Razporeditev();

	public Okno() throws HeadlessException {
		super();
		this.setTitle("Simulacija nihanja");
		this.setLayout(new GridBagLayout());
		plosca = this.getContentPane(); 
		
		//Menu
		menu = new Menu(this);
		this.setJMenuBar(menu);
		
		//Platno
		platno = new Platno(this.nihalo);
		plosca.add(platno);
		
		//Razporeditev
		razporeditev.zacetnaRazporeditev(nihalo, 500, 180, 100);
	}

	public void osvezi() {
		platno = new Platno(nihalo);
		razporeditev.zacetnaRazporeditev(nihalo, 500, 180, 100);
		plosca.removeAll();
		plosca.add(platno);
		plosca.revalidate();
		plosca.repaint();
	}
}
