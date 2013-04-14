package com.home.main.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.apache.log4j.Logger;

import com.home.main.algorithm.Algorithm;
import com.home.main.algorithm.Algorithm2;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.ui.ImagePanel;
import com.home.main.variable.Variable;

public class MonochromeEdgeDetection implements Runnable {

	private static final Logger log = Logger.getLogger(MonochromeEdgeDetection.class);

	private BufferedImage originImage;
	private BufferedImage resultImage;
	private Algorithm2 fuzzyLogic;
	private JProgressBar progress;
	private ImagePanel panel;
	private Graphics2D g;
	private List<Pixel> pixelToDraw;
	private JLabel status;
	private Variable v ;
	private boolean isRemoveNoize = true;

	public MonochromeEdgeDetection(Algorithm2 fuzzyLogic) {
		this.fuzzyLogic = fuzzyLogic;
		v = fuzzyLogic.getOutputVar();
	}

	public void setImage(BufferedImage originImage) {
		this.originImage = originImage;
	}
	
	public void setLable(JLabel status){
		this.status=status;
	}
	
	public BufferedImage getResult(){
		return resultImage;
	}

	private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

		final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[height][width];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
				argb += ((int) pixels[pixel + 1] & 0xff); // blue
				argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += -16777216; // 255 alpha
				argb += ((int) pixels[pixel] & 0xff); // blue
				argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		}

		return result;
	}

	public void setProgressBar(JProgressBar progress) {
		this.progress = progress;
	}
	
	public void setImagePanel(ImagePanel panel){
		this.panel = panel;
	}

	public void updateProgress(int k) {
		if (progress != null) {
			progress.setValue(k);
		}
	}
	
	public void updateView(){
		panel.setImage(resultImage);
	}
	
	public void updateStatus(String status){
		this.status.setText(status);
	}

	@Override
	public void run() {
		progress.setMinimum(0);
		progress.setMaximum((originImage.getWidth() - 1) * (originImage.getHeight() - 1));
		panel.clearPixels();
		
		resultImage = new BufferedImage(originImage.getWidth(), originImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		g = resultImage.createGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, resultImage.getWidth(), resultImage.getHeight());
		g.setColor(Color.white);
		
		detectEdges();
		
		if (isRemoveNoize){
			removeNoise();
		}
		
		updateStatus("Done!");
		Thread.currentThread().interrupt();
	}
	
	
	private void detectEdges(){
		int k = 0;
		pixelToDraw = new ArrayList<Pixel>();
		long start = System.currentTimeMillis();
		int[][] pixels = convertTo2DWithoutUsingGetRGB(originImage);
		updateStatus("Detecting Edges...");
		for (int i = 0; i < pixels.length - 1; i++) {
			for (int j = 0; j < pixels[i].length - 1; j++) {
				log.info("[" + i + ", " + j + "]");
				int p1 = Color.decode(Integer.toString(pixels[i][j])).getBlue();
				int p2 = Color.decode(Integer.toString(pixels[i + 1][j])).getBlue();
				int p3 = Color.decode(Integer.toString(pixels[i][j + 1])).getBlue();
				int p4 = Color.decode(Integer.toString(pixels[i + 1][j + 1])).getBlue();

				double[] inputVals = new double[4];
				inputVals[0] = (double)p1;
				inputVals[1] = (double)p2;
				inputVals[2] = (double)p3;
				inputVals[3] = (double)p4;

				double outputVal = fuzzyLogic.run(inputVals);
				FuzzySet term = v.getTermByValue(outputVal);

				if (term != null){
					if (term.getName().equals("edge")) {
						g.drawRect(j, i, 0, 0);
						g.drawRect(j, i + 1, 0, 0);
						g.drawRect(j + 1, i, 0, 0);
						g.drawRect(j + 1, i + 1, 0, 0);
					}
				}
				
				k++;
				updateProgress(k);
			}
			updateView();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Main loop: "+(end-start));
	}
	
	private void removeNoise(){
		long start = System.currentTimeMillis();
		int k=0;
		progress.setValue(0);
		progress.setMaximum((originImage.getWidth() - 2) * (originImage.getHeight() - 2));
		g.setColor(Color.black);
		int white = Color.white.getRGB();
		pixelToDraw.clear();
		List<Pixel> toDraw = new LinkedList<Pixel>();
		int[][] pixels = convertTo2DWithoutUsingGetRGB(resultImage);
		updateStatus("Removing noize...");
		for (int i = 0; i < pixels.length - 2; i++) {
			for (int j = 0; j < pixels[i].length - 2; j++) {
				if (pixels[i][j] == white &&
						pixels[i+1][j] == white &&
						pixels[i+2][j] == white &&
						pixels[i][j+1] == white &&
						pixels[i+2][j+1] == white &&
						pixels[i+2][j] == white &&
						pixels[i+2][j+1] == white &&
						pixels[i+2][j+2] == white ){
					//g.drawRect(j+1,i+1, 0, 0);
					toDraw.add(new Pixel(j+1,i+1, Color.black));
				}
				k++;
				updateProgress(k);
			}
			//updateView();
		}

		panel.addPixels(toDraw);
		panel.clearPixels();
		long end = System.currentTimeMillis();
		System.out.println("Noize loop: "+(end-start));
	}
	
	
	public class Pixel{
		public int x;
		public int y;
		public Color c;
		
		public Pixel(int x, int y, Color c) {
			this.x = x;
			this.y =y;
			this.c=c;
		}
		
		public void draw(Graphics g){
			g.setColor(c);
			g.drawRect(x, y, 0, 0);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pixel other = (Pixel) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}


	public void isRemoveNoize(boolean selected) {
		isRemoveNoize = selected;
	}
}
