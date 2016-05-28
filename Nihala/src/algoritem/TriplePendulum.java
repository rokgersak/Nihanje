package algoritem;

public class TriplePendulum implements Runnable {

    private int px1, px2, px3, py1, py2, py3; //pixelcoordinates
    private double phi1, omega1; //coordinates and velocities
    private double phi2, omega2; //coordinates and velocities
    private double phi3, omega3; //coordinates and velocities
    private double m1, m2, m3; //masses – potrebno dopolnit enačbe
    public int visc; //Friction
    private static final double grav = 9.81; //gravity
    private static final double dt = 0.0005; //Runge-Kutta-timestep
    private double k1[] = new double[4]; //Runge-Kutta
    private double k2[] = new double[4]; //Runge-Kutta
    private double k3[] = new double[4]; //Runge-Kutta
    private double l1[] = new double[4]; //Runge-Kutta
    private double l2[] = new double[4]; //Runge-Kutta
    private double l3[] = new double[4]; //Runge-Kutta
    private int step;
    Thread animator;
    
    
    public void initialConditions(double kot1, double kot2, double kot3, int masa1, int masa2, int masa3, int viskoznost){
		phi1 = kot1;
        phi2 = kot2;
    	phi3 = kot3;
    	m1 = masa1;
    	m2 = masa2;
    	m3 = masa3;
		omega1 = 0.0;
		omega2 = 0.0;
		omega3 = 0.0;
		visc = viskoznost;
    	step = 0;
    }
    
    /* force on Phi_1 */
    public double forcephi1(	double phi1, double omega1,
    							double phi2, double omega2,
    							double phi3, double omega3   )
    {
		return (   10*grav*Math.sin(phi1)
				 + 4*grav*Math.sin(phi1-2*phi2)
				 - grav*Math.sin(phi1+2*phi2-2*phi3)
				 - grav*Math.sin(phi1-2*phi2+2*phi3)
				 + 4*omega1*omega1*Math.sin(2*phi1-2*phi2)
				 + 8*omega2*omega2*Math.sin(phi1-phi2)
				 + 2*omega3*omega3*Math.sin(phi1-phi3)
				 + 2*omega3*omega3*Math.sin(phi1-2*phi2+phi3)
				) /
			   		(-10. + 4*Math.cos(2*phi1-2*phi2) + 2*Math.cos(2*phi2-2*phi3))
			    -0.1*visc*omega1;
    }

    /* force on Phi_2 */
    public double forcephi2(	double phi1, double omega1,
    							double phi2, double omega2,
    							double phi3, double omega3   )
    {
		return ( - 7*grav*Math.sin(2*phi1-phi2)
				 + 7*grav*Math.sin(phi2)
				 + grav*Math.sin(phi2-2*phi3)
				 + grav*Math.sin(2*phi1+phi2-2*phi3)
				 + 2*omega1*omega1*Math.sin(phi1+phi2-2*phi3)
				 - 14*omega1*omega1*Math.sin(phi1-phi2)
				 + 2*omega2*omega2*Math.sin(2*phi2-2*phi3)
				 - 4*omega2*omega2*Math.sin(2*phi1-2*phi2)
				 + 6*omega3*omega3*Math.sin(phi2-phi3)
				 - 2*omega3*omega3*Math.sin(2*phi1-phi2-phi3)
			   ) /
					(-10. + 4*Math.cos(2*phi1-2*phi2) + 2*Math.cos(2*phi2-2*phi3))
			   -0.1*visc*omega2;
    }//forcephi2()

    /* force on Phi_3 */
    public double forcephi3(	double phi1, double omega1,
    							double phi2, double omega2,
    							double phi3, double omega3   )
    {
		return -2*Math.sin(phi2-phi3)*
					(   grav*Math.cos(2*phi1-phi2)
				 	  + grav*Math.cos(phi2)
				 	  + 2*omega1*omega1*Math.cos(phi1-phi2)
				 	  + 2*omega2*omega2
				 	  + omega3*omega3*Math.cos(phi2-phi3)
					) /
			   			(-5. + 2*Math.cos(2*phi1-2*phi2) + Math.cos(2*phi2-2*phi3))
			   -0.1*visc*omega3;
    }//forcephi3()

    public double energy()
    {
    	return 0.5*(   3*omega1*omega1 + 2*omega2*omega2 + omega3*omega3
    		   		 + 4*omega1*omega2*Math.cos(phi1-phi2)
    		   		 + 2*omega1*omega3*Math.cos(phi1-phi3)
    		   		 + 2*omega2*omega3*Math.cos(phi2-phi3)
    		       )
    		   - grav*(3*Math.cos(phi1) + 2*Math.cos(phi2) + Math.cos(phi3));
    }

    public void runge_step_phi1()
    {
        k1[0] = dt*omega1;
        l1[0] = dt*forcephi1(phi1,omega1,phi2,omega2,phi3,omega3);
        k1[1] = dt*(omega1+l1[0]/2);
        l1[1] = dt*forcephi1(phi1+k1[0]/2,omega1+l1[0]/2,phi2,omega2,phi3,omega3);
        k1[2] = dt*(omega1+l1[1]/2);
        l1[2] = dt*forcephi1(phi1+k1[1]/2,omega1+l1[1]/2,phi2,omega2,phi3,omega3);
        k1[3] = dt*(omega1+l1[2]);
        l1[3] = dt*forcephi1(phi1+k1[2],omega1+l1[2],phi2,omega2,phi3,omega3);
    }

    public void runge_step_phi2()
    {
        k2[0] = dt*omega2;
        l2[0] = dt*forcephi2(phi1,omega1,phi2,omega2,phi3,omega3);
        k2[1] = dt*(omega2+l2[0]/2);
        l2[1] = dt*forcephi2(phi1,omega1,phi2+k2[0]/2,omega2+l2[0]/2,phi3,omega3);
        k2[2] = dt*(omega2+l2[1]/2);
        l2[2] = dt*forcephi2(phi1,omega1,phi2+k2[1]/2,omega2+l2[1]/2,phi3,omega3);
        k2[3] = dt*(omega2+l2[2]);
        l2[3] = dt*forcephi2(phi1,omega1,phi2+k2[2],omega2+l2[2],phi3,omega3);
    }

    public void runge_step_phi3()
    {
        k3[0] = dt*omega3;
        l3[0] = dt*forcephi3(phi1,omega1,phi2,omega2,phi3,omega3);
        k3[1] = dt*(omega3+l3[0]/2);
        l3[1] = dt*forcephi3(phi1,omega1,phi2,omega2,phi3+k3[0]/2,omega3+l3[0]/2);
        k3[2] = dt*(omega3+l3[1]/2);
        l3[2] = dt*forcephi3(phi1,omega1,phi2,omega2,phi3+k3[1]/2,omega3+l3[1]/2);
        k3[3] = dt*(omega3+l3[2]);
        l3[3] = dt*forcephi3(phi1,omega1,phi2,omega2,phi3+k3[2],omega3+l3[2]);
    }
    
    public void start()
    {
	    animator = new Thread(this);
        animator.start();
    }//start()

    public void stop()
    {
		animator = null;
    }//stop()
    
    public void pixels()  //method for calculating pixelcoords
    {
        px1 = (int)Math.round((double)(gui.Platno.sirina/2) + 55*Math.sin(phi1));
        py1 = (int)Math.round((double)(gui.Platno.visina/2) + 55*Math.cos(phi1));
        px2 = (int)Math.round((double)(px1) + 55*Math.sin(phi2));
        py2 = (int)Math.round((double)(py1) + 55*Math.cos(phi2));
        px3 = (int)Math.round((double)(px2) + 55*Math.sin(phi3));
        py3 = (int)Math.round((double)(py2) + 55*Math.cos(phi3));
    }//pixels()

    public void run(double kot1, double kot2, double kot3, int masa1, int masa2, int masa3, int viskoznost)
    {
        try{Thread.sleep(500);} catch(InterruptedException e){}
        initialConditions(kot1, kot2, kot3, masa1, masa2, masa3, viskoznost);

        while(Thread.currentThread() == animator)
        {

	       	runge_step_phi1(); //Runge-Kutta
        	runge_step_phi2(); //Runge-Kutta
        	runge_step_phi3(); //Runge-Kutta
            phi1 = phi1 + (k1[0]+2*k1[1]+2*k1[2]+k1[3])/6; //Runge-Kutta
            omega1 = omega1 + (l1[0]+2*l1[1]+2*l1[2]+l1[3])/6; //Runge-Kutta
            phi2 = phi2 + (k2[0]+2*k2[1]+2*k2[2]+k2[3])/6; //Runge-Kutta
            omega2 = omega2 + (l2[0]+2*l2[1]+2*l2[2]+l2[3])/6; //Runge-Kutta
            phi3 = phi3 + (k3[0]+2*k3[1]+2*k3[2]+k3[3])/6; //Runge-Kutta
            omega3 = omega3 + (l3[0]+2*l3[1]+2*l3[2]+l3[3])/6; //Runge-Kutta

        	if(step % 20 == 0) //paint not so often
        	{
	            try{Thread.sleep(8);} catch(InterruptedException e){}
                pixels(); //calculate pixelcoordinates
                //paint new frame
        	}
			step++;
        }//while currentThread()==animator
    }//run()

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
