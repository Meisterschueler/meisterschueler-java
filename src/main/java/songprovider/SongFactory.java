package songprovider;

import basic.SongBook;

public abstract class SongFactory {
	protected SongBook songBook = new SongBook();
	
	public SongBook getSongBook() {
		return songBook;
	}
}
