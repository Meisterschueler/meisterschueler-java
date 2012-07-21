package de.meisterschueler.service;

import static org.junit.Assert.assertEquals;

import java.util.Observable;

import org.junit.Before;
import org.junit.Test;


import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;

import de.meisterschueler.service.KeyboardHandler;


public class KeyboardHandlerTest extends Observable {

	private KeyboardHandler keyboardHandler = new KeyboardHandler();
	
	@Before
	public void setUp() {
		this.addObserver(keyboardHandler);
	}
	
	@Test
	public void test() {
		assertEquals( false, keyboardHandler.isKeyPressed() );
		
		setChanged();
		notifyObservers(new NoteOn(0, 0, 60, 30));
		assertEquals( true, keyboardHandler.isKeyPressed() );
		
		setChanged();
		notifyObservers(new NoteOff(0, 0, 60, 30));
		assertEquals( false, keyboardHandler.isKeyPressed() );
	}
}
