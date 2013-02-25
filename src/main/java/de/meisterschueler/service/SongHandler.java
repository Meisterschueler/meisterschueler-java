package de.meisterschueler.service;

import java.util.ArrayList;
import java.util.List;

import de.meisterschueler.basic.Song;
import de.meisterschueler.songprovider.BachSongFactory;
import de.meisterschueler.songprovider.BrahmsSongFactory;
import de.meisterschueler.songprovider.HanonSongFactory;

public class SongHandler {
	private HanonSongFactory hanonSongFactory = new HanonSongFactory();
	private BachSongFactory bachSongFactory = new BachSongFactory();
	private BrahmsSongFactory brahmsSongFactory = new BrahmsSongFactory();

	public List<Song> getSongs() {
		List<Song> songs = new ArrayList<Song>();
		songs.addAll(hanonSongFactory.getSongBook().getSongs());
		songs.addAll(bachSongFactory.getSongBook().getSongs());
		songs.addAll(brahmsSongFactory.getSongBook().getSongs());

		return songs ;
	}
}
