package simple;

import jrtr.*;
import jrtr.Light.Type;
import jrtr.gldeferredrenderer.Matrix4fUtils;
import jrtr.glrenderer.*;
//mport jrtr.swrenderer.*;

import javax.swing.*;

import java.awt.Point;
import java.awt.event.*;
import java.io.IOException; 
import javax.vecmath.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implements a simple 3D rendering application using the 3D rendering API 
 * provided by the package {@link jrtr}. Opens a 3D rendering window and 
 * shows a rotating cube. 
 */
public class simple
{	
	static RenderPanel renderPanel;
	static RenderContext renderContext;
	static Shader normalShader;
	static Shader diffuseShader;
	static Material material;
	static GraphSceneManager sceneManager;
	static Shape shape;
	static float currentstep, basicstep;
	static Shape cylinder;
	static Shape torus;
	static Shape cube;
	static Shape cylinder1;
	static Shape torus1;
	static Shape torus2;
	static Shape torus3;
	static Shape torus4;
	static Shape plane;
	static int currentShape;
	static Point m1;
	static Point m2;
	static Vector3d v;
	static Vector3d w;
	static Shape teapot;
	
	/**
	 * An extension of {@link GLRenderPanel} or {@link SWRenderPanel} to 
	 * provide a call-back function for initialization. 
	 */ 
	public final static class SimpleRenderPanel extends GLRenderPanel
	{
		/**
		 * Initialization call-back. We initialize our renderer and scene here.
		 * We construct a simple 3D scene consisting of a cube and start a timer 
		 * task to generate an animation.
		 * 
		 * @param r	the render context that is associated with this render panel
		 */
		public void init(RenderContext r)
		{
			
//			Matrix4f scaleWheel = new Matrix4f();
//			Matrix4f currentTransformation = new Matrix4f();
			
			renderContext = r;
										
			// Make a scene manager and add the object
//			sceneManager = new SimpleSceneManager();
			
			cylinder = new Shape(makeCylinder(24));
			torus = new Shape(makeTorus(24));
			cube = new Shape(makeCube());
			
//			renderTeapots();
//			renderRobot();
			
			
//			cylinder1 = new Shape(makeCylinder(24));
//			torus1 = new Shape(makeTorus(24));
//			torus2 = new Shape(makeTorus(24));
//			torus3 = new Shape(makeTorus(24));
//			torus4 = new Shape(makeTorus(24));
//			plane = new Shape(makePlane());
//			
//			scaleWheel.setIdentity();
//			scaleWheel.setScale(0.25f);
//			
//			currentTransformation = torus1.getTransformation();
//			currentTransformation.mul(scaleWheel);
//			torus1.setTransformation(currentTransformation);
//			
//			currentTransformation = torus1.getTransformation();
//			currentTransformation.setTranslation(new Vector3f(0,-1,1));
//			torus1.setTransformation(currentTransformation);
//			
//			currentTransformation = torus2.getTransformation();
//			currentTransformation.mul(scaleWheel);
//			torus2.setTransformation(currentTransformation);
//			
//			currentTransformation = torus2.getTransformation();
//			currentTransformation.setTranslation(new Vector3f(0,-1,-1));
//			torus2.setTransformation(currentTransformation);
//			
//			currentTransformation = torus3.getTransformation();
//			currentTransformation.mul(scaleWheel);
//			torus3.setTransformation(currentTransformation);
//			
//			currentTransformation = torus3.getTransformation();
//			currentTransformation.setTranslation(new Vector3f(-3,-1,-1));
//			torus3.setTransformation(currentTransformation);
//			
//			currentTransformation = torus4.getTransformation();
//			currentTransformation.mul(scaleWheel);
//			torus4.setTransformation(currentTransformation);
//			
//			currentTransformation = torus4.getTransformation();
//			currentTransformation.setTranslation(new Vector3f(-3,-1,1));
//			torus4.setTransformation(currentTransformation);
//			
//			currentTransformation = cylinder1.getTransformation();
//			currentTransformation.rotZ((float)Math.PI/2);
//			cylinder1.setTransformation(currentTransformation);
			
//			System.out.print("shape initialized");
//			shape = cylinder;
//			sceneManager.addShape(shape);
//			currentShape = 0;
			
			// Add the scene to the renderer
//			renderContext.setSceneManager(sceneManager);
			
			// Load some more shaders
//		    normalShader = renderContext.makeShader();
//		    try {
//		    	normalShader.load("../jrtr/shaders/normal.vert", "../jrtr/shaders/normal.frag");
//		    } catch(Exception e) {
//		    	System.out.print("Problem with shader:\n");
//		    	System.out.print(e.getMessage());
//		    }
//	
//		    diffuseShader = renderContext.makeShader();
//		    try {
//		    	diffuseShader.load("../jrtr/shaders/diffuse.vert", "../jrtr/shaders/diffuse.frag");
//		    } catch(Exception e) {
//		    	System.out.print("Problem with shader:\n");
//		    	System.out.print(e.getMessage());
//		    }
//
//		    diffuseShader = renderContext.makeShader();
//		    try {
//		    	diffuseShader.load("../jrtr/shaders/blinn.vert", "../jrtr/shaders/blinn.frag");
//		    } catch(Exception e) {
//		    	System.out.print("Problem with shader:\n");
//		    	System.out.print(e.getMessage());
//		    }
//		    
//		    // define the lights
//		    Light light = new Light();
//		    light.position = new Vector3f(0f,0f,5f);
//		    light.diffuse = new Vector3f(100f,100f,100f);
//		    light.type = Type.POINT;
//		    sceneManager.addLight(light);
//		    
//		    // Make a material that can be used for shading
//			material = new Material();
//			material.shader = diffuseShader;
//			material.diffuseMap = renderContext.makeTexture();
//			try {
//				material.diffuseMap.load("../textures/plant.jpg");
//			} catch(Exception e) {				
//				System.out.print("Could not load texture.\n");
//				System.out.print(e.getMessage());
//			}

			VertexData teapotData;
			try {
				teapotData = ObjReader.read("/Users/laurazheng/eclipse-workspace/CMSC427/JRTR-Base-Code/obj/teapot.obj", 2, renderContext);
				teapot = new Shape(teapotData);
				float[] c = new float[teapotData.getNumberOfVertices() * 3];
				
				for (int i = 0; i < teapotData.getNumberOfVertices(); i++) {
					c[3*i] = 0;
					c[3*i + 1] = 0;
					c[3*i + 2] = 1;
				}
				teapotData.addElement(c, VertexData.Semantic.COLOR, 3);
//				sceneManager.addShape(teapot);
			} catch (IOException e) {
				System.out.println("no file exists");
			}
			
//			ShapeNode temp = new ShapeNode(teapot);
//			TransformGroup root = new TransformGroup();
//			TransformGroup tpTransform = new TransformGroup();
//			tpTransform.children.add(temp);
//			tpTransform.transformation.set(new Vector3f(1,1,0));
////			ShapeNode cubeShape = new ShapeNode(cube);
//			root.children.add(tpTransform);
//			root.transformation = new Matrix4f();
//			root.transformation.setIdentity();
//			sceneManager = new GraphSceneManager(root);
//            renderContext.setSceneManager(sceneManager);
			
			TransformGroup root = new TransformGroup();
			root.transformation.setIdentity();
			ShapeNode teapotNode = new ShapeNode(teapot);
			
			for (int i = -50; i < 50; i++) {
				for (int j = -50; j < 50; j++) {
					TransformGroup tpTransform = new TransformGroup();
					TransformGroup tpTransform2 = new TransformGroup();

					tpTransform.transformation.set(new Vector3f(i,j,0));
					tpTransform2.transformation.set(0.4f);
					tpTransform.children.add(tpTransform2);
					tpTransform2.children.add(teapotNode);
					root.children.add(tpTransform);
				}
			}
			
			sceneManager = new GraphSceneManager(root);
            renderContext.setSceneManager(sceneManager);
			
			// Register a timer task
		    Timer timer = new Timer();
		    basicstep = 0.01f;
		    currentstep = 0;
		    timer.scheduleAtFixedRate(new AnimationTask(), 0, 10);
		}
		
//		public void renderTeapots() {
//			TransformGroup root = new TransformGroup();
//			root.transformation.setIdentity();
//			ShapeNode teapotNode = new ShapeNode(teapot);
//			
//			for (int i = 0; i < 100; i++) {
//				for (int j = 0; j < 100; j++) {
//					TransformGroup tpTransform = new TransformGroup();
//					tpTransform.transformation.set(new Vector3f(i,j,0));
//					tpTransform.children.add(teapotNode);
//					root.children.add(tpTransform);
//				}
//			}
//			
//			root.children.add(teapotNode);
//			
//			sceneManager = new GraphSceneManager(root);
//            renderContext.setSceneManager(sceneManager);
//		}
		
		public void renderRobot() {
//            GraphSceneManager.CULL = false;
//            GLRenderContext.PRINT = false;
//            showingRobot = true;
            
//			Matrix4f currentRotation = ((TransformGroup)sceneManager.getRoot()).transformation;

            TransformGroup root = new TransformGroup();
            Shape cube = new Shape(makeCube());
            ShapeNode cubeNode = new ShapeNode(cube);
            
            TransformGroup rotation = new TransformGroup();
//            rotation.transformation = currentRotation;
            rotation.transformation.setIdentity();
            root.children.add(rotation);
            
            TransformGroup startingPos = new TransformGroup();
            startingPos.transformation.setIdentity();
            startingPos.transformation.setTranslation(new Vector3f(-2, 0, 0));
            rotation.children.add(startingPos);
            
            TransformGroup rotateUp = new TransformGroup();
//            rotateUp.transformation = upRotation;
            rotateUp.transformation.setIdentity();
            startingPos.children.add(rotateUp);
            
            TransformGroup rotateDown = new TransformGroup();
//            rotateDown.transformation = downRotation;
            rotateDown.transformation.setIdentity();
            startingPos.children.add(rotateDown);
            
            TransformGroup body = new TransformGroup();
            body.transformation = new Matrix4f();
            body.transformation.setIdentity();
            body.transformation.m00 = 0.5f;
            body.transformation.m22 = 0.5f;
            body.children.add(cubeNode);
            startingPos.children.add(body);
            
            TransformGroup head = new TransformGroup();
            head.transformation = Matrix4fUtils.scaled(0.25f);
            head.transformation.setTranslation(new Vector3f(0, 1.25f, 0));
            head.children.add(cubeNode);
            startingPos.children.add(head);
            
            TransformGroup legL = new TransformGroup();
            legL.transformation = Matrix4fUtils.translated(-0.625f, -1, 0);
            TransformGroup thighL = new TransformGroup();
            thighL.transformation = new Matrix4f();
            thighL.transformation.setIdentity();
            thighL.transformation.m00 = 0.125f;
            thighL.transformation.m11 = 0.75f;
            thighL.transformation.m22 = 0.125f;
            thighL.children.add(cubeNode);
            legL.children.add(thighL);
            TransformGroup footL = new TransformGroup();
            footL.transformation = new Matrix4f();
            footL.transformation.setIdentity();
            footL.transformation.m00 = 0.125f;
            footL.transformation.m11 = 0.125f;
            footL.transformation.m22 = 0.5f;
            footL.transformation.setTranslation(new Vector3f(0, -0.875f, 0.375f));
            footL.children.add(cubeNode);
            legL.children.add(footL);
            rotateDown.children.add(legL);
            
            TransformGroup legR = new TransformGroup();
            legR.transformation = Matrix4fUtils.translated(0.625f, -1, 0);
            TransformGroup thighR = new TransformGroup();
            thighR.transformation = new Matrix4f();
            thighR.transformation.setIdentity();
            thighR.transformation.m00 = 0.125f;
            thighR.transformation.m11 = 0.75f;
            thighR.transformation.m22 = 0.125f;
            thighR.children.add(cubeNode);
            legR.children.add(thighR);
            TransformGroup footR = new TransformGroup();
            footR.transformation = new Matrix4f();
            footR.transformation.setIdentity();
            footR.transformation.m00 = 0.125f;
            footR.transformation.m11 = 0.125f;
            footR.transformation.m22 = 0.5f;
            footR.transformation.setTranslation(new Vector3f(0, -0.875f, 0.375f));
            footR.children.add(cubeNode);
            legR.children.add(footR);
            rotateUp.children.add(legR);
            
            TransformGroup armL = new TransformGroup();
            armL.transformation = Matrix4fUtils.translated(-0.625f, 0.25f, 0.5f);
            TransformGroup upperArmL = new TransformGroup();
            upperArmL.transformation = new Matrix4f();
            upperArmL.transformation.setIdentity();
            upperArmL.transformation.m00 = 0.125f;
            upperArmL.transformation.m11 = 0.125f;
            upperArmL.transformation.m22 = 0.5f;
            upperArmL.children.add(cubeNode);
            armL.children.add(upperArmL);
            TransformGroup handL = new TransformGroup();
            handL.transformation = new Matrix4f();
            handL.transformation.setIdentity();
            handL.transformation.mul(Matrix4fUtils.scaled(0.1875f));
            handL.transformation.setTranslation(new Vector3f(0, 0, 0.6875f));
            handL.children.add(cubeNode);
            armL.children.add(handL);
            rotateUp.children.add(armL);
            
            TransformGroup armR = new TransformGroup();
            armR.transformation = Matrix4fUtils.translated(0.625f, 0.25f, 0.5f);
            TransformGroup upperArmR = new TransformGroup();
            upperArmR.transformation = new Matrix4f();
            upperArmR.transformation.setIdentity();
            upperArmR.transformation.m00 = 0.125f;
            upperArmR.transformation.m11 = 0.125f;
            upperArmR.transformation.m22 = 0.5f;
            upperArmR.children.add(cubeNode);
            armR.children.add(upperArmR);
            TransformGroup handR = new TransformGroup();
            handR.transformation = new Matrix4f();
            handR.transformation.setIdentity();
            handR.transformation.mul(Matrix4fUtils.scaled(0.1875f));
            handR.transformation.setTranslation(new Vector3f(0, 0, 0.6875f));
            handR.children.add(cubeNode);
            armR.children.add(handR);
            rotateDown.children.add(armR);
            
            sceneManager = new GraphSceneManager(root);
            renderContext.setSceneManager(sceneManager);
		}
		
		
		/**
		 * Make a mesh for a cube.
		 * 
		 * @return vertexData the data representing the cube mesh
		 */
		private VertexData makeCube()
		{
			// Make a simple geometric object: a cube
		
			// The vertex positions of the cube
			float v[] = {-1,-1,1, 1,-1,1, 1,1,1, -1,1,1,		// front face
				         -1,-1,-1, -1,-1,1, -1,1,1, -1,1,-1,	// left face
					  	 1,-1,-1,-1,-1,-1, -1,1,-1, 1,1,-1,		// back face
						 1,-1,1, 1,-1,-1, 1,1,-1, 1,1,1,		// right face
						 1,1,1, 1,1,-1, -1,1,-1, -1,1,1,		// top face
						-1,-1,1, -1,-1,-1, 1,-1,-1, 1,-1,1};	// bottom face

			// The vertex normals 
			float n[] = {0,0,1, 0,0,1, 0,0,1, 0,0,1,			// front face
				         -1,0,0, -1,0,0, -1,0,0, -1,0,0,		// left face
					  	 0,0,-1, 0,0,-1, 0,0,-1, 0,0,-1,		// back face
						 1,0,0, 1,0,0, 1,0,0, 1,0,0,			// right face
						 0,1,0, 0,1,0, 0,1,0, 0,1,0,			// top face
						 0,-1,0, 0,-1,0, 0,-1,0, 0,-1,0};		// bottom face

			// The vertex colors
			float c[] = {1,0,0, 1,0,0, 1,0,0, 1,0,0,
					     0,1,0, 0,1,0, 0,1,0, 0,1,0,
						 1,0,0, 1,0,0, 1,0,0, 1,0,0,
						 0,1,0, 0,1,0, 0,1,0, 0,1,0,
						 0,0,1, 0,0,1, 0,0,1, 0,0,1,
						 0,0,1, 0,0,1, 0,0,1, 0,0,1};

			// Texture coordinates 
			float uv[] = {0,0, 1,0, 1,1, 0,1,
					  0,0, 1,0, 1,1, 0,1,
					  0,0, 1,0, 1,1, 0,1,
					  0,0, 1,0, 1,1, 0,1,
					  0,0, 1,0, 1,1, 0,1,
					  0,0, 1,0, 1,1, 0,1};

			// Construct a data structure that stores the vertices, their
			// attributes, and the triangle mesh connectivity
			VertexData vertexData = renderContext.makeVertexData(24);
			vertexData.addElement(c, VertexData.Semantic.COLOR, 3);
			vertexData.addElement(v, VertexData.Semantic.POSITION, 3);
			vertexData.addElement(n, VertexData.Semantic.NORMAL, 3);
			vertexData.addElement(uv, VertexData.Semantic.TEXCOORD, 2);
			
//			// The triangles (three vertex indices for each triangle)
			int indices[] = {0,2,3, 0,1,2,			// front face
							 4,6,7, 4,5,6,			// left face
							 8,10,11, 8,9,10,		// back face
							 12,14,15, 12,13,14,	// right face
							 16,18,19, 16,17,18,	// top face
							 20,22,23, 20,21,22};	// bottom face

			vertexData.addIndices(indices);
			
			return vertexData;
		}
		
		
		// p is the dimension of the cylinder
		// radius 1
		// height 3
		// center of base at origin (0,0,0)
		// base of cylinder on xz plane, height on y axis
		private VertexData makeCylinder(int p) {
			float v[] = new float[12*p + 6];
			int indices[] = new int[12*p];
			float c[] = new float[12*p + 6];
			float n[] = new float[12*p + 6];
			
			for (int i = 0; i < 12*p + 6; i++) {
//				c[3*i] = 1;
//				c[3*i + 1] = 0;
//				c[3*i + 2] = 0;
//				
//				c[3*p + 3*i] = 0;
//				c[3*p + 3*i + 1] = 1;
//				c[3*p + 3*i + 2] = 1;
				c[i] = 1;
			}
			
//			c[6*p] = 1;
//			c[6*p + 1] = 0;
//			c[6*p + 2] = 0;
//			
//			c[6*p + 3] = 1;
//			c[6*p + 4] = 0;
//			c[6*p + 5] = 0;
			
			for (int i = 0; i < p; i++) {
				v[3*i] = (float) Math.cos((i) * 2 * Math.PI/p);
				v[3*i + 1] = 3;
				v[3*i + 2] = (float) Math.sin((i ) * 2 * Math.PI/p);
				
				n[3*i] = 0;
				n[3*i + 1] = 1;
				n[3*i + 2] = 0;
				
				v[3*p + 3*i] = (float) Math.cos(i * 2 * Math.PI/p);
				v[3*p + 3*i + 1] = 0;
				v[3*p + 3*i + 2] = (float) Math.sin(i  * 2 * Math.PI/p);
				
				n[3*p + 3*i] = 0;
				n[3*p + 3*i + 1] = -1;
				n[3*p + 3*i + 2] = 0;
			}
			
			for (int i = 0; i < p; i++) {
				v[6*p + 3*i] = (float) Math.cos((i) * 2 * Math.PI/p);
				v[6*p + 3*i + 1] = 3;
				v[6*p + 3*i + 2] = (float) Math.sin((i ) * 2 * Math.PI/p);
				
				n[6*p + 3*i] = (float) Math.cos((i) * 2 * Math.PI/p);
				n[6*p + 3*i + 1] = 0;
				n[6*p + 3*i + 2] = (float) Math.sin((i ) * 2 * Math.PI/p);
				
				v[9*p + 3*i] = (float) Math.cos(i * 2 * Math.PI/p);
				v[9*p + 3*i + 1] = 0;
				v[9*p + 3*i + 2] = (float) Math.sin(i  * 2 * Math.PI/p);
				
				n[9*p + 3*i] = (float) Math.cos(i * 2 * Math.PI/p);
				n[9*p + 3*i + 1] = 0;
				n[9*p + 3*i + 2] = (float) Math.sin(i  * 2 * Math.PI/p);
			}
			
			// the two center points
			v[12*p] = 0;
			v[12*p + 1] = 3;
			v[12*p + 2] = 0;
			
			v[12*p + 3] = 0;
			v[12*p + 4] = 0;
			v[12*p + 5] = 0;

	
			
			VertexData vertexData = renderContext.makeVertexData(4*p + 2);
			vertexData.addElement(c, VertexData.Semantic.COLOR, 3);
			vertexData.addElement(v, VertexData.Semantic.POSITION, 3);
			
			// creating mesh for sides
			for (int i = 0; i < p; i++) {
				indices[12*i] = i;
				indices[12*i + 1] = p + i;
				indices[12*i + 2] = p + (i + 1) % p;
				indices[12*i + 3] = i;
				indices[12*i + 4] = p + (i + 1) % p;
				indices[12*i + 5] = (i + 1) % p;
				indices[12*i + 6] = i;
				indices[12*i + 7] = (i + 1) % p;
				indices[12*i + 8] = 2*p + 1;
				indices[12*i + 9] = p + i;
				indices[12*i + 10] = p + (i + 1) % p;
				indices[12*i + 11] = 2*p + 2;
			}
			
			vertexData.addIndices(indices);
			
			return vertexData;
		}
		
		private VertexData makeTorus(int p) {
			float v[] = new float[3*p*p];
			int indices[] = new int[6*p*p];
			float c[] = new float[3*p*p];
			
			// initialize two angles for formula
			double gamma = 0;
			double theta = 0;
			
			// parameters for the torus
			int R = 2;
			int r = 1;
			
			// for each circle in the torus..
			// gamma changes
			for (int i = 0; i < p; i++) {
				gamma = Math.toRadians(360f / p * i);
				// for each vertex in the circle
				// theta changes
				for (int j = 0; j < p; j++) {
					theta = Math.toRadians(360f / p * j);
					
					// x coordinate
					v[3*i*p + 3*j] = (float) ((R + r*Math.cos(theta)) * Math.cos(gamma));
					v[3*i*p + 3*j + 1] = (float) ((R + r*Math.cos(theta)) * Math.sin(gamma));
					v[3*i*p + 3*j + 2] = (float) (r * Math.sin(theta));
				}
			}
			
			// coloring
			for (int i = 0; i < p/2; i++) {
				for (int j = 0; j < p; j++) {
					c[3*i*p + 3*j] = 1;
					c[3*i*p + 3*j + 1] = 0;
					c[3*i*p + 3*j + 1] = 0;
				}
			}
			
			for (int i = p/2; i < p; i++) {
				for (int j = 0; j < p; j++) {
					c[3*i*p + 3*j] = 1;
					c[3*i*p + 3*j + 1] = 0;
					c[3*i*p + 3*j + 1] = 1;
				}
			}
			
			VertexData vertexData = renderContext.makeVertexData(p*p);
			vertexData.addElement(c, VertexData.Semantic.COLOR, 3);
			vertexData.addElement(v, VertexData.Semantic.POSITION, 3);
			
			for (int i = 0; i < p; i++) {
				for (int j = 0; j < p; j++) {
					indices[6*p*i + 6*j] = i*p + j;
					indices[6*p*i + 6*j + 1] = ((i+1)%p)*p + j;
					indices[6*p*i + 6*j + 2] = ((i+1)%p)*p + (j+1)%p;
					indices[6*p*i + 6*j + 3] = i*p + j;
					indices[6*p*i + 6*j + 4] = ((i+1)%p)*p + (j+1)%p;
					indices[6*p*i + 6*j + 5] = i*p + (j+1)%p;
				}
			}
			
			
			vertexData.addIndices(indices);
			
			return vertexData;
		}
		
		private VertexData makePlane() {
			float v[] = {10,-1.75f,10,10,-1.75f,-10,-10,-1.75f,10,-10,-1.75f,-10};
			int indices[] = {0,1,2,1,2,3};
			float c[] = new float[12];
			
			for (int i = 0; i < 4; i++) {
				c[3*i] = 0.2f;
				c[3*i + 1] = 0.6f;
				c[3*i + 2] = 0.7f;
			}
			
			VertexData vertexData = renderContext.makeVertexData(4);
			vertexData.addElement(c, VertexData.Semantic.COLOR, 3);
			vertexData.addElement(v, VertexData.Semantic.POSITION, 3);
			
			vertexData.addIndices(indices);
			
			return vertexData;
		}
	}


	
	/**
	 * A timer task that generates an animation. This task triggers
	 * the redrawing of the 3D scene every time it is executed.
	 */
	public static class AnimationTask extends TimerTask
	{
		public void run()
		{
			// Update transformation by rotating with angle "currentstep"
//			Matrix4f t = shape.getTransformation();
//    		Matrix4f rotX = new Matrix4f();
//    		rotX.rotX(currentstep);
//    		Matrix4f rotY = new Matrix4f();
//    		rotY.rotY(currentstep);
//    		t.mul(rotX);
//    		t.mul(rotY);
//    		shape.setTransformation(t);
//
//			t = cylinder1.getTransformation();
//			rotX = new Matrix4f();
//    		rotX.rotX(currentstep);
//    		t.mul(rotX);
//    		cylinder1.setTransformation(t);
//    		
//    		t = torus1.getTransformation();
//    		Matrix4f rotZ = new Matrix4f();
//    		rotZ.rotZ(currentstep);
//    		t.mul(rotZ);
//    		rotY = new Matrix4f();
//    		rotY.rotY(currentstep);
//    		t.mul(rotY,t);
//    		torus1.setTransformation(t);
//    		
//    		t = torus2.getTransformation();
//    		rotZ = new Matrix4f();
//    		rotZ.rotZ(currentstep);
//    		t.mul(rotZ);
//    		rotY = new Matrix4f();
//    		rotY.rotY(currentstep);
//    		t.mul(rotY,t);
//    		torus2.setTransformation(t);
//    		
//    		t = torus3.getTransformation();
//    		rotZ = new Matrix4f();
//    		rotZ.rotZ(currentstep);
//    		t.mul(rotZ);
//    		rotY = new Matrix4f();
//    		rotY.rotY(currentstep);
//    		t.mul(rotY,t);
//    		torus3.setTransformation(t);
//    		
//    		t = torus4.getTransformation();
//    		rotZ = new Matrix4f();
//    		rotZ.rotZ(currentstep);
//    		t.mul(rotZ);
//    		rotY = new Matrix4f();
//    		rotY.rotY(currentstep);
//    		t.mul(rotY,t);
//    		torus4.setTransformation(t);
//
//    		// Trigger redrawing of the render window
//    		renderPanel.getCanvas().repaint(); 
		}
	}

	/**
	 * A mouse listener for the main window. This can be
	 * used to process mouse events.
	 */
	public static class SimpleMouseListener implements MouseListener
	{
    	public void mousePressed(MouseEvent e) {
    		m1 = e.getPoint();
    	}
    	public void mouseReleased(MouseEvent e) {
//    		m2 = e.getPoint();
    	}
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
    	
    	// part 0
    	public void mouseClicked(MouseEvent e) {
    		if (currentstep == 0) {
    			currentstep = basicstep;
    		} else {
    			currentstep = 0;
    		}
    	}
	}
	
	public static class SimpleMouseMotionListener implements MouseMotionListener
	{

		public void mouseDragged(MouseEvent e) {
//			System.out.println("mouse dragged begins");
			double x = (double)m1.getX() / (renderPanel.getCanvas().getWidth() / 2);
			double y = (double)m1.getY() / (renderPanel.getCanvas().getHeight() / 2);
			
			x = x - 1;
			y = 1 - y;
			
			double z2 = 1 - x * x - y * y;
			double z = z2 > 0 ? Math.sqrt(z2) : 0;
			
			v  = new Vector3d(x,y,z);
			v.normalize();
			
			// we now have xyz coords of start point m1 on the sphere below the mouse pointer.
			m2 = e.getPoint();
			if (!m1.equals(m2)) {
				x = (double)m2.getX() / (renderPanel.getCanvas().getWidth() / 2);
				y = (double)m2.getY() / (renderPanel.getCanvas().getHeight() / 2);
				
				x = x - 1;
				y = 1 - y;
				
				z2 = 1 - x * x - y * y;
				z = z2 > 0 ? Math.sqrt(z2) : 0;
				
				w  = new Vector3d(x,y,z);
				w.normalize();
				
				// we now have xyz coords of end point m2 on the sphere below the mouse pointer.
				
				// start point on the sphere is v, end point on the sphere is w (both static).
				
				Vector3d axis = new Vector3d();
				axis.cross(v,w);
				axis.normalize();
				double theta = v.angle(w);
				
				// now the point is represented in axis-angle.
				// now apply new rotation to the current orientation.
				
				AxisAngle4d axisAngle = new AxisAngle4d(axis, theta);
				Matrix4f rotMatrix = new Matrix4f();
				rotMatrix.setIdentity();
				rotMatrix.set(axisAngle);
				
				// the rotation matrix is created.
				// now to create the translational matrix.
				
	//			float xTrans = shape.getTransformation().getM03();
	//			float yTrans = shape.getTransformation().getM13();
	//			float zTrans = shape.getTransformation().getM23();
				
	//			Matrix4f transMatrix = new Matrix4f(1,0,0,-xTrans,0,1,0,-yTrans,0,0,1,-zTrans,0,0,0,1);
//				Matrix4f t = shape.getTransformation();
				Matrix4f t = new Matrix4f();

				
				t.set(axisAngle);
				TransformGroup newRoot = new TransformGroup();
				newRoot.children.add(sceneManager.getRoot());
				newRoot.transformation = t;
				sceneManager.setRoot(newRoot);
//				rotMatrix.mul(t);
	//			t.mul(transMatrix);
//				shape.setTransformation(rotMatrix);
			}
		}

		public void mouseMoved(MouseEvent e) {}
		
	}
	
	/**
	 * A key listener for the main window. Use this to process key events.
	 * Currently this provides the following controls:
	 * 's': stop animation
	 * 'p': play animation
	 * '+': accelerate rotation
	 * '-': slow down rotation
	 * 'd': default shader
	 * 'n': shader using surface normals
	 * 'm': use a material for shading
	 */
	public static class SimpleKeyListener implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyChar())
			{
				case 's': {
					// Stop animation
					currentstep = 0;
					break;
				}
				case 'p': {
					// Resume animation
					currentstep = basicstep;
					break;
				}
				case '+': {
					// Accelerate rotation
					currentstep += basicstep;
					break;
				}
				case '-': {
					// Slow down rotation
					currentstep -= basicstep;
					break;
				}
				case 'n': {
					// Remove material from shape, and set "normal" shader
					shape.setMaterial(null);
					renderContext.useShader(normalShader);
					break;
				}
				case 'd': {
					// Remove material from shape, and set "default" shader
					shape.setMaterial(null);
					renderContext.useDefaultShader();
					break;
				}
				case 'm': {
					// Set a material for more complex shading of the shape
					if(shape.getMaterial() == null) {
						shape.setMaterial(material);
					} else
					{
						shape.setMaterial(null);
						renderContext.useDefaultShader();
					}
					break;
				}
//				case 'c':{
//					removeShapes();
//					shape = cylinder;
//					shape.setMaterial(null);
//					renderContext.useDefaultShader();
//					sceneManager.addShape(shape);
//					currentShape = 0;
//					
//					break;
//				}
//				case 't':{
//					removeShapes();
//					shape = torus;
//					shape.setMaterial(null);
//					renderContext.useDefaultShader();
//					sceneManager.addShape(shape);
//					currentShape = 0;
//					
//					break;
//				}
//				case 'b': {
//					removeShapes();
//					shape = cube;
//					shape.setMaterial(null);
//					renderContext.useDefaultShader();
//					sceneManager.addShape(shape);
//					currentShape = 0;
//					
//					break;
//				}
//				case 'o':{
//					removeShapes();
//					shape = teapot;
//					shape.setMaterial(null);
//					renderContext.useDefaultShader();
//					sceneManager.addShape(shape);
//					currentShape = 0;
//					
//					break;
//				}
//				case 'a':{
//					removeShapes();
//					sceneManager.addShape(cylinder1);
//					sceneManager.addShape(torus1);
//					sceneManager.addShape(torus2);
//					sceneManager.addShape(torus3);
//					sceneManager.addShape(torus4);
//					sceneManager.addShape(plane);
//					currentShape = 1;
//					
//					break;
//				}
//				case '0':{
//					removeShapes();
//					shape = plane;
//					shape.setMaterial(null);
//					renderContext.useDefaultShader();
//					sceneManager.addShape(shape);
//					currentShape = 0;
//					
//					break;
//				}
			}
			
			// Trigger redrawing
			renderPanel.getCanvas().repaint();
		}
		
//		private void removeShapes() {
//			if (currentShape == 0) {
//				sceneManager.removeShape(shape);
//			} else{
//				sceneManager.removeShape(cylinder1);
//				sceneManager.removeShape(torus1);
//				sceneManager.removeShape(torus2);
//				sceneManager.removeShape(torus3);
//				sceneManager.removeShape(torus4);
//				sceneManager.removeShape(plane);
//			}
//		}
		
		public void keyReleased(KeyEvent e)
		{
		}

		public void keyTyped(KeyEvent e)
        {
        }

	}
	
	/**
	 * The main function opens a window 3D rendering window, implemented by the class
	 * {@link SimpleRenderPanel}. {@link SimpleRenderPanel#init} is then called backed 
	 * for initialization automatically by the Java event dispatching thread (EDT), see
	 * <a href="https://stackoverflow.com/questions/7217013/java-event-dispatching-thread-explanation" target="_blank">
	 * this discussion on stackoverflow</a> and <a href="https://en.wikipedia.org/wiki/Event_dispatching_thread" target="_blank">
	 * this explanation on wikipedia</a>. Additional event listeners are added to handle mouse
	 * and keyboard events from the EDT. {@link SimpleRenderPanel#init}
	 * constructs a simple 3D scene, and starts a timer task to generate an animation.
	 */
	public static void main(String[] args)
	{		
		// Make a render panel. The init function of the renderPanel
		// (see above) will be called back for initialization.
		renderPanel = new SimpleRenderPanel();
		
		// Make the main window of this application and add the renderer to it
		JFrame jframe = new JFrame("simple");
		jframe.setSize(500, 500);
		jframe.setLocationRelativeTo(null); // center of screen
		jframe.getContentPane().add(renderPanel.getCanvas());// put the canvas into a JFrame window

		// Add a mouse and key listener
		renderPanel.getCanvas().addMouseMotionListener(new SimpleMouseMotionListener());
	    renderPanel.getCanvas().addMouseListener(new SimpleMouseListener());
	    renderPanel.getCanvas().addKeyListener(new SimpleKeyListener());
		renderPanel.getCanvas().setFocusable(true);   	    	    
	    
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jframe.setVisible(true); // show window
	}
}
