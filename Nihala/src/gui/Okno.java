package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import algoritem.Vlakno;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Nihalo nihalo = Nihalo.tocke(3);
	private Vlakno vlakno;
	
	private Menu menu;
	private Container plosca;
	private Platno platno;
	private GridBagConstraints platnoLayout = new GridBagConstraints();
	private Orodjarna orodjarna;
	private GridBagConstraints orodjarnaLayout = new GridBagConstraints();
	private Razporeditev razporeditev;

	public Okno() throws HeadlessException {
		super();
		setTitle("Simulacija nihanja");
		setLayout(new GridBagLayout());
		setResizable(false);
		
		// Plosca
		menu = new Menu(this);
		setJMenuBar(menu);
		plosca = this.getContentPane();
		pripraviPlosco();
		razporeditev.ponastaviRazporeditev(nihalo, 500, 100, 100);
		
		// Algoritem
		vlakno = new Vlakno(this);
	}		
	
	public void pripraviPlosco() {
		plosca.setBackground(Color.white);
		plosca.setPreferredSize(new Dimension(1100, 700));
		platno = new Platno(nihalo);
		platnoLayout.gridx = 0;
		platnoLayout.gridy = 0;
		plosca.add(platno, platnoLayout);
		orodjarna = new Orodjarna(this);
		orodjarnaLayout.gridx = 0;
		orodjarnaLayout.gridy = 1;
		plosca.add(orodjarna, orodjarnaLayout);
		razporeditev = new Razporeditev(this);
	}
	
	public void ponastavi(Nihalo ponastavljeno) {
		ustavi();
		nihalo = ponastavljeno;
		menu.ponastaviMenu(nihalo);
		platno.ponastaviPlatno(nihalo);
		razporeditev.ponastaviRazporeditev(nihalo, 500, 100, 100);
		plosca.repaint();
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
			vlakno.ustaviVlakno();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		orodjarna.pripraviGumb1(orodjarna.pozeniGumb);
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
