package service;

import java.util.Observable;

public abstract class SignalService extends Observable {
	public enum Signal {STARTUP, PING, DONG, STRONG_DONG, SHUTDOWN}

	public abstract void sendSignal(Signal ping);;
}
