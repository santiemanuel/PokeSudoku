package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import net.coobird.thumbnailator.Thumbnails;
import utils.Player;

public class ProPanel extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 8502032371876450117L;
	private JLabel profileicon, exp, level, monsters, owned;
	private JLabel diff[], rec[];
	private String[] difficulty = {"Facil: ","Normal: ","Dificil: ","Desafio: "};
	private JProgressBar progress;
	private int userprog;
	private Task task;

	public ProPanel(Player user){
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setOpaque(false);
		//setPreferredSize(new Dimension(400,1000));
		
		profileicon = new JLabel(user.getName());
	    profileicon.setFont(profileicon.getFont().deriveFont(28f));
		profileicon.setIcon(createIcon("/"+user.getProfilepic()+".png"));
		profileicon.setForeground(Color.WHITE);
		
		profileicon.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		
		userprog = user.currentExp();
		progress = new JProgressBar();
		progress.setValue(0);
		progress.setStringPainted(true);
		progress.setForeground(Color.RED);
		progress.setOpaque(false);
		progress.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		
		level = new JLabel("Nivel: "+user.getLevel());
		level.setForeground(Color.WHITE);
		level.setBorder(new EmptyBorder(5,0,0,0));

		
		exp = new JLabel("Experiencia: "+user.getExperience());
		exp.setForeground(Color.WHITE);
		exp.setBorder(new EmptyBorder(0,0,10,0));
		
		monsters = new JLabel("Pokemon desbloqueados: "+
		(new Integer(user.getUnlockedmonster().size()-1).toString())); //-1 taking out the first zero
		monsters.setForeground(Color.WHITE);
		
		owned = new JLabel("Pokemon atrapados: "+
		user.getOwnedpokemon());
		owned.setForeground(Color.WHITE);
		owned.setBorder(new EmptyBorder(0,0,10,0));
				
		diff = new JLabel[4];
		rec = new JLabel[4];
		JLabel subtitle = new JLabel("Victorias");
		subtitle.setForeground(Color.WHITE);
		
		for (int i=0;i<4;i++){
			diff[i] = new JLabel(difficulty[i]+user.getWonbydiff()[i]);
			diff[i].setForeground(Color.WHITE);
			rec[i] = new JLabel(difficulty[i]+user.getBestplay()[i]);
			rec[i].setForeground(Color.WHITE);
		}
		
		JLabel subtitle2 = new JLabel("Mejores tiempos");
		subtitle2.setForeground(Color.WHITE);
		subtitle2.setBorder(new EmptyBorder(10,0,0,0));
		
		JLabel total = new JLabel("Tiempo total de partidas: "+ user.getPlaytime());
		total.setForeground(Color.WHITE);
		
		JLabel medals = new JLabel("Medallas");
		medals.setForeground(Color.WHITE);
		
		JPanel typepanel = new JPanel(new GridLayout(0,3));
		
		typepanel.setOpaque(false);
		for (int i=0;i<user.getTypecountlist().length;i++){
			JPanel singlepanel = new JPanel();
			singlepanel.setBorder(new EmptyBorder(5,10,10,10));
			singlepanel.setLayout(new BorderLayout());
			singlepanel.setOpaque(false);
			JProgressBar typebar = new JProgressBar();
			typebar.setMaximum(10);
			typebar.setValue(user.getTypeCount(i));
			typebar.setForeground(Color.decode("#42A5F5"));
			typebar.setOpaque(false);
			typebar.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
			typebar.setSize(new Dimension(48, 48));
			typebar.setPreferredSize(new Dimension(48, 48));
			ImageIcon icon = new ImageIcon(getClass().getResource("/"+user.getPokemon().getTypesolo(i)+".png"));
			typebar.setUI(new ProgressCircleUI(icon.getImage()));
			singlepanel.add(typebar, BorderLayout.EAST);
			JLabel name = new JLabel(user.getPokemon().getTypesolo(i));
			singlepanel.add(name, BorderLayout.NORTH);
			typepanel.add(singlepanel);
		}
		
		JPanel container = new JPanel();
		container.setOpaque(false);
		
		JPanel paneltext = new JPanel();
		paneltext.setLayout(new BoxLayout(paneltext, BoxLayout.Y_AXIS));
		paneltext.setOpaque(false);
		
		container.setLayout(new BorderLayout());
		
		paneltext.add(profileicon);
		paneltext.add(progress);
		paneltext.add(level);
		paneltext.add(exp);
		paneltext.add(monsters);
		paneltext.add(owned);
		paneltext.add(subtitle);
		for (int i=0;i<4;i++)
			paneltext.add(diff[i]);
		paneltext.add(subtitle2);
		for (int i=0;i<4;i++) 
			paneltext.add(rec[i]);
		paneltext.add(total);
		paneltext.add(medals);
		
		container.add(paneltext, BorderLayout.CENTER);
		container.add(typepanel, BorderLayout.SOUTH);
		
		add(container);
		
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
		
	}
	
	public void propertyChange(PropertyChangeEvent evt){
		if ("progress" == evt.getPropertyName()){
			int prog = (Integer) evt.getNewValue();
			progress.setValue(prog);
		}
	}
	
	private ImageIcon createIcon(String name) {

		BufferedImage original;
		try {
			original = ImageIO.read(getClass().getResourceAsStream(name));
			original = Thumbnails.of(original)
					.scale(1.3)
					.asBufferedImage();
			ImageIcon icon = new ImageIcon(original);
			return (icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return null;
	}
	
	class Task extends SwingWorker<Void, Void>{

		@Override
		protected Void doInBackground() throws Exception {
			setProgress(0);
			
			int pro = 0;
			
			while (pro < userprog){
				try{
					Thread.sleep(20);
				} catch (InterruptedException ignore) {}
				pro++;
				setProgress(Math.min(pro, 100));
			}
			
			return null;
		}
		@Override
		public void done(){
			System.out.println("Terminado");
		}
	}

}
