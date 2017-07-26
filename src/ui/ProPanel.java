package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.coobird.thumbnailator.Thumbnails;
import utils.Player;

public class ProPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8502032371876450117L;
	private JLabel profileicon, exp, level, monsters;
	private JLabel diff[], rec[];

	public ProPanel(Player user){
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setPreferredSize(new Dimension(300,500));
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		System.out.println(user.getProfilepic());
		
		profileicon = new JLabel(user.getName(), SwingConstants.CENTER);
	    profileicon.setFont(profileicon.getFont().deriveFont(28f));
		profileicon.setIcon(createIcon("/"+user.getProfilepic()+".png"));
		profileicon.setForeground(Color.WHITE);
		
		JProgressBar progress = new JProgressBar();
		progress.setForeground(Color.RED);
		progress.setValue(user.currentExp());
		
		level = new JLabel("Nivel: "+user.getLevel());
		level.setForeground(Color.WHITE);
		level.setBorder(new EmptyBorder(5,0,0,0));
		exp = new JLabel("Experiencia: "+user.getExperience());
		exp.setForeground(Color.WHITE);
		exp.setBorder(new EmptyBorder(0,0,10,0));
		monsters = new JLabel("Pokemon desbloqueados: "+
		(new Integer(user.getUnlockedmonster().size()-1).toString()));
		monsters.setForeground(Color.WHITE);
		monsters.setBorder(new EmptyBorder(0,0,10,0));
		
		diff = new JLabel[4];
		rec = new JLabel[4];
		JLabel subtitle = new JLabel("Victorias");
		subtitle.setForeground(Color.WHITE);
		diff[0] = new JLabel("Facil: "+user.getWonbydiff()[0]);
		diff[0].setForeground(Color.WHITE);
		diff[1] = new JLabel("Normal: "+user.getWonbydiff()[1]);
		diff[1].setForeground(Color.WHITE);
		diff[2] = new JLabel("Dificil: "+user.getWonbydiff()[2]);
		diff[2].setForeground(Color.WHITE);
		diff[3] = new JLabel("Desafio: "+user.getWonbydiff()[3]);
		diff[3].setForeground(Color.WHITE);
		
		JLabel subtitle2 = new JLabel("Mejores tiempos");
		subtitle2.setForeground(Color.WHITE);
		subtitle2.setBorder(new EmptyBorder(10,0,0,0));
		
		rec[0] = new JLabel("Facil: "+user.getBestplay()[0]);
		rec[0].setForeground(Color.WHITE);
		rec[1] = new JLabel("Normal: "+user.getBestplay()[1]);
		rec[1].setForeground(Color.WHITE);
		rec[2] = new JLabel("Dificil: "+user.getBestplay()[2]);
		rec[2].setForeground(Color.WHITE);
		rec[3] = new JLabel("Desafio: "+user.getBestplay()[3]);
		rec[3].setForeground(Color.WHITE);
		
		add(profileicon);
		add(progress);
		add(level);
		add(exp);
		add(monsters);
		add(subtitle);
		for (int i=0;i<4;i++) add(diff[i]);
		add(subtitle2);
		for (int i=0;i<4;i++) add(rec[i]);
	}
	
	private ImageIcon createIcon(String name) {

		BufferedImage original;
		try {
			original = ImageIO.read(getClass().getResourceAsStream(name));
			original = Thumbnails.of(original)
					.scale(1.5)
					.asBufferedImage();
			ImageIcon icon = new ImageIcon(original);
			return (icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return null;
	}
	

}
