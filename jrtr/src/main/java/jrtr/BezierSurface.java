package jrtr;

import javax.vecmath.Point3f;

// part 3 of project 5
public class BezierSurface {
	
	PiecewiseBezier p;
	float v[];
	float n[];
	float uv[];
	int indices[];
	float c[];
	
	public BezierSurface(PiecewiseBezier p) {
		this.p = p;
	}
	
	public void generateSurface(Point3f[] points, Point3f[] tangents, int k) {
		//float angle = 2 * (float)Math.PI / k; // in radians
		
		int vertexCounter = 0;
		int uvCounter = 0;
		
		// initialize vertices
		float v[] = new float[points.length * k * 3];
		float n[] = new float[points.length * k * 3];
		float uv[] = new float[points.length * k * 2];
		float c[] = new float[points.length * k * 3];
		int indices[] = new int[6 * (points.length - 1) * k];
		
		for (int i = 0; i < points.length; i++) {
			v[3 * i] = points[i].x;
			v[3 * i + 1] = points[i].y;
			v[3 * i + 2] = points[i].z;
			
			n[3 * i] = tangents[i].x;
			n[3 * i + 1] = tangents[i].y;
			n[3 * i + 2] = tangents[i].z;
			
			uv[2 * i] = 0;
			uv[2 * i + 1] = 0;
			
			uvCounter += 2;
			vertexCounter += 3;
		}
		
		for (int i = 1; i < k; i++) {
			for (int j = 0; j < points.length; j++) {
				float sin = (float) Math.sin(Math.toRadians((double)360/k * i));
				float cos = (float) Math.cos(Math.toRadians((double)360/k * i));
				
				v[vertexCounter] = points[i].x;
				v[vertexCounter + 1] = points[i].y * cos - points[i].z * sin;
				v[vertexCounter + 2] = points[i].y * sin - points[i].z * cos;
				
				n[vertexCounter] = tangents[i].x;
				n[vertexCounter + 1] = tangents[i].y * cos + tangents[i].z * sin;
				n[vertexCounter + 2] = tangents[i].y * sin + tangents[i].z * cos;
				
				uv[uvCounter] = j / points.length;
				uv[uvCounter + 1] = (float)Math.toRadians((double)360/k * i) / 2 * (float)Math.PI;
				
				vertexCounter += 3;
				uvCounter += 2;
			}
		}
		
		// creating triangle mesh
		
		for (int i = 0; i < k; i++) {
			indices[6*i] = i;
			indices[6*i + 1] = i + k;
			indices[6*i + 2] = k + (i + 1) % k;
			indices[6*i + 3] = i;
			indices[6*i + 4] = k + (i + 1) % k;
			indices[6*i + 5] = (i + 1) % k;
		}
		
		// setting a random color value
		for (int i = 0; i < points.length * k * 3; i++) {
			c[i] = 1;
		}
		
		this.v = v;
		this.n = n;
		this.uv = uv;
		this.indices = indices;
	}
}
