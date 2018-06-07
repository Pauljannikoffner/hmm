package hmmStock.hmm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Hmm {
	private Set<State> states;
	private Map<State, Integer> recordedInitialDistributions;

	public Hmm() {
		states = new HashSet<State>();
		recordedInitialDistributions = new HashMap<State, Integer>();
	}

	public void addStateIfNotContains(State state) {
		if (!contains(state)) {
			states.add(state);
		}
	}

	private boolean contains(State state) {
		for (State containedState : states) {
			if (containedState.equals(state)) {
				return true;
			}
		}
		return false;
	}

	public Set<State> getStates() {
		return states;
	}

	public void recordInitialization(State state) {
		if (recordedInitialDistributions.containsKey(state)) {
			recordedInitialDistributions.put(state,
					new Integer(recordedInitialDistributions.get(state).intValue() + 1));
		} else {
			recordedInitialDistributions.put(state, new Integer(1));
		}
	}

	public double getInitialProbability(State state) {
		if (!recordedInitialDistributions.containsKey(state)) {
			return 0.0;
		} else {
			return (double) recordedInitialDistributions.get(state).intValue() / amountOfRecordedInitialDistributions();
		}
	}

	private int amountOfRecordedInitialDistributions() {
		int result = 0;
		for (Integer probability : recordedInitialDistributions.values()) {
			result += probability.intValue();
		}
		return result;
	}

	public void recordTransition(State current, State next) {
		current.recordTransition(next);
	}

	public double getTransitionProbability(State current, State next) {
		return current.getTransitionProbability(next);
	}

	public void recordEmission(State state, EmissionId emission) {
		state.recordEmission(emission);
	}

	public double getEmissionProbability(State state, String emission) {
		return state.getEmissionProbability(emission);
	}
}
