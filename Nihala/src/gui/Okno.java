package gui;

import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Platno platno;
	private Menu menu = new Menu();
	public Graf graf = Graf.seznamPovezav(3);

	public Okno() throws HeadlessException {
		super();
		this.setTitle("Simulacija nihanja");
		this.setLayout(new GridBagLayout());
		
		//Platno
		platno = new Platno(this);
		this.getContentPane().add(platno);
		
		//Menu
		this.setJMenuBar(menu);
		
		//Razporedi
		platno.razporedi(1280/2, 180, 100);
	}

	
	
	
}
