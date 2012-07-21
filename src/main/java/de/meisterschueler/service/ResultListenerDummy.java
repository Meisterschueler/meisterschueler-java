package de.meisterschueler.service;

import de.meisterschueler.basic.MatchingItem;

public class ResultListenerDummy implements ResultListener {

	private MatchingItem result;

	public MatchingItem getLastResult() {
		return result;
	}
	
	@Override
	public void gotResult(MatchingItem item) {
		result = item;
	}
}
