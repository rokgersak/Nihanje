package algoritem;

import gui.Nihalo;

public class DvojnoNihalo {

    private Nihalo nihalo;
    private double[] x = new double[3];
    private double[] y = new double[3];
    private double[] dolzina = new double[3];
    public double[] phi = new double[3];
    public double[] omega = new double[3];
    private int[] masa = new int[3];
    private int[] dusenje = new int[3];
    private double k1[] = new double[4];
    private double k2[] = new double[4]; 
    private double l1[] = new double[4];
    private double l2[] = new double[4];
    
    private static final double grav = 9.81; //Gravity
    private static final double dt = 0.0005; //Runge-Kutta-timestep
    
    public DvojnoNihalo() {
    	super();
    }
        
    public void zacetneVrednosti(Nihalo nihalo) {
    	this.nihalo = nihalo;
    	for (Integer tocka : nihalo.seznam.keySet()) {
    		if (tocka != 0) {
    			x[tocka] = nihalo.tocka(tocka).x;
    			y[tocka] = nihalo.tocka(tocka).y;
    			masa[tocka] = nihalo.tocka(tocka).masa;
    			dusenje[tocka] = nihalo.tocka(tocka).dusenje;
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
        phi[1] = phi[1] + (k1[0]+2*k1[1]+2*k1[2]+k1[3])/6;
        omega[1] = omega[1] + (l1[0]+2*l1[1]+2*l1[2]+l1[3])/6; 
        phi[2] = phi[2] + (k2[0]+2*k2[1]+2*k2[2]+k2[3])/6;
        omega[2] = omega[2] + (l2[0]+2*l2[1]+2*l2[2]+l2[3])/6;
  
    }
    
    /* force on Phi_1 */
    public double force1(double phi1, double phi2, double omega1, double omega2) {
    	double a = -(grav*(2*masa[1]*Math.sin(phi1) + masa[2]*Math.sin(phi1) + masa[2]*Math.sin(phi1-2*phi2))
    			+ masa[2]*omega1*omega1*Math.sin(2*phi1-2*phi2) + 2*masa[2]*omega2*omega2*Math.sin(phi1-phi2))/
    			(2*masa[1] + masa[2] - masa[2]*Math.cos(2*phi1-2*phi2)) - 0.1*dusenje[1]*omega1;
        return a;
    }

    /* force on Phi_2 */
    public double force2(double phi1, double phi2, double omega1, double omega2) {
    	double a = (2*Math.sin(phi1-phi2)*(grav*(masa[1] + masa[2])*Math.cos(phi1) + 
    			(masa[1] + masa[2])*omega1*omega1 + masa[2]*omega2*omega2*Math.cos(phi1-phi2)))/ 
    			(2*masa[1] + masa[2] - masa[2]*Math.cos(2*phi1-2*phi2)) - 0.1*dusenje[2]*omega2;
        return a;
    }

    /*public double energy() {
    	return 0.5*masa[1]*omega[1]*omega[1] + 0.5*masa[2]*omega[2]*omega[2] + 0.5*masa[2]*omega[1]*omega[1]
    		   + masa[2]*omega[1]*omega[2]*Math.cos(phi[1]-phi[2])
    		   - grav*(masa[1]*Math.cos(phi[1]) + masa[2]*Math.cos(phi[1]) + masa[2]*Math.cos(phi[2]));
    }*/

    public void rungeStep1() {
        k1[0] = dt*omega[1];
        l1[0] = dt*force1(phi[1], phi[2], omega[1], omega[2]);
        k1[1] = dt*(omega[1] + l1[0]/2);
        l1[1] = dt*force1(phi[1] + k1[0]/2, phi[2], omega[1] + l1[0]/2, omega[2]);
        k1[2] = dt*(omega[1] + l1[1]/2);
        l1[2] = dt*force1(phi[1] + k1[1]/2, phi[2], omega[1] + l1[1]/2, omega[2]);
        k1[3] = dt*(omega[1] + l1[2]);
        l1[3] = dt*force1(phi[1] + k1[2], phi[2], omega[1] + l1[2], omega[2]);
    }

    public void rungeStep2() {
        k2[0] = dt*omega[2];
        l2[0] = dt*force2(phi[1], phi[2], omega[1], omega[2]);
        k2[1] = dt*(omega[2] + l2[0]/2);
        l2[1] = dt*force2(phi[1], phi[2] + k2[0]/2, omega[1], omega[2] + l2[0]/2);
        k2[2] = dt*(omega[2] + l2[1]/2);
        l2[2] = dt*force2(phi[1], phi[2] + k2[1]/2, omega[1], omega[2] + l2[1]/2);
        k2[3] = dt*(omega[2] + l2[2]);
        l2[3] = dt*force2(phi[1], phi[2] + k2[2], omega[1], omega[2] + l2[2]);
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
