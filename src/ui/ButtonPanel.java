package ui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.SudokuBoard;

public class ButtonPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton[] buttons;
	public ButtonPanel(SudokuBoard puzzle, ImageButton images){
		
		buttons = new JButton[puzzle.getNewnumbers().size()];
		
		
		this.setLayout(new GridLayout(1,9,0,0));
		for (int i=1;i<images.getImages().size();i++){
			buttons[i] = new JButton();
			buttons[i].setIcon( new ImageIcon(images.getImages().get(i).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
			this.add(buttons[i]);
		}
		
	}

}
