package com.home.main.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import com.home.main.image.MonochromeEdgeDetection.Pixel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6687183069682212107L;
	
	private BufferedImage img;
	private int height;
	private int width;
	Set<Pixel> pixelToDraw = new HashSet<Pixel>();
	private boolean export;

	public void paint(Graphics g){
		super.paint(g);
		if (img != null){
			Dimension d = new Dimension(width, height);
			if (!export){
				d = getScaledDimension(new Dimension(width, height), this.getSize());
			}
			g.drawImage(img, 0, 0, d.width, d.height, null);
			double xScale = (d.width+.0)/(width+.0);
			double yScale = (d.height+.0)/(height+.0);
			for (Pixel p : pixelToDraw){
				g.setColor(p.c);
				g.drawRect((int) (p.x*xScale), (int) (p.y*yScale), 0, 0);
			}
		}
	}
	
	public BufferedImage getImage() {
		export = true;
		long s = System.currentTimeMillis();
	    int w = img.getWidth();
	    int h = img.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
	    Graphics2D g = bi.createGraphics();
	    this.paint(g);
	    long e = System.currentTimeMillis();
	    System.out.println("Generating image: "+(e-s));
	    export = false;
	    return bi;
	}
	
	public void addPixel(Pixel p){
		pixelToDraw.add(p);
	}
	
	public void addPixels(Collection<Pixel> p){
		pixelToDraw.addAll(p);
	}
	
	public void clearPixels(){
		pixelToDraw.clear();
	}
	
	public void setImage(BufferedImage img){
		this.img = img;
		this.width = img.getWidth();
		this.height = img.getHeight();
		repaint();
	}
	
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
}
