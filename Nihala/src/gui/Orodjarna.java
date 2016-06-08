package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Orodjarna extends JPanel implements ActionListener {
	
	private Okno okno;
	private JButton pozeniGumb = new JButton("Poženi");
	private JButton ustaviGumb = new JButton("Ustavi");
	private JLabel besedilo = new JLabel("Masne toèke premikamo s klikom in spustom.");
	private GridBagConstraints pozeniGumbLayout = new GridBagConstraints();
	private GridBagConstraints ustaviGumbLayout = new GridBagConstraints();
	private GridBagConstraints besediloLayout = new GridBagConstraints();

	public Orodjarna(Okno okno) {
		super();
		this.okno = okno;
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		pripraviOrodjarno();
		
		// Poslusalci
		pozeniGumb.addActionListener(this);
		ustaviGumb.addActionListener(this);
	}

	public void pripraviOrodjarno () {
		besediloLayout.ipady = 10;
		besediloLayout.gridx = 0;
		besediloLayout.gridy = 0;
		besediloLayout.gridwidth = 2;
		add(besedilo, besediloLayout);
		pozeniGumbLayout.ipadx = 40;
		pozeniGumbLayout.weightx = 0.5;
		pozeniGumbLayout.gridx = 0;
		pozeniGumbLayout.gridy = 2;
		add(pozeniGumb, pozeniGumbLayout);
		ustaviGumbLayout.ipadx = 40;
		ustaviGumbLayout.weightx = 0.5;
		ustaviGumbLayout.gridx = 1;
		ustaviGumbLayout.gridy = 2;
		add(ustaviGumb, ustaviGumbLayout);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pozeniGumb) {
			okno.pozeni();
		} else if (e.getSource() == ustaviGumb) {
			okno.ustavi();
		}
	}
}
