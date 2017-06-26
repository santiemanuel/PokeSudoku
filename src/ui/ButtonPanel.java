package ui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.SudokuGen;

public class ButtonPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton[] buttons;
	public ButtonPanel(SudokuGen puzzle, ImageButton images, SudokuPanel sPanel){
		
		buttons = new JButton[puzzle.getNewnumbers().size()];
		
		
		this.setLayout(new GridLayout(9,1,5,5));
		for (int i=1;i<images.getImages().size();i++){
			ImageIcon image = new ImageIcon(images.getImages().get(i).getImage());
			buttons[i] = createMouseListener(image, i, puzzle, sPanel);
			this.add(buttons[i]);
		}
		
	}
	
	private JButton createMouseListener(ImageIcon image, int i, SudokuGen puzzle, SudokuPanel sPanel){
		JButton button = new JButton();
		button.setIcon(image);
		button.setActionCommand(Integer.toString(puzzle.getNewnumbers().get(i)));
		button.addActionListener(sPanel.new ButtonActionListener());
		return button;
		
		
	}

}
