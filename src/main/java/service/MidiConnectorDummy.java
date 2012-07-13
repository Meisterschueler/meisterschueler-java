package service;

import java.util.List;
import java.util.Observable;

import com.leff.midi.event.MidiEvent;

public class MidiConnectorDummy extends MidiConnector implements Runnable {
	
	private List<MidiEvent> midiEvents;

	@Override
	public void run() {
		for (int i=0; i<midiEvents.size(); i++) {
			long delta;
			if (i < midiEvents.size()-1) {
				delta = midiEvents.get(i+1).getTick()-midiEvents.get(i).getTick();
			} else {
				delta = 0;
			}
			setChanged();
			notifyObservers(midiEvents.get(i));
			try {
				Thread.sleep(delta);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
