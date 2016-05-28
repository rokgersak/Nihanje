package algoritem;

public  class DoublePendulum implements Runnable {
	
    private int px1, px2, py1, py2; //pixelcoordinates
    private double phi1, omega1; //coordinates and velocities
    private double phi2, omega2; //coordinates and velocities
    private double m1, m2; //masses
    public int visc; //friction
    private static final double grav = 9.81; //Gravity
    private static final double dt = 0.0005; //Runge-Kutta-timestep
    private double k1[] = new double[4]; //Runge-Kutta
    private double k2[] = new double[4]; //Runge-Kutta
    private double l1[] = new double[4]; //Runge-Kutta
    private double l2[] = new double[4]; //Runge-Kutta
    private int step;
    Thread animator;
    
    public void initialConditions(double kot1, double kot2, int masa1, int masa2, int viskoznost) //initial values
    {
		phi1 = kot1;
        phi2 = kot2;
    	m1 = masa1;
    	m2 = masa2;
		omega1 = 0.0;
		omega2 = 0.0;
    	step = 0;
    }
    
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
    
    public void start()
    {
	    animator = new Thread(this);
        animator.start();
    }//start()

    public void stop()
    {
		animator = null;
    }//stop()
    
    public void pixels() //method for calculating pixelcoordinates
    {
        px1 = (int)Math.round((double)(gui.Platno.sirina/2) + 80*Math.sin(phi1));
        py1 = (int)Math.round((double)(gui.Platno.visina/2) + 80*Math.cos(phi1));
        px2 = (int)Math.round((double)px1 + 80*Math.sin(phi2));
        py2 = (int)Math.round((double)py1 + 80*Math.cos(phi2));
    }//pixels()
    
    public void run(double kot1, double kot2, int masa1, int masa2, int viskoznost)
    {
        try{Thread.sleep(500);} catch(InterruptedException e){}
        initialConditions(kot1, kot2, masa1, masa2, viskoznost);

        while(Thread.currentThread() == animator)
        {

	       	runge_step_phi1(); //Runge-Kutta
        	runge_step_phi2(); //Runge-Kutta
            phi1 = phi1 + (k1[0]+2*k1[1]+2*k1[2]+k1[3])/6; //Runge-Kutta
            omega1 = omega1 + (l1[0]+2*l1[1]+2*l1[2]+l1[3])/6; //Runge-Kutta
            phi2 = phi2 + (k2[0]+2*k2[1]+2*k2[2]+k2[3])/6; //Runge-Kutta
            omega2 = omega2 + (l2[0]+2*l2[1]+2*l2[2]+l2[3])/6; //Runge-Kutta

        	if(step % 25 == 0) //paint not so often
        	{
				try{Thread.sleep(8);} catch(InterruptedException e){}
                pixels(); //calculate pixelcoordinates
                //paint new frame into Image
		        //display Image on screen
        	}
			step++;
        }//while currentThread()==animator
    }//run()

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}



