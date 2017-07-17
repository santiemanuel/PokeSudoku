package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import ui.BackgroundPanel;
import ui.ButtonPanel;
import ui.ImageButton;
import ui.ShadowPanel;
import ui.SudokuPanel;
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
	
	private ShadowPanel pausePanel;
	
	/** The Layered Pane. */
	private JLayeredPane lp;
	
	/** The ImageButton object images. */
	private ImageButton images;
	
	/** The Sudoku matrix object. */
	private Sudoku puzzle;
	
	/** The newgame and hint buttons. */
	private JButton newgame, hint, clear, pause;
	
	private boolean paused;
	
	private List<String> difficulty = Arrays.asList("Facil", "Normal", "Dificil", "Desafio");
	
	/** The selected diff. */
	private int selectedDiff;
	
	/** The difficulty combo box. */
	private JComboBox<String> diffComboBox;
	
	private JLabel playTime;
	
	private long startTime, pausedStart, totalPause, elapsedtime;
	private Timer timer;
	private SimpleDateFormat date = new SimpleDateFormat("mm:ss");
	
	private int genpuzzlebg;
	
	private static int WAITGEN = 20;
	
	/** The width. */
	private static int WIDTH;
	
	/** The height. */
	private static int HEIGHT;
	
	/**
	 * Instantiates a new game frame.
	 */

	public GameFrame(){
		
		this.setTitle("PokeSudoku");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResolution();
		
		this.setLayout(new BorderLayout());
		this.selectedDiff = 0;
		this.lp = new JLayeredPane();
		this.lp.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.lp.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		
		this.windowPanel = new JPanel(new BorderLayout());
		windowPanel.setPreferredSize(new Dimension(WIDTH+100,HEIGHT+30));		
		windowPanel.setMinimumSize(new Dimension(WIDTH+100,HEIGHT+30));
	
		this.setResizable(false);
		
		this.diffComboBox = new JComboBox<String>((String[]) difficulty.toArray());
		
		this.diffComboBox.addActionListener(event ->
		{
			String sel = (String) diffComboBox.getSelectedItem();
			selectedDiff = difficulty.indexOf(sel);
		});
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.buttonsPanel = new JPanel();
		
		this.newgame = new JButton("Nuevo juego");
	    this.newgame.addActionListener(event -> {
	    	startTime = 0;
	    	rebuild();
	    });
	    
		this.puzzle = new Sudoku();
	    
	    this.hint = new JButton("Pista");
	    this.hint.addActionListener(event -> {
			try {
				getHint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		    
		this.lp.add(this.bgPanel, new Integer(1));
		
		this.buttonsPanel.add(this.diffComboBox);
		this.buttonsPanel.add(this.newgame);
	    
		this.windowPanel.add(this.lp);
		this.windowPanel.add(this.buttonsPanel, BorderLayout.SOUTH);
		
		
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
	 * @throws IOException 
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
			// TODO Auto-generated catch block
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
	    this.clear.addActionListener(event -> clearPanel());
	    
	    this.pause = new JButton("Pausa");
	    this.pause.setPreferredSize(new Dimension(100,(int) newgame.getPreferredSize().getHeight()));
	    
	    this.pause.addActionListener(event -> pauseGame());
	    this.paused = false;
	    
	    this.startTime = System.currentTimeMillis();
	    this.timer.start();
	    
	    this.playTime = new JLabel();
	    this.playTime.setFont(playTime.getFont().deriveFont(20f));

	    this.playTime.setBorder(new EmptyBorder(0, 200,0,0));
		this.buttonsPanel.add(newgame);
		this.buttonsPanel.add(diffComboBox);
		this.buttonsPanel.add(clear);
		this.buttonsPanel.add(hint);
		this.buttonsPanel.add(pause);
		this.buttonsPanel.add(playTime, BorderLayout.LINE_END);
		
		this.windowPanel.add(this.buttonsPanel, BorderLayout.SOUTH);
		this.windowPanel.add(this.iconsPanel, BorderLayout.LINE_END);
		
		this.add(windowPanel);
		
		this.sPanel.revalidate();
		this.sPanel.repaint();
	}
	
	private class ClockListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			if (!paused){
				updateClock();
				if (puzzle.getSudoku().isSolved()){
					timer.stop();
				}
			}
			else genpuzzlebg++;
			if (genpuzzlebg == WAITGEN){
					genpuzzlebg = 0;
					puzzle.bgSudoku();
			}
		}
	}
	private void updateClock(){
		Date elapsed = new Date(System.currentTimeMillis() - startTime - elapsedtime);
		playTime.setText("Tiempo: "+date.format(elapsed));
	}
	
	private void pause(){
			pausedStart = System.currentTimeMillis();
			paused = true;
	}
	
	private void resume(){
			totalPause = System.currentTimeMillis() - pausedStart;
			elapsedtime += totalPause;
			paused = false;
	}
	
	public void clearPanel(){
		this.sPanel.resetPuzzle();
	}
	
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
	
	public void getHint() throws InterruptedException{
		this.sPanel.getSudokuHint();
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
