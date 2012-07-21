package de.meisterschueler.service;

import java.util.List;

import de.meisterschueler.basic.MidiEventPair;



public class SignalServiceImpl extends SignalService {
	private GuidoService guidoService = new GuidoService();

	public void sendSignal(Signal command) {
		
		String gmn = null;
		switch (command) {
		case STARTUP:
			gmn = "g1/16 {c/4,e,g,c2}";
			break;
		case SHUTDOWN:
			gmn = "c2/16 b1/16 c2/4";
			break;
		case PING:
			gmn = "c1";
			break;
		case DONG:
			gmn = "{g1,c2,e}";
			break;
		case STRONG_DONG:
			gmn = "{g#1,c#2,e#}";
			break;
		default:
			break;

		}
		
		if (gmn != null) {
			List<MidiEventPair> events = guidoService.gmnToMidi(gmn);
			setChanged();
			notifyObservers(events);
		}
	}
}
