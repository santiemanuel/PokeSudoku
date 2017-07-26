package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import net.coobird.thumbnailator.Thumbnails;

public class ImageDex {

	private ArrayList<ImageIcon> imgdex;
	private int[] pokegen = {151, 251, 386};
	
	public ImageDex(ArrayList<Integer> unlocked, int gen) throws IOException{
		
		this.imgdex = new ArrayList<ImageIcon>();
		updateDex(unlocked, gen);

	}
	
	public void updateDex(ArrayList<Integer> unlocked, int gen) throws IOException{
		int counter = 1;
		if (gen == 2) counter = pokegen[0]+1;
		if (gen == 3) counter = pokegen[1]+1;
		
		
		while (counter <= pokegen[gen-1]){
			addtoDex(counter, unlocked);
			counter++;
			if (counter == 201){
				counter = 1000;
				while (counter <= 1027){
					addtoDex(counter, unlocked);
					counter++;
				}
				counter = 202;
			}
		}
	}
	
	private void addtoDex(int counter, ArrayList<Integer> unlocked) throws IOException{
		int index = unlocked.indexOf(counter);
		if (index != -1){
			ImageIcon img = (createIcon("/"+unlocked.get(index).toString()+".png"));
			img.setDescription(new Integer(counter-1).toString());
			this.imgdex.add(img);
		}
		
		else
		{
			ImageIcon shadowicon = (createIcon("/"+counter+".png"));
			BufferedImage shadowimg = blackImage((BufferedImage) shadowicon.getImage());
			shadowicon.setImage(shadowimg);
			this.imgdex.add(shadowicon);
		}
	}	
	
	/**
	 * Black image. Creates the shape of images.
	 *
	 * @param image the image
	 * @return the buffered image
	 */
	public BufferedImage blackImage(BufferedImage image){
		BufferedImage shadow = new BufferedImage(
	            image.getWidth() ,
	            image.getHeight() ,
	            BufferedImage.TYPE_INT_ARGB);
	        
	        Graphics2D g2 = shadow.createGraphics();
	        g2.drawImage(image, 0, 0, null);
	        
	        g2.setComposite(AlphaComposite.SrcIn);
	        g2.setColor(Color.BLACK);
	        g2.fillRect(0, 0, shadow.getWidth(), shadow.getHeight());       
	        
	        g2.dispose();      
	        
	        return shadow;
	}
	
	private ImageIcon createIcon(String name) throws IOException{

		BufferedImage original = ImageIO.read(getClass().getResourceAsStream(name));
		original = Thumbnails.of(original)
				.scale(1.0)
				.asBufferedImage();
		ImageIcon icon = new ImageIcon(original);
		return (icon);
	}



	public ArrayList<ImageIcon> getImgdex() {
		return imgdex;
	}
	
	public void setImgdex(ArrayList<ImageIcon> imgdex) {
		this.imgdex = imgdex;
	}
}
