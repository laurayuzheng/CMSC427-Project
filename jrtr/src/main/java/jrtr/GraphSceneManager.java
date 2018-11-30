package jrtr;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.vecmath.*;

public class GraphSceneManager implements SceneManagerInterface {

	private SceneNode root;
	private LinkedList<Light> lights;
	private Camera camera;
	private Frustum frustum;
	
	/**
	 * Implement the iterative graph traversal here. 
	 */
	private class GraphSceneManagerItr implements SceneManagerIterator {

		// initialize a new stack.
		public GraphSceneManagerItr(GraphSceneManager sceneManager)
		{
			stack = new Stack<SceneNode>();
			tranStack = new Stack<Matrix4f>();
			identity = new Matrix4f();
			identity.setIdentity();
			
			if (sceneManager.root instanceof ShapeNode) {
				stack.push((ShapeNode)sceneManager.root);
				tranStack.push(identity);
			} else {
				stack.push((TransformGroup)sceneManager.root);
				tranStack.push(((TransformGroup)sceneManager.root).getTransformation());
			}
		}
		
		// Dummy implementation.		
		public boolean hasNext()
		{
			return !stack.isEmpty();
		}

		// Dummy implementation
		public RenderItem next()
		{
			popped = stack.pop();
			currMatrix = tranStack.pop();
			if (popped instanceof TransformGroup) {
				TransformGroup transNode = (TransformGroup)popped;
				Matrix4f finalTransformation = new Matrix4f();
				finalTransformation.mul(currMatrix, transNode.getTransformation());
				for(SceneNode n : transNode.children) {
					stack.push(n);
					tranStack.push(finalTransformation);
				}
				return next();
			} else { // is a shape node
				ShapeNode shapeNode = (ShapeNode)popped;
				RenderItem next = new RenderItem(shapeNode.getShape(),currMatrix);
				return next;
			}
		}
		
		Matrix4f identity;
		SceneNode popped = null;
		Stack<SceneNode> stack;
		Stack<Matrix4f> tranStack;
		Matrix4f currMatrix = null;
	}
	
	public GraphSceneManager(SceneNode root)
	{
		this.root = root;
		camera = new Camera();
		frustum = new Frustum();
		lights = new LinkedList<Light>();
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public Frustum getFrustum()
	{
		return frustum;
	}

	public SceneManagerIterator iterator() {
		return new GraphSceneManagerItr(this);
	}
	
	public void addLight(Light light)
	{
		lights.add(light);
	}
	
	public Iterator<Light> lightIterator()
	{
		return lights.iterator();
	}
	
	public void setRoot(SceneNode newRoot) {
		root = newRoot;
	}
	
	public SceneNode getRoot() {
		return root;
	}

}