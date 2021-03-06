package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		
		
		scroll.setPreferredSize(new Dimension(360,500));
		add(scroll);
		setLocationRelativeTo(null);
		
		setVisible(true);
		pack();
	}

	public DexPanel getPanel() {
		return panel;
	}

	public void setPanel(DexPanel panel) {
		this.panel = panel;
	}
}
