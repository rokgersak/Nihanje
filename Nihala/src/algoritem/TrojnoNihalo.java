package algoritem;

import gui.Nihalo;

public class TrojnoNihalo {

    private Nihalo nihalo;
    private double[] x = new double[4];
    private double[] y = new double[4];
    private double[] dolzina = new double[4];
    private double[] phi = new double[4];
    private double[] omega = new double[4];
    private double k1[] = new double[4];
    private double k2[] = new double[4];
    private double k3[] = new double[4];
    private double l1[] = new double[4];
    private double l2[] = new double[4];
    private double l3[] = new double[4];
    
    private static final double grav = 9.81; //Gravity
    private static final double dt = 0.0005; //Runge-Kutta-timestep
	
    public TrojnoNihalo() {
    	super();
    }
    
    public void zacetneVrednosti(Nihalo nihalo) {
    	this.nihalo = nihalo;
    	for (Integer tocka : nihalo.seznam.keySet()) {
    		if (tocka != 0) {
    			x[tocka] = nihalo.tocka(tocka).x;
    			y[tocka] = nihalo.tocka(tocka).y;
    			dolzina[tocka] = Math.sqrt((x[tocka]-x[tocka-1])*(x[tocka]-x[tocka-1]) + (y[tocka]-y[tocka-1])*(y[tocka]-y[tocka-1]));
    			double sirina = x[tocka]-x[tocka-1];
    			phi[tocka] = Math.asin(sirina/dolzina[tocka]);
    			omega[tocka] = 0.0;
    		} else {
    			x[tocka] = nihalo.tocka(tocka).x;
    			y[tocka] = nihalo.tocka(tocka).y;
    		}
    	}
    }
    
    public void RungeKutta() {
    	rungeStep1();
        rungeStep2();
        rungeStep3();
        phi[1] = phi[1] + (k1[0]+2*k1[1]+2*k1[2]+k1[3])/6;
        omega[1] = omega[1] + (l1[0]+2*l1[1]+2*l1[2]+l1[3])/6;
        phi[2] = phi[2] + (k2[0]+2*k2[1]+2*k2[2]+k2[3])/6; 
        omega[2] = omega[2] + (l2[0]+2*l2[1]+2*l2[2]+l2[3])/6;
        phi[3] = phi[3] + (k3[0]+2*k3[1]+2*k3[2]+k3[3])/6;
        omega[3] = omega[3] + (l3[0]+2*l3[1]+2*l3[2]+l3[3])/6;
  
    }

    /* force on Phi_1 */
    public double force1(double phi1, double omega1, double phi2, double omega2, double phi3, double omega3) {
    	double a = (10*grav*Math.sin(phi1)
				 + 4*grav*Math.sin(phi1-2*phi2)
				 - grav*Math.sin(phi1+2*phi2-2*phi3)
				 - grav*Math.sin(phi1-2*phi2+2*phi3)
				 + 4*omega1*omega1*Math.sin(2*phi1-2*phi2)
				 + 8*omega2*omega2*Math.sin(phi1-phi2)
				 + 2*omega3*omega3*Math.sin(phi1-phi3)
				 + 2*omega3*omega3*Math.sin(phi1-2*phi2+phi3))
				/(-10. + 4*Math.cos(2*phi1-2*phi2) + 2*Math.cos(2*phi2-2*phi3));
		return a;
    }

    /* force on Phi_2 */
    public double force2(double phi1, double omega1, double phi2, double omega2, double phi3, double omega3) {
    	double b = ( - 7*grav*Math.sin(2*phi1-phi2)
				 + 7*grav*Math.sin(phi2)
				 + grav*Math.sin(phi2-2*phi3)
				 + grav*Math.sin(2*phi1+phi2-2*phi3)
				 + 2*omega1*omega1*Math.sin(phi1+phi2-2*phi3)
				 - 14*omega1*omega1*Math.sin(phi1-phi2)
				 + 2*omega2*omega2*Math.sin(2*phi2-2*phi3)
				 - 4*omega2*omega2*Math.sin(2*phi1-2*phi2)
				 + 6*omega3*omega3*Math.sin(phi2-phi3)
				 - 2*omega3*omega3*Math.sin(2*phi1-phi2-phi3))
    			/(-10. + 4*Math.cos(2*phi1-2*phi2) + 2*Math.cos(2*phi2-2*phi3));
		return b;
    }

    /* force on Phi_3 */
    public double force3(double phi1, double omega1, double phi2, double omega2, double phi3, double omega3) {
    	double c = -2*Math.sin(phi2-phi3)*
				(grav*Math.cos(2*phi1-phi2)
			 	  + grav*Math.cos(phi2)
			 	  + 2*omega1*omega1*Math.cos(phi1-phi2)
				  + 2*omega2*omega2
			 	  + omega3*omega3*Math.cos(phi2-phi3)) 
				/(-5. + 2*Math.cos(2*phi1-2*phi2) + Math.cos(2*phi2-2*phi3));
    	return c;
    }

    /*public double energy() {
    	return 0.5*(   3*omega1*omega1 + 2*omega2*omega2 + omega3*omega3
    		   		 + 4*omega1*omega2*Math.cos(phi1-phi2)
    		   		 + 2*omega1*omega3*Math.cos(phi1-phi3)
    		   		 + 2*omega2*omega3*Math.cos(phi2-phi3))
    		   - grav*(3*Math.cos(phi1) + 2*Math.cos(phi2) + Math.cos(phi3));
    }*/

    public void rungeStep1()
    {
        k1[0] = dt*omega[1];
        l1[0] = dt*force1(phi[1], omega[1], phi[2], omega[2], phi[3], omega[3]);
        k1[1] = dt*(omega[1] + l1[0]/2);
        l1[1] = dt*force1(phi[1] + k1[0]/2, omega[1] + l1[0]/2, phi[2], omega[2], phi[3], omega[3]);
        k1[2] = dt*(omega[1] + l1[1]/2);
        l1[2] = dt*force1(phi[1] + k1[1]/2, omega[1] + l1[1]/2, phi[2], omega[2], phi[3], omega[3]);
        k1[3] = dt*(omega[1] + l1[2]);
        l1[3] = dt*force1(phi[1] + k1[2], omega[1] + l1[2], phi[2], omega[2], phi[3], omega[3]);
    }

    public void rungeStep2()
    {
        k2[0] = dt*omega[2];
        l2[0] = dt*force2(phi[1], omega[1], phi[2], omega[2], phi[3], omega[3]);
        k2[1] = dt*(omega[2] + l2[0]/2);
        l2[1] = dt*force2(phi[1], omega[1], phi[2] + k2[0]/2, omega[2] + l2[0]/2, phi[3], omega[3]);
        k2[2] = dt*(omega[2] + l2[1]/2);
        l2[2] = dt*force2(phi[1], omega[1], phi[2] + k2[1]/2, omega[2] + l2[1]/2, phi[3], omega[3]);
        k2[3] = dt*(omega[2] + l2[2]);
        l2[3] = dt*force2(phi[1], omega[1], phi[2] + k2[2], omega[2] + l2[2], phi[3], omega[3]);
    }

    public void rungeStep3()
    {
        k3[0] = dt*omega[3];
        l3[0] = dt*force3(phi[1], omega[1], phi[2], omega[2], phi[3], omega[3]);
        k3[1] = dt*(omega[3] + l3[0]/2);
        l3[1] = dt*force3(phi[1], omega[1], phi[2], omega[2], phi[3] + k3[0]/2, omega[3] + l3[0]/2);
        k3[2] = dt*(omega[3] + l3[1]/2);
        l3[2] = dt*force3(phi[1], omega[1], phi[2], omega[2], phi[3] + k3[1]/2, omega[3] + l3[1]/2);
        k3[3] = dt*(omega[3] + l3[2]);
        l3[3] = dt*force3(phi[1], omega[1], phi[2], omega[2], phi[3] + k3[2], omega[3] + l3[2]);
    }
    
    public void koncneVrednosti() {
        for (Integer tocka : nihalo.seznam.keySet()) {
    		if (tocka != 0) {
    			nihalo.tocka(tocka).x = x[tocka] = x[tocka - 1] + dolzina[tocka]*Math.sin(phi[tocka]);
    			nihalo.tocka(tocka).y = y[tocka] = y[tocka - 1] + dolzina[tocka]*Math.cos(phi[tocka]);
    		}
    	}
    }
}
