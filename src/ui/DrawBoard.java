package ui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class DrawBoard extends JPanel {
    
	private DrawIcons myicons;
    
	public DrawBoard (){
		super();
		this.myicons = new DrawIcons();
		System.out.println("Largo de lista INICIO"+myicons.getMyicons().size());
		DrawBoard.this.repaint();
		
		
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Llegue aqui");
        ArrayList<Icon> listIcons = new ArrayList<Icon>();
        listIcons = this.myicons.getMyicons();
        System.out.println("Iconos cargados");
        System.out.println("Largo de lista"+listIcons.size());
        for (Icon myicon: listIcons){
        	myicon.drawIcon(g);
        }
    }
	
	
	
}
