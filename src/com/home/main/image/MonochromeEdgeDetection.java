package com.home.main.image;

import com.home.main.algorithm.Algorithm2;
import com.home.main.fuzzyset.FuzzySet;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;

public class MonochromeEdgeDetection extends AbstractEdgeDetection {

	private static final Logger log = Logger.getLogger(MonochromeEdgeDetection.class);

	public MonochromeEdgeDetection(Algorithm2 fuzzyLogic) {
		this.fuzzyLogic = fuzzyLogic;
		v = fuzzyLogic.getOutputVar();
	}

	protected void detectEdges(){
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
		}
		updateView();
		long end = System.currentTimeMillis();
		System.out.println("Main loop: "+(end-start));
	}
	
}
