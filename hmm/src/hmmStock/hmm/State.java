package hmmStock.hmm;

import java.util.HashMap;
import java.util.Map;

public class State {

	private Map<State, Integer> recordedTransitions;
	private Map<EmissionId, Integer> recordedEmissions;

	private final StateId stateId;

	public State(StateId stateId) {
		this.stateId = stateId;
		recordedTransitions = new HashMap<State, Integer>();
		recordedEmissions = new HashMap<EmissionId, Integer>();
	}

	public void recordTransition(State next) {
		if (recordedTransitions.containsKey(next)) {
			recordedTransitions.put(next, new Integer(recordedTransitions.get(next).intValue() + 1));
		} else {
			recordedTransitions.put(next, new Integer(1));
		}
	}

	public void recordEmission(EmissionId emission) {
		if (recordedEmissions.containsKey(emission)) {
			recordedEmissions.put(emission, new Integer(recordedEmissions.get(emission).intValue() + 1));
		} else {
			recordedEmissions.put(emission, new Integer(1));
		}
	}

	public double getTransitionProbability(State next) {
		if (!recordedTransitions.containsKey(next)) {
			return 0.0;
		} else {
			return (double) recordedTransitions.get(next).intValue() / amountOfRecordedTransisitons();
		}
	}

	private int amountOfRecordedTransisitons() {
		int result = 0;
		for (Integer amountOfRecordedTransition : recordedTransitions.values()) {
			result += amountOfRecordedTransition.intValue();
		}
		return result;
	}

	public double getEmissionProbability(String emission) {
		if (!recordedEmissions.containsKey(emission)) {
			return 0.0;
		} else {
			return (double) recordedEmissions.get(emission).intValue() / amountOfRecordedEmissions();
		}
	}

	private int amountOfRecordedEmissions() {
		int result = 0;
		for (Integer amountOfRecordedEmission : recordedEmissions.values()) {
			result += amountOfRecordedEmission.intValue();
		}
		return result;
	}

	public StateId getStateId() {
		return stateId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (stateId != other.stateId)
			return false;
		return true;
	}
}
