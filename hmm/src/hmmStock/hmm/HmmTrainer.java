package hmmStock.hmm;

import java.util.LinkedList;
import java.util.Map;

public class HmmTrainer {

	private Hmm hmm;

	public HmmTrainer(Hmm hmm) {
		this.hmm = hmm;
	}

	public void train(LinkedList<State> stateSequence) {
		if (!stateSequence.isEmpty()) {
			State previous;
			State current = stateSequence.removeFirst();
			hmm.addStateIfNotContains(current);
			hmm.recordInitialization(current);

			while (!stateSequence.isEmpty()) {
				previous = current;
				current = stateSequence.removeFirst();
				hmm.addStateIfNotContains(current);
				hmm.recordTransition(previous, current);
			}
		}
	}

	public void train(Map<State, EmissionId> emissions) {
		for (State state : emissions.keySet()) {
			hmm.addStateIfNotContains(state);
			hmm.recordEmission(state, emissions.get(state));
		}
	}
}
