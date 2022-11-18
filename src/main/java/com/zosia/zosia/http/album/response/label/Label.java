package com.zosia.zosia.http.album.response.label;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zosia.zosia.http.album.response.album.Album;
import com.zosia.zosia.http.album.response.artist.Artist;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Label {
	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String catno;
	private String name;
	@ManyToMany(mappedBy = "labels")
	private List<Album> albums;
	
	
	public void addAlbum (Album album) {
		
		album.addLabel(this);
		this.albums.add(album);
	}
	
	
}