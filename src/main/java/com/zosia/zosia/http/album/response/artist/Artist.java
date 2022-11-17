package com.zosia.zosia.http.album.response.artist;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zosia.zosia.http.album.response.album.Album;


import javax.persistence.*;
import java.util.List;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
	
	@Id
	@Column(nullable = false)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "artists")
	private List<Album> albums;
	
	public List<Album> getAlbums () {
		
		return albums;
	}
	
	public void setAlbums (List<Album> albums) {
		
		this.albums = albums;
	}
	
	public void addAlbum (Album album) {
		
		album.addArtist(this);
		this.albums.add(album);
	}
	
	public void removeAlbum(Album album){
		
		this.getAlbums().remove(this);
		album.getArtists().remove(this);
	}
	
	public Long getId () {
		
		return id;
	}
	
	public void setId (Long id) {
		
		this.id = id;
	}
	
	
	public String getName () {
		
		return name;
	}
	
	public void setName (String name) {
		
		this.name = name;
	}
	
}
