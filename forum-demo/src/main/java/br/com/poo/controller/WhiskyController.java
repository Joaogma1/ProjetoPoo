package br.com.poo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.poo.model.Vodka;
import br.com.poo.model.Whisky;
import br.com.poo.repository.WhiskyRepository;

@RestController
@RequestMapping("/api")
public class WhiskyController {
	
	@Autowired
	private WhiskyRepository whiskyRepository;
	@CrossOrigin()
	@GetMapping("/Whisky")
	public List<Whisky> get() {
		return whiskyRepository.findAll();
	}
	@CrossOrigin()
	@PutMapping("/Whisky/{id}")
	public ResponseEntity<Object> update(@RequestBody Whisky whisky, @PathVariable long id) {

		Optional<Whisky> whiskyOptional = whiskyRepository.findById(id);

		if (!whiskyOptional.isPresent())
			return ResponseEntity.notFound().build();

		whisky.setId(id);
		
		whiskyRepository.save(whisky);

		return ResponseEntity.noContent().build();
	}
	@CrossOrigin()
	@PostMapping("/Whisky")
	public ResponseEntity<Object> cadastrarVodka(@RequestBody Whisky data) {
		
		Whisky savedWhisky = whiskyRepository.save(data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedWhisky.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
