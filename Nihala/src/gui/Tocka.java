package gui;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Tocka {
	
	public Integer ime;
	public Integer masa;
	public Integer dusenje;

	public Double x;
	public Double y;
	public Double polmer;
	
	public Tocka(Integer naziv) {
		super();
		ime = naziv;
		masa = 1;
		dusenje = 1;
	}
	
	// Narisi tocko
	public void narisiTocko(Graphics g){
		if (masa == 2){
			polmer = (double) 20;
			g.fillOval(x.intValue() - 10, y.intValue() - 10, polmer.intValue(), polmer.intValue());
		} else if (masa == 1){
			polmer = (double) 10;
			g.fillOval(x.intValue() - 5, y.intValue() - 5, polmer.intValue(), polmer.intValue());
		}
	}

	// Naredi polje
	public Rectangle2D getPolje() {
		return new Rectangle2D.Double(x-polmer, y-polmer, 2*polmer, 2*polmer);
	}
	
	
}
