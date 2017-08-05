package ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

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
		setResizable(false);
		
		MouseAdapter mouse = new MouseAdapter(){

			private Point origin;
			
            @Override
            public void mousePressed(MouseEvent e) {
                origin = new Point(e.getPoint());
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
			@Override
            public void mouseDragged(MouseEvent e) {
                if (origin != null) {
                    JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, panel);
                    
                    if (viewPort != null) {
                        int deltaX = origin.x - e.getX();
                        int deltaY = origin.y - e.getY();

                        Rectangle view = viewPort.getViewRect();
                        view.x += deltaX;
                        view.y += deltaY;

                        panel.scrollRectToVisible(view);
                    }
                }
            }
		};

		panel = new ProPanel(user);
		
		panel.addMouseListener(mouse);
		panel.addMouseMotionListener(mouse);
		panel.setOpaque(false);
		
		JScrollPane scroll = new JScrollPane();
		
		JViewport viewport = new MyViewport();
		viewport.setView(panel);
		scroll.setViewport(viewport);
		
		scroll.getViewport().setPreferredSize(new Dimension(400, 600));
		
		scroll.setPreferredSize(new Dimension(400,600));
		add(scroll);
		setLocationRelativeTo(null);
		setVisible(true);
		
		pack();
		
	}
}
