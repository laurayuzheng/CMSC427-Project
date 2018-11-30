#version 150
// GLSL version 1.50 
// Vertex shader for diffuse shading in combination with a texture map

// Uniform variables, passed in from host program via suitable 
// variants of glUniform*
uniform mat4 projection;
uniform mat4 modelview;
uniform vec4 lightPosition[8];
uniform vec3 cl[8];
uniform int nLights;
uniform sampler2D myTexture;

// Input vertex attributes; passed in from host program to shader
// via vertex buffer objects
in vec3 normal;
in vec4 position;
in vec2 texcoord;

// Output variables for fragment shader
//out float ndotl;
//out vec2 frag_texcoord;
out vec4 frag_s[8];

void main()
{		
		float ndotl;
		vec2 frag_texcoord;
	
		for (int i = 0; i < nLights; i++) {
			// Compute dot product of normal and light direction
			// and pass color to fragment shader
			// Note: here we assume "lightDirection" is specified in camera coordinates,
			// so we transform the normal to camera coordinates, and we don't transform
			// the light direction, i.e., it stays in camera coordinates
			vec4 lightDirection = normalize(lightPosition[i] - (modelview * position));
			ndotl = max(dot(normalize(modelview * vec4(normal,0)), lightDirection),0);
		
			// Pass texture coordinates to fragment shader, OpenGL automatically
			// interpolates them to each pixel  (in a perspectively correct manner) 
			frag_texcoord = texcoord;
		
			// Transform position, including projection matrix
			// Note: gl_Position is a default output variable containing
			// the transformed vertex position
			gl_Position = projection * modelview * position;
			
			float length = length(lightPosition[i] - (modelview * position));
			vec4 color = vec4(cl[i] / (length * length),1);
			
			frag_s[i] = color * ndotl * texture(myTexture, frag_texcoord);
	}
}
