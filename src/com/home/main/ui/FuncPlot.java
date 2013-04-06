package com.home.main.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.variable.Variable;

public class FuncPlot extends Canvas {

	private static final long serialVersionUID = -8132117357890336682L;
	
	private int x;
	private int y;
	
	private double xScale;
	private int xScaleStart;
	
	private List<Func> functions = new ArrayList<Func>();
	private List<Double> additionalPoints = new ArrayList<Double>();
	
	public void clear(){
		functions.clear();
		additionalPoints.clear();
		this.repaint();
	}
	
	public void addFunction(Func f){
		functions.add(f);
		this.repaint();
	}
	
	public void addFunctions(List<Func> funcs){
		functions.addAll(funcs);
		this.repaint();
	}
	
	public void setFunctions(List<Func> funcs){
		functions=funcs;
		this.repaint();
	}
	
	public void drawFunction(Func f){
		this.clear();
		addFunction(f);
	}
	
	public void drawVariable(Variable v){
		this.clear();
		for (FuzzySet f : v.getTerms()){
			functions.add(f.getFunc());
		}
		additionalPoints.add(v.getMin());
		additionalPoints.add(v.getMax());
		this.repaint();
	}
	
	private void drawFunc(Graphics g){
		for(Func f : functions){
			g.setColor(Color.red);
			for (int i = getOx(); i < getXend()-1; i++) {
				int y1 = getYbyValue(f.getValue(getXbyPixel(i)));
				int y2 = getYbyValue(f.getValue(getXbyPixel(i+1)));
				//g.drawRect(i, y, 0, 0);
				g.drawLine(i, y1, i+1, y2);
			}
		}
	}
	
	private TreeSet<Double> getXScalePoints(){
		TreeSet<Double> points = new TreeSet<Double>();  
		for(Func f : functions){
			if (f.getA() != null){
				points.add(f.getA());
			}
			if (f.getB() != null){
				points.add(f.getB());
			}
			if (f.getC() != null){
				points.add(f.getC());
			}
			if (f.getD() != null){
				points.add(f.getD());
			}
		}
		
		return points;
	}
	
	private void drawXScale(Graphics g, TreeSet<Double> points){
		points.addAll(additionalPoints);
		double min = points.first();
		double max = points.last();
		int ident = x/10;
		xScale = ((getXend()-ident)-(getOx()+ident)) / (max-min);
		xScaleStart = (int)((getOx()+ident) - min*xScale);
		g.setColor(Color.gray);
		int y1 = getOy();
		int y2 = getOy()-2;
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 7));
		for(Double d : points){
			int start = getXbyValue(d);
			g.drawLine(start, y1, start, y2);
			g.drawString(Double.toString(d), start-4, getOy()+8);
		}
	}
	
	public FuncPlot()
    {
        setSize(200,150);
        setBackground(Color.white);
        setVisible(true);
        //addFunction(FuncUtil.createFunc(3., 6., FuncType.LINEUP));
        //addFunction(FuncUtil.createFunc(1., 4., FuncType.LINEDOWN));
        //addFunction(FuncUtil.createFunc(2., 5., FuncType.ZSPLAIN));
    }

    public void paint(Graphics g)
    {
    	super.paint(g);
        g.drawLine(getOx(), getOy(), getXend(), getOy());
        g.drawLine(getOx(), getOy(), getOx(), getYend());
        
		drawYScale(g);
		if (!functions.isEmpty()) {
			drawXScale(g, getXScalePoints());
			drawFunc(g);
		}
    }
    
    private void drawYScale(Graphics g){
    	int step = (getOy()-getYend())/10;
    	g.setColor(Color.gray);
    	int start = getOy();
    	int x1=getOx();
    	int x2=getOx()+2;
    	for (int i = 0; i <= 10; i++) {
			g.drawLine(x1, start, x2, start);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 7));
			String s= "";
			if (i==0){
				s="0";
			}else if(i==10){
				s="1";
			}else{
				s="."+Integer.toString(i);
			}
			g.drawString(s, getOx()-8, start+3);
			start-=step;
		}
    }
    
    public void setSize(int x, int y){
    	this.x=x;
    	this.y=y;
    	super.setSize(x, y);
    }
    
    private int getOx(){
    	return 10;
    }
    
    private int getOy(){
    	return y-10;
    }
    
    private int getXend(){
    	return x-10;
    }
    
    private int getYend(){
    	return 10;
    }
    
    private double getXbyPixel(int p){
    	return (p-xScaleStart)/xScale;
    }
    
    private int getXbyValue(double x){
    	return (int)(xScaleStart+xScale*x);
    }
    
    private int getYbyValue(double y){
    	return (int) (getOy()-((getOy()-getYend())*y));
    }
}
