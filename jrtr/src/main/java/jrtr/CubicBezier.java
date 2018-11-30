package jrtr;

import javax.vecmath.Point3f;

public class CubicBezier {
	Point3f p0,p1,p2,p3;
	Point3f[] points, tangents;
	
	public CubicBezier(Point3f p0, Point3f p1, Point3f p2, Point3f p3) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		
		points = null;
		tangents = null;
	}
	
	// n should really be an integer.
	private void evaluateCurve(int n) {
		float t;
		
		for (int i = 0; i < n; i++) {
			t = (float)i / (n - 1);
			
			Point3f a = new Point3f();
			Point3f b = new Point3f();
			Point3f c = new Point3f();
			Point3f d = new Point3f();
			Point3f x = new Point3f();
			Point3f tan = new Point3f();
			
			// computing coefficients a, b, c, and d
			a.x = -p0.x + 3*p1.x - 3*p2.x + p3.x;
			a.y = -p0.y + 3*p1.y - 3*p2.y + p3.y;
			a.z = -p0.z + 3*p1.z - 3*p2.z + p3.z;
			
			b.x = 3*p0.x - 6*p1.x + 3*p2.x;
			b.y = 3*p0.y - 6*p1.y + 3*p2.y;
			b.z = 3*p0.z - 6*p1.z + 3*p2.z;
			
			c.x = -3*p0.x + 3*p1.x;
			c.y = -3*p0.y + 3*p1.y;
			c.z = -3*p0.z + 3*p1.z;
	
			d.x = p0.x;
			d.y = p0.y;
			d.z = p0.z;
	
			// scale the components by t
			// decided not to do it this way for the tangents
//			a.scale(t*t*t);
//			b.scale(t*t);
//			c.scale(t);
			
			// calculate components for x
			x.x = a.x * (t*t*t) + b.x * (t*t) + c.x * t + d.x;
			x.y = a.y * (t*t*t) + b.y * (t*t) + c.y * t + d.y;
			x.z = a.z * (t*t*t) + b.z * (t*t) + c.z * t + d.z;
			
			tan.x = a.x * (3*t*t) + b.x * (2*t) + c.x;
			tan.y = a.y * (3*t*t) + b.y * (2*t) + c.y;
			tan.z = a.z * (3*t*t) + b.z * (2*t) + c.z;
			
			// put the point into the array
			points[i] = x;
			tangents[i] = tan;
		}
	}
	
	public Point3f[] getPoints(int n) {
		points = new Point3f[n];
		tangents = new Point3f[n];
		
		evaluateCurve(n);
		return points;
	}
}
