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
	private JMenu stevilo = new JMenu("Nihalo");
	private JRadioButtonMenuItem dvojno = new JRadioButtonMenuItem("Dvojno");
	private JRadioButtonMenuItem trojno = new JRadioButtonMenuItem("Trojno");
	private JMenu dusenje = new JMenu("Dušenje");
	private JCheckBoxMenuItem dusenje1 = new JCheckBoxMenuItem("Prvo nihalo");
	private JCheckBoxMenuItem dusenje2 = new JCheckBoxMenuItem("Drugo nihalo");
	private JCheckBoxMenuItem dusenje3 = new JCheckBoxMenuItem("Tretje nihalo");
	private JMenu masa = new JMenu("Masa");
	private JMenu masa1 = new JMenu("Masa prvega nihala");
	private JRadioButtonMenuItem masa11 = new JRadioButtonMenuItem("Enojna");
	private JRadioButtonMenuItem masa12 = new JRadioButtonMenuItem("Dvojna");
	private JRadioButtonMenuItem masa13 = new JRadioButtonMenuItem("Trojna");
	private JMenu masa2 = new JMenu("Masa drugega nihala");
	private JRadioButtonMenuItem masa21 = new JRadioButtonMenuItem("Enojna");
	private JRadioButtonMenuItem masa22 = new JRadioButtonMenuItem("Dvojna");
	private JRadioButtonMenuItem masa23 = new JRadioButtonMenuItem("Trojna");
	private JMenu masa3 = new JMenu("Masa tretjega nihala");
	private JRadioButtonMenuItem masa31 = new JRadioButtonMenuItem("Enojna");
	private JRadioButtonMenuItem masa32 = new JRadioButtonMenuItem("Dvojna");
	private JRadioButtonMenuItem masa33 = new JRadioButtonMenuItem("Trojna");
	

	public Menu(Okno okno) {
		super();
		this.okno = okno;
		
		//Tip nihala
		this.add(stevilo);
		stevilo.add(dvojno);
		stevilo.add(trojno);
		dvojno.setActionCommand("Dvojno");
		trojno.setActionCommand("Trojno");
		
		//Dusenje
		this.add(dusenje);
		dusenje.add(dusenje1);
		dusenje.add(dusenje2);
		
		//Masa
		this.add(masa);
		masa.add(masa1);
		masa.add(masa2);
		masa1.add(masa11);
		masa1.add(masa12);
		masa1.add(masa13);
		masa2.add(masa21);
		masa2.add(masa22);
		masa2.add(masa23);
		masa11.setActionCommand("PrvoEnojna");
		masa12.setActionCommand("PrvoDvojna");
		masa13.setActionCommand("PrvoTrojna");
		masa21.setActionCommand("DrugoEnojna");
		masa22.setActionCommand("DrugoDvojna");
		masa23.setActionCommand("DrugoTrojna");
		masa31.setActionCommand("TretjeEnojna");
		masa32.setActionCommand("TretjeDvojna");
		masa33.setActionCommand("TretjeTrojna");
		
		//Skupine
        ButtonGroup skupinaStevilo = new ButtonGroup();
        skupinaStevilo.add(dvojno);
        skupinaStevilo.add(trojno);
        ButtonGroup skupinaMasa1 = new ButtonGroup();
        skupinaMasa1.add(masa11);
        skupinaMasa1.add(masa12);
        skupinaMasa1.add(masa13);
        ButtonGroup skupinaMasa2 = new ButtonGroup();
        skupinaMasa2.add(masa21);
        skupinaMasa2.add(masa22);
        skupinaMasa2.add(masa23);
        ButtonGroup skupinaMasa3 = new ButtonGroup();
        skupinaMasa3.add(masa31);
        skupinaMasa3.add(masa32);
        skupinaMasa3.add(masa33);
		
		//Poslusalci
		dvojno.addActionListener(this);
		trojno.addActionListener(this);
		dusenje1.addItemListener(this);
		dusenje2.addItemListener(this);
		dusenje3.addItemListener(this);
		masa11.addActionListener(this);
		masa12.addActionListener(this);
		masa13.addActionListener(this);
		masa21.addActionListener(this);
		masa22.addActionListener(this);
		masa23.addActionListener(this);
		masa31.addActionListener(this);
		masa32.addActionListener(this);
		masa33.addActionListener(this);
		
		//Zacetek
		dvojno.setSelected(true);
		masa11.setSelected(true);
		masa21.setSelected(true);
		masa31.setSelected(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand()== "Dvojno" || e.getActionCommand()== "Trojno"){
			dusenje1.setSelected(false);
			dusenje2.setSelected(false);
			dusenje3.setSelected(false);
			masa11.setSelected(true);
			masa21.setSelected(true);
			if (e.getActionCommand()== "Dvojno"){
				dusenje.remove(dusenje3);
				masa3.remove(masa31);
				masa3.remove(masa32);
				masa3.remove(masa33);
				masa.remove(masa3);
				okno.nihalo = Nihalo.povezave(3);
			} else if (e.getActionCommand()== "Trojno"){
				dusenje.add(dusenje3);
				masa.add(masa3);
				masa3.add(masa31);
				masa3.add(masa32);
				masa3.add(masa33);
				masa31.setSelected(true);
				okno.nihalo = Nihalo.povezave(4);
			}
		}
		if (e.getActionCommand()== "PrvoEnojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(1), 1);
		} else if (e.getActionCommand()== "PrvoDvojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(1), 2);
		} else if (e.getActionCommand()== "PrvoTrojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(1), 3);
		}
		if (e.getActionCommand()== "DrugoEnojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(2), 1);
		} else if (e.getActionCommand()== "DrugoDvojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(2), 2);
		} else if (e.getActionCommand()== "DrugoTrojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(2), 3);
		}
		if (e.getActionCommand()== "TretjeEnojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(3), 1);
		} else if (e.getActionCommand()== "TretjeDvojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(3), 2);
		} else if (e.getActionCommand()== "TretjeTrojna"){
			okno.nihalo.dodajMaso(okno.nihalo.tocka(3), 3);
		}
		okno.osvezi();
	}
	
	public void itemStateChanged(ItemEvent e) {
	    Object naslov = e.getItemSelectable();
	    if (naslov == dusenje1){
	    	okno.nihalo.dodajDusenje(okno.nihalo.tocka(1));
	    } else if (naslov == dusenje2){
	    	okno.nihalo.dodajDusenje(okno.nihalo.tocka(2));
	    } else if (naslov == dusenje3){
	    	okno.nihalo.dodajDusenje(okno.nihalo.tocka(3));
	    }
	    okno.osvezi();
	}	
}
	