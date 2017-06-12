package testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.DrawBoard;
import ui.DrawIcons;

@SuppressWarnings("serial")
public class Game extends JPanel{

	DrawIcons icons;
	private static final int GAP = 5;
	private JPanel drawingboard;
	
    private void displayGUI() {
        JFrame frame = new JFrame("Sudoku Layout");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color bgColor = Color.decode("#94C9A9");
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(GAP, GAP));
        contentPane.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        contentPane.setBackground(bgColor);

        drawingboard = new DrawBoard();
        contentPane.add(drawingboard);
        
        frame.setMinimumSize(new Dimension(380, 380));
        frame.setResizable(false);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
	
	
	
	public static void main(String[] args) throws InterruptedException{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new Game().displayGUI();
            }
        };
        EventQueue.invokeLater(runnable);		
		
	}

}