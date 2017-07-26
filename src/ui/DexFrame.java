package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import utils.Player;

public class DexFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7281828208484095197L;
	private DexPanel panel;
	private List<String> generation = Arrays.asList("Generacion I", "Generacion II", "Generacion III");
	private JComboBox<String> gencombobox;
	private int gen;
	private JButton loadgen;
	
	public DexFrame(Player user){
				
		setTitle("Pokedex");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	public void showMenu(Player user){
		gencombobox = new JComboBox<String>((String[]) generation.toArray());
		gen = 1;
		gencombobox.addActionListener(event ->
		{
			String sel = (String) gencombobox.getSelectedItem();
			gen = generation.indexOf(sel)+1;
		});
		add(gencombobox);
		loadgen = new JButton("Cargar");
		loadgen.addActionListener(event -> updateDexFrame(user));
		add(loadgen);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		pack();
	}
	
	public void updateDexFrame(Player user){
		remove(gencombobox);
		remove(loadgen);
		this.panel = new DexPanel(user, gen);
		
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

		panel.addMouseListener(mouse);
		panel.addMouseMotionListener(mouse);
		panel.setOpaque(false);
		JScrollPane scroll = new JScrollPane();
		JViewport viewport = new MyViewport();
		viewport.setView(panel);
		scroll.setViewport(viewport);
		
		
		scroll.setPreferredSize(new Dimension(360,800));
		add(scroll);
		setLocationRelativeTo(null);
		
		setVisible(true);
		pack();
	}
	
	 private static class MyViewport extends JViewport {

		private static final long serialVersionUID = 1L;

			public MyViewport() {
	            this.setOpaque(true);
	            this.setPreferredSize(new Dimension(360,800));
	        }

	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            
	            Graphics2D g2d = (Graphics2D) g;
	            final BufferedImage image = new BufferedImage(
	            		this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
	            g2d = image.createGraphics();
	            GradientPaint prim = new GradientPaint(0f, 0f, new Color(185,173,235,255),
	            		200f, 0f, new Color(192,201,200,255));

	            g2d.setPaint(prim);
	            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	            
	            g.drawImage(image, 0, 0, null);
	            		
	        }
	    }

	public DexPanel getPanel() {
		return panel;
	}

	public void setPanel(DexPanel panel) {
		this.panel = panel;
	}
}
