package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ui.BackgroundPanel;
import ui.ButtonPanel;
import ui.DexFrame;
import ui.ImageButton;
import ui.ProfileFrame;
import ui.ShadowPanel;
import ui.SudokuPanel;
import ui.WinDialog;
import utils.PlayerManager;
import utils.Sudoku;


// TODO: Auto-generated Javadoc
/**
 * The Class GameFrame.
 */
public class GameFrame extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sudoku JPanel. */
	private SudokuPanel sPanel;
	
	/** The Buttons JPanel. */
	private ButtonPanel iconsPanel;
	
	/** The window and background JPanel. */
	private JPanel windowPanel, bgPanel, buttonsPanel;
	
	/** The pause panel. */
	private ShadowPanel pausePanel;
	
	/** The Layered Pane. */
	private JLayeredPane lp;
	
	/** The ImageButton object images. */
	private ImageButton images;
	
	/** The Sudoku matrix object. */
	private Sudoku puzzle;
	
	/** The newgame and hint buttons. */
	private JButton newgame, hint, clear, pause, dex, profile;
	
	/** The paused state. */
	private boolean paused;
	
	/** The difficulty list. */
	private List<String> difficulty = Arrays.asList("Facil", "Normal", "Dificil", "Desafio");
	
	/** The selected diff. */
	private int selectedDiff;
	
	/** The difficulty combo box. */
	private JComboBox<String> diffComboBox, usersCombobox;
	
	/** The play time. */
	private JLabel playTime;
	
	/** The elapsedtime. */
	private long startTime, pausedStart, totalPause, elapsedtime;
	
	/** The timer. */
	private Timer timer;
	
	/** The date. */
	private SimpleDateFormat date = new SimpleDateFormat("mm:ss");
	
	/** The counter used to trigger the background task. */
	private int genpuzzlebg;
	
	/** The limit of the counter when the game fires the bg task . */
	private static int WAITGEN = 30;
	
	/** The width. */
	private static int WIDTH;
	
	/** The height. */
	private static int HEIGHT;
	
	private String playername;
	
	private PlayerManager playerman;
	
	private DexFrame dexframe;
	/**
	 * Instantiates a new game frame.
	 */

	public GameFrame(){
		
		this.setTitle("PokeSudoku");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResolution();
		
		playerman = new PlayerManager();
		
		usersCombobox = new JComboBox<String>();
		
		String newplayer = "Nuevo jugador";
		usersCombobox.addItem(newplayer);
		
		if (playerman.getUserlist().length != 0){
		for (String username: playerman.getUserlist()){
				usersCombobox.addItem(username);
			}
		}
		JOptionPane.showMessageDialog(null, usersCombobox, "Selecciona tu usuario", JOptionPane.QUESTION_MESSAGE);
		
		playername = (String) usersCombobox.getSelectedItem();
		
		if (playername == newplayer){
			String name = "";
			while (name.isEmpty() || name == null){
				name = JOptionPane.showInputDialog("Ingresa tu nombre: ");
				if (name == null){
					JOptionPane.showMessageDialog(null, "No se puede cancelar.");
					name = "";
				}
				else if (name.isEmpty())
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.");
				if (playerman.isUser(name)){
					JOptionPane.showMessageDialog(null, "El usuario ya existe, cargando perfil.");
				}
			}
			playername = name;
		}
		
		playerman.initPlayer(playername);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.out.println("Cerrando");
				puzzle.savePuzzles(); //Save the remaining puzzles when closing the game.
				playerman.saveUser(); //Save the user info when closing the game.
				System.exit(0);
			}
		});
		
		this.setLayout(new BorderLayout());
		this.selectedDiff = 0;
		this.lp = new JLayeredPane();
		this.lp.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		this.windowPanel = new JPanel(new BorderLayout());
		windowPanel.setPreferredSize(new Dimension(WIDTH+100,HEIGHT+30));		
	
		this.setResizable(false);
		
		this.diffComboBox = new JComboBox<String>((String[]) difficulty.toArray());
		
		this.diffComboBox.setPrototypeDisplayValue((String) diffComboBox.getSelectedItem());
		
		this.diffComboBox.addActionListener(event ->
		{
			String sel = (String) diffComboBox.getSelectedItem();
			selectedDiff = difficulty.indexOf(sel);
		});
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    
		this.puzzle = new Sudoku(playerman.getUser());
		
		System.out.println("Nivel actual: "+playerman.getUser().getLevel());
		System.out.println("Experiencia actual: "+playerman.getUser().getExperience());
		System.out.println("Pistas disponibles: "+playerman.getUser().getAvailablehints());
		
		this.newgame = new JButton("Nuevo");
	    this.newgame.addActionListener(event -> {
	    	startTime = 0;
	    	rebuild();
	    });
	    

		this.lp.add(this.bgPanel, new Integer(1));
		
		this.buttonsPanel.add(this.newgame);
		this.buttonsPanel.add(this.diffComboBox);
		
		this.windowPanel.add(this.lp);
		this.windowPanel.add(this.buttonsPanel, BorderLayout.NORTH);
		
		
		this.add(windowPanel);
		
		try {
			BufferedImage original = ImageIO.read(getClass().getResourceAsStream("/25.png"));
			setIconImage(original);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the resolution.
	 */
	private void setResolution(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)dim.getWidth();
		if (width < 1920 ){
			WIDTH = 600;
			HEIGHT = 600;
		}
		else{
			WIDTH = 800;
			HEIGHT = 800;
		}		
	}
	
	/**
	 * Rebuild.
	 */
	public void rebuild() {
		
		this.lp.removeAll();
		this.buttonsPanel.removeAll();
		this.windowPanel.removeAll();
		this.bgPanel.removeAll();
		
		this.lp.revalidate();
		this.buttonsPanel.revalidate();
		this.windowPanel.revalidate();
		this.bgPanel.revalidate();
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.timer = new Timer(500, new ClockListener());
		this.elapsedtime = 0;
		this.timer.start();
	
		this.puzzle.newSudoku(selectedDiff);
		this.genpuzzlebg = 0;
		
		try {
			this.images = new ImageButton(puzzle.getSudoku(), WIDTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT, images);
		this.sPanel.setOpaque(false);
		this.sPanel.setSize(new Dimension(WIDTH, HEIGHT));
		
		this.iconsPanel = new ButtonPanel(this.puzzle.getSudoku(), images, sPanel);
		this.sPanel.newSudoku(this.puzzle.getSudoku(), images);

		this.lp.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.pausePanel = new ShadowPanel(WIDTH, HEIGHT);
		this.pausePanel.setOpaque(false);
		this.pausePanel.setSize(new Dimension(WIDTH, HEIGHT));
		
		this.lp.add(bgPanel, new Integer(1));
		this.lp.add(sPanel, new Integer(2));
		this.lp.add(pausePanel, new Integer(3));

		this.windowPanel.add(this.lp, BorderLayout.LINE_START);
		
		this.clear = new JButton("Limpiar");
		this.clear.setMargin(new Insets(1,1,3,3));
	    this.clear.addActionListener(event -> clearPanel());
	    
	    this.pause = new JButton("Pausa");
	    
	    this.pause.setPreferredSize(new Dimension(80,(int) newgame.getPreferredSize().getHeight()));
	    this.pause.setMargin(new Insets(1,1,3,3));
	    
	    this.pause.addActionListener(event -> pauseGame());
	    this.paused = false;
	    
	    this.hint = new JButton("Pista: "+playerman.getUser().getAvailablehints());
	    this.hint.setMargin(new Insets(1,1,3,3));
	    this.hint.addActionListener(event -> {
			try {
				getHint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	    
	    this.profile = new JButton("Perfil");
	    this.profile.setMargin(new Insets(1,1,3,3));
	    this.profile.addActionListener(event -> new ProfileFrame(playerman.getUser()));
	    
	    this.dex = new JButton("Pokedex");
	    this.dex.setMargin(new Insets(1,1,3,3));
	    this.dex.addActionListener(event ->{
	    	this.dexframe = new DexFrame(playerman.getUser());
	    	this.dexframe.showMenu(playerman.getUser());
	    });
	   
	    this.startTime = System.currentTimeMillis();
	    this.timer.start();
	    
	    this.playTime = new JLabel();
	    this.playTime.setFont(playTime.getFont().deriveFont(18f));

		this.buttonsPanel.add(newgame);
		this.buttonsPanel.add(diffComboBox);
		this.buttonsPanel.add(clear);
		this.buttonsPanel.add(hint);
		this.buttonsPanel.add(pause);
		this.buttonsPanel.add(dex);
		this.buttonsPanel.add(profile);
		this.buttonsPanel.add(playTime);
		
		this.windowPanel.add(this.buttonsPanel, BorderLayout.NORTH);
		this.windowPanel.add(this.iconsPanel, BorderLayout.LINE_END);
		
		this.add(windowPanel);
		
		this.sPanel.revalidate();
		this.sPanel.repaint();
	}
	
	
	/**
	 * The listener interface for receiving clock events.
	 * The class that is interested in processing a clock
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addClockListener<code> method. When
	 * the clock event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ClockEvent
	 */
	private class ClockListener implements ActionListener {
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if (!paused){
				updateClock();
				if (puzzle.getSudoku().isSolved() && timer.isRunning()){
					
					long time = System.currentTimeMillis() - startTime - elapsedtime;
					System.out.println("ActionPerformed llamado");
					int levelBefore = playerman.getUser().getLevel();
					playerman.getUser().winGame(selectedDiff, time);
					int levelAfter = playerman.getUser().getLevel();
					WinDialog dialog = new WinDialog(playerman.getUser(),levelBefore,levelAfter);
					if (levelAfter > levelBefore){
						dexframe = new DexFrame(playerman.getUser());
					}
					JOptionPane.showMessageDialog(null,dialog.getItems());
					timer.stop();
				}
			}else genpuzzlebg++;
			
			if (genpuzzlebg == WAITGEN){
				genpuzzlebg = 0;
				puzzle.bgSudoku();
			}
		}
	}
	
	/**
	 * Update clock.
	 */
	private void updateClock(){
		Date elapsed = new Date(System.currentTimeMillis() - startTime - elapsedtime);
		playTime.setText(date.format(elapsed));
	}
	
	/**
	 * Pause.
	 */
	private void pause(){
		pausedStart = System.currentTimeMillis();
		paused = true;
	}
	
	/**
	 * Resume.
	 */
	private void resume(){
		totalPause = System.currentTimeMillis() - pausedStart;
		elapsedtime += totalPause;
		paused = false;
	}
	
	/**
	 * Clear panel.
	 */
	public void clearPanel(){
		this.sPanel.resetPuzzle();
	}
	
	/**
	 * Pause game.
	 */
	public void pauseGame(){
		if (!paused){
			this.pausePanel.setColor();
			this.pause.setText("Reanudar");
			repaint();
			pause();
			this.sPanel.switchClickState();
		}
		else
		{
			this.pausePanel.setColor();
			this.pause.setText("Pausar");
			repaint();
			resume();
			this.sPanel.switchClickState();
		}
	}
	
	/**
	 * Gets the hint.
	 *
	 * @return the hint
	 * @throws InterruptedException the interrupted exception
	 */
	public void getHint() throws InterruptedException{
		int userhints = playerman.getUser().getAvailablehints();
		if (userhints > 0){
			this.sPanel.getSudokuHint();
			userhints--;
			playerman.getUser().setAvailablehints(userhints);
			this.hint.setText("Pista: "+playerman.getUser().getAvailablehints());
		}else	JOptionPane.showMessageDialog(null, "No te quedan pistas disponibles, gana partidas para recargar");
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				GameFrame frame = new GameFrame();
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});

	}

}
