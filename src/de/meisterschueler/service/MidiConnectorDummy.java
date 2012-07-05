package de.meisterschueler.service;

import java.util.List;
import java.util.Observable;

import com.leff.midi.event.MidiEvent;

public class MidiConnectorDummy extends MidiConnector implements Runnable {
	
	private List<MidiEvent> midiEvents;

	@Override
	public void run() {
		long currentTick = getMidiEvents().get(0).getTick();
		for (int i=0; i<getMidiEvents().size(); i++) {
			setChanged();
			notifyObservers(getMidiEvents().get(i));
			if (i<getMidiEvents().size()-1) {
				try {
					Thread.sleep(getMidiEvents().get(i+1).getTick() - currentTick);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				currentTick = getMidiEvents().get(i+1).getTick();
			}
		}
	}

	public List<MidiEvent> getMidiEvents() {
		return midiEvents;
	}

	public void setMidiEvents(List<MidiEvent> midiEvents) {
		this.midiEvents = midiEvents;
	}

	@Override
	public void sendMidiEvent(MidiEvent midiEvent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}
}
