package algoritem;

public class DoublePendulum {
	
    private double phi1, omega1; //coordinates and velocities
    private double phi2, omega2; //coordinates and velocities
    private double m1, m2; //masses
    private int visc; //friction
    private static final double grav = 9.81; //Gravity
    private static final double dt = 0.0005; //Runge-Kutta-timestep
    private double k1[] = new double[4]; //Runge-Kutta
    private double k2[] = new double[4]; //Runge-Kutta
    private double l1[] = new double[4]; //Runge-Kutta
    private double l2[] = new double[4]; //Runge-Kutta
    private double initial[] = new double[4]; //save initial values
    private int step;

    
    /* force on Phi_1 */
    public double forcephi1(double phi1,double phi2,double omega1,double omega2)
    {
        return -( grav*(   2*m1*Math.sin(phi1)
        		  	     + m2*Math.sin(phi1)
        		  	     + m2*Math.sin(phi1-2*phi2)
        		 	   )
        		  + m2*omega1*omega1*Math.sin(2*phi1-2*phi2)
        		  + 2*m2*omega2*omega2*Math.sin(phi1-phi2)
			    ) / ( 2*m1 + m2 - m2*Math.cos(2*phi1-2*phi2) )
        	    -0.1*visc*omega1;
    }

    /* force on Phi_2 */
    public double forcephi2(double phi1,double phi2,double omega1,double omega2)
    {
        return (   2*Math.sin(phi1-phi2)
        			  *(   grav*(m1+m2)*Math.cos(phi1)
        			     + (m1+m2)*omega1*omega1
			    		 + m2*omega2*omega2*Math.cos(phi1-phi2)
			    	   )
        	   ) / ( 2*m1 + m2 - m2*Math.cos(2*phi1-2*phi2) )
        	   -0.1*visc*omega2;
    }

    public double energy()
    {
    	return 0.5*m1*omega1*omega1 + 0.5*m2*omega2*omega2 + 0.5*m2*omega1*omega1
    		   + m2*omega1*omega2*Math.cos(phi1-phi2)
    		   - grav*(m1*Math.cos(phi1) + m2*Math.cos(phi1) + m2*Math.cos(phi2));
    }

    public void runge_step_phi1()
    {
        k1[0] = dt*omega1;
        l1[0] = dt*forcephi1(phi1,phi2,omega1,omega2);
        k1[1] = dt*(omega1+l1[0]/2);
        l1[1] = dt*forcephi1(phi1+k1[0]/2,phi2,omega1+l1[0]/2,omega2);
        k1[2] = dt*(omega1+l1[1]/2);
        l1[2] = dt*forcephi1(phi1+k1[1]/2,phi2,omega1+l1[1]/2,omega2);
        k1[3] = dt*(omega1+l1[2]);
        l1[3] = dt*forcephi1(phi1+k1[2],phi2,omega1+l1[2],omega2);
    }

    public void runge_step_phi2()
    {
        k2[0] = dt*omega2;
        l2[0] = dt*forcephi2(phi1,phi2,omega1,omega2);
        k2[1] = dt*(omega2+l2[0]/2);
        l2[1] = dt*forcephi2(phi1,phi2+k2[0]/2,omega1,omega2+l2[0]/2);
        k2[2] = dt*(omega2+l2[1]/2);
        l2[2] = dt*forcephi2(phi1,phi2+k2[1]/2,omega1,omega2+l2[1]/2);
        k2[3] = dt*(omega2+l2[2]);
        l2[3] = dt*forcephi2(phi1,phi2+k2[2],omega1,omega2+l2[2]);
    }

}
