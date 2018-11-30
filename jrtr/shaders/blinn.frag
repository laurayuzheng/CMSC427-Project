#version 150
// GLSL version 1.50
// Fragment shader for diffuse shading in combination with a texture map

// Uniform variables passed in from host program
uniform sampler2D myTexture;
uniform int nLights;

// Variables passed in from the vertex shader
//in float ndotl;
in vec4 frag_s[8];

// Output variable, will be written to framebuffer automatically
out vec4 frag_shaded;

void main()
{	
	frag_shaded = frag_s[0];
	for (int i = 1; i < nLights; i++){
		// The built-in GLSL function "texture" performs the texture lookup
		// frag_shaded = ndotl * texture(myTexture, frag_texcoord);
		
		frag_shaded += frag_s[i];
	}
}

