package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Razporeditev implements MouseListener {

	private Okno okno;
	private Nihalo nihalo;
	private int izbrana = 0;
	private int velikost;
	private int sirina;
	private int visina;
	
	public Razporeditev(Okno okno) {
		super();
		this.okno = okno;
		this.nihalo = okno.getNihalo();
		
		// Poslusalec
		this.okno.getPlatno().addMouseListener(this);
	}

	public void ponastaviRazporeditev(Nihalo nihalo, int x, int y, int r) {
		this.nihalo = nihalo;
		int i = 0;
		for (Integer tocka : nihalo.seznam.keySet()) {
			nihalo.tocka(tocka).x = (double) (r * i + x);
			nihalo.tocka(tocka).y = (double) (r * i + y);
			i++;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		velikost = 2*nihalo.seznam.size();
		sirina = (Platno.sirina)/(velikost);
		visina = (Platno.visina)/(velikost);
		for (Integer tocka : nihalo.seznam.keySet()) {
			if (tocka != 0 && nihalo.tocka(tocka).getPolje().contains(e.getPoint())) {
					izbrana = tocka ;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Integer tocka : nihalo.seznam.keySet()) {
			double noviX = e.getX();
			double noviY = e.getY();
			if (tocka != 0 && tocka == izbrana && noviX > sirina && noviX < sirina*(velikost - 1) && noviY > 0 && noviY < visina*(velikost - 1)) {
				nihalo.tocka(tocka).x = noviX;
				nihalo.tocka(tocka).y = noviY;
				okno.ustavi();
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