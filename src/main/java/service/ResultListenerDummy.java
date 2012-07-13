package service;

import basic.MatchingItem;

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
