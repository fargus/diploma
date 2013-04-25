package com.home.main.image;

import com.home.main.algorithm.Algorithm2;
import com.home.main.ui.ImagePanel;
import com.home.main.variable.Variable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.*;
import java.util.List;

public abstract class AbstractEdgeDetection implements Runnable {

	protected BufferedImage originImage;
	protected BufferedImage resultImage;
	protected Algorithm2 fuzzyLogic;
	protected JProgressBar progress;
	protected ImagePanel panel;
	protected Graphics2D g;
	protected java.util.List<Pixel> pixelToDraw;
	protected JLabel status;
	protected Variable v ;
	protected boolean isRemoveNoize = true;

	public void setImage(BufferedImage originImage) {
		this.originImage = originImage;
	}

	public void setLable(JLabel status){
		this.status=status;
	}

	public BufferedImage getResult(){
		return resultImage;
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

	protected abstract void detectEdges();

	protected static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

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

	protected void removeNoise(){
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
