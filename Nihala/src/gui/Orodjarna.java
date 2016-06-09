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
	public JButton pozeniGumb = new JButton("Poženi");
	private JButton ustaviGumb = new JButton("Ustavi");
	private JButton ponoviGumb = new JButton("Ponovi");
	private JLabel besedilo = new JLabel("Masne toèke premikamo s klikom in spustom.");
	private GridBagConstraints gumb1Layout = new GridBagConstraints();
	private GridBagConstraints gumb2Layout = new GridBagConstraints();
	private GridBagConstraints besediloLayout = new GridBagConstraints();

	public Orodjarna(Okno okno) {
		super();
		this.okno = okno;
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		pripraviBesedilo();
		pripraviGumb1(pozeniGumb);
		pripraviGumb2();
		
		// Poslusalci
		pozeniGumb.addActionListener(this);
		ustaviGumb.addActionListener(this);
		ponoviGumb.addActionListener(this);
	}

	public void pripraviBesedilo () {
		besediloLayout.ipady = 10;
		besediloLayout.gridx = 0;
		besediloLayout.gridy = 0;
		besediloLayout.gridwidth = 2;
		add(besedilo, besediloLayout);	
	}
	
	public void pripraviGumb1 (JButton gumb1) {
		remove(pozeniGumb);
		remove(ustaviGumb);
		gumb1Layout.ipadx = 40;
		gumb1Layout.weightx = 0.5;
		gumb1Layout.gridx = 0;
		gumb1Layout.gridy = 2;
		add(gumb1, gumb1Layout);
		revalidate();
		repaint();
	}
	
	public void pripraviGumb2 () {
		gumb2Layout.ipadx = 40;
		gumb2Layout.weightx = 0.5;
		gumb2Layout.gridx = 1;
		gumb2Layout.gridy = 2;
		add(ponoviGumb, gumb2Layout);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pozeniGumb) {
			okno.pozeni();
			pripraviGumb1(ustaviGumb);
		} else if (e.getSource() == ustaviGumb) {
			okno.ustavi();
			pripraviGumb1(pozeniGumb);
		} else if (e.getSource() == ponoviGumb)  {
			okno.ponastavi(okno.getNihalo());
			pripraviGumb1(pozeniGumb);
		}
	}
}
