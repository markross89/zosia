package com.zosia.zosia.album;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zosia.zosia.user.CurrentUser;
import com.zosia.zosia.user.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AlbumController {
	
	private final AlbumService albumService;
	private final AlbumRepository albumRepository;
	private final UserRepository userRepository;
	
	
	public AlbumController (AlbumService albumService, AlbumRepository albumRepository, UserRepository userRepository) {
		
		this.albumService = albumService;
		this.albumRepository = albumRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/details/{id}")
	public String albumDetails (@PathVariable long id, Model model) throws JsonProcessingException {
		
		model.addAttribute("album", albumService.requestAlbumBuilder(Album.class, id));
		return "/details";
	}
	
	@GetMapping("/save/{id}")
	public String saveAlbum (@PathVariable long id, @AuthenticationPrincipal CurrentUser customUser) throws JsonProcessingException {
		
		albumService.saveAlbum(id, userRepository.findById(customUser.getUser().getId()).get());
		return "redirect:/albums";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAlbum (@PathVariable long id, @AuthenticationPrincipal CurrentUser customUser) {
		
		albumService.deleteAlbum(id, userRepository.findById(customUser.getUser().getId()).get());
		return "redirect:/albums";
	}
	
	@GetMapping("/albums")
	public String displayAlbums (Model model, @AuthenticationPrincipal CurrentUser customUser, @RequestParam(defaultValue = "0") int page,
								 @RequestParam(defaultValue = "48") int size) {
		

		model.addAttribute("albums", albumRepository.findAlbumsByUsers(customUser.getUser(), PageRequest.of(page, size)));
		return "/albums";
	}
}
