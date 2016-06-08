package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel {
	
	private Nihalo nihalo;
 	public static int sirina = 1000;
	public static int visina = 550;
	
	public Platno(Nihalo nihalo) {
		super();
		this.nihalo = nihalo;
		setBackground(Color.white);
		setPreferredSize(new Dimension(sirina, visina));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
		
	public void ponastaviPlatno(Nihalo nihalo) {
		this.nihalo = nihalo;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(410, 98, 180, 4);
		for (Integer tocka : nihalo.seznam.keySet()){
			narisiPovezave(g, tocka);
			narisiTocke(g, tocka);
		}
	}
		
	// Narisemo povezave
	public void narisiPovezave(Graphics g, Integer tocka){
		g.setColor(Color.BLACK);
		Integer velikost = (int) nihalo.seznam.size() - 1;
		if (tocka < velikost){
			int zacetniX = nihalo.tocka(tocka).x.intValue();
			int zacetniY = nihalo.tocka(tocka).y.intValue();
			int koncniX = nihalo.tocka(tocka+1).x.intValue();
			int koncniY = nihalo.tocka(tocka+1).y.intValue();
			g.drawLine(zacetniX, zacetniY, koncniX, koncniY);
		}
    }
	
	// Narisemo tocke
	public void narisiTocke(Graphics g, Integer tocka){
		if (tocka == 0){
			g.setColor(Color.BLACK);
			g.fillOval(495, 95, 10, 10);
		} else{
			if (nihalo.tocka(tocka).dusenje == 1){
				g.setColor(Color.BLUE);
				nihalo.tocka(tocka).narisiTocko(g);
			} else{
				g.setColor(Color.RED);
				nihalo.tocka(tocka).narisiTocko(g);
			}
		}
    }

}
