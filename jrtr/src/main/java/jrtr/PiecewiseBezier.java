package jrtr;

import javax.vecmath.Point3f;

public class PiecewiseBezier {
	
	Point3f[] controls;
	CubicBezier[] segments;
	Point3f[] points,tangents;
	int pointCounter;
	
	public PiecewiseBezier(Point3f[] controls) {
		
		this.controls = controls;
		int numSegments = (controls.length - 1) / 3;
		segments = new CubicBezier[numSegments];
		pointCounter = 0;
		
		// creation of the individual Bezier curves
		for (int i = 0; i < numSegments; i++) {
			segments[i] = new CubicBezier(controls[i*3],
					controls[i*3 +1], 
					controls[i*3 + 2],
					controls[i*3 + 3]);
			
		}
		
	}
	
	public void evaluatePiecewiseCurve(int n) {
		int stepSize = n / segments.length;
		Point3f[] tempPoints = new Point3f[stepSize];
		Point3f[] tempTangents = new Point3f[stepSize];
		
		for(int i = 0; i < segments.length; i++) {
			tempPoints = segments[i].getPoints(stepSize);
			tempTangents = segments[i].getTangents();
			
			for (int j = 0; j < tempPoints.length; j++) {
				points[pointCounter] = tempPoints[j];
				tangents[pointCounter] = tempTangents[j];
				pointCounter++;
			}
		}
	}
	
	public Point3f[] getPoints(int n) {
		points = new Point3f[n];
		tangents = new Point3f[n];
		pointCounter = 0;
		
		evaluatePiecewiseCurve(n);
		return points;
	}
}
