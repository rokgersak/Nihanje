package algoritem;

import gui.Nihalo;

public class Razporeditev {


	public Razporeditev() {
		super();
	}

	public void zacetnaRazporeditev(Nihalo nihalo, int x, int y, int r){
		int i = 0;
		for (Object tocka : nihalo.seznam.keySet()){
			nihalo.tocka(tocka).x = (double) (r * i + x);
			nihalo.tocka(tocka).y = (double) (r * i + y);
			i++;
		}
	}
	
}
