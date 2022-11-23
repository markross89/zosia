package com.zosia.zosia.playlist;

import com.zosia.zosia.box.Box;
import com.zosia.zosia.user.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;


@Service
public class PlaylistService {
	
	private final PlaylistRepository playlistRepository;
	
	public PlaylistService (PlaylistRepository playlistRepository) {
		
		this.playlistRepository = playlistRepository;
	}
	
	public void addPlaylist (String name, User user) {
		
		Playlist playlist = Playlist.builder()
				.name(name)
				.date(Date.valueOf(LocalDate.now()))
				.user(user).build();
		playlistRepository.save(playlist);
	}
}