package com.home.main.func;

public class FuncUtil {
	
	public static Func createFunc(double a, double b, FuncType ft){
		return createFunc(a, b, 0, 0, ft);
	}
	
	public static Func createFunc(double a, double b, double c, FuncType ft){
		return createFunc(a, b, c, 0, ft);
	}
	
	public static Func createFunc(double a, double b, double c, double d, FuncType ft){
		
		switch(ft){
		case LINEDOWN:
			return new LineDownFunc(a, b);
		case LINEUP:
			return new LineUpFunc(a, b);
		case PBELL:
			return new PBellShapedFunc(a, b, c);
		case PPROB:
			return new PProbFunc(a, b);
		case S:
			return new SFunc(a, b);
		case SSPLAIN:
			return new SSplainFunc(a, b);
		case TRAPEZE:
			return new TrapezeFunc(a, b, c, d);
		case TRIANGLE:
			return new TriangleFunc(a, b, c);
		case Z:
			return new ZFunc(a, b);
		case ZSPLAIN:
			return new ZSplainFunc(a, b);
		default:
			return new TriangleFunc(-1, 0, 1);
		
		}
	}
	
	public static Func createComplexFunc(double a, double b, double c, double d, FuncType f1, FuncType f2){
		return new PComplexFunc(createFunc(a, b, f1), createFunc(c, d, f2));
	}

}
