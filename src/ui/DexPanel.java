package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Player;
import utils.Pokemon;

public class DexPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4918536527912316714L;
	private ArrayList<Integer> unlockedlist;
	private ImageDex mydeximages;
	private ArrayList<JPanel> dexcell;
	private Pokemon pokemon;
	private Player user;

	public DexPanel(Player user, int gen){
		
		setLayout(new GridLayout(0,4));
		this.pokemon = new Pokemon();
		this.user = user;
		dexcell = new ArrayList<JPanel>();
		this.unlockedlist = user.getUnlockedmonster();
		try {
			this.mydeximages = new ImageDex(unlockedlist, gen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateDex();
	}
	
	private void updateDex(){
		ArrayList<ImageIcon> imagelist = mydeximages.getImgdex();
		int ind = 0;
		while (ind < imagelist.size()){
			dexcell.add(new JPanel());
			dexcell.get(ind).setOpaque(false);
			dexcell.get(ind).add(new JLabel());
			((JLabel)dexcell.get(ind).getComponent(0)).setHorizontalTextPosition(JLabel.CENTER);
			((JLabel)dexcell.get(ind).getComponent(0)).setVerticalTextPosition(JLabel.BOTTOM);
			((JLabel)dexcell.get(ind).getComponent(0)).setForeground(new Color(89,109,229));
			
			String imgdesc = imagelist.get(ind).getDescription();
			if (imgdesc != null){
				Integer desc = Integer.parseInt(imgdesc);
				if (desc < 1000){
					((JLabel)dexcell.get(ind).getComponent(0)).setText(pokemon.getName(desc));
					
					String poketypes = "";
					for (int i=0;i<pokemon.getTypeStr(desc).size();i++){
						String name = pokemon.getTypeStr(desc).get(i);
						poketypes += name+" ";
						poketypes +=  "<img src='" + getClass().getResource("/"+name+".png").toString();
						poketypes +=  "' width=16 height=16> ";
					}
										
					((JLabel)dexcell.get(ind).getComponent(0)).setToolTipText
					("<html>"+"Atrapados: "+new Integer(user.getCatchcount()[desc+1]).toString()+"<br>"+"Tipo: "+poketypes);
					
				}
				else{
					((JLabel)dexcell.get(ind).getComponent(0)).setText("Unown "+pokemon.getName(desc));
					((JLabel)dexcell.get(ind).getComponent(0)).setToolTipText("Atrapados: "+new Integer(user.getCatchcount()[desc+1]).toString());
				}
				
			}
		
			((JLabel)dexcell.get(ind).getComponent(0)).setIcon(imagelist.get(ind));
			this.add(dexcell.get(ind));
			ind++;
		}
	}
	
	
	
}
