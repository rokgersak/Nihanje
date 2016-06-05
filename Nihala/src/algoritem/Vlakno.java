package algoritem;

import gui.Nihalo;
import gui.Okno;

public  class Vlakno {
	
	private Okno okno;
    private Thread animator;
	private DvojnoNihalo algoritem1 = new DvojnoNihalo();
	private TrojnoNihalo algoritem2 = new TrojnoNihalo();
    private int velikost;
    private int korak;
    public boolean prekiniAlgoritem;

    
    public Vlakno(Okno okno) {
    	super();
    	this.okno = okno;
    }
    
	public void ponastaviAlgoritem(Nihalo nihalo) {
    	velikost = (int) nihalo.seznam.size();
    	if (velikost == 3 && animator == null) {
    		algoritem1.zacetneVrednosti(nihalo);
    	} else if (velikost == 4 && animator == null) {
    		algoritem2.zacetneVrednosti(nihalo);
    	}
	}
    
    public void narediVlakno() throws InterruptedException {
    	uniciVlakno();
    	animator = new Thread(new Runnable() {
			@Override
			public void run() {
				pozeniVlakno();				
			}
		});
    	animator.start();
    }
    
    public void pozeniVlakno() {
    	korak = 0;
        while(Thread.currentThread() == animator) {
        	if (velikost == 3) {
        		algoritem1.RungeKutta();
            	if (korak % 20 == 0) {
    				try {
    					Thread.sleep(8);
    				} catch(InterruptedException e) {}
    				algoritem1.koncneVrednosti(); 
                    okno.getPlosca().repaint();
            	}
        	} else if (velikost == 4) {
        		algoritem2.RungeKutta();
        		if (korak % 20 == 0) {
        			try {
        				Thread.sleep(8);
        			} catch(InterruptedException e) {}
        			algoritem2.koncneVrednosti(); 
        			okno.getPlosca().repaint();
        		}
        	}
        	if (prekiniAlgoritem == true) {
        		break;
        	}
			korak++;
        }
    }
    
    public void uniciVlakno() throws InterruptedException {
    	animator = null;
    	prekiniAlgoritem = false;
    }
}


