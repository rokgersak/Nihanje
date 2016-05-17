package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class Menu extends JMenuBar implements ActionListener {

	private JMenu stevilo = new JMenu("Nihalo");
	private JRadioButtonMenuItem dvojno = new JRadioButtonMenuItem("Dvojno");
	private JRadioButtonMenuItem trojno = new JRadioButtonMenuItem("Trojno");
	private JMenu dodatki = new JMenu("Dodatno");
	private JMenu dusenje = new JMenu("Dušenje");
	private JCheckBoxMenuItem dusenje1 = new JCheckBoxMenuItem("Prvo nihalo");
	private JCheckBoxMenuItem dusenje2 = new JCheckBoxMenuItem("Drugo nihalo");
	private JCheckBoxMenuItem dusenje3 = new JCheckBoxMenuItem("Tretje nihalo");
	private JMenu sklopitev = new JMenu("Sklopitev");
	private JCheckBoxMenuItem sklopitev12 = new JCheckBoxMenuItem("Prvega in drugega nihala");
	private JCheckBoxMenuItem sklopitev23 = new JCheckBoxMenuItem("Drugega in tretjega nihala");
	private String stringDvojno = "Dvojno";
	private String stringTrojno = "Trojno";
	

	public Menu() {
		super();
		
		//Tip nihala
		this.add(stevilo);
		stevilo.add(dvojno);
		dvojno.setActionCommand(stringDvojno);
		stevilo.add(trojno);
		trojno.setActionCommand(stringTrojno);
		
		//Dodatki
		this.add(dodatki);
		dodatki.add(dusenje);
		dusenje.add(dusenje1);
		dusenje.add(dusenje2);
		dodatki.add(sklopitev);
		sklopitev.add(sklopitev12);
		
		//Skupina
        ButtonGroup skupina = new ButtonGroup();
        skupina.add(dvojno);
        skupina.add(trojno);
		
		//Poslušalci
		dvojno.addActionListener(this);
		trojno.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		dusenje1.setSelected(false);
		dusenje2.setSelected(false);
		dusenje3.setSelected(false);
		sklopitev12.setSelected(false);
		sklopitev23.setSelected(false);
		if (e.getActionCommand()== "Dvojno"){
			dusenje.remove(dusenje3);
			sklopitev.remove(sklopitev23);
		}
		if (e.getActionCommand()== "Trojno"){
			dusenje.add(dusenje3);
			sklopitev.add(sklopitev23);
		}
	}
}
	