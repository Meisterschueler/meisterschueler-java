package de.meisterschueler.service;

import java.util.Observable;

public abstract class SignalService extends Observable {
	public enum Signal {STARTUP, PING, DONG, STRONG_DONG, SHUTDOWN, RECORDING_FINISHED}

	public abstract void sendSignal(Signal ping);;
}
