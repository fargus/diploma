package com.home.main.image;

import com.home.main.algorithm.Algorithm2;
import com.home.main.fuzzyset.FuzzySet;

import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;

public class ColorEdgeDetection extends AbstractEdgeDetection {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ColorEdgeDetection.class);

	public ColorEdgeDetection(Algorithm2 fuzzyLogic) {
		this.fuzzyLogic = fuzzyLogic;
		v = fuzzyLogic.getOutputVar();
	}

	@Override
	protected void detectEdges() {
		int k = 0;
		pixelToDraw = new ArrayList<Pixel>();
		long start = System.currentTimeMillis();
		int[][] pixels = convertTo2DWithoutUsingGetRGB(originImage);
		double[][] positions = convertToPosition(pixels);
		updateStatus("Detecting Edges...");
		for (int i = 0; i < pixels.length - 1; i++) {
			for (int j = 0; j < pixels[i].length - 1; j++) {
				double p1p2 = Math.abs(positions[i][j]-positions[i][j+1]);
				double p2p3 = Math.abs(positions[i][j+1]-positions[i+1][j+1]);
				double p3p4 = Math.abs(positions[i+1][j+1]-positions[i+1][j]);
				double p4p1 = Math.abs(positions[i+1][j]-positions[i][j]);
				double p1p3 = Math.abs(positions[i][j]-positions[i+1][j+1]);
				double p2p4 = Math.abs(positions[i][j+1]-positions[i+1][j]);

				double[] inputVals = new double[6];
				inputVals[0] = p1p2;
				inputVals[1] = p2p3;
				inputVals[2] = p3p4;
				inputVals[3] = p4p1;
				inputVals[4] = p1p3;
				inputVals[5] = p2p4;

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
				//System.out.println("Distances\n");
				//System.out.println("["+p1p2+"] ["+p2p3+"] ["+p3p4+"] ["+p4p1+"] ["+p1p3+"] ["+p2p4+"]");

				k++;
				updateProgress(k);
			}
			if (isLiveView)
				updateView();
		}
		updateView();
		long end = System.currentTimeMillis();
		System.out.println("Main loop: "+(end-start));
	}

	private double[][] convertToPosition(int[][] pixels){
		double[][] positions = new double[pixels.length][pixels[0].length];
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				Color c = Color.decode(Integer.toString(pixels[i][j]));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				double position = Math.sqrt(r*r+b*b+g*g)/441.6729559300637;
				//System.out.print("["+position+"] ");
				positions[i][j] = position;
			}
			System.out.println();
		}

		return positions;
	}
}
