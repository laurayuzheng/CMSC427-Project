package jrtr;
import java.util.LinkedList;

import javax.vecmath.*;

import jrtr.VertexData.VertexElement;

/**
 * Represents a 3D object. The shape references its geometry, 
 * that is, a triangle mesh stored in a {@link VertexData} 
 * object, its {@link Material}, and a transformation {@link Matrix4f}.
 */
public class Shape {

	private Material material;
	private VertexData vertexData;
	private Matrix4f t;
	public Point3f center;
	public float radius;
	
	/**
	 * Make a shape from {@link VertexData}. A shape contains the geometry 
	 * (the {@link VertexData}), material properties for shading (a 
	 * reference to a {@link Material}), and a transformation {@link Matrix4f}.
	 *  
	 *  
	 * @param vertexData the vertices of the shape.
	 */
	// iterate through all vertices to find the center
	// iterate through all vertices again to find the radius
	// store center and radius as properties of this object
	public Shape(VertexData vertexData)
	{
		this.vertexData = vertexData;
		t = new Matrix4f();
		t.setIdentity();
		
		LinkedList<VertexElement> elements = vertexData.getElements();
		float[] vertices = elements.getLast().getData();
		
		float avgX = 0;
		float avgY = 0;
		float avgZ = 0;
		int numVertices = vertices.length/3;
		
		for (int i = 0; i < numVertices; i++) {
			vertices[i * 3] += avgX;
			vertices[i * 3 + 1] += avgY;
			vertices[i * 3 + 2] += avgZ;
		}
		
		avgX /= numVertices;
		avgY /= numVertices;
		avgZ /= numVertices;
		
		center = new Point3f(avgX,avgY,avgZ);
		
		radius = -1;
		for (int i = 0; i < numVertices; i++) {
			Point3f vertex = new Point3f(vertices[i * 3], vertices[i * 3 + 1], vertices[i * 3 + 2]);
			float dist = vertex.distance(center);
			
			if (dist > radius)
				radius = dist;
		}
		
		System.out.println("Radius: " + radius);
		material = null;
	}
	
	public VertexData getVertexData()
	{
		return vertexData;
	}
	
	public void setTransformation(Matrix4f t)
	{
		this.t = t;
	}
	
	public Matrix4f getTransformation()
	{
		return t;
	}
	
	/**
	 * Set a reference to a material for this shape.
	 * 
	 * @param material
	 * 		the material to be referenced from this shape
	 */
	public void setMaterial(Material material)
	{
		this.material = material;
	}

	/**
	 * To be implemented in the "Textures and Shading" project.
	 */
	public Material getMaterial()
	{
		return material;
	}

}
