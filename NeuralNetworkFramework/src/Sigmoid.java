import java.util.ArrayList;

public class Sigmoid{
	private ArrayList<Double> w;		//weights that link the previous layer to the current neuron
	private double b;					//bias
	
	private ArrayList<Sigmoid> next;	//references to the next layer of sigmoid neurons
	private ArrayList<Sigmoid> prev;	//references to the previous layer of sigmoid neurons
	
	private double out;
	private double[] inputs;
	
	public Sigmoid(ArrayList<Sigmoid> n, double[] in){				//Constructor for the input layer of neurons
		next = new ArrayList<Sigmoid>();
		prev = null;
		inputs = new double[in.length];
		w = new ArrayList<Double>();
		b = Math.random();
		next.addAll(n);
		for(int c = 0; c < in.length; c++){
			inputs[c] = in[c];
			w.add(Math.random());
		}
		setOut();
	}
	
	public Sigmoid(ArrayList<Sigmoid> n, ArrayList<Sigmoid> p){	//Constructor for the hidden and output layer of neurons
		next = new ArrayList<>();
		prev = new ArrayList<>();
		inputs = new double[p.size()];
		w = new ArrayList<Double>();
		b = Math.random();
		next.addAll(n);
		prev.addAll(p);
		for(int c = 0; c < p.size(); c ++){
			inputs[c] = p.get(c).output();
			w.add(Math.random());
		}
		setOut();
	}
	
	public void setOut(){									//Set the output of this sigmoid neuron
		double sum = 0; 
		for(int c = 0; c < prev.size(); c ++){				//sum the corresponding weight to the output of the previous sigmoid
			sum += w.get(c) * inputs[c] - b;		//and then subtracting the bias.
		}
		out = 1 / (1 + Math.exp(-1 * sum));					//sigmoid function
	}
	
	public double output(){
		return out;											//uses an output double so that when another neuron needs the
	}														//output, it doesn't need to be recalculated.
	
	public ArrayList<Double> getW(){						//the neural network class will need to access and
		return w;											//change the weights and bias in order to make the network learn.
	}
	
	public void updateWeights(ArrayList<Double> updates){
		for(int c = 0; c < updates.size(); c ++){
			w.set(c, updates.get(c));
		}
	}
	
	public double getB(){
		return b;											
	}
	
	public void updateBias(double update){
		b = update;
	}
	
	public ArrayList<Sigmoid> getNext(){
		return next;
	}
	
	public ArrayList<Sigmoid> getPrev(){
		return prev;
	}
}


