package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import algoritem.Vlakno;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Nihalo nihalo = Nihalo.tocke(3);
	private Razporeditev razporeditev;
	private Vlakno vlakno;
	
	private Container plosca;
	private Platno platno;
	private GridBagConstraints platnoLayout = new GridBagConstraints();
	private Orodjarna gumb;
	private GridBagConstraints gumbLayout = new GridBagConstraints();

	public Okno() throws HeadlessException {
		super();
		setTitle("Simulacija nihanja");
		setJMenuBar(new Menu(this));
		setLayout(new GridBagLayout());
		
		// Plosca
		plosca = this.getContentPane();
		plosca.setBackground(Color.white);
		pripraviPlosco();
		razporeditev.ponastaviRazporeditev(nihalo, 500, 100, 100);
		
		// Algoritem
		vlakno = new Vlakno(this);
	}		
	
	public void pripraviPlosco() {
		platno = new Platno(nihalo);
		platnoLayout.gridx = 0;
		platnoLayout.gridy = 0;
		plosca.add(platno, platnoLayout);
		gumb = new Orodjarna(this);
		gumbLayout.gridx = 0;
		gumbLayout.gridy = 1;
		plosca.add(gumb, gumbLayout);
		razporeditev = new Razporeditev(this);
	}
	
	public void ponastavi(Nihalo ponastavljeno) {
		nihalo = ponastavljeno;
		platno.ponastaviPlatno(nihalo);
		razporeditev.ponastaviRazporeditev(nihalo, 500, 100, 100);
		ustavi();
	}
	
	public void pozeni() {
		try {
			vlakno.ponastaviAlgoritem(nihalo);
			vlakno.narediVlakno();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void ustavi() {
		try {
			vlakno.prekiniAlgoritem = true;
			vlakno.uniciVlakno();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		plosca.repaint();
	}

	public Container getPlosca() {
		return plosca;
	}

	public Platno getPlatno() {
		return platno;
	}
	
	public Nihalo getNihalo() {
		return nihalo;
	}
}
