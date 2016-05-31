package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Nihalo nihalo = Nihalo.tocke(3);
	private Razporeditev razporeditev;
	
	private Container plosca;
	private Platno platno;
	private GridBagConstraints platnoLayout = new GridBagConstraints();
	private JButton gumb;
	private GridBagConstraints gumbLayout = new GridBagConstraints();

	public Okno() throws HeadlessException {
		super();
		this.setTitle("Simulacija nihanja");
		this.setJMenuBar(new Menu(this));
		this.setLayout(new GridBagLayout());
		
		// Plosca
		plosca = this.getContentPane();
		pripraviPlosco(nihalo);
		
		// Razporeditev
		razporeditev = new Razporeditev(this);
		razporeditev.zacetnaRazporeditev(nihalo, 500, 100, 100);
	}		
	
	public void pripraviPlosco(Nihalo nihalo){
		platno = new Platno(nihalo);
		platnoLayout.gridx = 0;
		platnoLayout.gridy = 0;
		plosca.add(platno, platnoLayout);
		gumb = new JButton("Poženi");
		gumbLayout.gridx = 0;
		gumbLayout.gridy = 1;
		gumbLayout.weightx = 10.0;
		gumbLayout.fill = GridBagConstraints.CENTER;
		plosca.add(gumb, gumbLayout);
	}
	
	public void ponastavi(Nihalo ponastavljeno) {
		razporeditev.zacetnaRazporeditev(ponastavljeno, 500, 100, 100);
		osvezi(ponastavljeno);
	}
	
	public void osvezi(Nihalo osvezeno) {
		plosca.removeAll();
		nihalo = osvezeno;
		pripraviPlosco(nihalo);
		razporeditev = new Razporeditev(this);
		plosca.revalidate();
		plosca.repaint();
	}

	public Platno getPlatno() {
		return platno;
	}
	
	public Nihalo getNihalo() {
		return nihalo;
	}
}
