package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel {
	
 	private int sirina = 1000;
	private int visina = 500;
	private Nihalo nihalo;
	
	public Platno(Nihalo nihalo) {
		super();
		this.nihalo = nihalo;
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(sirina, visina));
	}
	
	// Narisemo komponente
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (Object tocka : nihalo.seznam.keySet()){
			int zacetniX = nihalo.tocka(tocka).x.intValue();
			int zacetniY = nihalo.tocka(tocka).y.intValue();
			for (Tocka soseda : nihalo.tocka(tocka).sosedi){
				int koncniX = soseda.x.intValue();
				int koncniY = soseda.y.intValue();
				g.drawLine(zacetniX, zacetniY, koncniX, koncniY);
			}
		}
		g.setColor(Color.BLUE);
		for (Object tocka : nihalo.seznam.keySet()){
			if (nihalo.tocka(tocka).masa == 3){
				g.fillOval(nihalo.tocka(tocka).x.intValue() - 15, nihalo.tocka(tocka).y.intValue() - 15, 30, 30);
			} else 	if (nihalo.tocka(tocka).masa == 2){
				g.fillOval(nihalo.tocka(tocka).x.intValue() - 10, nihalo.tocka(tocka).y.intValue() - 10, 20, 20);
			} else{
				g.fillOval(nihalo.tocka(tocka).x.intValue() - 5, nihalo.tocka(tocka).y.intValue() - 5, 10, 10);
			}
		}
		g.setColor(Color.RED);
		for (Object tocka : nihalo.seznam.keySet()){
			if (nihalo.tocka(tocka).dusenje == true){
				if (nihalo.tocka(tocka).masa == 3){
					g.fillOval(nihalo.tocka(tocka).x.intValue() - 15, nihalo.tocka(tocka).y.intValue() - 15, 30, 30);
				} else 	if (nihalo.tocka(tocka).masa == 2){
					g.fillOval(nihalo.tocka(tocka).x.intValue() - 10, nihalo.tocka(tocka).y.intValue() - 10, 20, 20);
				} else{
					g.fillOval(nihalo.tocka(tocka).x.intValue() - 5, nihalo.tocka(tocka).y.intValue() - 5, 10, 10);
				}
			}
        }
		g.setColor(Color.GRAY);
		g.fillRect(410, 178, 180, 4);
		g.fillOval(495, 175, 10, 10);
	}
}
