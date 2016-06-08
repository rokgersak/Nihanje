package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class Menu extends JMenuBar implements ActionListener, ItemListener {

	private Okno okno;
	private Nihalo nihalo;
	private JMenu stevilo = new JMenu("Nihalo");
	private JRadioButtonMenuItem dvojno = new JRadioButtonMenuItem("Dvojno");
	private JRadioButtonMenuItem trojno = new JRadioButtonMenuItem("Trojno");
	private JMenu dusenje = new JMenu("Dusenje");
	private JCheckBoxMenuItem dusenje1 = new JCheckBoxMenuItem("Prvo nihalo");
	private JCheckBoxMenuItem dusenje2 = new JCheckBoxMenuItem("Drugo nihalo");
	private JMenu masa = new JMenu("Masa");
	private JMenu masa1 = new JMenu("Masa prvega nihala");
	private JRadioButtonMenuItem masa11 = new JRadioButtonMenuItem("Enojna");
	private JRadioButtonMenuItem masa12 = new JRadioButtonMenuItem("Dvojna");
	private JMenu masa2 = new JMenu("Masa drugega nihala");
	private JRadioButtonMenuItem masa21 = new JRadioButtonMenuItem("Enojna");
	private JRadioButtonMenuItem masa22 = new JRadioButtonMenuItem("Dvojna");

	public Menu(Okno okno) {
		super();
		this.okno = okno;
		this.nihalo = okno.getNihalo();
		
		// Zacetek
		steviloNihal();
		dusenje();
		masa();
		dvojno.setSelected(true);
		masa11.setSelected(true);
		masa21.setSelected(true);
	}

	// Stevilo Nihal
	public void steviloNihal() {
		this.add(stevilo);
		stevilo.add(dvojno);
		stevilo.add(trojno);
		dvojno.setActionCommand("Dvojno");
		trojno.setActionCommand("Trojno");
		
		// Poslusalci
		dvojno.addActionListener(this);
		trojno.addActionListener(this);
		
		// Skupine
        ButtonGroup skupinaStevilo = new ButtonGroup();
        skupinaStevilo.add(dvojno);
        skupinaStevilo.add(trojno);
	}
	
	// Dusenje
	public void dusenje() {
		this.add(dusenje);
		dusenje.add(dusenje1);
		dusenje.add(dusenje2);
		
		// Poslusalci
		dusenje1.addItemListener(this);
		dusenje2.addItemListener(this);
	}
	
	// Masa
	public void masa() {
		this.add(masa);
		masa.add(masa1);
		masa.add(masa2);
		masa1.add(masa11);
		masa1.add(masa12);
		masa2.add(masa21);
		masa2.add(masa22);
		masa11.setActionCommand("PrvoEnojna");
		masa12.setActionCommand("PrvoDvojna");
		masa21.setActionCommand("DrugoEnojna");
		masa22.setActionCommand("DrugoDvojna");
		
		// Poslusalci
		masa11.addActionListener(this);
		masa12.addActionListener(this);
		masa21.addActionListener(this);
		masa22.addActionListener(this);
		
		// Skupine
        ButtonGroup skupinaMasa1 = new ButtonGroup();
        skupinaMasa1.add(masa11);
        skupinaMasa1.add(masa12);
        ButtonGroup skupinaMasa2 = new ButtonGroup();
        skupinaMasa2.add(masa21);
        skupinaMasa2.add(masa22);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand()== "Dvojno" || e.getActionCommand()== "Trojno"){
			dusenje1.setSelected(false);
			dusenje2.setSelected(false);
			masa11.setSelected(true);
			masa21.setSelected(true);
			if (e.getActionCommand()== "Dvojno"){
				add(dusenje);
				add(masa);
				nihalo = Nihalo.tocke(3);
				okno.ponastavi(nihalo);
			} else if (e.getActionCommand()== "Trojno"){
				remove(dusenje);
				remove(masa);
				nihalo = Nihalo.tocke(4);
				okno.ponastavi(nihalo);
			}
		}
		if (e.getActionCommand()== "PrvoEnojna"){
			nihalo.tocka(1).masa = 1;
		} else if (e.getActionCommand()== "PrvoDvojna"){
			nihalo.tocka(1).masa = 2;
		}
		if (e.getActionCommand()== "DrugoEnojna"){
			nihalo.tocka(2).masa = 1;
		} else if (e.getActionCommand()== "DrugoDvojna"){
			nihalo.tocka(2).masa = 2;
		}
		if (e.getActionCommand()== "TretjeEnojna"){
			nihalo.tocka(3).masa = 1;
		} else if (e.getActionCommand()== "TretjeDvojna"){
			nihalo.tocka(3).masa = 2;
		} else if (e.getActionCommand()== "TretjeTrojna"){
			nihalo.tocka(3).masa = 3;
		}
		okno.ustavi();
	}
	
	public void itemStateChanged(ItemEvent e) {
	    Object naslov = e.getItemSelectable();
	    Integer izbran = 0;
	    // Dolocimo gumb
	    if (naslov == dusenje1){
	    	izbran = 1;
	    } else if (naslov == dusenje2){
	    	izbran = 2;
	    } 
	    // Gumb odkljukan ali ne
	    if (e.getStateChange() == ItemEvent.DESELECTED){
	    	nihalo.tocka(izbran).dusenje = 1;
	    } else{
	    	nihalo.tocka(izbran).dusenje = 20;
	    }
	    okno.ustavi();
	}	
}
	