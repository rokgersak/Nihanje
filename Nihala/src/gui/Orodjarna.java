package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Orodjarna extends JToolBar implements ActionListener {
	
	private Okno okno;
	private JButton pozeniGumb = new JButton("Poženi");
	private JButton ustaviGumb = new JButton("Ustavi");

	public Orodjarna(Okno okno) {
		super();
		this.okno = okno;
		setFloatable(false);
		setBackground(Color.white);
		
		// Gumbi
		add(pozeniGumb);
		add(ustaviGumb);
		
		// Poslusalci
		pozeniGumb.addActionListener(this);
		ustaviGumb.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pozeniGumb) {
			okno.pozeni();
		} else if (e.getSource() == ustaviGumb) {
			okno.ustavi();
		}
	}
}
