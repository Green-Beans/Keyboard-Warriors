package neuralNetwork2;

import java.util.ArrayList;
import java.util.List;

public class Sigmoid {
	private List<Double> w = new ArrayList<Double>(); // weights that link the
														// previous layer to the
														// current neuron
	private double b; // bias

	private List<Sigmoid> next = new ArrayList<Sigmoid>(); // references to the
															// next layer of
															// sigmoid neurons
	private List<Sigmoid> prev = null; // references to the previous layer of
										// sigmoid neurons

	private double out;
	// private double[] inputs;

	public Sigmoid(double in) {
		out = in;
	}

	public Sigmoid(ArrayList<Sigmoid> prev) {
		this.prev = prev;
		for (int c = 0; c < prev.size(); c++) {
			w.add(Math.random());
		}
		b = Math.random();
	}

	public void addPrev(ArrayList<Sigmoid> p) {
		for (int c = 0; c < p.size(); c++) {
			addPrev(p.get(c));
		}
	}

	public void addPrev(Sigmoid p) {
		prev.add(p);
		p.addNext(this);
		w.add(Math.random());
	}

	public void addNext(Sigmoid n) {
		next.add(n);
	}

	public void addNext(ArrayList<Sigmoid> n) {
		for (int c = 0; c < n.size(); c++) {
			addNext(n.get(c));
		}
	}

	public void removeThis() {
		removeAllNext();
		removeAllPrev();
	}

	private void removeAllNext() {
		if (!isOut()) {
			while (!next.isEmpty()) {
				next.remove(1).removePrev(this);
			}
		}
	}

	private void removeAllPrev() {
		if (!isIn()) {
			while (!prev.isEmpty()) {
				prev.remove(1).removeNext(this);
				w.remove(1);
			}
		}
	}

	private void removeNext(Sigmoid n) {
		int c = 0;
		while (!next.get(c).equals(n) && c <= next.size()) {
			c++;
		}
		if (c <= next.size()) {
			next.remove(c);
		} else {
			System.out.println("Next node can not be found: " + n.toString());
		}
	}

	private void removePrev(Sigmoid p) {
		int c = 0;
		while (!prev.get(c).equals(p) && c <= prev.size()) {
			c++;
		}
		if (c <= prev.size()) {
			prev.remove(c);
			w.remove(c);
		} else {
			System.out.println("Previous node can not be found: " + p.toString());
		}

	}

	public void setOut() {
		if (!isIn()) {
			double sum = 0;
			for (int c = 0; c < prev.size(); c++) {
				sum += w.get(c) * prev.get(c).getOut();
			}
			sum += b;
			out = 1 / (1 + Math.exp(-1 * sum));
		}
	}

	public void updateWeights(double[] update) {
		for (int c = 0; c < update.length; c++) {
			w.set(c, update[c]);
		}
	}

	public void updateBias(double update) {
		b = update;
	}

	public double getOut() {
		return out;
	}

	public double getBias() {
		return b;
	}

	public List<Double> getWeights() {
		return w;
	}

	public List<Sigmoid> getNext() {
		return next;
	}

	public List<Sigmoid> getPrev() {
		return prev;
	}

	public boolean isIn() {
		if (prev == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOut() {
		if (next.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
