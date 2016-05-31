package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Razporeditev implements MouseListener {

	private Okno okno;
	private Platno platno;
	private Nihalo nihalo;
	private Integer izbrana = 0;
	
	public Razporeditev(Okno okno) {
		super();
		this.okno = okno;
		this.platno = okno.getPlatno();
		this.nihalo = okno.getNihalo();
		
		// Poslusalec
		platno.addMouseListener(this); 	
	}

	public void zacetnaRazporeditev(Nihalo nihalo, int x, int y, int r){
		int i = 0;
		for (Integer tocka : nihalo.seznam.keySet()){
			nihalo.tocka(tocka).x = (double) (r * i + x);
			nihalo.tocka(tocka).y = (double) (r * i + y);
			i++;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Integer tocka : nihalo.seznam.keySet()) {
			if (tocka != 0 && nihalo.tocka(tocka).getPolje().contains(e.getPoint())){
					izbrana = tocka ;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Integer tocka : nihalo.seznam.keySet()) {
			double noviX = e.getX();
			double noviY = e.getY();
			if (tocka != 0 && tocka == izbrana && noviX > 0 && noviX < Platno.sirina && noviY > 0 && noviY < Platno.visina){
				nihalo.tocka(tocka).x = noviX;
				nihalo.tocka(tocka).y = noviY;
				okno.osvezi(nihalo);
			}
		}
		izbrana = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}