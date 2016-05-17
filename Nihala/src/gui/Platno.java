package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel {
	
 	private int sirina = 1280;
	private int visina = 720;
	public Graf graf;
	
	public Platno(Okno okno) {
		super();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(sirina, visina));
		this.graf = okno.graf;
	}

	public void razporedi(int x, int y, int r){
		int i = 0;
		for (Object tocka : graf.seznam.keySet()){
			graf.tocka(tocka).x = (double) (r * i + x);
			graf.tocka(tocka).y = (double) (r * i + y);
			i++;
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		for (Object tocka : graf.seznam.keySet()){
			int zacetniX = graf.tocka(tocka).x.intValue();
			int zacetniY = graf.tocka(tocka).y.intValue();
			for (Tocka soseda : graf.tocka(tocka).sosedi){
				int koncniX = soseda.x.intValue();
				int koncniY = soseda.y.intValue();
				
				g.drawLine(zacetniX, zacetniY, koncniX, koncniY);
			}
		}
		for (Object tocka : graf.seznam.keySet()){
			g.fillOval(graf.tocka(tocka).x.intValue() - 10, graf.tocka(tocka).y.intValue() - 10, 20, 20);
		}
	}
}
