
public class NeuralNetwork {
	private double[][] inputs;
	private double[] outputs;
	private double[] expOut;
	private Sigmoid[][] neuralNetwork;
	
	public NeuralNetwork(double[][] in, double[] eO, int layers, int[] layerSizes){
		inputs = new double[in.length][in[0].length];
		outputs = new double[eO.length];
		expOut = new double[eO.length];
		neuralNetwork = new Sigmoid[layers][];
		
		for(int r = 0; r < in.length; r ++){
			for(int c = 0; c < in[r].length; c ++){
				inputs[r][c] = in[r][c];
			}
		}
		for(int c = 0; c < eO.length; c ++){
			expOut[c] = eO[c];
		}
		for(int l = 0; l < layers; l ++){
			neuralNetwork[l] = new Sigmoid[layerSizes[l]];
			for(int n = 0; n < neuralNetwork[l].length; n ++){
				//neuralNetwork[l][n] = new Sigmoid(null, null);
			}
		}
	}
}
