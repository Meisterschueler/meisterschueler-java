package de.meisterschueler.songprovider;

import de.meisterschueler.basic.SongBook;

public abstract class SongFactory {
	protected SongBook songBook = new SongBook();
	
	public SongBook getSongBook() {
		return songBook;
	}
}
