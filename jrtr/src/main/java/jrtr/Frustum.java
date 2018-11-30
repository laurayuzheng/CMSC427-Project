package jrtr;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

/**
 * Stores the specification of a viewing frustum, or a viewing
 * volume. The viewing frustum is represented by a 4x4 projection
 * matrix. You will extend this class to construct the projection 
 * matrix from intuitive parameters.
 * <p>
 * A scene manager (see {@link SceneManagerInterface}, {@link SimpleSceneManager}) 
 * stores a frustum.
 */
public class Frustum {

	private Matrix4f projectionMatrix;
	
	/**
	 * Construct a default viewing frustum. The frustum is given by a 
	 * default 4x4 projection matrix.
	 */
	public Frustum()
	{
		projectionMatrix = new Matrix4f();
		float f[] = {2.f, 0.f, 0.f, 0.f, 
					 0.f, 2.f, 0.f, 0.f,
				     0.f, 0.f, -1.02f, -2.02f,
				     0.f, 0.f, -1.f, 0.f};
		projectionMatrix.set(f);
	}
	
	/**
	 * Return the 4x4 projection matrix, which is used for example by 
	 * the renderer.
	 * 
	 * @return the 4x4 projection matrix
	 */
	public Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}
	
	public void setProjectionMatrix(Matrix4f m)
	{
		this.projectionMatrix = m;
	}
	
	public boolean shouldCull (RenderItem s) {
		boolean shouldCull = false;
		float radius = s.getShape().radius;
		Matrix4f t = s.getT();
				
		Vector4f p4 = new Vector4f(s.getShape().center.x, s.getShape().center.y, s.getShape().center.z, 1);
		t.transform(p4);
		Vector3f p = new Vector3f(p4.x,p4.y,p4.z);
		
		float scalar = 1/(float)Math.sqrt(2);
		
		Vector3f n1 = new Vector3f(scalar,0,scalar);
		Vector3f n2 = new Vector3f(-scalar,0,scalar);
		Vector3f n3 = new Vector3f(0,scalar,scalar);
		Vector3f n4 = new Vector3f(0,-scalar,scalar);
		Vector3f[] n = {n1,n2,n3,n4};
		
		for (int i = 0; i < 4; i++) {
			if (p.dot(n[i]) > (radius*2.5)) {
				shouldCull = true;
			}
		}
		
		return shouldCull;
	}
}
