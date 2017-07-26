package ui;

import javax.swing.JFrame;

import utils.Player;

public class ProfileFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707594845036613821L;

	ProPanel panel;
	
	public ProfileFrame(Player user){
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.panel = new ProPanel(user);
		
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
}
