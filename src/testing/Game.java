package testing;

import java.awt.BorderLayout;
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
        JFrame frame = new JFrame("Circle Drawing Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(GAP, GAP));
        contentPane.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        drawingboard = new DrawBoard();
        contentPane.add(drawingboard);
        
        frame.setMinimumSize(new Dimension(800,600));
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