package ui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import utils.Player;

public class WinDialog {


	Player userinfo;
	Object[] lista;
	
	public WinDialog(Player user, int before, int after){
			
		JLabel level = new JLabel("Nivel "+user.getLevel());
		JProgressBar progress = new JProgressBar();
		JLabel label = new JLabel("Ganaste "+user.getName()+"!");
		progress.setForeground(Color.RED);
		progress.setValue(user.currentExp());
		lista = new Object[4];
		JLabel levelpass;
		
		if (after > before){
			levelpass = new JLabel("Pasaste al nivel "+after+"!");
			lista[3] = levelpass;
		}

		lista[0] = level;
		lista[1] = progress;
		lista[2] = label;
	}
	
	public Object[] getItems(){
		return lista;
	}
	
	
}
